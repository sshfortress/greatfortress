package com.sshfortress.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sshfortress.common.beans.MemberForm;
import com.sshfortress.common.beans.MemberInfo;
import com.sshfortress.common.beans.SysUser;
import com.sshfortress.common.beans.SysUserInfo;
import com.sshfortress.common.enums.UserStatus;
import com.sshfortress.common.enums.ViewShowEnums;
import com.sshfortress.common.model.UserDetailDto;
import com.sshfortress.common.util.OperationContextHolder;
import com.sshfortress.common.util.PageModel;
import com.sshfortress.common.util.Pager;
import com.sshfortress.common.util.ResponseObj;
import com.sshfortress.common.util.StringUtil;
import com.sshfortress.common.util.ValidateUtil;
import com.sshfortress.dao.system.mapper.SysUserMapper;
import com.sshfortress.dao.user.mapper.SysUserInfoMapper;
import com.sshfortress.service.system.SysUserService;

@Controller
@RequestMapping("memberWeb")
public class MemberWeb {

	@Autowired
	SysUserInfoMapper sysUserInfoMapper;

	@Autowired
	SysUserMapper sysUserMapper;

	@Autowired
	SysUserService sysUserService;

	/**
	 * <p class="detail">
	 * 功能：web注册用户分页列表
	 * </p>
	 * @param member
	 * @param page
	 * @param searchText
	 * @return
	 */
	@RequestMapping(value = "/getList.web",produces = { "application/json;charset=UTF-8" },  method = RequestMethod.POST)
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
		List<MemberInfo> list = sysUserInfoMapper.selectMemberInfoListPager(map);
		obj.setData(new PageModel(page, list));
		return obj;
	}


	/** <p class="detail">
	 * 功能：获取会员详情
	 * </p>
	 * @param user
	 * @param page
	 * @return    
	 */
	@RequestMapping(value = "/getDetail.web",produces = { "application/json;charset=UTF-8" },  method = RequestMethod.POST)
	@ResponseBody 
	public ResponseObj getDetail(String userId) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);                                             
		UserDetailDto list = sysUserInfoMapper.getUserDetail(map);
		obj.setData(list);
		return obj;                                                                  
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 

	/**
	 * <p class="detail">
	 * 功能：批量删除登录用户
	 * </p>
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/del.web", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj delUserByBatch(String[] userId) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),ViewShowEnums.INFO_SUCCESS.getDetail());
		if(null==userId && userId.length<=0){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("请选择待删除用户");
			return obj;
		}
		int count=sysUserService.delUser(userId);
		if(count<=0){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("删除失败");
			return obj;
		}
		return obj;
	}
	
	/**
	 * <p class="detail">
	 * 功能：更新登录用户信息
	 * </p>
	 * @param sysUserInfo
	 * @param sysUser
	 * @param memberUser
	 * @return
	 */
	@RequestMapping(value = "/updateUser.web", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj updateUser(SysUserInfo sysUserInfo,SysUser sysUser) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),ViewShowEnums.INFO_SUCCESS.getDetail());
		sysUser.setUserId(OperationContextHolder.getUserId());
		sysUserInfo.setUserId(OperationContextHolder.getUserId());
		if(StringUtil.isNullOrEmpty(sysUser.getUserName())){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("请输入登录名");
			return obj;
		}
		if(StringUtil.isNullOrEmpty(sysUser.getPassWord())){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("请输入登录密码");
			return obj;
		}
		if(!StringUtil.isNullOrEmpty(sysUser.getPassWord()) && sysUser.getPassWord().length()!=32){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("密码系统错误");
			return obj;
		}
//		if(!StringUtil.isNullOrEmpty(sysUserInfo.getRemark()) &&
//				sysUserInfo.getRemark().length()>200){
//			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
//			obj.setShowMessage("备注字数为200字(含)以内");
//			return obj;
//		}
//		if(!StringUtil.isNullOrEmpty(sysUserInfo.getEmail()) && 
//				!ValidateUtil.isValidEmail(sysUserInfo.getEmail())){
//			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
//			obj.setShowMessage("邮箱格式不正确");
//			return obj;
//		}
//		if(!StringUtil.isNullOrEmpty(sysUserInfo.getPhone()) && 
//				!ValidateUtil.isValidMobile(sysUserInfo.getPhone())){
//			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
//			obj.setShowMessage("手机号格式不正确");
//			return obj;
//		}
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("userId", sysUser.getUserId());                                             
//		UserDetailDto userDetail = sysUserInfoMapper.getUserDetail(map);
//		String oldUserName=userDetail.getUserName();
//		if(!oldUserName.equals(sysUser.getUserName())){
//			Map<String, Object> m = new HashMap<String, Object>();
//			m.put("userName", sysUser.getUserName());
//			List<String> isExistUserName=sysUserService.isUserNameExist(m);
//			if(null!=isExistUserName && !isExistUserName.isEmpty()){
//				obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
//				obj.setShowMessage("登录名已存在");
//				return obj;
//			}
//		}
		int count=sysUserService.updateUser(sysUserInfo, sysUser);
//		if(count<=0){
//			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
//			obj.setShowMessage("修改失败");
//			return obj;
//		}
		return obj;
	}

	/**
	 * <p class="detail">
	 * 功能：增加登录用户
	 * </p>
	 * @param sysUserInfo
	 * @param sysUser
	 * @return
	 */
	@RequestMapping(value = "/addUser.web", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObj addUser(SysUserInfo sysUserInfo,SysUser sysUser ) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),ViewShowEnums.INFO_SUCCESS.getDetail());
		if(StringUtil.isNullOrEmpty(sysUser.getUserName())){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("请输入登录名");
			return obj;
		}
		if(StringUtil.isNullOrEmpty(sysUser.getPassWord())){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("请输入登录密码");
			return obj;
		}
		if(!StringUtil.isNullOrEmpty(sysUser.getPassWord()) && sysUser.getPassWord().length()!=32){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("密码系统错误");
			return obj;
		}
//		if(!StringUtil.isNullOrEmpty(sysUserInfo.getRemark()) &&
//				sysUserInfo.getRemark().length()>200){
//			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
//			obj.setShowMessage("备注字数为200字(含)以内");
//			return obj;
//		}
//		if(!StringUtil.isNullOrEmpty(sysUserInfo.getEmail()) && 
//				!ValidateUtil.isValidEmail(sysUserInfo.getEmail())){
//			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
//			obj.setShowMessage("邮箱格式不正确");
//			return obj;
//		}
//		if(!StringUtil.isNullOrEmpty(sysUserInfo.getPhone()) && 
//				!ValidateUtil.isValidMobile(sysUserInfo.getPhone())){
//			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
//			obj.setShowMessage("手机号格式不正确");
//			return obj;
//		}
		Map<String,Object> m=new HashMap<String, Object>();
		m.put("userName", sysUser.getUserName());
		List<String> isExistUserName=sysUserService.isUserNameExist(m);
		if(null!=isExistUserName && !isExistUserName.isEmpty()){
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			obj.setShowMessage("登录名已存在");
			return obj;
		}
		int count=sysUserService.addUser(sysUserInfo, sysUser);
//		if(count<=0){
//			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
//			obj.setShowMessage("添加失败");
//			return obj;
//		}
		return obj;
	}

}
