package com.sshfortress.service.system;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshfortress.common.model.Menu;
import com.sshfortress.common.model.SysMenuDto;
import com.sshfortress.dao.system.mapper.SysMenuMapper;



@Service("sysMenuService")
public class SysMenuService {

	@Autowired
	SysMenuMapper sysMenuMapper;

	
	
	public List<Menu> selectByRoleIds(Long array[]) {
		List<SysMenuDto> list = sysMenuMapper.selectByRoleIds(array);
		List<Menu> lists = new ArrayList<Menu>();
		return menuConvertModel(list, lists);
	}

	
	private List<Menu> menuConvertModel(List<SysMenuDto> list, List<Menu> result) {
		List<SysMenuDto> listParent = new ArrayList<SysMenuDto>();
		List<SysMenuDto> listChild = new ArrayList<SysMenuDto>();
		
		for (SysMenuDto sysMenu : list) {
			if (sysMenu.getParentId() == 0) {
				listParent.add(sysMenu);
			} else {
				listChild.add(sysMenu);
			}
		}
		for (SysMenuDto sysMenu : listParent) {
			Menu menu = new Menu();
			BeanUtils.copyProperties(sysMenu, menu);
			
			recursionMenu(menu, listChild);
			result.add(menu);
		}

		return result;
	}

	
	private void recursionMenu(Menu menu, List<SysMenuDto> listChild) {
		if (menu != null) {
			List<Menu> child = new ArrayList<Menu>();
			for (SysMenuDto sysMenuChild : listChild) {
				if (menu.getId() == sysMenuChild.getParentId().longValue()) {				
					Menu menuChild = new Menu();
					BeanUtils.copyProperties(sysMenuChild, menuChild);
					recursionMenu(menuChild, listChild);
					child.add(menuChild);
				}
			}
			menu.setChildMenus(child);

		}
	}

}
