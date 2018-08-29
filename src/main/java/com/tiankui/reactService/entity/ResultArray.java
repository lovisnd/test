package com.tiankui.reactService.entity;

import java.util.Arrays;

public class ResultArray<T> {

	private T[] data;
	private Integer status;
	private String message;

	@Override
	public String toString() {
		return "Result [data=" + Arrays.toString(data) + ", status=" + status + ", message=" + message + "]";
	}

	public T[] getData() {
		return data;
	}

	public void setData(T[] data) {
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResultArray() {
		super();
	}

	public ResultArray(T[] data, Integer status, String message) {
		super();
		this.data = data;
		this.status = status;
		this.message = message;
	}

}
