/*
 * Copyright (c) 2016 - Goertek -All rights reserved
 */

package com.goertek.watchface.goerwatchface;

import android.support.wearable.watchface.WatchFaceService;

import com.goertek.commonlib.provider.manager.AssetPackage;
import com.goertek.commonlib.service.BaseCanvasWatchFaceService;

/**
 *  金色年华
 *
 *  @author: carey.yu
 *  @version: 1.0.0
 *  @since: 2019/7/30
 */
public class BusinessGoldenWatchFaceService extends BaseCanvasWatchFaceService {

    private static final String PATH = "businessgoldenwatchface";

    @Override
    public WatchFaceService.Engine onCreateEngine() {
        return new Engine();
    }

    private class Engine extends BaseCanvasWatchFaceService.Engine{

        @Override
        public AssetPackage getAssetPackage() {
            return new AssetPackage(PATH, true);
        }
    }
}
