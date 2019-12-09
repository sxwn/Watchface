package com.goertek.watchface.dataprovider.worldtime;

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
public class WorldTimeRegister {
    private static final String TAG = "WorldTimeRegister";
    private static WorldTimeProvider worldTimeProvider = new WorldTimeProvider();

    private WorldTimeRegister() {
    }

    private static void registerProvider(String lable) {
        DataAdapter.getInstance().registerDataProvider(lable, worldTimeProvider);
    }

    public static void registerWorldTimeProvider() {
        LogUtil.i("WorldTimeRegister", "consumption registerWorldTimeProvider");
        registerProvider(DataConstantUtils.VALUE_TYPE_WORLD_TIME_CITY_CODE);
        registerProvider(DataConstantUtils.VALUE_TYPE_WORLD_TIME_HOUR);
        registerProvider(DataConstantUtils.VALUE_TYPE_WORLD_TIME_MINUTE);
        registerProvider(DataConstantUtils.VALUE_TYPE_WORLD_TIME_MOON_OR_SUN);
        registerProvider(DataConstantUtils.VALUE_TYPE_WORLD_TIME_AM_PM);
        registerProvider(DataConstantUtils.VALUE_TYPE_WORLD_TIME_AM_PM_INDEX);
        registerProvider(DataConstantUtils.DATA_TYPE_WORLD_TIME);
    }

    public static void unregisteWorldTimeProvider() {
        LogUtil.i("WorldTimeRegister", "consumption unregisteWorldTimeProvider");
        unregisterProvider(DataConstantUtils.VALUE_TYPE_WORLD_TIME_CITY_CODE);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_WORLD_TIME_HOUR);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_WORLD_TIME_MINUTE);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_WORLD_TIME_MOON_OR_SUN);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_WORLD_TIME_AM_PM);
        unregisterProvider(DataConstantUtils.VALUE_TYPE_WORLD_TIME_AM_PM_INDEX);
        unregisterProvider(DataConstantUtils.DATA_TYPE_WORLD_TIME);
    }

    private static void unregisterProvider(String lable) {
        DataAdapter.getInstance().unregisterDataProvider(lable, worldTimeProvider);
    }
}
