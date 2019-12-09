package com.goertek.commonlib.utils;

import android.os.Build;
import android.util.Log;

/**
 * Log工具类
 *
 * @author ww
 * @version 1.0.0
 * @since 2019/07/08
 */
public class LogUtil {
    private static final String APK_TAG = "Watchface";

    private static final boolean IS_DEBUG;

    private static final boolean LOG_DEBUG = true;

    private static final boolean LOG_ERROR = true;

    private static final boolean LOG_INFO = true;

    private static final int LOG_LEVEL;

    private static final boolean LOG_OFF = false;

    private static final boolean LOG_VERBOSE = true;

    private static final boolean LOG_WARNING = true;

    static  {
        byte logLevel;
        IS_DEBUG = !"user".equals(Build.TYPE);
        if (IS_DEBUG) {
            logLevel = 2;
        } else {
            logLevel = 4;
        }
        LOG_LEVEL = logLevel;
    }

    public static void d(String strTag, String strMsg) {
        if (isLoggable(APK_TAG, 3)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(strTag);
            stringBuilder.append("::");
            stringBuilder.append(strMsg);
            Log.d(APK_TAG, stringBuilder.toString());
        }
    }

    public static void e(String strTag, String strMsg) {
        if (isLoggable(APK_TAG, 6)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(strTag);
            stringBuilder.append("::");
            stringBuilder.append(strMsg);
            Log.e(APK_TAG, stringBuilder.toString());
        }
    }

    public static void i(String strTag, String strMsg) {
        if (isLoggable(APK_TAG, 4)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(strTag);
            stringBuilder.append("::");
            stringBuilder.append(strMsg);
            Log.i(APK_TAG, stringBuilder.toString());
        }
    }

    private static boolean isLoggable(String strTag, int strMsg) { return (strMsg >= LOG_LEVEL); }

    public static void v(String strTag, String strMsg) {
        if (isLoggable(APK_TAG, 2)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(strTag);
            stringBuilder.append("::");
            stringBuilder.append(strMsg);
            Log.v(APK_TAG, stringBuilder.toString());
        }
    }

    public static void w(String strTag, String strMsg) {
        if (isLoggable(APK_TAG, 5)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(strTag);
            stringBuilder.append("::");
            stringBuilder.append(strMsg);
            Log.w(APK_TAG, stringBuilder.toString());
        }
    }
}
