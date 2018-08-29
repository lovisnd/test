package com.tiankui.reactService.entity;

public class Dept {

	private String id;
	private String parentId;
	private String name;
	private String code;
	private String description;
	private Integer sort;
	private Integer deleted;
	private Long createTime;
	private Long updateTime;

	public String getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Dept() {
		super();
	}

	public Dept(String id, String parentId, String name, String code, String description, Integer sort, Integer deleted,
			Long createTime, Long updateTime) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.code = code;
		this.description = description;
		this.sort = sort;
		this.deleted = deleted;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

}
