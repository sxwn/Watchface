package com.goertek.commonlib.view.element;

import com.goertek.commonlib.provider.manager.AssetPackage;

public class TimeElement extends BaseElement {
    private static final String TAG = "TimeElement";

    public static final String LABLE = "time";
    public TimeElement(AssetPackage assertPackage) {
        super(LABLE,assertPackage);
    }
}
