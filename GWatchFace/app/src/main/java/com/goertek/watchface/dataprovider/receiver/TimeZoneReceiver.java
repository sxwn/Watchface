package com.goertek.watchface.dataprovider.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.goertek.commonlib.utils.LogUtil;
import com.goertek.watchface.dataprovider.moon.MoonLunarUtil;

import java.util.TimeZone;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class TimeZoneReceiver extends BroadcastReceiver {
    private static final String TAG = "TimeZoneReceiver";
    private static TimeZoneReceiver sReceiver;

    public TimeZoneReceiver() {
    }

    public static void register(Context context) {
        if (context == null) {
            LogUtil.i("TimeZoneReceiver", "consumption context is null");
        } else {
            sReceiver = new TimeZoneReceiver();
            IntentFilter var1 = new IntentFilter("android.intent.action.TIMEZONE_CHANGED");
            context.registerReceiver(sReceiver, var1);
            LogUtil.i("TimeZoneReceiver", "consumption register success");
        }
    }

    public static void unregister(Context context) {
        if (context != null) {
            TimeZoneReceiver sReceiver = TimeZoneReceiver.sReceiver;
            if (sReceiver != null) {
                try {
                    context.unregisterReceiver(sReceiver);
                } catch (IllegalArgumentException var2) {
                    LogUtil.i("TimeZoneReceiver", "unregister() IllegalArgumentException");
                }

                LogUtil.i("TimeZoneReceiver", "consumption unregister success");
                TimeZoneReceiver.sReceiver = null;
                return;
            }
        }

        LogUtil.i("TimeZoneReceiver", "consumption context or sReceiver is null");
    }

    public void onReceive(Context context, Intent intent) {
        String extra = intent.getStringExtra("time-zone");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onReceive() time-zone:");
        stringBuilder.append(extra);
        LogUtil.d("TimeZoneReceiver", stringBuilder.toString());
        if (extra == null) {
            LogUtil.e("TimeZoneReceiver", "onReceive() getStringExtra time-zone is null!");
        } else {
            TimeZone.setDefault(TimeZone.getTimeZone(extra));
            MoonLunarUtil.updateMoonDay();
        }
    }
}
