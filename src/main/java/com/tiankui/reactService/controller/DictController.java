package com.tiankui.reactService.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiankui.reactService.entity.DictGroup;
import com.tiankui.reactService.entity.Result;
import com.tiankui.reactService.entity.ResultArray;
import com.tiankui.reactService.entity.TSyslog;
import com.tiankui.reactService.enums.LogLevel;
import com.tiankui.reactService.enums.LogType;
import com.tiankui.reactService.service.IDictService;
import com.tiankui.reactService.service.ISyslogService;
import com.tiankui.reactService.util.IPUtil;

@Controller
@RequestMapping(value = "/api/system/dict")
public class DictController {
	
	@Autowired
	private IDictService dictService;
	@Autowired
	public ISyslogService syslogService;
	
	@ResponseBody
	@RequestMapping( value = "/list")
	public Result<List<DictGroup>> getList(HttpServletRequest request, @RequestParam String token,@RequestBody Map<String, Object> map){
		List<DictGroup> list = dictService.getTableInfo(map);
		int count = dictService.getCount();
		Result<List<DictGroup>> result = new Result<List<DictGroup>>();
		result.setData(list);
		result.setCount(count);
		result.setStatus(1);
		result.setMessage("查询成功！");
       /* TSyslog tSyslog = new TSyslog(LogType.DATA_QUERY_SUCCESS.getIndex(), LogLevel.INFO.getIndex(),
				"数字字典列表数据查询成功！");
    	syslogService.insert(tSyslog,token,IPUtil.getIpAdrress(request));*/
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/query")
	public ResultArray<DictGroup> sort(@RequestParam String id) {
		DictGroup dictGroup = dictService.getDictGroup(id);
		ResultArray<DictGroup> result = new ResultArray<DictGroup>();
		DictGroup[] dicts = { dictGroup };
		result.setStatus(1);
		result.setMessage("查询成功");
		result.setData(dicts);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/add")
	public Result<Integer> addDictGroup(HttpServletRequest request, @RequestParam String token,@RequestBody Map<String, Object> map) {
		int resultNum = dictService.addDictGroup(map);
		Result<Integer> result = new Result<Integer>();
		if (resultNum == 1) {
			result.setStatus(1);
			result.setMessage("添加成功");
			result.setData(resultNum);
	        TSyslog tSyslog = new TSyslog(LogType.DATA_ADD_SUCCESS.getIndex(), LogLevel.INFO.getIndex(),
					"数字字典列表数据添加成功！添加的字典名称："+map.get("groupName"));
	    	syslogService.insert(tSyslog,token,IPUtil.getIpAdrress(request));
		} else {
			result.setStatus(0);
			result.setMessage("添加失败");
			result.setData(resultNum);
	        TSyslog tSyslog = new TSyslog(LogType.DATA_ADD_FAIL.getIndex(), LogLevel.INFO.getIndex(),
					"数字字典列表数据添加失败！");
	    	syslogService.insert(tSyslog,token,IPUtil.getIpAdrress(request));
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit")
	public Result<Integer> editDictGroup(HttpServletRequest request, @RequestParam String token,@RequestParam String id, @RequestBody Map<String, Object> map) {
		int resultNum = dictService.editDictGroup(id, map);
		Result<Integer> result = new Result<Integer>();
		if (resultNum == 1) {
			result.setStatus(1);
			result.setMessage("修改成功");
			result.setData(resultNum);
	        TSyslog tSyslog = new TSyslog(LogType.DATA_UPDATE_SUCCESS.getIndex(), LogLevel.INFO.getIndex(),
					"数字字典列表数据修改成功！");
	    	syslogService.insert(tSyslog,token,IPUtil.getIpAdrress(request));
		} else {
			result.setStatus(0);
			result.setMessage("修改失败");
			result.setData(resultNum);
	        TSyslog tSyslog = new TSyslog(LogType.DATA_UPDATE_FAIL.getIndex(), LogLevel.INFO.getIndex(),
					"数字字典列表数据修改失败！");
	    	syslogService.insert(tSyslog,token,IPUtil.getIpAdrress(request));
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/del")
	public Result<Integer> delDept(HttpServletRequest request, @RequestParam String token,@RequestParam String id) {
		DictGroup dictGroup = dictService.getDictGroup(id);
		int resultNum = dictService.delDictGroup(id);
		Result<Integer> result = new Result<Integer>();
		if (resultNum == 1) {
			result.setStatus(1);
			result.setMessage("删除成功");
			result.setData(resultNum);
	        TSyslog tSyslog = new TSyslog(LogType.DATA_DELETE_SUCCESS.getIndex(), LogLevel.INFO.getIndex(),
					"数字字典列表数据删除成功！被删除的字典名称:"+dictGroup.getGroupName());
	    	syslogService.insert(tSyslog,token,IPUtil.getIpAdrress(request));
		} else {
			result.setStatus(0);
			result.setMessage("删除失败");
			result.setData(resultNum);
	        TSyslog tSyslog = new TSyslog(LogType.DATA_DELETE_FAIL.getIndex(), LogLevel.INFO.getIndex(),
					"数字字典列表数据删除失败！");
	    	syslogService.insert(tSyslog,token,IPUtil.getIpAdrress(request));
		}
		return result;
	}
	
}
