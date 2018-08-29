package com.tiankui.reactService.enums;

public enum LogType {
	
	LOGIN_SUCCESS("用户登录",200),
	LOGIN_FAIL("登录失败",1),
	SYSTEM_ERROR("系统错误",2),
	DATA_QUERY_SUCCESS("数据查询成功",3),
	DATA_QUERY_FAIL("数据查询失败",4),
	DATA_ADD_SUCCESS("添加数据成功",5),
	DATA_ADD_FAIL("数据添加失败",6),
	DATA_UPDATE_SUCCESS("数据修改成功",7),
	DATA_UPDATE_FAIL("数据修改失败",8),
	DATA_DELETE_SUCCESS("数据删除成功",9),
	DATA_DELETE_FAIL("数据删除失败",10),
	FILE_UPLOAD_SUCCESS("文件导入成功",11),
	FILE_UPLOAD_FAIL("文件导入失败",12),
	FILE_DOWNLOAD_SUCCESS("文件导出成功",13),
	FILE_DOWNLOAD_FAIL("文件导出失败",14);
	
	// 成员变量  
    private String name;  
    private int index;
	
    private LogType(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
    
}
