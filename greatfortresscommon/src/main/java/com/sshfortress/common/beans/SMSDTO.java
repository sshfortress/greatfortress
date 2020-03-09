package com.sshfortress.common.beans;

public class SMSDTO {
private String signName;//签名
private String userId;//用户ID
private String phoneNum;//电话号码
private String SmsTemplateCode;//短信模板
private String smsType="normal";//默认normal
private String content;//内容
private String param;//键值对
public String getSignName() {
	return signName;
}
public void setSignName(String signName) {
	this.signName = signName;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getPhoneNum() {
	return phoneNum;
}
public void setPhoneNum(String phoneNum) {
	this.phoneNum = phoneNum;
}
public String getSmsTemplateCode() {
	return SmsTemplateCode;
}
public void setSmsTemplateCode(String smsTemplateCode) {
	SmsTemplateCode = smsTemplateCode;
}
public String getSmsType() {
	return smsType;
}
public void setSmsType(String smsType) {
	this.smsType = smsType;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getParam() {
	return param;
}
public void setParam(String param) {
	this.param = param;
}

}
