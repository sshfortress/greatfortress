package com.sshfortress.common.entity;
import java.util.Date;
public class LoginfailedDay implements java.io.Serializable{

	private static final long serialVersionUID = -1633362233849037411L;

	/**  */
	private Integer sid;

	/**  */
	private String sourceip;

	/** ssh2 or ssh1 */
	private String portocol;

	/**  */
	private String audituser;

	/** 1 登陆成功 0登陆失败 */
	private Integer loginstate;

	/**  */
	private Date time;

	public LoginfailedDay (){
		super(); 
	}
	public LoginfailedDay(Integer sid){
		this.sid=sid;
	}

	public Integer getSid(){
		return this.sid;
	}
	public void setSid(Integer sid){
		this.sid=sid;
	}

	public String getSourceip(){
		return this.sourceip;
	}
	public void setSourceip(String sourceip){
		this.sourceip=sourceip;
	}

	public String getPortocol(){
		return this.portocol;
	}
	public void setPortocol(String portocol){
		this.portocol=portocol;
	}

	public String getAudituser(){
		return this.audituser;
	}
	public void setAudituser(String audituser){
		this.audituser=audituser;
	}

	public Integer getLoginstate(){
		return this.loginstate;
	}
	public void setLoginstate(Integer loginstate){
		this.loginstate=loginstate;
	}

	public Date getTime(){
		return this.time;
	}
	public void setTime(Date time){
		this.time=time;
	}
}
