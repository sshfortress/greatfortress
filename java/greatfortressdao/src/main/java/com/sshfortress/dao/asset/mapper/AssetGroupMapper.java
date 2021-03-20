package com.sshfortress.dao.asset.mapper;

import java.util.List;
import java.util.Map;

import com.sshfortress.common.entity.AssetGroup;
import com.sshfortress.common.model.DetailByAssetGroup;
import com.sshfortress.common.model.ListByAssetGroup;
import com.sshfortress.common.model.PageListByAssetGroup;

public interface AssetGroupMapper {
    int deleteByPrimaryKey(Long id);
    
    /**增加*/
    int addAssetGroup(AssetGroup record);

    int insertSelective(AssetGroup record);

    AssetGroup selectByPrimaryKey(Long id);
    
    /**更新*/
    int updateAssetGroup(AssetGroup record);

    int updateByPrimaryKey(AssetGroup record);
    
    /**名是否存在*/
    List<String> isExistName(Map<String,Object> m);
    
    /**分组详情*/
    DetailByAssetGroup getAssetGroupDetail(Map<String,Object> m);
    
    /**资产分组分页列表*/
    List<PageListByAssetGroup> getAssetGroupPageList(Map<String,Object> m);
    
    /**资产组下拉框*/
    List<ListByAssetGroup> assetGroupList();
}