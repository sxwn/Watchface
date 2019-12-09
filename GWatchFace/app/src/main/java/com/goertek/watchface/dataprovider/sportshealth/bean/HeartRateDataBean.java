package com.goertek.watchface.dataprovider.sportshealth.bean;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class HeartRateDataBean {
    private String currentHeartRate;
    private String maxHeartRate;
    private String minHeartRate;
    private String restHeartRate;

    public HeartRateDataBean() {
    }

    public String getCurrentHeartRate() {
        return this.currentHeartRate;
    }

    public String getMaxHeartRate() {
        return this.maxHeartRate;
    }

    public String getMinHeartRate() {
        return this.minHeartRate;
    }

    public String getRestHeartRate() {
        return this.restHeartRate;
    }

    public void setCurrentHeartRate(String var1) {
        this.currentHeartRate = var1;
    }

    public void setMaxHeartRate(String var1) {
        this.maxHeartRate = var1;
    }

    public void setMinHeartRate(String var1) {
        this.minHeartRate = var1;
    }

    public void setRestHeartRate(String var1) {
        this.restHeartRate = var1;
    }

}