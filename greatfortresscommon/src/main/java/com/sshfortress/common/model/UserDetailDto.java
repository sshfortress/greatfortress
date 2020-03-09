package com.sshfortress.common.model;

import java.util.List;
import java.util.Map;

import com.sshfortress.common.beans.MemberInfo;

/**
 * <p class="detail">
 * 功能：用户详情信息
 * </p>
 * @ClassName: UserDetailDto 
 * @version V1.0  
 */
public class UserDetailDto {


	private String userId;
	
	private String passWord;

	private String userName;

	private String email;

	private String name;

	private String remark;

	private String phone;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


}
