package com.sshfortress.dao.system.mapper;

import java.util.List;
import java.util.Map;

import com.sshfortress.common.beans.SimpleAreaDo;
import com.sshfortress.common.beans.SysArea;
import com.sshfortress.common.model.SysAreaModel;

public interface SysAreaMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysArea record);

    int insertSelective(SysArea record);

    SysArea selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysArea record);

    int updateByPrimaryKey(SysArea record);
    
    SimpleAreaDo selectSimplAreaByPrimaryKey(Long id);
    
    SimpleAreaDo selectParantSimpleArea(Map<String ,Object> params);
    
    List<SimpleAreaDo>  selectChildren(Map<String ,Object> params);
    
    List<SysArea> selectListByParantId(Map<String ,Object> params);
    List<SysAreaModel> selectAllArea();
    /**获取城市区域*/
    List<Map<String,Object>> selectAreaByCity(Map<String,Object> m);
}