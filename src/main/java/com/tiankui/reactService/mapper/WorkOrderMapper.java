package com.tiankui.reactService.mapper;

import java.util.List;
import java.util.Map;

import com.tiankui.reactService.entity.WorkOrder;

/**
 * 
 * @ClassName: WorkOrderMapper
 * @Description: TODO 工单处理持久层
 * @author zhouao
 * @date 2018年7月30日
 *
 */
public interface WorkOrderMapper {

    /**
     * 保存工单信息
     * @param wo
     */
    int save(WorkOrder wo);

    /**
     * 查询工单列表
     * @param map
     * @return
     */
    List<WorkOrder> getListByMap(Map<String,Object> map);

    /**
     * 查询工单记录数
     * @param map
     * @return
     */
    int getCountListByMap(Map<String,Object> map);

    /**
     *
     * @Title: insertByBatch
     * @Description: TODO 批量插入
     * @param @param list    参数
     * @return void    返回类型
     * @throws
     */
    void insertByBatch(List<WorkOrder> list);

    /**
     * 
     * @Title: getWorkOrderById
     * @Description: TODO 根据ID查询工单
     * @param @param id
     * @param @return    参数
     * @return WorkOrder    返回类型
     * @throws
     */
	WorkOrder getWorkOrderById(String id);

	/**
	 * 
	 * @Title: getOrderByOrderNo
	 * @Description: TODO 根据工单号查询工单
	 * @param @param orderNo
	 * @param @return    参数
	 * @return WorkOrder    返回类型
	 * @throws
	 */
	WorkOrder getOrderByOrderNo(String orderNo);
	
	/**
	 * 
	 * @Title: updateBatch
	 * @Description: TODO 批量更新工单编号重复的记录
	 * @param @param repeatList    参数
	 * @return void    返回类型
	 * @throws
	 */
	void updateBatch(List<WorkOrder> list);

	/**
	 * 按条件查询记录
	 * @param map
	 * @return
	 */
    List<WorkOrder> getList(Map<String,Object> map);

	/**
	 * 根据ID删除工单信息
	 * @param id
	 * @return
	 */
	int detele(String id);

	/**
	 * 更新工单信息
	 * @param map
	 * @return
	 */
    int update(Map<String,Object> map);

    /**
     * 查询工单区域列表
     * @Title: getAreas
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @return    参数
     * @return List<String>    返回类型
     * @throws
     */
	List<String> getAreas();
	
	/**
	 * 查询所有的工单
	 * @return
	 */
	List<WorkOrder> getAllOrder();
}
