package com.sshfortress.common.beans;

import java.util.Date;

/**
 * 邮件验证码
 *
 */
public class EmailRegcode {
    private String email;

    private String emailCode;

    private Date sendTime;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailCode() {
        return emailCode;
    }

    public void setEmailCode(String emailCode) {
        this.emailCode = emailCode;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}