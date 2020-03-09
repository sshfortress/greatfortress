package com.sshfortress.common.model;

import java.util.List;

/**
 * <p class="detail">
 * 功能：资产分组详情
 * </p>
 * @ClassName: DetailByAssetGroup 
 * @version V1.0  
 */
public class DetailByAssetGroup {
	
	/**  */
	private Long assetGroupId;

	/** 资产组名称 */
	private String groupName;

	/** 备注 */
	private String remark;
	
	List<ListByAsset> assetList;

	public Long getAssetGroupId() {
		return assetGroupId;
	}

	public void setAssetGroupId(Long assetGroupId) {
		this.assetGroupId = assetGroupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<ListByAsset> getAssetList() {
		return assetList;
	}

	public void setAssetList(List<ListByAsset> assetList) {
		this.assetList = assetList;
	}
	
	
	
}
