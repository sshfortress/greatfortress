//
//package com.sshfortress.service.system;
//
//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.sshfortress.common.beans.SMSDTO;
//import com.sshfortress.common.beans.SmsRegcode;
//import com.sshfortress.common.beans.SmsSendOrder;
//import com.sshfortress.common.config.DomainConfig;
//import com.sshfortress.common.contants.RegCodeConstants;
//import com.sshfortress.common.enums.ViewShowEnums;
//import com.sshfortress.common.util.DateUtilSelf;
//import com.sshfortress.common.util.ResponseObj;
//import com.sshfortress.dao.system.mapper.SmsRegcodeMapper;
//import com.sshfortress.service.helper.PhoneHelper;
//
//
//@Service("smsRegCodeService")
//public class SmsRegCodeService {
//
//    @Autowired
//    private PhoneHelper    phoneHelper;
//
//    @Autowired
//    private SmsRegcodeMapper smsRegcodeMapper;
//    @Autowired
//    DomainConfig domainConfig;
//
//   
//    public ResponseObj sendRegCode( String verifyCode, SmsSendOrder smsSendOrder) {
//
//        String finalLock = new StringBuilder(RegCodeConstants.smsLockKey).append("_").append(smsSendOrder.getReceiverPhone()).toString();
//
//      
//        synchronized (finalLock.intern()) {
//
//        	SmsRegcode smsRegcode = smsRegcodeMapper.selectByPrimaryKey(smsSendOrder.getReceiverPhone());
//            if (null != smsRegcode) {
//                int second = DateUtilSelf.calculateDecreaseSecond(smsRegcode.getSendTime(),
//                    new Date());
//
//               
//                if (second < RegCodeConstants.repMaxSecond) {
//                    return new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(), "验证码发送中，请等待");
//                }
//            }
//
//            boolean flag = false;
//
//    
//            if (domainConfig.isDebug()) {
//                flag = phoneHelper.sendRegCodeByXYT(smsSendOrder.getReceiverPhone(), verifyCode);
//            } else {
//                flag = phoneHelper.sendRegCodeByYTX(smsSendOrder);
//            }
//
//            if (flag) {
//                if (null == smsRegcode) {
//                	
//                    smsRegcode = new SmsRegcode();
//                    smsRegcode.setPhone(smsSendOrder.getReceiverPhone());
//                    smsRegcode.setSmsCode(verifyCode);
//                    smsRegcode.setSendTime(new Date());
//                    smsRegcodeMapper.insert(smsRegcode);
//                    return new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(), "验证码已发送，请等待");
//                }
//
//                smsRegcode.setSmsCode(verifyCode);
//                smsRegcode.setSendTime(new Date());
//                smsRegcodeMapper.updateByPrimaryKey(smsRegcode);
//                return new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(), "验证码已发送，请等待");
//            }
//        }
//
//        return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), "验证码发送失败请重试");
//
//    }
//
//    
//   
//    public ResponseObj getTimelinessRegCode(String phone, Integer timeliness) {
//
//    	SmsRegcode smsRegcode = smsRegcodeMapper.selectByPrimaryKey(phone);
//
//        if (null == smsRegcode) {
//            return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), "验证码还未获取到");
//        }
//
//        int minute = DateUtilSelf.calculateDecreaseMinute(smsRegcode.getSendTime(), new Date());
//
//        int maxTimeliness = null != timeliness ? timeliness.intValue()
//            : RegCodeConstants.timeliness;
//
//
//        if (minute > maxTimeliness) {
//            return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), "验证码已过期");
//        }
//
//        return new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
//            ViewShowEnums.INFO_SUCCESS.getDetail(), smsRegcode.getSmsCode());
//    }
//    
//    
//    /**
//     * <p class="detail">
//     * 功能：通过阿里大鱼发送短信
//     * </p>
//     * @param verifyCode
//     * @param smsSendOrder
//     * @return
//     */
//    
//    public ResponseObj sendRegCodeByAliDaYu(SMSDTO sm) {
//
//    
//
//        	SmsRegcode smsRegcode = smsRegcodeMapper.selectByPrimaryKey(sm.getPhoneNum());
//            if (null != smsRegcode) {
//                int second = DateUtilSelf.calculateDecreaseSecond(smsRegcode.getSendTime(),
//                    new Date());
//
//               
//                if (second < RegCodeConstants.repMaxSecond) {
//                    return new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(), "验证码发送中，请等待");
//                }
//            }
//            phoneHelper.sendSmsByAliDaYu(sm);
//                if (null == smsRegcode) {
//                	
//                    smsRegcode = new SmsRegcode();
//                    smsRegcode.setPhone(sm.getPhoneNum());
//                    smsRegcode.setSmsCode(sm.getContent());
//                    smsRegcode.setSendTime(new Date());
//                    smsRegcodeMapper.insert(smsRegcode);
//                    return new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(), "验证码已发送，请等待");
//                }
//
//                smsRegcode.setSmsCode(sm.getContent());
//                smsRegcode.setSendTime(new Date());
//                smsRegcodeMapper.updateByPrimaryKey(smsRegcode);
//                return new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(), "验证码已发送，请等待");
//
//    }
//    
//    
//    
//    /**
//     * <p class="detail">
//     * 功能：互动无线通过
//     * </p>
//     * @param sm
//     * @return
//     */
//    public ResponseObj sendRegCodeByHuyi(SMSDTO sm) {
//
//        
//    	System.out.println("sendRegCodeByHuyi");
//    	SmsRegcode smsRegcode = smsRegcodeMapper.selectByPrimaryKey(sm.getPhoneNum());
//        if (null != smsRegcode) {
//            int second = DateUtilSelf.calculateDecreaseSecond(smsRegcode.getSendTime(),
//                new Date());
//
//           
//            if (second < RegCodeConstants.repMaxSecond) {
//                return new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(), "验证码发送中，请等待");
//            }
//        }
//        phoneHelper.sendSmsByHuYi(sm);
//            if (null == smsRegcode) {
//            	
//                smsRegcode = new SmsRegcode();
//                smsRegcode.setPhone(sm.getPhoneNum());
//                smsRegcode.setSmsCode(sm.getContent());
//                smsRegcode.setSendTime(new Date());
//                smsRegcodeMapper.insert(smsRegcode);
//                return new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(), "验证码已发送，请等待");
//            }
//
//            smsRegcode.setSmsCode(sm.getContent());
//            smsRegcode.setSendTime(new Date());
//            smsRegcodeMapper.updateByPrimaryKey(smsRegcode);
//            return new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(), "验证码已发送，请等待");
//
//}
//    
//}
