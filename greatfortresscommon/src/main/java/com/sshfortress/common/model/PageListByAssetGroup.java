package com.sshfortress.common.model;
import java.util.Date;

/**
 * <p class="detail">
 * 功能：资产分组分页列表
 * </p>
 * @ClassName: PageListByAssetGroup 
 * @version V1.0  
 */
public class PageListByAssetGroup implements java.io.Serializable{

	private static final long serialVersionUID = -2540573644049389365L;

	/** 资产组id */
	private Long assetGroupId;

	/** 资产组名称 */
	private String groupName;

	/** 备注 */
	private String remark;
	
	/**分组下的资产数量*/
	private Integer assetNumber;

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

	public Integer getAssetNumber() {
		return assetNumber;
	}

	public void setAssetNumber(Integer assetNumber) {
		this.assetNumber = assetNumber;
	}
	
	
	
	

}
