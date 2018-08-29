package com.tiankui.reactService.entity;

import java.util.Date;
import java.util.List;

public class User {
	private String id;
	private String uid;
	private String pwd;
	private Date pwdUpdateTime;
	private String name;
	private List<Role> roles;
	private String rolesName;
	private String deptId;
	private String deptName;
	private String phone;
	private String email;
	private Integer enabled;
	private Integer deleted;
	private Date createTime;
	private Date updateTime;
	private String token;
	private Long tokenTime;
	
	private List<UserSettings> settings;
	
	public String getRolesName() {
		return rolesName;
	}

	public void setRolesName(String rolesName) {
		this.rolesName = rolesName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Date getPwdUpdateTime() {
		return pwdUpdateTime;
	}

	public void setPwdUpdateTime(Date pwdUpdateTime) {
		this.pwdUpdateTime = pwdUpdateTime;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getTokenTime() {
		return tokenTime;
	}

	public void setTokenTime(Long tokenTime) {
		this.tokenTime = tokenTime;
	}

	public List<UserSettings> getSettings() {
		return settings;
	}

	public void setSettings(List<UserSettings> settings) {
		this.settings = settings;
	}

	public User() {
		super();
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public User(String uid, String pwd) {
		super();
		this.uid = uid;
		this.pwd = pwd;
	}

	public User(String id, String uid, String pwd, Date pwdUpdateTime, String name, List<Role> roles, String rolesName,
			String deptId, String deptName, String phone, String email, Integer enabled, Integer deleted,
			Date createTime, Date updateTime, String token, Long tokenTime, List<UserSettings> settings) {
		super();
		this.id = id;
		this.uid = uid;
		this.pwd = pwd;
		this.pwdUpdateTime = pwdUpdateTime;
		this.name = name;
		this.roles = roles;
		this.rolesName = rolesName;
		this.deptId = deptId;
		this.deptName = deptName;
		this.phone = phone;
		this.email = email;
		this.enabled = enabled;
		this.deleted = deleted;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.token = token;
		this.tokenTime = tokenTime;
		this.settings = settings;
	}
	
}
