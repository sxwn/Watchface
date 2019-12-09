/*
 * Copyright (c) 2016 - Goertek -All rights reserved
 */

package com.goertek.watchface.goerwatchface;

import com.goertek.commonlib.custom.BaseCustomWidgetActivity;
import com.goertek.commonlib.provider.manager.AssetPackage;

/**
 *  多彩表盘
 *
 *  @author: carey.yu
 *  @version: 1.0.0
 *  @since: 2019/8/2
 */
public class ColorWatchFaceCustomActivity extends BaseCustomWidgetActivity {

    private static final String PATH = "colorwatchface";

    @Override
    protected AssetPackage getAssetPackage() {
        return new AssetPackage(PATH, true);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
