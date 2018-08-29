package com.tiankui.reactService.service;

import java.util.List;
import java.util.Map;

import com.tiankui.reactService.entity.DictGroup;

public interface IDictService {

	List<DictGroup> getTableInfo(Map<String, Object> map);

	List<String> getTabs();

	List<DictGroup> getAll();

	DictGroup getDictGroup(String id);

	int addDictGroup(Map<String, Object> map);

	int editDictGroup(String id, Map<String, Object> map);

	int delDictGroup(String id);

	int getCount();
}
