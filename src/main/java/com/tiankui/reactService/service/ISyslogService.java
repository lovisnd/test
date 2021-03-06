package com.tiankui.reactService.service;

import java.util.List;
import java.util.Map;

import com.tiankui.reactService.entity.TSyslog;

public interface ISyslogService {
	
	public List<TSyslog> getAllLog(Map<String, Object> map);
	
	public List<Map<String,Object>> selectLogType();

	public int getCountLog(Map<String, Object> map);

	public int insert(TSyslog log,String token, String ip);

	public List<Map<String, Object>> selectLogLevel();
	
}
