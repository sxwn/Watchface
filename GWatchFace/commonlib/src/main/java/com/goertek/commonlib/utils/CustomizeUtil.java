package com.goertek.commonlib.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.goertek.commonlib.R;
import com.goertek.commonlib.provider.data.util.DataConstantUtils;
import com.goertek.commonlib.view.unit.UnitConstants;

/**
 * 模块功能列表数据类型
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/23
 */
public class CustomizeUtil {
    private static final String TAG = "CustomizeUtil";

    private CustomizeUtil() {
    }

    /**
     * 根据label获取tab的名字
     *
     * @param context 上下文对象
     * @param label   标签
     * @return tab名
     */
    public static String getTabNameByLabel(@NonNull Context context, @NonNull String label) {
        String tab = "";
        switch (label) {
            case UnitConstants.LABEL_BACKGROUND:
                tab = context.getResources().getString(R.string.tab_background);
                break;
            case UnitConstants.LABEL_DIAL:
                tab = context.getResources().getString(R.string.tab_dial);
                break;
            case UnitConstants.LABEL_TIME:
                tab = context.getResources().getString(R.string.tab_hand);
                break;
            case UnitConstants.LABEL_WIDGET:
                tab = context.getResources().getString(R.string.tab_module);
                break;
            case UnitConstants.STYLES:
                tab = context.getResources().getString(R.string.tab_style);
                break;
            case UnitConstants.LABEL_FONT:
                tab = context.getResources().getString(R.string.tab_font);
                break;
            case UnitConstants.LABEL_POSITION:
                tab = context.getResources().getString(R.string.tab_position);
                break;
            default:
                break;
        }
        return tab;
    }

    /**
     * 获取数据的一级分类
     *
     * @param context 上下文对象
     * @param subType 子类名称
     * @return 一级分类
     */
    public static String getDataMainTypeBySubType(@NonNull Context context, @NonNull String subType) {
        String mainType = "";
        switch (subType) {
            case DataConstantUtils.DATA_TYPE_STEP:
            case DataConstantUtils.DATA_TYPE_CALORIES:
            case DataConstantUtils.DATA_TYPE_HEART_RATE:
            case DataConstantUtils.DATA_TYPE_CLIMB_STAIR:
            case DataConstantUtils.DATA_TYPE_ACTIVITY_HOUR:
            case DataConstantUtils.DATA_TYPE_MIDDLE_HIGH_INTENSITY:
            case DataConstantUtils.DATA_TYPE_ACTIVITY_HOUR_TEXT:
            case DataConstantUtils.DATA_TYPE_MIDDLE_HIGH_INTENSITY_TEXT:
            case DataConstantUtils.DATA_TYPE_DISTANCE:
            case DataConstantUtils.DATA_TYPE_PRESSURE_SPORT:
            case DataConstantUtils.DATA_TYPE_MAX_OXYGEN_UPTAKE:
                mainType = context.getResources().getString(R.string.type_health);
                break;
            case DataConstantUtils.DATA_TYPE_AIR_QUALITY:
            case DataConstantUtils.DATA_TYPE_WEATHER:
            case DataConstantUtils.DATA_TYPE_PRESSURE:
                mainType = context.getResources().getString(R.string.type_weather);
                break;
            case DataConstantUtils.DATA_TYPE_BATTERY:
                mainType = context.getResources().getString(R.string.type_general);
                break;
            case DataConstantUtils.DATA_TYPE_CALENDAR:
            case DataConstantUtils.DATA_TYPE_WORLD_TIME:
            case DataConstantUtils.DATA_TYPE_SECOND_HAND:
            case DataConstantUtils.DATA_TYPE_MOON_PHASE:
            case DataConstantUtils.DATA_TYPE_SUNRISE_SUNSET:
            case DataConstantUtils.DATA_TYPE_SECONDS_DEGREE:
            case DataConstantUtils.VALUE_TYPE_DATA_DAY:
            case DataConstantUtils.VALUE_TYPE_DATA_WEEK:
                mainType = context.getResources().getString(R.string.type_clock);
                break;
            default:
                break;
        }
        return mainType;
    }

