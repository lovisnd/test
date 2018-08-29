package com.tiankui.reactService.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tiankui.reactService.entity.DateInterval;
import com.tiankui.reactService.entity.DateOrder;
import com.tiankui.reactService.entity.Dict;
import com.tiankui.reactService.entity.GuestInfo;
import com.tiankui.reactService.entity.HWStatistic;
import com.tiankui.reactService.entity.JkWorkOrder;
import com.tiankui.reactService.entity.TSyslog;
import com.tiankui.reactService.entity.User;
import com.tiankui.reactService.enums.LogLevel;
import com.tiankui.reactService.enums.LogType;
import com.tiankui.reactService.mapper.DeptMapper;
import com.tiankui.reactService.mapper.DictGroupMapper;
import com.tiankui.reactService.mapper.GuestInfoMapper;
import com.tiankui.reactService.mapper.JkWorkOrderMapper;
import com.tiankui.reactService.mapper.TSyslogMapper;
import com.tiankui.reactService.mapper.UserMapper;
import com.tiankui.reactService.service.IJkWorkOrderService;
import com.tiankui.reactService.util.DateDiff;
import com.tiankui.reactService.util.ObjectUtil;
import com.tiankui.reactService.util.UUIDUtil;

/**
 * @author zhangmingrui
 * @ClassName: JkWorkOrderService
 * @Description: TODO 集客工单处理逻辑处理实现层
 * @date 2018年8月06日
 */
@Service
public class JkWorkOrderService implements IJkWorkOrderService {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
	private final static String[] DWZ = {"市区代维一组","市区代维二组","市区代维三组","霍邱代维组","霍山代维组","金寨代维一组","金寨代维二组","舒城代维组","叶集代维一组"};
	@Autowired
	private DeptMapper deptMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private JkWorkOrderMapper jkworkOrderMapper;
	@Autowired
	private GuestInfoMapper guestInfoMapper;
	@Autowired
	private TSyslogMapper syslogMapper;
	@Autowired
	private DictGroupMapper dictGroupMapper;
	
