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

    //Device protected storage:新的存储类型，只要设备是启动后，随时可以访问存在在这里的数据，包括Direct boot模式时
    //要访问Device protected storage，需要在使用所有文件相关的API时，用以下的Context,
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Context getDirectBootContext() {
        return ( sContextRef.get()).createDeviceProtectedStorageContext();
    }

    public static void initContext(Context context) {
        sContextRef = new SoftReference(context.getApplicationContext());
    }
}
