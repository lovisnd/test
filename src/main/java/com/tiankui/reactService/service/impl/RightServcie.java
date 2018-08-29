package com.tiankui.reactService.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tiankui.reactService.entity.RoleRight;
import com.tiankui.reactService.entity.TMenu;
import com.tiankui.reactService.mapper.RightMapper;
import com.tiankui.reactService.mapper.TMenuMapper;
import com.tiankui.reactService.service.IRightService;
import com.tiankui.reactService.util.ObjectUtil;
import com.tiankui.reactService.util.UUIDUtil;

@Service
public class RightServcie implements IRightService {

	@Autowired
	private RightMapper rightMapper;
	@Autowired
	private TMenuMapper menuMapper;

	@Override
	public List<String> getRightByRole(String roleId) {
		List<TMenu> list = menuMapper.getRightByRole(roleId);
		ArrayList<String> idList = new ArrayList<String>();
		for (TMenu menu : list) {
			idList.add(menu.getId());
		}
		return idList;
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	@Override
	public void addRoleRight(String roleId, String[] rights) {
		rightMapper.delRightByRoleId(roleId);
		//默认角色权限
		ArrayList<String> defaultList = new ArrayList<String>();
		defaultList.add("98f1f65eb32c11e699cdc860002a75d2");
		defaultList.add("195f784cc76749bda4f27d86a1cf1d96");
		defaultList.add("fc8228ba73914e4e813808ad52c00856");
		List<String> arrList = Arrays.asList(rights);
		List<String> rightList = new ArrayList<String>(arrList);
		List<String> sonList = new ArrayList<String>(arrList);
		rightList.addAll(defaultList);
		for (String right : rightList) {
			List<String> sonRight = menuMapper.getMenuByPrentId(right);
			if(ObjectUtil.isNotNull(sonRight)){
				sonList.addAll(sonRight);
			}
		}
		rightList.addAll(sonList);
		for (String right : rightList) {
			RoleRight roleRight = new RoleRight();
			roleRight.setId(UUIDUtil.getUUID());
			roleRight.setRoleId(roleId);
			roleRight.setMenuId(right);
			rightMapper.saveRight(roleRight);
		}
	}

}
