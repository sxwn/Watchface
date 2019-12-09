package com.goertek.commonlib.utils;

import java.lang.ref.SoftReference;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

/**
 * 上下文获取工具类
 *
 * @author ww
 * @version 1.0.0
 * @since 2019/07/08
 */
public class ContextUtil {
    private static final String TAG = "ContextUtil";

    private static SoftReference<Context> sContextRef;

    public static Context getContext() {
        return  sContextRef.get();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Context getDirectBootContext() {
        return ( sContextRef.get()).createDeviceProtectedStorageContext();
    }

    public static void initContext(Context context) {
        sContextRef = new SoftReference(context.getApplicationContext());
    }
}
