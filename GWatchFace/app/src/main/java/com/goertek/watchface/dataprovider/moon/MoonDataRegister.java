package com.goertek.watchface.dataprovider.moon;

import android.content.Context;

import com.goertek.commonlib.provider.data.manager.DataAdapter;
import com.goertek.commonlib.utils.LogUtil;
import com.goertek.watchface.dataprovider.receiver.DateChangeReceiver;
import com.goertek.watchface.dataprovider.receiver.TimeZoneReceiver;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class MoonDataRegister {
    private static final String TAG = "MoonDataRegister";
    private static MoonDataProvider moonDataProvider = new MoonDataProvider();

    private MoonDataRegister() {
    }

    public static void registerBatteryDataRegister(Context context) {
         LogUtil.i("MoonDataRegister", "consumption registerBatteryDataRegister");
        registerProvider("data_moon");
        registerProvider("data_rotate_moon");
        DateChangeReceiver.register(context);
        TimeZoneReceiver.register(context);
    }

    private static void registerProvider(String valueType) {
        DataAdapter.getInstance().registerDataProvider(valueType, moonDataProvider);
    }

    public static void unregisterBatteryDataRegister(Context context) {
        LogUtil.i("MoonDataRegister", "consumption unregisterBatteryDataRegister");
        unregisterProvider("data_moon");
        registerProvider("data_rotate_moon");
        DateChangeReceiver.unregister(context);
        TimeZoneReceiver.unregister(context);
    }

    private static void unregisterProvider(String valueType) {
        DataAdapter.getInstance().unregisterDataProvider(valueType, moonDataProvider);
    }
}