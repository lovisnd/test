package com.tiankui.reactService.service;

import java.util.List;
import java.util.Map;

import com.tiankui.reactService.entity.Role;
import com.tiankui.reactService.entity.User;

public interface IRoleService {

	public List<Role> getListByExample(Map<String, Object> map);

	public int addRole(Map<String, Object> map);

	public Role getRoleById(String id);

	public int modifyRole(String id, Map<String, Object> map);
	
	public List<Role> getExportList(Map<String, Object> map);

	/**
	 * 
	 * @Title: queryUserByRoleId
	 * @Description: TODO 查询角色下的所有用户
	 * @param @return    参数
	 * @return List<User>    返回类型
	 * @throws
	 */
	public List<User> queryUserByRoleId(String id);

	/**
	 * 
	 * @Title: getCountList
	 * @Description: TODO 查询总记录数
	 * @param @param map
	 * @param @return    参数
	 * @return int    返回类型
	 * @throws
	 */
	public int getCountList(Map<String, Object> map);

	/**
	 * 删除角色信息
	 * @Title: delRole
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param id
	 * @param @return    参数
	 * @return int    返回类型
	 * @throws
	 */
	public int delRole(String id);
}
