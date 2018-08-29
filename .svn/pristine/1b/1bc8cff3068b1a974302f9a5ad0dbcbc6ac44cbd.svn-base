package com.tiankui.reactService.net;

import java.util.ArrayList;
import java.util.List;

public class SnmpUtilsTest {
    private SnmpUtils snmpUtils = new SnmpUtils();

    public static void main(String[] args) {
        SnmpUtilsTest SnmpTest = new SnmpUtilsTest();

        SnmpTest.testVersion();

        SnmpTest.testGet();
    }

    public void testGet() {
        String ip = "127.0.0.1";
        String community = "public";
//        String oidval = ".1.3.6.1.2.1.1.1.0";
        String oidval = ".1.3.6.1.2.1.1.2.0";//获取设备识别码
        String vb = snmpUtils.snmpGet(ip, community, oidval);
        System.out.println("***** " + vb + " *****");
    }

    public void testGetList() {
        String ip = "127.0.0.1";
        String community = "public";
        List<String> oidList = new ArrayList<String>();
        oidList.add(".1.3.6.1.2.1.1.1.0");
        oidList.add(".1.3.6.1.2.1.1.2.0");
        snmpUtils.snmpGetList(ip, community, oidList);
    }

    public void testGetAsyList() {
        String ip = "127.0.0.1";
        String community = "public";
        List<String> oidList = new ArrayList<String>();
        oidList.add(".1.3.6.1.2.1.1.1.0");
        oidList.add(".1.3.6.1.2.1.1.2.0");
        snmpUtils.snmpAsynGetList(ip, community, oidList);
        System.out.println("i am first!");
    }

    public void testWalk() {
        String ip = "127.0.0.1";
        String community = "public";
        String targetOid = ".1.3.6.1.2.1";
        snmpUtils.snmpWalk(ip, community, targetOid);
    }

    public void testAsyWalk() {
        String ip = "127.0.0.1";
        String community = "public";
        // 异步采集数据
        snmpUtils.snmpAsynWalk(ip, community, ".1.3.6.1.2.1");
    }

    public void testVersion() {
        System.out.println(org.snmp4j.version.VersionInfo.getVersion());
    }
}
