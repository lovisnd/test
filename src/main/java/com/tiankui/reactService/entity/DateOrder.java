package com.tiankui.reactService.entity;
/**
 * 日期工单数类/故障类型数量类
 * @author joke
 *
 */
public class DateOrder {
	private String x;
	private int y;
	//平均处理时长
	private double time;
	//处理成功率
	private double FAULTSUCCESSLV;
	public DateOrder() {
		super();
	}
	public DateOrder(String x, int y, double time, double fAULTSUCCESSLV) {
		super();
		this.x = x;
		this.y = y;
		this.time = time;
		FAULTSUCCESSLV = fAULTSUCCESSLV;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	public double getFAULTSUCCESSLV() {
		return FAULTSUCCESSLV;
	}
	public void setFAULTSUCCESSLV(double fAULTSUCCESSLV) {
		FAULTSUCCESSLV = fAULTSUCCESSLV;
	}
	
	
}
