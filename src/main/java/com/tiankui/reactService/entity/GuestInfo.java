package com.tiankui.reactService.entity;

import java.util.Date;

/**
 * 
 * @ClassName: GuestInfo
 * @Description: TODO 客户信息实体类
 * @author zhouao
 * @date 2018年7月24日
 *
 */
public class GuestInfo {

	private String id;
	private String guestNo;
	private String guestName;
	private String guestLevel;
	private String guestServiceLevel;
	private String country;
	private String province;
	private String city;
	private String area;
	private String guestManage;
	private String guestManagePhone;
	private String guestManageEmail;
	private Date createTime;
	private String creater;
	private String guestAddress;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGuestNo() {
		return guestNo;
	}

	public void setGuestNo(String guestNo) {
		this.guestNo = guestNo;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public String getGuestLevel() {
		return guestLevel;
	}

	public void setGuestLevel(String guestLevel) {
		this.guestLevel = guestLevel;
	}

	public String getGuestServiceLevel() {
		return guestServiceLevel;
	}

	public void setGuestServiceLevel(String guestServiceLevel) {
		this.guestServiceLevel = guestServiceLevel;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
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

	public String getGuestManage() {
		return guestManage;
	}

	public void setGuestManage(String guestManage) {
		this.guestManage = guestManage;
	}

	public String getGuestManagePhone() {
		return guestManagePhone;
	}

	public void setGuestManagePhone(String guestManagePhone) {
		this.guestManagePhone = guestManagePhone;
	}

	public String getGuestManageEmail() {
		return guestManageEmail;
	}

	public void setGuestManageEmail(String guestManageEmail) {
		this.guestManageEmail = guestManageEmail;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getGuestAddress() {
		return guestAddress;
	}

	public void setGuestAddress(String guestAddress) {
		this.guestAddress = guestAddress;
	}

	@Override
	public String toString() {
		return "GuestInfo [id=" + id + ", guestNo=" + guestNo + ", guestName=" + guestName + ", guestLevel="
				+ guestLevel + ", guestServiceLevel=" + guestServiceLevel + ", country=" + country + ", province="
				+ province + ", city=" + city + ", area=" + area + ", guestManage=" + guestManage
				+ ", guestManagePhone=" + guestManagePhone + ", guestManageEmail=" + guestManageEmail + ", createTime="
				+ createTime + ", creater=" + creater + ", guestAddress=" + guestAddress + "]";
	}

	public GuestInfo() {
		super();
	}

	public GuestInfo(String id, String guestNo, String guestName, String guestLevel, String guestServiceLevel,
			String country, String province, String city, String area, String guestManage, String guestManagePhone,
			String guestManageEmail, Date createTime, String creater, String guestAddress) {
		super();
		this.id = id;
		this.guestNo = guestNo;
		this.guestName = guestName;
		this.guestLevel = guestLevel;
		this.guestServiceLevel = guestServiceLevel;
		this.country = country;
		this.province = province;
		this.city = city;
		this.area = area;
		this.guestManage = guestManage;
		this.guestManagePhone = guestManagePhone;
		this.guestManageEmail = guestManageEmail;
		this.createTime = createTime;
		this.creater = creater;
		this.guestAddress = guestAddress;
	}

}
