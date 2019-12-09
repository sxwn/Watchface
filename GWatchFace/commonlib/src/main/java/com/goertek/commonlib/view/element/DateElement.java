package com.goertek.commonlib.view.element;

import com.goertek.commonlib.custom.BaseCustomFragment;
import com.goertek.commonlib.provider.manager.AssetPackage;

public class DateElement extends BaseElement {
    private static final String TAG = "DateElement";

    private static final String LABLE = "date";

    public DateElement(AssetPackage assertPackage) {
        super(LABLE, assertPackage);
    }
}