    /**
     * 获取数据名称
     *
     * @param context 上下文对象
     * @param subType 子类名称
     * @return 数据名称
     */
    public static String getDataNameBySubType(@NonNull Context context, @NonNull String subType) {
        String name;
        switch (subType) {
            case DataConstantUtils.DATA_TYPE_STEP:
                name = context.getResources().getString(R.string.module_step);
                break;
            case DataConstantUtils.DATA_TYPE_CALORIES:
                name = context.getResources().getString(R.string.module_calories);
                break;
            case DataConstantUtils.DATA_TYPE_HEART_RATE:
                name = context.getResources().getString(R.string.module_heart_rate);
                break;
            case DataConstantUtils.DATA_TYPE_AIR_QUALITY:
                name = context.getResources().getString(R.string.module_air_quality);
                break;
            case DataConstantUtils.DATA_TYPE_WEATHER:
                name = context.getResources().getString(R.string.type_weather);
                break;
            case DataConstantUtils.DATA_TYPE_PRESSURE:
                name = context.getResources().getString(R.string.module_pressure);
                break;
            case DataConstantUtils.DATA_TYPE_BATTERY:
                name = context.getResources().getString(R.string.module_data_battery);
                break;
            default:
                name = getDataNameBySubTypeInternal(context, subType);
                break;
        }
        return name;
    }

    private static String getDataNameBySubTypeInternal(@NonNull Context context, @NonNull String subType) {
        String name;
        switch (subType) {
            case DataConstantUtils.DATA_TYPE_CALENDAR:
                name = context.getResources().getString(R.string.module_calendar);
                break;
            case DataConstantUtils.DATA_TYPE_WORLD_TIME:
                name = context.getResources().getString(R.string.module_world_time);
                break;
            case DataConstantUtils.DATA_TYPE_SECOND_HAND:
                name = context.getResources().getString(R.string.module_second_hand);
                break;
            case DataConstantUtils.DATA_TYPE_MOON_PHASE:
                name = context.getResources().getString(R.string.module_moon_phase);
                break;
            case DataConstantUtils.DATA_TYPE_SUNRISE_SUNSET:
                name = context.getResources().getString(R.string.module_sunrise_and_sunset);
                break;
            case DataConstantUtils.DATA_TYPE_CLIMB_STAIR:
                name = context.getResources().getString(R.string.module_climb_stair);
                break;
            case DataConstantUtils.DATA_TYPE_ACTIVITY_HOUR:
                name = context.getResources().getString(R.string.module_health_stand_num);
                break;
            case DataConstantUtils.DATA_TYPE_MIDDLE_HIGH_INTENSITY:
                name = context.getResources().getString(R.string.module_health_sport_times);
                break;
            case DataConstantUtils.DATA_TYPE_ACTIVITY_HOUR_TEXT:
                name = context.getResources().getString(R.string.module_health_stand_num);
                break;
            case DataConstantUtils.DATA_TYPE_MIDDLE_HIGH_INTENSITY_TEXT:
                name = context.getResources().getString(R.string.module_health_sport_times);
                break;
            case DataConstantUtils.DATA_TYPE_DISTANCE:
                name = context.getResources().getString(R.string.module_distance);
                break;
            case DataConstantUtils.DATA_TYPE_PRESSURE_SPORT:
                name = context.getResources().getString(R.string.module_pressure_sport);
                break;
            case DataConstantUtils.DATA_TYPE_MAX_OXYGEN_UPTAKE:
                name = context.getResources().getString(R.string.module_max_oxygen_uptake);
                break;
            case DataConstantUtils.DATA_TYPE_CALENDAR_1:
                name = context.getString(R.string.module_calendar_style_1);
                break;
            case DataConstantUtils.DATA_TYPE_CALENDAR_2:
                name = context.getString(R.string.module_calendar_style_2);
                break;
            case DataConstantUtils.DATA_TYPE_CALENDAR_3:
                name = context.getString(R.string.module_calendar_style_3);
                break;
            case DataConstantUtils.DATA_TYPE_CALENDAR_4:
                name = context.getString(R.string.module_calendar_style_4);
                break;
            case DataConstantUtils.DATA_DAY:
                name = context.getString(R.string.module_day);
                break;
            case DataConstantUtils.DATA_WEEK:
                name = context.getString(R.string.module_week);
                break;
            case DataConstantUtils.DATA_TYPE_SECONDS_DEGREE:
                name = context.getString(R.string.module_health_seconds_degree);
                break;
            case DataConstantUtils.DATA_TYPE_MONTH_DAY:
                name = context.getString(R.string.module_day);
                break;
            default:
                name = context.getResources().getString(R.string.module_none);
                break;
        }
        return name;
    }

