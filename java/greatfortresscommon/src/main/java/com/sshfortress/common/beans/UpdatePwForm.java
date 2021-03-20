package com.sshfortress.common.beans;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * <p class="detail">
 * 功能：修改用户的
 * </p>
 * 
 * @ClassName: UpdateForm
 * @version V1.0
 */
public class UpdatePwForm {
	@NotBlank(message = "老密码不能为空")
	@Length(min = 32, max = 32, message = "密码系统错误!")
	private String passWord;
	@NotBlank(message = "新密码不能为空")
	@Length(min = 32, max = 32, message = "密码系统错误!")
	private String newPassWord;

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getNewPassWord() {
		return newPassWord;
	}

	public void setNewPassWord(String newPassWord) {
		this.newPassWord = newPassWord;
	}

}
