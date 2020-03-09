package com.sshfortress.controller.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sshfortress.common.beans.SysRole;
import com.sshfortress.common.beans.SysRoleForm;
import com.sshfortress.common.enums.ViewShowEnums;
import com.sshfortress.common.util.PageModel;
import com.sshfortress.common.util.Pager;
import com.sshfortress.common.util.ResponseObj;
import com.sshfortress.dao.system.mapper.SysRoleMapper;



/** <p class="detail">
 * 功能：角色操作的
 * </p>
 * @ClassName: SysRoleWeb 
 * @version V1.0  
 */
@Controller
@RequestMapping("web/sysRole")
public class SysRoleWeb {
	
	@Autowired
     SysRoleMapper sysRoleMapper;
	
	
	 /** <p class="detail">
	 * 功能：添加角色
	 * </p>
	 * @param request
	 * @param response
	 * @param form
	 * @param result
	 * @return    
	 */
	@RequestMapping(value = "/add.web",produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj add(HttpServletRequest request, HttpServletResponse response, @Valid SysRoleForm form,
			BindingResult result) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		if (result.hasErrors()) {
			obj.setShowMessage(result.getAllErrors().get(0).getDefaultMessage());
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			return obj;
		}
		SysRole sysRole = new SysRole();
		sysRole.setCreateTime(new Date());
		BeanUtils.copyProperties(form, sysRole);
		try {
			sysRoleMapper.insert(sysRole);
			obj.setShowMessage("添加成功");
			obj.setStatus(ViewShowEnums.INFO_SUCCESS.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			obj.setShowMessage("添加失败");
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
		}
		obj.setData("");
		return obj;
	}
	
	
	 /** <p class="detail">
	 * 功能：修改角色
	 * </p>
	 * @param form
	 * @return    
	 */
	@RequestMapping(value = "/update.web", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj update(@Valid SysRoleForm form) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		
		if(form.getId()==null)
		{
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("角色Id不能为空");
			return obj;
		}
		SysRole sysRole = sysRoleMapper.selectByPrimaryKey(form.getId());
		if(sysRole==null)
		{
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("角色不存在！");
			return obj;
		}
		try {
			BeanUtils.copyProperties(form, sysRole);
			sysRoleMapper.updateByPrimaryKeySelective(sysRole);
			obj.setShowMessage("修改成功");
			
			obj.setStatus(ViewShowEnums.INFO_SUCCESS.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			obj.setShowMessage("修改失败");
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
		}
		return obj;
	}
	
	
	
	
	 /** <p class="detail">
	 * 功能：删除角色
	 * </p>
	 * @param id
	 * @return    
	 */
	@RequestMapping(value = "/del.web",produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj del(Long  id)
	{
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		try {
			sysRoleMapper.deleteByPrimaryKey(id);
			obj.setShowMessage("删除成功");
			obj.setStatus(ViewShowEnums.INFO_SUCCESS.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			obj.setShowMessage("删除失败");
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
		}
		return obj;
	}
	
	/**
	 * 获取角色的列表
	 * @param role
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/getList.web",produces = { "application/json;charset=UTF-8" },  method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj getList(String flag,String name,SysRole role, Pager page) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("record", role);
		map.put("name", name);
		map.put("flag", flag);
		map.put("page", page);
		List<SysRole> list = sysRoleMapper.queryByParamsListPager(map);
		obj.setData(new PageModel(page, list));
		return obj;
	}
	
	
	/**
	 * 获取角色的列表
	 * @param role
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/selectRole.web",produces = { "application/json;charset=UTF-8" },  method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj selectRole() {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		Map<String, Object> map = new HashMap<String, Object>();
		Pager page=new Pager();
		page.setCurrentPage(1);
		page.setPageSize(9999);
		map.put("page", page);
		List<SysRole> list = sysRoleMapper.queryByParamsListPager(map);
		List<Map<String,Object>> l=new ArrayList<Map<String,Object>>();
		if(list!=null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				Map r=new HashMap();
				r.put("id", list.get(i).getId());
				r.put("roleName", list.get(i).getName());
				l.add(r);
			}
		}
		obj.setData(l);
		return obj;
	}
	
}
