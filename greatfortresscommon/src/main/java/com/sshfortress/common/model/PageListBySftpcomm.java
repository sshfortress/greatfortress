package com.sshfortress.common.model;
import java.util.Date;
public class PageListBySftpcomm implements java.io.Serializable{

	private static final long serialVersionUID = 5931925718871209005L;

	/**  */
	private String cmd;

	/** 创建时间 */
	private String createTime;

	private String userName;


	public PageListBySftpcomm (){
		super(); 
	}


	public String getCmd(){
		return this.cmd;
	}
	public void setCmd(String cmd){
		this.cmd=cmd;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}
