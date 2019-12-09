package com.goertek.commonlib.view.unit.basic;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.goertek.commonlib.provider.data.manager.DataAdapter;
import com.goertek.commonlib.provider.manager.AssetPackage;
import com.goertek.commonlib.provider.model.Layer;
import com.goertek.commonlib.view.unit.base.BaseResDraw;

import java.util.List;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/10
 */
public class SelectedResDraw extends BaseResDraw {
    private static final String TAG = "SelectedResDraw";

    private int mResIndex;

    public SelectedResDraw(AssetPackage assetPackage, Layer layer) {
        super(assetPackage, layer);
    }

    public void isAmbientModeChanged(boolean isAmbientMode) {
    }

    protected void onDestroy() {
    }

    public void preDraw(Canvas canvas, boolean isAmbientMode) {
        mResIndex = DataAdapter.getInstance().getIndexValue(getValueType());
    }

    protected void onDraw(Canvas canvas, boolean isAmbientMode) {
        List bitmapList;
        if (isAmbientMode) {
            bitmapList = getAmbientBitmaps();
        } else {
            bitmapList = getActiveBitmaps();
        }
        if (bitmapList != null) {
            Bitmap bitmap;
            if (bitmapList.size() <= 0) {
                return;
            }
            if (mResIndex < bitmapList.size()) {
                bitmap = (Bitmap) bitmapList.get(mResIndex);
            } else {
                bitmap = (Bitmap) bitmapList.get(0);
            }
            if (bitmap != null) {
                canvas.drawBitmap(bitmap, getPosition()[0], getPosition()[1], getPaint());
            }
        }
    }
}

