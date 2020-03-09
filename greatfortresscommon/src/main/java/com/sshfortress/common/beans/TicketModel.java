package com.sshfortress.common.beans;

import java.io.Serializable;

public class TicketModel extends UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 
	 * <p class="detail">
	 * 功能：加密的用户信息
	 * </p>
	 * @Fields ticket 
	 */ 
	private String ticket;
	/** 
	 * <p class="detail">
	 * 功能：用户积�?
	 * </p>
	 * @Fields score 
	 */ 
	private Integer score;
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "TicketModel [ticket=" + ticket + ", score=" + score + ", getUserId()=" + getUserId()
				+ ", getUserName()=" + getUserName() + ", getPassWord()=" + getPassWord() + ", getStatus()="
				+ getStatus() + ", getUserType()=" + getUserType()  +  ", getLastLoginTime()=" + getLastLoginTime() + ", getCreateTime()="
				+ getCreateTime() + ", getUpdateTime()=" + getUpdateTime() + "]";
	}


}
