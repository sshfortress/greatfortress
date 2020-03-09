package com.sshfortress.controller.ssh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sshfortress.common.entity.SshList;
import com.sshfortress.common.enums.ViewShowEnums;
import com.sshfortress.common.model.DetailBySsh;
import com.sshfortress.common.model.PageListBySsh;
import com.sshfortress.common.util.PageModel;
import com.sshfortress.common.util.Pager;
import com.sshfortress.common.util.ResponseObj;
import com.sshfortress.common.util.StringUtil;
import com.sshfortress.service.ssh.SshService;

@Controller
@RequestMapping("ssh")
public class SshWeb {

	@Autowired
	SshService sshService;

	@RequestMapping(value = "/isEnableBySsh.web",produces = { "application/json;charset=UTF-8" },  method = RequestMethod.POST)
	@ResponseBody 
	public ResponseObj isEnableByAssetBatch(String[] sshId) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		if(null==sshId || "".equals(sshId)){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("请选择待授权SSH用户");
			return obj;
		}
		for(int i=0;i<sshId.length;i++){
			String isExistSshAssetByEnable=sshService.isExistSshAssetByEnable(sshId[i]);
			if(StringUtil.isNullOrEmpty(isExistSshAssetByEnable)){
				obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
				obj.setShowMessage("请选择有资产组的SSH用户");
				return obj;
			}
		}
		int list = sshService.isEnableBySshIdBatch(sshId);
		obj.setData(list);
		return obj;                                                                  
	}        

	@RequestMapping(value = "/getSshPageList.web",produces = { "application/json;charset=UTF-8" },  method = RequestMethod.POST)
	@ResponseBody 
	public ResponseObj getAssetGroupList(Pager page,String searchText) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchText", searchText);
		//不传表示ALL
		if(null==page.getPageSize() || "".equals(page.getPageSize())){

		}else{
			map.put("page", page);
		}
		List<PageListBySsh> list = sshService.getSshPageList(map);
		obj.setData(new PageModel(page, list));
		return obj;
	}


	@RequestMapping(value = "/getDetail.web",produces = { "application/json;charset=UTF-8" },  method = RequestMethod.POST)
	@ResponseBody 
	public ResponseObj getIdcList(String sshId) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		if(null==sshId || "".equals(sshId)){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("请选择待查询SSH用户");
			return obj;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sshId", sshId);  
		DetailBySsh list = sshService.getDetailBySsh(map);
		obj.setData(list);
		return obj;
	}

	@RequestMapping(value = "/del.web", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj delAssetByBatch(String[] sshId) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),ViewShowEnums.INFO_SUCCESS.getDetail());
		if(null==sshId && sshId.length<=0){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("请选择待删除SSH用户");
			return obj;
		}
		int count=sshService.delSshBatch(sshId);
		if(count<=0){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("删除失败");
			return obj;
		}
		return obj;
	}

	@RequestMapping(value = "/updateSsh.web", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj updateSsh(Long sshId,SshList sshList,String[] assetGroupId,Integer[] isEnable,String[] assetDstId,String[] assetId) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),ViewShowEnums.INFO_SUCCESS.getDetail());
		if(StringUtil.isNullOrEmpty(sshList.getName())){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("请输入SSH登录名");
			return obj;
		}
		if("".equals(sshList.getPassword())){
			sshList.setPassword(null);
		}
		if(!StringUtil.isNullOrEmpty(sshList.getRemark()) &&
				sshList.getRemark().length()>200){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("备注字数为200字(含)以内");
			return obj;
		}
		if(null!=assetGroupId && assetGroupId.length>0){
			for(int i=0;i<assetGroupId.length-1;i++){
				for(int j=i+1;j<assetGroupId.length;j++){
					if(assetGroupId[i].equals(assetGroupId[j])){
						obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
						obj.setShowMessage("同一个SSH用户下一个资产组只能添加一个目标用户，第"+(i+1)+"个和第"+(j+1)+"个资产组重复");
						return obj;
					}
				}
			}
		}
		sshList.setId(sshId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sshId", sshList.getId());                                             
		DetailBySsh sshDetail = sshService.getDetailBySsh(map);
		String oldName=sshDetail.getName();
		if(!oldName.equals(sshList.getName())){
			Map<String,Object> m=new HashMap<String, Object>();
			m.put("name", sshList.getName());
			List<String> isExistName=sshService.isExistNameBySsh(m);
			if(null!=isExistName && !isExistName.isEmpty()){
				obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
				obj.setShowMessage("SSH登录名已存在");
				return obj;
			}
		}
		int count=sshService.updateSSH(sshList,assetGroupId,isEnable,assetDstId,assetId);
		if(count<=0){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("修改失败");
			return obj;
		}
		return obj;
	}
	
	/**
	 * <p class="detail">
	 * 功能：权限管理-->增加SSH用户
	 * 	        注意: 同一个SSH用户下一个资产组只能添加一个用户
	 * </p>
	 * @param sshList
	 * @param assetGroupId
	 * @param isEnable
	 * @param assetDstId
	 * @return
	 */
	@RequestMapping(value = "/addSsh.web", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj addSsh(SshList sshList,String[] assetGroupId,Integer[] isEnable,String[] assetDstId,String[] assetId) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),ViewShowEnums.INFO_SUCCESS.getDetail());
		if(StringUtil.isNullOrEmpty(sshList.getName())){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("请输入SSH登录名");
			return obj;
		}
		if(StringUtil.isNullOrEmpty(sshList.getPassword())){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("请输入SSH登录密码");
			return obj;
		}
		if(!StringUtil.isNullOrEmpty(sshList.getRemark()) &&
				sshList.getRemark().length()>200){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("备注字数为200字(含)以内");
			return obj;
		}
		if(null!=assetGroupId && assetGroupId.length>0){
			for(int i=0;i<assetGroupId.length-1;i++){
				for(int j=i+1;j<assetGroupId.length;j++){
					if(assetGroupId[i].equals(assetGroupId[j])){
						obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
						obj.setShowMessage("同一个SSH用户下一个资产组只能添加一个目标用户");
						return obj;
					}
				}
			}
		}
		
		Map<String,Object> m=new HashMap<String, Object>();
		m.put("name", sshList.getName());
		List<String> isExistName=sshService.isExistNameBySsh(m);
		if(null!=isExistName && !isExistName.isEmpty()){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("SSH登录名已存在");
			return obj;
		}
		int count=sshService.addSSH(sshList,assetGroupId,isEnable,assetDstId,assetId);
		if(count<=0){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("添加失败");
			return obj;
		}
		return obj;
	}

	@RequestMapping(value = "/assetGroupList.web", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj assetGroupList() {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),ViewShowEnums.INFO_SUCCESS.getDetail());
		obj.setData(sshService.assetGroupList());
		return obj;
	}

	@RequestMapping(value = "/assetList.web", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj assetList(Long assetGroupId) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),ViewShowEnums.INFO_SUCCESS.getDetail());
		if(null==assetGroupId || "".equals(assetGroupId)){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("请选择资产组");
			return obj;
		}
		obj.setData(sshService.assetListByAssetGroupId(assetGroupId));
		return obj;
	}
	
	@RequestMapping(value = "/dstList.web", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj dstListByAssetId(Long assetGroupId) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),ViewShowEnums.INFO_SUCCESS.getDetail());
		if(null==assetGroupId || "".equals(assetGroupId)){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("请选择资产组");
			return obj;
		}
		obj.setData(sshService.dstListByAssetGroupId(assetGroupId));
		return obj;
	}
}
