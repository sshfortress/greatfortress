package com.sshfortress.dao.asset.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sshfortress.common.entity.AssetDst;
import com.sshfortress.common.model.DstListByAssetId;
import com.sshfortress.common.model.ListByDst;

public interface AssetDstMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AssetDst record);

    int insertSelective(AssetDst record);

    AssetDst selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AssetDst record);

    int updateByPrimaryKeyWithBLOBs(AssetDst record);

    int updateByPrimaryKey(AssetDst record);
    
    /**删除某个目标*/
    int delDstByDstId(@Param("dstId")Long dstId);
    
    /**先删除再增加*/
    int delAssetDstByAssetId(@Param("assetId")Long assetId);
    
    /**批量增加资产的目标用户名和目标密码*/
    int addAssetDstNamePsdByBatch(ArrayList<AssetDst> arrayList);
    
    /**查询某个资产下的所有目标用户名和密码*/
    List<DstListByAssetId> getDstNamePsdListByAssetId(@Param("assetId")Long assetId);
    
    /**目标用户下拉框*/
    List<ListByDst> dstListByAssetGroupId(@Param("assetGroupId")Long assetGroupId);
    
    /**查询某个资产组下除了某个资产及某个用户名以外的用户名集合 Map: assetId assetGroupId dstName*/
    Long getDstListByExceptAssetIdDstName(Map<String,Object> m);
    
}