package com.sshfortress.common.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Menu implements Serializable{
    /** 
	 * <p class="detail">
	 * 功能：这里写描述
	 * </p>
	 * @Fields serialVersionUID 
	 */ 
	private static final long serialVersionUID = 2228545421169228628L;

	private Long id;

    private String name;

    private String symbol;

    private String url;

    private String icon;

    private Integer parentId;

    private Integer menuOrder;

    private String isUsable;

    private String remark;

    private Date createTime;
    private  Integer isCheck;
    
    List<Menu> childMenus;
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
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getMenuOrder() {
		return menuOrder;
	}
	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}
	public String getIsUsable() {
		return isUsable;
	}
	public void setIsUsable(String isUsable) {
		this.isUsable = isUsable;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public List<Menu> getChildMenus() {
		return childMenus;
	}
	public void setChildMenus(List<Menu> childMenus) {
		this.childMenus = childMenus;
	}
	public Integer getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}
    
}
