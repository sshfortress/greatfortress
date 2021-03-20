/**
 * 
 */
package com.sshfortress.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 仅限序列化为ticket的对象 不外置
 *
 */
public class SessionUser implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6936090533747015059L;

    private String            userId;

    /** 
     * <p class="detail">
     * 功能：账号
     * </p>
     * @Fields userName 
     */ 
    private String            userName;


    /** 
     * <p class="detail">
     * 功能：这里写描述
     * </p>
     * @Fields userType "(0, "系统管理员"),(1, "供货商"), (2, "会员")"
     */ 
    private int               userType;

    private Date              loginTime;
    
    private String  loginIp ;
    

    /**
     * @return userId
     */

    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     *            the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }


    /**
     * @return userType
     */

    public int getUserType() {
        return userType;
    }

    /**
     * @param userType
     */
    public void setUserType(int userType) {
        this.userType = userType;
    }

    /**
     * @return the loginTime
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * @param loginTime
     *            the loginTime to set
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
    

}
