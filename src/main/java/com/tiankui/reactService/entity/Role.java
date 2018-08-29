package com.tiankui.reactService.entity;

import java.util.Date;

public class Role {

	private String id;
	private String name;
	private String code;
	private String description;
	private Integer deleted;
	private Date createTime;
	private Date updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Role() {
		super();
	}

	public Role(String id, String name, String code, String description, Integer deleted, Date createTime,
			Date updateTime) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.description = description;
		this.deleted = deleted;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

}
