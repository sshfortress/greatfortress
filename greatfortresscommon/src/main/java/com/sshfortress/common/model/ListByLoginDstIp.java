package com.sshfortress.common.model;

/**
 * <p class="detail">
 * 功能：当前登录的dstIp相关信息
 * </p>
 * @ClassName: ListByLoginDstIp 
 * @version V1.0  
 */
public class ListByLoginDstIp implements java.io.Serializable{


	private static final long serialVersionUID = 7966867369356920944L;

	/** 国家中文名 */
	private String countryCn;

	/** 国家英文名 */
	private String countryEn;

	/** 市名 */
	private String city;

	/** 经度 */
	private String lon;

	/** 纬度 */
	private String lat;

	public ListByLoginDstIp (){
		super(); 
	}

	public String getCountryCn(){
		return this.countryCn;
	}
	public void setCountryCn(String countryCn){
		this.countryCn=countryCn;
	}

	public String getCountryEn(){
		return this.countryEn;
	}
	public void setCountryEn(String countryEn){
		this.countryEn=countryEn;
	}

	public String getCity(){
		return this.city;
	}
	public void setCity(String city){
		this.city=city;
	}

	public String getLon(){
		return this.lon;
	}
	public void setLon(String lon){
		this.lon=lon;
	}

	public String getLat(){
		return this.lat;
	}
	public void setLat(String lat){
		this.lat=lat;
	}




}
