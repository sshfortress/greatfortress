package com.sshfortress.common.entity;
import java.util.Date;
public class IpPlace implements java.io.Serializable{

	private static final long serialVersionUID = -2337767198077170268L;

	/**  */
	private Integer id;

	/** 国家中文名 */
	private String countryCn;

	/** 国家英文名 */
	private String countryEn;

	/** 省 */
	private String prov;

	/** 城市/首都 */
	private String city;

	/** 纬度 */
	private String lat;

	/** 经度 */
	private String lon;

	/** 国家编号 */
	private String countryId;

	public IpPlace (){
		super(); 
	}
	public IpPlace(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return this.id;
	}
	public void setId(Integer id){
		this.id=id;
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

	public String getProv(){
		return this.prov;
	}
	public void setProv(String prov){
		this.prov=prov;
	}

	public String getCity(){
		return this.city;
	}
	public void setCity(String city){
		this.city=city;
	}

	public String getLat(){
		return this.lat;
	}
	public void setLat(String lat){
		this.lat=lat;
	}

	public String getLon(){
		return this.lon;
	}
	public void setLon(String lon){
		this.lon=lon;
	}

	public String getCountryId(){
		return this.countryId;
	}
	public void setCountryId(String countryId){
		this.countryId=countryId;
	}
}
