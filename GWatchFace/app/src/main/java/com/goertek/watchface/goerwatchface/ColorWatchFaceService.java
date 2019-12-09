package com.goertek.watchface.goerwatchface;

import android.support.wearable.watchface.WatchFaceService;

import com.goertek.commonlib.provider.manager.AssetPackage;
import com.goertek.commonlib.service.BaseCanvasWatchFaceService;
import com.goertek.commonlib.view.element.IDrawElement;

/**
 * 多彩表盘
 *
 * @author:  ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class ColorWatchFaceService extends BaseCanvasWatchFaceService {
    private static final String TAG = "ColorWatchFaceService";

    private static final String PATH = "colorwatchface";

    @Override
    public WatchFaceService.Engine onCreateEngine() {
        return new Engine();
    }


    protected class Engine extends BaseCanvasWatchFaceService.Engine {

        @Override
        public AssetPackage getAssetPackage() {
            return new AssetPackage("colorwatchface", true);
        }
    }
}
