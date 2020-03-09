package com.sshfortress.common.model;

import java.util.List;

public class GroupDto {
		
    private Long id;
	
	private String groupName;
    
	private List<SysAreaDto> sad;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public List<SysAreaDto> getSad() {
		return sad;
	}

	public void setSad(List<SysAreaDto> sad) {
		this.sad = sad;
	}
	
}
