package com.sshfortress.service.asset;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sshfortress.common.entity.AssetDst;
import com.sshfortress.common.entity.AssetGroup;
import com.sshfortress.common.entity.AssetList;
import com.sshfortress.common.entity.AssetListRelation;
import com.sshfortress.common.model.DetailByAsset;
import com.sshfortress.common.model.DetailByAssetGroup;
import com.sshfortress.common.model.DstListByAssetId;
import com.sshfortress.common.model.PageListByAsset;
import com.sshfortress.common.model.PageListByAssetGroup;
import com.sshfortress.common.util.OperationContextHolder;
import com.sshfortress.dao.asset.mapper.AssetDstMapper;
import com.sshfortress.dao.asset.mapper.AssetGroupMapper;
import com.sshfortress.dao.asset.mapper.AssetListMapper;
import com.sshfortress.dao.asset.mapper.AssetListRelationMapper;

@Service
public class AssetService {

	@Autowired
	AssetListMapper assetListMapper;

	@Autowired
	AssetGroupMapper assetGroupMapper;

	@Autowired
	AssetListRelationMapper assetListRelationMapper;
	
	@Autowired
	AssetDstMapper assetDstMapper;
	

    /**查询某个资产组下除了某个资产及某个用户名以外的用户名集合 Map: assetId assetGroupId dstName*/
    public Long getDstListByExceptAssetIdDstName(Map<String,Object> m){
    	
    	return assetDstMapper.getDstListByExceptAssetIdDstName(m);
    }
	

    /**通过资产查找资产组id*/
    public Long getAssetGroupIdByAssetId(Long assetId){
    	
    	return assetListRelationMapper.getAssetGroupIdByAssetId(assetId);
    }
    
    /**删除某个目标*/
    public int delDstByDstId(Long dstId){
    	
    	return assetDstMapper.delDstByDstId(dstId);
    }
    
    
	/**增加资产的目标用户名和密码*/
	@Transactional
	public int addAssetDstNamePsd(List<String> dstNameInData,List<String> dstPsdInData,
			List<Long> dstIdInData,List<String> dstNameNotInData,List<String> dstPsdNotInData,
			Long assetId){
		ArrayList<AssetDst> arrayList=new ArrayList<AssetDst>();
		int count1=0;
		// id存在则更新
		if(null!=dstNameInData && !dstNameInData.isEmpty()){
			for(int i=0;i<dstNameInData.size();i++){
				AssetDst assetDst=new AssetDst();
				assetDst.setAssetId(assetId);
				assetDst.setId(dstIdInData.get(i));
				assetDst.setDstName(dstNameInData.get(i));
				assetDst.setDstPassword(dstPsdInData.get(i));
				int count2=assetDstMapper.updateByPrimaryKeySelective(assetDst);
				assetDst=null;
			}
		}
		
		// id不存在则添加
		if(null!=dstNameNotInData && !dstNameNotInData.isEmpty()){
			for(int i=0;i<dstNameNotInData.size();i++){
				AssetDst assetDst=new AssetDst();
				assetDst.setAssetId(assetId);
				assetDst.setDstName(dstNameNotInData.get(i));
				assetDst.setDstPassword(dstPsdNotInData.get(i));
				arrayList.add(assetDst);
				assetDst=null;
			}
			count1=assetDstMapper.addAssetDstNamePsdByBatch(arrayList);
		}
		return count1;
	}
	
	/**查询某个资产下的所有目标用户名和密码*/
	public List<DstListByAssetId> getDstNamePsdListByAssetId(Long assetId){
		
		return assetDstMapper.getDstNamePsdListByAssetId(assetId);
	}

	
	/**增加资产*/
	@Transactional
	public int addAsset(AssetList assetList,String[] assetGroupId){
		String userId=OperationContextHolder.getUserId();
		//主表
		assetList.setCreateTime(new Date());
		assetList.setCreator(userId);
		assetList.setUpdateTime(new Date());
		assetList.setIsDelete(0);
		int count=assetListMapper.addAsset(assetList);
		//绑定组员关系
		if(null!=assetGroupId && assetGroupId.length>0){
			ArrayList<AssetListRelation> array=new ArrayList<AssetListRelation>();
			for(int i=0;i<assetGroupId.length;i++){
				AssetListRelation assetListRelation=new AssetListRelation();
				assetListRelation.setAssetGroup(Long.valueOf(assetGroupId[i]));;
				assetListRelation.setAssetId(assetList.getId());;
				array.add(assetListRelation);
			}
			int count2=assetListRelationMapper.addAssetGroupRelationByBatch(array);
			if(count2<=0){
				return 0;
			}
		}
		if(count>0){
			return 1;
		}
		return 0;
	}

	/**资产名是否存在*/
	public List<String> isExistNameByAsset(Map<String,Object> m){
		List<String> l=assetListMapper.isExistName(m);
		return l;
	}

