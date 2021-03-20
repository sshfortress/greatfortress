package com.sshfortress.dao.system.mapper;

import com.sshfortress.common.beans.EmailRegcode;


public interface EmailRegcodeMapper {
    int deleteByPrimaryKey(String email);

    int insert(EmailRegcode record);

    int insertSelective(EmailRegcode record);

    EmailRegcode selectByPrimaryKey(String email);

    int updateByPrimaryKeySelective(EmailRegcode record);

    int updateByPrimaryKey(EmailRegcode record);
}