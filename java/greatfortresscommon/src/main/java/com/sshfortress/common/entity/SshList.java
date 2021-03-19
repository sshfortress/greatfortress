package com.sshfortress.common.entity;
import java.util.Date;

/**
 * <p class="detail">
 * 功能：SSH帐号信息
 * </p>
 * @ClassName: SshList 
 * @version V1.0  
 */
public class SshList implements java.io.Serializable{

	private static final long serialVersionUID = -7086092475909761449L;

	/**  */
	private Long id;

	/** ssh登录名 */
	private String name;

	/** ssh登录密码 */
	private String password;

	/** 备注 */
	private String remark;

	/** 是否删除 0正常 1删除 */
	private Integer isDelete;

	/** 创建时间 */
	private Date createTime;

	/** 更新时间 */
	private Date updateTime;

	/** 创建者 */
	private String creator;

	public SshList (){
		super(); 
	}
	public SshList(Long id){
		this.id=id;
	}

	public Long getId(){
		return this.id;
	}
	public void setId(Long id){
		this.id=id;
	}

	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}

	public String getPassword(){
		return this.password;
	}
	public void setPassword(String password){
		this.password=password;
	}

	public String getRemark(){
		return this.remark;
	}
	public void setRemark(String remark){
		this.remark=remark;
	}

	public Integer getIsDelete(){
		return this.isDelete;
	}
	public void setIsDelete(Integer isDelete){
		this.isDelete=isDelete;
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

	public String getCreator(){
		return this.creator;
	}
	public void setCreator(String creator){
		this.creator=creator;
	}
}
