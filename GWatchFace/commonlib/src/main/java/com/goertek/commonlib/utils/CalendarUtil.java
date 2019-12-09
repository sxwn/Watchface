package com.goertek.commonlib.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.provider.Settings;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * 时间日期工具类
 *
 * @author ww
 * @version 1.0.0
 * @since 2019/07/29
 */
public class CalendarUtil {
    /**
     * 每秒毫秒值
     */
    public static final long MILLIS_PER_SECOND = 1000L;

    /**
     * 每分钟秒数
     */
    public static final long SECONDS_PER_MINUTE = 60L;

    /**
     * 每小时分钟数
     */
    public static final long MINUTES_PER_HOUR = 60L;

    /**
     * 半天的小时数
     */
    public static final long HOURS_PER_HALF_DAY = 12L;

    /**
     * 每周天数
     */
    public static final long DAYS_PER_WEEK = 7L;

    /**
     * 每年月数
     */
    public static final long MONTHS_PER_YEAR = 12L;

    private static final String WORD_MONTH_JANUARY = "January";

    private static final String WORD_MONTH_FEBRUARY = "February";

    private static final String WORD_MONTH_MARCH = "March";

    private static final String WORD_MONTH_APRIL = "April";

    private static final String WORD_MONTH_MAY = "May";

    private static final String WORD_MONTH_JUNE = "June";

    private static final String WORD_MONTH_JULY = "July";

    private static final String WORD_MONTH_AUGUST = "August";

    private static final String WORD_MONTH_SEPTEMBER = "September";

    private static final String WORD_MONTH_OCTOBER = "October";

    private static final String WORD_MONTH_NOVEMBER = "November";

    private static final String WORD_MONTH_DECEMBER = "December";

    private static final String WORD_ABBR_MONTH_JANUARY = "Jan";

    private static final String WORD_ABBR_MONTH_FEBRUARY = "Feb";

    private static final String WORD_ABBR_MONTH_MARCH = "Mar";

    private static final String WORD_ABBR_MONTH_APRIL = "Apr";

    private static final String WORD_ABBR_MONTH_MAY = "May";

    private static final String WORD_ABBR_MONTH_JUNE = "Jun";

    private static final String WORD_ABBR_MONTH_JULY = "Jul";

    private static final String WORD_ABBR_MONTH_AUGUST = "Aug";

    private static final String WORD_ABBR_MONTH_SEPTEMBER = "Sep";

    private static final String WORD_ABBR_MONTH_OCTOBER = "Oct";

    private static final String WORD_ABBR_MONTH_NOVEMBER = "Nov";

    private static final String WORD_ABBR_MONTH_DECEMBER = "Dec";

    private static final String WORD_WEEK_MONDAY = "Monday";

    private static final String WORD_WEEK_TUESDAY = "Tuesday";

    private static final String WORD_WEEK_WEDNESDAY = "Wednesday";

    private static final String WORD_WEEK_THURSDAY = "Thursday";

    private static final String WORD_WEEK_FRIDAY = "Friday";

    private static final String WORD_WEEK_SATURDAY = "Saturday";

    private static final String WORD_WEEK_SUNDAY = "Sunday";

    private static final String WORD_ABBR_WEEK_MONDAY = "Mon";

    private static final String WORD_ABBR_WEEK_TUESDAY = "Tue";

    private static final String WORD_ABBR_WEEK_WEDNESDAY = "Wed";

    private static final String WORD_ABBR_WEEK_THURSDAY = "Thu";

    private static final String WORD_ABBR_WEEK_FRIDAY = "Fri";

    private static final String WORD_ABBR_WEEK_SATURDAY = "Sat";

    private static final String WORD_ABBR_WEEK_SUNDAY = "Sun";

    private static final int DAY_HOUR_12 = 12;

    private static final ArrayList<String> MONTH_WORD_LIST = new ArrayList<>(0);

    private static final ArrayList<String> MONTH_WORD_ABBR_LIST = new ArrayList<>(0);

    private static final ArrayList<String> WEEK_WORD_LIST = new ArrayList<>(0);

    private static final ArrayList<String> WEEK_WORD_ABBR_LIST = new ArrayList<>(0);

