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

import com.tiankui.reactService.entity.Dept;
import com.tiankui.reactService.entity.Result;
import com.tiankui.reactService.entity.ResultArray;
import com.tiankui.reactService.entity.TSyslog;
import com.tiankui.reactService.enums.LogLevel;
import com.tiankui.reactService.enums.LogType;
import com.tiankui.reactService.service.IDeptService;
import com.tiankui.reactService.service.ISyslogService;
import com.tiankui.reactService.util.IPUtil;
/**
 * @author joke
 * 组织机构控制器
 */
@Controller
@RequestMapping("/api/system/dept")
public class DeptController {

	@Autowired
	private IDeptService deptService;
	
	@Autowired
	public ISyslogService syslogService;	
	
	@ResponseBody
	@RequestMapping(value = "/list")
	public Result<List<Dept>> getList(HttpServletRequest request, @RequestParam String token,@RequestParam Map<String, Object> map){
		Result<List<Dept>> result = new Result<List<Dept>>();
		List<Dept> list = deptService.getAll();
		result.setData(list);
		result.setStatus(1);
		result.setMessage("查询成功");
        TSyslog tSyslog = new TSyslog(LogType.DATA_QUERY_SUCCESS.getIndex(), LogLevel.INFO.getIndex(),
				"组织机构列表数据查询成功！");
    	syslogService.insert(tSyslog,token,IPUtil.getIpAdrress(request));
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/query")
	public ResultArray<Dept> sort(@RequestParam String id) {
		Dept dept = deptService.getDept(id);
		ResultArray<Dept> result = new ResultArray<Dept>();
		Dept[] depts = { dept };
		result.setStatus(1);
		result.setMessage("查询成功");
		result.setData(depts);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit")
	public Result<Integer> editMenu(HttpServletRequest request, @RequestParam String token,@RequestParam String id, @RequestBody Map<String, Object> map) {
		int resultNum = deptService.editDept(id, map);
		Result<Integer> result = new Result<Integer>();
		if (resultNum == 1) {
			result.setStatus(1);
			result.setMessage("修改成功");
			result.setData(resultNum);
	        TSyslog tSyslog = new TSyslog(LogType.DATA_UPDATE_SUCCESS.getIndex(), LogLevel.INFO.getIndex(),
					"组织机构数据修改成功！");
	    	syslogService.insert(tSyslog,token,IPUtil.getIpAdrress(request));
		} else {
			result.setStatus(0);
			result.setMessage("修改失败");
			result.setData(resultNum);
	        TSyslog tSyslog = new TSyslog(LogType.DATA_UPDATE_FAIL.getIndex(), LogLevel.INFO.getIndex(),
					"组织机构数据修改失败！");
	    	syslogService.insert(tSyslog,token,IPUtil.getIpAdrress(request));
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/add")
	public Result<Integer> addDept(HttpServletRequest request, @RequestParam String token,@RequestBody Map<String, Object> map) {
		int resultNum = deptService.addDept(map);
		Result<Integer> result = new Result<Integer>();
		if (resultNum == 1) {
			result.setStatus(1);
			result.setMessage("添加成功");
			result.setData(resultNum);
	        TSyslog tSyslog = new TSyslog(LogType.DATA_ADD_SUCCESS.getIndex(), LogLevel.INFO.getIndex(),
					"组织机构数据添加成功！");
	    	syslogService.insert(tSyslog,token,IPUtil.getIpAdrress(request));
		} else {
			result.setStatus(0);
			result.setMessage("添加失败");
			result.setData(resultNum);
	        TSyslog tSyslog = new TSyslog(LogType.DATA_ADD_FAIL.getIndex(), LogLevel.INFO.getIndex(),
					"组织机构数据添加失败！");
	    	syslogService.insert(tSyslog,token,IPUtil.getIpAdrress(request));
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/del")
	public Result<Integer> delDept(HttpServletRequest request, @RequestParam String token,@RequestParam String id) {
		int resultNum = deptService.delDept(id);
		Result<Integer> result = new Result<Integer>();
		if (resultNum == 1) {
			result.setStatus(1);
			result.setMessage("删除成功");
			result.setData(resultNum);
	        TSyslog tSyslog = new TSyslog(LogType.DATA_DELETE_SUCCESS.getIndex(), LogLevel.INFO.getIndex(),
					"组织机构数据删除成功！");
	    	syslogService.insert(tSyslog,token,IPUtil.getIpAdrress(request));
		} else {
			result.setStatus(0);
			result.setMessage("删除失败");
			result.setData(resultNum);
	        TSyslog tSyslog = new TSyslog(LogType.DATA_DELETE_FAIL.getIndex(), LogLevel.INFO.getIndex(),
					"组织机构数据删除失败！");
	    	syslogService.insert(tSyslog,token,IPUtil.getIpAdrress(request));
		}
		return result;
	}
	
}
