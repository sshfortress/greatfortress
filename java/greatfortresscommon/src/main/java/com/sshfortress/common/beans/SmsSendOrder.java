package com.sshfortress.common.beans;

import java.io.Serializable;


/** <p class="detail">
 * 功能：这里写类描述
 * </p>
 * @ClassName: SmsSendOrder 
 * @version V1.0  
 */
public class SmsSendOrder implements Serializable {

    /** 
     * <p class="detail">
     * 功能：
     * </p>
     * @Fields serialVersionUID 
     */
    private static final long serialVersionUID = 285752858169472445L;
    /** 
     * <p class="detail">
     * 功能：接收短信者
     * </p>
     * @Fields receiverPhone 
     */
    String                    receiverPhone;
    /** 
     * <p class="detail">
     * 功能：地址
     * </p>
     * @Fields smsUrl 
     */
    private String            smsUrl;

    /** 
     * <p class="detail">
     * 功能：端口
     * </p>
     * @Fields smsPort 
     */
    private String            smsPort;

    /** 
     * <p class="detail">
     * 功能：账号
     * </p>
     * @Fields accountName 
     */
    private String            accountName;

    /** 
     * <p class="detail">
     * 功能：密码
     * </p>
     * @Fields accountPsd 
     */
    private String            accountPsd;

    /** 
     * <p class="detail">
     * 功能：对接应用id
     * </p>
     * @Fields appId 
     */
    private String            appId;

    /** 
     * <p class="detail">
     * 功能：对方接口的模板id
     * </p>
     * @Fields smsTempid 
     */
    private String            smsTempid;

    /** 
     * <p class="detail">
     * 功能：短信内容 按占位符排序
     * </p>
     * @Fields content 
     */
    private String            contents[];

    /**
     * @return receiverPhone
     */

    public String getReceiverPhone() {
        return receiverPhone;
    }

    /**
     * @param receiverPhone
     */
    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    /**
     * @return smsUrl
     */

    public String getSmsUrl() {
        return smsUrl;
    }

    /**
     * @param smsUrl
     */
    public void setSmsUrl(String smsUrl) {
        this.smsUrl = smsUrl;
    }

    /**
     * @return smsPort
     */

    public String getSmsPort() {
        return smsPort;
    }

    /**
     * @param smsPort
     */
    public void setSmsPort(String smsPort) {
        this.smsPort = smsPort;
    }

    /**
     * @return accountName
     */

    public String getAccountName() {
        return accountName;
    }

    /**
     * @param accountName
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * @return accountPsd
     */

    public String getAccountPsd() {
        return accountPsd;
    }

    /**
     * @param accountPsd
     */
    public void setAccountPsd(String accountPsd) {
        this.accountPsd = accountPsd;
    }

    /**
     * @return appId
     */

    public String getAppId() {
        return appId;
    }

    /**
     * @param appId
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * @return smsTempid
     */

    public String getSmsTempid() {
        return smsTempid;
    }

    /**
     * @param smsTempid
     */
    public void setSmsTempid(String smsTempid) {
        this.smsTempid = smsTempid;
    }

    /**
     * @return contents
     */

    public String[] getContents() {
        return contents;
    }

    /**
     * @param contents
     */
    public void setContents(String[] contents) {
        this.contents = contents;
    }

}
