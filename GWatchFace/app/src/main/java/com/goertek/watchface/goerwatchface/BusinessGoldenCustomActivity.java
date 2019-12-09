/*
 * Copyright (c) 2016 - Goertek -All rights reserved
 */

package com.goertek.watchface.goerwatchface;

import com.goertek.commonlib.custom.BaseCustomWidgetActivity;
import com.goertek.commonlib.provider.manager.AssetPackage;

/**
 *  金色年华
 *  @author: carey.yu
 *  @version: 1.0.0
 *  @since: 2019/7/31
 */
public class BusinessGoldenCustomActivity extends BaseCustomWidgetActivity {
    @Override
    protected AssetPackage getAssetPackage() {
        return new AssetPackage("businessgoldenwatchface", true);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
