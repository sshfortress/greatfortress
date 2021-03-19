package com.sshfortress.dao.system.mapper;

import com.sshfortress.common.beans.SysLogin;

public interface SysLoginMapper {
	 
	 /**添加登录日志*/
	 int insert(SysLogin sl);
}
