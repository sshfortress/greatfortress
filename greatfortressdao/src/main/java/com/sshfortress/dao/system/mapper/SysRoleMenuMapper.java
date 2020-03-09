package com.sshfortress.dao.system.mapper;

import java.util.List;

import com.sshfortress.common.beans.SysRoleMenu;



public interface SysRoleMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRoleMenu record);

    int insertSelective(SysRoleMenu record);

    SysRoleMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRoleMenu record);

    int updateByPrimaryKey(SysRoleMenu record);
    
    int insertBatch(List<SysRoleMenu> list);
    
    int deleteByRoleId(Long roleId);
}