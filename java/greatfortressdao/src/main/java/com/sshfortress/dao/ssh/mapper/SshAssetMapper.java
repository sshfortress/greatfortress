package com.sshfortress.dao.ssh.mapper;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sshfortress.common.entity.SshAsset;

public interface SshAssetMapper {
    int deleteByPrimaryKey(Long id);

    int bindingSshAssetByBatch(ArrayList<SshAsset> array);

    int insertSelective(SshAsset record);

    SshAsset selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SshAsset record);

    int updateByPrimaryKey(SshAsset record);
    
    int unbindingSshAsset(@Param("sshId")String sshId);
    
    int isEnableBySshIdBatch(Map<String,Object> m);
    
    String isExistSshAssetByEnable(@Param("sshId")String sshId);
}