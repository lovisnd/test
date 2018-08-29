package com.tiankui.reactService.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiankui.reactService.entity.Result;
import com.tiankui.reactService.service.IWarnService;
import com.tiankui.reactService.util.ExcelUtils;

@Controller
@RequestMapping(value = "/api/system/warn")
public class WarnController {

	@Autowired
	private IWarnService warnService;
	
	@ResponseBody
	@RequestMapping(value = "/list")
	public Result<List<Map<String,Object>>> getList(@RequestParam Map<String,Object> map){
		Result<List<Map<String,Object>>> result = new Result<List<Map<String,Object>>>();
		List<Map<String,Object>> list = warnService.getListByMap(map);
		result.setData(list);
		result.setCount(list.size());
		result.setMessage("查询成功！");
		result.setStatus(1);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/pics")
	public Map<String,Object> getPics(){
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> map = warnService.getPics();
		result.put("firstData", map.get("firstData"));
		result.put("secondData", map.get("secondData"));
		result.put("thirdData", map.get("thirdData"));
		result.put("fourthData", map.get("fourthData"));
		result.put("status", 1);
		result.put("message", "查询成功");
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/export")
	public void export(@RequestParam Map<String,Object> map,HttpServletRequest request,HttpServletResponse response){
		String[] excelHeader = { "告警来源", "告警对象", "告警名称", "所属业务", "对象ip", "告警类型", "告警等级", "告警信息", "告警触发时间", "是否确认"};
		String[] ds_titles = {"warnSource", "warnObject", "warnName", "warnBusiness", "objIP", "warnType", "warnLevel", "warnMessage", "warnTime", "confirm"};  
		int[] ds_format = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
		int[] widths = { 256*12, 256*12, 256*20, 256*25, 256*20, 256*12, 256*12, 256*60, 256*16, 256*12};
		List<Map<String,Object>> list = warnService.getListByMap(map);
		try {
			ExcelUtils.export("告警表", "告警", excelHeader, ds_titles, ds_format, widths, list, request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
