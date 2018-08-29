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
import com.tiankui.reactService.entity.ResultArray;
import com.tiankui.reactService.entity.TMenu;
import com.tiankui.reactService.service.ITMenuService;

@Controller
@RequestMapping(value = "/api/system/menu")
public class TMenuController {

	@Autowired
	private ITMenuService menuService;

	@ResponseBody
	@RequestMapping(value = "/list")
	public Result<List<TMenu>> getAll() {
		List<TMenu> list = menuService.getAll();
		Result<List<TMenu>> result = new Result<List<TMenu>>();
		result.setData(list);
		result.setStatus(1);
		result.setMessage("查询成功");
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/position")
	public Result<Integer> sort(@RequestBody Map<String, Object> map) {
		int sortMenu = menuService.sortMenu(map);
		Result<Integer> result = new Result<Integer>();
		if (sortMenu == 1) {
			result.setStatus(1);
			result.setMessage("修改成功");
		} else {
			result.setStatus(0);
			result.setMessage("修改失败");
		}
		result.setData(sortMenu);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/query")
	public ResultArray<TMenu> sort(@RequestParam String id) {
		TMenu menu = menuService.getMenu(id);
		ResultArray<TMenu> result = new ResultArray<TMenu>();
		TMenu[] menus = { menu };
		result.setStatus(1);
		result.setMessage("查询成功");
		result.setData(menus);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/edit")
	public Result<Integer> editMenu(@RequestParam String id, @RequestBody Map<String, Object> map) {
		int resultNum = menuService.editMenu(id, map);
		Result<Integer> result = new Result<Integer>();
		if (resultNum == 1) {
			result.setStatus(1);
			result.setMessage("修改成功");
			result.setData(resultNum);
		} else {
			result.setStatus(0);
			result.setMessage("修改失败");
			result.setData(resultNum);
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/add")
	public Result<Integer> addMenu(@RequestBody Map<String, Object> map) {
		int resultNum = menuService.addMenu(map);
		Result<Integer> result = new Result<Integer>();
		if (resultNum == 1) {
			result.setStatus(1);
			result.setMessage("添加成功");
			result.setData(resultNum);
		} else {
			result.setStatus(0);
			result.setMessage("添加失败");
			result.setData(resultNum);
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/del")
	public Result<Integer> delMenu(@RequestParam String id) {
		int resultNum = menuService.delMenu(id);
		Result<Integer> result = new Result<Integer>();
		if (resultNum == 1) {
			result.setStatus(1);
			result.setMessage("删除成功");
			result.setData(resultNum);
		} else {
			result.setStatus(0);
			result.setMessage("删除失败");
			result.setData(resultNum);
		}
		return result;
	}
	
}
