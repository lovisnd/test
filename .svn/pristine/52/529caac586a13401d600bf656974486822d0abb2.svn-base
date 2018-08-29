package com.tiankui.reactService.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tiankui.reactService.entity.Dict;
import com.tiankui.reactService.mapper.DictGroupMapper;
import com.tiankui.reactService.service.IDictGroupService;
import com.tiankui.reactService.util.UUIDUtil;

@Service
public class DictGroupService implements IDictGroupService {

	@Autowired
	private DictGroupMapper dictGroupMapper;
	
	@Override
	public List<Dict> getTreeDataList(String id) {
		List<Dict> list = dictGroupMapper.selectByPrimaryKey(id);
		return list;
	}
	
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	@Override
	public int delDict(String id) {
		int result = dictGroupMapper.delDict(id);
		return result;
	}

	@Override
	public Dict getDict(String id) {
		Dict dict = dictGroupMapper.selectById(id);
		return dict;
	}
	
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	@Override
	public int editDict(String id, Map<String, Object> map) {
		map.put("id", id);
		long time = new Date().getTime();
		map.put("createTime", time);
		int result = dictGroupMapper.updateDict(map);
		return result;
	}
	
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	@Override
	public int addDict(String pid,Map<String, Object> map) {
		String uuid = UUIDUtil.getUUID();
		map.put("id", uuid);
		String dictGroupId = pid;
		map.put("dictGroupId",dictGroupId);
		map.put("createTime", new Date().getTime());
		int result = dictGroupMapper.insertDict(map);
		return result;
	}

}
