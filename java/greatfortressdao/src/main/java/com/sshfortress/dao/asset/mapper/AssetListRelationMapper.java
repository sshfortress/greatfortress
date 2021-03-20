package com.sshfortress.dao.asset.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.sshfortress.common.entity.AssetListRelation;

public interface AssetListRelationMapper {
	
	/**删除资产时解绑关系*/
    int unbundlingByDelAsset(@Param("assetId")Long assetId);
    
    /**删除资产分组时解绑关系*/
    int unbundlingByDelAssetGroup(@Param("assetGroupId")Long assetGroupId);
    
    /**批量增加资产和组的关系*/
    int addAssetGroupRelationByBatch(ArrayList<AssetListRelation> array);

    int insertSelective(AssetListRelation record);

    AssetListRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AssetListRelation record);

    int updateByPrimaryKey(AssetListRelation record);
    
    /**通过资产查找资产组id*/
    Long getAssetGroupIdByAssetId(@Param("assetId")Long assetId);
    
    /**通过资产组查找资产id*/
    Long getAssetIdByAssetGroupId(@Param("assetGroupId")Long assetGroupId);
}