package com.sshfortress.dao.dstip.mapper;

import com.sshfortress.common.entity.DstipLoginHistory;

public interface DstipLoginHistoryMapper {
    int deleteByPrimaryKey(Long id);

    int addDstIpLoginHistory(DstipLoginHistory record);

    int insertSelective(DstipLoginHistory record);

    DstipLoginHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DstipLoginHistory record);

    int updateByPrimaryKeyWithBLOBs(DstipLoginHistory record);

    int updateByPrimaryKey(DstipLoginHistory record);
}