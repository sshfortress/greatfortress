package com.sshfortress.common.model;

/**
 * <p class="detail">
 * 功能：某个资产属于哪些资产分组
 * </p>
 * @ClassName: ListByAssetGroup 
 * @version V1.0  
 */
public class ListByAssetGroup {
	
	/**资产分组id*/
	private Long assetGroupId;
	
	/**资产分组名称*/
	private String assetGroupName;

	public Long getAssetGroupId() {
		return assetGroupId;
	}

	public void setAssetGroupId(Long assetGroupId) {
		this.assetGroupId = assetGroupId;
	}

	public String getAssetGroupName() {
		return assetGroupName;
	}

	public void setAssetGroupName(String assetGroupName) {
		this.assetGroupName = assetGroupName;
	}
	
	
}
