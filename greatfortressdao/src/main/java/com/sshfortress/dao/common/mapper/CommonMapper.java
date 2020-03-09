package com.sshfortress.dao.common.mapper;

import java.util.List;
import java.util.Map;

import com.sshfortress.common.model.DetailByCountry;
import com.sshfortress.common.model.User;
import com.sshfortress.common.websocket.Message;


/**
 * <p class="detail">
 * 功能：公共方法
 * </p>
 * @ClassName: CommonMapper 
 * @version V1.0  
 */
public interface CommonMapper {
	
	int addTest(User user);
	
	int addMsg(Message message);
    
	/**用户下拉框*/
	List<Map<String,Object>> getUserList(Map<String,Object> m);
	
	/**资产下拉框*/
	List<Map<String,Object>> getAssetPageList(Map<String,Object> m);
	
	/**资产分组下拉框*/
	List<Map<String,Object>> getAssetGroupList(Map<String,Object> m);
	
	/**机房下拉框*/
	List<Map<String,Object>> getIdcList(Map<String,Object> m);
	
	/**国家信息*/
	DetailByCountry getCountryDetailByIp(Map<String,Object> m);
	
	/**查询当前登录IP的相关信息*/
	Map<String,Object> getLoginIpDetail(Map<String,Object> m);
	
	/**最近一个月SSH用户登录数量统计图*/
	List<String> getSSHLoginNameByMonth();
	
	List<Integer> getSSHLoginNumberByMonth();
	
	/**最近一周SSH用户登录数量统计图*/
	List<Map<String,Object>> getSSHLoginNameByWeek();
}