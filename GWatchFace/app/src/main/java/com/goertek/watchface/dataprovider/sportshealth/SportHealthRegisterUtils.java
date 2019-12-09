package com.goertek.watchface.dataprovider.sportshealth;

import com.goertek.commonlib.provider.data.manager.DataAdapter;
import com.goertek.commonlib.utils.LogUtil;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class SportHealthRegisterUtils {
    private static final String TAG = "SportHealthRegisterUtils";
    private static SportsHealthProvider sportsHealthProvider = new SportsHealthProvider();

    private SportHealthRegisterUtils() {
    }

    private static void registerProvider(String valueType) {
        DataAdapter.getInstance().registerDataProvider(valueType, sportsHealthProvider);
    }

    public static void registerSportHealthRegister() {
        LogUtil.i("SportHealthRegisterUtils", "consumption registerSportHealthRegister");
        registerProvider("data_health_step");
        registerProvider("data_health_stand_current");
        registerProvider("data_health_calorie_current");
        registerProvider("data_health_health_current");
        registerProvider("data_health_sporttime_current");
        registerProvider("data_health_step_target_max_min_value");
        registerProvider("data_health_step_current");
        registerProvider("data_health_health_current_max");
        registerProvider("data_health_health_current_min");
        registerProvider("data_health_stand_max_min_value");
        registerProvider("data_health_sporttime_max_min_value");
        registerProvider("data_health_colorie_max_min_value");
        registerProvider("data_health_heat_max_min_value");
        registerProvider("data_health_stand");
        registerProvider("data_health_sporttime");
        registerProvider("data_health_health_current_max");
        registerProvider("data_health_health_min");
        registerProvider("data_current_heat_rate_step");
        registerProvider("data_health_clim");
        registerProvider("data_health_step_current_and_unit");
        registerProvider("data_health_calorie_current_and_unit");
        registerProvider("heart_rate");
        registerProvider("step");
        registerProvider("middle_high_intensity");
        registerProvider("calories");
        registerProvider("activity_hour");
        registerProvider("climb_stair");
        registerProvider("distance");
        registerProvider("max_oxygen_uptake");
        registerProvider("data_health_step_current_index");
        registerProvider("data_health_calorie_current_index");
        registerProvider("data_health_health_current_index");
    }

    private static void unRegisterProvider(String valueType) {
        DataAdapter.getInstance().unregisterDataProvider(valueType, sportsHealthProvider);
    }

    public static void unRegisterSportHealthRegister() {
        LogUtil.i("SportHealthRegisterUtils", "consumption unRegisterSportHealthRegister");
        unRegisterProvider("data_health_step");
        unRegisterProvider("data_health_stand_current");
        unRegisterProvider("data_health_calorie_current");
        unRegisterProvider("data_health_health_current");
        unRegisterProvider("data_health_sporttime_current");
        unRegisterProvider("data_health_step_target_max_min_value");
        unRegisterProvider("data_health_step_current");
        unRegisterProvider("data_health_health_current_max");
        unRegisterProvider("data_health_health_current_min");
        unRegisterProvider("data_health_stand_max_min_value");
        unRegisterProvider("data_health_sporttime_max_min_value");
        unRegisterProvider("data_health_colorie_max_min_value");
        unRegisterProvider("data_health_heat_max_min_value");
        unRegisterProvider("data_health_stand");
        unRegisterProvider("data_health_sporttime");
        unRegisterProvider("data_health_health_current_max");
        unRegisterProvider("data_health_health_min");
        unRegisterProvider("data_current_heat_rate_step");
        unRegisterProvider("data_health_clim");
        unRegisterProvider("data_health_step_current_and_unit");
        unRegisterProvider("data_health_calorie_current_and_unit");
        unRegisterProvider("heart_rate");
        unRegisterProvider("step");
        unRegisterProvider("middle_high_intensity");
        unRegisterProvider("calories");
        unRegisterProvider("activity_hour");
        unRegisterProvider("climb_stair");
        unRegisterProvider("distance");
        unRegisterProvider("max_oxygen_uptake");
        unRegisterProvider("data_health_step_current_index");
        unRegisterProvider("data_health_calorie_current_index");
        unRegisterProvider("data_health_health_current_index");
    }
}