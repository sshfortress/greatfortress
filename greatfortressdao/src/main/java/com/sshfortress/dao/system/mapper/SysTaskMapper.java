package com.sshfortress.dao.system.mapper;

import com.sshfortress.common.beans.SysTask;



public interface SysTaskMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysTask record);

    int insertSelective(SysTask record);

    SysTask selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysTask record);

    int updateByPrimaryKey(SysTask record);
}