	/**更新资产*/
	@Transactional
	public int updateAsset(AssetList assetList,String[] assetGroupId){
		//主表
		assetList.setUpdateTime(new Date());
		assetListMapper.updateAsset(assetList);
		if(null!=assetGroupId && assetGroupId.length>0){
			//解除组员和组的关系
			assetListRelationMapper.unbundlingByDelAsset(Long.valueOf(assetList.getId()));
			ArrayList<AssetListRelation> array=new ArrayList<AssetListRelation>();
			for(int i=0;i<assetGroupId.length;i++){
				AssetListRelation assetListRelation=new AssetListRelation();
				assetListRelation.setAssetGroup(Long.valueOf(assetGroupId[i]));;
				assetListRelation.setAssetId(assetList.getId());;
				array.add(assetListRelation);
			}
			int count2=assetListRelationMapper.addAssetGroupRelationByBatch(array);
			if(count2<=0){
				return 0;
			}
		}
		return 1;
	}

	/**删除资产*/
	@Transactional
	public int delAsset(String[] assetId){
		for(int i=0;i<assetId.length;i++){
			//删除资产下的目标用户
			assetDstMapper.delAssetDstByAssetId(Long.valueOf(assetId[i]));
			//删除主表
			AssetList record=new AssetList();
			record.setId(Long.valueOf(assetId[i]));
			record.setIsDelete(1);
			int count=assetListMapper.updateAsset(record);
			//解除组员和组的关系
			assetListRelationMapper.unbundlingByDelAsset(Long.valueOf(assetId[i]));
			
			if(count>0){

			}else{
				return 0;
			}
		}
		return 1;
	}

	/**资产详情*/
	public DetailByAsset getAssetDetail(Map<String,Object> m){


		return assetListMapper.getAssetDetail(m);
	}

	/**资产分页列表*/
	public List<PageListByAsset> getAssetPageList(Map<String,Object> m){

		return assetListMapper.getAssetPageList(m);
	}


	/********************************************************资产分组*********************************************************/

	/**增加资产分组*/
	@Transactional
	public int addAssetGroup(AssetGroup assetGroup,String[] assetId){
		String userId=OperationContextHolder.getUserId();
		//主表
		assetGroup.setCreateTime(new Date());
		assetGroup.setCreator(userId);
		assetGroup.setUpdateTime(new Date());
		assetGroup.setIsDelete(0);
		int count=assetGroupMapper.addAssetGroup(assetGroup);
		//绑定组员关系
		if(null!=assetId && assetId.length>0){
			ArrayList<AssetListRelation> array=new ArrayList<AssetListRelation>();
			for(int i=0;i<assetId.length;i++){
				AssetListRelation assetListRelation=new AssetListRelation();
				assetListRelation.setAssetGroup(assetGroup.getId());;
				assetListRelation.setAssetId(Long.valueOf(assetId[i]));;
				array.add(assetListRelation);
			}
			int count2=assetListRelationMapper.addAssetGroupRelationByBatch(array);
			if(count2<=0){
				return 0;
			}
		}
		if(count>0){
			return 1;
		}
		return 0;
	}

	/**资产组名是否存在*/
	public List<String> isExistNameByAssetGroup(Map<String,Object> m){
		List<String> l=assetGroupMapper.isExistName(m);
		return l;
	}

	/**更新资产分组*/
	@Transactional
	public int updateAssetGroup(AssetGroup assetGroup,String[] assetId){
		//主表
		assetGroup.setUpdateTime(new Date());
		assetGroupMapper.updateAssetGroup(assetGroup);
		if(null!=assetId && assetId.length>0){
			//解除组员和组的关系
			assetListRelationMapper.unbundlingByDelAssetGroup(assetGroup.getId());
			ArrayList<AssetListRelation> array=new ArrayList<AssetListRelation>();
			for(int i=0;i<assetId.length;i++){
				AssetListRelation assetListRelation=new AssetListRelation();
				assetListRelation.setAssetGroup(assetGroup.getId());;
				assetListRelation.setAssetId(Long.valueOf(assetId[i]));;
				array.add(assetListRelation);
			}
			int count2=assetListRelationMapper.addAssetGroupRelationByBatch(array);
			if(count2<=0){
				return 0;
			}
		}
		return 1;
	}

	/**删除资产分组*/
	@Transactional
	public int delAssetGroup(String[] assetGroupId){
		for(int i=0;i<assetGroupId.length;i++){
			//删除主表
			AssetGroup record=new AssetGroup();
			record.setId(Long.valueOf(assetGroupId[i]));
			record.setIsDelete(1);
			int count=assetGroupMapper.updateAssetGroup(record);
			//解除组员和组的关系
			assetListRelationMapper.unbundlingByDelAssetGroup(Long.valueOf(assetGroupId[i]));
			if(count>0){

			}else{
				return 0;
			}
		}
		return 1;
	}

	/**资产分组详情*/
	public DetailByAssetGroup getAssetGroupDetail(Map<String,Object> m){


		return assetGroupMapper.getAssetGroupDetail(m);
	}

	/**资产分组分页列表*/
	public List<PageListByAssetGroup> getAssetGroupPageList(Map<String,Object> m){

		return assetGroupMapper.getAssetGroupPageList(m);
	}

}
