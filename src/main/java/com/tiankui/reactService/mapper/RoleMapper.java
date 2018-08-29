package com.tiankui.reactService.mapper;

import java.util.List;
import java.util.Map;

import com.tiankui.reactService.entity.Role;

public interface RoleMapper {

	public List<Role> getListByExample(Map<String, Object> map);

	public List<Role> getListByUserId(Map<String, Object> hashMap);

	public Role selectExampleById(String id);

	public int updateRole(Map<String, Object> map);

	public int addRole(Map<String, Object> map);

	/**
	 * 
	 * @Title: getCount
	 * @Description: TODO 查询总记录数
	 * @param @param map
	 * @param @return    参数
	 * @return int    返回类型
	 * @throws
	 */
	public int getCount(Map<String, Object> map);

	/**
	 * 删除角色信息
	 * @Title: deleteRole
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param id    参数
	 * @return void    返回类型
	 * @throws
	 */
	public int deleteRole(String id);

	public List<Role> getExportList(Map<String, Object> map); 
}
