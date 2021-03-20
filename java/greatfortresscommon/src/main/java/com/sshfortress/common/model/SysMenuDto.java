package com.sshfortress.common.model;

import com.sshfortress.common.beans.SysMenu;

public class SysMenuDto extends SysMenu {
    /** 
	 * <p class="detail">
	 * 功能：这里写描述
	 * </p>
	 * @Fields serialVersionUID 
	 */ 
	private static final long serialVersionUID = 5761683225134788830L;
	private  Integer isCheck;
	public Integer getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}
    
    
}
