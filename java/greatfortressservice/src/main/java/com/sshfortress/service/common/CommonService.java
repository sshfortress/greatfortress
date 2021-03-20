package com.sshfortress.service.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshfortress.dao.common.mapper.CommonMapper;

@Service
public class CommonService {
	
	@Autowired
	CommonMapper commonMapper;
	
	/**用户下拉框*/
	public List<Map<String,Object>> getUserList(Map<String,Object> m){
		
		return commonMapper.getUserList(m);
	}
	
	/**资产下拉框*/
	public List<Map<String,Object>> getAssetPageList(Map<String,Object> m){
		
		return commonMapper.getAssetPageList(m);
	}
	
	/**资产分组下拉框*/
	public List<Map<String,Object>> getAssetGroupList(Map<String,Object> m){
		
		return commonMapper.getAssetGroupList(m);
	}
	
	/**机房下拉框*/
	public List<Map<String,Object>> getIdcList(Map<String,Object> m){
		
		return commonMapper.getIdcList(m);
	} 
	
	public List<Map<String,Object>> getSSHLoginNameByWeek(){
		
		return commonMapper.getSSHLoginNameByWeek();
	} 
	
	public Map<String,Object> getSSHLoginByMonth(){
		Map<String,Object> m=new HashMap<String, Object>();
		m.put("x", commonMapper.getSSHLoginNameByMonth());
		m.put("y", commonMapper.getSSHLoginNumberByMonth());
		return m;
	} 
	
}
