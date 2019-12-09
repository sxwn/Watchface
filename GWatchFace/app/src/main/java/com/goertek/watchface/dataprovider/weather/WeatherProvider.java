package com.goertek.watchface.dataprovider.weather;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */

import android.text.TextUtils;

import com.goertek.commonlib.provider.data.manager.IDataProvider;
import com.goertek.commonlib.provider.data.model.FloatRangeValue;
import com.goertek.commonlib.provider.data.model.IntRangeValue;

import java.util.Optional;

public class WeatherProvider implements IDataProvider {
    private static final int HOURS_10 = 10;
    private static final int HOURS_12 = 12;
    private static final int HOURS_14 = 14;
    private static final int HOURS_16 = 16;
    private static final int HOURS_18 = 18;
    private static final int HOURS_2 = 2;
    private static final int HOURS_20 = 20;
    private static final int HOURS_22 = 22;
    private static final int HOURS_24 = 24;
    private static final int HOURS_4 = 4;
    private static final int HOURS_6 = 6;
    private static final int HOURS_8 = 8;
    private static final int LEFT_OFFSET_ONE = -1;
    private static final int LEFT_OFFSET_TWO = -2;
    private static final int RIGHT_OFFSET_ONE = 1;
    private static final int RIGHT_OFFSET_TWO = 2;
    private WeatherControl mWeatherControl = WeatherControl.getInstance();

    public WeatherProvider() {
    }

    private String get24HoursWeather(String valueType) {
        switch (valueType) {
            case "data_24hours_sunrise_sunset_time":
                return this.mWeatherControl.getSunRiseOrSunSetTime();
            case "data_24hours_weather_high_temp":
                return this.mWeatherControl.getHigherTemp();
            case "data_24hours_weather_high_temp_and_degree":
                return this.mWeatherControl.getHigherTempWithDegree();
            case "data_24hours_weather_lower_temp_and_degree":
                return this.mWeatherControl.getLowerTempWithDegree();
            case "data_24hours_weather_lower_temp":
                return this.mWeatherControl.getLowerTemp();
            default:
                return "";
        }
    }

    private String getAirQulity(String valueType) {
        switch (valueType) {
            case "data_air_quality_text":
                return this.mWeatherControl.getAirQualityText();
            case "data_air_quality_pm2_5_num":
                return this.mWeatherControl.getAirQualityPm25Value();
            case "data_air_quality_pm2_5_num_left_one":
                return this.mWeatherControl.getAirQualityPm25ValueOffset(-1);
            case "data_air_quality_pm2_5_num_left_two":
                return this.mWeatherControl.getAirQualityPm25ValueOffset(-2);
            case "data_air_quality_pm2_5_num_right_one":
                return this.mWeatherControl.getAirQualityPm25ValueOffset(1);
            case "data_air_quality_pm2_5_num_right_two":
                return this.mWeatherControl.getAirQualityPm25ValueOffset(2);
            default:
                return "";
        }
    }

    private String getCurrentWeather(String valueType) {
        switch (valueType) {
            case "data_current_weather_value":
                return this.mWeatherControl.getCurrentTempValue();
            case "data_current_weather_unit":
                return this.mWeatherControl.getCurrentTempUnit();
            case "data_current_weather_value_all":
                return this.mWeatherControl.getCurrentTempDreeAndUnit();
            case "data_current_weather_value_and_degree":
                return this.mWeatherControl.getCurrentTempValueWithDegree();
            case "data_current_weather_value_right_one":
                return this.mWeatherControl.getCurrentTempValueOffset(1);
            case "data_current_weather_value_right_two":
                return this.mWeatherControl.getCurrentTempValueOffset(2);
            case "data_current_weather_value_left_one":
                return this.mWeatherControl.getCurrentTempValueOffset(-1);
            case "data_current_weather_value_left_two":
                return this.mWeatherControl.getCurrentTempValueOffset(-2);
            default:
                return "";
        }
    }

    public void doClick(String dateType) {

    }

    public FloatRangeValue getFloatRangeValue(String valueType) {
        return (FloatRangeValue) Optional.empty().get();
    }

    public float getFloatValue(String valueType) {
        return 0.0F;
    }

    public int getIndexValue(String valueType) {
        if (valueType.equals("data_current_weather_icon")) {
            valueType = this.mWeatherControl.getCurrentWeatherIcon();
            if (!TextUtils.isEmpty(valueType)) {
                return Integer.parseInt(valueType);
            }
        } else if (valueType.equals("data_24hours_sunrise_sunset_icon")) {
            return this.mWeatherControl.getSunriseOrSunSetIcon();
        }

        return 0;
    }

