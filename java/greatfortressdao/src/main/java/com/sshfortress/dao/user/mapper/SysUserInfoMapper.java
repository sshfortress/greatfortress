package com.sshfortress.dao.user.mapper;

import java.util.List;
import java.util.Map;

import com.sshfortress.common.base.BaseMapper;
import com.sshfortress.common.beans.MemberInfo;
import com.sshfortress.common.beans.SysUserInfo;
import com.sshfortress.common.beans.UserInfo;
import com.sshfortress.common.entity.TbUserInfo;
import com.sshfortress.common.model.UserDetailDto;


public interface SysUserInfoMapper extends BaseMapper<String,SysUserInfo>{
	
	
	int addUser(SysUserInfo sui);
	
	
	UserInfo  selecLogintUserInfoByParam(Map<String ,Object> map);
	
	UserInfo  selecttUserInfoByManyUserType(Map<String ,Object> map);
	
	List<MemberInfo>  selectMemberInfoListPager(Map<String ,Object> map);
    
	
	/**得到用户详情**/
	UserDetailDto getUserDetail(Map<String,Object> map);
	
	
	/**登录的时候获取用户信息**/
	Map<String,Object> queryUserInfo(Map<String,Object> map);
	
}