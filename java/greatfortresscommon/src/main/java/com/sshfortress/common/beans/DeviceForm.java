package com.sshfortress.common.beans;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;


/**
 * 个推的From
 *
 */

public class DeviceForm implements Serializable{

	/** 
	 * <p class="detail">
	 * 功能：这里写描述
	 * </p>
	 * @Fields serialVersionUID 
	 */ 
	private static final long serialVersionUID = 1L;
	
    @NotBlank(message = "cid不能为空！")
	private String cid;
	
    @NotNull(message = "设备类型不能为空！")
	private Integer deviceType;
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public Integer getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}
	

}
