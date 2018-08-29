package com.tiankui.reactService.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiankui.reactService.entity.Result;
import com.tiankui.reactService.entity.TSyslog;
import com.tiankui.reactService.service.ISyslogService;

@Controller
@RequestMapping("/api")
public class SyslogController {
	
	@Autowired
	public ISyslogService syslogService;

	@ResponseBody
    @RequestMapping(value = "/system/log")
    public Result<List<TSyslog>> getUserList(@RequestBody Map<String,Object> map){
		List<TSyslog> logList = syslogService.getAllLog(map);
		int count = syslogService.getCountLog(map);
    	Result<List<TSyslog>> result = new Result<List<TSyslog>>();
    	result.setData(logList);
    	result.setCount(count);
    	result.setMessage("查询成功！");
    	result.setStatus(1);
    	return result;
    }
	
	@ResponseBody
	@RequestMapping(value = "/system/log/type")
	public Result<Map<String,Object>> selectLogType(){
		List<Map<String,Object>> typeList = syslogService.selectLogType();
		List<Map<String,Object>> levelList = syslogService.selectLogLevel();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("type", typeList);
		resultMap.put("level", levelList);
		Result<Map<String,Object>> result = new Result<Map<String,Object>>();
		result.setData(resultMap);
		result.setMessage("查询成功！");
		result.setStatus(1);
		return result;
	}
	
}
