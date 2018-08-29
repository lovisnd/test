package com.tiankui.reactService.mapper;

import java.util.List;
import java.util.Map;

import com.tiankui.reactService.entity.Dict;
import com.tiankui.reactService.entity.DictGroup;
import com.tiankui.reactService.entity.DictGroupExample;

public interface DictGroupMapper {

	List<DictGroup> selectByExample(DictGroupExample dictGroupExample);
	
	String getDictByCode(String code);

	DictGroup getById(String id);

	List<Dict> selectByPrimaryKey(String id);

	List<Dict> selectAllDict();

	int delDict(String id);

	Dict selectById(String id);

	int updateDict(Map<String, Object> map);

	int insertDict(Map<String, Object> map);

}
