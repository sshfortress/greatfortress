package com.sshfortress.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sshfortress.common.enums.ViewShowEnums;
import com.sshfortress.common.util.ResponseObj;
import com.sshfortress.service.system.SysRoleMenuService;

@Controller
@RequestMapping("web/sysRoleMenu")
public class SysRoleMenuWeb {
	
	@Autowired
	SysRoleMenuService sysRoleMenuService;
	
	  
	 /** <p class="detail">
	 * 功能：为角色添加菜单
	 * </p>
	 * @param request
	 * @param response
	 * @param roleId
	 * @param menuIds
	 * @return    
	 */
	@RequestMapping(value = "/add.web",produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
		@ResponseBody
		public ResponseObj add(HttpServletRequest request, HttpServletResponse response,Long roleId,Long[] menuIds) {
			ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
					ViewShowEnums.INFO_SUCCESS.getDetail());
			
			if(roleId==null)
			{
				obj.setShowMessage("角色ID不能为空");
				obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
				obj.setData("");
			}
			
			if(menuIds!=null&&menuIds.length>0)
			{
				obj.setShowMessage("菜单ID不能为空");
				obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
				obj.setData("");
			}
			try {
				sysRoleMenuService.add(roleId,menuIds);
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

}
