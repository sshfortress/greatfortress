package com.sshfortress.common.model;
import java.util.Date;

/**
 * <p class="detail">
 * 功能：SSH帐号信息
 * </p>
 * @ClassName: SshList 
 * @version V1.0  
 */
public class DetailBySsh implements java.io.Serializable{

	private static final long serialVersionUID = -7086092475909761449L;

	/** ssh登录名 */
	private String name;

	/** 备注 */
	private String remark;

	/** 资产组id */
	private String assetGroupId;
	
	private String assetGroupName;
	
	private String assetId;

	private String assetName;

	private String dstId;

	private String dstName;
	
	/**是否授权 */
	private String isEnable;
	

	public DetailBySsh (){
		super(); 
	}

	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}

	public String getRemark(){
		return this.remark;
	}
	public void setRemark(String remark){
		this.remark=remark;
	}

	public String getAssetGroupId() {
		return assetGroupId;
	}

	public void setAssetGroupId(String assetGroupId) {
		this.assetGroupId = assetGroupId;
	}

	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

	public String getAssetGroupName() {
		return assetGroupName;
	}

	public void setAssetGroupName(String assetGroupName) {
		this.assetGroupName = assetGroupName;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getDstName() {
		return dstName;
	}

	public void setDstName(String dstName) {
		this.dstName = dstName;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getDstId() {
		return dstId;
	}

	public void setDstId(String dstId) {
		this.dstId = dstId;
	}
	

}
