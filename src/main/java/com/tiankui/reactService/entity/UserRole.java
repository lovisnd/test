package com.tiankui.reactService.entity;

public class UserRole {

	private String id;
	private String userId;
	private String roleId;

	public String getId() {
		return id;
	}

	public String getRoleId() {
		return roleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public UserRole() {
		super();
	}

	public UserRole(String id, String userId, String roleId) {
		super();
		this.id = id;
		this.userId = userId;
		this.roleId = roleId;
	}

}
