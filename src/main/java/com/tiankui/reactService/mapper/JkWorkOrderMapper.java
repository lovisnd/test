package com.tiankui.reactService.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tiankui.reactService.entity.DateOrder;
import com.tiankui.reactService.entity.JkWorkOrder;

/**
 * 
 * @ClassName: JkWorkOrderMapper
 * @Description: TODO 集客工单处理持久层
 * @author zhangmingrui
 * @date 2018年8月06日
 *
 */
public interface JkWorkOrderMapper {

   
    /**
     * 查询工单列表
     * @param map
     * @return
     */
    List<JkWorkOrder> getListByMap(Map<String,Object> map);

	int getCountListByMap(Map<String, Object> map);

	List<JkWorkOrder> getCliqueNameByName(String cliqueName);

	int save(JkWorkOrder jkworkOrder);

	JkWorkOrder getOrderByJkOrderNo(String jkOrderNo);

	JkWorkOrder getJkWorkOrderById(String id);

	int updateJkWorkOrderInfo(JkWorkOrder jkworkOrder);

	List<JkWorkOrder> getList(Map<String, Object> map);

	/**
	 * 查询未归档的工单
	 * @Title: getNotArchiveOrder
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return    参数
	 * @return List<JkWorkOrder>    返回类型
	 * @throws
	 */
	List<JkWorkOrder> getNotArchiveOrder();

	/**
	 * 将未归档的时间超过3天工单归档
	 * @Title: archiveOrder
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param list    参数
	 * @return void    返回类型
	 * @throws
	 */
	void archiveOrder(List<JkWorkOrder> list);
	/**
	 * 查询工单数量
	 * @Title: archiveOrder
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return int    返回类型
	 * @throws
	 */
	int queryOrderCount();
	/**
	 * 查询紧急工单数
	 * @return
	 */
	List<JkWorkOrder> queryUrgentData();
	/**
	 * 查询日期工单数量
	 * @return
	 */
	List<JkWorkOrder> queryDateOrder();
	/**
	 * 查询故障数量
	 * @return
	 */
	List<DateOrder> queryFaultType(Map<String,Object> map);
	/**
	 * 查询当前时间一年内数据
	 * @return
	 */
	List<JkWorkOrder> queryDateOrderByTime(@Param("createDate")Date star1,@Param("returnOrderDate")Date end1);
	/**
	 * 查询区域内故障数量
	 * @param map
	 * @return
	 */
	List<DateOrder> queryAreaFaultType(Map<String, Object> map);
	/**
	 * 查询维护组故障平均处理时长
	 * @param paramMap
	 * @return
	 */
	List<DateOrder> statisticByOrderhandler(Map<String, Object> paramMap);
	/**
	 * 查询维护组故障处理成功率
	 * @param paramMap
	 * @return
	 */
	List<DateOrder> statisticBySuccessLv(Map<String, Object> paramMap);
	/**
	 * 查询同一集团客户重复投诉信息
	 * @param paramMap
	 * @return
	 */
	List<DateOrder> statisticByRepeatAccount(Map<String, Object> paramMap);

	List<JkWorkOrder> queryDate(Map<String, Object> paramMap);

	int queryTotalCount(Map<String, Object> paramMap);

}
