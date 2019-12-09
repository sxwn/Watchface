/*
 * Copyright (c) 2016 - Goertek -All rights reserved
 */

package com.goertek.watchface.goerwatchface;

import android.support.wearable.watchface.WatchFaceService;

import com.goertek.commonlib.provider.manager.AssetPackage;
import com.goertek.commonlib.service.BaseCanvasWatchFaceService;

/**
 *  ming ke
 *  @author: carey.yu
 *  @version: 1.0.0
 *  @since: 2019/8/3
 */
public class EngravedWatchFaceService extends BaseCanvasWatchFaceService {

    private static final String PATH  = "engravedwatchface";
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
