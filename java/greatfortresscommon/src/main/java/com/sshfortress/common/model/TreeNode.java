package com.sshfortress.common.model;

import java.io.Serializable;

/**
 * 树形菜单
 *
 */
public class TreeNode implements Serializable{

	private static final long serialVersionUID = -6907768597537702786L;
	
	private Long id;

	private String name;
	
	private String flag;

    private String icon;

    private String url;

    private Integer nlevel;

    private Integer leftId;

    private Integer rightId;
    
    private String remark;
    
    private String firstSpell;

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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getNlevel() {
		return nlevel;
	}

	public void setNlevel(Integer nlevel) {
		this.nlevel = nlevel;
	}

	public Integer getLeftId() {
		return leftId;
	}

	public void setLeftId(Integer leftId) {
		this.leftId = leftId;
	}

	public Integer getRightId() {
		return rightId;
	}

	public void setRightId(Integer rightId) {
		this.rightId = rightId;
	}

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public String getFirstSpell() {
		return firstSpell;
	}

	public void setFirstSpell(String firstSpell) {
		this.firstSpell = firstSpell;
	}
    
    
    
}
