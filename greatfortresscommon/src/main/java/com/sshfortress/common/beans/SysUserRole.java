package com.sshfortress.common.beans;

import java.io.Serializable;

/**
 * 角色与用户的关系
 *
 */
public class SysUserRole implements Serializable {
    /** 
	 * <p class="detail">
	 * 功能：这里写描述
	 * </p>
	 * @Fields serialVersionUID 
	 */ 
	private static final long serialVersionUID = 2344349268954206139L;

	private String userId;

    private Long roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}