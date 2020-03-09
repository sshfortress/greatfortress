package com.sshfortress.common.entity;
import java.util.Date;

/**
 * <p class="detail">
 * 功能：资产
 * </p>
 * @ClassName: AssetList 
 * @version V1.0  
 */
public class AssetList implements java.io.Serializable{

	private static final long serialVersionUID = -2540573644049389365L;

	/**  */
	private Long id;

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
	
	/** 创建时间 */
	private Date createTime;

	/** 更新时间 */
	private Date updateTime;

	/** 是否删除 0正常 1删除 */
	private Integer isDelete;

	/** 创建者 */
	private String creator;
	
	/**父级id*/
	private Long parentId;
	
	/**分类类型(1级 2级 3级)*/
	private Integer type;
	

	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public AssetList (){
		super(); 
	}
	public AssetList(Long id){
		this.id=id;
	}

	public Long getId(){
		return this.id;
	}
	public void setId(Long id){
		this.id=id;
	}

	public String getHostName(){
		return this.hostName;
	}
	public void setHostName(String hostName){
		this.hostName=hostName;
	}

	public String getDstIp(){
		return this.dstIp;
	}
	public void setDstIp(String dstIp){
		this.dstIp=dstIp;
	}

	public Integer getSshPort(){
		return this.sshPort;
	}
	public void setSshPort(Integer sshPort){
		this.sshPort=sshPort;
	}

	public String getSystemType(){
		return this.systemType;
	}
	public void setSystemType(String systemType){
		this.systemType=systemType;
	}

	public Integer getAssetEnvironment(){
		return this.assetEnvironment;
	}
	public void setAssetEnvironment(Integer assetEnvironment){
		this.assetEnvironment=assetEnvironment;
	}

	public Long getIdcId(){
		return this.idcId;
	}
	public void setIdcId(Long idcId){
		this.idcId=idcId;
	}

	public String getRemark(){
		return this.remark;
	}
	public void setRemark(String remark){
		this.remark=remark;
	}

	public Date getCreateTime(){
		return this.createTime;
	}
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public Date getUpdateTime(){
		return this.updateTime;
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}

	public Integer getIsDelete(){
		return this.isDelete;
	}
	public void setIsDelete(Integer isDelete){
		this.isDelete=isDelete;
	}

	public String getCreator(){
		return this.creator;
	}
	public void setCreator(String creator){
		this.creator=creator;
	}
	
	
}
