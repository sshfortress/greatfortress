package com.sshfortress.dao.system.mapper;

import com.sshfortress.common.beans.SysConfigGroup;


public interface SysConfigGroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysConfigGroup record);

    int insertSelective(SysConfigGroup record);

    SysConfigGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysConfigGroup record);

    int updateByPrimaryKey(SysConfigGroup record);
}