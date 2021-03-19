package com.sshfortress.common.beans;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;


/**
 * 系统菜单
 *
 */

public class SysMenuForm implements Serializable{

	/** 
	 * <p class="detail">
	 * 功能：这里写描述
	 * </p>
	 * @Fields serialVersionUID 
	 */ 
	private static final long serialVersionUID = 3782196360260089721L;
	
	private Long id;

	@NotBlank(message = "菜单名称不能为空")
    private String name;
	@NotBlank(message = "菜单标识")
    private String symbol;
    private String url;

    private String icon;
    @NotNull(message="父菜单ID不能为空！")
    private Integer parentId;

    private Integer menuOrder;

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


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "SysMenuForm [id:" + id + ", name:" + name + ", symbol:" + symbol + ", url:" + url + ", icon:" + icon
				+ ", parentId:" + parentId + ", menuOrder:" + menuOrder + ", remark:" + remark + "]";
	}
	
   public static void main(String[] args) {
	   SysMenuForm form = new SysMenuForm();
	   System.out.println(form.toString());
    }
	

}
