package com.sshfortress.common.entity;
import java.util.Date;

/**
 * <p class="detail">
 * 功能：命令历史表
 * </p>
 * @ClassName: Commands 
 * @version V1.0  
 */
public class Commands implements java.io.Serializable{

	private static final long serialVersionUID = -7960325260382842050L;

	/**  */
	private Long id;

	/** 用户id */
	private String userId;

	/** 用户分组id */
	private Long userGroup;

	/** 创建时间 */
	private Date at;

	/** 命令 */
	private String cmd;

	/** 危险等级 */
	private Integer dangerlevel;

	/** 跳板机session */
	private Integer jumpSession;

	public Commands (){
		super(); 
	}
	public Commands(Long id){
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

	public Date getAt(){
		return this.at;
	}
	public void setAt(Date at){
		this.at=at;
	}

	public String getCmd(){
		return this.cmd;
	}
	public void setCmd(String cmd){
		this.cmd=cmd;
	}

	public Integer getDangerlevel(){
		return this.dangerlevel;
	}
	public void setDangerlevel(Integer dangerlevel){
		this.dangerlevel=dangerlevel;
	}

	public Integer getJumpSession(){
		return this.jumpSession;
	}
	public void setJumpSession(Integer jumpSession){
		this.jumpSession=jumpSession;
	}
}
