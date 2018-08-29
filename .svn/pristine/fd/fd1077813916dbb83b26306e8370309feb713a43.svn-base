package com.tiankui.reactService.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiankui.reactService.entity.Result;
import com.tiankui.reactService.service.IRightService;

@Controller
@RequestMapping(value = "/api/system/right")
public class RightController {
	@Autowired
	private IRightService rightService;

	@ResponseBody
	@RequestMapping(value = "/role/right/list")
	public Result<List<String>> getRoleRight(@RequestParam Map<String,Object> map){
		Result<List<String>> result = new Result<List<String>>();
		List<String> rights = rightService.getRightByRole(map.get("roleId").toString());
		result.setData(rights);
		result.setStatus(1);
		result.setMessage("查询成功！");
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/role/right/add")
	public Result<String> saveRoleRight(@RequestBody Map<String,Object> map){
		String roleId = map.get("roleId").toString();
		String obj = map.get("rights").toString();
		String[] rights = obj.substring(1, obj.length()-1).split(", ");
		rightService.addRoleRight(roleId,rights);
		Result<String> result = new Result<String>();
		result.setMessage("角色权限配置成功！");
		result.setStatus(1);
		return result;
	}
	
}
