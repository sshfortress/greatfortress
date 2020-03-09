package com.sshfortress.common.model;

public class User {

	private String phone;
	
	private String name;
	
	private Integer userId;
	
	private Integer id;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String toString() {
		return "User [phone=" + phone + ", name=" + name + ", userId=" + userId
				+ "]";
	}
	
	
}
