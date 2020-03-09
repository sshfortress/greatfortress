package com.sshfortress.common.model;
import java.util.Date;

/**
 * <p class="detail">
 * 功能：资产分页列表
 * </p>
 * @ClassName: AssetList 
 * @version V1.0  
 */
public class PageListByAsset implements java.io.Serializable{

	private static final long serialVersionUID = -2540573644049389365L;

	/**  */
	private Long assetId;

	/** 主机名 */
	private String hostName;

	/** 目标ip */
	private String dstIp;

	/** 资产环境 10-Production 20-Development 30-Testing */
	private String assetEnvironment;
	
	/**系统类型*/
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

	public String getAssetEnvironment() {
		return assetEnvironment;
	}

	public void setAssetEnvironment(String assetEnvironment) {
		this.assetEnvironment = assetEnvironment;
	}

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}


	

}
