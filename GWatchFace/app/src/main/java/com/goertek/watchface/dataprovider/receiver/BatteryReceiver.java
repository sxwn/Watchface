package com.goertek.watchface.dataprovider.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;

import com.goertek.commonlib.utils.LogUtil;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/30
 */
public class BatteryReceiver extends BroadcastReceiver {
    private static final int MAX_BATTERY = 100;

    private static final String TAG = "BatteryReceiver";

    private static BatteryReceiver.BatteryDataChangedCallBack callBack;

    private static boolean isRegister;

    private static int sBatteryLevel;

    private static int sBatteryScale;

    private static boolean sCharging;

    private static BatteryReceiver sReceiver;

    private BatteryReceiver() {
    }

    public static boolean getCharging() {
        return sCharging;
    }

    public static void getPrimaryBatteryValue(Context context) {
        Intent intent = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"), "android.permission.BATTERY_STATS", (Handler) null);
        if (intent != null) {
            setsBatteryLevel(intent.getIntExtra("level", 0));
            int status = intent.getIntExtra("status", 1);
            if (status != 2 && status != 5) {
                setsCharging(false);
                return;
            }
            setsCharging(true);
        }
    }

    public static BatteryReceiver getReceiver() {
        if (sReceiver == null) {
            sReceiver = new BatteryReceiver();
        } else {
            return sReceiver;
        }
        return sReceiver;
    }

    public static int getsBatteryLevel() {
        return sBatteryLevel;
    }

    public static int getsBatteryScale() {
        return sBatteryScale;
    }

    public static boolean isRegister() {
        return isRegister;
    }

    public static void setDataChangedCallBack(BatteryReceiver.BatteryDataChangedCallBack batteryDataChangedCallBack) {
        callBack = batteryDataChangedCallBack;
    }

    public static void setIsRegister(boolean isRegister) {
        BatteryReceiver.isRegister = isRegister;
    }

    public static void setsBatteryLevel(int batterylevel) {
        BatteryReceiver.sBatteryLevel = batterylevel;
    }

    public static void setsBatteryScale(int batteryscale) {
        BatteryReceiver.sBatteryScale = batteryscale;
    }

    public static void setsCharging(boolean isCharging) {
        sCharging = isCharging;
    }

    public static void setsReceiver(BatteryReceiver batteryReceiver) {
        sReceiver = batteryReceiver;
    }

    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BATTERY_CHANGED".equals(intent.getAction())) {
            setsBatteryLevel(intent.getIntExtra("level", 0));
            if (getsBatteryScale() == 0) {
                setsBatteryScale(intent.getIntExtra("scale", 100));
            }

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("current battery is:");
            stringBuilder.append(sBatteryLevel);
            stringBuilder.append("% --- ");
            LogUtil.d("BatteryReceiver", stringBuilder.toString());
            int status = intent.getIntExtra("status", 1);
            if (status != 2 && status != 5) {
                setsCharging(false);
            } else {
                setsCharging(true);
            }

            stringBuilder = new StringBuilder();
            stringBuilder.append("current battery charging status:");
            stringBuilder.append(sCharging);
            LogUtil.d("BatteryReceiver", stringBuilder.toString());
        }
    }

    public void register(Context context) {
        if (!isRegister) {
            setsReceiver(getReceiver());
            IntentFilter intentFilter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
            Intent intent = context.registerReceiver(sReceiver, intentFilter);
            if (intent != null) {
                setsBatteryLevel(intent.getIntExtra("level", 0));
                setsBatteryScale(intent.getIntExtra("scale", 100));
            }

            LogUtil.i("BatteryReceiver", "consumption registerReceiver success");
            setIsRegister(true);
        }

    }

    public void unregister(Context context) {
        if (context != null) {
            BatteryReceiver batteryReceiver = sReceiver;
            if (batteryReceiver != null) {
                if (isRegister) {
                    context.unregisterReceiver(batteryReceiver);
                    setsReceiver(null);
                    LogUtil.i("BatteryReceiver", "consumption unregisterReceiver success");
                }

                setIsRegister(false);
                return;
            }
        }

        LogUtil.i("BatteryReceiver", "context or sReceiver is null");
    }

    public interface BatteryDataChangedCallBack {
        void onDataChanged();
    }
}
