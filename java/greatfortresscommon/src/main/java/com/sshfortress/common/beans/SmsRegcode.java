package com.sshfortress.common.beans;

import java.util.Date;

/**
 * 
 * 短信发送验证码
 *
 */
public class SmsRegcode {
    private String phone;

    private String smsCode;

    private Date sendTime;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}