package com.tiankui.reactService.service;

import com.tiankui.reactService.entity.DateOrder;
import com.tiankui.reactService.entity.GuestInfo;
import com.tiankui.reactService.entity.JkWorkOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: IJkWorkOrderService
 * @Description: TODO 工单处理逻辑处理
 * @author zhangmingrui
 * @date 2018年8月06日
 *
 */

public interface IJkWorkOrderService {

    /**
     * @Title: getList
     * @Description: TODO 集客工单列表查询
     * @param map
     */
    List<JkWorkOrder> getListByMap(Map<String,Object> map);

	int getCountList(Map<String, Object> map);

	List<GuestInfo> getCliqueNameByName(String name);

	int addJkWorkOrder(Map<String, Object> map,String ip);

	int getJkOrderNo(String id);

	Map<String, Object> getJkWorkOrderById(String id);

	JkWorkOrder getOrderById(String id);

	int updateJkWorkOrder(String id, Map<String, Object> map);

	List<Map<String, Object>> getList(Map<String, Object> map);

	/**
	 * 获取未归档的工单
	 * @Title: getNotArchiveOrder
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return    参数
	 * @return List<JkWorkOrder>    返回类型
	 * @throws
	 */
	List<JkWorkOrder> getNotArchiveOrder();

	/**
	 * 将未归档的工单自动归档
	 * @Title: archiveOrder
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param notArchiveList    参数
	 * @return void    返回类型
	 * @throws
	 */
	void archiveOrder(List<JkWorkOrder> notArchiveList);
	/**
	 * 获取工单个数
	 * @return
	 */
	int queryOrderCount();
	/**
	 * 查询紧急待处理工单
	 * @return
	 */
	List<JkWorkOrder> queryUrgentData();
	/**
	 * 获取日期工单数量
	 * @return
	 */
	HashMap<String, Object> queryDateOrder();
    /**
     * 查询符合及时率工单
     * @return
     */
	int queryOrderTimely();
	/**
	 * 查询故障类型数量
	 * @return
	 */
	HashMap<String, Object> queryFaultType(Map<String,Object> map);
	/**
	 * 查询故障区域数量
	 * @param map
	 * @return
	 */
	HashMap<String, Object> queryAreaFaultType(Map<String, Object> map);
	/**
	 * 查询各维护组平均故障处理时长
	 * @param map
	 * @return
	 */
	HashMap<String, Object> queryOrderHandler(Map<String, Object> map);
	/**
	 * 查询各维护组故障成功率
	 * @param map
	 * @return
	 */
	HashMap<String, Object> querySuccessLv(Map<String, Object> map);
	/**
	 * 查询同一集团客户重复投诉信息
	 * @param map
	 * @return
	 */
	HashMap<String, Object> queryRepeatAccount(Map<String, Object> map);


}
