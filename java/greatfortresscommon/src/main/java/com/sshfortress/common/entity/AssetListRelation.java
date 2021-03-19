package com.sshfortress.common.entity;
import java.util.Date;

/**
 * <p class="detail">
 * 功能：资产和资产分组的关系
 * </p>
 * @ClassName: AssetListRelation 
 * @version V1.0  
 */
public class AssetListRelation implements java.io.Serializable{

	private static final long serialVersionUID = -8335215339400294292L;

	/**  */
	private Long id;

	/** 资产组id */
	private Long assetGroup;

	/** 资产id */
	private Long assetId;

	public AssetListRelation (){
		super(); 
	}
	public AssetListRelation(Long id){
		this.id=id;
	}

	public Long getId(){
		return this.id;
	}
	public void setId(Long id){
		this.id=id;
	}

	public Long getAssetGroup(){
		return this.assetGroup;
	}
	public void setAssetGroup(Long assetGroup){
		this.assetGroup=assetGroup;
	}

	public Long getAssetId(){
		return this.assetId;
	}
	public void setAssetId(Long assetId){
		this.assetId=assetId;
	}
}
