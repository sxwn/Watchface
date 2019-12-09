package com.goertek.commonlib.view.element;

import com.goertek.commonlib.provider.manager.AssetPackage;

public class BackgroundElement extends BaseElement {
    private static final String TAG = "BackgroundElement";

    private static final String LABLE = "background";

    public BackgroundElement(AssetPackage assertPackage) {
        super(LABLE, assertPackage);
    }
}
