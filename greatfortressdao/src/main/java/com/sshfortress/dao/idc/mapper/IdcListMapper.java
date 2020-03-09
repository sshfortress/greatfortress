package com.sshfortress.dao.idc.mapper;

import java.util.List;
import java.util.Map;

import com.sshfortress.common.entity.IdcList;
import com.sshfortress.common.model.DetailByIdc;
import com.sshfortress.common.model.PageListByIdc;

public interface IdcListMapper {
    int deleteByPrimaryKey(Long id);
    
    /**增加*/
    int addIdc(IdcList record);

    int insertSelective(IdcList record);

    IdcList selectByPrimaryKey(Long id);
    
    /**更新*/
    int updateIdc(IdcList record);

    int updateByPrimaryKey(IdcList record);
    
    /**机房分页列表*/
    List<PageListByIdc> getIdcPageList(Map<String,Object> m);
    
    /**机房详情*/
    DetailByIdc getIdcDetail(Map<String,Object> m);
    
    /**名是否存在*/
    List<String> isExistName(Map<String,Object> m);
}