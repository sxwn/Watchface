package com.goertek.watchface.dataprovider.pressure;

import android.text.TextUtils;

import com.goertek.commonlib.provider.data.manager.IDataProvider;
import com.goertek.commonlib.provider.data.model.FloatRangeValue;
import com.goertek.commonlib.provider.data.model.IntRangeValue;
import com.goertek.commonlib.provider.data.util.DataConstantUtils;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class PressureDataProvider implements IDataProvider {
    PressureSensorListenerUtils mPressureSensorListenerUtils = PressureSensorListenerUtils.getInstance();

    public PressureDataProvider() {
    }

    public void doClick(String var1) {
    }

    public FloatRangeValue getFloatRangeValue(String var1) {
        return new FloatRangeValue(0.0F, 0.0F, 0.0F);
    }

    public float getFloatValue(String var1) {
        return 0.0F;
    }

    public int getIndexValue(String var1) {
        return 0;
    }

    public IntRangeValue getIntRangeValue(String var1) {
        return new IntRangeValue(this.getIntValue(var1), 10000, 1000);
    }

    public int getIntValue(String intValue) {
        int value;
        if (TextUtils.isEmpty(intValue)) {
            return -1;
        }

        switch (intValue) {
            case DataConstantUtils.VALUE_TYPE_DATA_PRESSURE:
                value = 0;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE:
                value = 1;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_0:
                value = 2;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_1:
                value = 3;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_2:
                value = 4;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_3:
                value = 5;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_4:
                value = 6;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_5:
                value = 7;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_6:
                value = 8;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_7:
                value = 9;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_8:
                value = 10;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_9:
                value = 11;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_10:
                value = 12;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_11:
                value = 13;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_12:
                value = 14;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_13:
                value = 15;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_14:
                value = 16;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_15:
                value = 17;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_16:
                value = 18;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_17:
                value = 19;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_18:
                value = 20;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_19:
                value = 21;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_20:
                value = 22;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_21:
                value = 23;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_22:
                value = 24;
                break;
            case DataConstantUtils.VALUE_TYPE_DATA_ALTITUDE_OFFSET_23:
                value = 25;
                break;
            default:
                value = -1;
                break;
        }
        switch (value) {
            case 0:
                return this.mPressureSensorListenerUtils.getPressureValue();
            case 1:
                return this.mPressureSensorListenerUtils.getAltitudeValue();
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
                return PressureControl.getInstance().getAltitudeWithOffset(intValue);
            default:
                return 0;
        }
    }

    public int getLayerIndexValue(String value) {
        return 0;
    }

    public String getStringValue(String value) {
        return String.valueOf(this.getIntValue(value));
    }

    public String getStringValue(String value, String... format) {
        return String.valueOf("");
    }
}
