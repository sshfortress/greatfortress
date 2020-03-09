package com.sshfortress.common.entity;
import java.util.Date;
public class DstipLoginHistory implements java.io.Serializable{

	private static final long serialVersionUID = -7644213082986532566L;

	/**  */
	private Long id;

	/** 目标IP */
	private String dstIp;

	/** 国家中文名 */
	private String countryCn;

	/** 国家英文名 */
	private String countryEn;

	/** 市名 */
	private String city;

	/** 经度 */
	private String lon;

	/** 纬度 */
	private String lat;

	/** 登录状态 10已登录 20登出 */
	private Integer loginStatus;

	/** 用户id */
	private String userId;

	/** 用户组id */
	private Long userGroup;

	/** 创建时间 */
	private Date createTime;

	/** 备注 */
	private String remark;

	public DstipLoginHistory (){
		super(); 
	}
	public DstipLoginHistory(Long id){
		this.id=id;
	}

	public Long getId(){
		return this.id;
	}
	public void setId(Long id){
		this.id=id;
	}

	public String getDstIp(){
		return this.dstIp;
	}
	public void setDstIp(String dstIp){
		this.dstIp=dstIp;
	}

	public String getCountryCn(){
		return this.countryCn;
	}
	public void setCountryCn(String countryCn){
		this.countryCn=countryCn;
	}

	public String getCountryEn(){
		return this.countryEn;
	}
	public void setCountryEn(String countryEn){
		this.countryEn=countryEn;
	}

	public String getCity(){
		return this.city;
	}
	public void setCity(String city){
		this.city=city;
	}

	public String getLon(){
		return this.lon;
	}
	public void setLon(String lon){
		this.lon=lon;
	}

	public String getLat(){
		return this.lat;
	}
	public void setLat(String lat){
		this.lat=lat;
	}

	public Integer getLoginStatus(){
		return this.loginStatus;
	}
	public void setLoginStatus(Integer loginStatus){
		this.loginStatus=loginStatus;
	}

	public String getUserId(){
		return this.userId;
	}
	public void setUserId(String userId){
		this.userId=userId;
	}

	public Long getUserGroup(){
		return this.userGroup;
	}
	public void setUserGroup(Long userGroup){
		this.userGroup=userGroup;
	}

	public Date getCreateTime(){
		return this.createTime;
	}
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public String getRemark(){
		return this.remark;
	}
	public void setRemark(String remark){
		this.remark=remark;
	}
}
