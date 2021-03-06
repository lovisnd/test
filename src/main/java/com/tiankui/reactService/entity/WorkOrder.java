package com.tiankui.reactService.entity;

import java.util.Date;

/**
 * 
 * @ClassName: WorkOrder
 * @Description: TODO 工单处理实体类
 * @author zhouao
 * @date 2018年7月30日
 *
 */
public class WorkOrder {

	private String id;
	private String orderNo;
	private Integer orderType;
	private String complaintPhoneno;
	private String internetAccount;
	private String guestName;
	private String guestAddress;
	private String city;
	private String area;
	private String complaintContent;
	private Date orderCreateDate;
	private Date takeOrderDateTt;
	private Date lastReturnOrderDate;
	private Integer isSuccess;
	private String orderHandler;
	private String handlerChangeArea;
	private String faultType;
	private Integer handleMin;
	private Integer isTimeout;
	private String intervenePerson;
	private Integer orderStatus;
	private Integer callOutWay;
	private Integer isSatisfaction;
	private Date createDate;
	private String creater;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getComplaintPhoneno() {
		return complaintPhoneno;
	}

	public void setComplaintPhoneno(String complaintPhoneno) {
		this.complaintPhoneno = complaintPhoneno;
	}

	public String getInternetAccount() {
		return internetAccount;
	}

	public void setInternetAccount(String internetAccount) {
		this.internetAccount = internetAccount;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public String getGuestAddress() {
		return guestAddress;
	}

	public void setGuestAddress(String guestAddress) {
		this.guestAddress = guestAddress;
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

	public String getComplaintContent() {
		return complaintContent;
	}

	public void setComplaintContent(String complaintContent) {
		this.complaintContent = complaintContent;
	}


	public Date getOrderCreateDate() {
		return orderCreateDate;
	}

	public void setOrderCreateDate(Date orderCreateDate) {
		this.orderCreateDate = orderCreateDate;
	}

	public Date getTakeOrderDateTt() {
		return takeOrderDateTt;
	}

	public void setTakeOrderDateTt(Date takeOrderDateTt) {
		this.takeOrderDateTt = takeOrderDateTt;
	}

	public Date getLastReturnOrderDate() {
		return lastReturnOrderDate;
	}

	public void setLastReturnOrderDate(Date lastReturnOrderDate) {
		this.lastReturnOrderDate = lastReturnOrderDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Integer isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getOrderHandler() {
		return orderHandler;
	}

	public void setOrderHandler(String orderHandler) {
		this.orderHandler = orderHandler;
	}

	public String getHandlerChangeArea() {
		return handlerChangeArea;
	}

	public void setHandlerChangeArea(String handlerChangeArea) {
		this.handlerChangeArea = handlerChangeArea;
	}

	public String getFaultType() {
		return faultType;
	}

	public void setFaultType(String faultType) {
		this.faultType = faultType;
	}

	public Integer getHandleMin() {
		return handleMin;
	}

	public void setHandleMin(Integer handleMin) {
		this.handleMin = handleMin;
	}

	public Integer getIsTimeout() {
		return isTimeout;
	}

	public void setIsTimeout(Integer isTimeout) {
		this.isTimeout = isTimeout;
	}

	public String getIntervenePerson() {
		return intervenePerson;
	}

	public void setIntervenePerson(String intervenePerson) {
		this.intervenePerson = intervenePerson;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
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

	public Date getCreateDate() {
		return createDate;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public WorkOrder() {
		super();
	}

	public WorkOrder(String id, String orderNo, Integer orderType, String complaintPhoneno, String internetAccount,
			String guestName, String guestAddress, String city, String area, String complaintContent,
			Date orderCreateDate, Date takeOrderDateTt, Date lastReturnOrderDate, Integer isSuccess,
			String orderHandler, String handlerChangeArea, String faultType, Integer handleMin, Integer isTimeout,
			String intervenePerson, Integer orderStatus, Integer callOutWay, Integer isSatisfaction, Date createDate,
			String creater) {
		super();
		this.id = id;
		this.orderNo = orderNo;
		this.orderType = orderType;
		this.complaintPhoneno = complaintPhoneno;
		this.internetAccount = internetAccount;
		this.guestName = guestName;
		this.guestAddress = guestAddress;
		this.city = city;
		this.area = area;
		this.complaintContent = complaintContent;
		this.orderCreateDate = orderCreateDate;
		this.takeOrderDateTt = takeOrderDateTt;
		this.lastReturnOrderDate = lastReturnOrderDate;
		this.isSuccess = isSuccess;
		this.orderHandler = orderHandler;
		this.handlerChangeArea = handlerChangeArea;
		this.faultType = faultType;
		this.handleMin = handleMin;
		this.isTimeout = isTimeout;
		this.intervenePerson = intervenePerson;
		this.orderStatus = orderStatus;
		this.callOutWay = callOutWay;
		this.isSatisfaction = isSatisfaction;
		this.createDate = createDate;
		this.creater = creater;
	}

	@Override
	public String toString() {
		return "WorkOrder [id=" + id + ", orderNo=" + orderNo + ", orderType=" + orderType + ", complaintPhoneno="
				+ complaintPhoneno + ", internetAccount=" + internetAccount + ", guestName=" + guestName
				+ ", guestAddress=" + guestAddress + ", city=" + city + ", area=" + area + ", complaintContent="
				+ complaintContent + ", orderCreateDate=" + orderCreateDate + ", takeOrderDateTt=" + takeOrderDateTt
				+ ", lastReturnOrderDate=" + lastReturnOrderDate + ", isSuccess=" + isSuccess + ", orderHandler="
				+ orderHandler + ", handlerChangeArea=" + handlerChangeArea + ", faultType=" + faultType
				+ ", handleMin=" + handleMin + ", isTimeout=" + isTimeout + ", intervenePerson=" + intervenePerson
				+ ", orderStatus=" + orderStatus + ", callOutWay=" + callOutWay + ", isSatisfaction=" + isSatisfaction
				+ ", createDate=" + createDate + ", creater=" + creater + "]";
	}

}
