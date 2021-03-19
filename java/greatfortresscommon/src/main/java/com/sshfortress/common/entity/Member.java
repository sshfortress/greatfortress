package com.sshfortress.common.entity;
import java.util.Date;

import com.sshfortress.common.beans.SysUserInfo;
public class Member extends SysUserInfo implements java.io.Serializable{

	private static final long serialVersionUID = 3088408055538002786L;

	/** 用户id */
	private String uid;

	/** 用户名 */
	private String username;

	/** 登录密码 */
	private String password;

	/** 目标用户 */
	private String dstname;

	/** 目标密码 */
	private String dstpassword;

	/** 目标IP */
	private String dstip;

	/** 手机号 */
	private String mobile;

	/** 记录时间(该字段暂时不用) */
	private Integer logintimes;

	/** 最后一次登录时间 */
	private Date lastdate;

	/** 开始时间 */
	private Date startTime;

	/** 结束时间 */
	private Date endTime;

	/** 星期几 */
	private String weektime;

	/** 资源IP */
	private String sourceip;

	/** 公司 */
	private String company;

	/** 手机号 */
	private String mobilenum;

	/** 是否启用 */
	private Integer enable;

	/** SSH端口号 */
	private Integer sshport;

	/** 真实姓名 */
	private String name;

	/** 邮箱 */
	private String email;

	/** 备注 */
	private String remark;

	/** 是否删除 0正常 1删除 */
	private Integer isDelete;

	public Member (){
		super(); 
	}
	public Member(String uid){
		this.uid=uid;
	}

	public String getUid(){
		return this.uid;
	}
	public void setUid(String uid){
		this.uid=uid;
	}

	public String getUsername(){
		return this.username;
	}
	public void setUsername(String username){
		this.username=username;
	}

	public String getPassword(){
		return this.password;
	}
	public void setPassword(String password){
		this.password=password;
	}

	public String getDstname(){
		return this.dstname;
	}
	public void setDstname(String dstname){
		this.dstname=dstname;
	}

	public String getDstpassword(){
		return this.dstpassword;
	}
	public void setDstpassword(String dstpassword){
		this.dstpassword=dstpassword;
	}

	public String getDstip(){
		return this.dstip;
	}
	public void setDstip(String dstip){
		this.dstip=dstip;
	}

	public String getMobile(){
		return this.mobile;
	}
	public void setMobile(String mobile){
		this.mobile=mobile;
	}

	public Integer getLogintimes(){
		return this.logintimes;
	}
	public void setLogintimes(Integer logintimes){
		this.logintimes=logintimes;
	}

	public Date getLastdate(){
		return this.lastdate;
	}
	public void setLastdate(Date lastdate){
		this.lastdate=lastdate;
	}

	public Date getStartTime(){
		return this.startTime;
	}
	public void setStartTime(Date startTime){
		this.startTime=startTime;
	}

	public Date getEndTime(){
		return this.endTime;
	}
	public void setEndTime(Date endTime){
		this.endTime=endTime;
	}

	public String getWeektime(){
		return this.weektime;
	}
	public void setWeektime(String weektime){
		this.weektime=weektime;
	}

	public String getSourceip(){
		return this.sourceip;
	}
	public void setSourceip(String sourceip){
		this.sourceip=sourceip;
	}

	public String getCompany(){
		return this.company;
	}
	public void setCompany(String company){
		this.company=company;
	}

	public String getMobilenum(){
		return this.mobilenum;
	}
	public void setMobilenum(String mobilenum){
		this.mobilenum=mobilenum;
	}

	public Integer getEnable(){
		return this.enable;
	}
	public void setEnable(Integer enable){
		this.enable=enable;
	}

	public Integer getSshport(){
		return this.sshport;
	}
	public void setSshport(Integer sshport){
		this.sshport=sshport;
	}

	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}

	public String getEmail(){
		return this.email;
	}
	public void setEmail(String email){
		this.email=email;
	}

	public String getRemark(){
		return this.remark;
	}
	public void setRemark(String remark){
		this.remark=remark;
	}

	public Integer getIsDelete(){
		return this.isDelete;
	}
	public void setIsDelete(Integer isDelete){
		this.isDelete=isDelete;
	}
}
