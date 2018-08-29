package com.tiankui.reactService.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tiankui.reactService.entity.Dept;
import com.tiankui.reactService.entity.DeptExample;
import com.tiankui.reactService.mapper.DeptMapper;
import com.tiankui.reactService.service.IDeptService;
import com.tiankui.reactService.util.ObjectUtil;
import com.tiankui.reactService.util.UUIDUtil;

@Service
public class DeptService implements IDeptService {

	@Autowired
	private DeptMapper deptMapper;

	@Override
	public List<Dept> getList(Map<String, Object> map) {
		List<Dept> list = deptMapper.getListByExample(map);
		return list;
	}

	@Override
	public List<Dept> getAll() {
		DeptExample deptExample = new DeptExample();
		deptExample.setOrderByClause("code asc");
		List<Dept> list = deptMapper.selectByExample(deptExample);
		for (Dept dept : list) {
			if (dept.getParentId() == null) {
				dept.setParentId("");
			}
		}
		return list;
	}

	@Override
	public Dept getDept(String id) {
		Dept dept = deptMapper.selectByPrimaryKey(id);
		return dept;
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	@Override
	public int editDept(String id, Map<String, Object> map) {
		map.put("id", id);
		long time = new Date().getTime();
		map.put("updateTime", time);
		int result = deptMapper.updateDept(map);
		return result;
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	@Override
	public int addDept(Map<String, Object> map) {
		int sort = 0;
		String code = "";
		String uuid = UUIDUtil.getUUID();
		map.put("id", uuid);
		map.put("deleted", 0);
		String parentId = ObjectUtil.isNull(map.get("parentId")) ? "" : map.get("parentId").toString();
		if (parentId != null && parentId != "") {
			sort = deptMapper.getSortByParentId(parentId);
			code = deptMapper.getCodeByParentId(parentId);
			if (ObjectUtil.isNull(code)) {
				code = deptMapper.getCodeById(parentId);
				map.put("code", code + "A01");
			} else {
				String pre = code.substring(0, code.length() - 2);
				int valueOf = Integer.valueOf(code.substring(code.length() - 2, code.length()));
				int suf = valueOf + 1;
				int loop = valueOf + 1;
				int count = 0;
				while (loop > 0) {
					loop = loop / 10;
					count++;
				}
				map.put("code", pre + (count==1 ? "0"+suf : suf));
			}
		}else{
			code = deptMapper.getCodeByNullId();
			String pre = code.substring(0, code.length() - 2);
			char[] cs = pre.toCharArray();
			int index = (int)cs[0]+1;
			map.put("code", (char)index+"01");
		}
		map.put("sort", ObjectUtil.isNull(sort) ? 0 : sort + 1);
		map.put("createTime", new Date().getTime());
		int result = deptMapper.insertDept(map);
		return result;
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	@Override
	public int delDept(String id) {
		int result = deptMapper.delDept(id);
		return result;
	}
}
