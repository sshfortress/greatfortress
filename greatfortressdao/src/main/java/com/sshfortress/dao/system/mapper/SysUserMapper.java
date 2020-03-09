package com.sshfortress.dao.system.mapper;

import java.util.List;
import java.util.Map;

import com.sshfortress.common.base.BaseMapper;
import com.sshfortress.common.beans.SysUser;

public interface SysUserMapper extends BaseMapper<String,SysUser> {
	List<SysUser> queryByParamsListPager(Map<String, Object> params);
	SysUser queryByParamsSingle(Map<String, Object> params);
	List<SysUser> queryByParamsList(Map<String, Object> params);
	int updateByUserNameAndUserType(Map<String, Object> params);
	
	//List<UserIdAndCid> queryUserIdByType(Integer type);
	List<Map<String,Object>> selectUserListPager(Map<String,Object> params);
    //获取权限列表
	
	/** web用户名是否存在  */
	List<String> isUserNameExist(Map<String,Object> m);
	
	String deleteUser(Map<String,Object> m);

}