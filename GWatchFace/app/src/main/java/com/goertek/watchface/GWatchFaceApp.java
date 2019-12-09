/*
 * Copyright (c) 2016 - Goertek -All rights reserved
 */

package com.goertek.watchface;

import android.app.Application;

import com.goertek.commonlib.eventbus.ModuleBus;
import com.goertek.commonlib.utils.ContextUtil;
import com.goertek.commonlib.utils.LogUtil;
import com.goertek.watchface.dataprovider.attribute.AttributeDataRegister;
import com.goertek.watchface.dataprovider.battery.BatteryDataRegister;
import com.goertek.watchface.dataprovider.datetime.DateTimeRegister;
import com.goertek.watchface.dataprovider.moon.MoonDataRegister;
import com.goertek.watchface.dataprovider.network.NetWorkStateRegister;
import com.goertek.watchface.dataprovider.pressure.PressureControl;
import com.goertek.watchface.dataprovider.pressure.PressureDataRegister;
import com.goertek.watchface.dataprovider.sportshealth.SportHealthRegisterUtils;
import com.goertek.watchface.dataprovider.weather.WeatherRegister;
import com.goertek.watchface.dataprovider.worldtime.WorldTimeRegister;

/**
 *  表盘app
 *  @author: carey.yu
 *  @version: 1.0.0
 *  @since: 2019/7/25
 */
public class GWatchFaceApp extends Application {

    private static final String TAG = GWatchFaceApp.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.i(TAG, "onCreate()");
        ContextUtil.initContext(this);
        ModuleBus.initEventBus();
        DateTimeRegister.registerDateTimeProvider();
        DateTimeRegister.registerDateTimeProvider();
        SportHealthRegisterUtils.registerSportHealthRegister();
        WeatherRegister.registerWeatherProvider();
        NetWorkStateRegister.registerNetWorkStateProvider(this);
        BatteryDataRegister.registerBatteryDataRegister(this);
        PressureControl.getInstance().registerTimeTickReceiver(this);
        PressureDataRegister.registerBatteryDataRegister();
        MoonDataRegister.registerBatteryDataRegister(this);
        AttributeDataRegister.registerBatteryDataRegister();
        WorldTimeRegister.registerWorldTimeProvider();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        PressureDataRegister.unregisterBatteryDataRegister();
        PressureControl.getInstance().unregisterTimeTickReceiver(this);
        BatteryDataRegister.unregisterBatteryDataRegister(this);
        SportHealthRegisterUtils.unRegisterSportHealthRegister();
        MoonDataRegister.unregisterBatteryDataRegister(this);
        DateTimeRegister.unregisterDateTimeProvider();
        WeatherRegister.unRegisterWeatherProvider();
        NetWorkStateRegister.unregisterNetWorkStateProvider();
        AttributeDataRegister.unregisterBatteryDataRegister();
        WorldTimeRegister.unregisteWorldTimeProvider();
    }
}
