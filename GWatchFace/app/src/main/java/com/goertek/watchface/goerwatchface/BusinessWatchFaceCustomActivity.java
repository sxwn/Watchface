package com.goertek.watchface.goerwatchface;

import com.goertek.commonlib.custom.BaseCustomWidgetActivity;
import com.goertek.commonlib.provider.manager.AssetPackage;

/**
 *  简炫表盘
 *
 *  @author: carey.yu
 *  @version: 1.0.0
 *  @since: 2019/8/12
 */
public class BusinessWatchFaceCustomActivity extends BaseCustomWidgetActivity {

    private static final String PATH = "businesswatchface";
    @Override
    protected AssetPackage getAssetPackage() {
        return new AssetPackage(PATH,true);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
