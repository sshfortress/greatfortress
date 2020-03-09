package com.sshfortress.service.init;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.sshfortress.common.beans.SelectDto;
import com.sshfortress.common.util.StringUtil;
import com.sshfortress.dao.system.mapper.SysDictionaryMapper;


public class SysDictionary{

	/**
	 * 系统配置静态缓存map
	 */
	public static Map<String,Map<String, String>> SYS_DICTIONARY_MAP = new HashMap<String,Map<String, String>>(20);
	
	@Autowired
	private SysDictionaryMapper sysDictionaryMapper;
	/**
	 * 系统启动时初始化加载词典
	 */
	public void init() {
		StringBuilder sb = new StringBuilder("SELECT id,dflag,dkey,dvalue FROM sys_dictionary order by dflag asc,dkey+0 asc ");
		List<Map<String,Object>> dictionary = sysDictionaryMapper.selectByAll();
		for(int i=0; dictionary!=null && i<dictionary.size(); ){
			Map<String,Object> dic=dictionary.get(i);
			Map<String, String> mapString = new HashMap<String,String>();
			mapString.put(dic.get("dkey").toString(), String.valueOf(dic.get("dvalue")));
			do{
				++i;
				if(i>=dictionary.size()){
					break;
				}
				Map<String,Object> dic2=dictionary.get(i);
				if(dic.get("dflag").equals(dic2.get("dflag"))){
					mapString.put(dic2.get("dkey").toString(), String.valueOf(dic2.get("dvalue")));
				}else{
					break;
				}
			}while(i<dictionary.size());
			SYS_DICTIONARY_MAP.put(dic.get("dflag").toString(),mapString);
		}
		System.out.print("sys_dictionary缓存加载完成，共加载配置项："+dictionary.size());
	}
	
	
	/**
	 * 获取词典接口
	 * @param 
	 * @return String
	 */
	public static Map<String, String> getDvalueByMap(String dflag){
		return SYS_DICTIONARY_MAP.get(dflag);
	}
	
	/**
	 * 获取词典接口
	 * @param 
	 * @return String
	 */
	public static List getDvalueList(String dflag){
		Map<String, String> map=SYS_DICTIONARY_MAP.get(dflag);
		List a=new ArrayList();
		int index=0;
		for (String key : map.keySet()) {  
			SelectDto sd=new SelectDto();
		    String value = map.get(key);  
		    sd.setKey(key);
		    sd.setValue(value);
		    a.add(sd);
//		    System.out.println("Key = " + key + ", Value = " + value);  
		    index=index+1;
		}  
		
		return a;
	}
	
	/**
	 * <p class="detail">
	 * 功能：获取某组字典 某代码的含义
	 * </p>
	 * @param dflag
	 * @return
	 */
	public static String getValueByKey(String dflag,String dkey){
		String value="";
		if(StringUtil.isNullOrEmpty(dflag) || StringUtil.isNullOrEmpty(dkey)){
		return value;
		}else{
		Map<String,Map<String, String>> k=SYS_DICTIONARY_MAP;
		Map<String, String> m=k.get(dflag);
		value=m.get(dkey);
		return value;
		}
		
	}
	
	
}
