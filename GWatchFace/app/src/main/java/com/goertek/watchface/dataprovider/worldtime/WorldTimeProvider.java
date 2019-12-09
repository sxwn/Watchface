package com.goertek.watchface.dataprovider.worldtime;

import android.content.Intent;
import android.text.TextUtils;

import com.goertek.commonlib.provider.data.manager.IDataProvider;
import com.goertek.commonlib.provider.data.model.FloatRangeValue;
import com.goertek.commonlib.provider.data.model.IntRangeValue;
import com.goertek.commonlib.provider.data.util.DataConstantUtils;
import com.goertek.commonlib.utils.CalendarUtil;
import com.goertek.commonlib.utils.CommonConstantsUtil;
import com.goertek.commonlib.utils.ContextUtil;
import com.goertek.watchface.dataprovider.worldtime.utils.WorldTimeSharedPreferencesUtil;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class WorldTimeProvider implements IDataProvider {
    private static final String TAG = "WorldTimeProvider";

    private static final int MINUTE_DISTANCE = 60;

    private static final int TIME_DIFF = 1;

    private static final int TIME_EIGHTEEN = 18;

    private static final int TIME_SIX = 6;

    private static final int TIME_TWENTYFOUR = 24;

    public WorldTimeProvider() {
    }

    private String getCityCode() {
        return WorldTimeSharedPreferencesUtil.getWordTimeString("time_zone_zons_id", "");
    }

    private int getDayOrNight() {
        int digitHour = CalendarUtil.getDigitHour(this.getTimeZoneByStringId());
        return (digitHour < TIME_EIGHTEEN || digitHour > TIME_TWENTYFOUR) && (digitHour < 0 || digitHour > TIME_SIX) ? TIME_DIFF : 0;
    }

    private TimeZone getTimeZoneByStringId() {
        return TimeZone.getTimeZone(WorldTimeSharedPreferencesUtil.getWordTimeString("time_zone_string_id", TimeZone.getDefault().getID()));
    }

    public void doClick(String dateType) {
        if (TextUtils.isEmpty(dateType)) {
            return;
        }
        if (TextUtils.equals(dateType, DataConstantUtils.DATA_TYPE_WORLD_TIME)) {
            Intent intent = new Intent();
            intent.setAction(CommonConstantsUtil.ACTION_START_WORLD_TIME_SETTING);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            ContextUtil.getContext().startActivity(intent);
        }
    }

    public FloatRangeValue getFloatRangeValue(String valueType) {
        return new FloatRangeValue(0.0F, 0.0F, 0.0F);
    }

    public float getFloatValue(String valueType) {
        return 0.0F;
    }

    public int getIndexValue(String valueType) {

        if (TextUtils.isEmpty(valueType)) {
            return -1;
        }

        if (TextUtils.equals(valueType, DataConstantUtils.VALUE_TYPE_WORLD_TIME_MOON_OR_SUN)) {
            return getDayOrNight();
        } else {
            return 0;
        }
    }

    public IntRangeValue getIntRangeValue(String valueType) {

        if (TextUtils.isEmpty(valueType)) {
            return new IntRangeValue(0, 0, 0);
        }

        if (TextUtils.equals(valueType, DataConstantUtils.VALUE_TYPE_WORLD_TIME_HOUR)) {
            return new IntRangeValue(CalendarUtil.getDigitHour(this.getTimeZoneByStringId()) * 60 + CalendarUtil.getDigitMinute(this.getTimeZoneByStringId()), (Calendar.getInstance(this.getTimeZoneByStringId()).getMaximum(10) + 1) * 60, Calendar.getInstance(this.getTimeZoneByStringId()).getMinimum(10) * 60);
        } else if (TextUtils.equals(valueType, DataConstantUtils.VALUE_TYPE_WORLD_TIME_MINUTE)) {
            return new IntRangeValue(CalendarUtil.getDigitMinute(this.getTimeZoneByStringId()), Calendar.getInstance(this.getTimeZoneByStringId()).getMaximum(12) + 1, Calendar.getInstance(this.getTimeZoneByStringId()).getMinimum(12));
        } else {
            return new IntRangeValue(0, 0, 0);
        }
    }

    public int getIntValue(String valueType) {

        if (TextUtils.isEmpty(valueType)) {
            return 0;
        }

        if (TextUtils.equals(valueType, DataConstantUtils.VALUE_TYPE_WORLD_TIME_HOUR)) {
            return CalendarUtil.getDigitHour(this.getTimeZoneByStringId());
        }

        if (TextUtils.equals(valueType, DataConstantUtils.VALUE_TYPE_WORLD_TIME_MINUTE)) {
            return CalendarUtil.getDigitMinute(this.getTimeZoneByStringId());
        }
        return 0;
    }

    public int getLayerIndexValue(String valueType) {
        return 0;
    }

    public String getStringValue(String valueType) {

        if (TextUtils.isEmpty(valueType)) {
            return "";
        }

        switch (valueType) {
            case DataConstantUtils.VALUE_TYPE_WORLD_TIME_AM_PM:
                return CalendarUtil.getWordAmPm(this.getTimeZoneByStringId());

            case DataConstantUtils.VALUE_TYPE_WORLD_TIME_MINUTE:
                return String.valueOf(CalendarUtil.getDigitMinute(this.getTimeZoneByStringId()));

            case DataConstantUtils.VALUE_TYPE_WORLD_TIME_AM_PM_INDEX:
                return String.valueOf(CalendarUtil.getDigitAmPm(this.getTimeZoneByStringId()));

            case DataConstantUtils.VALUE_TYPE_WORLD_TIME_HOUR:
                return String.valueOf(CalendarUtil.getDigitHour(this.getTimeZoneByStringId()));
            case DataConstantUtils.VALUE_TYPE_WORLD_TIME_CITY_CODE:
                return this.getCityCode();
            default:
                return "";

        }
    }

    public String getStringValue(String valueType, String... format) {
        return "";
    }
}