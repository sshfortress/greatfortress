package com.sshfortress.common.model;

/***
 * <p class="detail">
 * 功能：获取国家相关信息
 * </p>
 * @ClassName: DetailByCountry 
 * @version V1.0  
 */
public class DetailByCountry {
	
	/** 国家中文名 */
	private String countryCn;

	/** 国家英文名 */
	private String countryEn;

	/** 城市/首都 */
	private String city;

	/** 纬度 */
	private String lat;

	/** 经度 */
	private String lon;

	public String getCountryCn() {
		return countryCn;
	}

	public void setCountryCn(String countryCn) {
		this.countryCn = countryCn;
	}

	public String getCountryEn() {
		return countryEn;
	}

	public void setCountryEn(String countryEn) {
		this.countryEn = countryEn;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}
	
	
}
