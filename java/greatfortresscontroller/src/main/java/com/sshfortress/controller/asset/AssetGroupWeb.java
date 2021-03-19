package com.sshfortress.controller.asset;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sshfortress.common.entity.AssetGroup;
import com.sshfortress.common.entity.AssetList;
import com.sshfortress.common.enums.ViewShowEnums;
import com.sshfortress.common.model.DetailByAsset;
import com.sshfortress.common.model.DetailByAssetGroup;
import com.sshfortress.common.model.PageListByAsset;
import com.sshfortress.common.model.PageListByAssetGroup;
import com.sshfortress.common.util.PageModel;
import com.sshfortress.common.util.Pager;
import com.sshfortress.common.util.ResponseObj;
import com.sshfortress.common.util.StringUtil;
import com.sshfortress.service.asset.AssetService;

@Controller
@RequestMapping("assetgroupWeb")
public class AssetGroupWeb {

	@Autowired
	AssetService assetService;


	@RequestMapping(value = "/getAssetGroupPageList.web",produces = { "application/json;charset=UTF-8" },  method = RequestMethod.POST)
	@ResponseBody 
	public ResponseObj getList(Pager page,String searchText) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchText", searchText);
		//不传表示ALL
		if(null==page.getPageSize() || "".equals(page.getPageSize())){

		}else{
			map.put("page", page);
		}
		List<PageListByAssetGroup> list = assetService.getAssetGroupPageList(map);
		obj.setData(new PageModel(page, list));
		return obj;
	}


	@RequestMapping(value = "/getDetail.web",produces = { "application/json;charset=UTF-8" },  method = RequestMethod.POST)
	@ResponseBody 
	public ResponseObj getDetail(String assetGroupId) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		if(null==assetGroupId || "".equals(assetGroupId)){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("请选择待查询资产分组");
			return obj;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("assetGroupId", assetGroupId);                                             
		DetailByAssetGroup list = assetService.getAssetGroupDetail(map);
		obj.setData(list);
		return obj;                                                                  
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 

	@RequestMapping(value = "/del.web", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj delAssetGroupByBatch(String[] assetGroupId) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),ViewShowEnums.INFO_SUCCESS.getDetail());
		if(null==assetGroupId || assetGroupId.length<=0){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("请选择待删除资产分组");
			return obj;
		}
		int count=assetService.delAssetGroup(assetGroupId);
		if(count<=0){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("删除失败");
			return obj;
		}
		return obj;
	}
	
	@RequestMapping(value = "/updateAssetGroup.web", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj updateAssetGroup(Long assetGroupId,AssetGroup assetGroup,String[] assetId) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),ViewShowEnums.INFO_SUCCESS.getDetail());
		if(StringUtil.isNullOrEmpty(assetGroup.getGroupName())){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("请输入资产分组名");
			return obj;
		}
		if(!StringUtil.isNullOrEmpty(assetGroup.getRemark()) &&
				assetGroup.getRemark().length()>200){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("备注字数为200字(含)以内");
			return obj;
		}
		assetGroup.setId(assetGroupId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("assetGroupId", assetGroup.getId());                                             
		DetailByAssetGroup assetDetail = assetService.getAssetGroupDetail(map);
		String oldGroupName=assetDetail.getGroupName();
		if(!oldGroupName.equals(assetGroup.getGroupName())){
			Map<String,Object> m=new HashMap<String, Object>();
			m.put("groupName", assetGroup.getGroupName());
			List<String> isExistName=assetService.isExistNameByAssetGroup(m);
			if(null!=isExistName && !isExistName.isEmpty()){
				obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
				obj.setShowMessage("资产分组名已存在");
				return obj;
			}
		}
		int count=assetService.updateAssetGroup(assetGroup, assetId);
		if(count<=0){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("修改失败");
			return obj;
		}
		return obj;
	}

	/**
	 * <p class="detail">
	 * 功能：增加资产分组
	 * </p>
	 * @param assetList
	 * @param assetGroupId
	 * @return
	 */
	@RequestMapping(value = "/addAssetGroup.web", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj addAssetGroup(AssetGroup assetGroup,String[] assetId) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),ViewShowEnums.INFO_SUCCESS.getDetail());
		if(StringUtil.isNullOrEmpty(assetGroup.getGroupName())){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("请输入资产分组名");
			return obj;
		}
		if(!StringUtil.isNullOrEmpty(assetGroup.getRemark()) &&
				assetGroup.getRemark().length()>200){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("备注字数为200字(含)以内");
			return obj;
		}
		Map<String,Object> m=new HashMap<String, Object>();
		m.put("groupName", assetGroup.getGroupName());
		List<String> isExistName=assetService.isExistNameByAssetGroup(m);
		if(null!=isExistName && !isExistName.isEmpty()){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("资产分组名已存在");
			return obj;
		}
		int count=assetService.addAssetGroup(assetGroup, assetId);
		if(count<=0){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("添加失败");
			return obj;
		}
		return obj;
	}

}