    private static final ArrayList<String> WEEK_WORD_CN_LIST = new ArrayList<>(0);

    private static final ArrayList<String> WEEK_WORD_CN_1_LIST = new ArrayList<>(0);

    private static final ArrayList<String> WEEK_WORD_CN = new ArrayList(0);

    private static final ArrayList<String> WEEK_WORD_CN_1 = new ArrayList(0);

    private CalendarUtil() {
    }

    static {
        MONTH_WORD_LIST.add(WORD_MONTH_JANUARY);
        MONTH_WORD_LIST.add(WORD_MONTH_FEBRUARY);
        MONTH_WORD_LIST.add(WORD_MONTH_MARCH);
        MONTH_WORD_LIST.add(WORD_MONTH_APRIL);
        MONTH_WORD_LIST.add(WORD_MONTH_MAY);
        MONTH_WORD_LIST.add(WORD_MONTH_JUNE);
        MONTH_WORD_LIST.add(WORD_MONTH_JULY);
        MONTH_WORD_LIST.add(WORD_MONTH_AUGUST);
        MONTH_WORD_LIST.add(WORD_MONTH_SEPTEMBER);
        MONTH_WORD_LIST.add(WORD_MONTH_OCTOBER);
        MONTH_WORD_LIST.add(WORD_MONTH_NOVEMBER);
        MONTH_WORD_LIST.add(WORD_MONTH_DECEMBER);

        MONTH_WORD_ABBR_LIST.add(WORD_ABBR_MONTH_JANUARY);
        MONTH_WORD_ABBR_LIST.add(WORD_ABBR_MONTH_FEBRUARY);
        MONTH_WORD_ABBR_LIST.add(WORD_ABBR_MONTH_MARCH);
        MONTH_WORD_ABBR_LIST.add(WORD_ABBR_MONTH_APRIL);
        MONTH_WORD_ABBR_LIST.add(WORD_ABBR_MONTH_MAY);
        MONTH_WORD_ABBR_LIST.add(WORD_ABBR_MONTH_JUNE);
        MONTH_WORD_ABBR_LIST.add(WORD_ABBR_MONTH_JULY);
        MONTH_WORD_ABBR_LIST.add(WORD_ABBR_MONTH_AUGUST);
        MONTH_WORD_ABBR_LIST.add(WORD_ABBR_MONTH_SEPTEMBER);
        MONTH_WORD_ABBR_LIST.add(WORD_ABBR_MONTH_OCTOBER);
        MONTH_WORD_ABBR_LIST.add(WORD_ABBR_MONTH_NOVEMBER);
        MONTH_WORD_ABBR_LIST.add(WORD_ABBR_MONTH_DECEMBER);

        WEEK_WORD_LIST.add(WORD_WEEK_SUNDAY);
        WEEK_WORD_LIST.add(WORD_WEEK_MONDAY);
        WEEK_WORD_LIST.add(WORD_WEEK_TUESDAY);
        WEEK_WORD_LIST.add(WORD_WEEK_WEDNESDAY);
        WEEK_WORD_LIST.add(WORD_WEEK_THURSDAY);
        WEEK_WORD_LIST.add(WORD_WEEK_FRIDAY);
        WEEK_WORD_LIST.add(WORD_WEEK_SATURDAY);

        WEEK_WORD_ABBR_LIST.add(WORD_ABBR_WEEK_SUNDAY);
        WEEK_WORD_ABBR_LIST.add(WORD_ABBR_WEEK_MONDAY);
        WEEK_WORD_ABBR_LIST.add(WORD_ABBR_WEEK_TUESDAY);
        WEEK_WORD_ABBR_LIST.add(WORD_ABBR_WEEK_WEDNESDAY);
        WEEK_WORD_ABBR_LIST.add(WORD_ABBR_WEEK_THURSDAY);
        WEEK_WORD_ABBR_LIST.add(WORD_ABBR_WEEK_FRIDAY);
        WEEK_WORD_ABBR_LIST.add(WORD_ABBR_WEEK_SATURDAY);

        WEEK_WORD_CN_LIST.add(getStringFromRes("word_cn_week_sunday"));
        WEEK_WORD_CN_LIST.add(getStringFromRes("word_cn_week_monday"));
        WEEK_WORD_CN_LIST.add(getStringFromRes("word_cn_week_tuesday"));
        WEEK_WORD_CN_LIST.add(getStringFromRes("word_cn_week_wednesday"));
        WEEK_WORD_CN_LIST.add(getStringFromRes("word_cn_week_thursday"));
        WEEK_WORD_CN_LIST.add(getStringFromRes("word_cn_week_friday"));
        WEEK_WORD_CN_LIST.add(getStringFromRes("word_cn_week_saturday"));

        WEEK_WORD_CN_1_LIST.add(getStringFromRes("word_cn_week_sunday_1"));
        WEEK_WORD_CN_1_LIST.add(getStringFromRes("word_cn_week_monday_1"));
        WEEK_WORD_CN_1_LIST.add(getStringFromRes("word_cn_week_tuesday_1"));
        WEEK_WORD_CN_1_LIST.add(getStringFromRes("word_cn_week_wednesday_1"));
        WEEK_WORD_CN_1_LIST.add(getStringFromRes("word_cn_week_thursday_1"));
        WEEK_WORD_CN_1_LIST.add(getStringFromRes("word_cn_week_friday_1"));
        WEEK_WORD_CN_1_LIST.add(getStringFromRes("word_cn_week_saturday_1"));
    }

