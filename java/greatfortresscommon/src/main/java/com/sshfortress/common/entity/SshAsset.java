package com.sshfortress.common.entity;
import java.util.Date;
public class SshAsset implements java.io.Serializable{

	/**  */
	private Long id;

	/** ssh_list表的主键 ssh用户id */
	private String sshId;

	/** asset_group表的主键 资产组id */
	private String assetGroup;
	
	/** asset_list表的主键 资产id */
	private String assetId;
	
	/**asset_dst表的主键 目标用户id*/
	private String assetDstId;

	/**是否授权 10已授权 20未授权*/
	private Integer isEnable;

	public SshAsset (){
		super(); 
	}
	public SshAsset(Long id){
		this.id=id;
	}

	public Long getId(){
		return this.id;
	}
	public void setId(Long id){
		this.id=id;
	}

	public String getSshId(){
		return this.sshId;
	}
	public void setSshId(String sshId){
		this.sshId=sshId;
	}

	public String getAssetId(){
		return this.assetId;
	}
	public void setAssetId(String assetId){
		this.assetId=assetId;
	}

	public String getAssetGroup(){
		return this.assetGroup;
	}
	public void setAssetGroup(String assetGroup){
		this.assetGroup=assetGroup;
	}
	public Integer getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}
	public String getAssetDstId() {
		return assetDstId;
	}
	public void setAssetDstId(String assetDstId) {
		this.assetDstId = assetDstId;
	}


}
