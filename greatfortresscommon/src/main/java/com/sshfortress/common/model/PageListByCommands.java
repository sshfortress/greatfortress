package com.sshfortress.common.model;
import java.util.Date;

/**
 * <p class="detail">
 * 功能：命令历史表
 * </p>
 * @ClassName: Commands 
 * @version V1.0  
 */
public class PageListByCommands implements java.io.Serializable{

	private static final long serialVersionUID = -7960325260382842050L;

	/** 创建时间 */
	private String createTime;

	/** 命令 */
	private String cmd;
	
	private String userName;

	public PageListByCommands (){
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
