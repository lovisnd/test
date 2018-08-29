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

import com.tiankui.reactService.entity.Result;
import com.tiankui.reactService.service.IHWStatisticService;
import com.tiankui.reactService.util.ExcelUtils;

/**
 * 家庭客户工单统计
 * 
 * @ClassName: HomeWorkOrderStatisticController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhouao
 * @date 2018年8月13日
 *
 */
@Controller
@RequestMapping(value = "/api/statistic/homeWorkOrder")
public class HWStatisticController {

	@Autowired
	private IHWStatisticService hwStatisticService;

	/**
	 * 故障类型统计分析 @Title: getFaultType @Description:
	 * TODO(这里用一句话描述这个方法的作用) @param @return 参数 @return Map<String,Object>
	 * 返回类型 @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/faultType")
	public Result<HashMap<String, Object>> getFaultType(@RequestBody Map<String,Object> paramMap) {
		Result<HashMap<String, Object>> result = new Result<HashMap<String, Object>>();
		HashMap<String, Object> map = hwStatisticService.getFaultType(paramMap);
		result.setData(map);
		result.setStatus(1);
		result.setMessage("查询成功");
		return result;
	}
	
	/**
	 * 故障区域统计分析 @Title: getOnline @Description:
	 * TODO(这里用一句话描述这个方法的作用) @param @return 参数 @return Map<String,Object>
	 * 返回类型 @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/faultArea")
	public Result<HashMap<String, Object>> getFaultArea(@RequestBody Map<String,Object> paramMap) {
		Result<HashMap<String, Object>> result = new Result<HashMap<String, Object>>();
		HashMap<String, Object> map = hwStatisticService.getFaultArea(paramMap);
		result.setData(map);
		result.setStatus(1);
		result.setMessage("查询成功");
		return result;
	}
	
	/**
	 * 故障装维人员处理时长统计分析 @Title: getOrderHandler @Description:
	 * TODO(这里用一句话描述这个方法的作用) @param @return 参数 @return Map<String,Object>
	 * 返回类型 @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/orderHandler")
	public Result<HashMap<String, Object>> getOrderHandler(@RequestBody Map<String,Object> paramMap) {
		Result<HashMap<String, Object>> result = new Result<HashMap<String, Object>>();
		HashMap<String, Object> map = hwStatisticService.getOrderhandler(paramMap);
		result.setData(map);
		result.setStatus(1);
		result.setMessage("查询成功");
		return result;
	}

	/**
	 * 故障装维人员处理成功率统计分析 @Title: getSuccessLv @Description:
	 * TODO(这里用一句话描述这个方法的作用) @param @return 参数 @return Map<String,Object>
	 * 返回类型 @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/successLv")
	public Result<HashMap<String, Object>> getSuccessLv(@RequestBody Map<String,Object> paramMap) {
		Result<HashMap<String, Object>> result = new Result<HashMap<String, Object>>();
		HashMap<String, Object> map = hwStatisticService.getSuccessLv(paramMap);
		result.setData(map);
		result.setStatus(1);
		result.setMessage("查询成功");
		return result;
	}

	/**
	 * 工单重复投诉账号统计分析 @Title: getSuccessLv @Description:
	 * TODO(这里用一句话描述这个方法的作用) @param @return 参数 @return Map<String,Object>
	 * 返回类型 @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/repeatAccount")
	public Result<HashMap<String, Object>> getRepeatAccount(@RequestBody Map<String,Object> paramMap) {
		Result<HashMap<String, Object>> result = new Result<HashMap<String, Object>>();
		HashMap<String, Object> map = hwStatisticService.getRepeatAccount(paramMap);
		result.setData(map);
		result.setStatus(1);
		result.setMessage("查询成功");
		return result;
	}

	/**
	 * 装维人员处理故障数量统计分析 @Title: getSuccessLv @Description:
	 * TODO(这里用一句话描述这个方法的作用) @param @return 参数 @return Map<String,Object>
	 * 返回类型 @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/faultLv")
	public Result<HashMap<String, Object>> getFaultLv(@RequestBody Map<String,Object> paramMap) {
		Result<HashMap<String, Object>> result = new Result<HashMap<String, Object>>();
		HashMap<String, Object> map = hwStatisticService.getFaultLv(paramMap);
		result.setData(map);
		result.setStatus(1);
		result.setMessage("查询成功");
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
		String[] faultTimeExcelHeader = { "装维人员", "平均处理时长(h)" };
		String[] faultSuccessExcelHeader = { "装维人员", "成功率(%)" };
		String[] repeatAccountExcelHeader = { "上网账号", "投诉次数(次)" };
		String[] faultLvExcelHeader = { "装维人员", "工单数量", "故障率" };
		String[] faultTypeTitles = { "faultType", "sum" };
		String[] faultAreaTitles = { "faultArea", "sum" };
		String[] faultTimeTitles = { "orderHandler", "duration" };
		String[] faultSuccessTitles = { "orderHandler", "faultSuccessLv" };
		String[] repeatAccountTitles = { "account", "sum" };
		String[] faultLvTitles = { "orderHandler", "sum", "faultLv" };
		int[] ds_format = { 1, 1 };
		int[] FaultFormat = { 1, 1, 1 };
		int[] widths = { 256 * 20, 256 * 20 };
		int[] FaultWidths = { 256 * 20, 256 * 20, 256 * 20 };
		HashMap<String, Object> resultMap = new HashMap<String,Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			switch (type) {
			case 1:
				resultMap = hwStatisticService.getFaultType(paramMap);
				list = (List<Map<String, Object>>) resultMap.get("tableData");
				ExcelUtils.export("故障类型统计", "故障类型", faultTypeExcelHeader, faultTypeTitles, ds_format, widths, list,
						request, response);
				break;
			case 2:
				resultMap = hwStatisticService.getFaultArea(paramMap);
				list = (List<Map<String, Object>>) resultMap.get("tableData");
				ExcelUtils.export("故障区域统计", "故障区域", faultAreaExcelHeader, faultAreaTitles, ds_format, widths, list,
						request, response);
				break;
			case 3:
				resultMap = hwStatisticService.getOrderhandler(paramMap);
				list = (List<Map<String, Object>>) resultMap.get("tableData");
				ExcelUtils.export("故障处理时长统计", "故障处理时长", faultTimeExcelHeader, faultTimeTitles, ds_format, widths, list,
						request, response);
				break;
			case 4:
				resultMap = hwStatisticService.getSuccessLv(paramMap);
				list = (List<Map<String, Object>>) resultMap.get("tableData");
				ExcelUtils.export("成功率统计", "成功率", faultSuccessExcelHeader, faultSuccessTitles, ds_format, widths, list,
						request, response);
				break;
			case 5:
				resultMap = hwStatisticService.getRepeatAccount(paramMap);
				list = (List<Map<String, Object>>) resultMap.get("tableData");
				ExcelUtils.export("重复投诉统计", "投诉次数", repeatAccountExcelHeader, repeatAccountTitles, ds_format, widths, list,
						request, response);
				break;
			case 6:
				resultMap = hwStatisticService.getFaultLv(paramMap);
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
