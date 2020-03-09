package com.sshfortress.dao.system.mapper;

import java.util.List;
import java.util.Map;

import com.sshfortress.common.beans.SysConfig;
import com.sshfortress.common.beans.SysConfigKeyValue;


public interface SysConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysConfig record);

    int insertSelective(SysConfig record);

    SysConfig selectByPrimaryKey(Long id);
    
    /** 更新配置的值  */
    int updateCvalueByCkey(SysConfig config);
    
    /** 查询配置的值  */
    String getCvalueByCkey(String ckey);
    
    int updateByPrimaryKey(SysConfig record);
    String selectGroupCodeByCkey(Map<String,Object> params);
    List<SysConfigKeyValue> selectKeyValueByGroupCode(Map<String,Object> params);
    /** 获取配置的全部数据 */
    List<Map<String,Object>> selectByAll();
 }