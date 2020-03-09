package com.sshfortress.common.beans;

/** <p class="detail">
 * 功能：
 * </p> 邮件类
 * @ClassName: EmailOrder 
 * @version V1.0  
 */
public class EmailOrder {

    /** 
     * <p class="detail">
     * 功能：标题
     * </p>
     * @Fields mailTitle 
     */
    private String title;

    /** 
     * <p class="detail">
     * 功能：正文内容
     * </p>
     * @Fields mailContent 
     */
    private String content;

    /** 
     * <p class="detail">
     * 功能：收件人邮箱
     * </p>
     * @Fields emailReceive 
     */
    private String receive;

    /** 
     * <p class="detail">
     * 功能：发送服务 : smtp.qq.com
     * </p>
     * @Fields emailHost
     */
    private String host;

    /** 
     * <p class="detail">
     * 功能：发件人账号
     * </p>
     * @Fields emailUsername 
     */
    private String sender;

    /** 
     * <p class="detail">
     * 功能：服务器认证账号
     * </p>
     * @Fields userName 
     */
    private String userName;

    /** 
     * <p class="detail">
     * 功能：邮件认证服务密码
     * </p>
     * @Fields emailPassword 
     */
    private String password;

    /**
     * @return title
     */

    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return content
     */

    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return receive
     */

    public String getReceive() {
        return receive;
    }

    /**
     * @param receive
     */
    public void setReceive(String receive) {
        this.receive = receive;
    }

    /**
     * @return host
     */

    public String getHost() {
        return host;
    }

    /**
     * @param host
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return sender
     */

    public String getSender() {
        return sender;
    }

    /**
     * @param sender
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * @return userName
     */

    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return password
     */

    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
