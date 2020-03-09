package com.sshfortress.dao.system.mapper;

import com.sshfortress.common.beans.SysOrganization;


public interface SysOrganizationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysOrganization record);

    int insertSelective(SysOrganization record);

    SysOrganization selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysOrganization record);

    int updateByPrimaryKey(SysOrganization record);
    
    SysOrganization selectSysOrganization(String userId);
}