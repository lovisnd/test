package com.tiankui.reactService.service;

import com.tiankui.reactService.entity.DateOrder;
import com.tiankui.reactService.entity.Dict;
import com.tiankui.reactService.entity.ErrorInfo;
import com.tiankui.reactService.entity.WorkOrder;

import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: IWorkOrderService
 * @Description: TODO 工单处理逻辑处理
 * @author zhouao
 * @date 2018年7月30日
 *
 */
public interface IWorkOrderService {

	/**
	 * 
	 * @Title: upload
	 * @Description: TODO 10086工单导入
	 * @param @param listob
	 * @param @param token
	 * @param @param originalFilename
	 * @param @return    参数
	 * @throws
	 */
	List<ErrorInfo> upload(List<List<Object>> listob, String token, String originalFilename) throws Exception;

    /**
     * @Title: upload
     * @Description: TODO 铁通工单导入
     * @param listob
     */
    void ttWorkOrderInsert(String fileSrc, List<List<Object>> listob);

    /**
     * @Title: getList
     * @Description: TODO 工单列表查询
     * @param map
     */
    List<WorkOrder> getListByMap(Map<String,Object> map);

    /**
     * @Title: getCountList
     * @Description: TODO 查询工单总记录数
     * @param map
     */
    int getCountList(Map<String,Object> map);

    /**
     * 
     * @Title: getWorkOrderById
     * @Description: TODO 根据ID查询工单信息
     * @param @param id
     * @param @return    参数
     * @return WorkOrder    返回类型
     * @throws
     */
	Map<String, Object> getWorkOrderById(String id);

	/**
     *
     * @Title: getWorkOrderById
     * @Description: TODO 根据ID查询工单信息
     * @param @param id
     * @param @return    参数
     * @return WorkOrder    返回类型
     * @throws
     */
	WorkOrder getOrderById(String id);

	/**
	 * @Title: getList
	 * @Description: TODO 工单列表查询
	 * @param map
	 */
	List<Map<String, Object>> getList(Map<String,Object> map);

	/**
	 * 删除工单信息
	 * @param id
	 * @return
	 */
	int delOrderId(String id);

	/**
	 * 查询重复的工单号
	 * @param id
	 * @return
	 */
	int getOrderNo(String id);

	/**
	 * 添加工单信息
	 * @param map
	 * @return
	 */
	int addWorkOrder(Map<String,Object> map);

	/**
	 * 修改工单信息
	 * @param id
	 * @param map
	 * @return
	 */
	int updateWorkOrder(String id, Map<String,Object> map);

	/**
	 * 查询工单区域
	 * @Title: getAreaList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return    参数
	 * @return List<String>    返回类型
	 * @throws
	 */
	List<Dict> getAreaList();
	/**
	 * 查询每个月工单数
	 * @return
	 */
	List<DateOrder> queryDateOrder();
}
