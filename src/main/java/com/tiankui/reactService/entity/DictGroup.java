package com.tiankui.reactService.entity;

public class DictGroup {
	private String id;
	private String groupCode;
	private String groupName;
	private String groupType;
	private Long createTime;

	public DictGroup() {
		super();
	}

	public DictGroup(String id, String groupCode, String groupName, String groupType, Long createTime) {
		super();
		this.id = id;
		this.groupCode = groupCode;
		this.groupName = groupName;
		this.groupType = groupType;
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

}
