package com.sshfortress.common.beans;


import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


/** <p class="detail">
 * 功能：这里写类描述
 * 验证码
 * </p>
 * @ClassName: RegCodeForm 
 * @version V1.0  
 */
public class RegCodeForm implements Serializable {

    private static final long serialVersionUID = 7214481054683487081L;
    @NotBlank(message = "账号为手机号码")
    @Length(min = 11, max = 11, message = "账号为手机号码!")
    private String            userName;

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

}
