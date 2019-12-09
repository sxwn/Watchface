package com.goertek.watchface.dataprovider.sportshealth.bean;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class SportTimeDataBean {
    private String currentIntensity;
    private String goalIntensity;

    public SportTimeDataBean() {
    }

    public String getCurrentSportTime() {
        return this.currentIntensity;
    }

    public String getTargetSportTime() {
        return this.goalIntensity;
    }

    public void setCurrentSportTime(String var1) {
        this.currentIntensity = var1;
    }

    public void setTargetSportTime(String var1) {
        this.goalIntensity = var1;
    }

}
