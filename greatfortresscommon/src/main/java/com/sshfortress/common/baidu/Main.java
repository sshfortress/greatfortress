package com.sshfortress.common.baidu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class Main {

    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String BAIDU_APP_ID = "20170602000050276";
    private static final String BAIDU_SECURITY_KEY = "z3LyicBmAI4w_fgHpz7y";

    public static void main(String[] args) {
        TransApi api = new TransApi(BAIDU_APP_ID, BAIDU_SECURITY_KEY);
        String query = "陈欣欣九堡店";
        String result=api.getTransResult(query, "auto", "en");
        System.out.println(api.getTransResult(query, "auto", "en"));
        Gson gson=new Gson(); 
        Map<String, Object> retMap2 = gson.fromJson(result,  
                new TypeToken<Map<String, Object>>() {  
                }.getType());  
        for (String key : retMap2.keySet()) {  
        	if(key.equalsIgnoreCase("trans_result")){
        		List<Map> transResult=(List<Map>) retMap2.get(key);
        		System.out.println("输入参数"+transResult.get(0).get("src"));
        		System.out.println("输出目标"+transResult.get(0).get("dst"));
        	};
        }  
        
    }

}
