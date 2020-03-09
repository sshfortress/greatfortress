
package com.sshfortress.service.system;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshfortress.common.beans.EmailOrder;
import com.sshfortress.common.beans.EmailRegcode;
import com.sshfortress.common.contants.RegCodeConstants;
import com.sshfortress.common.enums.ViewShowEnums;
import com.sshfortress.common.util.DateUtilSelf;
import com.sshfortress.common.util.ResponseObj;
import com.sshfortress.dao.system.mapper.EmailRegcodeMapper;
import com.sshfortress.service.helper.MailHelper;


@Service("emailRegCodeService")
public class EmailRegCodeService {

    @Autowired
    private MailHelper       mailHelper;

    @Autowired
    private EmailRegcodeMapper emailRegcodeMapper;

    
     /** <p class="detail">
     * 鍔熻兘:鍙戦�楠岃瘉鐮�
     * </p>
     * @param appCode
     * @param email
     * @param verifyCode
     * @param emailOrder
     * @return    
     */
    public ResponseObj sendRegCode( String email, String verifyCode,
                                   EmailOrder emailOrder) {

        String finalLock = new StringBuilder(RegCodeConstants.emailLockKey).append("_").append(email).toString();

       
        synchronized (finalLock.intern()) {

            EmailRegcode emailRegcode = emailRegcodeMapper.selectByPrimaryKey(email);

            if (null != emailRegcode) {
                int minute = DateUtilSelf.calculateDecreaseSecond(emailRegcode.getSendTime(),
                    new Date());

               
                if (minute < RegCodeConstants.repMaxSecond) {
                    return new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(), "楠岃瘉鐮佸彂閫佷腑锛岃绛夊緟");
                }
            }

         
            if (mailHelper.sendMail(emailOrder)) {
                if (null == emailRegcode) {
                    emailRegcode = new EmailRegcode();
                    emailRegcode.setEmail(email);
                    emailRegcode.setEmailCode(verifyCode);
                    emailRegcode.setSendTime(new Date());
                    emailRegcodeMapper.insert(emailRegcode);
                    return new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(), "楠岃瘉鐮佸凡鍙戦�锛岃绛夊緟");
                }

                emailRegcode.setEmailCode(verifyCode);
                emailRegcode.setSendTime(new Date());
                emailRegcodeMapper.updateByPrimaryKey(emailRegcode);
                return new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(), "楠岃瘉鐮佸凡鍙戦�锛岃绛夊緟");
            }
        }

        return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), "楠岃瘉鐮佸彂閫佸け璐ヨ閲嶈瘯");

    }

    
     /** <p class="detail">
     * 鍔熻兘锛氳幏鍙栨椂鏁堟�鍐呯殑楠岃瘉鐮�
     * </p>
     * @param appCode
     * @param email
     * @param timeliness 楠岃瘉鐮佸け鏁堟椂闂�鍗曚綅鍒嗛挓,榛樿5鍒嗛挓
     * @return    
     */
    public ResponseObj getTimelinessRegCode( String email, Integer timeliness) {

        EmailRegcode emailRegcode = emailRegcodeMapper.selectByPrimaryKey(email);

        if (null == emailRegcode) {
            return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), "楠岃瘉鐮佽繕鏈幏鍙栧埌");
        }

        int minute = DateUtilSelf.calculateDecreaseMinute(emailRegcode.getSendTime(), new Date());

        int maxTimeliness = null != timeliness ? timeliness.intValue()
            : RegCodeConstants.timeliness;

    
        if (minute > maxTimeliness) {
            return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), "楠岃瘉鐮佸凡杩囨湡");
        }

        return new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
            ViewShowEnums.INFO_SUCCESS.getDetail(), emailRegcode.getEmailCode());
    }

}
