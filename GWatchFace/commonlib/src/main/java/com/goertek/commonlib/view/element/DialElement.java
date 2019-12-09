package com.goertek.commonlib.view.element;

import com.goertek.commonlib.provider.manager.AssetPackage;

public class DialElement extends BaseElement {
    private static final String TAG = "DialElement";

    private static final String LABLE = "dial";

    public DialElement(AssetPackage assetPackage) {
        super(LABLE,assetPackage);
    }
}
