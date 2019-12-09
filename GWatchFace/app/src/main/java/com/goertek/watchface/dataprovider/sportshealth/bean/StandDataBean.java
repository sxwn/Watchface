package com.goertek.watchface.dataprovider.sportshealth.bean;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class StandDataBean {
    private String currentStandingNumber;
    private String goalStandingNumber;

    public StandDataBean() {
    }

    public String getCurrentStand() {
        return this.currentStandingNumber;
    }

    public String getTargetStand() {
        return this.goalStandingNumber;
    }

    public void setCurrentStand(String var1) {
        this.currentStandingNumber = var1;
    }

    public void setTargetStand(String var1) {
        this.goalStandingNumber = var1;
    }

}
