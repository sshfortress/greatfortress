/*
 *  
 * This software is the confidential and proprietary information of
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 */
package com.sshfortress.service.helper;

import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.sshfortress.common.beans.EmailOrder;



@Service("mailHelper")
public class MailHelper {

    /** 日志. */
    private static final Log   logger = LogFactory.getLog(MailHelper.class);

    @Autowired
    private JavaMailSenderImpl mailSender;

    public boolean sendMail(EmailOrder emailOrder) {
        try {
            // 发�?者认证权�?
            mailSender.setUsername(emailOrder.getUserName()); // 根据自己的情况设置发用户名
            mailSender.setPassword(emailOrder.getPassword()); // 根据自己的情况 设置password

            Properties prop = new Properties();
            prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认�?认证用户名和密码是否正确
            prop.put("mail.smtp.timeout", "25000");

            mailSender.setJavaMailProperties(prop);

            //设定mail server 
            mailSender.setHost(emailOrder.getHost());
            //建立邮件消息,发送邮件和html邮件的区域
            MimeMessage mailMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");

            // 发�?人邮�?
            messageHelper.setFrom(emailOrder.getSender());
            // 设置收件人邮箱
            messageHelper.setTo(emailOrder.getReceive());

            // 主题
            messageHelper.setSubject(emailOrder.getTitle());
            // true 表示启动HTML格式的邮箱
            messageHelper.setText("<html><head></head><body>" + emailOrder.getContent()
                                  + "</body></html>", true);

            mailSender.send(mailMessage); //发�?邮件 
            return true;
        } catch (Exception e) {
            logger.error("email发�?异常", e);
        }
        return false;
    }
}
