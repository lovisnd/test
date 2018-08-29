package com.tiankui.reactService.mapper;

import java.util.List;
import java.util.Map;

import com.tiankui.reactService.entity.TMenu;
import com.tiankui.reactService.entity.TMenuExample;

public interface TMenuMapper {
    List<TMenu> selectByExample(TMenuExample example);

    TMenu selectByPrimaryKey(String id);

	TMenu getMenuByExample(Map<String, Object> hashMap);

	int updateMenuSort(TMenu menu);

	int updateMenu(Map<String, Object> map);

	int getSortByPrenId(String parenId);

	int insertMenu(Map<String, Object> map);

	int delMenu(String id);

	List<TMenu> getRightByRole(String id);

	List<String> getMenuByPrentId(String parentId);
}