    public IntRangeValue getIntRangeValue(String valueType) {
        switch (valueType) {
            case "data_current_weather_percentage":
                return new IntRangeValue(this.mWeatherControl.getCurrentTempIntValue(), this.mWeatherControl.getHigherTempIntValue(), this.mWeatherControl.getLowerTempIntValue());
            case "data_current_weather_value":
                return this.mWeatherControl.getWeatherTempIntRange();
            case "data_air_quality_pm2_5_num":
                return this.mWeatherControl.getAirQualityPm25IntRange();
            case "data_24hours_sunset_range":
                return this.mWeatherControl.getSunSetTimeRange();
            case "data_24hours_sunrise_sunset_hours_14_layer":
            case "data_24hours_sunrise_sunset_hours_8_layer":
            case "data_24hours_sunrise_sunset_hours_18_layer":
            case "data_24hours_sunrise_sunset_hours_4_layer":
            case "data_24hours_sunrise_sunset_hours_22_layer":
            case "data_24hours_sunrise_sunset_hours_10_layer":
            case "data_24hours_sunrise_sunset_hours_6_layer":
            case "data_24hours_sunrise_sunset_hours_24_layer":
            case "data_24hours_sunrise_sunset_hours_16_layer":
            case "data_24hours_sunrise_sunset_hours_2_layer":
            case "data_24hours_sunrise_sunset_hours_20_layer":
            case "data_24hours_sunrise_sunset_hours_12_layer":
                return new IntRangeValue(1, 1, 0);
            case "data_24hours_sunrise_range":
                return this.mWeatherControl.getSunRiseTimeRange();
            default:
                return null;
        }

    }

    public int getIntValue(String valueType) {
        switch (valueType) {
            case "data_24hours_sunrise_sunset_icon":
                return this.mWeatherControl.getSunriseOrSunSetIcon();
            case "data_current_weather_value":
                return this.mWeatherControl.getCurrentTempIntValue();
            case "data_24hours_weather_high_temp":
                return this.mWeatherControl.getHigherTempIntValue();
            case "data_air_quality_pm2_5_num":
                return this.mWeatherControl.getAirQualityPm25IntValue();
            case "data_current_weather_icon":
                valueType = this.mWeatherControl.getCurrentWeatherIcon();
                if (!TextUtils.isEmpty(valueType)) {
                    return Integer.parseInt(valueType);
                } else {
                    return 0;
                }
            case "data_24hours_weather_lower_temp":
                return this.mWeatherControl.getLowerTempIntValue();
            default:
                return 0;
        }

    }

    public int getLayerIndexValue(String valueType) {
        switch (valueType) {
            case "data_24hours_sunrise_sunset_hours_8_layer":
                return this.mWeatherControl.getSunRiseSunSetLayer(8);
            case "data_24hours_sunrise_sunset_hours_18_layer":
                return this.mWeatherControl.getSunRiseSunSetLayer(18);
            case "data_24hours_sunrise_sunset_hours_4_layer":
                return this.mWeatherControl.getSunRiseSunSetLayer(4);
            case "data_24hours_sunrise_sunset_hours_22_layer":
                return this.mWeatherControl.getSunRiseSunSetLayer(22);
            case "data_24hours_sunrise_sunset_hours_14_layer":
                return this.mWeatherControl.getSunRiseSunSetLayer(14);
            case "data_24hours_sunrise_sunset_hours_10_layer":
                return this.mWeatherControl.getSunRiseSunSetLayer(10);
            case "data_24hours_sunrise_sunset_hours_6_layer":
                return this.mWeatherControl.getSunRiseSunSetLayer(6);
            case "data_24hours_sunrise_sunset_hours_24_layer":
                return this.mWeatherControl.getSunRiseSunSetLayer(24);
            case "data_24hours_sunrise_sunset_hours_16_layer":
                return this.mWeatherControl.getSunRiseSunSetLayer(16);
            case "data_24hours_sunrise_sunset_hours_2_layer":
                return this.mWeatherControl.getSunRiseSunSetLayer(2);
            case "data_24hours_sunrise_sunset_hours_20_layer":
                return this.mWeatherControl.getSunRiseSunSetLayer(20);
            case "data_24hours_sunrise_sunset_hours_12_layer":
                return this.mWeatherControl.getSunRiseSunSetLayer(12);
            default:
                return 0;
        }
    }

    public String getStringValue(String valueType) {
        byte var2;
        label74:
        {
            switch (valueType) {
                case "data_air_quality_pm2_5_num_right_one":
                case "data_air_quality_pm2_5_num_right_two":
                case "data_air_quality_text":
                case "data_air_quality_pm2_5_num":
                case "data_air_quality_pm2_5_num_left_one":
                case "data_air_quality_pm2_5_num_left_two":
                    return this.getAirQulity(valueType);
                case "data_current_weather_unit":
                case "data_current_weather_value_all":
                case "data_current_weather_value":
                case "data_current_weather_value_right_two":
                case "data_current_weather_value_left_one":
                case "data_current_weather_value_left_two":
                case "data_current_weather_value_and_degree":
                case "data_current_weather_value_right_one":
                    return this.getCurrentWeather(valueType);
                case "data_24hours_sunrise_sunset_time":
                case "data_24hours_weather_high_temp":
                case "data_24hours_weather_high_temp_and_degree":
                case "data_24hours_weather_lower_temp_and_degree":
                case "data_24hours_weather_lower_temp":
                    return this.get24HoursWeather(valueType);
                default:
                    return "";
            }
        }
    }

    public String getStringValue(String valueType, String... format) {
        return "";
    }
}

