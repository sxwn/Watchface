package com.goertek.commonlib.provider.data.manager;

import com.goertek.commonlib.provider.data.model.FloatRangeValue;
import com.goertek.commonlib.provider.data.model.IntRangeValue;

public interface IDataProvider {
    void doClick(String dateType);

    FloatRangeValue getFloatRangeValue(String valueType);

    float getFloatValue(String valueType);

    int getIndexValue(String valueType);

    IntRangeValue getIntRangeValue(String valueType);

    int getIntValue(String valueType);

    int getLayerIndexValue(String valueType);

    String getStringValue(String valueType);

    String getStringValue(String valueType, String... format);
}
