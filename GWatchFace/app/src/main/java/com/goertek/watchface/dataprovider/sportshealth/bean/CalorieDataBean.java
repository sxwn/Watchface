package com.goertek.watchface.dataprovider.sportshealth.bean;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class CalorieDataBean {
    private String currentCalories;
    private String goalCalories;

    public CalorieDataBean() {
    }

    public String getCurrentCalorie() {
        return this.currentCalories;
    }

    public String getTargetCalorie() {
        return this.goalCalories;
    }

    public void setCurrentCalorie(String var1) {
        this.currentCalories = var1;
    }

    public void setTargetCalorie(String var1) {
        this.goalCalories = var1;
    }
}