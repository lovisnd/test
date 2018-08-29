package com.tiankui.reactService.entity;

import java.util.Date;

/**
 * 
 * @ClassName: JkWorkOrder
 * @Description: TODO 集客工单处理实体类
 * @author zhangmingrui
 * @date 2018年8月06日
 *
 */
public class JkWorkOrder {
	private String id;
	private String city;
	private String area;
	private String phoneNo;
	private String guestName;
	private String cliqueName;
	private String cliqueNo;
	private String cliqueAddress;
	private String complainContent;
	private String distributePerson;
	private Date takeOrderDate;
	private String faultType;
	private Integer isTranspond;
	private String transpondPerson;
	private Date transpondTakeOrderDate;
    private String orderHandler;
    private Date returnOrderDate;
    private String faultReson;
	private Integer handleMin;
	private Integer isSuccess;
	private Integer isTimeout;
	private Integer callOutWay;
	private Integer isSatisfaction;
	private Integer orderState;
	private Date createDate;
	private String creater;
	private String timeOutDate;
	public JkWorkOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JkWorkOrder(String id, String city, String area, String phoneNo, String guestName, String cliqueName,
			String cliqueNo, String cliqueAddress, String complainContent, String distributePerson, Date takeOrderDate,
			String faultType, Integer isTranspond, String transpondPerson, Date transpondTakeOrderDate,
			String orderHandler, Date returnOrderDate, String faultReson, Integer handleMin, Integer isSuccess,
			Integer isTimeout, Integer callOutWay, Integer isSatisfaction, Integer orderState, Date createDate,
			String creater, String timeOutDate) {
		super();
		this.id = id;
		this.city = city;
		this.area = area;
		this.phoneNo = phoneNo;
		this.guestName = guestName;
		this.cliqueName = cliqueName;
		this.cliqueNo = cliqueNo;
		this.cliqueAddress = cliqueAddress;
		this.complainContent = complainContent;
		this.distributePerson = distributePerson;
		this.takeOrderDate = takeOrderDate;
		this.faultType = faultType;
		this.isTranspond = isTranspond;
		this.transpondPerson = transpondPerson;
		this.transpondTakeOrderDate = transpondTakeOrderDate;
		this.orderHandler = orderHandler;
		this.returnOrderDate = returnOrderDate;
		this.faultReson = faultReson;
		this.handleMin = handleMin;
		this.isSuccess = isSuccess;
		this.isTimeout = isTimeout;
		this.callOutWay = callOutWay;
		this.isSatisfaction = isSatisfaction;
		this.orderState = orderState;
		this.createDate = createDate;
		this.creater = creater;
		this.timeOutDate = timeOutDate;
	}
	@Override
	public String toString() {
		return "JkWorkOrder [id=" + id + ", city=" + city + ", area=" + area + ", phoneNo=" + phoneNo + ", guestName="
				+ guestName + ", cliqueName=" + cliqueName + ", cliqueNo=" + cliqueNo + ", cliqueAddress="
				+ cliqueAddress + ", complainContent=" + complainContent + ", distributePerson=" + distributePerson
				+ ", takeOrderDate=" + takeOrderDate + ", faultType=" + faultType + ", isTranspond=" + isTranspond
				+ ", transpondPerson=" + transpondPerson + ", transpondTakeOrderDate=" + transpondTakeOrderDate
				+ ", orderHandler=" + orderHandler + ", returnOrderDate=" + returnOrderDate + ", faultReson="
				+ faultReson + ", handleMin=" + handleMin + ", isSuccess=" + isSuccess + ", isTimeout=" + isTimeout
				+ ", callOutWay=" + callOutWay + ", isSatisfaction=" + isSatisfaction + ", orderState=" + orderState
				+ ", createDate=" + createDate + ", creater=" + creater + ", timeOutDate=" + timeOutDate + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getCliqueName() {
		return cliqueName;
	}
	public void setCliqueName(String cliqueName) {
		this.cliqueName = cliqueName;
	}
	public String getCliqueNo() {
		return cliqueNo;
	}
	public void setCliqueNo(String cliqueNo) {
		this.cliqueNo = cliqueNo;
	}
	public String getCliqueAddress() {
		return cliqueAddress;
	}
	public void setCliqueAddress(String cliqueAddress) {
		this.cliqueAddress = cliqueAddress;
	}
	public String getComplainContent() {
		return complainContent;
	}
	public void setComplainContent(String complainContent) {
		this.complainContent = complainContent;
	}
	public String getDistributePerson() {
		return distributePerson;
	}
	public void setDistributePerson(String distributePerson) {
		this.distributePerson = distributePerson;
	}
	public Date getTakeOrderDate() {
		return takeOrderDate;
	}
	public void setTakeOrderDate(Date takeOrderDate) {
		this.takeOrderDate = takeOrderDate;
	}
	public String getFaultType() {
		return faultType;
	}
	public void setFaultType(String faultType) {
		this.faultType = faultType;
	}
	public Integer getIsTranspond() {
		return isTranspond;
	}
	public void setIsTranspond(Integer isTranspond) {
		this.isTranspond = isTranspond;
	}
	public String getTranspondPerson() {
		return transpondPerson;
	}
	public void setTranspondPerson(String transpondPerson) {
		this.transpondPerson = transpondPerson;
	}
	public Date getTranspondTakeOrderDate() {
		return transpondTakeOrderDate;
	}
	public void setTranspondTakeOrderDate(Date transpondTakeOrderDate) {
		this.transpondTakeOrderDate = transpondTakeOrderDate;
	}
	public String getOrderHandler() {
		return orderHandler;
	}
	public void setOrderHandler(String orderHandler) {
		this.orderHandler = orderHandler;
	}
	public Date getReturnOrderDate() {
		return returnOrderDate;
	}
	public void setReturnOrderDate(Date returnOrderDate) {
		this.returnOrderDate = returnOrderDate;
	}
	public String getFaultReson() {
		return faultReson;
	}
	public void setFaultReson(String faultReson) {
		this.faultReson = faultReson;
	}
	public Integer getHandleMin() {
		return handleMin;
	}
	public void setHandleMin(Integer handleMin) {
		this.handleMin = handleMin;
	}
	public Integer getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(Integer isSuccess) {
		this.isSuccess = isSuccess;
	}
	public Integer getIsTimeout() {
		return isTimeout;
	}
	public void setIsTimeout(Integer isTimeout) {
		this.isTimeout = isTimeout;
	}
	public Integer getCallOutWay() {
		return callOutWay;
	}
	public void setCallOutWay(Integer callOutWay) {
		this.callOutWay = callOutWay;
	}
	public Integer getIsSatisfaction() {
		return isSatisfaction;
	}
	public void setIsSatisfaction(Integer isSatisfaction) {
		this.isSatisfaction = isSatisfaction;
	}
	public Integer getOrderState() {
		return orderState;
	}
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getTimeOutDate() {
		return timeOutDate;
	}
	public void setTimeOutDate(String timeOutDate) {
		this.timeOutDate = timeOutDate;
	}
	
	

}
