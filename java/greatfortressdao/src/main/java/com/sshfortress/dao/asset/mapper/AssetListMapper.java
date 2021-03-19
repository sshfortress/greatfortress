package com.sshfortress.dao.asset.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sshfortress.common.entity.AssetList;
import com.sshfortress.common.model.DetailByAsset;
import com.sshfortress.common.model.PageListByAsset;

public interface AssetListMapper {
    int deleteByPrimaryKey(Long id);
    
    /**增加*/
    int addAsset(AssetList record);

    int insertSelective(AssetList record);

    AssetList selectByPrimaryKey(Long id);
    
    /**更新*/
    int updateAsset(AssetList record);

    int updateByPrimaryKey(AssetList record);
    
    /**资产分页列表*/
    List<PageListByAsset> getAssetPageList(Map<String,Object> m);
    
    /**资产名是否存在*/
    List<String> isExistName(Map<String,Object> m);
    
    /**资产详情*/
    DetailByAsset getAssetDetail(Map<String,Object> m);
    
    /**授权*/
    int isEnableByAssetId(Map<String,Object> m);
    
    /**资产下拉框*/
    List<Map<String,Object>> assetListByAssetGroupId(@Param("assetGroupId")Long assetGroupId);
}