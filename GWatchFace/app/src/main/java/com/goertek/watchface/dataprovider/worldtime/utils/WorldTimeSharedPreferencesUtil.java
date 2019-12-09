package com.goertek.watchface.dataprovider.worldtime.utils;

import android.content.SharedPreferences;

import com.goertek.commonlib.utils.ContextUtil;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class WorldTimeSharedPreferencesUtil {
    private static final String TAG = "WorldTimeSharedPreferen";

    public static final String WORLD_TIME_SHARED_PREFERENCES = "world_time";

    public static final String WORLD_TIME_TIMEZONE_CITY_CODE = "time_zone_zons_id";

    public static final String WORLD_TIME_TIMEZONE_STRING_ID = "time_zone_string_id";

    private WorldTimeSharedPreferencesUtil() {
    }

    public static void clearWorldTimeInfo() {
        SharedPreferences.Editor editor = ContextUtil.getDirectBootContext().getSharedPreferences("world_time", 0).edit();
        editor.clear();
        editor.apply();
    }

    public static String getWordTimeString(String cityCode, String id) {
        return ContextUtil.getDirectBootContext().getSharedPreferences("world_time", 0).getString(cityCode, id);
    }

    public static void saveWorldTimeString(String key, String cityCode) {
        SharedPreferences.Editor editor = ContextUtil.getDirectBootContext().getSharedPreferences("world_time", 0).edit();
        editor.putString(key, cityCode);
        editor.apply();
    }
}