    /**
     * 功能列表界面获取正常状态下的图片资源
     *
     * @param context 上下文对象
     * @param subType 子类名称
     * @return 正常状态的图片资源
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Drawable getDataDrawableBySubType(@NonNull Context context, @NonNull String subType) {
        Drawable drawableRes = context.getResources().getDrawable(R.drawable.nothing_normal, null);
        switch (subType) {
            case DataConstantUtils.DATA_TYPE_STEP:
                drawableRes = context.getResources().getDrawable(R.drawable.step_normal, null);
                break;
            case DataConstantUtils.DATA_TYPE_CALORIES:
                drawableRes = context.getResources().getDrawable(R.drawable.kcal_normal, null);
                break;
            case DataConstantUtils.DATA_TYPE_HEART_RATE:
                drawableRes = context.getResources().getDrawable(R.drawable.heart_rate_normal, null);
                break;
            case DataConstantUtils.DATA_TYPE_AIR_QUALITY:
                drawableRes = context.getResources().getDrawable(R.drawable.aqi_normal, null);
                break;
            case DataConstantUtils.DATA_TYPE_WEATHER:
                drawableRes = context.getResources().getDrawable(R.drawable.weather_normal, null);
                break;
            case DataConstantUtils.DATA_TYPE_PRESSURE:
                drawableRes = context.getResources().getDrawable(R.drawable.pressure_normal, null);
                break;
            case DataConstantUtils.DATA_TYPE_BATTERY:
                drawableRes = context.getResources().getDrawable(R.drawable.battery_normal, null);
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_DAY:
            case DataConstantUtils.VALUE_TYPE_DATA_WEEK:
            case DataConstantUtils.DATA_TYPE_CALENDAR:
                drawableRes = context.getResources().getDrawable(R.drawable.calendar_normal, null);
                break;
            case DataConstantUtils.DATA_TYPE_SECOND_HAND:
                drawableRes = context.getResources().getDrawable(R.drawable.date_a1_normal, null);
                break;
            case DataConstantUtils.DATA_TYPE_MOON_PHASE:
                drawableRes = context.getResources().getDrawable(R.drawable.moon_normal, null);
                break;
            case DataConstantUtils.DATA_TYPE_SUNRISE_SUNSET:
                drawableRes = context.getResources().getDrawable(R.drawable.sunrise_normal, null);
                break;
            case DataConstantUtils.DATA_TYPE_SECONDS_DEGREE:
                drawableRes = context.getResources().getDrawable(R.drawable.second_normal, null);
                break;
            case DataConstantUtils.DATA_TYPE_CLIMB_STAIR:
                drawableRes = context.getResources().getDrawable(R.drawable.upstairs_normal, null);
                break;
            case DataConstantUtils.DATA_TYPE_PRESSURE_SPORT:
                drawableRes = context.getResources().getDrawable(R.drawable.pressure_normal, null);
                break;
            case DataConstantUtils.DATA_TYPE_DISTANCE:
                drawableRes = context.getResources().getDrawable(R.drawable.distance_normal, null);
                break;
            case DataConstantUtils.DATA_TYPE_ACTIVITY_HOUR:
                drawableRes = context.getResources().getDrawable(R.drawable.activity_normal, null);
                break;
            case DataConstantUtils.DATA_TYPE_MIDDLE_HIGH_INTENSITY:
                drawableRes = context.getResources().getDrawable(R.drawable.middle_high_strength_normal, null);
                break;
            case DataConstantUtils.DATA_TYPE_ACTIVITY_HOUR_TEXT:
                drawableRes = context.getResources().getDrawable(R.drawable.activity_normal, null);
                break;
            case DataConstantUtils.DATA_TYPE_MIDDLE_HIGH_INTENSITY_TEXT:
                drawableRes = context.getResources().getDrawable(R.drawable.middle_high_strength_normal, null);
                break;
            case DataConstantUtils.DATA_TYPE_MAX_OXYGEN_UPTAKE:
                drawableRes = context.getResources().getDrawable(R.drawable.max_oxygen_uptake_normal, null);
                break;
            case DataConstantUtils.DATA_TYPE_CALENDAR_1:
                drawableRes = context.getResources().getDrawable(R.drawable.date_d1_normal, null);
                break;
            case DataConstantUtils.DATA_TYPE_CALENDAR_2:
                drawableRes = context.getResources().getDrawable(R.drawable.date_b1_normal, null);
                break;
            case DataConstantUtils.DATA_TYPE_CALENDAR_3:
                drawableRes = context.getResources().getDrawable(R.drawable.date_c1_normal, null);
                break;
            case DataConstantUtils.DATA_TYPE_CALENDAR_4:
                drawableRes = context.getResources().getDrawable(R.drawable.date_a1_normal, null);
                break;
            default:
                break;
        }
        return drawableRes;
    }

    /**
     * 功能列表界面获取选中状态的图片资源
     *
     * @param context 上下文对象
     * @param subType 子类名称
     * @return 选中状态的图片资源
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Drawable getChoiceDataDrawableBySubType(@NonNull Context context, @NonNull String subType) {
        Drawable drawableRes = context.getResources().getDrawable(R.drawable.nothing_choice, null);
        switch (subType) {
            case DataConstantUtils.DATA_TYPE_STEP:
                drawableRes = context.getResources().getDrawable(R.drawable.step_choice, null);
                break;
            case DataConstantUtils.DATA_TYPE_CALORIES:
                drawableRes = context.getResources().getDrawable(R.drawable.kcal_choice, null);
                break;
            case DataConstantUtils.DATA_TYPE_HEART_RATE:
                drawableRes = context.getResources().getDrawable(R.drawable.heart_rate_choice, null);
                break;
            case DataConstantUtils.DATA_TYPE_AIR_QUALITY:
                drawableRes = context.getResources().getDrawable(R.drawable.aqi_choice, null);
                break;
            case DataConstantUtils.DATA_TYPE_WEATHER:
                drawableRes = context.getResources().getDrawable(R.drawable.weather_choice, null);
                break;
            case DataConstantUtils.DATA_TYPE_PRESSURE:
                drawableRes = context.getResources().getDrawable(R.drawable.pressure_choice, null);
                break;
            case DataConstantUtils.DATA_TYPE_BATTERY:
                drawableRes = context.getResources().getDrawable(R.drawable.battery_choice, null);
                break;
            case DataConstantUtils.DATA_TYPE_CALENDAR:
            case DataConstantUtils.VALUE_TYPE_DATA_DAY:
            case DataConstantUtils.VALUE_TYPE_DATA_WEEK:
                drawableRes = context.getResources().getDrawable(R.drawable.calendar_choice, null);
                break;
            case DataConstantUtils.DATA_TYPE_SECOND_HAND:
                drawableRes = context.getResources().getDrawable(R.drawable.date_a1_choice, null);
                break;
            case DataConstantUtils.DATA_TYPE_MOON_PHASE:
                drawableRes = context.getResources().getDrawable(R.drawable.moon_choice, null);
                break;
            case DataConstantUtils.DATA_TYPE_SUNRISE_SUNSET:
                drawableRes = context.getResources().getDrawable(R.drawable.sunrise_choice, null);
                break;
            case DataConstantUtils.DATA_TYPE_SECONDS_DEGREE:
                drawableRes = context.getResources().getDrawable(R.drawable.second_choice, null);
                break;
            case DataConstantUtils.DATA_TYPE_CALENDAR_1:
                drawableRes = context.getResources().getDrawable(R.drawable.date_d1_choice, null);
                break;
            case DataConstantUtils.DATA_TYPE_CALENDAR_2:
                drawableRes = context.getResources().getDrawable(R.drawable.date_b1_choice, null);
                break;
            case DataConstantUtils.DATA_TYPE_CALENDAR_3:
                drawableRes = context.getResources().getDrawable(R.drawable.date_c1_choice, null);
                break;
            case DataConstantUtils.DATA_TYPE_CALENDAR_4:
                drawableRes = context.getResources().getDrawable(R.drawable.date_a1_choice, null);
                break;
            default:
                break;
        }
        return drawableRes;
    }
}

