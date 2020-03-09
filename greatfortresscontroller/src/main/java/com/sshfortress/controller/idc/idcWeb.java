package com.sshfortress.controller.idc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sshfortress.common.entity.AssetList;
import com.sshfortress.common.entity.IdcList;
import com.sshfortress.common.enums.ViewShowEnums;
import com.sshfortress.common.model.DetailByAsset;
import com.sshfortress.common.model.DetailByIdc;
import com.sshfortress.common.model.PageListByAsset;
import com.sshfortress.common.model.PageListByIdc;
import com.sshfortress.common.util.PageModel;
import com.sshfortress.common.util.Pager;
import com.sshfortress.common.util.ResponseObj;
import com.sshfortress.common.util.StringUtil;
import com.sshfortress.common.util.ValidateUtil;
import com.sshfortress.service.asset.AssetService;
import com.sshfortress.service.idc.IdcService;

@Controller
@RequestMapping("idcWeb")
public class idcWeb {

	@Autowired
	AssetService assetService;
	
	@Autowired
	IdcService idcService;


	@RequestMapping(value = "/getIdcPageList.web",produces = { "application/json;charset=UTF-8" },  method = RequestMethod.POST)
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
		List<PageListByIdc> list = idcService.getIdcPageList(map);
		obj.setData(new PageModel(page, list));
		return obj;
	}


	@RequestMapping(value = "/getDetail.web",produces = { "application/json;charset=UTF-8" },  method = RequestMethod.POST)
	@ResponseBody 
	public ResponseObj getDetail(String idcId) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		if(null==idcId || "".equals(idcId)){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("请选择待查询机房");
			return obj;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idcId", idcId);                                             
		DetailByIdc list = idcService.getIdcDetail(map);
		obj.setData(list);
		return obj;                                                                  
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 

	@RequestMapping(value = "/del.web", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj delIdcByBatch(String[] idcId) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),ViewShowEnums.INFO_SUCCESS.getDetail());
		if(null==idcId && idcId.length<=0){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("请选择待删除机房");
			return obj;
		}
		int count=idcService.deleteIdc(idcId);
		if(count<=0){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("删除失败");
			return obj;
		}
		return obj;
	}
	
	@RequestMapping(value = "/updateIdc.web", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj updateIdc(Long idcId,IdcList idcList) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),ViewShowEnums.INFO_SUCCESS.getDetail());
		if(StringUtil.isNullOrEmpty(idcList.getIdcName())){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("请输入机房名");
			return obj;
		}
		if(!StringUtil.isNullOrEmpty(idcList.getPhone()) && 
				!ValidateUtil.isValidMobile(idcList.getPhone())){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("手机号格式不正确");
			return obj;
		}
		idcList.setId(idcId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idcId", idcList.getId());                                             
		DetailByIdc idcDetail = idcService.getIdcDetail(map);
		String oldIdcName=idcDetail.getIdcName();
		if(!oldIdcName.equals(idcList.getIdcName())){
			Map<String,Object> m=new HashMap<String, Object>();
			m.put("idcName", idcList.getIdcName());
			List<String> isExistName=idcService.isExistName(m);
			if(null!=isExistName && !isExistName.isEmpty()){
				obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
				obj.setShowMessage("机房名已存在");
				return obj;
			}
		}
		int count=idcService.updateIdc(idcList);
		if(count<=0){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("修改失败");
			return obj;
		}
		return obj;
	}

	/**
	 * <p class="detail">
	 * 功能：增加资产
	 * </p>
	 * @param assetList
	 * @param assetGroupId
	 * @return
	 */
	@RequestMapping(value = "/addIdc.web", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj addIdc(IdcList idcList) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),ViewShowEnums.INFO_SUCCESS.getDetail());
		if(StringUtil.isNullOrEmpty(idcList.getIdcName())){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("请输入机房名");
			return obj;
		}
		if(!StringUtil.isNullOrEmpty(idcList.getPhone()) && 
				!ValidateUtil.isValidMobile(idcList.getPhone())){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("手机号格式不正确");
			return obj;
		}
		Map<String,Object> m=new HashMap<String, Object>();
		m.put("idcName", idcList.getIdcName());
		List<String> isExistName=idcService.isExistName(m);
		if(null!=isExistName && !isExistName.isEmpty()){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("机房名已存在");
			return obj;
		}
		int count=idcService.addIdc(idcList);
		if(count<=0){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("添加失败");
			return obj;
		}
		return obj;
	}

}
