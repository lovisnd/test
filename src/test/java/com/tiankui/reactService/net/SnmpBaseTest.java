package com.tiankui.reactService.net;

import com.tiankui.reactService.entity.SystemInfo;

public class SnmpBaseTest {
    public static void main(String[] args) {
        SnmpBase snmpBase = new SnmpBase();
        try {
            SystemInfo systemInfo = snmpBase.getSysInfo("localhost", "public");
            System.out.println(systemInfo.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
