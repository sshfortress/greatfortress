package com.sshfortress.common.beans;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


/** <p class="detail">
 * 功能：APP 注册类情况
 * </p>
 * @ClassName: AppRegistForm 
 * @version V1.0  
 */
public class AppRegistForm implements Serializable {

    private static final long serialVersionUID = 6971205044653379280L;



    @NotBlank(message = "账号为手机号码")
    @Length(min = 11, max = 11, message = "账号为手机号码!")
    private String            userName;

    @NotBlank(message = "passWord不能为空")
    @Length(min = 32, max = 32, message = "密码系统错误!")
    private String            passWord;

    @NotBlank(message = "验证码不能为空")
    @Length(min = 6, max = 6, message = "验证码错误!")
    private String            regCode;
    private Integer type;
    
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
        this.userName = StringUtils.trim(userName);
    }


    /**
     * @return passWord
     */

    public String getPassWord() {
        return passWord;
    }

    /**
     * @param passWord
     */
    public void setPassWord(String passWord) {
        this.passWord = StringUtils.trim(passWord);
    }

    /**
     * @return regCode
     */

    public String getRegCode() {
        return regCode;
    }

    /**
     * @param regCode
     */
    public void setRegCode(String regCode) {
        this.regCode = StringUtils.trim(regCode);
    }

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
