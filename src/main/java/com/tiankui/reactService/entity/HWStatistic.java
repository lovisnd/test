package com.tiankui.reactService.entity;

/**
 * 家庭工单统计实体类
 * 
 * @ClassName: HWStatistic
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhouao
 * @date 2018年8月13日
 *
 */
public class HWStatistic {

	/**
	 * 故障类型
	 */
	private String faultType;
	/**
	 * 故障区域
	 */
	private String faultArea;
	/**
	 * 工单装维人员
	 */
	private String orderHandler;
	/**
	 * 上网账号
	 */
	private String account;
	/**
	 * 故障成功率
	 */
	private Double faultSuccessLv;
	/**
	 * 故障重复投诉
	 */
	private Double repeatFault;
	/**
	 * 故障率
	 */
	private Double faultLv;
	/**
	 * 数量
	 */
	private Integer sum;
	/**
	 * 时长
	 */
	private Double duration;

	public String getFaultType() {
		return faultType;
	}

	public void setFaultType(String faultType) {
		this.faultType = faultType;
	}

	public String getFaultArea() {
		return faultArea;
	}

	public void setFaultArea(String faultArea) {
		this.faultArea = faultArea;
	}

	public String getOrderHandler() {
		return orderHandler;
	}

	public void setOrderHandler(String orderHandler) {
		this.orderHandler = orderHandler;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Double getFaultSuccessLv() {
		return faultSuccessLv;
	}

	public void setFaultSuccessLv(Double faultSuccessLv) {
		this.faultSuccessLv = faultSuccessLv;
	}

	public Double getRepeatFault() {
		return repeatFault;
	}

	public void setRepeatFault(Double repeatFault) {
		this.repeatFault = repeatFault;
	}

	public Double getFaultLv() {
		return faultLv;
	}

	public void setFaultLv(Double faultLv) {
		this.faultLv = faultLv;
	}

	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

	public Double getDuration() {
		return duration;
	}

	public void setDuration(Double duration) {
		this.duration = duration;
	}

	public HWStatistic() {
		super();
	}

	public HWStatistic(String faultType, String faultArea, String orderHandler, String account, Double faultSuccessLv,
			Double repeatFault, Double faultLv, Integer sum, Double duration) {
		super();
		this.faultType = faultType;
		this.faultArea = faultArea;
		this.orderHandler = orderHandler;
		this.account = account;
		this.faultSuccessLv = faultSuccessLv;
		this.repeatFault = repeatFault;
		this.faultLv = faultLv;
		this.sum = sum;
		this.duration = duration;
	}

}
