package com.tiankui.reactService.enums;

/**
 * 操作系统类型
 */
public enum SnmpType {
    WINDOWS("windows"), LINUX("linux"), ESXI("esxi");

    private final String type;

    private SnmpType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
