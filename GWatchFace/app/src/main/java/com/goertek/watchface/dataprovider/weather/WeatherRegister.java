package com.goertek.watchface.dataprovider.weather;

import com.goertek.commonlib.provider.data.manager.DataAdapter;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class WeatherRegister {
    private static WeatherProvider weatherProvider = new WeatherProvider();

    private WeatherRegister() {
    }

    private static void registerProvider(String valueType) {
        DataAdapter.getInstance().registerDataProvider(valueType, weatherProvider);
    }

    public static void registerWeatherProvider() {
        registerProvider("data_air_quality_pm2_5_num");
        registerProvider("data_air_quality_pm2_5_num_right_one");
        registerProvider("data_air_quality_pm2_5_num_right_two");
        registerProvider("data_air_quality_pm2_5_num_left_one");
        registerProvider("data_air_quality_pm2_5_num_left_two");
        registerProvider("data_air_quality_text");
        registerProvider("data_current_weather_value");
        registerProvider("data_current_weather_value_and_degree");
        registerProvider("data_current_weather_value_left_one");
        registerProvider("data_current_weather_value_left_two");
        registerProvider("data_current_weather_value_right_one");
        registerProvider("data_current_weather_value_right_two");
        registerProvider("data_current_weather_unit");
        registerProvider("data_current_weather_icon");
        registerProvider("data_current_weather_value_all");
        registerProvider("data_current_weather_percentage");
        registerProvider("data_24hours_weather_high_temp");
        registerProvider("data_24hours_weather_high_temp_and_degree");
        registerProvider("data_24hours_weather_lower_temp");
        registerProvider("data_24hours_weather_lower_temp_and_degree");
        registerProvider("data_24hours_sunrise_sunset_time");
        registerProvider("data_24hours_sunrise_sunset_icon");
        registerProvider("data_24hours_sunrise_range");
        registerProvider("data_24hours_sunset_range");
        registerProvider("data_24hours_sunrise_sunset_hours_2_layer");
        registerProvider("data_24hours_sunrise_sunset_hours_4_layer");
        registerProvider("data_24hours_sunrise_sunset_hours_6_layer");
        registerProvider("data_24hours_sunrise_sunset_hours_8_layer");
        registerProvider("data_24hours_sunrise_sunset_hours_10_layer");
        registerProvider("data_24hours_sunrise_sunset_hours_12_layer");
        registerProvider("data_24hours_sunrise_sunset_hours_14_layer");
        registerProvider("data_24hours_sunrise_sunset_hours_16_layer");
        registerProvider("data_24hours_sunrise_sunset_hours_18_layer");
        registerProvider("data_24hours_sunrise_sunset_hours_20_layer");
        registerProvider("data_24hours_sunrise_sunset_hours_22_layer");
        registerProvider("data_24hours_sunrise_sunset_hours_24_layer");
        registerProvider("air_quality");
        registerProvider("weather");
    }

    private static void unRegisterProvider(String var0) {
        DataAdapter.getInstance().unregisterDataProvider(var0, weatherProvider);
    }

    public static void unRegisterWeatherProvider() {
        unRegisterProvider("data_air_quality_pm2_5_num");
        unRegisterProvider("data_air_quality_pm2_5_num_right_one");
        unRegisterProvider("data_air_quality_pm2_5_num_right_two");
        unRegisterProvider("data_air_quality_pm2_5_num_left_one");
        unRegisterProvider("data_air_quality_pm2_5_num_left_two");
        unRegisterProvider("data_air_quality_text");
        unRegisterProvider("data_current_weather_value");
        unRegisterProvider("data_current_weather_value_and_degree");
        unRegisterProvider("data_current_weather_value_left_one");
        unRegisterProvider("data_current_weather_value_left_two");
        unRegisterProvider("data_current_weather_value_right_one");
        unRegisterProvider("data_current_weather_value_right_two");
        unRegisterProvider("data_current_weather_unit");
        unRegisterProvider("data_current_weather_icon");
        unRegisterProvider("data_current_weather_value_all");
        unRegisterProvider("data_current_weather_percentage");
        unRegisterProvider("data_24hours_weather_high_temp");
        unRegisterProvider("data_24hours_weather_high_temp_and_degree");
        unRegisterProvider("data_24hours_weather_lower_temp");
        unRegisterProvider("data_24hours_weather_lower_temp_and_degree");
        unRegisterProvider("data_24hours_sunrise_sunset_time");
        unRegisterProvider("data_24hours_sunrise_sunset_icon");
        unRegisterProvider("data_24hours_sunrise_range");
        unRegisterProvider("data_24hours_sunset_range");
        unRegisterProvider("data_24hours_sunrise_sunset_hours_2_layer");
        unRegisterProvider("data_24hours_sunrise_sunset_hours_4_layer");
        unRegisterProvider("data_24hours_sunrise_sunset_hours_6_layer");
        unRegisterProvider("data_24hours_sunrise_sunset_hours_8_layer");
        unRegisterProvider("data_24hours_sunrise_sunset_hours_10_layer");
        unRegisterProvider("data_24hours_sunrise_sunset_hours_12_layer");
        unRegisterProvider("data_24hours_sunrise_sunset_hours_14_layer");
        unRegisterProvider("data_24hours_sunrise_sunset_hours_16_layer");
        unRegisterProvider("data_24hours_sunrise_sunset_hours_18_layer");
        unRegisterProvider("data_24hours_sunrise_sunset_hours_20_layer");
        unRegisterProvider("data_24hours_sunrise_sunset_hours_22_layer");
        unRegisterProvider("data_24hours_sunrise_sunset_hours_24_layer");
        unRegisterProvider("air_quality");
        unRegisterProvider("weather");
    }
}