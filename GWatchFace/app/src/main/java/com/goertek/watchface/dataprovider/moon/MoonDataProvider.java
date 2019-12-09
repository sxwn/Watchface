package com.goertek.watchface.dataprovider.moon;

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
public class MoonDataProvider implements IDataProvider {
    private static final String TAG = "MoonDataProvider";

    private static final int EMPTY_MOON_DAY_OF_MONTH = 30;

    private static final int FIRST_MOON_DAY = 1;

    private static final int FULL_MOON_DAY_OF_MONTH = 15;

    private static final int MIN_MOON_RANGE = 0;

    private static final int NUMBER_FIVE = 5;

    private static final int NUMBER_FOUR = 4;

    private static final int NUMBER_FOURTEEN = 14;

    private static final int NUMBER_ONE = 1;

    private static final int NUMBER_SIX = 6;

    private static final int NUMBER_SIXTEEN = 16;

    private static final int NUMBER_TEN = 10;

    private static final int NUMBER_THREE = 3;

    private static final int NUMBER_TWENTY_ONE = 21;

    private static final int NUMBER_TWENTY_SIX = 26;

    private static final int NUMBER_TWO = 2;

    private static final int NUMBER_ZERO = 0;

    private int mMoonDay = 0;

    public MoonDataProvider() {
    }

    private int getMoonIndex() {
        MoonLunarUtil.updateMoonDay();
        int moonDay = MoonLunarUtil.getMoonDay();
        if (moonDay > NUMBER_ZERO && moonDay <= NUMBER_FIVE) {
            return MIN_MOON_RANGE;
        } else if (moonDay > NUMBER_FIVE && moonDay <= NUMBER_TEN) {
            return NUMBER_ONE;
        } else if (moonDay > NUMBER_TEN && moonDay <= NUMBER_FOURTEEN) {
            return NUMBER_TWO;
        } else if (moonDay > NUMBER_FOURTEEN && moonDay <= NUMBER_SIXTEEN) {
            return NUMBER_THREE;
        } else if (moonDay > NUMBER_SIXTEEN && moonDay <= NUMBER_TWENTY_ONE) {
            return NUMBER_FOUR;
        } else {
            return moonDay > NUMBER_TWENTY_ONE && moonDay <= NUMBER_TWENTY_SIX ? NUMBER_FIVE : NUMBER_SIX;
        }
    }

    public void doClick(String dateType) {
    }

    public FloatRangeValue getFloatRangeValue(String valueType) {
        return new FloatRangeValue(0.0F, 0.0F, 0.0F);
    }

    public float getFloatValue(String valueType) {
        return 0.0F;
    }

    public int getIndexValue(String valueType) {

        if (TextUtils.isEmpty(valueType)) {
            return -1;
        }
        int value;

        if (TextUtils.equals(valueType, DataConstantUtils.VALUE_TYPE_DATA_MOON)) {
            value = 0;
        } else {
            value = -1;
        }
        return value != 0 ? 0 : this.getMoonIndex();
    }

    public IntRangeValue getIntRangeValue(String valueType) {
        int value;

        if (TextUtils.isEmpty(valueType)) {
            value = -1;
        }

        if (TextUtils.equals(valueType, DataConstantUtils.VALUE_TYPE_DATA_ROTATE_MOON)) {
            value = 0;
        } else if (TextUtils.equals(valueType, DataConstantUtils.VALUE_TYPE_DATA_MOON)) {
            value = 1;
        } else {
            value = -1;
        }

        switch (value) {
            case 0:
                return new IntRangeValue(this.getIntValue(valueType), 30, 0);
            case 1:
                int intValue = this.getIntValue(valueType);
                value = intValue;
                if (intValue > 15) {
                    value = 30 - intValue;
                }

                return new IntRangeValue(value, 15, 0);
            default:
                return new IntRangeValue(1, 30, 0);
        }
    }

    public int getIntValue(String valueType) {
        int value;

        if (TextUtils.isEmpty(valueType)) {
            value = -1;
        }

        if (TextUtils.equals(valueType, DataConstantUtils.VALUE_TYPE_DATA_ROTATE_MOON)) {
            value = 0;
        } else if (TextUtils.equals(valueType, DataConstantUtils.VALUE_TYPE_DATA_MOON)) {
            value = 1;
        } else {
            value = -1;
        }

        switch (value) {
            case 0:
            case 1:
                MoonLunarUtil.updateMoonDay();
                this.mMoonDay = MoonLunarUtil.getMoonDay();
                break;
            default:
                this.mMoonDay = 0;
        }

        return this.mMoonDay;
    }

    public int getLayerIndexValue(String valueType) {
        if (DataConstantUtils.VALUE_TYPE_DATA_MOON.equals(valueType)) {
            return this.getIntValue(valueType) > 15 ? 0 : 1;
        } else {
            return 0;
        }
    }

    public String getStringValue(String valueType) {
        return String.valueOf(this.getIntValue(valueType));
    }

    public String getStringValue(String valueType, String... format) {
        return String.valueOf("");
    }
}
