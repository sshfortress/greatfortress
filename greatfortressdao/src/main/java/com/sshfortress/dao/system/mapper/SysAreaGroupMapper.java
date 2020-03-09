package com.sshfortress.dao.system.mapper;

import java.util.List;
import java.util.Map;

import com.sshfortress.common.beans.SysAreaGroup;
import com.sshfortress.common.model.GroupDto;
import com.sshfortress.common.model.SysAreaGroupDto;


public interface SysAreaGroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysAreaGroup record);

    int insertSelective(SysAreaGroup record);

    SysAreaGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysAreaGroup record);

    int updateByPrimaryKey(SysAreaGroup record);
    
    /*获取分组区域的信息**/
    GroupDto  selectAreaGroup();
    
    /*获取分组区域的信息**/
    List<Map<String,Object>>  getAreaGroup();
}