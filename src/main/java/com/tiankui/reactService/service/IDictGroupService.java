package com.tiankui.reactService.service;

import java.util.List;
import java.util.Map;

import com.tiankui.reactService.entity.Dict;

public interface IDictGroupService {
	List<Dict> getTreeDataList(String id);

	int delDict(String id);

	Dict getDict(String id);

	int editDict(String id, Map<String, Object> map);

	int addDict(String pid,Map<String, Object> map);
}
