package com.goertek.commonlib.view.element;


import android.graphics.Canvas;

import com.goertek.commonlib.provider.manager.AssetPackage;
import com.goertek.commonlib.provider.model.Element;
import com.goertek.commonlib.provider.model.Layer;
import com.goertek.commonlib.provider.model.Option;
import com.goertek.commonlib.provider.model.Styles;
import com.goertek.commonlib.utils.LogUtil;
import com.goertek.commonlib.utils.UIDrawUnitFactory;
import com.goertek.commonlib.utils.WatchFaceUtil;
import com.goertek.commonlib.view.unit.base.IDrawUnit;

import java.util.ArrayList;
import java.util.List;


public class BaseElement implements IDrawElement {
    private static final String TAG = "zyazya";
    private AssetPackage assetPackage;
    private boolean isAmbientMode;
    private String mLabel;
    protected List<IDrawUnit> mDrawUnits = new ArrayList<>(0);

    public BaseElement(String label, AssetPackage assertPackage) {
        assetPackage = assertPackage;
        mLabel = label;
        LogUtil.e(TAG, "lable==============" + label);
        addUnit();
    }

    public void addUnit() {
        Element element = getElement();
        if (element == null) {
            LogUtil.i(TAG, "addUnit:element=null");
            return;
        }
        boolean isOption = WatchFaceUtil.getBoolValue(element.getIsSupportOption());
        if (isOption) {
            Option option = element.getOption(element.getSelectedOption());
            if (option == null) {
                return;
            }

            List<Layer> optionLayers = option.getLayers();
            if (optionLayers == null || optionLayers.isEmpty()) {
                return;
            }

            for (Layer layer : optionLayers) {
                if (layer != null) {
                    IDrawUnit drawUnit = UIDrawUnitFactory.createDrawUnit(assetPackage, layer);
                    mDrawUnits.add(drawUnit);
                }
            }
        } else {
            List<Layer> elementLayers = element.getLayers();
            for (Layer layer : elementLayers) {
                if (layer != null) {
                    IDrawUnit drawUnit = UIDrawUnitFactory.createDrawUnit(assetPackage, layer);
                    mDrawUnits.add(drawUnit);
                }
            }
        }
    }

    @Override
    public void preDraw(Canvas canvas, boolean isAmbientMode) {
    }

    @Override
    public void draw(Canvas canvas) {
        for (IDrawUnit drawUnit : mDrawUnits) {
            if (drawUnit != null) {
                drawUnit.draw(canvas, isAmbientMode);
            }
        }
    }

    @Override
    public void onAmbientModeChanged(boolean inAmbientMode) {
        isAmbientMode = inAmbientMode;
        for (IDrawUnit drawUnit : mDrawUnits) {
            if (drawUnit != null) {
                drawUnit.isAmbientModeChanged(inAmbientMode);
            }
        }
    }

    @Override
    public void onClick(int x, int y) {
        for (IDrawUnit drawUnit : mDrawUnits) {
            if (drawUnit != null) {
                drawUnit.onClick(x, y);
            }
        }
    }

    @Override
    public AssetPackage getAssetPackage() {
        return assetPackage;
    }

    protected Element getElement() {
        LogUtil.i(TAG, "getElement:mLabel:" + mLabel);
        return assetPackage.getElementProvider().getElementWithLabel(mLabel);
    }

    protected Styles getStyles() {
        return assetPackage.getElementProvider().getStyles();
    }

    public List<IDrawUnit> getDrawUnits() {
        return mDrawUnits;
    }

    private void clearUnit() {
        for (IDrawUnit drawUnit : mDrawUnits) {
            if (drawUnit != null) {
                drawUnit.destroy();
            }
        }
        mDrawUnits.clear();
    }

    public void onReconstruct(AssetPackage assetPackage) {
        this.assetPackage = assetPackage;
        clearUnit();
        addUnit();
    }

    public void onDestroy() {
        clearUnit();
    }
}