    private static String getStringFromRes(String resIdStr) {
        Context context = ContextUtil.getContext();
        if (context == null) {
            return "";
        }
        try {
            return context.getString(context.getResources().getIdentifier(resIdStr, "string", context
                                                                                                      .getPackageName()));
        } catch (Resources.NotFoundException exception) {
            LogUtil.e("CalendarUtil", "method getStringFromRes catch Resources.NotFoundException");
        }
        return "";
    }

    /**
     * 获取月份
     *
     * @param isAbbr 是否简写
     * @return 月份
     */
    public static String getWordMonth(boolean isAbbr) {
        int month = getDigitMonth() - 1;
        return isAbbr ? (MONTH_WORD_ABBR_LIST.get(month)) : (MONTH_WORD_LIST.get(month));
    }

    /**
     * 获取星期
     *
     * @param isAbbr 是否简写
     * @return 周
     */
    public static String getWordWeek(boolean isAbbr) {
        int week = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
        return isAbbr ? (WEEK_WORD_ABBR_LIST.get(week)) : (WEEK_WORD_LIST.get(week));
    }

    public static int getDigitWeek() {
        // the first week of day is SUNDAY, and it's value is 1
        int week = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        return (week == Calendar.SUNDAY) ? (CommonConstantsUtil.NUMBER_SEVEN) : (week - 1);
    }

    public static String getWordCnWeek() {
        int week = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
        return WEEK_WORD_CN_LIST.get(week);
    }

    public static String getWordCnWeek1() {
        int week = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
        return WEEK_WORD_CN_1_LIST.get(week);
    }

    public static int getDigitYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static int getDigitMonth() {
        // the first MONTH is JANUARY, and it's value is 0
        return Calendar.getInstance().get(Calendar.MONTH) + CommonConstantsUtil.NUMBER_ONE;
    }

