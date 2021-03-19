package com.sshfortress.service.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sshfortress.common.beans.SysRoleMenu;
import com.sshfortress.dao.system.mapper.SysRoleMenuMapper;


@Service("sysRoleMenuService")
public class SysRoleMenuService {
	
	@Autowired
	SysRoleMenuMapper sysRoleMenuMapper;
	@Transactional
   public void add(Long roleId,Long[] menuIds)
   {
		List<SysRoleMenu> list = new ArrayList<SysRoleMenu>();
		for (Long menuId : menuIds) {
			
			SysRoleMenu sysRoleMenu = new SysRoleMenu();
			sysRoleMenu.setCreateTime(new Date());
			sysRoleMenu.setMenuId(menuId);
			sysRoleMenu.setRoleId(roleId);
			list.add(sysRoleMenu);
		}
		sysRoleMenuMapper.deleteByRoleId(roleId);
		sysRoleMenuMapper.insertBatch(list);
   }
}
