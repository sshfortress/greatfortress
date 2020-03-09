package com.sshfortress.service.init;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.sshfortress.dao.system.mapper.SysConfigMapper;


public class SysConfig {
	Logger log=Logger.getLogger(SysConfig.class);	
			
	public static Map<String,String> SYS_CONFIG_MAP = new HashMap<String,String>();
	
	@Autowired
	private SysConfigMapper sysConfigMapper;
	/**
	 * 系统启动时初始化加载配置
	 */
	public void init() {
		StringBuilder sbu = new StringBuilder("SELECT id,ckey,cvalue,group_code,config_name FROM sys_config ");
		List<Map<String,Object>> config = sysConfigMapper.selectByAll();
		for(int i=0; config!=null && !config.isEmpty() && i<config.size(); i++){
			SYS_CONFIG_MAP.put(config.get(i).get("ckey").toString(), String.valueOf(config.get(i).get("cvalue")));
		}
		System.out.print("sys_config缓存加载完成，共加载配置项："+config.size());
		log.info("sys_config缓存加载完成，共加载配置项："+config.size());
	}
	
	
	/**
	 * 
	 * 描述：获取配置接口
	 * @param 
	 * @return String
	 */
	public static String getValue(String key){
		return SYS_CONFIG_MAP.get(key);
	}
	
}