    public static int getDigitDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public static int getDigitDayBefore() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -CommonConstantsUtil.NUMBER_ONE);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static int getDigitDayBefore2() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -CommonConstantsUtil.NUMBER_ONE);
        return cal.get(Calendar.DAY_OF_MONTH) > getDigitDay() ? 0 : cal.get(Calendar.DAY_OF_MONTH);
    }

    public static int getDigitDayAfter() {
        Calendar cl = Calendar.getInstance();
        cl.add(Calendar.DATE, 1);
        return cl.get(Calendar.DAY_OF_MONTH);
    }

    public static int getDaysOfMonth() {
        int month = Calendar.getInstance().get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.MARCH:
            case Calendar.MAY:
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.OCTOBER:
            case Calendar.DECEMBER:
                return CommonConstantsUtil.NUMBER_THIRTY_ONE;
            case Calendar.FEBRUARY:
                return ((getDigitYear() % (CommonConstantsUtil.NUMBER_FOUR)) == 0)
                               ? (CommonConstantsUtil.NUMBER_TWENTY_NINE)
                               : (CommonConstantsUtil.NUMBER_TWENTY_EIGHT);
            case Calendar.APRIL:
            case Calendar.JUNE:
            case Calendar.SEPTEMBER:
            case Calendar.NOVEMBER:
            default:
                return CommonConstantsUtil.NUMBER_THIRTY;
        }
    }

    public static int getDigitHour() {
        if (is24HourTime()) {
            return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        } else {
            int hour = Calendar.getInstance().get(Calendar.HOUR);
            if (hour == 0) {
                return DAY_HOUR_12;
            } else {
                return Calendar.getInstance().get(Calendar.HOUR);
            }
        }
    }

    public static int getDigitHour(TimeZone timeZone) {
        if (is24HourTime()) {
            return Calendar.getInstance(timeZone).get(Calendar.HOUR_OF_DAY);
        } else {
            return Calendar.getInstance(timeZone).get(Calendar.HOUR);
        }
    }

    public static String getTextHour() {
        int hour = getDigitHour();
        int num = CommonConstantsUtil.NUMBER_ZERO;
        return (hour < CommonConstantsUtil.NUMBER_TEN) ? (String.valueOf(num) + hour) : ("" + hour);
    }

    public static int getDigitMinute() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }

    public static int getDigitMinute(TimeZone timeZone) {
        return Calendar.getInstance(timeZone).get(Calendar.MINUTE);
    }

    public static String getTextMinute() {
        int minute = getDigitMinute();
        int num = CommonConstantsUtil.NUMBER_ZERO;
        return (minute < CommonConstantsUtil.NUMBER_TEN) ? (String.valueOf(num) + minute) : ("" + minute);
    }

    public static int getDigitSecond() {
        return Calendar.getInstance().get(Calendar.SECOND);
    }

    public static String getTextSecond() {
        int second = getDigitSecond();
        int num = CommonConstantsUtil.NUMBER_ZERO;
        return (second < CommonConstantsUtil.NUMBER_TEN) ? (String.valueOf(num) + second) : ("" + second);
    }

    public static long getCurTimeMillis() {
        Calendar mCalendar = Calendar.getInstance();
        return mCalendar.get(Calendar.HOUR) * CalendarUtil.MINUTES_PER_HOUR * CalendarUtil.SECONDS_PER_MINUTE
                       * CalendarUtil.MILLIS_PER_SECOND + mCalendar.get(Calendar.MINUTE) * CalendarUtil.SECONDS_PER_MINUTE
                                                                  * CalendarUtil.MILLIS_PER_SECOND + mCalendar.get(Calendar.SECOND) * CalendarUtil.MILLIS_PER_SECOND
                       + mCalendar.get(Calendar.MILLISECOND);
    }

    public static boolean is24HourTime() {
        ContentResolver resolver = ContextUtil.getContext().getContentResolver();
        String timeFormat = Settings.System.getString(resolver, Settings.System.TIME_12_24);
        return TextUtils.equals(timeFormat, "24");
    }

    public static int getDigitAmPm(TimeZone timeZone) {
        return Calendar.getInstance(timeZone).get(Calendar.AM_PM);
    }

    public static int getDigitAmPm() {
        return Calendar.getInstance().get(Calendar.AM_PM);
    }

    public static String getWordAmPm() {
        return (getDigitAmPm() == Calendar.AM) ? "AM" : "PM";
    }

    public static String getWordAmPm(TimeZone timeZone) {
        return (getDigitAmPm(timeZone) == Calendar.AM) ? "AM" : "PM";
    }

    public static String getWordCnAmPm() {
        return (getDigitAmPm() == Calendar.AM) ? "上午" : "下午";
    }

    public static String getWordCNWeek() {
        int index = Calendar.getInstance().get(7);
        return WEEK_WORD_CN.get(index - 1);
    }

    public static String getWordCNWeek1() {
        int index = Calendar.getInstance().get(7);
        return WEEK_WORD_CN_1.get(index - 1);
    }

    public static String getWordCNAmPm() {
        return getDigitAmPm() == 0 ? "上午" : "下午";
    }

}

