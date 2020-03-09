package com.sshfortress.controller.asset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sshfortress.common.entity.AssetList;
import com.sshfortress.common.enums.ViewShowEnums;
import com.sshfortress.common.model.DetailByAsset;
import com.sshfortress.common.model.DetailByAssetGroup;
import com.sshfortress.common.model.DstListByAssetId;
import com.sshfortress.common.model.PageListByAsset;
import com.sshfortress.common.util.PageModel;
import com.sshfortress.common.util.Pager;
import com.sshfortress.common.util.ResponseObj;
import com.sshfortress.common.util.StringUtil;
import com.sshfortress.service.asset.AssetService;

@Controller
@RequestMapping("assetWeb")
public class AssetWeb {

	@Autowired
	AssetService assetService;

	@RequestMapping(value = "/getAssetPageList.web",produces = { "application/json;charset=UTF-8" },  method = RequestMethod.POST)
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
		List<PageListByAsset> list = assetService.getAssetPageList(map);
		obj.setData(new PageModel(page, list));
		return obj;
	}


	@RequestMapping(value = "/getDetail.web",produces = { "application/json;charset=UTF-8" },  method = RequestMethod.POST)
	@ResponseBody 
	public ResponseObj getDetail(String assetId) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		if(null==assetId || "".equals(assetId)){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("请选择待查询资产");
			return obj;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("assetId", assetId);                                             
		DetailByAsset list = assetService.getAssetDetail(map);
		obj.setData(list);
		return obj;                                                                  
	}    


	@RequestMapping(value = "/sonAssetByParentList.web",produces = { "application/json;charset=UTF-8" },  method = RequestMethod.POST)
	@ResponseBody 
	public ResponseObj getSonAssetByParentList(Long assetId) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		if(null==assetId || "".equals(assetId)){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("请选择待查询父级资产");
			return obj;
		}
		List<PageListByAsset> list = new ArrayList<PageListByAsset>();
		obj.setData(list);
		return obj;                                                                  
	}    

	@RequestMapping(value = "/del.web", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj delAssetByBatch(String[] assetId) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),ViewShowEnums.INFO_SUCCESS.getDetail());
		if(null==assetId && assetId.length<=0){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("请选择待删除资产");
			return obj;
		}
		int count=assetService.delAsset(assetId);
		if(count<=0){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("删除失败");
			return obj;
		}
		return obj;
	}

	@RequestMapping(value = "/updateAsset.web", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj updateAsset(Long assetId,AssetList assetList,String[] assetGroupId) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),ViewShowEnums.INFO_SUCCESS.getDetail());
		if(StringUtil.isNullOrEmpty(assetList.getHostName())){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("请输入主机名");
			return obj;
		}
		if(null==assetList.getSshPort() || "".equals(assetList.getSshPort())){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("请输入端口号");
			return obj;
		}
		if(StringUtil.isNullOrEmpty(assetList.getDstIp())){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("请输入目标IP");
			return obj;
		}
		//		if(StringUtil.isNullOrEmpty(assetList.getDstName())){
		//			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
		//			obj.setShowMessage("请输入目标名");
		//			return obj;
		//		}
		//		if("".equals(assetList.getDstPassword())){
		//			assetList.setDstPassword(null);
		//		}
		if(!StringUtil.isNullOrEmpty(assetList.getRemark()) &&
				assetList.getRemark().length()>200){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("备注字数为200字(含)以内");
			return obj;
		}
		assetList.setId(assetId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("assetId", assetList.getId());                                             
		DetailByAsset assetDetail = assetService.getAssetDetail(map);
		String oldHostName=assetDetail.getHostName();
		if(!oldHostName.equals(assetList.getHostName())){
			Map<String,Object> m=new HashMap<String, Object>();
			m.put("hostName", assetList.getHostName());
			List<String> isExistName=assetService.isExistNameByAsset(m);
			if(null!=isExistName && !isExistName.isEmpty()){
				obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
				obj.setShowMessage("主机名已存在");
				return obj;
			}
		}
		int count=assetService.updateAsset(assetList, assetGroupId);
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
	@RequestMapping(value = "/addAsset.web", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj addAsset(AssetList assetList,String[] assetGroupId) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),ViewShowEnums.INFO_SUCCESS.getDetail());
		if(StringUtil.isNullOrEmpty(assetList.getHostName())){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("请输入主机名");
			return obj;
		}
		if(null==assetList.getSshPort() || "".equals(assetList.getSshPort())){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("请输入端口号");
			return obj;
		}
		if(StringUtil.isNullOrEmpty(assetList.getDstIp())){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("请输入目标IP");
			return obj;
		}
		//		if(StringUtil.isNullOrEmpty(assetList.getDstName())){
		//			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
		//			obj.setShowMessage("请输入目标名");
		//			return obj;
		//		}
		//		if(StringUtil.isNullOrEmpty(assetList.getDstPassword())){
		//			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
		//			obj.setShowMessage("请输入目标密码");
		//			return obj;
		//		}
		//		if(!StringUtil.isNullOrEmpty(assetList.getDstIp()) && !ValidateUtil.isValidIP(assetList.getDstIp())){
		//			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
		//			obj.setShowMessage("目标IP格式错误");
		//			return obj;
		//		}
		if(!StringUtil.isNullOrEmpty(assetList.getRemark()) &&
				assetList.getRemark().length()>200){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("备注字数为200字(含)以内");
			return obj;
		}
		Map<String,Object> m=new HashMap<String, Object>();
		m.put("hostName", assetList.getHostName());
		List<String> isExistName=assetService.isExistNameByAsset(m);
		if(null!=isExistName && !isExistName.isEmpty()){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("主机名已存在");
			return obj;
		}
		int count=assetService.addAsset(assetList, assetGroupId);
		if(count<=0){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("添加失败");
			return obj;
		}
		return obj;
	}

	/**
	 * <p class="detail">
	 * 功能：增加资产的目标用户名和密码
	 * 	        注意:某个资产组下面只能存在唯一的用户名
	 * </p>
	 * @param assetId
	 * @param dstName
	 * @param dstPassword
	 * @param dstId
	 * @return
	 */
	@RequestMapping(value = "/addAssetDstNamePsd.web", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj addAssetDstNamePsd(Long assetId,String[] dstName,String[] dstPassword,String[] dstId) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),ViewShowEnums.INFO_SUCCESS.getDetail());
		// 数据库已存在的目标用户
		List<String> dstNameInData=new ArrayList<String>();
		List<String> dstPsdInData=new ArrayList<String>();
		List<Long> dstIdInData=new ArrayList<Long>();
		// 数据库不存在的目标用户
		List<String> dstNameNotInData=new ArrayList<String>();
		List<String> dstPsdNotInData=new ArrayList<String>();
