package com.sshfortress.common.util;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.maxmind.geoip.*;

/**
 * <p class="detail">
 * 功能：通过GeoIp获取IP的相关信息
 * 需要导入<dependency>
			<groupId>com.maxmind.minfraud</groupId>
			<artifactId>minfraud</artifactId>
			<version>1.7.0</version>
		</dependency>
		<dependency>
			<groupId>com.maxmind.geoip</groupId>
			<artifactId>geoip-api</artifactId>
			<version>1.2.10</version>
		</dependency>
 * </p>
 * @ClassName: GeoIPUtil 
 * @version V1.0  
 */
public class GeoIPUtil {
	
	public static Map<String, Object> getIPDetailByGeoIP (String IP){
		Map<String,Object> resultMap=new HashMap<String, Object>();
		try {
			String OS=System.getProperty("os.name").toUpperCase();
			String classPath="";
			if(OS.indexOf("WINDOWS")>=0){
				classPath="E:\\svn\\greatfortress\\greatfortressparent\\greatfortressframework\\src\\main\\webapp\\GeoIP\\GeoLiteCity.dat";
			}else{
				classPath="/usr/local/tomcat/webapps/greatfortressframework/GeoIP/GeoLiteCity.dat";
			}
			LookupService cl = new LookupService(classPath,LookupService.GEOIP_MEMORY_CACHE);
			Location l = cl.getLocation(IP);
			if(null==l){
				return resultMap;
			}
			String countryName=l.countryName;
			String city=l.city;
			float lat=l.latitude;
			float lon=l.longitude;
			resultMap.put("countryEnName", countryName);
			// 韩国共和国
			if(countryName.indexOf("Korea, Republic of")>=0){
				resultMap.put("countryEnName", "Korea");
			}
			//香港 澳门 台湾 改为中国
			if(countryName.indexOf("Hong Kong")>=0){
				resultMap.put("countryEnName", "China");
				resultMap.put("city", "Hong Kong");
			}
			if(countryName.indexOf("Macau")>=0){
				resultMap.put("countryEnName", "China");
				resultMap.put("city", "Macau");
			}
			if(countryName.indexOf("Taiwan")>=0){
				resultMap.put("countryEnName", "China");
				resultMap.put("city", "Taiwan");
			}
			//中国则多返回一个参数城市
			if(countryName.indexOf("China")>=0){
				resultMap.put("city", city);
			}
			resultMap.put("lat", lat);
			resultMap.put("lon", lon);
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return resultMap;
	}
	
	public static void main(String[] args) {
		String IP="119.28.11.50";
		String[] ipArr={"210.100.170.93","117.41.235.149"};
		for(int i=0;i<ipArr.length;i++){
			
			Map<String,Object> m=getIPDetailByGeoIP(ipArr[i]);
			System.out.println(m);
		}
	}
	
}
