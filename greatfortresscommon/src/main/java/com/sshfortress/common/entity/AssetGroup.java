package com.sshfortress.common.entity;
import java.util.Date;

/**
 * <p class="detail">
 * 功能：资产分组
 * </p>
 * @ClassName: AssetGroup 
 * @version V1.0  
 */
public class AssetGroup implements java.io.Serializable{

	private static final long serialVersionUID = -7583954822278171930L;

	/**  */
	private Long id;

	/** 资产组名称 */
	private String groupName;

	/** 备注 */
	private String remark;

	/** 创建时间 */
	private Date createTime;

	/** 更新时间 */
	private Date updateTime;

	/** 是否删除 0正常 1删除 */
	private Integer isDelete;

	/** 创建者 */
	private String creator;

	public AssetGroup (){
		super(); 
	}
	public AssetGroup(Long id){
		this.id=id;
	}

	public Long getId(){
		return this.id;
	}
	public void setId(Long id){
		this.id=id;
	}

	public String getGroupName(){
		return this.groupName;
	}
	public void setGroupName(String groupName){
		this.groupName=groupName;
	}

	public String getRemark(){
		return this.remark;
	}
	public void setRemark(String remark){
		this.remark=remark;
	}

	public Date getCreateTime(){
		return this.createTime;
	}
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public Date getUpdateTime(){
		return this.updateTime;
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}

	public Integer getIsDelete(){
		return this.isDelete;
	}
	public void setIsDelete(Integer isDelete){
		this.isDelete=isDelete;
	}

	public String getCreator(){
		return this.creator;
	}
	public void setCreator(String creator){
		this.creator=creator;
	}
}
