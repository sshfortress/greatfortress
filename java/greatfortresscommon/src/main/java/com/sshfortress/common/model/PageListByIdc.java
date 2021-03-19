package com.sshfortress.common.model;
import java.util.Date;

/**
 * <p class="detail">
 * 功能：机房分页列表
 * </p>
 * @ClassName: PageListByIdc 
 * @version V1.0  
 */
public class PageListByIdc implements java.io.Serializable{

	private static final long serialVersionUID = -546248101335632243L;

	/** 机房id */
	private Long idcId;

	/** 机房名称 */
	private String idcName;

	/** 联系人 */
	private String contact;

	/** 手机号 */
	private String phone;

	/** 运营商 */
	private String operator;
	
	/**资产数量*/
	private Integer assetNumber;

	public PageListByIdc (){
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

	public Integer getAssetNumber() {
		return assetNumber;
	}

	public void setAssetNumber(Integer assetNumber) {
		this.assetNumber = assetNumber;
	}


}
