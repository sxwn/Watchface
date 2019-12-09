package com.goertek.watchface.dataprovider.sportshealth.controller;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.RemoteException;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.goertek.commonlib.provider.data.model.IntRangeValue;
import com.goertek.commonlib.utils.LogUtil;
import com.goertek.watchface.dataprovider.sportshealth.bean.CalorieDataBean;
import com.goertek.watchface.dataprovider.sportshealth.bean.HeartRateDataBean;
import com.goertek.watchface.dataprovider.sportshealth.bean.ParseUtil;
import com.goertek.watchface.dataprovider.sportshealth.bean.SportTimeDataBean;
import com.goertek.watchface.dataprovider.sportshealth.bean.StandDataBean;
import com.goertek.watchface.dataprovider.sportshealth.bean.StepDataBean;

import java.util.Map;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class SportsHealthController {
    private static final String APPLICATION_ID_HEALTH = "com.huawei.health";
    private static final int CALORIES_MAX_DEFAULT_VAULE = 334;
    private static final int CURR = 3;
    private static final int DELAY_REGISTER_TIME = 200;
    private static final int HEAT_RATE_NUM = 5;
    private static final int MIN_SPORTS_NUM = 0;
    private static final String NODATA_STR = "--";
    private static final String NO_VALUE_STR = "-1";
    private static final int SERVICE_PROAD_TIME = 5000;
    private static final String TAG = "SportsHealthController";
    private static SportsHealthController instance;
    private Handler handler = new Handler();


    private boolean isConnectSuccess = false;
    private CalorieDataBean mCalorieBean;
    private int mCalorieMaxVaule;
    private Context mContext;
    private HeartRateDataBean mHeartBean;
    private SportTimeDataBean mSportTimeBean;
    private StandDataBean mStandBean;
    private StepDataBean mStepDataBean;


    private SportsHealthController() {
    }

    @NonNull
    private String getHeatRateString(String valueType) {
        return (!TextUtils.isEmpty(valueType) && !TextUtils.equals("-1", valueType)) ? valueType : "--";
    }

    public static SportsHealthController getInstance() {

        synchronized (SportsHealthController.class) {
            if (instance == null) {
                instance = new SportsHealthController();
            }

            return instance;
        }
}

    private int getIntValue(String valueType) {
        try {
            int intValue = Integer.parseInt(valueType);
            return intValue;
        } catch (NumberFormatException var3) {
            LogUtil.e("SportsHealthController", "method getIntValue() catch NumberFormatException");
            return 0;
        }
    }

    private int getStepValue(int min, int max, int value, int step) {
        float steps = (float) (max - min) / (float) step;
        StringBuilder var6 = new StringBuilder();
        var6.append("step=== ");
        var6.append(steps);
        LogUtil.d("SportsHealthController", var6.toString());

        for (min = 0; min <= step && (float) value > (float) min * steps; ++min) {
        }

        var6 = new StringBuilder();
        var6.append("index ===");
        var6.append(min);
        LogUtil.d("SportsHealthController", var6.toString());
        return min;
    }

    private String getTestCurrAndTarget() {
        return "--/--";
    }

    private String getTestCurrStr() {
        return "50";
    }

    private int getTestCurrentIntData() {
        return 50;
    }

    private IntRangeValue getTestRangeData() {
        return new IntRangeValue(50, 100, 0);
    }

    private String getValueFromMap(Map var1, String var2) {
        if (var1 == null) {
            return "0";
        } else {
            Object var3 = var1.get(var2);
            return var3 != null && var3 instanceof String ? (String) var3 : "0";
        }
    }

    private boolean isNnmber(String var1) {
        try {
            Integer.valueOf(var1);
            return true;
        } catch (NumberFormatException var2) {
            return false;
        }
    }

    private void logExceptionName(String var1, String var2) {
        StringBuilder var3 = new StringBuilder();
        var3.append(var1);
        var3.append(" has catch ");
        var3.append(var2);
        LogUtil.e("SportsHealthController", var3.toString());
    }

    private int parseString2Int(String var1) {
        if (!TextUtils.isEmpty(var1) && !var1.equals("null")) {
            if (TextUtils.equals("-1", var1)) {
                return 0;
            } else {
                try {
                    int var2 = Integer.parseInt(var1);
                    return var2;
                } catch (NumberFormatException var3) {
                    this.logExceptionName("parseString2Int", "NumberFormatException");
                    return 0;
                }
            }
        } else {
            return 0;
        }
    }

    private void setCurrentHeartRate(String var1) {
        if (this.mHeartBean == null) {
            this.mHeartBean = new HeartRateDataBean();
        }

        this.mHeartBean.setCurrentHeartRate(var1);
        if (TextUtils.equals("-1", var1)) {
            this.mHeartBean.setMaxHeartRate("--");
            this.mHeartBean.setMinHeartRate("--");
            this.mHeartBean.setRestHeartRate("--");
            this.mHeartBean.setCurrentHeartRate("--");
        }

    }

    private void setHeartRate(String heartRate) {
        if (this.mHeartBean == null) {
            this.mHeartBean = new HeartRateDataBean();
        }

        this.mHeartBean.setCurrentHeartRate(heartRate);
        this.mHeartBean.setMaxHeartRate(heartRate);
        this.mHeartBean.setMinHeartRate(heartRate);
        this.mHeartBean.setRestHeartRate(heartRate);
    }


    public IntRangeValue getCalorieRange() {
        IntRangeValue intRangeValue = this.getTestRangeData();
        /*CalorieDataBean var3 = this.mCalorieBean;
        if (var3 != null) {
            return new IntRangeValue(this.parseString2Int(var3.getCurrentCalorie()), this.mCalorieMaxVaule, 0);
        } else {
            label47: {
                label52: {
                    int var1;
                    boolean var10001;
                    try {
                        var1 = HWWearableClientAPI.getInstance().getCaloriesInfo();
                        this.mCalorieBean = new CalorieDataBean();
                        this.mCalorieBean.setCurrentCalorie(String.valueOf(var1));
                    } catch (NoBinderException var10) {
                        var10001 = false;
                        break label47;
                    } catch (RemoteException var11) {
                        var10001 = false;
                        break label52;
                    }

                    if (var1 > 334) {
                        try {
                            this.mCalorieBean.setTargetCalorie(String.valueOf(var1));
                        } catch (NoBinderException var8) {
                            var10001 = false;
                            break label47;
                        } catch (RemoteException var9) {
                            var10001 = false;
                            break label52;
                        }
                    } else {
                        try {
                            this.mCalorieBean.setTargetCalorie(String.valueOf(334));
                        } catch (NoBinderException var6) {
                            var10001 = false;
                            break label47;
                        } catch (RemoteException var7) {
                            var10001 = false;
                            break label52;
                        }
                    }

                    try {
                        IntRangeValue var12 = new IntRangeValue(this.parseString2Int(this.mCalorieBean.getCurrentCalorie()), this.parseString2Int(this.mCalorieBean.getTargetCalorie()), 0);
                        return var12;
                    } catch (NoBinderException var4) {
                        var10001 = false;
                        break label47;
                    } catch (RemoteException var5) {
                        var10001 = false;
                    }
                }

                this.logExceptionName("getCalorieRange", "RemoteException");
                return var2;
            }

            this.logExceptionName("getCalorieRange", "NoBinderException");*/
        return intRangeValue;
    }

    public int getCurrentHeatRateStep() {
        IntRangeValue intRangeValue = this.getHeatRange();
        if (intRangeValue != null) {
            int max = intRangeValue.getmMax();
            int min = intRangeValue.getmMin();
            int value = intRangeValue.getmValue();
            if (max != 0) {
                return this.getStepValue(min, max, value, 5);
            } else {
                LogUtil.d("SportsHealthController", "max is 0");
                return 0;
            }
        } else {
            LogUtil.d("SportsHealthController", "getHeatRange is null");
            return 0;
        }
    }

    public String getHealthCalorieCurrent() {
        String testCurrStr = this.getTestCurrStr();
        /*CalorieDataBean var3 = this.mCalorieBean;
        if (var3 != null) {
            return var3.getCurrentCalorie();
        } else {
            try {
                int var1 = HWWearableClientAPI.getInstance().getCaloriesInfo();
                this.mCalorieBean = new CalorieDataBean();
                this.mCalorieBean.setCurrentCalorie(String.valueOf(var1));
                String var6 = this.mCalorieBean.getCurrentCalorie();
                return var6;
            } catch (NoBinderException var4) {
                this.logExceptionName("getHealthCalorieCurrent", "NoBinderException");
                return var2;
            } catch (RemoteException var5) {
                this.logExceptionName("getHealthCalorieCurrent", "RemoteException");
                return var2;
            }
        }*/

        return testCurrStr;
    }

    public int getHealthCalorieCurrentInt() {
        int currentIntData = this.getTestCurrentIntData();
       /* String var3 = this.getHealthCalorieCurrent();
        int var1 = var2;
        if (!TextUtils.isEmpty(var3)) {
            var1 = var2;
            if (!TextUtils.equals("--", var3)) {
                var1 = this.getIntValue(var3);
            }
        }*/

        return currentIntData;
    }

    public String getHealthCalorieCurrentWithUnit() {
        String calorieCurrent = this.getHealthCalorieCurrent();
        /*if (TextUtils.isEmpty(var1)) {
            StringBuilder var3 = new StringBuilder();
            var3.append("0");
            var3.append(this.mContext.getString(2131755044));
            return var3.toString();
        } else {
            StringBuilder var2 = new StringBuilder();
            var2.append(var1);
            var2.append(this.mContext.getString(2131755044));
            return var2.toString();
        }*/

        return calorieCurrent;
    }

    public String getHealthClimCurrent() {
        return this.getTestCurrStr();
    }

    public int getHealthClimCurrentInt() {
        return this.getTestCurrentIntData();
    }

    public IntRangeValue getHealthClimRange() {
        return this.getTestRangeData();
    }

    public String getHealthHeartRateCurrent() {
        String currStr = this.getTestCurrStr();
        /*HeartRateDataBean var2 = this.mHeartBean;
        if (var2 != null) {
            var1 = var2.getCurrentHeartRate();
        }
*/
        return this.getHeatRateString(currStr);
    }

    public int getHealthHeartRateCurrentInt() {
        int currentIntData = this.getTestCurrentIntData();
        /*String var3 = this.getHealthHeartRateCurrent();
        int var1 = var2;
        if (!TextUtils.isEmpty(var3)) {
            var1 = var2;
            if (!TextUtils.equals("--", var3)) {
                var1 = this.getIntValue(var3);
            }
        }*/

        return currentIntData;
    }

    public String getHealthHeartRateMax() {
        String currStr = this.getTestCurrStr();
        HeartRateDataBean var2 = this.mHeartBean;
       /* if (var2 != null) {
            currStr = var2.getMaxHeartRate();
        }*/

        return this.getHeatRateString(currStr);
    }

    public String getHealthHeartRateMin() {
        String currStr = this.getTestCurrStr();
       /* HeartRateDataBean var2 = this.mHeartBean;
        if (var2 != null) {
            var1 = var2.getMinHeartRate();
        }*/

        return this.getHeatRateString(currStr);
    }

    public String getHealthSportTime() {
        String currAndTarget = this.getTestCurrAndTarget();
        /*if (this.mSportTimeBean != null) {
            StringBuilder var5 = new StringBuilder(0);
            var5.append(this.mSportTimeBean.getCurrentSportTime());
            var5.append("/");
            var5.append(this.mSportTimeBean.getTargetSportTime());
            return var5.toString();
        } else {
            try {
                this.mSportTimeBean = ParseUtil.parseMapToSportTimeDataBean(HWWearableClientAPI.getInstance().getIntensityInfo());
                StringBuilder var2 = new StringBuilder(0);
                var2.append(this.mSportTimeBean.getCurrentSportTime());
                var2.append("/");
                var2.append(this.mSportTimeBean.getTargetSportTime());
                String var6 = var2.toString();
                return var6;
            } catch (NoBinderException var3) {
                this.logExceptionName("getHealthSportTime", "NoBinderException");
                return var1;
            } catch (RemoteException var4) {
                this.logExceptionName("getHealthSportTime", "RemoteException");
                return var1;
            }
        }*/

        return currAndTarget;
    }

    public String getHealthSportTimeCurrent() {
        String currStr = this.getTestCurrStr();
        /*SportTimeDataBean var2 = this.mSportTimeBean;
        if (var2 != null) {
            return var2.getCurrentSportTime();
        } else {
            try {
                this.mSportTimeBean = ParseUtil.parseMapToSportTimeDataBean(HWWearableClientAPI.getInstance().getIntensityInfo());
                String var5 = this.mSportTimeBean.getCurrentSportTime();
                return var5;
            } catch (NoBinderException var3) {
                this.logExceptionName("getHealthSportTimeCurrent", "NoBinderException");
                return currStr;
            } catch (RemoteException var4) {
                this.logExceptionName("getHealthSportTimeCurrent", "RemoteException");
                return currStr;
            }
        }*/

        return currStr;
    }

    public int getHealthSportTimeCurrentInt() {
        int currentIntData = this.getTestCurrentIntData();
        /*String var3 = this.getHealthSportTimeCurrent();
        int var1 = currentIntData;
        if (!TextUtils.isEmpty(var3)) {
            var1 = currentIntData;
            if (!TextUtils.equals("--", var3)) {
                var1 = this.getIntValue(var3);
            }
        }*/

        return currentIntData;
    }

    public String getHealthStandCurrent() {
        String currStr = this.getTestCurrStr();
        /*StandDataBean var2 = this.mStandBean;
        if (var2 != null) {
            return var2.getCurrentStand();
        } else {
            try {
                this.mStandBean = ParseUtil.parseMapToStandDataBean(HWWearableClientAPI.getInstance().getStandNumberInfo());
                String var5 = this.mStandBean.getCurrentStand();
                return var5;
            } catch (NoBinderException var3) {
                this.logExceptionName("getHealthStandCurrent", "NoBinderException");
                return currStr;
            } catch (RemoteException var4) {
                this.logExceptionName("getHealthStandCurrent", "RemoteException");
                return currStr;
            }
        }*/
        return currStr;
    }

    public int getHealthStandCurrentInt() {
        int currentIntData = this.getTestCurrentIntData();
        /*String var3 = this.getHealthStandCurrent();
        int var1 = currentIntData;
        if (!TextUtils.isEmpty(var3)) {
            var1 = currentIntData;
            if (!TextUtils.equals("--", var3)) {
                var1 = this.getIntValue(var3);
            }
        }*/

        return currentIntData;
    }

    public String getHealthStep() {
        String currAndTarget = this.getTestCurrAndTarget();
        /*StringBuilder var2 = new StringBuilder(0);
        StepDataBean var3 = this.mStepDataBean;
        if (var3 != null) {
            var2.append(var3.getCurrentStep());
            var2.append("/");
            var2.append(this.mStepDataBean.getTargetStep());
            return var2.toString();
        } else {
            try {
                this.mStepDataBean = ParseUtil.parseMapToStepDataBean(HWWearableClientAPI.getInstance().getStepNumberInfo());
                var2.append(this.mStepDataBean.getCurrentStep());
                var2.append("/");
                var2.append(this.mStepDataBean.getTargetStep());
                String var6 = var2.toString();
                return var6;
            } catch (NoBinderException var4) {
                this.logExceptionName("getHealthStep", "NoBinderException");
                return currAndTarget;
            } catch (RemoteException var5) {
                this.logExceptionName("getHealthStep", "RemoteException");
                return currAndTarget;
            }
        }*/

        return currAndTarget;
    }

    public String getHealthStepCurrent() {
        String testCurrStr = this.getTestCurrStr();
        /*StepDataBean var2 = this.mStepDataBean;
        if (var2 != null) {
            return var2.getCurrentStep();
        } else {
            try {
                this.mStepDataBean = ParseUtil.parseMapToStepDataBean(HWWearableClientAPI.getInstance().getStepNumberInfo());
                String var5 = this.mStepDataBean.getCurrentStep();
                return var5;
            } catch (NoBinderException var3) {
                this.logExceptionName("getHealthStepCurrent", "NoBinderException");
                return testCurrStr;
            } catch (RemoteException var4) {
                this.logExceptionName("getHealthStepCurrent", "RemoteException");
                return testCurrStr;
            }
        }*/
        return testCurrStr;
    }

    public int getHealthStepCurrentInt() {
        int currentIntData = this.getTestCurrentIntData();
        /*String var3 = this.getHealthStepCurrent();
        int var1 = currentIntData;
        if (!TextUtils.isEmpty(var3)) {
            var1 = currentIntData;
            if (!TextUtils.equals("--", var3)) {
                var1 = this.getIntValue(var3);
            }
        }
*/
        return currentIntData;
    }

    public String getHealthStepCurrentWithUnit() {
        String healthStepCurrent = this.getHealthStepCurrent();
        /*if (TextUtils.isEmpty(healthStepCurrent)) {
            StringBuilder var3 = new StringBuilder();
            var3.append("0");
            var3.append(this.mContext.getString(2131755148));
            return var3.toString();
        } else {
            StringBuilder var2 = new StringBuilder();
            var2.append(healthStepCurrent);
            var2.append(this.mContext.getString(2131755148));
            return var2.toString();
        }*/

        return healthStepCurrent;
    }

    public IntRangeValue getHeatRange() {
        IntRangeValue rangeData = this.getTestRangeData();
       /* HeartRateDataBean var2 = this.mHeartBean;
        if (var2 != null) {
            rangeData = new IntRangeValue(this.parseString2Int(var2.getCurrentHeartRate()), this.parseString2Int(this.mHeartBean.getMaxHeartRate()), this.parseString2Int(this.mHeartBean.getMinHeartRate()));
        }*/

        return rangeData;
    }

    public IntRangeValue getSportTimeRange() {
        IntRangeValue rangeData = this.getTestRangeData();
        /*SportTimeDataBean var2 = this.mSportTimeBean;
        if (var2 != null) {
            return new IntRangeValue(this.parseString2Int(var2.getCurrentSportTime()), this.parseString2Int(this.mSportTimeBean.getTargetSportTime()), 0);
        } else {
            try {
                this.mSportTimeBean = ParseUtil.parseMapToSportTimeDataBean(HWWearableClientAPI.getInstance().getIntensityInfo());
                IntRangeValue var5 = new IntRangeValue(this.parseString2Int(this.mSportTimeBean.getCurrentSportTime()), this.parseString2Int(this.mSportTimeBean.getTargetSportTime()), 0);
                return var5;
            } catch (NoBinderException var3) {
                this.logExceptionName("getSportTimeRange", "NoBinderException");
                return rangeData;
            } catch (RemoteException var4) {
                this.logExceptionName("getSportTimeRange", "RemoteException");
                return rangeData;
            }
        }*/

        return rangeData;
    }

    public String getStandNumber() {
        String currAndTarget = this.getTestCurrAndTarget();
        /*if (this.mStandBean != null) {
            StringBuilder var5 = new StringBuilder(0);
            var5.append(this.mStandBean.getCurrentStand());
            var5.append("/");
            var5.append(this.mStandBean.getTargetStand());
            return var5.toString();
        } else {
            try {
                this.mStandBean = ParseUtil.parseMapToStandDataBean(HWWearableClientAPI.getInstance().getStandNumberInfo());
                StringBuilder var2 = new StringBuilder(0);
                var2.append(this.mStandBean.getCurrentStand());
                var2.append("/");
                var2.append(this.mStandBean.getTargetStand());
                String var6 = var2.toString();
                return var6;
            } catch (NoBinderException var3) {
                this.logExceptionName("getHealthSportTime", "NoBinderException");
                return currAndTarget;
            } catch (RemoteException var4) {
                this.logExceptionName("getHealthSportTime", "RemoteException");
                return currAndTarget;
            }
        }*/

        return currAndTarget;
    }

    public IntRangeValue getStandRange() {
        IntRangeValue testRangeData = this.getTestRangeData();
       /* StandDataBean var2 = this.mStandBean;
        if (var2 != null) {
            return new IntRangeValue(this.parseString2Int(var2.getCurrentStand()), this.parseString2Int(this.mStandBean.getTargetStand()), 0);
        } else {
            try {
                this.mStandBean = ParseUtil.parseMapToStandDataBean(HWWearableClientAPI.getInstance().getStandNumberInfo());
                IntRangeValue var5 = new IntRangeValue(this.parseString2Int(this.mStandBean.getCurrentStand()), this.parseString2Int(this.mStandBean.getTargetStand()), 0);
                return var5;
            } catch (NoBinderException var3) {
                this.logExceptionName("getStandRange", "NoBinderException");
                return var1;
            } catch (RemoteException var4) {
                this.logExceptionName("getStandRange", "RemoteException");
                return var1;
            }
        }*/

       return testRangeData;
    }

    public IntRangeValue getStepRange() {
        IntRangeValue rangeData = this.getTestRangeData();
        /*StepDataBean var2 = this.mStepDataBean;
        if (var2 != null) {
            return new IntRangeValue(this.parseString2Int(var2.getCurrentStep()), this.parseString2Int(this.mStepDataBean.getTargetStep()), 0);
        } else {
            try {
                this.mStepDataBean = ParseUtil.parseMapToStepDataBean(HWWearableClientAPI.getInstance().getStepNumberInfo());
                IntRangeValue var5 = new IntRangeValue(this.parseString2Int(this.mStepDataBean.getCurrentStep()), this.parseString2Int(this.mStepDataBean.getTargetStep()), 0);
                return var5;
            } catch (NoBinderException var3) {
                this.logExceptionName("getStepRange", "NoBinderException");
                return rangeData;
            } catch (RemoteException var4) {
                this.logExceptionName("getStepRange", "RemoteException");
                return rangeData;
            }
        }*/

        return rangeData;
    }

   /* public void gotoHealthApp(String path) {
        try {
            Intent var2 = new Intent("android.intent.action.MAIN");
            var2.addCategory("android.intent.category.LAUNCHER");
            var2.setFlags(268435456);
            var2.setComponent(new ComponentName("com.huawei.health", path));
            this.mContext.startActivity(var2);
        } catch (ActivityNotFoundException var3) {
             LogUtil.e("SportsHealthController", "gotoHealthApp:ActivityNotFoundException");
        }
    }*/

    /*public void init(Context var1) {
        this.mContext = var1;

        try {
            StringBuilder var2 = new StringBuilder();
            var2.append("getPackageName");
            var2.append(var1.getPackageName());
            HwLogUtil.i("SportsHealthController", var2.toString());
            HWWearableClientAPI.getInstance(this.callback).bindHWWearableService(var1, var1.getPackageName());
        } catch (NoFoundServiceException var3) {
            this.logExceptionName("init", "NoFoundServiceException");
            this.handler.postDelayed(this.runnable, 5000L);
        }
    }

    public void registerHeartSensor() {
        try {
            HWWearableClientAPI.getInstance().registerRealTimeHeartRateSensorCallback(this.iHeartRateRealTimeCallback);
        } catch (NoBinderException var2) {
            this.logExceptionName("registerHeartSensor", "NoBinderException");
        } catch (RemoteException var3) {
            this.logExceptionName("registerHeartSensor", "RemoteException");
        }
    }*/

    /*public void unregisterHeartSensor() {
        try {
            HWWearableClientAPI.getInstance().unregisterRealTimeHeartRateSensorCallback(this.iHeartRateRealTimeCallback);
        } catch (NoBinderException var2) {
            this.logExceptionName("unregisterHeartSensor", "NoBinderException");
        } catch (RemoteException var3) {
            this.logExceptionName("unregisterHeartSensor", "RemoteException");
        }
    }*/
}
