package com.tiankui.reactService.service;

import java.util.List;
import java.util.Map;

public interface ICheckService {

	List<Map<String, Object>> getListByMap(Map<String, Object> map);

	Map<String, Object> getPics();

}
