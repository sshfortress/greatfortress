package com.sshfortress.dao.system.mapper;

import java.util.List;
import java.util.Map;

import com.sshfortress.common.beans.SysMenu;
import com.sshfortress.common.model.SysMenuDto;


public interface SysMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

	List<SysMenu> queryByParamsListPager(Map<String, Object> map);
	
	List<SysMenuDto> selectByRoleIds(Long roleIds[]);
}