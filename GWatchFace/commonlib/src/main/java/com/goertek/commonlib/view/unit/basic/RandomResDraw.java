package com.goertek.commonlib.view.unit.basic;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.goertek.commonlib.provider.manager.AssetPackage;
import com.goertek.commonlib.provider.model.Layer;
import com.goertek.commonlib.view.unit.base.BaseResDraw;

import java.util.List;
import java.util.Random;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/10
 */
public class RandomResDraw extends BaseResDraw {
    private static final int MIN_SIZE = 2;

    private static final int RANDOM_SEED = 100;

    private static final int SHOWED_SIZE = 1;

    private static final String TAG = "RandomResDraw";

    private int mActiveIndex;

    private int mAmbientIndex;

    private int mPointXIndex = 0;

    private int mPointYIndex = 1;

    private Random mRandom = new Random();

    private Bitmap mRandomBitmap;

    private int mRandomValue = 0;

    public RandomResDraw(AssetPackage assetPackage, Layer layer) {
        super(assetPackage, layer);
        mRandomValue = mRandom.nextInt(100);
    }

    private void calcRandomValue() {
        if (mRandom == null) {
            mRandom = new Random();
        }
        mRandomValue = mRandom.nextInt(100);
        List activeBitmaps = getActiveBitmaps();
        Bitmap bitmap = (Bitmap) activeBitmaps.get(mActiveIndex);
        activeBitmaps.remove(mActiveIndex);
        activeBitmaps.add(bitmap);
        List ambientBitmaps = getAmbientBitmaps();
        int activeSize = activeBitmaps.size();
        if (activeSize < 2) {
            activeSize = 0;
        } else {
            activeSize = mRandomValue % (activeBitmaps.size() - 1);
        }
        this.mActiveIndex = activeSize;
        if (ambientBitmaps.size() < 2) {
            activeSize = 0;
        } else {
            activeSize = this.mRandomValue % (ambientBitmaps.size() - 1);
        }
        mAmbientIndex = activeSize;
    }

    public int getActiveIndex() {
        return mActiveIndex;
    }

    public void isAmbientModeChanged(boolean isAmbientMode) {
        if (isAmbientMode) {
            calcRandomValue();
        }
    }

    protected void onDestroy() {
    }

    public void preDraw(Canvas canvas, boolean isAmbientMode) {
        if (isAmbientMode) {
            mRandomBitmap = getAmbientBitmaps().get(this.mAmbientIndex);
            return;
        }
        mRandomBitmap = getActiveBitmaps().get(this.mActiveIndex);
    }

    protected void onDraw(Canvas canvas, boolean isAmbientMode) {
        if (mRandomBitmap != null) {
            if (getPosition().length <= mPointYIndex) {
                return;
            }
            canvas.drawBitmap(mRandomBitmap, getPosition()[mPointXIndex], getPosition()[mPointYIndex], getPaint());
        }
    }

    public void onVisibilityChanged(boolean isAmbientMode) {
        if (!isAmbientMode) {
            calcRandomValue();
        }
    }

}
