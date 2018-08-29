package com.tiankui.reactService.entity;

public class PingInfo {
    private int sendPackages;//已发送数据包
    private int receivingPackages;//已接受数据包
    private double lossRate;//丢失率
    private int minTime;//最短时间（ms）
    private int maxTime;//最长时间（ms）
    private int avgTime;//平均时间（ms）
    private boolean flag;//成功为1，失败为0

    public int getSendPackages() {
        return sendPackages;
    }

    public void setSendPackages(int sendPackages) {
        this.sendPackages = sendPackages;
    }

    public int getReceivingPackages() {
        return receivingPackages;
    }

    public void setReceivingPackages(int receivingPackages) {
        this.receivingPackages = receivingPackages;
    }

    public double getLossRate() {
        return lossRate;
    }

    public void setLossRate(double lossRate) {
        this.lossRate = lossRate;
    }

    public int getMinTime() {
        return minTime;
    }

    public void setMinTime(int minTime) {
        this.minTime = minTime;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public int getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(int avgTime) {
        this.avgTime = avgTime;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "PingInfo{" +
                "sendPackages=" + sendPackages +
                ", receivingPackages=" + receivingPackages +
                ", lossRate=" + lossRate +
                ", minTime=" + minTime +
                ", maxTime=" + maxTime +
                ", avgTime=" + avgTime +
                ", flag=" + flag +
                '}';
    }
}
