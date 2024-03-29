package com.sshfortress.common.util;

import java.net.URL;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetLocationByLatAndLog {

	public static String getAdd(String log, String lat ){  
		//lat 小  log  大  
		//参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项)  
		String urlString = "http://gc.ditu.aliyun.com/regeocoding?l="+lat+","+log+"&type=010";  
		String res = "";     
		try {     
			URL url = new URL(urlString);    
			java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();    
			conn.setDoOutput(true);    
			conn.setRequestMethod("POST");    
			java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"UTF-8"));    
			String line;    
			while ((line = in.readLine()) != null) {    
				res += line+"\n";    
			}    
			in.close();    
		} catch (Exception e) {    
			System.out.println("error in wapaction,and e is " + e.getMessage());    
		}   
		System.out.println(res);  
		return res;    
	}  
	
	public static void main(String[] args) {
		// lat 30.26 纬度       
        //log 120.19 经度   
        String add = getAdd("120.20", "30.27");  
        JSONObject jsonObject = JSONObject.fromObject(add);  
        JSONArray jsonArray = JSONArray.fromObject(jsonObject.getString("addrList"));  
        JSONObject j_2 = JSONObject.fromObject(jsonArray.get(0));  
        String allAdd = j_2.getString("admName");  
        String arr[] = allAdd.split(",");  
        System.out.println("省："+arr[0]+"\n市："+arr[1]+"\n区："+arr[2]);  
	}
}
