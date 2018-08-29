package com.tiankui.reactService.service;

import java.util.List;
import java.util.Map;

import com.tiankui.reactService.entity.ErrorInfo;
import com.tiankui.reactService.entity.GuestInfo;

/**
 * 
 * @ClassName: IGuestInfoService
 * @Description: TODO 客户信息操作
 * @author zhouao
 * @date 2018年7月24日
 *
 */
public interface IGuestInfoService {

	/**
	 * @param @param list
	 * @param @return    参数
	 * @param token
	 *
	 * @param string
	 * @Title: upload
	 * @Description: TODO 客户信息文件导入
	 * @return int    返回类型
	 * @throws
	 */
	List<ErrorInfo> upload(List<List<Object>> list, String token, String string) throws Exception;

	/**
	 * 
	 * @Title: getList
	 * @Description: TODO 条件查询所有客户信息
	 * @param @param map
	 * @param @return    参数
	 * @return List<GuestInfo>    返回类型
	 * @throws
	 */
	List<GuestInfo> getListByMap(Map<String, Object> map);

	/**
	 * 
	 * @Title: getAreaList
	 * @Description: TODO 查询客户所在区域列表
	 * @param @return    参数
	 * @return List<String>    返回类型
	 * @throws
	 */
	List<String> getAreaList();

	/**
	 * 
	 * @Title: addGuestInfo
	 * @Description: TODO 添加客户信息
	 * @param @param map
	 * @param @return    参数
	 * @return int    返回类型
	 * @throws
	 */
	int addGuestInfo(Map<String, Object> map);

	/**
	 * 
	 * @Title: delGuestInfo
	 * @Description: TODO 删除客户信息
	 * @param @param id
	 * @param @return    参数
	 * @return int    返回类型
	 * @throws
	 */
	int delGuestInfo(String id);

	/**
	 * 
	 * @Title: getGuestInfoById
	 * @Description: TODO 根据ID查询客户信息
	 * @param @param id
	 * @param @return    参数
	 * @return GuestInfo    返回类型
	 * @throws
	 */
	GuestInfo getGuestInfoById(String id);

	/**
	 * 
	 * @Title: updateGuestInfo
	 * @Description: TODO 修改客户信息
	 * @param @param id
	 * @param @param map
	 * @param @return    参数
	 * @return int    返回类型
	 * @throws
	 */
	int updateGuestInfo(String id, Map<String, Object> map);

	/**
	 * 
	 * @Title: getGuestNo
	 * @Description: TODO 查询客户编码
	 * @param @param guestNo
	 * @param @return    参数
	 * @return int    返回类型
	 * @throws
	 */
	int getGuestNo(String guestNo);

	/**
	 * 
	 * @Title: getCountList
	 * @Description: TODO 条件查询所有客户信息总记录数
	 * @param @param map
	 * @param @return    参数
	 * @return int    返回类型
	 * @throws
	 */
	int getCountList(Map<String, Object> map);

	/**
	 * 获取错误记录列表
	 * @return
	 */
	List<GuestInfo> getList(Map<String, Object> map);

}
