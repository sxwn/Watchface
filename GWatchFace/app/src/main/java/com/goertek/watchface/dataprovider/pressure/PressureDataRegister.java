package com.goertek.watchface.dataprovider.pressure;

import com.goertek.commonlib.provider.data.manager.DataAdapter;
import com.goertek.commonlib.provider.data.util.DataConstantUtils;
import com.goertek.commonlib.utils.LogUtil;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class PressureDataRegister  {
    private static final String TAG = "PressureDataRegister";
    private static PressureDataProvider pressureDataProvider = new PressureDataProvider();

    private PressureDataRegister() {
    }

    public static void registerBatteryDataRegister() {
         LogUtil.i("PressureDataRegister", "consumption registerBatteryDataRegister");
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_PRESSURE);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_0);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_1);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_2);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_3);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_4);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_5);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_6);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_7);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_8);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_9);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_10);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_11);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_12);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_13);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_14);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_15);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_16);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_17);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_18);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_19);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_20);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_21);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_22);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_23);
    }

    private static void registerProvider(String valueType) {
        DataAdapter.getInstance().registerDataProvider(valueType, pressureDataProvider);
    }

    public static void unregisterBatteryDataRegister() {
        LogUtil.i("PressureDataRegister", "consumption unregisterBatteryDataRegister");
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_PRESSURE);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_0);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_1);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_2);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_3);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_4);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_5);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_6);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_7);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_8);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_9);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_10);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_11);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_12);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_13);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_14);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_15);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_16);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_17);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_18);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_19);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_20);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_21);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_22);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_23);
    }

    private static void unregisterProvider(String var0) {
        DataAdapter.getInstance().unregisterDataProvider(var0, pressureDataProvider);
    }
}