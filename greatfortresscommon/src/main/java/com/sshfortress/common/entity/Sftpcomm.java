package com.sshfortress.common.entity;
import java.util.Date;
public class Sftpcomm implements java.io.Serializable{

	private static final long serialVersionUID = 5931925718871209005L;

	/**  */
	private Long id;

	/** 用户id */
	private String userId;

	/** 用户组id */
	private Long userGroup;

	/**  */
	private String comm;

	/** 创建时间 */
	private Date at;

	/** 资源名称 */
	private String filename;

	/**  */
	private Integer successed;

	/**  */
	private Integer backupflag;

	/**  */
	private Integer backupsize;

	/**  */
	private Integer backup;

	public Sftpcomm (){
		super(); 
	}
	public Sftpcomm(Long id){
		this.id=id;
	}

	public Long getId(){
		return this.id;
	}
	public void setId(Long id){
		this.id=id;
	}

	public String getUserId(){
		return this.userId;
	}
	public void setUserId(String userId){
		this.userId=userId;
	}

	public Long getUserGroup(){
		return this.userGroup;
	}
	public void setUserGroup(Long userGroup){
		this.userGroup=userGroup;
	}

	public String getComm(){
		return this.comm;
	}
	public void setComm(String comm){
		this.comm=comm;
	}

	public Date getAt(){
		return this.at;
	}
	public void setAt(Date at){
		this.at=at;
	}

	public String getFilename(){
		return this.filename;
	}
	public void setFilename(String filename){
		this.filename=filename;
	}

	public Integer getSuccessed(){
		return this.successed;
	}
	public void setSuccessed(Integer successed){
		this.successed=successed;
	}

	public Integer getBackupflag(){
		return this.backupflag;
	}
	public void setBackupflag(Integer backupflag){
		this.backupflag=backupflag;
	}

	public Integer getBackupsize(){
		return this.backupsize;
	}
	public void setBackupsize(Integer backupsize){
		this.backupsize=backupsize;
	}

	public Integer getBackup(){
		return this.backup;
	}
	public void setBackup(Integer backup){
		this.backup=backup;
	}
}
