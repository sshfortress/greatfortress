/**
 * 
 */
package com.sshfortress.common.beans;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


/** <p class="detail">
 * 功能：这里写类描述
 * </p>
 * @ClassName: LoginForm 
 * @version V1.0  
 */
public class LoginForm implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7203755690860194955L;


    @NotBlank(message = "账号为手机号码")
    @Length(min = 11, max = 11, message = "账号为手机号码!")
    private String            userName;

    @NotBlank(message = "passWord不能为空")
    @Length(min = 32, max = 32, message = "密码系统错误!")
    private String            passWord;
    @NotNull(message = "登录类型不能为空")
    private Integer          type;

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
        this.userName = StringUtils.trim(userName);
    }

    /**
     * @return the passWord
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * @param passWord
     *            the passWord to set
     */
    public void setPassWord(String passWord) {
        this.passWord = StringUtils.trim(passWord);
    }

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
