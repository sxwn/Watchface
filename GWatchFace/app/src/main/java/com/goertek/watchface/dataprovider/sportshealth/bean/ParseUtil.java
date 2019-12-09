package com.goertek.watchface.dataprovider.sportshealth.bean;

import java.util.Map;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class ParseUtil {
    public static final String CALORIES_CURRENT = "currentCalories";
    public static final String CALORIES_GOAL = "goalCalories";
    public static final String HEART_RATE_MAX = "maxHeartRate";
    public static final String HEART_RATE_MIN = "minHeartRate";
    public static final String HEART_RATE_REST = "restHeartRate";
    public static final String INTENSITY_CURRENT = "currentIntensity";
    public static final String INTENSITY_GOAL = "goalIntensity";
    public static final String STANDING_NUMBER_CURRENT = "currentStandingNumber";
    public static final String STANDING_NUMBER_GOAL = "goalStandingNumber";
    public static final String STEP_NUMBER_CURRENT = "currentStepNumber";
    public static final String STEP_NUMBER_GOAL = "goalStepNumber";

    private ParseUtil() {
    }

    public static HeartRateDataBean parseMapToHeartRateDataBean(Map<String, String> var0) {
        if (var0 != null && !var0.isEmpty()) {
            HeartRateDataBean var1 = new HeartRateDataBean();
            var1.setCurrentHeartRate(String.valueOf(var0.get("currentHeartRate")));
            var1.setRestHeartRate(String.valueOf(var0.get("restHeartRate")));
            var1.setMaxHeartRate(String.valueOf(var0.get("maxHeartRate")));
            var1.setMinHeartRate(String.valueOf(var0.get("minHeartRate")));
            return var1;
        } else {
            return null;
        }
    }

    public static SportTimeDataBean parseMapToSportTimeDataBean(Map<String, String> var0) {
        if (var0 != null && !var0.isEmpty()) {
            SportTimeDataBean var1 = new SportTimeDataBean();
            var1.setCurrentSportTime(String.valueOf(var0.get("currentIntensity")));
            var1.setTargetSportTime(String.valueOf(var0.get("goalIntensity")));
            return var1;
        } else {
            return null;
        }
    }

    public static StandDataBean parseMapToStandDataBean(Map<String, String> var0) {
        if (var0 != null && !var0.isEmpty()) {
            StandDataBean var1 = new StandDataBean();
            var1.setTargetStand(String.valueOf(var0.get("goalStandNumber")));
            var1.setCurrentStand(String.valueOf(var0.get("currentStandNumber")));
            return var1;
        } else {
            return null;
        }
    }

    public static StepDataBean parseMapToStepDataBean(Map<String, String> var0) {
        if (var0 != null && !var0.isEmpty()) {
            StepDataBean var1 = new StepDataBean();
            var1.setCurrentStep(String.valueOf(var0.get("currentStepNumber")));
            var1.setTargetStep(String.valueOf(var0.get("goalStepNumber")));
            return var1;
        } else {
            return null;
        }
    }
}
