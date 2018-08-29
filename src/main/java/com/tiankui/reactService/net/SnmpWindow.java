package com.tiankui.reactService.net;

import com.tiankui.reactService.entity.SystemInfo;

/**
 * 对windows系统进行snmp采集
 */
public class SnmpWindow extends SnmpBase {
    @Override
    public SystemInfo getSysInfo(String ip, String community) throws Exception {
        SystemInfo sysInfo = super.getSysInfo(ip, community);

        sysInfo.setMemoryInfo(getMemoryInfo(ip, community));
        sysInfo.setDiskInfos(getDiskInfo(ip, community));
        sysInfo.setCpuInfo(getCpuInfo(ip, community));
        return sysInfo;
    }
}
