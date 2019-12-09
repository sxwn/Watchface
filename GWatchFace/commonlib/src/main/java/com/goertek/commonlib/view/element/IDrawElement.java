package com.goertek.commonlib.view.element;

import android.graphics.Canvas;

import com.goertek.commonlib.provider.manager.AssetPackage;

public interface IDrawElement {
    void preDraw(Canvas canvas, boolean isAmbientMode);

    void draw(Canvas canvas);

    /**
     * onAmbientModeChanged
     * @param inAmbientMode
     */
    void onAmbientModeChanged(boolean inAmbientMode);

    /**
     * click
     * @param x
     * @param y
     */
    void onClick(int x,int y);

    AssetPackage getAssetPackage();

    /**
     * 重新构建元素
     * @param assetPackage
     */
    void onReconstruct(AssetPackage assetPackage);
}
