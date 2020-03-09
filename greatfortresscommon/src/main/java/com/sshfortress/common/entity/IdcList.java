package com.sshfortress.common.entity;
import java.util.Date;
public class IdcList implements java.io.Serializable{

	private static final long serialVersionUID = -546248101335632243L;

	/**  */
	private Long id;

	/** 机房名称 */
	private String idcName;

	/** 地址 */
	private String address;

	/** 联系人 */
	private String contact;

	/** 手机号 */
	private String phone;

	/** 运营商 */
	private String operator;

	/** 内网IP */
	private String innerNet;

	/** 外网IP */
	private String extranet;

	/** 创建时间 */
	private Date createTime;

	/** 更新时间 */
	private Date updateTime;

	/** 是否删除 0正常 1删除 */
	private Integer isDelete;

	/** 创建者 */
	private String creator;

	public IdcList (){
		super(); 
	}
	public IdcList(Long id){
		this.id=id;
	}

	public Long getId(){
		return this.id;
	}
	public void setId(Long id){
		this.id=id;
	}

	public String getIdcName(){
		return this.idcName;
	}
	public void setIdcName(String idcName){
		this.idcName=idcName;
	}

	public String getAddress(){
		return this.address;
	}
	public void setAddress(String address){
		this.address=address;
	}

	public String getContact(){
		return this.contact;
	}
	public void setContact(String contact){
		this.contact=contact;
	}

	public String getPhone(){
		return this.phone;
	}
	public void setPhone(String phone){
		this.phone=phone;
	}

	public String getOperator(){
		return this.operator;
	}
	public void setOperator(String operator){
		this.operator=operator;
	}

	public String getInnerNet(){
		return this.innerNet;
	}
	public void setInnerNet(String innerNet){
		this.innerNet=innerNet;
	}

	public String getExtranet(){
		return this.extranet;
	}
	public void setExtranet(String extranet){
		this.extranet=extranet;
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
