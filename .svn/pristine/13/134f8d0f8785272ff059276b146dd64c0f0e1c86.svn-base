package com.tiankui.reactService.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tiankui.reactService.service.IWarnService;
import com.tiankui.reactService.util.UUIDUtil;

@Service
public class WarnService implements IWarnService {

	@Override
	public List<Map<String, Object>> getListByMap(Map<String, Object> map) {
		Map<String, Object> mapObject = new HashMap<String,Object>();
		Map<String, Object> mapObject1 = new HashMap<String,Object>();
		Map<String, Object> mapObject2 = new HashMap<String,Object>();
		mapObject.put("id", UUIDUtil.getUUID());
		mapObject.put("warnObject", "虚拟机");
		mapObject.put("warnName", "quanzi-databases");
		mapObject.put("warnBusiness", "自有合作/圈子营销平台");
		mapObject.put("objIP", "120.209.138.68");
		mapObject.put("warnType", "内存利用率");
		mapObject.put("warnLevel", 1);
		mapObject.put("warnMessage", "当前内存利用率98.82%，超过紧急告警阀值（90%-100%）");
		mapObject.put("warnTime", Long.parseLong("1514707871000"));
		mapObject.put("confirm", 1);
		mapObject.put("warnSource", 1);
		
		mapObject1.put("id", UUIDUtil.getUUID());
		mapObject1.put("warnObject", "服务器");
		mapObject1.put("warnName", "guanzi-databases");
		mapObject1.put("warnBusiness", "自有合作/管子营销平台");
		mapObject1.put("objIP", "111.222.333.44");
		mapObject1.put("warnType", "cpu利用率");
		mapObject1.put("warnLevel", 0);
		mapObject1.put("warnMessage", "当前cpu利用率81.23%，未超过紧急告警阀值（90%-100%）");
		mapObject1.put("warnTime", Long.parseLong("1514707871123"));
		mapObject1.put("confirm", 0);
		mapObject1.put("warnSource", 2);

		mapObject2.put("id", UUIDUtil.getUUID());
		mapObject2.put("warnObject", "交换机");
		mapObject2.put("warnName", "pingzi-databases");
		mapObject2.put("warnBusiness", "自有合作/瓶子营销平台");
		mapObject2.put("objIP", "101.202.303.44");
		mapObject2.put("warnType", "cpu利用率");
		mapObject2.put("warnLevel", 1);
		mapObject2.put("warnMessage", "当前cpu利用率95.23%，超过紧急告警阀值（90%-100%）");
		mapObject2.put("warnTime", Long.parseLong("1514707871123"));
		mapObject2.put("confirm", 0);
		mapObject2.put("warnSource", 3);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(mapObject);
		list.add(mapObject1);
		list.add(mapObject2);
		return list;
	}

	@Override
	public Map<String, Object> getPics() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		//firstData
		String[] firstX = {"OTT-d","szjtm","OTT-j","duand","OTT-s","hsdai","sjain","OTT-c","cmpay","xxt-d"};
		Map<String,Object> firstMap = new HashMap<String,Object>();
		Map<String,Object> map = new HashMap<String,Object>();
		int[] datas = {89, 88, 87, 87, 86, 86, 84, 64, 64, 64};
		map.put("name", "告警次数");
		map.put("data", datas);
		List<Map<String,Object>> firstList = new ArrayList<Map<String,Object>>();
		firstList.add(map);
		firstMap.put("x", firstX);
		firstMap.put("y", firstList);
		resultMap.put("firstData", firstMap);
		
		//secondData
		String[] x = {"虚拟机", "主机", "存储", "交换机", "其他"};
		Map<String,Object> secondMap = new HashMap<String,Object>();
		Map<String,Object> map1 = new HashMap<String,Object>();
		Map<String,Object> map2 = new HashMap<String,Object>();
		int[] datas1 = {30, 49, 30, 18, 19};
		int[] datas2 = {305, 18, 18, 22, 20};
		map1.put("name", "紧急");
		map1.put("data", datas1);
		map2.put("name", "警告");
		map2.put("data", datas2);
		List<Map<String,Object>> secondList = new ArrayList<Map<String,Object>>();
		secondList.add(map1);
		secondList.add(map2);
		secondMap.put("x", x);
		secondMap.put("y", secondList);
		resultMap.put("secondData", secondMap);
		
		//thirdMap
		Map<String, Object> thirdMap = new HashMap<String,Object>();
		Map<String, Object> hashMap = new HashMap<String,Object>();
		String[] name = {"虚拟机","交换机","存储","主机","其他"};
		int[] y = {35,4,39,15,1};
		Object[] maps = new Object[name.length];
		for (int i = 0; i < name.length; i++) {
			Map<String, Object> pMap = new HashMap<String,Object>();
			pMap.put("name", name[i]);
			pMap.put("y", y[i]);
			maps[i] = pMap;
		}
		List<Object> list3 = new ArrayList<Object>();
		hashMap.put("type", "pie");
		hashMap.put("name", "告警数量");
		hashMap.put("data", maps);
		list3.add(hashMap);
		thirdMap.put("data", list3);
		resultMap.put("thirdData", thirdMap);
		
		//fourthMap
		Map<String, Object> fourthMap = new HashMap<String,Object>();
		String[] dataX = {"一月", "二月", "三月", "四月", "五月", "六月", "七月"};
		int[] data2 = {800, 500, 780, 770, 800, 1908, 900};
		int[] data3 = {25, 25, 20, 33, 3, 92, 50};
		int[] totalData = new int[data2.length];
		for (int i = 0; i < totalData.length; i++) {
			totalData[i] = data2[i]+data3[i];
		}
		Map<String,Object> hashMap3 = new HashMap<String,Object>();
		hashMap3.put("name", "总数");
		hashMap3.put("data", totalData);
		Map<String,Object> hashMap4 = new HashMap<String,Object>();
		hashMap4.put("name", "警告");
		hashMap4.put("data", data2);
		Map<String,Object> hashMap5 = new HashMap<String,Object>();
		hashMap5.put("name", "紧急");
		hashMap5.put("data", data3);
		List<Map<String,Object>> list4 = new ArrayList<Map<String,Object>>();
		list4.add(hashMap3);
		list4.add(hashMap4);
		list4.add(hashMap5);
		fourthMap.put("data", list4);
		fourthMap.put("dataX", dataX);
		resultMap.put("fourthData", fourthMap);
		return resultMap;
	}

}
