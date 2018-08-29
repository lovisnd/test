package com.tiankui.reactService.mapper;

import java.util.List;
import java.util.Map;

import com.tiankui.reactService.entity.Dict;
import com.tiankui.reactService.entity.DictExample;
import com.tiankui.reactService.entity.DictGroup;

public interface DictMapper {

	List<DictGroup> getListByTableName(Map<String, Object> map);

	List<String> getTabs();

	List<DictGroup> selectByExample(DictExample dictExample);

	List<Dict> selectByGroupId(String id);

	DictGroup selectByPrimaryKey(String id);

	int updateDept(Map<String, Object> map);

	int insertDictGroup(Map<String, Object> map);

	int delDept(String id);

	int getCount();

}
