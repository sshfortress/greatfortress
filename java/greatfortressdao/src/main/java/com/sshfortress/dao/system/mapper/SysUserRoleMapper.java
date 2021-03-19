package com.sshfortress.dao.system.mapper;

import java.util.List;

import com.sshfortress.common.beans.SysUserRole;

public interface SysUserRoleMapper {
    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);
    
    int insertBatchs(List<SysUserRole> list);
    
    int delByUserId(String userId);
    
}