package com.sshfortress.dao.system.mapper;

import java.util.List;
import java.util.Map;

import com.sshfortress.common.beans.SysDictionary;


public interface SysDictionaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysDictionary record);

    int insertSelective(SysDictionary record);

    SysDictionary selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysDictionary record);

    int updateByPrimaryKey(SysDictionary record);
    
    
    List<Map<String,Object>> selectByAll();
    
}