package com.sshfortress.common.beans;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;


/** <p class="detail">
 * 功能：修改用户的
 * </p>
 * @ClassName: UpdateForm 
 * @version V1.0  
 */
public class UpdateForm implements Serializable{
	
    /** 
	 * <p class="detail">
	 * 功能：这里写描述
	 * </p>
	 * @Fields serialVersionUID 
	 */ 
	private static final long serialVersionUID = 4482802660373332558L;
	
    private String            nickName;
    /** 
     * <p class="detail">
     * 功能：性别：0-保密 1-男  2-女
     * </p>
     * @Fields sex 
     */
    @Range(min = 0, max = 2, message = "请选择性别")
    private Integer           sex;
    
    private String city;
    
    private String email;
    
    private String facePhoto;
    
    private String identityNumber;
    
    private String name;
    
    private String otherPhoto;
    
    private String qq;
    
    private String sign;
    
    private String phone;
    
    private String headUrl;
    
    private Long userId;
    
    private String firstSpell;
    
    private String authType;
 
    private String lastLoginTime;
    
    private String createTime;
    
    private String updateTime;
    
    private String loginIp;
    
    private String lastLoginIp;
    
    private String province;
    
    private String hximId;
    
    private String level;
    
    private Double credit;
    
    private Double coin;
    
    private Long identity;
    
    private String type;
    
    private String policeNumber;
    

	public String getPoliceNumber() {
		return policeNumber;
	}
	public void setPoliceNumber(String policeNumber) {
		this.policeNumber = policeNumber;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getHeadUrl() {
		return headUrl;
	}
	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFacePhoto() {
		return facePhoto;
	}
	public void setFacePhoto(String facePhoto) {
		this.facePhoto = facePhoto;
	}
	public String getIdentityNumber() {
		return identityNumber;
	}
	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOtherPhoto() {
		return otherPhoto;
	}
	public void setOtherPhoto(String otherPhoto) {
		this.otherPhoto = otherPhoto;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getFirstSpell() {
		return firstSpell;
	}
	public void setFirstSpell(String firstSpell) {
		this.firstSpell = firstSpell;
	}
	public String getAuthType() {
		return authType;
	}
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getHximId() {
		return hximId;
	}
	public void setHximId(String hximId) {
		this.hximId = hximId;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Double getCredit() {
		return credit;
	}
	public void setCredit(Double credit) {
		this.credit = credit;
	}
	public Double getCoin() {
		return coin;
	}
	public void setCoin(Double coin) {
		this.coin = coin;
	}
	public Long getIdentity() {
		return identity;
	}
	public void setIdentity(Long identity) {
		this.identity = identity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    
}
