package com.sshfortress.controller.system;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sshfortress.common.beans.SysOrganization;
import com.sshfortress.common.enums.ViewShowEnums;
import com.sshfortress.common.util.OperationContextHolder;
import com.sshfortress.common.util.ResponseObj;
import com.sshfortress.dao.system.mapper.SysOrganizationMapper;

/**
 * <p class="detail">
 * 功能：组织结构
 * </p>
 * @ClassName: SysOrganizationWeb 
 * @version V1.0  
 */
@Controller
@RequestMapping("sysOrganizationWeb")
public class SysOrganizationWeb {

	@Autowired
	SysOrganizationMapper sysOrganizationMapper;
	/**
	 * <p class="detail">
	 * 功能：后台添加或者修改组织机构，第一次为添加，之后都为修改
	 * </p>
	 * @param request
	 * @param response
	 * @param sysOrganization
	 * @return
	 */
	@RequestMapping(value= "/addSysOrganization.web" ,method=RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseObj addSysOrganization(HttpServletRequest request,HttpServletResponse response,SysOrganization sysOrganization){
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(), ViewShowEnums.INFO_SUCCESS.getDetail());
		if(sysOrganization.getId()==null){
			int addSysOrganization = sysOrganizationMapper.insertSelective(sysOrganization);
			if(addSysOrganization<=0){
				obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
				obj.setShowMessage(ViewShowEnums.ERROR_FAILED.getDetail());
			}
			obj.setData(addSysOrganization);
			return obj;
		}
	
	int updateSysOrganization = sysOrganizationMapper.updateByPrimaryKeySelective(sysOrganization);
	if(updateSysOrganization<=0){
		obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
		obj.setShowMessage(ViewShowEnums.ERROR_FAILED.getDetail());
	}
	obj.setData(updateSysOrganization);
	return obj;
		}
	/**
	 * <p class="detail">
	 * 功能：查询组织机构
	 * </p>
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value= "/querySysOrganization.web" ,method=RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseObj querySysOrganization(HttpServletRequest request,HttpServletResponse response){
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(), ViewShowEnums.INFO_SUCCESS.getDetail());
		String userId = OperationContextHolder.getUserId();
		SysOrganization sysOrganization = sysOrganizationMapper.selectSysOrganization(userId);
		if(sysOrganization==null){
			obj.setData(0);
			return obj;
		}
		obj.setData(sysOrganization);
		return obj;
	}
	
	}