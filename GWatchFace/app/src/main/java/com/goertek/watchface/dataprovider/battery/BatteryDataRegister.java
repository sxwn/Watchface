package com.goertek.watchface.dataprovider.battery;

import android.content.Context;

import com.goertek.commonlib.provider.data.manager.DataAdapter;
import com.goertek.commonlib.utils.LogUtil;
import com.goertek.watchface.dataprovider.receiver.BatteryReceiver;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class BatteryDataRegister {
    private static final String TAG = "BatteryDataRegister";

    private static BatteryDataProvider batteryDataProvider = new BatteryDataProvider();

    private BatteryDataRegister() {
    }

    public static void registerBatteryDataRegister(Context context) {
       LogUtil.i("BatteryDataRegister", "consumption registerBatteryDataRegister");
        registerProvider("data_battery");
        registerProvider("data_battery_icon_index");
        registerProvider("battery_selection");
        BatteryReceiver.getReceiver().register(context);
    }

    private static void registerProvider(String valueType) {
        DataAdapter.getInstance().registerDataProvider(valueType, batteryDataProvider);
    }

    public static void unregisterBatteryDataRegister(Context context) {
        LogUtil.i("BatteryDataRegister", "consumption unregisterBatteryDataRegister");
        unregisterProvider("data_battery");
        unregisterProvider("data_battery_icon_index");
        unregisterProvider("battery_selection");
        BatteryReceiver.getReceiver().unregister(context);
    }

    private static void unregisterProvider(String valueType) {
        DataAdapter.getInstance().unregisterDataProvider(valueType, batteryDataProvider);
    }
}
