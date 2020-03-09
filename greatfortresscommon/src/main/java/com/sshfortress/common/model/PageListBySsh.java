package com.sshfortress.common.model;
import java.util.Date;

/**
 * <p class="detail">
 * 功能：SSH帐号信息
 * </p>
 * @ClassName: SshList 
 * @version V1.0  
 */
public class PageListBySsh implements java.io.Serializable{

	private static final long serialVersionUID = -7086092475909761449L;

	/**  */
	private Long sshId;

	/** ssh登录名 */
	private String name;

	/** 资产组名 */
	private String assetGroupName;
	
	public PageListBySsh (){
		super(); 
	}
	

	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}


	public Long getSshId() {
		return sshId;
	}


	public void setSshId(Long sshId) {
		this.sshId = sshId;
	}


	public String getAssetGroupName() {
		return assetGroupName;
	}


	public void setAssetGroupName(String assetGroupName) {
		this.assetGroupName = assetGroupName;
	}


	
	
}
