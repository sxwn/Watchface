package com.goertek.watchface.dataprovider.attribute;

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
public class GoldenNoneWidgetDataProvider implements IDataProvider {
    private static final String TAG = "GoldenNoneWidgetDataProvider" +
                                              "";
    private static final int NUMBER_ONE = 1;

    private static final int NUMBER_ZERO = 0;

    private static final String PATH = "businessgoldenwatchface";

    private boolean isHaveWidget = true;

    private String mAssetPath;

    public GoldenNoneWidgetDataProvider() {
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
        return 0;
    }

    public IntRangeValue getIntRangeValue(String valueType) {
        return new IntRangeValue(0, 0, 0);
    }

    public int getIntValue(String valueType) {
        return 0;
    }

    public int getLayerIndexValue(String valueType) {
        int value;
        if (TextUtils.isEmpty(valueType)) {
            value = -1;
        }

        if (TextUtils.equals(valueType, DataConstantUtils.VALUE_TYPE_NONE_WIDGET_GOLDEN_DIAL)) {
            value = 0;
        } else {
            value = -1;
        }

        return value != 0 ? 0 : 1;
    }

    public String getStringValue(String valueType) {
        return String.valueOf(this.getIntValue(valueType));
    }

    public String getStringValue(String valueType, String... format) {
        return String.valueOf("");
    }
}