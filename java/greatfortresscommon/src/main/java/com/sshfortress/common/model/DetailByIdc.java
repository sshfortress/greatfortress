package com.sshfortress.common.model;
import java.util.Date;

/**
 * <p class="detail">
 * 功能：机房详情
 * </p>
 * @ClassName: DetailByIdc 
 * @version V1.0  
 */
public class DetailByIdc implements java.io.Serializable{

	private static final long serialVersionUID = -546248101335632243L;

	/**  */
	private Long idcId;

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

	public DetailByIdc (){
		super(); 
	}

	public Long getIdcId() {
		return idcId;
	}

	public void setIdcId(Long idcId) {
		this.idcId = idcId;
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

}
