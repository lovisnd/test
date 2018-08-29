package com.tiankui.reactService.net;

import com.tiankui.reactService.entity.MemoryInfo;
import com.tiankui.reactService.entity.SystemInfo;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * 对ESXI服务器（虚拟机）进行snmp采集
 */
public class SnmpEsxi extends SnmpBase {
    public MemoryInfo getMemoryInfo(String ip, String community) throws Exception {
        MemoryInfo memoryInfo = new MemoryInfo();
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(1);
        nf.setMaximumFractionDigits(1);

        int index = this.getMemoryIndex(ip, community);
        double physicalSize = Double.parseDouble(snmpGet(ip, community, Constant.WINDOW_DISK_SIZE + "." + index)) * Double.parseDouble(snmpGet(ip, community, Constant.WINDOW_DISK_AMOUNT + "." + index)) / (1024 * 1024 * 1024);
        double physicalUsedSize = Double.parseDouble(snmpGet(ip, community, Constant.WINDOW_DISK_USED + "." + index)) * Double.parseDouble(snmpGet(ip, community, Constant.WINDOW_DISK_AMOUNT + "." + index)) / (1024 * 1024 * 1024);

        memoryInfo.setMemorySize(super.getMemorySize(ip, community));
        memoryInfo.setMemoryUsedSize(nf.format(physicalUsedSize));
        memoryInfo.setMemoryFreeSize(nf.format(physicalSize - physicalUsedSize));

        nf.setMinimumFractionDigits(0);
        nf.setMaximumFractionDigits(0);
        memoryInfo.setMemoryPercentage(nf.format(physicalUsedSize / physicalSize * 100));
        return memoryInfo;
    }

    private int getMemoryIndex(String ip, String community) throws Exception {
        ArrayList<String> diskIndexTable = this.snmpWalk(ip, community, Constant.WINDOW_DISK_INDEX);
        String physicalMemoryID = Constant.DEVICE_STORAGE_RAM_ESXi_ID;
        int index = 0;
        int i = 1;
        for (String str : diskIndexTable) {
            String diskType = this.snmpGet(ip, community, Constant.WINDOW_DISK_TYPE + "." + i);
            if (diskType.equals(physicalMemoryID))
                index = Integer.parseInt(str);
            i++;
        }
        diskIndexTable = null;
        return index;
    }

    public SystemInfo getSysInfo(String ip, String community) throws Exception {
        SystemInfo sysInfo = super.getSysInfo(ip, community);

        sysInfo.setMemoryInfo(this.getMemoryInfo(ip, community));
        sysInfo.setDiskInfos(getDiskInfo(ip, community));
        sysInfo.setCpuInfo(getCpuInfo(ip, community));
        return sysInfo;
    }
}
