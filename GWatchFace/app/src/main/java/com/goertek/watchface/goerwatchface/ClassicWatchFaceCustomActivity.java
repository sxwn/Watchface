package com.goertek.watchface.goerwatchface;

import com.goertek.commonlib.custom.BaseCustomWidgetActivity;
import com.goertek.commonlib.provider.manager.AssetPackage;

/**
 *  功能说明
 * <p>
 *  @author: carey.yu
 *  @version: 1.0.0
 *  @since: 2019/8/12
 */
public class ClassicWatchFaceCustomActivity extends BaseCustomWidgetActivity {


    private static final String PATH = "classicwatchface";
    @Override
    protected AssetPackage getAssetPackage() {
        return new AssetPackage(PATH,true);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
