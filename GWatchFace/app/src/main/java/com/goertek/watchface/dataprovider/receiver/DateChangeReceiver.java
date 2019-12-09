package com.goertek.watchface.dataprovider.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;

import com.goertek.commonlib.utils.LogUtil;
import com.goertek.watchface.dataprovider.moon.MoonLunarUtil;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/30
 */
public class DateChangeReceiver extends BroadcastReceiver {
    private static final String TAG = "DateChangeReceiver";

    private static long mLastTimeMillis = System.currentTimeMillis();

    private static LinkedList<IDateChange> mListeners = new LinkedList();

    private static DateChangeReceiver sReceiver = null;

    public DateChangeReceiver() {
    }

    public static boolean addDateChangeListener(DateChangeReceiver.IDateChange iDateChange) {
        return iDateChange != null && !mListeners.contains(iDateChange) ? mListeners.add(iDateChange) : false;
    }

    private static long getLastTimeMillis() {
        return mLastTimeMillis;
    }

    public static void register(Context context) {
        if (context == null) {
            LogUtil.i("DateChangeReceiver", "consumption context is null");
        } else {
            sReceiver = new DateChangeReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addAction("android.intent.action.TIME_SET");
            filter.addAction("android.intent.action.DATE_CHANGED");
            context.registerReceiver(sReceiver, filter);
            LogUtil.i("DateChangeReceiver", "consumption register success");
        }
    }

    public static boolean removeDateChangeListener(DateChangeReceiver.IDateChange dateChange) {
        return dateChange != null && mListeners.contains(dateChange) ? mListeners.remove(dateChange) : false;
    }

    private static void setLastTimeMillis(long var0) {
        mLastTimeMillis = var0;
    }

    public static void unregister(Context context) {
        if (context != null) {
            DateChangeReceiver dateChangeReceiver = sReceiver;
            if (dateChangeReceiver != null) {
                try {
                    context.unregisterReceiver(dateChangeReceiver);
                } catch (IllegalArgumentException var2) {
                    LogUtil.i("DateChangeReceiver", "consumption unregister() IllegalArgumentException");
                }

                LogUtil.i("DateChangeReceiver", "consumption unregister success");
                sReceiver = null;
                return;
            }
        }

        LogUtil.i("DateChangeReceiver", "consumption context or sReceiver is null");
    }

    public void onReceive(Context context, Intent intent) {
        LogUtil.d("DateChangeReceiver", "onReceive()");
        if (context != null) {
            if (intent != null) {
                String action = intent.getAction();
                if ("android.intent.action.TIME_SET".equals(action) || "android.intent.action.DATE_CHANGED".equals(action)) {
                    LogUtil.d("DateChangeReceiver", "in ACTION");
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    long currentTimeMillis = System.currentTimeMillis();
                    if (TextUtils.equals(simpleDateFormat.format(getLastTimeMillis()), simpleDateFormat.format(currentTimeMillis))) {
                        LogUtil.d("DateChangeReceiver", "OLD DATE");
                    } else {
                        Iterator iDateChangeIterator = mListeners.iterator();

                        while (iDateChangeIterator.hasNext()) {
                            ((DateChangeReceiver.IDateChange) iDateChangeIterator.next()).onDateChange();
                        }

                        MoonLunarUtil.updateMoonDay();
                        LogUtil.d("DateChangeReceiver", "DATE CHANGE chineseCalenderProviderUpdateRequester.requestUpdateAll()");
                    }

                    setLastTimeMillis(currentTimeMillis);
                }

            }
        }
    }

    public interface IDateChange {
        void onDateChange();
    }
}
