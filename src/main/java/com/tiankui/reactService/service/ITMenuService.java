package com.tiankui.reactService.service;

import java.util.List;
import java.util.Map;

import com.tiankui.reactService.entity.Role;
import com.tiankui.reactService.entity.TMenu;

public interface ITMenuService {
	
	List<TMenu> getAll();

	int sortMenu(Map<String, Object> map);

	TMenu getMenu(String id);

	int editMenu(String id, Map<String, Object> map);

	int addMenu(Map<String, Object> map);

	int delMenu(String id);

	List<TMenu> getRoleRight(List<Role> list);
}
