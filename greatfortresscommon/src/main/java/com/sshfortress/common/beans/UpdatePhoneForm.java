package com.sshfortress.common.beans;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


/** <p class="detail">
 * 功能：这里写类描述
 * </p>
 * @ClassName: UpdatePhoneForm 
 * @version V1.0  
 */
public class UpdatePhoneForm {
  //@Pattern(regexp="^1[3|4|5|7|8]\\d{9}$",message="请输入正确的手机号")
  //private String phone;
    @NotBlank(message = "验证码不能为空")
    @Length(min = 6, max = 6, message = "验证码错误!")
	private String regCode;
    @Pattern(regexp="^1[3|4|5|7|8]\\d{9}$",message="请输入正确的手机号")
	private String newPhone;

//	public String getPhone() {
//		return phone;
//	}
//
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}

	public String getRegCode() {
		return regCode;
	}

	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}

	public String getNewPhone() {
		return newPhone;
	}

	public void setNewPhone(String newPhone) {
		this.newPhone = newPhone;
	}

}
