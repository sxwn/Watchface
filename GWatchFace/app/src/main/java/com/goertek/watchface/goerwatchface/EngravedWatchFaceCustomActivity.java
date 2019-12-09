/*
 * Copyright (c) 2016 - Goertek -All rights reserved
 */

package com.goertek.watchface.goerwatchface;

import com.goertek.commonlib.custom.BaseCustomWidgetActivity;
import com.goertek.commonlib.provider.manager.AssetPackage;

/**
 *  铭刻
 *  @author: carey.yu
 *  @version: 1.0.0
 *  @since: 2019/8/3
 */
public class EngravedWatchFaceCustomActivity extends BaseCustomWidgetActivity {

    private static final String PATH = "engravedwatchface";

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
