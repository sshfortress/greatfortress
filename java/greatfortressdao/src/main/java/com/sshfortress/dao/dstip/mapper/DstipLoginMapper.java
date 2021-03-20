package com.sshfortress.dao.dstip.mapper;

import java.util.List;
import java.util.Map;

import com.sshfortress.common.entity.DstipLogin;
import com.sshfortress.common.model.ListByLoginDstIp;

public interface DstipLoginMapper {
    int deleteByPrimaryKey(Long id);
    
    /**增加*/
    int addDstIpLogin(DstipLogin record);

    int insertSelective(DstipLogin record);

    DstipLogin selectByPrimaryKey(Long id);
    
    /**更新*/
    int updateDstIpLogin(DstipLogin record);

    int updateByPrimaryKeyWithBLOBs(DstipLogin record);

    int updateByPrimaryKey(DstipLogin record);
    
    /**是否存在*/
    List<Long> isExistDstIpLogin(Map<String,Object> m);
    
    /**查询某段时间的登录用户集合(默认一天内)*/
    List<ListByLoginDstIp> getLoginDstIpListByNow(Map<String,Object> m);
    
    /**查询某段时间内某个国家/城市登录的数量(默认一天内)*/
    Integer getLoginDstIpNumberBycountry(Map<String,Object> m);
    
    /**主机数*/
    Integer getHostNumber();
    
    /**当天正常登录数量*/
    Integer getNowLoginIpNumber();
    
    /**当天最新登录的3条IP*/
    List<Map<String,Object>> getNewLoginIPList();
}