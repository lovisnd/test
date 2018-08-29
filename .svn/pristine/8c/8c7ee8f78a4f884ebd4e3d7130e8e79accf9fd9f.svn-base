package com.tiankui.reactService.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tiankui.reactService.service.ICheckService;
import com.tiankui.reactService.util.UUIDUtil;

@Service
public class CheckService implements ICheckService {

	@Override
	public List<Map<String, Object>> getListByMap(Map<String, Object> map) {
		Map<String, Object> mapObject = new HashMap<String, Object>();
		Map<String, Object> mapObject1 = new HashMap<String, Object>();
		Map<String, Object> mapObject2 = new HashMap<String, Object>();
		Map<String, Object> mapObject3 = new HashMap<String, Object>();
		mapObject.put("key", UUIDUtil.getUUID());
		mapObject.put("work", "平台DNS日常巡检");
		mapObject.put("testType", "DNS测试");
		mapObject.put("state", "启用");
		mapObject.put("newTime", "2018-05-22 15:23:00");
		mapObject.put("failureTime", "2018-05-22 15:23:00");
		mapObject.put("testWheel", "1");
		mapObject.put("testTime", "1,3,5,7,9,11,13,15,17");
		mapObject.put("remarks", "");

		mapObject1.put("key", UUIDUtil.getUUID());
		mapObject1.put("work", "平台网页日常巡检");
		mapObject1.put("testType", "网页测试");
		mapObject1.put("state", "启用");
		mapObject1.put("newTime", "2018-05-22 15:23:00");
		mapObject1.put("failureTime", "2018-05-22 15:23:00");
		mapObject1.put("testWheel", "1");
		mapObject1.put("testTime", "1,3,5,7,9,11,13,15,17");
		mapObject1.put("remarks", "");

		mapObject2.put("key", UUIDUtil.getUUID());
		mapObject2.put("work", "平台下载日常巡检");
		mapObject2.put("testType", "下载测试");
		mapObject2.put("state", "启用");
		mapObject2.put("newTime", "2018-05-22 15:23:00");
		mapObject2.put("failureTime", "2018-05-22 15:23:00");
		mapObject2.put("testWheel", "1");
		mapObject2.put("testTime", "1,3,5,7,9,11,13,15,17");
		mapObject2.put("remarks", "");

		mapObject3.put("key", UUIDUtil.getUUID());
		mapObject3.put("work", "平台游戏日常巡检");
		mapObject3.put("testType", "游戏测试");
		mapObject3.put("state", "启用");
		mapObject3.put("newTime", "2018-05-22 15:23:00");
		mapObject3.put("failureTime", "2018-05-22 15:23:00");
		mapObject3.put("testWheel", "1");
		mapObject3.put("testTime", "1,3,5,7,9,11,13,15,17");
		mapObject3.put("remarks", "");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(mapObject);
		list.add(mapObject1);
		list.add(mapObject2);
		list.add(mapObject3);
		return list;
	}

	@Override
	public Map<String, Object> getPics() {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		// fourthMap
		HashMap<String, Object> resourceMap = new HashMap<String, Object>();
		int[] data1 = { 1192, 1288, 1288, 1288, 1356, 1378, 1425, 1436 };
		int[] data2 = { 570, 570, 570, 570, 578, 589, 593, 596 };
		int[] data3 = { 10, 20, 30, 25, 15, 40, 60, 30 };
		HashMap<String, Object> hashMap3 = new HashMap<String, Object>();
		hashMap3.put("name", "CPU(核)");
		hashMap3.put("data", data1);
		HashMap<String, Object> hashMap4 = new HashMap<String, Object>();
		hashMap4.put("name", "内存(Gb)");
		hashMap4.put("data", data2);
		HashMap<String, Object> hashMap5 = new HashMap<String, Object>();
		hashMap5.put("name", "存储(Tb)");
		hashMap5.put("data", data3);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(hashMap3);
		list.add(hashMap4);
		list.add(hashMap5);
		resourceMap.put("data", list);
		resultMap.put("resourceData", resourceMap);
		return resultMap;
	}

}
