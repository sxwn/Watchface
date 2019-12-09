package com.goertek.watchface.dataprovider.attribute;

import android.text.TextUtils;

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
public class AttributeDataRegister {
    private static final String TAG = "AttributeDataRegister";

    private static AttributeDataProvider attributeDataProvider = new AttributeDataProvider();

    private static GoldenNoneWidgetDataProvider goldenNoneWidgetDataProvider = new GoldenNoneWidgetDataProvider();

    private AttributeDataRegister() {
    }

    public static void registerBatteryDataRegister() {
        LogUtil.i("AttributeDataRegister", "consumption registerBatteryDataRegister");
        registerProvider(DataConstantUtils.VALUE_TYPE_NONE_WIDGET_DIAL_DEFAULT_HIDE);
        registerProvider(DataConstantUtils.VALUE_TYPE_NONE_WIDGET_DIAL);
        registerProvider(DataConstantUtils.VALUE_TYPE_NONE_WIDGET_GOLDEN_DIAL);
    }

    private static void registerProvider(String valueType) {
        int value;
        if (TextUtils.isEmpty(valueType)) {
            value = -1;
        }

        switch (valueType) {
            case DataConstantUtils.VALUE_TYPE_NONE_WIDGET_DIAL:
                value = 1;
                break;
            case DataConstantUtils.VALUE_TYPE_NONE_WIDGET_DIAL_DEFAULT_HIDE:
                value = 0;
                break;
            case DataConstantUtils.VALUE_TYPE_NONE_WIDGET_GOLDEN_DIAL:
                value = 2;
                break;
            default:
                value = -1;
                break;
        }

        switch (value) {
            case 0:
            case 1:
                DataAdapter.getInstance().registerDataProvider(valueType, attributeDataProvider);
                return;
            case 2:
                DataAdapter.getInstance().registerDataProvider(valueType, goldenNoneWidgetDataProvider);
                return;
            default:
        }
    }

    public static void unregisterBatteryDataRegister() {
        LogUtil.i("AttributeDataRegister", "consumption unregisterBatteryDataRegister");
        unregisterProvider(DataConstantUtils.VALUE_TYPE_NONE_WIDGET_DIAL_DEFAULT_HIDE);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_NONE_WIDGET_DIAL);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_NONE_WIDGET_GOLDEN_DIAL);
    }

    private static void unregisterProvider(String valueType) {
        int value;
        if (TextUtils.isEmpty(valueType)) {
            value = -1;
        }

        if (TextUtils.equals(valueType, DataConstantUtils.VALUE_TYPE_NONE_WIDGET_DIAL)) {
            value = 0;
        } else if (TextUtils.equals(valueType, DataConstantUtils.VALUE_TYPE_NONE_WIDGET_GOLDEN_DIAL)) {
            value = 1;
        } else {
            value = -1;
        }
        switch (value) {
            case 0:
                DataAdapter.getInstance().unregisterDataProvider(valueType, attributeDataProvider);
                return;
            case 1:
                DataAdapter.getInstance().unregisterDataProvider(valueType, goldenNoneWidgetDataProvider);
                return;
            default:
        }
    }
}
