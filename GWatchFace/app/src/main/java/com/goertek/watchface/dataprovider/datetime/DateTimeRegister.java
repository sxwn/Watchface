package com.goertek.watchface.dataprovider.datetime;

import com.goertek.commonlib.provider.data.manager.DataAdapter;
import com.goertek.commonlib.provider.data.util.DataConstantUtils;
import com.goertek.commonlib.utils.LogUtil;

/**
 * 日期数据
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/30
 */
public class DateTimeRegister {
    private static final String TAG = "DateTimeRegister";

    private static DateTimeProvider dateTimeProvider = new DateTimeProvider();

    private DateTimeRegister() {
    }

    public static void registerDateTimeProvider() {
        LogUtil.i("DateTimeRegister", "consumption registerDateTimeProvider");
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_YEAR);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_MONTH);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_WORD_MONTH);
        registerProvider(DataConstantUtils.DATA_DAY);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_DAY_FILL_IN_0);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_DAY_FILL_IN_LEFT);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_DAY_FILL_IN_RIGHT);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_DAY_FILL_IN_0);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_DAY_BEFORE);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_DAY_AFTER);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_WEEK);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_WORD_WEEK);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_WORD_WEEK_1);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_HOUR);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_MINUTE);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_SECOND);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_AMPM);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_DAYS_OF_MONTH_INDEX);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_HOUR_MINUTE);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_MONTH_DAY);
        registerProvider(DataConstantUtils.VALUE_TYPE_DATA_MINUTE_SECOND);
        registerProvider(DataConstantUtils.VALUE_TYPE_AWEEK_BACKGROUND_INDEX);
        registerProvider(DataConstantUtils.VALUE_TYPE_AWEEK_HOUR_INDEX);
        registerProvider(DataConstantUtils.VALUE_TYPE_AWEEK_MINUTE_INDEX);
    }

    private static void registerProvider(String valueType) {
        DataAdapter.getInstance().registerDataProvider(valueType, dateTimeProvider);
    }

    public static void unregisterDateTimeProvider() {
        LogUtil.i("DateTimeRegister", "consumption registerDateTimeProvider");
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_YEAR);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_MONTH);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_WORD_MONTH);
        unregisterProvider(DataConstantUtils.DATA_DAY);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_DAY_FILL_IN_0);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_DAY_FILL_IN_LEFT);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_DAY_FILL_IN_RIGHT);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_DAY_FILL_IN_0);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_DAY_BEFORE);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_DAY_AFTER);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_WEEK);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_WORD_WEEK);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_WORD_WEEK_1);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_HOUR);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_MINUTE);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_SECOND);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_AMPM);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_DAYS_OF_MONTH_INDEX);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_HOUR_MINUTE);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_MONTH_DAY);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_DATA_MINUTE_SECOND);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_AWEEK_BACKGROUND_INDEX);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_AWEEK_HOUR_INDEX);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_AWEEK_MINUTE_INDEX);
    }

    private static void unregisterProvider(String valueType) {
        DataAdapter.getInstance().unregisterDataProvider(valueType, dateTimeProvider);
    }
}