	@Override
	public List<JkWorkOrder> getListByMap(Map<String, Object> map) {
		Date sysDate = new Date();
		long time =0L;
		try {
			 time = sdf.parse(sdf.format(sysDate)).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (ObjectUtil.isNotNull(map.get("id"))) {
			map.put("id", "%" + map.get("id") + "%");
		}
		if (ObjectUtil.isNotNull(map.get("orderState"))) {
			map.put("orderState", "%" + map.get("orderState") + "%");
		}
		if (ObjectUtil.isNotNull(map.get("phoneNo"))) {
			map.put("phoneNo", "%" + map.get("phoneNo") + "%");
		}
		if (ObjectUtil.isNotNull(map.get("cliqueName"))) {
			map.put("cliqueName", "%" + map.get("cliqueName") + "%");
		}
		if (ObjectUtil.isNotNull(map.get("orderHandler"))) {
			map.put("orderHandler", "%" + map.get("orderHandler") + "%");
		}
		if (ObjectUtil.isNotNull(map.get("start"))) {
			int start = Integer.valueOf(map.get("start").toString());
			int limit = Integer.valueOf(map.get("limit").toString());
			map.put("start", start + 1);
			map.put("limit", limit + start);
		}
		List<JkWorkOrder> list = jkworkOrderMapper.getListByMap(map);
        List<JkWorkOrder> list2 = new ArrayList<JkWorkOrder>();
        for (JkWorkOrder jkWorkOrder : list) {
        	Integer orderState = jkWorkOrder.getOrderState();
        	if(orderState==1){
        		int days = days(jkWorkOrder.getCreateDate().getTime(), time);
        		//工单状态待处理，超时一天以上
        		if(days>=1){
        			 double hour = hour(jkWorkOrder.getCreateDate().getTime(), time);
        			 jkWorkOrder.setTimeOutDate(hour+"");
        		}else{
        			jkWorkOrder.setTimeOutDate("0");
        		}
        	}else if(orderState==2){
        		int days = days(jkWorkOrder.getCreateDate().getTime(), time);
        		//工单状态已回单，但超时的
        		if(days>=1){
       			 //double hour = hour(jkWorkOrder.getReturnOrderDate(), time);
       			 jkWorkOrder.setTimeOutDate("1");
        		}else{
       			jkWorkOrder.setTimeOutDate("0");
       			}
        	}else if(orderState==3){
        		//工单状态归档，即工单正常
        	jkWorkOrder.setTimeOutDate("0");
          } 
        	list2.add(jkWorkOrder);
       }
		return list2;
	}

	@Override
	public int getCountList(Map<String, Object> map) {
		if (ObjectUtil.isNotNull(map.get("jkOrderNo"))) {
			map.put("jkOrderNo", "%" + map.get("jkOrderNo") + "%");
		}
		if (ObjectUtil.isNotNull(map.get("orderState"))) {
			map.put("orderState", "%" + map.get("orderState") + "%");
		}
		if (ObjectUtil.isNotNull(map.get("phoneNo"))) {
			map.put("phoneNo", "%" + map.get("phoneNo") + "%");
		}
		if (ObjectUtil.isNotNull(map.get("cliqueName"))) {
			map.put("cliqueName", "%" + map.get("cliqueName") + "%");
		}
		if (ObjectUtil.isNotNull(map.get("orderHandler"))) {
			map.put("orderHandler", "%" + map.get("orderHandler") + "%");
		}
		int count = jkworkOrderMapper.getCountListByMap(map);
		return count;
	}

	@Override
	public List<GuestInfo> getCliqueNameByName(String cliqueName) {
		if (ObjectUtil.isNotNull(cliqueName)) {
			cliqueName = "%" + cliqueName + "%";
		}
		List<GuestInfo> list = guestInfoMapper.getCliqueNameByName(cliqueName);
		return list;
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	@Override
	public int addJkWorkOrder(Map<String, Object> map,String ip) {
		TSyslog syslog = new TSyslog();
		User user = userMapper.getTokenUser(map.get("token").toString());
		Object object = map.get("createDate");
		Date systemDate = new Date();
		Date time = null;
		try {
			time = sdf.parse(sdf.format(systemDate).toString());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		System.out.println(object);
		JkWorkOrder jkworkOrder = null;
		map.put("distributePerson", "");
		map.put("creater", user.getName());
		map.put("createDate", time);
		map.put("faultReson","");
		map.put("handleMin", 0);
		map.put("isTimeout", 0);
		map.put("returnOrderDate", null);
		map.put("takeOrderDate", time);
		map.put("transpondPerson", "");
		map.put("transpondTakeOrderDate",null);
		map.put("isSatisfaction", 0);
	    map.put("isSuccess",0);
	    map.put("faultType","");
	    map.put("isTranspond",0);
		map.put("callOutWay", 1);
		map.put("orderState", 1);
		map.put("city", "六安");
		try {
			jkworkOrder = (JkWorkOrder) ObjectUtil.mapToObject(map, JkWorkOrder.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String jkOrderNo = df.format(systemDate);
		jkworkOrder.setId("JK"+jkOrderNo);
		
		syslog.setLogLevel(LogLevel.INFO.getIndex());
		syslog.setLogContent("集客工单派发成功,登陆账号" + user.getUid());
		syslog.setUserId(user.getId());
		syslog.setLogType(LogType.DATA_ADD_SUCCESS.getIndex());
		syslog.setId(UUIDUtil.getUUID());
		syslog.setAddress(ip);
		syslog.setLogTime(new Date());
		syslogMapper.insert(syslog);
		return jkworkOrderMapper.save(jkworkOrder);
	}

	@Override
	public int getJkOrderNo(String jkOrderNo) {
		int result = 0;
		JkWorkOrder jkOrder = jkworkOrderMapper.getOrderByJkOrderNo(jkOrderNo);
		if (ObjectUtil.isNotNull(jkOrder)) {
			result = 1;
		}
		return result;
	}

	@Override
	public Map<String, Object> getJkWorkOrderById(String id) {
		JkWorkOrder order = jkworkOrderMapper.getJkWorkOrderById(id);
		Map<String, Object> resultMap = convertField(order);
		return resultMap;
	}

	/**
	 * 更改字段含义使用户看得懂
	 * 
	 * @param user
	 * @return
	 */
	private Map<String, Object> convertField(JkWorkOrder user) {
		Map<String, Object> toMap = ObjectUtil.objectToMap(user);
		if(ObjectUtil.isNotNull(toMap.get("takeOrderDate"))){
			Date object = (Date)toMap.get("takeOrderDate");
			toMap.put("takeOrderDate", object.getTime());
		}
		if(ObjectUtil.isNotNull(toMap.get("createDate"))){
			Date object = (Date)toMap.get("createDate");
			toMap.put("createDate", object.getTime());
		}
		if (ObjectUtil.isNotNull(toMap.get("orderState"))) {
			if (Integer.valueOf(toMap.get("orderState").toString()) == 1) {
				toMap.put("orderState", "处理中");
			} else if (Integer.valueOf(toMap.get("orderState").toString()) == 2) {
				toMap.put("orderState", "已回单");
			} else if (Integer.valueOf(toMap.get("orderState").toString()) == 3) {
				toMap.put("orderState", "已归档");
			}
		}
		if (ObjectUtil.isNotNull(toMap.get("callOutWay"))) {
			if (Integer.valueOf(toMap.get("callOutWay").toString()) == 1) {
				toMap.put("callOutWay", "未外呼");
			} else if (Integer.valueOf(toMap.get("callOutWay").toString()) == 2) {
				toMap.put("callOutWay", "自动外呼");
			} else if (Integer.valueOf(toMap.get("callOutWay").toString()) == 3) {
				toMap.put("callOutWay", "esales外呼");
			} else if (Integer.valueOf(toMap.get("callOutWay").toString()) == 4) {
				toMap.put("callOutWay", "客服电话回访");
			}
		}
		if (ObjectUtil.isNotNull(toMap.get("isSatisfaction"))) {
			if (Integer.valueOf(toMap.get("isSatisfaction").toString()) == 1) {
				toMap.put("isSatisfaction", "很满意（问题已经解决）");
			} else if (Integer.valueOf(toMap.get("isSatisfaction").toString()) == 2) {
				toMap.put("isSatisfaction", "不满意（问题已经解决）");
			} else if (Integer.valueOf(toMap.get("isSatisfaction").toString()) == 3) {
				toMap.put("isSatisfaction", "不满意（问题未解决）");
			} else if (Integer.valueOf(toMap.get("isSatisfaction").toString()) == 0) {
				toMap.put("isSatisfaction", "尚未回访");
			}
		}
		if (ObjectUtil.isNotNull(toMap.get("isSuccess"))) {
			if (Integer.valueOf(toMap.get("isSuccess").toString()) == 0) {
				toMap.put("isSuccess", "尚未解决");
			} else {
				toMap.put("isSuccess", "已经解决");
			}
		}
		if(ObjectUtil.isNotNull(toMap.get("isTimeout"))){
			if (Integer.valueOf(toMap.get("isTimeout").toString()) == 2||Integer.valueOf(toMap.get("isTimeout").toString()) == 3) {
				toMap.put("isTimeout", "工单已超时");
			} else {
				toMap.put("isTimeout", "工单未超时");
			}
		}
		if(ObjectUtil.isNotNull(toMap.get("isTranspond"))){
			if (Integer.valueOf(toMap.get("isTranspond").toString()) == 0) {
				toMap.put("isTranspond", "不转发");
				toMap.put("transpondPerson", "未转发");
			} else {
				toMap.put("isTranspond", "转发");
			}
		}
		return toMap;
	}

	@Override
	public JkWorkOrder getOrderById(String id) {
		JkWorkOrder jkWorkOrder = jkworkOrderMapper.getJkWorkOrderById(id);
		return jkWorkOrder;
	}
	
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	@Override
	public int updateJkWorkOrder(String id, Map<String, Object> map) {
		Object isSatisfaction = map.get("isSatisfaction");
		map.put("callOutWay", 4);
		if (isSatisfaction != null && isSatisfaction != "") {
			map.put("isSatisfaction",isSatisfaction);
		}
		map.put("orderState",3);
		JkWorkOrder jkworkOrder = null;
		try {
			jkworkOrder = (JkWorkOrder) ObjectUtil.mapToObject(map, JkWorkOrder.class);
			jkworkOrder.setId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jkworkOrderMapper.updateJkWorkOrderInfo(jkworkOrder);
	}

	@Override
	public List<Map<String, Object>> getList(Map<String, Object> map) {
		if (map.get("id").equals("undefined")) {
			map.put("id", "");
		} else {
			map.put("id", "%" + map.get("id") + "%");
		}
		if (map.get("orderState").equals("undefined")) {
			map.put("orderState", "");
		} else {
			map.put("orderState", "");
		}
		if (map.get("phoneNo").equals("undefined")) {
			map.put("phoneNo", "");
		} else {
			map.put("phoneNo", "%" + map.get("phoneNo") + "%");
		}
		if (map.get("cliqueName").equals("undefined")) {
			map.put("cliqueName", "");
		} else {
			map.put("cliqueName", "%" + map.get("cliqueName") + "%");
		}
		if (map.get("orderHandler").equals("undefined")) {
			map.put("orderHandler", "");
		} else {
			map.put("orderHandler", "%" + map.get("orderHandler") + "%");
		}
		List<JkWorkOrder> list = jkworkOrderMapper.getList(map);
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (JkWorkOrder user : list) {
			Map<String, Object> toMap = convertField(user);
			mapList.add(toMap);
		}
		return mapList;
	}

	@Override
	public List<JkWorkOrder> getNotArchiveOrder() {
		return jkworkOrderMapper.getNotArchiveOrder();
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	@Override
	public void archiveOrder(List<JkWorkOrder> list) {
		jkworkOrderMapper.archiveOrder(list);
	}

	@Override
	public int queryOrderCount() {
		int count = jkworkOrderMapper.queryOrderCount();
		return count;
	}

	@Override
	public List<JkWorkOrder> queryUrgentData() {
		Date date = new Date();
	    long time = date.getTime();
	    List<JkWorkOrder> result = new ArrayList<JkWorkOrder>();
		List<JkWorkOrder> list = jkworkOrderMapper.queryUrgentData();
		for (JkWorkOrder jkWorkOrder : list) {
			Long createDate = jkWorkOrder.getCreateDate().getTime();
			int days = days(createDate,time);
			if(days>=1){
				result.add(jkWorkOrder);
			}
		}
		return result;
	}

	@Override
	public HashMap<String, Object> queryDateOrder() {
		Date sysDate= new Date();
		String date = sdf.format(sysDate);
		Long createDate = 0L;
		Date star1= null;
		Date end1 = null;
		Long returnOrderDate = 0L;
		try {
			returnOrderDate=sdf.parse(date).getTime();
			end1 = sdf.parse(date);
			createDate = sdf.parse(date).getTime()-31536000000L;
			star1 = sdf.parse(sdf.format(new Date(createDate)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<DateOrder> result = new ArrayList<DateOrder>();
		List<JkWorkOrder> list = jkworkOrderMapper.queryDateOrderByTime(star1,end1);
		SimpleDateFormat sf3 = new SimpleDateFormat("yyyy-M");
		String str = sf3.format(sysDate);
		long start=0L;
		long end =0L;
		int startYear=0;
		int startMounth=0;
		int endYear=0;
		int endMounth=0;
		try {
			start = sf3.parse(str).getTime()-31536000000L;
			String[] split = sf3.format(start).split("-");
			startYear = Integer.parseInt(split[0]);
			startMounth = Integer.parseInt(split[1]);
			end = sf3.parse(str).getTime();
			String[] split2 = sf3.format(end).split("-");
			endYear = Integer.parseInt(split2[0]);
			endMounth = Integer.parseInt(split2[1]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 DateDiff a1=new DateDiff();
		 List da=a1.dateDiff(startYear,endYear,startMounth,endMounth);
		 List<String> data =new ArrayList<String>();
		  for(int j=0;j<da.size();j++){
			  data.add(da.get(j).toString());
	         }	
		  
		    //最近一年每月工单数量	   
			List<String> xAxis = new ArrayList<String>();
			List<Integer> yAxis = new ArrayList<Integer>();
		  for (String string : data) {
			 int count =0;
				for (JkWorkOrder jkWorkOrder : list) {
					createDate = jkWorkOrder.getCreateDate().getTime();
					SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM");
					 long time = 0L;
					String format = sf2.format(createDate);
				
					 if(format.equals(string)){
						 count++;
					 }
				}
				DateOrder dateOrder = new DateOrder();
				dateOrder.setX(string);
				dateOrder.setY(count);
				result.add(dateOrder);
			    xAxis.add(dateOrder.getX());
			    yAxis.add(dateOrder.getY());			
		}
		  
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("xAxis", xAxis);
			map.put("yAxis", yAxis);
			map.put("data",result );
		 return map;
		/*for (long i = start; i <=end ; i+=2628000000L) {
			int count =0;
			for (JkWorkOrder jkWorkOrder : list) {
				createDate = jkWorkOrder.getCreateDate();
				SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM");
				 long time = 0L;
				try {
					time = sf2.parse(sf2.format(createDate)).getTime();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				 if(time==i){
					 count++;
				 }
			}
			DateOrder dateOrder = new DateOrder();
			Date da = new Date(i);
			String format = sf3.format(da);
			dateOrder.setX(format);
			dateOrder.setY(count);
			result.add(dateOrder);
		}
		return result;
		//计算当年全年的工单数量
		/*for (int i = 1; i < 13; i++) {
			int count =0;
			for (JkWorkOrder jkWorkOrder : list) {
				createDate = jkWorkOrder.getCreateDate();
				SimpleDateFormat sf2 = new SimpleDateFormat("M");
				 String format = sf2.format(createDate);
				 if(format.equals(i+"")){
					 count++;
				 }
			}
			DateOrder dateOrder = new DateOrder();
			dateOrder.setX("2018年"+i+"月");
			dateOrder.setY(count);
			result.add(dateOrder);
		}	
		return result;*/
	}
	/**
	 * 计算两个日期相差天数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public int days(Long startDate,Long endDate) {
	    int num = (int) ((endDate - startDate) / (1000 * 60 * 60 * 24));
	    return num;
	}
	public double hour(Long startDate,Long endDate) {
	    double num = (int) ((endDate - startDate) / (1000 * 60 * 60));
	    return num;
	}
	@Override
	public int queryOrderTimely() {
		List<JkWorkOrder> list = jkworkOrderMapper.queryDateOrder();
		int count =0;
		long time = new Date().getTime();
		for (JkWorkOrder jkWorkOrder : list) {
			Date createDate = jkWorkOrder.getCreateDate();
			int days = days(createDate.getTime(),time);
			if(jkWorkOrder.getOrderState()==1&&days<1){
				count++;
			}else if(jkWorkOrder.getOrderState()==2&&days(time,jkWorkOrder.getReturnOrderDate().getTime())<1){
				count++;
			}else if(jkWorkOrder.getOrderState()==3){
				count++;
			}
		}
		return count;
	}
	/**
	 * 转换日期格式生成查询条件
	 * @Title: convertDate
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param paramMap    参数
	 * @return void    返回类型
	 * @throws
	 */
	private void convertDate(Map<String, Object> paramMap) {
		String startT = paramMap.get("startTime").toString();
		String endT = paramMap.get("endTime").toString();
		try {
			SimpleDateFormat ssf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			long startTime = ssf.parse(startT).getTime();
			long endTime = ssf.parse(endT).getTime();
			paramMap.put("startTime", startTime);
			paramMap.put("endTime", endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public HashMap<String, Object> queryFaultType(Map<String,Object> map) {
		List<Dict> dictList = dictGroupMapper.selectByPrimaryKey("d58db3ed6bfc473da5ebed3ed1e0e1d1");
		List<String> listA = new ArrayList<String>();
		for (Dict dict : dictList) {
			DateOrder order = new DateOrder();
			listA.add(dict.getName());
		}
		//convertDate(map);
		List<DateOrder> list = jkworkOrderMapper.queryFaultType(map);
		List<DateOrder> listB = new ArrayList<DateOrder>();
		for (String str : listA) {
			boolean f =true;
			for (DateOrder dateOrder : list) {
				if(str.equals(dateOrder.getX())){
					listB.add(dateOrder);
					f=false;
					break;
				}
			}
			if(f){
			DateOrder order = new DateOrder();
			order.setX(str);
			order.setY(0);
			listB.add(order);
			}
		}
		
    	ArrayList<Object> resultAll =new ArrayList<Object>();
		for (DateOrder order : listB) {
			Map<String, Object> data1 = new HashMap<String, Object>();
			data1.put("name",order.getX());
			data1.put("y",order.getY());
			resultAll.add(data1);
		}
		HashMap<String,Object> map1 = new HashMap<String,Object>();
		map1.put("tableData", listB);
		map1.put("data1", resultAll);
		return map1;
	}
	/**
	 * 处理类型通用类
	 * @param list
	 * @param listA
	 * @return
	 */
	public List<DateOrder> work (List<DateOrder> list,String[] listA){
		List<DateOrder> listB = new ArrayList<DateOrder>();
		for (String str : listA) {
			boolean f =true;
			for (DateOrder dateOrder : list) {
				if(str.equals(dateOrder.getX())){
					listB.add(dateOrder);
					f=false;
					break;
				}
			}
			if(f){
			DateOrder order = new DateOrder();
			order.setX(str);
			order.setY(0);
			listB.add(order);
			}
		}
		return listB;
	}
	@Override
	public HashMap<String, Object> queryAreaFaultType(Map<String, Object> map) {
		//convertDate(map);
		List<DateOrder> list = jkworkOrderMapper.queryAreaFaultType(map);
    	ArrayList<Object> resultAll =new ArrayList<Object>();
		for (DateOrder order : list) {
			Map<String, Object> data1 = new HashMap<String, Object>();
			data1.put("name",order.getX());
			data1.put("y",order.getY());
			resultAll.add(data1);
		}
		HashMap<String,Object> map1 = new HashMap<String,Object>();
		map1.put("tableData", list);
		map1.put("data1", resultAll);
		return map1;
	}

	@Override
	public HashMap<String, Object> queryOrderHandler(Map<String, Object> paramMap) {
		//convertDate(paramMap);
		List<DateOrder> list = jkworkOrderMapper.statisticByOrderhandler(paramMap);
		for (DateOrder dateOrder : list) {
			String name = dateOrder.getX();
			String detpName = deptMapper.getNameByDeptId(name);
			dateOrder.setX(detpName);
		}
		List<DateOrder> listB = work(list,DWZ);
		List<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>();
		List<String> xAxis = new ArrayList<String>();
		List<Double> yAxis = new ArrayList<Double>();
		for (DateOrder order : listB) {
			Map<String, Object> toMap = ObjectUtil.objectToMap(order);
			arrayList.add(toMap);
			xAxis.add(order.getX());
			yAxis.add(order.getTime());
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("tableData", arrayList);
		map.put("xAxis", xAxis);
		map.put("yAxis", yAxis);
		return map;
	}

	@Override
	public HashMap<String, Object> querySuccessLv(Map<String, Object> paramMap) {
		//convertDate(paramMap);
		List<DateOrder> list = jkworkOrderMapper.statisticBySuccessLv(paramMap);
		for (DateOrder dateOrder : list) {
			String name = dateOrder.getX();
			String detpName = deptMapper.getNameByDeptId(name);
			dateOrder.setX(detpName);
		}
		List<DateOrder> listB = work(list,DWZ);
		List<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>();
		List<String> xAxis = new ArrayList<String>();
		List<Double> yAxis = new ArrayList<Double>();
    	ArrayList<Object> resultAll =new ArrayList<Object>();
		for (DateOrder order : listB) {
			Map<String, Object> data1 = new HashMap<String, Object>();
			Map<String, Object> toMap = ObjectUtil.objectToMap(order);
			arrayList.add(toMap);
			xAxis.add(order.getX());
			yAxis.add(order.getFAULTSUCCESSLV());
			data1.put("name",order.getX());
			data1.put("y",order.getFAULTSUCCESSLV());
			resultAll.add(data1);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("tableData", arrayList);
		map.put("xAxis", xAxis);
		map.put("yAxis", yAxis);
		map.put("pie",resultAll);
		return map;
	}

	@Override
	public HashMap<String, Object> queryRepeatAccount(Map<String, Object> paramMap) {
		//convertDate(paramMap);	
		List<DateOrder> list = jkworkOrderMapper.statisticByRepeatAccount(paramMap);
		int totalCount = jkworkOrderMapper.queryTotalCount(paramMap);
		List<DateOrder> resultList = new ArrayList<DateOrder>();
		for (DateOrder dateOrder : list) {
			int count = 0;
			paramMap.put("cliqueName", dateOrder.getX());
			List<JkWorkOrder> dateList = jkworkOrderMapper.queryDate(paramMap);			
			Date createDate = dateList.get(0).getCreateDate();
			List<DateInterval> dateInterval = new ArrayList<DateInterval>();
			try {
				SimpleDateFormat ssf = new SimpleDateFormat("yyyy-MM-dd HH24:mm:ss");
				Date object2 =ssf.parse(paramMap.get("endTime").toString());
			    dateInterval = getDate(createDate, object2, new ArrayList<DateInterval>());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			for (DateInterval dateInterval2 : dateInterval) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("startTime", dateInterval2.getStartDate());
				map.put("endTime", dateInterval2.getStartDate());
				int i = jkworkOrderMapper.queryTotalCount(map);
				if(i>1){
					count=i-1;
				}
			}
			DateOrder order = new DateOrder();
			order.setX(dateOrder.getX());
			order.setY(count);
			double b = new BigDecimal((float)count/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			order.setFAULTSUCCESSLV(b);
			resultList.add(order);
		}
    	ArrayList<Object> resultAll =new ArrayList<Object>();
		for (DateOrder order : resultList) {
			Map<String, Object> data1 = new HashMap<String, Object>();
			data1.put("name",order.getX());
			data1.put("y",order.getFAULTSUCCESSLV());
			resultAll.add(data1);
		}
		HashMap<String,Object> map1 = new HashMap<String,Object>();
		map1.put("tableData", resultList);
		map1.put("data1", resultAll);
		return map1;
	}
	/**
	 * 判断日期是否在某个区间
	 * @param date
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public boolean dateCompare(Date date,Date startDate,Date endDate){
		//是否在指定区间
		boolean flag = false;
		//日期转换数字
		long time = date.getTime();
		long time2 = startDate.getTime();
		long time3 = endDate.getTime();
		if(time>=time2&&time<=time3){
			flag = true;
		}
		return flag;
	}
	public List<DateInterval> getDate(Date start,Date end,List<DateInterval> list){
		Calendar c = Calendar.getInstance();
	    c.setTime(start);
	    c.add(Calendar.DAY_OF_MONTH,4);
	    Date time = c.getTime();
	    if(time.getTime()<=end.getTime()){
	    	DateInterval dateInterval = new DateInterval();
	    	dateInterval.setStartDate(start);
	    	dateInterval.setEndDate(time);
	    	list.add(dateInterval);
	    	Calendar c1 = Calendar.getInstance();
		    c1.setTime(time);
		    c1.add(Calendar.DAY_OF_MONTH,1);
		    Date time1 = c1.getTime();
		    getDate(time1,end,list);
	    }else{
	    	DateInterval dateInterval = new DateInterval();
	    	dateInterval.setStartDate(start);
	    	dateInterval.setEndDate(end);
	    	list.add(dateInterval);
	    }
		return list;
	}
}
