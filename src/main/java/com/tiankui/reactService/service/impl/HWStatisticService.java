package com.tiankui.reactService.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiankui.reactService.entity.HWStatistic;
import com.tiankui.reactService.mapper.DictGroupMapper;
import com.tiankui.reactService.mapper.HWStatisticMapper;
import com.tiankui.reactService.service.IHWStatisticService;
import com.tiankui.reactService.util.ObjectUtil;

/**
 * 家庭客户工单统计分析
 * 
 * @ClassName: HWStatisticService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhouao
 * @date 2018年8月13日
 *
 */
@Service
public class HWStatisticService implements IHWStatisticService {

	@Autowired
	private HWStatisticMapper hwStatisticMapper;
	@Autowired
	private DictGroupMapper dictGroupMapper;

	@Override
	public HashMap<String, Object> getFaultType(Map<String, Object> paramMap) {
//		convertDate(paramMap);
		List<HWStatistic> list = hwStatisticMapper.statisticByFault(paramMap);
		for (HWStatistic workOrder : list) {
			String name = dictGroupMapper.getDictByCode(workOrder.getFaultType());
			if (ObjectUtil.isNotNull(name)) {
				workOrder.setFaultType(name);
			} else {
				workOrder.setFaultType("其他故障");
			}
		}
		List<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> pie = new ArrayList<Map<String, Object>>();
		for (HWStatistic order : list) {
			Map<String, Object> toMap = ObjectUtil.objectToMap(order);
			arrayList.add(toMap);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", order.getFaultType());
			map.put("y", order.getSum());
			pie.add(map);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("tableData", arrayList);
		map.put("pie", pie);
		return map;
	}

	@Override
	public HashMap<String, Object> getFaultArea(Map<String, Object> paramMap) {
//		convertDate(paramMap);
		List<HWStatistic> list = hwStatisticMapper.statisticByArea(paramMap);
		List<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> pie = new ArrayList<Map<String, Object>>();
		for (HWStatistic order : list) {
			Map<String, Object> toMap = ObjectUtil.objectToMap(order);
			arrayList.add(toMap);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", order.getFaultArea());
			map.put("y", order.getSum());
			pie.add(map);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("tableData", arrayList);
		map.put("pie", pie);
		return map;
	}

//	/**
//	 * 转换日期格式生成查询条件
//	 * @Title: convertDate
//	 * @Description: TODO(这里用一句话描述这个方法的作用)
//	 * @param @param paramMap    参数
//	 * @return void    返回类型
//	 * @throws
//	 */
//	private void convertDate(Map<String, Object> paramMap) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String startTime = paramMap.get("startTime").toString();
//		String endTime = paramMap.get("endTime").toString();
//		long start = 0l;
//		long end = new Date().getTime();
//		try {
//			start = sdf.parse(startTime).getTime();
//			end = sdf.parse(endTime).getTime();
//			paramMap.put("start", start);
//			paramMap.put("end", end);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//	}

	@Override
	public HashMap<String, Object> getOrderhandler(Map<String, Object> paramMap) {
//		convertDate(paramMap);
		List<HWStatistic> list = hwStatisticMapper.statisticByOrderhandler(paramMap);
		List<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>();
		List<String> xAxis = new ArrayList<String>();
		List<Double> yAxis = new ArrayList<Double>();
		for (HWStatistic order : list) {
			Map<String, Object> toMap = ObjectUtil.objectToMap(order);
			arrayList.add(toMap);
			xAxis.add(order.getOrderHandler());
			yAxis.add(order.getDuration());
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("tableData", arrayList);
		map.put("xAxis", xAxis);
		map.put("yAxis", yAxis);
		return map;
	}

	@Override
	public HashMap<String, Object> getSuccessLv(Map<String, Object> paramMap) {
//		convertDate(paramMap);
		List<HWStatistic> list = hwStatisticMapper.statisticBySuccessLv(paramMap);
		List<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>();
		List<String> xAxis = new ArrayList<String>();
		List<Double> yAxis = new ArrayList<Double>();
		for (HWStatistic order : list) {
			Map<String, Object> toMap = ObjectUtil.objectToMap(order);
			arrayList.add(toMap);
			xAxis.add(order.getOrderHandler());
			yAxis.add(order.getFaultSuccessLv());
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("tableData", arrayList);
		map.put("xAxis", xAxis);
		map.put("yAxis", yAxis);
		return map;
	}

	@Override
	public HashMap<String, Object> getRepeatAccount(Map<String, Object> paramMap) {
//		convertDate(paramMap);
		List<HWStatistic> list = hwStatisticMapper.statisticByRepeatAccount(paramMap);
		List<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> pie = new ArrayList<Map<String, Object>>();
		for (HWStatistic order : list) {
			Map<String, Object> toMap = ObjectUtil.objectToMap(order);
			arrayList.add(toMap);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", order.getAccount());
			map.put("y", order.getSum());
			pie.add(map);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("tableData", arrayList);
		map.put("pie", pie);
		return map;
	}

	@Override
	public HashMap<String, Object> getFaultLv(Map<String, Object> paramMap) {
//		convertDate(paramMap);
		List<HWStatistic> list = hwStatisticMapper.statisticByFaultLv(paramMap);
		List<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>();
		List<String> xAxis = new ArrayList<String>();
		List<Double> yAxis = new ArrayList<Double>();
		for (HWStatistic order : list) {
			Map<String, Object> toMap = ObjectUtil.objectToMap(order);
			arrayList.add(toMap);
			xAxis.add(order.getOrderHandler());
			yAxis.add(order.getFaultLv());
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("tableData", arrayList);
		map.put("xAxis", xAxis);
		map.put("yAxis", yAxis);
		return map;
	}

}
