package com.sshfortress.controller.system;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sshfortress.common.beans.SysUser;
import com.sshfortress.common.beans.SysUserForm;
import com.sshfortress.common.enums.UserStatus;
import com.sshfortress.common.enums.UserTypeEnums;
import com.sshfortress.common.enums.ViewShowEnums;
import com.sshfortress.common.util.PageModel;
import com.sshfortress.common.util.Pager;
import com.sshfortress.common.util.ResponseObj;
import com.sshfortress.common.util.StringUtil;
import com.sshfortress.dao.system.mapper.SysUserMapper;
import com.sshfortress.service.system.SysUserService;

@Controller
@RequestMapping("web/sysUser")
public class SysUserWeb {

	@Autowired
	SysUserMapper sysUserMapper;
	@Autowired
	SysUserService sysUserService;
	
	 /** <p class="detail">
	 * 功能：获取系统用户
	 * </p>
	 * @param user
	 * @param page
	 * @return    
	 */
	@RequestMapping(value = "/getList.web",produces = { "application/json;charset=UTF-8" },  method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj getList(SysUser user, Pager page) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("record", user);
		map.put("page", page);
		List<SysUser> list = sysUserMapper.queryByParamsListPager(map);
		obj.setData(new PageModel(page, list));
		return obj;
	}

	
	 /** <p class="detail">
	 * 功能：根据用户ID获取系统用户
	 * </p>
	 * @param userId
	 * @return    
	 */
	@RequestMapping(value = "/getSysUser.web", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj getSysUser(String userId) {
		SysUser selectByPrimaryKey = sysUserMapper.selectByPrimaryKey(userId);
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		obj.setData(selectByPrimaryKey);
		return obj;
	}

	
	 /** <p class="detail">
	 * 功能：更新系统用户
	 * </p>
	 * @param form
	 * @return    
	 */
	@RequestMapping(value = "/update.web", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj update(@Valid SysUserForm form) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		
		if(StringUtil.isNullOrEmpty(form.getUserId()))
		{
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("用户Id不能为空");
			return obj;
		}
		SysUser sysUser = sysUserMapper.selectByPrimaryKey(form.getUserId());
		if(sysUser==null)
		{
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("用户不存在！");
			return obj;
		}
		try {
			sysUserService.updateUser(form.getUserId(),form.getUserName(), form.getPassWord(),form.getRoleId());
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
	 * 功能：添加系统用户
	 * </p>
	 * @param request
	 * @param response
	 * @param form
	 * @param result
	 * @return    
	 */
	@RequestMapping(value = "/add.web",produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj add(HttpServletRequest request, HttpServletResponse response, @Valid SysUserForm form,
			BindingResult result) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		if (result.hasErrors()) {
			obj.setShowMessage(result.getAllErrors().get(0).getDefaultMessage());
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			return obj;
		}
		if (form != null && form.getRoleId() == null) {
			obj.setShowMessage("角色ID不能为空");
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			return obj;
		}
		//判断用户是否存在
		SysUser queryByUserNameAndUserType = sysUserService.queryByUserNameAndUserType(form.getUserName(), UserTypeEnums.SYSTEM_MANAGER.getCode());
		if(queryByUserNameAndUserType!=null)
		{
			if(queryByUserNameAndUserType.getStatus()==UserStatus.VOID.getCode())
			{
				obj.setShowMessage("账户被禁用！");
				obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			}
			else
			{
				obj.setShowMessage("账户已经存在");
				obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			}
			obj.setData("");
			return obj;
		}
		try {
			sysUserService.addUser(form.getUserName(), form.getPassWord(), form.getRoleId());
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
	 * 功能：删除系统用户
	 * </p>
	 * @param userId
	 * @return    
	 */
	@RequestMapping(value = "/del.web",produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj del(String userId)
	{
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		try {
			sysUserService.delUser(userId);
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
		 * 功能：查询系统权限列表
		 * </p>
		 * @param userId
		 * @return    
		 */
		@RequestMapping(value = "/selectUser.web",produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
		@ResponseBody
		public ResponseObj selectUser(String userName,String roleName,Pager pager)
		{
			ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
					ViewShowEnums.INFO_SUCCESS.getDetail());
            Map<String,Object> params=new HashMap<String,Object>();
            params.put("userName", userName);
            params.put("roleName", roleName);
            params.put("pager", pager);
            List<Map<String,Object>> l=sysUserService.selectUser(params);
			obj.setData(new PageModel(pager, l));
            return obj;
		}
		
		
		/**
		 * <p class="detail">
		 * 功能：冻结解冻会员
		 * </p>
		 * @param sysUser
		 * @return
		 */
		@RequestMapping(value = "/freezeUser.web",produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
		@ResponseBody
		public ResponseObj freezeUser(SysUser sysUser){
			ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
					ViewShowEnums.INFO_SUCCESS.getDetail());
			int freezeUser = sysUserMapper.updateByPrimaryKeySelective(sysUser);
			if(freezeUser<=0){
				obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
				obj.setShowMessage(ViewShowEnums.ERROR_FAILED.getDetail());
				obj.setData(freezeUser);
				return obj;
			}
			obj.setData(freezeUser);
			return obj;
		}
		
}
