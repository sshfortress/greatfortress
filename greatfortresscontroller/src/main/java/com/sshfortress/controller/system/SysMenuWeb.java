package com.sshfortress.controller.system;

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

import com.sshfortress.common.beans.SysMenu;
import com.sshfortress.common.beans.SysMenuForm;
import com.sshfortress.common.enums.ViewShowEnums;
import com.sshfortress.common.model.Menu;
import com.sshfortress.common.util.PageModel;
import com.sshfortress.common.util.Pager;
import com.sshfortress.common.util.ResponseObj;
import com.sshfortress.dao.system.mapper.SysMenuMapper;
import com.sshfortress.service.system.SysMenuService;


@Controller
@RequestMapping("web/sysMenu")
public class SysMenuWeb {
    @Autowired  
	SysMenuMapper  sysMenuMapper;
    @Autowired  
    SysMenuService  sysMenuService;
    
     /** <p class="detail">
     * 功能：添加菜单
     * </p>
     * @param request
     * @param response
     * @param form
     * @param result
     * @return    
     */
    @RequestMapping(value = "/add.web",produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj add(HttpServletRequest request, HttpServletResponse response, @Valid SysMenuForm form,
			BindingResult result) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		if (result.hasErrors()) {
			obj.setShowMessage(result.getAllErrors().get(0).getDefaultMessage());
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			return obj;
		}
		SysMenu sysMenu = new SysMenu();
		sysMenu.setCreateTime(new Date());
		BeanUtils.copyProperties(form, sysMenu);
		try {
			sysMenuMapper.insertSelective(sysMenu);
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
	 * 功能：修改菜单
	 * </p>
	 * @param form
	 * @return    
	 */
	@RequestMapping(value = "/update.web", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj update(@Valid SysMenuForm form) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		
		if(form.getId()==null)
		{
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("菜单Id不能为空");
			return obj;
		}
		SysMenu sysMenu = sysMenuMapper.selectByPrimaryKey(form.getId());
		if(sysMenu==null)
		{
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("菜单不存在！");
			return obj;
		}
		try {
			BeanUtils.copyProperties(form, sysMenu);
			sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
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
	 * 功能：删除菜单
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
			sysMenuMapper.deleteByPrimaryKey(id);
			obj.setShowMessage("删除成功");
			obj.setStatus(ViewShowEnums.INFO_SUCCESS.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			obj.setShowMessage("删除失败");
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
		}
		return obj;
	}
	
	
	 /** <p class="detail">
	 * 功能：获取菜单列表
	 * </p>
	 * @param role
	 * @param page
	 * @return    
	 */
	@RequestMapping(value = "/getList.web",produces = { "application/json;charset=UTF-8" },  method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj getList(SysMenu menu, Pager page) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("record", menu);
		map.put("page", page);
		List<SysMenu> list = sysMenuMapper.queryByParamsListPager(map);
		obj.setData(new PageModel(page, list));
		return obj;
	}
      
	 /** <p class="detail">
		 * 功能：根据角色id 获取菜单的列表
		 * </p>
		 * @param role
		 * @param page
		 * @return    
		 */

	@RequestMapping(value = "/getByRoleIds.web",produces = { "application/json;charset=UTF-8" },  method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj getByRoleIds(Long[] roleIds) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		try {
			List<Menu> list = sysMenuService.selectByRoleIds(roleIds);
			obj.setData(list);
		} catch (Exception e) {
			e.printStackTrace();
			obj.setShowMessage("查询失败");
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setData("");
		}
		
		return obj;
	}
}
