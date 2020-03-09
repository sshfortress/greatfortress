package com.sshfortress.common.model;

/**
 * <p class="detail">
 * 功能：资产集合
 * </p>
 * @ClassName: ListByAsset 
 * @version V1.0  
 */
public class ListByAsset {
	
	/**  */
	private Long assetId;

	/** 主机名 */
	private String hostName;

	/** 目标ip */
	private String dstIp;
	
	/**系统类型 10物理机 20虚拟机 30交换机 40路由器 50防火墙 60存储*/
	private String systemType;

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

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}
	
	
	
}
