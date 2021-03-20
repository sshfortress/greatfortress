package com.sshfortress.service.ssh;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sshfortress.common.entity.SshAsset;
import com.sshfortress.common.entity.SshList;
import com.sshfortress.common.model.DetailBySsh;
import com.sshfortress.common.model.ListByAssetGroup;
import com.sshfortress.common.model.ListByDst;
import com.sshfortress.common.model.PageListBySsh;
import com.sshfortress.common.util.OperationContextHolder;
import com.sshfortress.dao.asset.mapper.AssetDstMapper;
import com.sshfortress.dao.asset.mapper.AssetGroupMapper;
import com.sshfortress.dao.asset.mapper.AssetListMapper;
import com.sshfortress.dao.ssh.mapper.SshAssetMapper;
import com.sshfortress.dao.ssh.mapper.SshListMapper;

@Service
public class SshService {

	@Autowired
	SshListMapper sshListMapper;

	@Autowired
	SshAssetMapper sshAssetMapper;
	
	@Autowired
	AssetDstMapper assetDstMapper;
	
	@Autowired
	AssetGroupMapper assetGroupMapper;
	
	@Autowired
	AssetListMapper assetListMapper;

	/**批量授权*/
	@Transactional
	public int isEnableBySshIdBatch(String[] sshId){
		for(int i=0;i<sshId.length;i++){
			Map<String,Object> m=new HashMap<String, Object>();
			m.put("sshId", sshId[i]);
			int count=sshAssetMapper.isEnableBySshIdBatch(m);
			if(count<=0){
				return 0;
			}
		}
		return 1;
	}

	/**SSH分页列表*/
	public List<PageListBySsh> getSshPageList(Map<String,Object> m){

		return sshListMapper.getSshPageList(m);
	}

	/**SSH详情*/
	public DetailBySsh getDetailBySsh(Map<String,Object> m){

		return sshListMapper.getDetailBySsh(m);
	}

	/**增加*/
	@Transactional
	public int addSSH(SshList sshList,String[] assetGroupId,Integer[] isEnable,String[] assetDstId,String[] assetId){
		String userId=OperationContextHolder.getUserId();
		sshList.setCreateTime(new Date());
		sshList.setUpdateTime(new Date());
		sshList.setCreator(userId);
		sshList.setIsDelete(0);
		int count=sshListMapper.addSSH(sshList);
		
		
		//SSH用户和资产组的关系
		if(null!=assetGroupId && assetGroupId.length>0){
			ArrayList<SshAsset> array=new ArrayList<SshAsset>();
			for(int i=0;i<assetGroupId.length;i++){
				SshAsset sshAsset=new SshAsset();
				sshAsset.setAssetGroup(assetGroupId[i]);
				sshAsset.setSshId(sshList.getId().toString());
				sshAsset.setIsEnable(isEnable[i]);
				sshAsset.setAssetDstId(assetDstId[i]);
				sshAsset.setAssetId(assetId[i]);
				array.add(sshAsset);
				sshAsset=null;
			}
			int count3=sshAssetMapper.bindingSshAssetByBatch(array);
			if(count3<=0){
				return 0;
			}
		}
		if(count<=0){
			return 0;
		}
		return 1;
	}

	/**更新*/
	@Transactional
	public int updateSSH(SshList sshList,String[] assetGroupId,Integer[] isEnable,String[] assetDstId,String[] assetId){
		sshList.setUpdateTime(new Date());
		int count=sshListMapper.updateSSH(sshList);
		//SSH用户和资产组的关系
		if(null!=assetGroupId && assetGroupId.length>0){
			sshAssetMapper.unbindingSshAsset(sshList.getId().toString());
			ArrayList<SshAsset> array=new ArrayList<SshAsset>();
			for(int i=0;i<assetGroupId.length;i++){
				SshAsset sshAsset=new SshAsset();
				sshAsset.setAssetGroup(assetGroupId[i]);
				sshAsset.setSshId(sshList.getId().toString());
				sshAsset.setIsEnable(isEnable[i]);
				sshAsset.setAssetDstId(assetDstId[i]);
				sshAsset.setAssetId(assetId[i]);
				array.add(sshAsset);
				sshAsset=null;
			}
			int count3=sshAssetMapper.bindingSshAssetByBatch(array);
			if(count3<=0){
				return 0;
			}
		}
		if(count<=0){
			return 0;
		}
		return 1;
	}

	/**删除*/
	@Transactional
	public int delSshBatch(String[] sshId){
		for(int i=0;i<sshId.length;i++){
			//删除主表
			SshList sshList=new SshList();
			sshList.setId(Long.valueOf(sshId[i]));
			sshList.setIsDelete(1);
			int count=sshListMapper.updateSSH(sshList);
			//解绑SSH和资产组的关系
			sshAssetMapper.unbindingSshAsset(sshId[i]);
		}
		return 1;
	} 

	public List<String> isExistNameBySsh(Map<String,Object> m){
		List<String> l=sshListMapper.isExistNameBySsh(m);
		return l;
	}
	
	public String isExistSshAssetByEnable(String sshId){
		
		return sshAssetMapper.isExistSshAssetByEnable(sshId);
	}
	

    /**资产组下拉框*/
    public List<ListByAssetGroup> assetGroupList(){
    	
    	return assetGroupMapper.assetGroupList();
    }

    /**资产下拉框*/
    public List<Map<String,Object>> assetListByAssetGroupId(Long assetGroupId){
    	
    	return assetListMapper.assetListByAssetGroupId(assetGroupId);
    }

    /**目标用户下拉框*/
    public List<ListByDst> dstListByAssetGroupId(Long assetGroupId){
    	
    	return assetDstMapper.dstListByAssetGroupId(assetGroupId);
    }

}
