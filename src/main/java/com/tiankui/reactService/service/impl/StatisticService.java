package com.tiankui.reactService.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tiankui.reactService.service.IStatisticService;
import com.tiankui.reactService.util.ColorUtil;

@Service
public class StatisticService implements IStatisticService {

	@Override
	public Map<String, Object> getOnline() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String[] firstX = {"虚拟机","服务器","交换机","路由器","pc","存储","主机","其他"};
		Map<String,Object> pillarData = new HashMap<String,Object>();
		Map<String,Object> map = new HashMap<String,Object>();
		int[] datas = {89, 80, 70, 64, 50, 38, 11, 1};
		map.put("name", "在线个数");
		map.put("data", datas);
		List<Map<String,Object>> firstList = new ArrayList<Map<String,Object>>();
		firstList.add(map);
		pillarData.put("x", firstX);
		pillarData.put("y", firstList);
		resultMap.put("pillarData", pillarData);
		
		Map<String, Object> thirdMap = new HashMap<String,Object>();
		Map<String, Object> hashMap = new HashMap<String,Object>();
		String[] name = {"虚拟机","服务器","交换机","路由器","pc","防火墙","网络设备","数据库","其他"};
		int[] y = {89,80,70,64,50,38,11,10,1};
		Object[] maps = new Object[name.length];
		for (int i = 0; i < y.length; i++) {
			Map<String, Object> pMap = new HashMap<String,Object>();
			pMap.put("name", name[i]);
			pMap.put("y", y[i]);
			maps[i] = pMap;
		}
		List<Object> list3 = new ArrayList<Object>();
		hashMap.put("data", maps);
		list3.add(hashMap);
		thirdMap.put("data", list3);
		resultMap.put("cakeData", thirdMap);
		return resultMap;
	}

	@Override
	public Map<String, Object> getDeviceType() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String[] devices = {"交换机","服务器","路由器","网络设备","虚拟机","防火墙","pc","数据库","其他"};
		Integer[] ys = {50,80,70,64,50,38,20,10,3};
		Object[] deciceArray = new Object[devices.length];
		for (int i = 0; i< devices.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", devices[i]);
			map.put("y", ys[i]);
			map.put("color", ColorUtil.randomColor());
			deciceArray[i] = map;
		}
		Object[] deviceDescName = new Object[devices.length*3];
		int i = 0;
		for (; i < deviceDescName.length;) {
			for (String device : devices) {
				for(int j = 0; j<3; j++){
					deviceDescName[i] = device+"-"+j;
					i++;
				}
			}
		}
		Object[] deviceDescArray = new Object[devices.length*3];
		Integer[] deviceDescy = {10,20,20,18,30,32,10,30,30,11,12,41,10,20,20,12,16,10,13,6,2,1,4,5,1,1,1};
		for (int is = 0; is < deviceDescArray.length; is++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", deviceDescName[is]);
			map.put("y", deviceDescy[is]);
			map.put("color", ColorUtil.randomColor());
			deviceDescArray[is] = map;
		}
		resultMap.put("deviceData", deciceArray);
		resultMap.put("deviceDescData", deviceDescArray);
		return resultMap;
	}

}
