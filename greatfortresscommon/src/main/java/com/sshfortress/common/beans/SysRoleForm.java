package com.sshfortress.common.beans;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 系统角色
 *
 */

public class SysRoleForm {
	private Long id;
	
	@NotBlank(message = "角色名称不能为空")
    private String name;
	@NotBlank(message = "角色标识不能为空")
    private String flag;

    private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
}
