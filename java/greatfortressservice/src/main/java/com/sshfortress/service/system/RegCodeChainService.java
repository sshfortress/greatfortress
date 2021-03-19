//package com.sshfortress.service.system;
//
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//
//
//
//
//import com.sshfortress.common.enums.SendCodeTarget;
//import com.sshfortress.common.enums.ViewShowEnums;
//import com.sshfortress.common.util.ResponseObj;
//import com.sshfortress.common.util.SendCodeTargetUtils;
//
//
//@Service("regCodeChainService")
//public class RegCodeChainService {
//	@Autowired
//	SmsRegCodeService  smsRegCodeService;
//	@Autowired
//	EmailRegCodeService  emailRegCodeService;
//	
//	public ResponseObj validatorRegCode(String phoneOrEmail, String regCode, Integer timeliness) {
//
//		SendCodeTarget sendCodeTarget = SendCodeTargetUtils.getTypeByTarget(phoneOrEmail);
//
//		ResponseObj appResponseObj = new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), "账号为手机号码或邮箱");
//
//		if (null == sendCodeTarget) {
//			return appResponseObj;
//		}
//
//		switch (sendCodeTarget) {
//		case PHONE:
//			
//			appResponseObj = smsRegCodeService.getTimelinessRegCode(phoneOrEmail, timeliness);
//
//			if (appResponseObj.getStatus() == 0) {
//				return appResponseObj;
//			}
//
//			if (!StringUtils.equals(appResponseObj.getData().toString(), regCode)) {
//				return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), "验证码错误");
//			}
//
//			return new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(), ViewShowEnums.INFO_SUCCESS.getDetail());
//
//		case EMAIL:
//			
//			appResponseObj = emailRegCodeService.getTimelinessRegCode(phoneOrEmail, timeliness);
//
//			if (appResponseObj.getStatus() == 0) {
//				return appResponseObj;
//			}
//
//			if (!StringUtils.equals(appResponseObj.getData().toString(), regCode)) {
//				return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), "验证码错误");
//			}
//
//			return new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(), ViewShowEnums.INFO_SUCCESS.getDetail());
//
//		default:
//			return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), ViewShowEnums.ERROR_FAILED.getDetail());
//		}
//
//	}
//}
