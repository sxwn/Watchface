package com.goertek.watchface.dataprovider.attribute;

import com.goertek.commonlib.provider.data.manager.IDataProvider;
import com.goertek.commonlib.provider.data.model.FloatRangeValue;
import com.goertek.commonlib.provider.data.model.IntRangeValue;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class AttributeDataProvider implements IDataProvider {
    private static final String TAG = "AttributeDataProvider";

    private static final int NUMBER_ONE = 1;

    private static final int NUMBER_ZERO = 0;

    private static final String PATH = "businesswatchface";

    private boolean isHaveWidget = false;

    private boolean isHaveWidgetDefault = true;

    private String mAssetPath;

    public AttributeDataProvider() {
    }

    public void doClick(String var1) {
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
        return 0;
    }

    public String getStringValue(String valueType) {
        return String.valueOf(this.getIntValue(valueType));
    }

    public String getStringValue(String valueType, String... format) {
        return String.valueOf("");
    }
}
