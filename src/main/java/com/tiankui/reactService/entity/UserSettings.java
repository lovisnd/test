package com.tiankui.reactService.entity;

public class UserSettings {
	private String id;
	private String userId;
	private String name;
	private String value;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public UserSettings() {
		super();
	}

	public UserSettings(String id, String userId, String name, String value) {
		super();
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.value = value;
	}

}
