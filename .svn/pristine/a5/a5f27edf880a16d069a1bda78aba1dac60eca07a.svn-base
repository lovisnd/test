package com.tiankui.reactService.net;

import com.tiankui.reactService.entity.CpuInfo;
import com.tiankui.reactService.entity.SystemInfo;

/**
 * 对Linux服务器进行snmp采集
 */
public class SnmpLinux extends SnmpBase {
    @Override
    public CpuInfo getCpuInfo(String ip, String community) throws Exception {
        CpuInfo cpuInfo = super.getCpuInfo(ip, community);
        cpuInfo.setSysRate(snmpGet(ip, community, Constant.LINUX_SYS));
        cpuInfo.setUserRate(snmpGet(ip, community, Constant.LINUX_USER));
        cpuInfo.setFreeRate(snmpGet(ip, community, Constant.LINUX_FREE));
        return cpuInfo;
    }

    @Override
    public SystemInfo getSysInfo(String ip, String community) throws Exception {
        SystemInfo sysInfo = super.getSysInfo(ip, community);

        sysInfo.setMemoryInfo(getMemoryInfo(ip, community));
        sysInfo.setDiskInfos(getDiskInfo(ip, community));
        sysInfo.setCpuInfo(getCpuInfo(ip, community));
        return sysInfo;
    }
}
