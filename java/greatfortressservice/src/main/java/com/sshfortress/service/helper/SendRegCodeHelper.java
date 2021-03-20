///*
// *  
// * This software is the confidential and proprietary information of
// * shall not disclose such Confidential Information and shall use
// * it only in accordance with the terms of the license agreement
// */
//package com.sshfortress.service.helper;
//
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.sshfortress.common.beans.EmailOrder;
//import com.sshfortress.common.beans.SMSDTO;
//import com.sshfortress.common.beans.SmsSendOrder;
//import com.sshfortress.common.contants.RegCodeConstants;
//import com.sshfortress.common.enums.RegCodeType;
//import com.sshfortress.common.enums.UserNameType;
//import com.sshfortress.common.enums.ViewShowEnums;
//import com.sshfortress.common.util.AutoCodeUtils;
//import com.sshfortress.common.util.ResponseObj;
//import com.sshfortress.service.system.EmailRegCodeService;
//import com.sshfortress.service.system.SmsRegCodeService;
//import com.sshfortress.service.system.SysConfigService;
//
///** <p class="detail">
// * 功能�?
// * </p>
// * @ClassName: SendRegCodeHelper 
// * @version V1.0  
// */
//@Component("sendRegCodeHelper")
//public class SendRegCodeHelper {
//
//    @Autowired
//    private SmsRegCodeService    smsRegCodeService;
//
//    @Autowired
//    private EmailRegCodeService  emailRegCodeService;
//
//    @Autowired
//    private SysConfigService sysConfigService;
//
//    /** <p class="detail">
//    * 功能：设置发送验证码
//    * </p>
//    * @param userNameType  绑定类型：邮箱还是手机号
//    * @param appCode 
//    * @param phoneOrEmail  接收验证码的�?
//    * @param regCodeType   发�?标题类型   注册验证码or绑定验证码or找回密码验证码等
//    * @return    
//    */
//    public ResponseObj sendRegCode(UserNameType userNameType, String phoneOrEmail,
//                                   RegCodeType regCodeType) {
//    	System.out.println("进入service sendregcodehelper方法");
//        if (null == userNameType) {
//            return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), "账号为手机号码或邮箱");
//        }
//
//        // 生成6位数字验证码
//        String verifyCode = AutoCodeUtils.getRandNum(6);
//
//        switch (userNameType) {
//            case PHONE:
//            	
//            	if((sysConfigService.getValue("sms_ytx_open")==null?"0":sysConfigService.getValue("sms_ytx_open")).equals("1")){
//                // 验证码失效时�?分钟
//                String sms_ytx_valid_min = sysConfigService.getValue("sms_ytx_valid_min");
//
//                String timeliness = StringUtils.isBlank(sms_ytx_valid_min) ? String
//                    .valueOf(RegCodeConstants.timeliness) : sms_ytx_valid_min;
//
//                // 数组顺序即短信模板占位符顺序
//                String contents[] = { regCodeType.getDetail(), verifyCode, timeliness };
//
//                SmsSendOrder smsSendOrder = new SmsSendOrder();
//                smsSendOrder.setReceiverPhone(phoneOrEmail);
//                smsSendOrder.setContents(contents);
//                fill(smsSendOrder);
//                return smsRegCodeService.sendRegCode(verifyCode, smsSendOrder);
//            	}
//            	/**阿里大鱼短信发送规则*/
//            	if((sysConfigService.getValue("sms_alidayu_open")==null?"0":sysConfigService.getValue("sms_alidayu_open")).equals("1")){
//                    // 验证码失效时�?分钟
//                    String sms_alidayu_valid_min = sysConfigService.getValue("sms_alidayu_valid_min");
//
//                    String timeliness = StringUtils.isBlank(sms_alidayu_valid_min) ? String
//                        .valueOf(RegCodeConstants.timeliness) : sms_alidayu_valid_min;
//
//                    // 数组顺序即短信模板占位符顺序
//                    //String contents[] = { regCodeType.getDetail(), verifyCode, timeliness };                 
//                    //SmsSendOrder smsSendOrder = new SmsSendOrder();
//                    //smsSendOrder.setReceiverPhone(phoneOrEmail);
//                    //smsSendOrder.setContents(contents);
//                    //fill(smsSendOrder);
//                    
//                    SMSDTO smsDto=new SMSDTO();
//                    smsDto.setContent(verifyCode);
//                    smsDto.setPhoneNum(phoneOrEmail);
//                    fillAliDaYu(regCodeType,smsDto);
//                    return smsRegCodeService.sendRegCodeByAliDaYu(smsDto);
//                }
//            	
//            	
//            	/**互亿无线短信发送规则*/
//            	if((sysConfigService.getValue("sms_huyiwx_open")==null?"0":sysConfigService.getValue("sms_huyiwx_open")).equals("1")){
//                    // 验证码失效时�?分钟
//            		System.out.println("进入互亿无线短信发送通道");
//                    String sms_huyiwx_valid_min = sysConfigService.getValue("sms_huyiwx_valid_min");
//
//                    String timeliness = StringUtils.isBlank(sms_huyiwx_valid_min) ? String
//                        .valueOf(RegCodeConstants.timeliness) : sms_huyiwx_valid_min;
//                    
//                    SMSDTO smsDto=new SMSDTO();
//                    smsDto.setContent(verifyCode);
//                    smsDto.setPhoneNum(phoneOrEmail);
//                   
//                    return smsRegCodeService.sendRegCodeByHuyi(smsDto);
//                }
//            	
//            	
//            case EMAIL:
//                EmailOrder emailOrder = new EmailOrder();
//
//                String mailTitle = regCodeType.getDetail() + "验证";
//                String mailContent = "您正在用电子邮箱进行" + regCodeType.getDetail() + "，本次验证码<h2>"
//                                     + verifyCode + "</h2>  如非本人操作请忽略该邮件";
//
//                emailOrder.setSender(sysConfigService.getValue("email_username"));
//                emailOrder.setHost(sysConfigService.getValue("email_service"));
//                emailOrder.setUserName(sysConfigService.getValue("email_username"));
//                emailOrder.setPassword(sysConfigService.getValue("email_password"));
//
//                emailOrder.setReceive(phoneOrEmail);
//                emailOrder.setTitle(mailTitle);
//                emailOrder.setContent(mailContent);
//                return emailRegCodeService.sendRegCode(phoneOrEmail, verifyCode, emailOrder);
//
//            default:
//                return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(),
//                    ViewShowEnums.ERROR_FAILED.getDetail());
//        }
//    }
//
//     /** <p class="detail">
//     * 功能：填充参数
//     * </p>
//     * @param smsSendOrder    
//     */
//    private void fill(SmsSendOrder smsSendOrder) {
//
//        String smsUrl = sysConfigService.getValue("sms_ytx_url");
//        smsSendOrder.setSmsUrl(smsUrl);
//        String smsPort = sysConfigService.getValue("sms_ytx_port");
//        smsSendOrder.setSmsPort(smsPort);
//        String accountName = sysConfigService.getValue("sms_ytx_sid");
//        smsSendOrder.setAccountName(accountName);
//        String accountPsd = sysConfigService.getValue("sms_ytx_token");
//        smsSendOrder.setAccountPsd(accountPsd);
//        String appId = sysConfigService.getValue("sms_ytx_appid");
//        smsSendOrder.setAppId(appId);
//        String smsTempid = sysConfigService.getValue("sms_ytx_tempid");
//        smsSendOrder.setSmsTempid(smsTempid);
//    }
//    
//    
//    /**
//     * <p class="detail">
//     * 功能：填充阿里的发送参数
//     * </p> str=6位随机数 
//     * username= 手机号码
//     * @param str
//     * @param username
//     * @return
//     */
//    private void fillAliDaYu(RegCodeType rct,SMSDTO smsDto) {
//        
//		//smsDto.setContent(str);
//		//smsDto.setPhoneNum(userName);
//		smsDto.setSignName(sysConfigService.getValue("sms_alidayu_sign_name"));
//		smsDto.setSmsTemplateCode(sysConfigService.getValue("sms_alidayu_template_code"));
//		String time=sysConfigService.getValue("sms_alidayu_valid_time");
//		smsDto.setParam("{\"code\":\"  "+smsDto.getContent()+"\",\"time\":\""+time+"\"}");
//
//
//    }
//    
//    
//    
//}
