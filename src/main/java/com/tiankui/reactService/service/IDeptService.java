package com.tiankui.reactService.service;

import java.util.List;
import java.util.Map;

import com.tiankui.reactService.entity.Dept;

public interface IDeptService {

	public List<Dept> getList(Map<String, Object> map);

	public List<Dept> getAll();

	public Dept getDept(String id);

	public int editDept(String id, Map<String, Object> map);

	public int addDept(Map<String, Object> map);

	public int delDept(String id);

}
