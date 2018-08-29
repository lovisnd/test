package com.tiankui.reactService.entity;

import java.util.Date;

public class TSyslog {
    private String id;

    private Integer logType;

    private Integer logLevel;

    private String logContent;

    private Date logTime;

    private String userId;
    
    private String userName;

    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

	public Integer getLogType() {
		return logType;
	}

	public void setLogType(Integer logType) {
		this.logType = logType;
	}

	public Integer getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(Integer logLevel) {
		this.logLevel = logLevel;
	}

	public String getLogContent() {
		return logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

	public TSyslog() {
		super();
	}

	public TSyslog(Integer logType, Integer logLevel, String logContent) {
		super();
		this.logType = logType;
		this.logLevel = logLevel;
		this.logContent = logContent;
	}
    
}