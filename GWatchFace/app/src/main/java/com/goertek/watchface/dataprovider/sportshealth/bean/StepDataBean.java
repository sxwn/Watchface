package com.goertek.watchface.dataprovider.sportshealth.bean;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class StepDataBean {
    private String currentStepNumber;
    private String goalStepNumber;

    public StepDataBean() {
    }

    public String getCurrentStep() {
        return this.currentStepNumber;
    }

    public String getTargetStep() {
        return this.goalStepNumber;
    }

    public void setCurrentStep(String var1) {
        this.currentStepNumber = var1;
    }

    public void setTargetStep(String var1) {
        this.goalStepNumber = var1;
    }

}