//		Long assetGroupId=assetService.getAssetGroupIdByAssetId(assetId);
		if(null!=dstName && dstName.length>0){
			for(int i=0;i<dstName.length;i++){

//				boolean b=this.isExistByDstName(dstName[i], assetId, assetGroupId);
//				if(b==true){
//					Map<String,Object> m=new HashMap<String, Object>();
//					m.put("assetGroupId", assetGroupId);
//					DetailByAssetGroup detali=assetService.getAssetGroupDetail(m);
//					obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
//					obj.setShowMessage("该资产组 "+detali.getGroupName()+" 下目标用户名 "+dstName[i]+" 已存在，请重新编辑");
//					return obj;
//				}

				if(!"0".equals(dstId[i])){
					dstNameInData.add(dstName[i]);
					dstPsdInData.add(dstPassword[i]);
					dstIdInData.add(Long.valueOf(dstId[i]));
				}else{
					dstNameNotInData.add(dstName[i]);
					dstPsdNotInData.add(dstPassword[i]);
				}
			}
		}
		assetService.addAssetDstNamePsd(dstNameInData,dstPsdInData,dstIdInData,dstNameNotInData,dstPsdNotInData,assetId);
		return obj;
	}

	/**
	 * <p class="detail">
	 * 功能：查询某个资产下的所有目标用户名和密码
	 * </p>
	 * @param assetId
	 * @return
	 */
	@RequestMapping(value = "/getDstNamePsdListByAssetId.web", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj getDstNamePsdListByAssetId(Long assetId) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),ViewShowEnums.INFO_SUCCESS.getDetail());
		if(null==assetId || "".equals(assetId)){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("请选择待查看资产");
			return obj;
		}
		List<DstListByAssetId> l=assetService.getDstNamePsdListByAssetId(assetId);
		obj.setData(l);
		return obj;
	}

	/**
	 * <p class="detail">
	 * 功能：删除目标用户
	 * </p>
	 * @param dstId
	 * @return
	 */
	@RequestMapping(value = "/delDstByDstId.web", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj delDstByDstId(Long dstId) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),ViewShowEnums.INFO_SUCCESS.getDetail());
		if(null==dstId || "".equals(dstId)){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("请选择待删除目标用户");
			return obj;
		}
		int count=assetService.delDstByDstId(dstId);
		obj.setData(count);
		return obj;
	}

	/**
	 * <p class="detail">
	 * 功能：某个资产组下除了某个资产及某个用户名以外的用户名集合中是否存在该用户名
	 * </p>
	 * @param dstName
	 * @param assetId
	 * @param assetGroupId
	 * @return 存在则返回true 不存在则返回false
	 */
	public boolean isExistByDstName(String dstName,Long assetId,Long assetGroupId){
		boolean b=true;
		Map<String,Object> m=new HashMap<String, Object>();
		m.put("dstName", dstName);
		m.put("assetId", assetId);
		m.put("assetGroupId", assetGroupId);
		Long isExist=assetService.getDstListByExceptAssetIdDstName(m);
		if(null!=isExist && !"".equals(isExist)){
			b=true;
		}else{
			b=false;
		}
		return b;

	}
}
