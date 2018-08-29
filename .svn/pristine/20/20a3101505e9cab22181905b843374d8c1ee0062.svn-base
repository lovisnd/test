package com.tiankui.reactService.entity;

import java.util.ArrayList;

/**
 * CPU基本信息采集类
 */
public class CpuInfo {
    private String cpuDesc;//CPU信息描述
    private int coreNum;//CPU核数
    private String userRate;//CPU使用率
    private String sysRate;
    private String freeRate;      // cpu空闲率
    private ArrayList<CpuDetail> cpuDetailInfos;//每个核的信息

    public String getCpuDesc() {
        return cpuDesc;
    }

    public void setCpuDesc(String cpuDesc) {
        this.cpuDesc = cpuDesc;
    }

    public int getCoreNum() {
        return coreNum;
    }

    public void setCoreNum(int coreNum) {
        this.coreNum = coreNum;
    }

    public String getUserRate() {
        return userRate;
    }

    public void setUserRate(String userRate) {
        this.userRate = userRate;
    }

    public String getSysRate() {
        return sysRate;
    }

    public void setSysRate(String sysRate) {
        this.sysRate = sysRate;
    }

    public String getFreeRate() {
        return freeRate;
    }

    public void setFreeRate(String freeRate) {
        this.freeRate = freeRate;
    }

    public ArrayList<CpuDetail> getCpuDetailInfos() {
        return cpuDetailInfos;
    }

    public void setCpuDetailInfos(ArrayList<CpuDetail> cpuDetailInfos) {
        this.cpuDetailInfos = cpuDetailInfos;
    }

    private String toStringCpuDetailInfos() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < cpuDetailInfos.size(); i++) {
            CpuDetail cpuDetail = cpuDetailInfos.get(i);
            if (i != cpuDetailInfos.size() - 1) {
                stringBuffer.append("CPU { " +
                        "cpuDesc='" + cpuDetail.getCpuDesc() + '\'' +
                        ", coreNum=" + cpuDetail.getCoreNum() +
                        ", userRate='" + cpuDetail.getUserRate() + '\'' +
                        ", sysRate='" + cpuDetail.getSysRate() + '\'' +
                        ", freeRate='" + cpuDetail.getFreeRate() + '\'' +
                        '}');
            }
        }
        return stringBuffer.toString();
    }

    @Override
    public String toString() {
        return "CpuInfo{" +
                "cpuDesc='" + cpuDesc + '\'' +
                ", coreNum=" + coreNum +
                ", userRate='" + userRate + '\'' +
                ", sysRate='" + sysRate + '\'' +
                ", freeRate='" + freeRate + '\'' +
                ", cpuDetailInfos:\n" + this.toStringCpuDetailInfos() +
                '}';
    }
}
