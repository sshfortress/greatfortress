package com.sshfortress.common.model;

import java.io.Serializable;

import com.sshfortress.common.model.TreeNode;

public class SysAreaModel  extends TreeNode implements Serializable {
    private static final long serialVersionUID = -5568124247192609337L;
    
    private Long id;
    
    private String aname;

    private String code;
    
    private String firstSpell;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

	public String getFirstSpell() {
		return firstSpell;
	}

	public void setFirstSpell(String firstSpell) {
		this.firstSpell = firstSpell;
	}

}