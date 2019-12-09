package com.goertek.watchface.dataprovider.datetime;

import android.text.TextUtils;

import com.goertek.commonlib.provider.data.manager.IDataProvider;
import com.goertek.commonlib.provider.data.model.FloatRangeValue;
import com.goertek.commonlib.provider.data.model.IntRangeValue;
import com.goertek.commonlib.provider.data.util.DataConstantUtils;
import com.goertek.commonlib.utils.CalendarUtil;
import com.goertek.commonlib.utils.LogUtil;
import com.goertek.commonlib.utils.WatchFaceUtil;

import java.util.Calendar;
import java.util.Locale;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class DateTimeProvider implements IDataProvider {
    private static final String TAG = "DateTimeProvider";
    private static final int DATE_DAY_INDEX_ONE = 1;
    private static final int DATE_DAY_INDEX_SECOND = 2;
    private static final int DAYS_OF_MONTH_THIRTY = 2;
    private static final int DAYS_OF_MONTH_THIRTY_ONE = 3;
    private static final int DAYS_OF_MONTH_TWENTY_EIGHT = 0;
    private static final int DAYS_OF_MONTH_TWENTY_NINE = 1;
    private static final int MINUTE_DISTANCE = 60;
    private static final int TIME_DIFF = 1;
    private static final int WORD_CAPITAL_TYPE = 2;
    private static final int WORD_IS_ABBR = 1;
    private static final int WORD_SUPPORT_CN = 0;

    public DateTimeProvider() {
    }

    private int getDaysOfMonthIndexValue() {
        int daysOfMonth = CalendarUtil.getDaysOfMonth();
        int indexValue = 0;
        switch (daysOfMonth) {
            case 28:
                return indexValue;
            case 29:
                indexValue = 1;
                break;
            case 30:
                indexValue = 2;
                break;
            case 31:
                indexValue = 3;
                break;
            default:
                return 0;
        }
        return indexValue;
    }

    public void doClick(String dateType) {
    }

    public FloatRangeValue getFloatRangeValue(String valueType) {
        return new FloatRangeValue(0.0F, 0.0F, 0.0F);
    }

    public float getFloatValue(String valueType) {
        return 0.0F;
    }

    public int getIndexValue(String valueType) {
        int indexValue;
        if (TextUtils.isEmpty(valueType)) {
            return 0;
        }
        switch (valueType) {
            case DataConstantUtils.VALUE_TYPE_DATA_AMPM:
                indexValue = 2;
                break;

            case DataConstantUtils.VALUE_TYPE_DATA_WORD_WEEK:
                indexValue = 1;
                break;

            case DataConstantUtils.VALUE_TYPE_DATA_DAYS_OF_MONTH_INDEX:
                indexValue = 3;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_WEEK:
                indexValue = 0;
                break;
            default: {
                indexValue = -1;
            }


        }

        if (valueType.equals(DataConstantUtils.VALUE_TYPE_DATA_AMPM)) {
            indexValue = 2;
        }

        if (valueType.equals(DataConstantUtils.VALUE_TYPE_DATA_WORD_WEEK)) {
            indexValue = 1;
        }

        if (valueType.equals(DataConstantUtils.VALUE_TYPE_DATA_DAYS_OF_MONTH_INDEX)) {
            indexValue = 3;
        }

        if (valueType.equals(DataConstantUtils.VALUE_TYPE_DATA_WEEK)) {
            indexValue = 0;
        }

        switch (indexValue) {
            case 0:
            case 1:
                return CalendarUtil.getDigitWeek() - 1;
            case 2:
                return CalendarUtil.getDigitAmPm();
            case 3:
                return this.getDaysOfMonthIndexValue();
            default:
                return 0;
        }
    }

    public IntRangeValue getIntRangeValue(String valueType) {
        int indexValue = 0;
        IntRangeValue rangeValue;
        if (TextUtils.isEmpty(valueType)) {
            return null;
        }

        rangeValue = new IntRangeValue(0, 0, 0);
        switch (valueType) {
            case DataConstantUtils.VALUE_TYPE_DATA_MINUTE:
                indexValue = 5;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_SECOND:
                indexValue = 7;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_HOUR:
                indexValue = 4;
                break;

            case DataConstantUtils.VALUE_TYPE_DATA_WEEK:
                indexValue = 1;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_DAY_BEFORE:
                indexValue = 3;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_MINUTE_SECOND:
                indexValue = 6;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_MONTH:
                break;

            case DataConstantUtils.DATA_DAY:
                indexValue = 2;
                break;

            default:
                indexValue = -1;
        }

        switch (indexValue) {
            case 0:
                return new IntRangeValue(CalendarUtil.getDigitMonth(), Calendar.getInstance().getMaximum(2), Calendar.getInstance().getMinimum(2));
            case 1:
                return new IntRangeValue(CalendarUtil.getDigitWeek(), Calendar.getInstance().getMaximum(7), Calendar.getInstance().getMinimum(7));
            case 2:
                return new IntRangeValue(CalendarUtil.getDigitDay(), Calendar.getInstance().getActualMaximum(5), Calendar.getInstance().getActualMinimum(5) - 1);
            case 3:
                return new IntRangeValue(CalendarUtil.getDigitDayBefore2(), Calendar.getInstance().getActualMaximum(5), Calendar.getInstance().getActualMinimum(5) - 1);
            case 4:
                return new IntRangeValue(CalendarUtil.getDigitHour() * 60 + CalendarUtil.getDigitMinute(), (Calendar.getInstance().getMaximum(10) + 1) * 60, Calendar.getInstance().getMinimum(10) * 60);
            case 5:
                return new IntRangeValue(CalendarUtil.getDigitMinute() * 60 + CalendarUtil.getDigitSecond(), (Calendar.getInstance().getMaximum(12) + 1) * 60, Calendar.getInstance().getMinimum(12) * 60);
            case 6:
                return new IntRangeValue(CalendarUtil.getDigitMinute(), Calendar.getInstance().getMaximum(12) + 1, Calendar.getInstance().getMinimum(12));
            case 7:
                int var4 = Calendar.getInstance().getMaximum(13);
                return new IntRangeValue(CalendarUtil.getDigitSecond(), var4 + 1, Calendar.getInstance().getMinimum(13));
            default:
                return rangeValue;
        }

    }

    public int getIntValue(String valueType) {
        int intValue = 0;
        if (TextUtils.isEmpty(valueType)) {
            return intValue;
        }
        switch (valueType) {
            case DataConstantUtils.VALUE_TYPE_DATA_MINUTE:
                intValue = 6;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_SECOND:
                intValue = 7;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_HOUR:
                intValue = 5;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_WEEK:
                intValue = 2;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_YEAR:
                intValue = 0;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_DAYS_OF_MONTH_INDEX:
                intValue = 8;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_AMPM:
                intValue = 4;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_MONTH:
                intValue = 1;
                break;
            case DataConstantUtils.DATA_DAY:
                intValue = 3;
                break;
            default:
                intValue = -1;
        }

        switch (intValue) {
            case 0:
                return CalendarUtil.getDigitYear();
            case 1:
                return CalendarUtil.getDigitMonth();
            case 2:
                return CalendarUtil.getDigitWeek();
            case 3:
                return CalendarUtil.getDigitDay();
            case 4:
                return CalendarUtil.getDigitAmPm();
            case 5:
                return CalendarUtil.getDigitHour();
            case 6:
                return CalendarUtil.getDigitMinute();
            case 7:
                return CalendarUtil.getDigitSecond();
            case 8:
                return this.getDaysOfMonthIndexValue();
            default:
                return 0;
        }
    }

    public int getLayerIndexValue(String valueType) {
        int indexValue;

        if (TextUtils.isEmpty(valueType)) {
            return -1;
        }

        switch (valueType) {
            case DataConstantUtils.VALUE_TYPE_AWEEK_HOUR_INDEX:
                indexValue = 2;
                break;
            case DataConstantUtils.VALUE_TYPE_AWEEK_MINUTE_INDEX:
                indexValue = 3;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_DAYS_OF_MONTH_INDEX:
                indexValue = 0;
                break;
            case DataConstantUtils.VALUE_TYPE_AWEEK_BACKGROUND_INDEX:
                indexValue = 1;
                break;
            default:
                indexValue = -1;
        }


        switch (indexValue) {
            case 0:
                return getDaysOfMonthIndexValue();
            case 1:
            case 2:
            case 3:
                return CalendarUtil.getDigitWeek() - 1;
            default:
                return 0;
        }
    }

    public String getStringValue(String valueType) {
        int stringValue;

        if (TextUtils.isEmpty(valueType)) {
            stringValue = -1;
        }

        switch (valueType) {
            case DataConstantUtils.VALUE_TYPE_DATA_HOUR_MINUTE:
                stringValue = 12;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_MINUTE:
                stringValue = 10;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_SECOND:
                stringValue = 11;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_HOUR:
                stringValue = 9;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_WEEK:
                stringValue = 2;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_YEAR:
                stringValue = 0;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_DAY_BEFORE:
                stringValue = 7;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_DAY_AFTER:
                stringValue = 8;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_DAY_FILL_IN_0:
                stringValue = 4;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_MONTH:
                stringValue = 1;
                break;
            case DataConstantUtils.DATA_DAY:
                stringValue = 3;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_DAY_FILL_IN_LEFT:
                stringValue = 5;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_DAY_FILL_IN_RIGHT:
                stringValue = 6;
                break;
            default:
                stringValue = -1;
                break;
        }

        StringBuilder stringBuilder;
        switch (stringValue) {
            case 0:
                return String.valueOf(CalendarUtil.getDigitYear());
            case 1:
                return String.valueOf(CalendarUtil.getDigitMonth());
            case 2:
                return String.valueOf(CalendarUtil.getDigitWeek());
            case 3:
                return String.valueOf(CalendarUtil.getDigitDay());
            case 4:
                if (CalendarUtil.getDigitDay() < 10) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("0");
                    stringBuilder.append(CalendarUtil.getDigitDay());
                    return stringBuilder.toString();
                }

                return String.valueOf(CalendarUtil.getDigitDay());
            case 5:
                if (CalendarUtil.getDigitDay() < 10) {
                    return "0";
                }

                return String.valueOf(CalendarUtil.getDigitDay()).substring(0, 1);
            case 6:
                if (CalendarUtil.getDigitDay() < 10) {
                    return String.valueOf(CalendarUtil.getDigitDay());
                }

                return String.valueOf(CalendarUtil.getDigitDay()).substring(1, 2);
            case 7:
                return String.valueOf(CalendarUtil.getDigitDayBefore());
            case 8:
                return String.valueOf(CalendarUtil.getDigitDayAfter());
            case 9:
                return CalendarUtil.getTextHour();
            case 10:
                return CalendarUtil.getTextMinute();
            case 11:
                return CalendarUtil.getTextSecond();
            case 12:
                stringBuilder = new StringBuilder();
                stringBuilder.append(CalendarUtil.getTextHour());
                stringBuilder.append(":");
                stringBuilder.append(CalendarUtil.getTextMinute());
                return stringBuilder.toString();
            default:
                return "";
        }
    }

    public String getStringValue(String valueType, String... format) {
        int stringValue;
        String value = "";
        if (TextUtils.isEmpty(valueType)) {
            stringValue = -1;
        }
        switch (valueType) {
            case DataConstantUtils.VALUE_TYPE_DATA_MONTH_DAY:
                stringValue = 4;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_WORD_WEEK_1:
                stringValue = 2;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_WORD_MONTH:
                stringValue = 0;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_WORD_WEEK:
                stringValue = 1;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_AMPM:
                stringValue = 3;
                break;
            default:
                stringValue = -1;
                break;
        }

        switch (stringValue) {
            case 0:
                value = CalendarUtil.getWordMonth(WatchFaceUtil.getBoolValue(format[1]));
                valueType = value;
                if (TextUtils.equals(format[2], "all")) {
                    valueType = value.toUpperCase(Locale.ENGLISH);
                }
                break;
            case 1:
                if (WatchFaceUtil.getBoolValue(format[0])) {
                    return CalendarUtil.getWordCNWeek();
                }

                value = CalendarUtil.getWordWeek(WatchFaceUtil.getBoolValue(format[1]));
                LogUtil.e(TAG,"value ======" + value);
                LogUtil.e(TAG,"format[2] ======" + value);
                valueType = value;
                if (TextUtils.equals(format[2], "true")) {
                    return value.toUpperCase(Locale.ENGLISH);
                }
                break;
            case 2:
                if (WatchFaceUtil.getBoolValue(format[0])) {
                    return CalendarUtil.getWordCNWeek1();
                }

                value = CalendarUtil.getWordWeek(WatchFaceUtil.getBoolValue(format[1]));
                valueType = value;
                if (TextUtils.equals(format[2], "all")) {
                    return value.toUpperCase(Locale.ENGLISH);
                }
                break;
            case 3:
                valueType = value;
                if (!CalendarUtil.is24HourTime()) {
                    if (WatchFaceUtil.getBoolValue(format[0])) {
                        return CalendarUtil.getWordCNAmPm();
                    }

                    return CalendarUtil.getWordAmPm();
                }
                break;
            case 4:
                value = CalendarUtil.getWordMonth(WatchFaceUtil.getBoolValue(format[1]));
                valueType = value;
                if (TextUtils.equals(format[2], "all")) {
                    valueType = value.toUpperCase(Locale.ENGLISH);
                }

                StringBuilder var5 = new StringBuilder();
                var5.append(valueType);
                var5.append(" ");
                var5.append(CalendarUtil.getDigitDay());
                return var5.toString();
            default:
                return "";
        }
        return valueType;
    }
}