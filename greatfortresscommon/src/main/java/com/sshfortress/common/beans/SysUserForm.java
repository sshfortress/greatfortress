package com.sshfortress.common.beans;

import org.hibernate.validator.constraints.NotBlank;

public class SysUserForm  {
	public String  userId;
	@NotBlank(message = "用户名称不能为空")
	public String userName;
	@NotBlank(message = "密码不能为空")
	public String passWord;
	public Long roleId[];
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public Long[] getRoleId() {
		return roleId;
	}
	public void setRoleId(Long[] roleId) {
		this.roleId = roleId;
	}
	
}
