package com.tiankui.reactService.enums;
public enum LogLevel {  
	DEBUG("DEBUG", 200), INFO("INFO", 1), WARN("WARN", 2), ERROR("ERROR", 3), FATAL("FATAL", 4);  
    // 成员变量  
    private String name;  
    private int index;  
    // 构造方法  
    private LogLevel(String name, int index) {  
        this.name = name;  
        this.index = index;  
    }  
    // 普通方法  
    public static String getName(int index) {  
        for (LogLevel c : LogLevel.values()) {  
            if (c.getIndex() == index) {  
                return c.name;  
            }  
        }  
        return null;  
    }  
    // get set 方法  
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
