package com.goertek.watchface.dataprovider.moon;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class MoonLunarUtil {
    private static final String TAG = "MoonLunarUtil";

    private static final int INDEX_TWO = 2;

    private static int mMoonCalendar;

    private MoonLunarUtil() {
    }

    public static int getMoonDay() {
        return mMoonCalendar;
    }

    public static void setMoonDay(int moonDay) {
        mMoonCalendar = moonDay;
    }

    public static int[] solarToLunar() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        return LunarCalendar.solarToLunar(calendar.get(1), calendar.get(2), calendar.get(5));
    }

    public static void updateMoonDay() {
        setMoonDay(solarToLunar()[2]);
    }
}

