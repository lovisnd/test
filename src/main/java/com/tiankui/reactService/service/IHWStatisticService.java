package com.tiankui.reactService.service;

import java.util.HashMap;
import java.util.Map;

/**
 * 家庭客户工单统计分析
 * @ClassName: IHWStatisticService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhouao
 * @date 2018年8月13日
 *
 */
public interface IHWStatisticService {

	/**
	 * @param paramMap 
	 * 按工单故障类型统计
	 * @Title: getFaultType
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return    参数
	 * @return List<Map<String,Object>>    返回类型
	 * @throws
	 */
	HashMap<String,Object> getFaultType(Map<String, Object> paramMap);

	/**
	 * @param paramMap 
	 * 按工单故障区域统计
	 * @Title: getFaultArea
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return    参数
	 * @return List<Map<String,Object>>    返回类型
	 * @throws
	 */
	HashMap<String,Object> getFaultArea(Map<String, Object> paramMap);

	/**
	 * @param paramMap 
	 * 按工单故障区域统计
	 * @Title: getOrderhandler
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return    参数
	 * @return List<Map<String,Object>>    返回类型
	 * @throws
	 */
	HashMap<String,Object> getOrderhandler(Map<String, Object> paramMap);

	/**
	 * @param paramMap 
	 * 按工单故障成功率统计
	 * @Title: getSuccessLv
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return    参数
	 * @return List<Map<String,Object>>    返回类型
	 * @throws
	 */
	HashMap<String,Object> getSuccessLv(Map<String, Object> paramMap);

	/**
	 * @param paramMap 
	 * 按工单重复投诉账号统计
	 * @Title: getRepeatAccount
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return    参数
	 * @return List<Map<String,Object>>    返回类型
	 * @throws
	 */
	HashMap<String,Object> getRepeatAccount(Map<String, Object> paramMap);

	/**
	 * @param paramMap 
	 * 分区域装维人员故障统计
	 * @Title: getRepeatAccount
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return    参数
	 * @return List<Map<String,Object>>    返回类型
	 * @throws
	 */
	HashMap<String,Object> getFaultLv(Map<String, Object> paramMap);

}
