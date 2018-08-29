package com.tiankui.reactService.mapper;

import java.util.List;
import java.util.Map;

import com.tiankui.reactService.entity.Role;
import com.tiankui.reactService.entity.User;

public interface UserMapper {

	User getUserByNameAndPassword(User user);

	void setTokenByUser(User userLogin);

	User getTokenUser(String token);

	List<User> getList(Map<String, Object> map);

	User getUserById(String id);

	int updateUser(Map<String, Object> map);
	
	int updateUserPassword(Map<String, Object> map);

	int updateUserEnabled(User user);

	int insertUser(User user);

	int delUser(String id);

	int resetPassword(Map<String,Object> hashMap);

	int clearToken(String token);

	List<Role> getRoleByUserId(String id);

	List<User> queryUserByRid(String id);

	/**
	 * 
	 * @Title: getCount
	 * @Description: TODO 查询总记录数
	 * @param @param map
	 * @param @return    参数
	 * @return int    返回类型
	 * @throws
	 */
	int getCount(Map<String, Object> map);

	/**
	 * 查询部门下的所有用户
	 * @param deptList
	 * @return
	 */
	List<User> getUserByDepts(List<String> deptList);

	/**
	 * 查询运维人员
	 * @return
	 */
    List<User> getOptStaffss();

    /**
	 * 根据区县查询运维人员
	 * @return
	 */
	List<User> getUserByDept(List<String> strings);

	List<User> getExportList(Map<String, Object> map);
}
