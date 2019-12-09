package com.goertek.watchface.dataprovider.battery;

import android.text.TextUtils;

import com.goertek.commonlib.provider.data.manager.IDataProvider;
import com.goertek.commonlib.provider.data.model.FloatRangeValue;
import com.goertek.commonlib.provider.data.model.IntRangeValue;
import com.goertek.commonlib.provider.data.util.DataConstantUtils;
import com.goertek.watchface.dataprovider.receiver.BatteryReceiver;

/**
 * 电量数据
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/30
 */
public class BatteryDataProvider implements IDataProvider {
    private static final int NUMBER_EIGHTY = 80;
    private static final String TAG = "BatteryDataProvider";

    private static final int NUMBER_FIVE = 5;

    private static final int NUMBER_FORTY = 40;

    private static final int NUMBER_FOUR = 4;

    private static final int NUMBER_ONE = 1;

    private static final int NUMBER_SIX = 6;

    private static final int NUMBER_SIXTY = 60;

    private static final int NUMBER_THREE = 3;

    private static final int NUMBER_TWENTY = 20;

    private static final int NUMBER_TWO = 2;

    private static final int NUMBER_ZERO = 0;

    private int mBatteryLevel = 0;

    public BatteryDataProvider() {
    }

    private int getBatteryIconIndex() {
        boolean charging = BatteryReceiver.getCharging();
        int batteryLevel = BatteryReceiver.getsBatteryLevel();
        if (charging) {
            return 0;
        } else if (batteryLevel == 0) {
            return 1;
        } else if (batteryLevel > 0 && batteryLevel <= 20) {
            return 2;
        } else if (batteryLevel > 20 && batteryLevel <= 40) {
            return 3;
        } else if (batteryLevel > 40 && batteryLevel <= 60) {
            return 4;
        } else {
            return batteryLevel > 60 && batteryLevel <= 80 ? 5 : 6;
        }
    }

    public void doClick(String var1) {
    }

    public FloatRangeValue getFloatRangeValue(String var1) {
        return new FloatRangeValue(0.0F, 0.0F, 0.0F);
    }

    public float getFloatValue(String var1) {
        return 0.0F;
    }

    public int getIndexValue(String valueType) {
        int indexVlaue;
        if (valueType.equals(DataConstantUtils.VALUE_TYPE_DATA_BATTERY_ICON_INDEX)) {
            indexVlaue = 0;
        } else {
            indexVlaue = -1;
        }
        return indexVlaue != 0 ? 0 : getBatteryIconIndex();
    }

    public IntRangeValue getIntRangeValue(String valueType) {
        int maxValue;
        int value;
        if (TextUtils.equals(DataConstantUtils.VALUE_TYPE_DATA_BATTERY, valueType)) {
            value = BatteryReceiver.getsBatteryLevel();
            maxValue = BatteryReceiver.getsBatteryScale();
        } else {
            value = 0;
            maxValue = value;
        }

        return new IntRangeValue(value, maxValue, 0);
    }

    public int getIntValue(String valueType) {
        if (TextUtils.equals(valueType, DataConstantUtils.VALUE_TYPE_DATA_BATTERY)) {
            this.mBatteryLevel = BatteryReceiver.getsBatteryLevel();
        }

        return this.mBatteryLevel;
    }

    public int getLayerIndexValue(String valueType) {
        int batteryLevel = BatteryReceiver.getsBatteryLevel();
        int indexValue;
        if (TextUtils.equals(valueType, DataConstantUtils.VALUE_TYPE_DATA_BATTERY_SELECTED)) {
            indexValue = 0;
        } else {
            indexValue = -1;
        }

        if (indexValue != 0) {
            return 0;
        } else if (batteryLevel > 60) {
            return 0;
        } else {
            return batteryLevel > 20 && batteryLevel < 60 ? 1 : 2;
        }
    }

    public String getStringValue(String valueType) {
        return String.valueOf(this.getIntValue(valueType));
    }

    public String getStringValue(String valueType, String... format) {
        return String.valueOf("");
    }
}
