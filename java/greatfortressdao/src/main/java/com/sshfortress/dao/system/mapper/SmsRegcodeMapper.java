package com.sshfortress.dao.system.mapper;

import com.sshfortress.common.beans.SmsRegcode;


public interface SmsRegcodeMapper {
    int deleteByPrimaryKey(String phone);

    int insert(SmsRegcode record);

    int insertSelective(SmsRegcode record);

    SmsRegcode selectByPrimaryKey(String phone);

    int updateByPrimaryKeySelective(SmsRegcode record);

    int updateByPrimaryKey(SmsRegcode record);
}