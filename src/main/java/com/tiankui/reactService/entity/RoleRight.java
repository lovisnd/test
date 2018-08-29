package com.tiankui.reactService.entity;

/**
 * 角色权限实体类
 * 
 * @author Z.A
 *
 */
public class RoleRight {

	private String id;
	private String roleId;
	private String menuId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public RoleRight(String id, String roleId, String menuId) {
		super();
		this.id = id;
		this.roleId = roleId;
		this.menuId = menuId;
	}

	public RoleRight() {
		super();
	}

}
