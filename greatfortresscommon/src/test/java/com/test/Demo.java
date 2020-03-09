package com.test;

import java.util.HashMap;
import java.util.Map;

public class Demo {
	public static void main(String[] args) {
		Map<String,Object> m=new HashMap<String, Object>();
		m.put("a", 1);
		m.put("b", 2);
		if(m.get("c")!=null){
			System.out.println("有c");
		}else{
			System.out.println("无c");
		}
	}
}
