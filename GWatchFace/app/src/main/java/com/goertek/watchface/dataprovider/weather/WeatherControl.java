package com.goertek.watchface.dataprovider.weather;

import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.goertek.commonlib.provider.data.model.IntRangeValue;
import com.goertek.commonlib.utils.LogUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class WeatherControl {
    private static final String DEGREE_STR = "°";
    private static final String F_UNINT = "℉";
    private static final int HOURS_DAY_12 = 12;
    private static final int HOURS_DAY_24 = 24;
    private static final int LAYER_INDEX_ONE = 0;
    private static final int LAYER_INDEX_TWO = 1;
    private static final int MILLSECOND_SECOND_CHANGE = 1000;
    private static final int MINUTE_SECOND_CHANGE = 60;
    private static final String NOT_US_UNIT = "17";
    private static final int NUM_FOUR = 4;
    private static final int NUM_ONE = 1;
    private static final int NUM_THREE = 3;
    private static final int NUM_TWO = 2;
    private static final int NUM_ZERO = 0;
    private static final int PM_MAX = 300;
    private static final int PM_MIN = 0;
    private static final String S_UNIT = "℃";
    private static final String TAG = "WeatherControl";
    private static WeatherControl instance;
    private Context mContext;

    public WeatherControl() {
    }

    private int changeFloatStringToInt(String valueType) {
        return !TextUtils.isEmpty(valueType) && !TextUtils.equals("10000.0", valueType) ? Float.valueOf(valueType).intValue() : 0;
    }

    @NonNull
    private String changeFloatStringToIntString(String valueType) {
        return !TextUtils.isEmpty(valueType) && !TextUtils.equals("10000.0", valueType) ? String.valueOf(Float.valueOf(valueType).intValue()) : "--";
    }

    private String convertLongTimeToStr(long var1, String var3) {
        return (new SimpleDateFormat(var3)).format(new Date(var1 * 1000L));
    }

    private long getCurrTimeSecond() {
        return Calendar.getInstance().getTimeInMillis() / 1000L;
    }

    public static WeatherControl getInstance() {

        synchronized (WeatherControl.class) {
            if (instance == null) {
                instance = new WeatherControl();
            }
        }
        return instance;
    }

    private long getSunRiseTime() {
       /* OneDayWeatherData var1 = this.mOneDayWeatherData;
        if (var1 != null) {
            return Long.parseLong(var1.getSunRise());
        } else {
            var1 = WeatherSharedPreferencesUtil.getOneDayWeatherFromSp(this.mContext);
            if (var1 != null && !var1.isInvalid()) {
                this.mOneDayWeatherData = var1;
                return Long.parseLong(this.mOneDayWeatherData.getSunRise());
            } else {
                return 0L;
            }
        }*/

        return 0L;
    }

    private long getSunSetTime() {
        /*OneDayWeatherData var1 = this.mOneDayWeatherData;
        if (var1 != null) {
            return Long.parseLong(var1.getSunSet());
        } else {
            var1 = WeatherSharedPreferencesUtil.getOneDayWeatherFromSp(this.mContext);
            if (var1 != null && !var1.isInvalid()) {
                this.mOneDayWeatherData = var1;
                return Long.parseLong(this.mOneDayWeatherData.getSunSet());
            } else {
                return 0L;
            }
        }*/
        return 0L;
    }

    private boolean isDay(long var1, long var3, long var5) {
        return var1 > var3 && var1 <= var5;
    }

   /* public void destory() {
        this.mAirQualityShowBean = null;
        this.mCurrentWeatherShowBean = null;
        this.mOneDayWeatherData = null;
        WeatherManager.getInstance(this.mContext).unregisterWeatherCallback(this);
        this.mContext = null;
    }*/

    public IntRangeValue getAirQualityPm25IntRange() {
        int pm25IntValue = this.getAirQualityPm25IntValue();
        int currValue = 300;
        if (pm25IntValue > 300) {
            currValue = pm25IntValue;
        }

        return new IntRangeValue(pm25IntValue, currValue, 0);
    }

    public int getAirQualityPm25IntValue() {
        /*AirQualityShowBean var1 = this.mAirQualityShowBean;
        String var3;
        if (var1 != null && !var1.isInvalid()) {
            var3 = this.mAirQualityShowBean.getPm25Value();
        } else {
            List var2 = Arrays.asList(WeatherSharedPreferencesUtil.getAirQualityFromSp(this.mContext));
            if (var2 != null && var2.size() == 2) {
                if (this.mAirQualityShowBean == null) {
                    this.mAirQualityShowBean = new AirQualityShowBean();
                }

                this.mAirQualityShowBean.setAirQualityText((String) var2.get(0));
                this.mAirQualityShowBean.setPm25Value((String) var2.get(1));
                var3 = (String) var2.get(1);
            } else {
                var3 = null;
            }
        }

        return this.changeFloatStringToInt(var3);*/

        return testIntData();
    }

    private int testIntData() {
        return 50;
    }

    private String testStringData() {
        return "50";
    }

    public String getAirQualityPm25Value() {
        /*AirQualityShowBean var1 = this.mAirQualityShowBean;
        String var3;
        if (var1 != null && !var1.isInvalid()) {
            var3 = this.mAirQualityShowBean.getPm25Value();
        } else {
            List var2 = Arrays.asList(WeatherSharedPreferencesUtil.getAirQualityFromSp(this.mContext));
            if (var2 != null && var2.size() == 2) {
                if (this.mAirQualityShowBean == null) {
                    this.mAirQualityShowBean = new AirQualityShowBean();
                }

                this.mAirQualityShowBean.setAirQualityText((String) var2.get(0));
                this.mAirQualityShowBean.setPm25Value((String) var2.get(1));
                var3 = (String) var2.get(1);
            } else {
                var3 = null;
            }
        }

        return this.changeFloatStringToIntString(var3);*/

        return testStringData();
    }

    public String getAirQualityPm25ValueOffset(int var1) {
        String var2 = this.getAirQualityPm25Value();
        return !TextUtils.isEmpty(var2) ? String.valueOf(Float.valueOf(var2).intValue() + var1) : null;
    }

    public String getAirQualityText() {
       /* AirQualityShowBean var1 = this.mAirQualityShowBean;
        if (var1 != null && !var1.isInvalid()) {
            return this.mAirQualityShowBean.getAirQualityText();
        } else {
            List var2 = Arrays.asList(WeatherSharedPreferencesUtil.getAirQualityFromSp(this.mContext));
            if (var2 != null && var2.size() == 2) {
                if (this.mAirQualityShowBean == null) {
                    this.mAirQualityShowBean = new AirQualityShowBean();
                }

                this.mAirQualityShowBean.setAirQualityText((String) var2.get(0));
                this.mAirQualityShowBean.setPm25Value((String) var2.get(1));
                return (String) var2.get(0);
            } else {
                return null;
            }
        }*/

        return testStringData();
    }

    public String getCurrentTempDreeAndUnit() {
        String var1 = this.getCurrentTempValue();
        String var2 = TAG;
        StringBuilder var3 = new StringBuilder();
        var3.append("currTemp===");
        var3.append(var1);
        var3.append("==");
        var3.append(this.getCurrentTempUnit());
        LogUtil.d(var2, var3.toString());
        StringBuilder var4 = new StringBuilder();
        var4.append(var1);
        if (TextUtils.equals("17", this.getCurrentTempUnit())) {
            var1 = "℃";
        } else {
            var1 = "℉";
        }

        var4.append(var1);
        return var4.toString();
    }

    public int getCurrentTempIntValue() {
        /*CurrentWeatherShowBean var1 = this.mCurrentWeatherShowBean;
        String var3;
        if (var1 != null && !var1.isInvalid()) {
            var3 = this.mCurrentWeatherShowBean.getTemperature();
        } else {
            List var2 = Arrays.asList(WeatherSharedPreferencesUtil.getCurrentWeatherFromSp(this.mContext));
            if (var2 != null && var2.size() == 4) {
                if (this.mCurrentWeatherShowBean == null) {
                    this.mCurrentWeatherShowBean = new CurrentWeatherShowBean();
                }

                this.mCurrentWeatherShowBean.setmWeatherIcon((String) var2.get(0));
                this.mCurrentWeatherShowBean.setmTemperature((String) var2.get(1));
                this.mCurrentWeatherShowBean.setmUnitType((String) var2.get(2));
                var3 = (String) var2.get(1);
            } else {
                var3 = null;
            }
        }*/

        return this.changeFloatStringToInt("40");
    }

    public String getCurrentTempUnit() {
        /*String var2 = "";
        CurrentWeatherShowBean var1 = this.mCurrentWeatherShowBean;
        if (var1 != null && !var1.isInvalid()) {
            return this.mCurrentWeatherShowBean.getUnitType();
        } else {
            List var3 = Arrays.asList(WeatherSharedPreferencesUtil.getCurrentWeatherFromSp(this.mContext));
            String var4 = var2;
            if (var3 != null) {
                var4 = var2;
                if (var3.size() == 4) {
                    if (this.mCurrentWeatherShowBean == null) {
                        this.mCurrentWeatherShowBean = new CurrentWeatherShowBean();
                    }

                    this.mCurrentWeatherShowBean.setmWeatherIcon((String) var3.get(0));
                    this.mCurrentWeatherShowBean.setmTemperature((String) var3.get(1));
                    this.mCurrentWeatherShowBean.setmUnitType((String) var3.get(2));
                    var4 = (String) var3.get(2);
                }
            }

            return var4;
        }*/

        return testStringData();
    }

    public String getCurrentTempValue() {
        /*CurrentWeatherShowBean var1 = this.mCurrentWeatherShowBean;
        String var3;
        if (var1 != null && !var1.isInvalid()) {
            var3 = this.mCurrentWeatherShowBean.getTemperature();
        } else {
            List var2 = Arrays.asList(WeatherSharedPreferencesUtil.getCurrentWeatherFromSp(this.mContext));
            if (var2 != null && var2.size() == 4) {
                if (this.mCurrentWeatherShowBean == null) {
                    this.mCurrentWeatherShowBean = new CurrentWeatherShowBean();
                }

                this.mCurrentWeatherShowBean.setmWeatherIcon((String) var2.get(0));
                this.mCurrentWeatherShowBean.setmTemperature((String) var2.get(1));
                this.mCurrentWeatherShowBean.setmUnitType((String) var2.get(2));
                var3 = (String) var2.get(1);
            } else {
                var3 = null;
            }
        }*/

        return this.changeFloatStringToIntString(testStringData());
    }

    public String getCurrentTempValueOffset(int var1) {
        String var2 = getInstance().getCurrentTempValue();
        return !TextUtils.isEmpty(var2) && !TextUtils.equals("--", var2) ? String.valueOf(Float.valueOf(var2).intValue() + var1) : "--";
    }

    public String getCurrentTempValueWithDegree() {
        String var1 = getInstance().getCurrentTempValue();
        if (!TextUtils.isEmpty(var1) && !TextUtils.equals("--", var1)) {
            StringBuilder var2 = new StringBuilder();
            var2.append(var1);
            var2.append("°");
            return var2.toString();
        } else {
            return "--";
        }
    }

    public String getCurrentWeatherIcon() {
        /*CurrentWeatherShowBean var1 = this.mCurrentWeatherShowBean;
        if (var1 != null && !var1.isInvalid()) {
            return this.mCurrentWeatherShowBean.getWeatherIcon();
        } else {
            List var2 = Arrays.asList(WeatherSharedPreferencesUtil.getCurrentWeatherFromSp(this.mContext));
            if (var2 != null && var2.size() == 4) {
                if (this.mCurrentWeatherShowBean == null) {
                    this.mCurrentWeatherShowBean = new CurrentWeatherShowBean();
                }

                this.mCurrentWeatherShowBean.setmWeatherIcon((String) var2.get(0));
                this.mCurrentWeatherShowBean.setmTemperature((String) var2.get(1));
                this.mCurrentWeatherShowBean.setmUnitType((String) var2.get(2));
                return (String) var2.get(0);
            } else {
                return null;
            }
        }*/

        return "0";
    }

    public String getHigherTemp() {
       /* OneDayWeatherData var1 = this.mOneDayWeatherData;
        String var2;
        if (var1 != null) {
            var2 = var1.getHighTemperature();
        } else {
            var1 = WeatherSharedPreferencesUtil.getOneDayWeatherFromSp(this.mContext);
            if (var1 != null && !var1.isInvalid()) {
                this.mOneDayWeatherData = var1;
                var2 = this.mOneDayWeatherData.getHighTemperature();
            } else {
                var2 = null;
            }
        }*/

        return this.changeFloatStringToIntString(testStringData());
    }

    public int getHigherTempIntValue() {
        /*OneDayWeatherData var1 = this.mOneDayWeatherData;
        String var2;
        if (var1 != null) {
            var2 = var1.getHighTemperature();
        } else {
            var1 = WeatherSharedPreferencesUtil.getOneDayWeatherFromSp(this.mContext);
            if (var1 != null && !var1.isInvalid()) {
                this.mOneDayWeatherData = var1;
                var2 = this.mOneDayWeatherData.getHighTemperature();
            } else {
                var2 = null;
            }
        }*/

        return this.changeFloatStringToInt(testStringData());
    }

    public String getHigherTempWithDegree() {
        String var1 = getInstance().getHigherTemp();
        if (!TextUtils.isEmpty(var1) && !TextUtils.equals("--", var1)) {
            StringBuilder var2 = new StringBuilder();
            var2.append(var1);
            var2.append("°");
            return var2.toString();
        } else {
            return "--";
        }
    }

    public String getLowerTemp() {
        /*OneDayWeatherData var1 = this.mOneDayWeatherData;
        String var2;
        if (var1 != null) {
            var2 = var1.getLowTemperature();
        } else {
            var1 = WeatherSharedPreferencesUtil.getOneDayWeatherFromSp(this.mContext);
            if (var1 != null && !var1.isInvalid()) {
                this.mOneDayWeatherData = var1;
                var2 = this.mOneDayWeatherData.getLowTemperature();
            } else {
                var2 = null;
            }
        }*/

        return this.changeFloatStringToIntString("0");
    }

    public int getLowerTempIntValue() {
        /*OneDayWeatherData var1 = this.mOneDayWeatherData;
        String var4;
        if (var1 != null) {
            var4 = var1.getLowTemperature();
        } else {
            var1 = WeatherSharedPreferencesUtil.getOneDayWeatherFromSp(this.mContext);
            if (var1 != null && !var1.isInvalid()) {
                this.mOneDayWeatherData = var1;
                var4 = this.mOneDayWeatherData.getLowTemperature();
            } else {
                var4 = null;
            }
        }

        String var2 = TAG;
        StringBuilder var3 = new StringBuilder();
        var3.append("getLowerTempIntValue===");
        var3.append(var4);
        HwLogUtil.d(var2, var3.toString());*/
        return this.changeFloatStringToInt("0");
    }

    public String getLowerTempWithDegree() {
        String var1 = getInstance().getLowerTemp();
        if (!TextUtils.isEmpty(var1) && !TextUtils.equals("--", var1)) {
            StringBuilder var2 = new StringBuilder();
            var2.append(var1);
            var2.append("°");
            return var2.toString();
        } else {
            return "--";
        }
    }

    public String getSunRiseOrSunSetTime() {
        long var1 = this.getSunRiseTime();
        long var3 = this.getSunSetTime();
        return this.isDay(this.getCurrTimeSecond(), var1, var3) ? this.convertLongTimeToStr(var3, "HH:mm") : this.convertLongTimeToStr(var1, "HH:mm");
    }

    public int getSunRiseSunSetLayer(int index) {
        return 0;
    }

    public IntRangeValue getSunRiseTimeRange() {
        /*long var1 = this.getSunRiseTime();
        Calendar var3 = Calendar.getInstance();
        var3.setTimeInMillis(var1 * 1000L);*/
        /*return new IntRangeValue(720 - (var3.get(11) * 60 + var3.get(12)), 720, 0);*/
        return new IntRangeValue(500, 720, 0);
    }

    public IntRangeValue getSunSetTimeRange() {
        /*long var1 = this.getSunSetTime();
        Calendar var3 = Calendar.getInstance();
        var3.setTimeInMillis(var1 * 1000L);
        return new IntRangeValue(var3.get(11) * 60 + var3.get(12), 1440, 720);*/

        return new IntRangeValue(800, 1440, 720);
    }

    public int getSunriseOrSunSetIcon() {
        long var1 = this.getSunRiseTime();
        long var3 = this.getSunSetTime();
        return this.isDay(this.getCurrTimeSecond(), var1, var3) ? 1 : 0;
    }

    public IntRangeValue getWeatherTempIntRange() {
        int var1 = this.getLowerTempIntValue();
        int var2 = this.getHigherTempIntValue();
        return new IntRangeValue(this.getCurrentTempIntValue(), var2, var1);
    }

    public void setContext(Context var1) {
        this.mContext = var1;
    }
}
