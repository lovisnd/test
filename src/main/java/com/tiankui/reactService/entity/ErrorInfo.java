package com.tiankui.reactService.entity;

/**
 * 错误信息
 * 
 * @ClassName: ErrorInfo
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhouao
 * @date 2018年8月7日
 *
 */
public class ErrorInfo {

	public String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorInfo() {
		super();
	}

	public ErrorInfo(String message) {
		super();
		this.message = message;
	}

}
