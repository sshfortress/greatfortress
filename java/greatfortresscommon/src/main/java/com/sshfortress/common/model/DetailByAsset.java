package com.sshfortress.common.model;

import java.util.List;

/**
 * <p class="detail">
 * 功能：资产详情
 * </p>
 * @ClassName: DetailByAsset 
 * @version V1.0  
 */
public class DetailByAsset {
	
	private Long assetId;
	
	/** 主机名 */
	private String hostName;

	/** 目标ip */
	private String dstIp;

	/** 端口 */
	private Integer sshPort;

	/** 系统类型 10物理机 20虚拟机 30交换机 40路由器 50防火墙 60存储 */
	private String systemType;

	/** 资产环境 10-Production 20-Development 30-Testing */
	private Integer assetEnvironment;

	/** 机房id */
	private Long idcId;
	
	/** 备注 */
	private String remark;
	
	/**资产分组集合*/
	private List<ListByAssetGroup> assetGroupList;
	
	public Long getAssetId() {
		return assetId;
	}

	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getDstIp() {
		return dstIp;
	}

	public void setDstIp(String dstIp) {
		this.dstIp = dstIp;
	}

	public Integer getSshPort() {
		return sshPort;
	}

	public void setSshPort(Integer sshPort) {
		this.sshPort = sshPort;
	}

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	public Integer getAssetEnvironment() {
		return assetEnvironment;
	}

	public void setAssetEnvironment(Integer assetEnvironment) {
		this.assetEnvironment = assetEnvironment;
	}

	public Long getIdcId() {
		return idcId;
	}

	public void setIdcId(Long idcId) {
		this.idcId = idcId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public List<ListByAssetGroup> getAssetGroupList() {
		return assetGroupList;
	}

	public void setAssetGroupList(List<ListByAssetGroup> assetGroupList) {
		this.assetGroupList = assetGroupList;
	}

	
}
