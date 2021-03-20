package com.sshfortress.service.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sshfortress.common.beans.SysUser;
import com.sshfortress.common.beans.SysUserInfo;
import com.sshfortress.common.beans.SysUserRole;
import com.sshfortress.common.enums.SexType;
import com.sshfortress.common.enums.UserFromEnum;
import com.sshfortress.common.enums.UserStatus;
import com.sshfortress.common.enums.UserTypeEnums;
import com.sshfortress.common.util.PrimaryKeyUtils;
import com.sshfortress.common.util.StringUtil;
import com.sshfortress.dao.system.mapper.SysUserMapper;
import com.sshfortress.dao.system.mapper.SysUserRoleMapper;
import com.sshfortress.dao.user.mapper.SysUserInfoMapper;

@Service("sysUserService")
public class SysUserService {
	@Autowired
	SysUserMapper sysUserMapper;
	@Autowired
	SysUserInfoMapper sysUserInfoMapper;
	@Autowired
	SysUserRoleMapper sysUserRoleMapper;
     
	@Transactional
	public void addUser(String userName, String passWord, Long roleIds[]) {
		
		final String userId = PrimaryKeyUtils.getPrimaryKey();
		SysUser sysUser = new SysUser();
		sysUser.setUserId(userId);
		sysUser.setUserName(userName);
		sysUser.setPassWord(passWord);
		sysUser.setStatus(UserStatus.OPEN.getCode());
		sysUser.setUserType(UserTypeEnums.SYSTEM_MANAGER.getCode());
		sysUser.setCreateTime(new Date());
		sysUser.setIsDelete(0);
		sysUserMapper.insertSelective(sysUser);

		SysUserInfo sysUserInfo = new SysUserInfo();
		sysUserInfo.setUserId(userId);
		sysUserInfo.setCreateTime(new Date());
		sysUserInfo.setUpdateTime(new Date());
		sysUserInfoMapper.insertSelective(sysUserInfo);

		
		List<SysUserRole> userRoles = new ArrayList<SysUserRole>();
		for (Long roleId : roleIds) {
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setUserId(userId);
			sysUserRole.setRoleId(roleId);
			userRoles.add(sysUserRole);
		}
		sysUserRoleMapper.insertBatchs(userRoles);
	}

	
	
	public SysUser queryByUserNameAndUserType(String userName, Integer userType) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userName", userName);
		params.put("userType", userType);
		return sysUserMapper.queryByParamsSingle(params);
	}
	public List<SysUser> queryByUserName(String userName, List<Integer> userTypes,Integer status) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userName", userName);
		if(!CollectionUtils.isEmpty(userTypes))
		{
			params.put("userTypes", userTypes);
		}
		if(status!=null)
		{
			params.put("status", status);
		}
		return sysUserMapper.queryByParamsList(params);
	}
	
	

	@Transactional
	public void updateUser(String userId,String userName,String passWord,Long roleIds[])
	{
		
		SysUser sysUser = new SysUser();
		sysUser.setUserId(userId);
		sysUser.setPassWord(passWord);
		sysUser.setUserName(userName);
		sysUserMapper.updateByPrimaryKeySelective(sysUser);
		
		SysUserInfo sysUserInfo = new SysUserInfo();
		sysUserInfo.setUserId(userId);
		sysUserInfo.setUpdateTime(new Date());
		sysUserInfoMapper.updateByPrimaryKeySelective(sysUserInfo);
		
		sysUserRoleMapper.delByUserId(userId);
		List<SysUserRole> userRoles = new ArrayList<SysUserRole>();
		for (Long roleId : roleIds) {
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setUserId(userId);
			sysUserRole.setRoleId(roleId);
			userRoles.add(sysUserRole);
		}
		sysUserRoleMapper.insertBatchs(userRoles);
		
	}
	
	@Transactional
	public void delUser(String userId)
	{

		sysUserMapper.deleteByPrimaryKey(userId);
		sysUserInfoMapper.deleteByPrimaryKey(userId);
		sysUserRoleMapper.delByUserId(userId);
	}
    
	/**获取权限的列表*/
	public List<Map<String,Object>> selectUser(Map<String,Object> params){
        
		List<Map<String,Object>> l=sysUserMapper.selectUserListPager(params);
		return l;
		
	}
	
	/**返回用户的信息*/
	public Map<String,Object> queryUserInfo(Map<String,Object> map){
		Map<String,Object> m=sysUserInfoMapper.queryUserInfo(map);
		return m;
	}
	
	/**
	 * 批量删除用户
	 */
	@Transactional
	public int delUser(String[] userId){
		for(int i=0;i<userId.length;i++){
			
			SysUser sysUser=new SysUser();
			sysUser.setUserId(userId[i]);
			sysUser.setIsDelete(1);
			int count=sysUserMapper.updateByPrimaryKeySelective(sysUser);
			
			SysUserInfo sysUserInfo=new SysUserInfo();
			sysUserInfo.setUserId(userId[i]);
			sysUserInfo.setIsDelete(1);
			int count2=sysUserInfoMapper.updateByPrimaryKeySelective(sysUserInfo);
			
			if(count>0 && count2>0){
				
			}else{
				return 0;
			}
		}
		return 1;
	}
	
	/**增加登录用户*/
	@Transactional
	public int addUser(SysUserInfo sysUserInfo,SysUser sysUser){
		//用户主表
//		final String userId = PrimaryKeyUtils.getPrimaryKey();
		sysUser.setStatus(UserStatus.OPEN.getCode());
		sysUser.setUserType(UserTypeEnums.SYSTEM_MANAGER.getCode());
		sysUser.setCreateTime(new Date());
		sysUser.setIsDelete(0);
		int count=sysUserMapper.insertSelective(sysUser);
		//用户详情表
		sysUserInfo.setUserId(sysUser.getUserId());
		sysUserInfo.setCreateTime(new Date());
		sysUserInfo.setUpdateTime(new Date());
		sysUserInfo.setIsDelete(0);
		int count2=sysUserInfoMapper.addUser(sysUserInfo);
		if(count>0 && count2>0){
			return 1;
		} 
		return 0;
	}
	
	/** web用户名是否存在  */
	public List<String> isUserNameExist(Map<String,Object> m){
		
		return sysUserMapper.isUserNameExist(m);
	}
	
	/**更新登录用户*/
	@Transactional
	public int updateUser(SysUserInfo sysUserInfo,SysUser sysUser){
		//主表
		int count=sysUserMapper.updateByPrimaryKeySelective(sysUser);
		
		//副表
		sysUserInfo.setUpdateTime(new Date());
		int count2=sysUserInfoMapper.updateByPrimaryKeySelective(sysUserInfo);
		
		if(count>0 && count2>0){
			return 1;
		} 
		return 0;
	}
}
