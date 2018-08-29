package com.tiankui.reactService.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiankui.reactService.entity.DateOrder;
import com.tiankui.reactService.entity.Result;
import com.tiankui.reactService.entity.TSyslog;
import com.tiankui.reactService.enums.LogLevel;
import com.tiankui.reactService.enums.LogType;
import com.tiankui.reactService.service.IJkWorkOrderService;
import com.tiankui.reactService.service.IStatisticService;
import com.tiankui.reactService.service.ISyslogService;
import com.tiankui.reactService.util.ExcelUtils;
import com.tiankui.reactService.util.IPUtil;
import com.tiankui.reactService.util.ObjectUtil;
/**
 * @author zhangmingrui
 * @ClassName: StatisticController
 * @Description: TODO 集客工单数据分析接入层
 * @date 2018年8月15日
 */
@Controller
@RequestMapping(value = "/api/statistic")
public class StatisticController {
	
	@Autowired
	private IStatisticService statisticService;
	@Autowired
	private IJkWorkOrderService jkworkOrderService;
	@Autowired
	public ISyslogService syslogService;
	
	@ResponseBody
	@RequestMapping(value = "/online")
	public Map<String,Object> getOnline(){
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> map = statisticService.getOnline();
		result.put("pillarData", map.get("pillarData"));
		result.put("cakeData", map.get("cakeData"));
		result.put("status", 1);
		result.put("message", "查询成功");
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deviceType")
	public Map<String,Object> getDeviceType(){
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> map = statisticService.getDeviceType();
		result.put("deviceData", map.get("deviceData"));
		result.put("deviceDescData", map.get("deviceDescData"));
		result.put("status", 1);
		result.put("message", "查询成功");
		return result;
	}
	/**
	 * 查询所有工单故障类型数量
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/QueryFaultType")
	public Result<HashMap<String, Object>> queryFaultType(HttpServletRequest request, @RequestParam String token,@RequestBody Map<String,Object> map){
		   HashMap<String, Object> list = jkworkOrderService.queryFaultType(map);
		   Result<HashMap<String, Object>> result = new Result<HashMap<String, Object>>();
		   result.setData(list);
	       result.setStatus(1);
	       result.setMessage("查询成功！");
	        TSyslog tSyslog = new TSyslog(LogType.DATA_QUERY_SUCCESS.getIndex(), LogLevel.INFO.getIndex(),
					"故障列表数据查询成功！");
	    	syslogService.insert(tSyslog,token,IPUtil.getIpAdrress(request));
	       return result; 
	}
	/**
	 * 查询所有区域故障数量
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/QueryAreaFaultType")
	public Result<HashMap<String, Object>> QueryAreaFaultType(HttpServletRequest request, @RequestParam String token,@RequestBody Map<String,Object> map){
		   HashMap<String, Object> list = jkworkOrderService.queryAreaFaultType(map);
		   Result<HashMap<String, Object>> result = new Result<HashMap<String, Object>>();
		   result.setData(list);
	       result.setStatus(1);
	       result.setMessage("查询成功！");
	        TSyslog tSyslog = new TSyslog(LogType.DATA_QUERY_SUCCESS.getIndex(), LogLevel.INFO.getIndex(),
					"故障列表数据查询成功！");
	    	syslogService.insert(tSyslog,token,IPUtil.getIpAdrress(request));
	       return result; 
	}
	/**
	 * 查询区域维护组故障平均处理时长
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/orderHandler")
	public Result<HashMap<String, Object>> QueryOrderHandler(HttpServletRequest request, @RequestParam String token,@RequestBody Map<String,Object> map){
		   HashMap<String, Object> list = jkworkOrderService.queryOrderHandler(map);
		   Result<HashMap<String, Object>> result = new Result<HashMap<String, Object>>();
		   result.setData(list);
	       result.setStatus(1);
	       result.setMessage("查询成功！");
	       return result; 
	}
	/**
	 * 查询区域维护组故障平均处理时长
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/successLv")
	public Result<HashMap<String, Object>> QuerySuccessLv(HttpServletRequest request, @RequestParam String token,@RequestBody Map<String,Object> map){
		   HashMap<String, Object> list = jkworkOrderService.querySuccessLv(map);
		   Result<HashMap<String, Object>> result = new Result<HashMap<String, Object>>();
		   result.setData(list);
	       result.setStatus(1);
	       result.setMessage("查询成功！");
	       return result; 
	}
	/**
	 * 查询同一客户故障重复率
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/repeatAccount")
	public Result<HashMap<String, Object>> QueryRepeatAccount(HttpServletRequest request, @RequestParam String token,@RequestBody Map<String,Object> map){
		   HashMap<String, Object> list = jkworkOrderService.queryRepeatAccount(map);
		   Result<HashMap<String, Object>> result = new Result<HashMap<String, Object>>();
		   result.setData(list);
	       result.setStatus(1);
	       result.setMessage("查询成功！");
	       return result; 
	}
	/**
	 * 导出工单信息
	 *
	 * @param map
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/export")
	public void export(@RequestParam Map<String,Object> paramMap, @RequestParam int type, HttpServletRequest request, HttpServletResponse response) {
		String[] faultTypeExcelHeader = { "故障类型", "工单数量" };
		String[] faultAreaExcelHeader = { "故障区域", "工单数量" };
		String[] faultTimeExcelHeader = { "故障维护组", "平均处理时长(h)" };
		String[] faultSuccessExcelHeader = { "故障维护组", "成功率(%)" };
		String[] repeatAccountExcelHeader = { "上网账号", "投诉次数(次)" };
		String[] faultLvExcelHeader = { "故障维护组", "成功数量", "成功率(%)" };
		String[] faultTypeTitles = { "x", "y" };
		String[] faultAreaTitles = { "x", "y" };
		String[] faultTimeTitles = { "x", "time" };
		String[] faultSuccessTitles = { "x", "FAULTSUCCESSLV" };
		String[] repeatAccountTitles = { "x", "y" };
		String[] faultLvTitles = { "x", "y", "FAULTSUCCESSLV" };
		int[] ds_format = { 1, 1 };
		int[] FaultFormat = { 1, 1, 1 };
		int[] widths = { 256 * 20, 256 * 20 };
		int[] FaultWidths = { 256 * 20, 256 * 20, 256 * 20 };
		HashMap<String, Object> resultMap = new HashMap<String,Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<DateOrder> object = new ArrayList<DateOrder>();
		try {
			switch (type) {
			case 1:
				resultMap = jkworkOrderService.queryFaultType(paramMap);
				object = (List<DateOrder>)resultMap.get("tableData");
				for (DateOrder dateOrder : object) {
					Map<String, Object> toMap = ObjectUtil.objectToMap(dateOrder);
					list.add(toMap);
				}
				ExcelUtils.export("故障类型统计", "故障类型", faultTypeExcelHeader, faultTypeTitles, ds_format, widths, list,
						request, response);
				break;
			case 2:
				resultMap = jkworkOrderService.queryAreaFaultType(paramMap);
				object = (List<DateOrder>)resultMap.get("tableData");
				for (DateOrder dateOrder : object) {
					Map<String, Object> toMap = ObjectUtil.objectToMap(dateOrder);
					list.add(toMap);
				}
				ExcelUtils.export("故障区域统计", "故障区域", faultAreaExcelHeader, faultAreaTitles, ds_format, widths, list,
						request, response);
				break;
			case 3:
				resultMap = jkworkOrderService.queryOrderHandler(paramMap);
				list = (List<Map<String, Object>>) resultMap.get("tableData");
				ExcelUtils.export("故障处理时长统计", "故障处理时长", faultTimeExcelHeader, faultTimeTitles, ds_format, widths, list,
						request, response);
				break;
			case 4:
				resultMap = jkworkOrderService.querySuccessLv(paramMap);
				list = (List<Map<String, Object>>) resultMap.get("tableData");
				ExcelUtils.export("成功率统计", "成功率", faultSuccessExcelHeader, faultSuccessTitles, ds_format, widths, list,
						request, response);
				break;
			case 5:
				resultMap = jkworkOrderService.queryRepeatAccount(paramMap);
				object = (List<DateOrder>)resultMap.get("tableData");
				for (DateOrder dateOrder : object) {
					Map<String, Object> toMap = ObjectUtil.objectToMap(dateOrder);
					list.add(toMap);
				}
				ExcelUtils.export("重复投诉统计", "投诉次数", repeatAccountExcelHeader, repeatAccountTitles, ds_format, widths, list,
						request, response);
				break;
			case 6:
				resultMap = jkworkOrderService.querySuccessLv(paramMap);
				list = (List<Map<String, Object>>) resultMap.get("tableData");
				ExcelUtils.export("故障率统计", "故障率", faultLvExcelHeader, faultLvTitles, FaultFormat, FaultWidths, list,
						request, response);
				break;
			default:
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
