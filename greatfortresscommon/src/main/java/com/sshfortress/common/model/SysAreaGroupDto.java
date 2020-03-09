package com.sshfortress.common.model;

import java.io.Serializable;
import java.util.List;

public class SysAreaGroupDto implements Serializable{
		
	/** 
	 * <p class="detail">
	 * 功能：这里写描述
	 * </p>
	 * @Fields serialVersionUID 
	 */ 
	private static final long serialVersionUID = -8496593673447002316L;

	
	private List<GroupDto> gd;


	public List<GroupDto> getGd() {
		return gd;
	}


	public void setGd(List<GroupDto> gd) {
		this.gd = gd;
	}
	
}
