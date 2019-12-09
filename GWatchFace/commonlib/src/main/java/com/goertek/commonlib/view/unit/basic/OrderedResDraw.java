package com.goertek.commonlib.view.unit.basic;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.goertek.commonlib.provider.manager.AssetPackage;
import com.goertek.commonlib.provider.model.Layer;
import com.goertek.commonlib.utils.FpsUtils;
import com.goertek.commonlib.utils.LogUtil;
import com.goertek.commonlib.utils.WatchFaceUtil;
import com.goertek.commonlib.view.unit.base.BaseResDraw;


/**
 * 序列帧绘制类，不支持位置变换效果
 *
 * @author ww
 * @version 1.0.0
 * @since 2019/07/10
 */
public class OrderedResDraw extends BaseResDraw {
    private static final String TAG = "OrderedResDraw";

    private static final int RES_INDEX_INVALID = -1;

    private int mResIndex;

    private long mResInterval;

    private long mLastDrawTime;

    private boolean mResOrderedIsPlayOnce; // 帧动画是否需要播放一次

    private boolean mIsPlayOnceFinish = false; // 帧动画是否播放一次已经完成

    /**
     * 初始化序列帧绘制资源
     *
     * @param assetPackage 资源包
     * @param layer 图层
     */
    public OrderedResDraw(AssetPackage assetPackage, Layer layer) {
        super(assetPackage, layer);
        mResInterval = WatchFaceUtil.getLongValue(layer.getResInterval());
        mResOrderedIsPlayOnce = WatchFaceUtil.getBoolValue(layer.getResOrderedIsPlayOnce());
        mLastDrawTime = 0L;
        FpsUtils.setUpdateInterval(mResInterval);
    }

    @Override
    public void preDraw(Canvas canvas, boolean isAmbientMode) {
        if (isAmbientMode || mIsPlayOnceFinish) {
            mResIndex = RES_INDEX_INVALID;
            return;
        }
        long curTime = System.currentTimeMillis();
        if ((curTime - mLastDrawTime) < mResInterval) {
            return;
        }
        mLastDrawTime = curTime;
        mResIndex++;
        if (mResIndex >= getActiveBitmaps().size()) {
            if (mResOrderedIsPlayOnce) {
                mIsPlayOnceFinish = true;
            }
            mResIndex = 0;
        }
    }

    @Override
    protected void onDraw(Canvas canvas, boolean isAmbientMode) {
        if (isAmbientMode) {
            if ((getAmbientBitmaps() != null) && (getAmbientBitmaps().size() > 0)) {
                Bitmap bitmap = getAmbientBitmaps().get(0);

                if (bitmap != null) {
                    canvas.drawBitmap(bitmap, getPosition()[0], getPosition()[1], getPaint());
                }
            }
        } else {
            Bitmap bitmap;
            if (mResOrderedIsPlayOnce) {
                if (!mIsPlayOnceFinish) {
                    bitmap = getActiveBitmaps().get(mResIndex);
                } else {
                    bitmap = getActiveBitmaps().get(getActiveBitmaps().size() - 1); // 播放完停留在最后一帧
                    FpsUtils.resetUpdateInterval();
                }
            } else {
                bitmap = getActiveBitmaps().get(mResIndex);
            }

            if (bitmap != null) {
                canvas.drawBitmap(bitmap, getPosition()[0], getPosition()[1], getPaint());
            }
        }
    }

    @Override
    protected void onDestroy() {
    }

    @Override
    public void isAmbientModeChanged(boolean ambientMode) {
        if (mResOrderedIsPlayOnce) {
            if (!ambientMode) {
                mResIndex = RES_INDEX_INVALID; // index复位
                mIsPlayOnceFinish = false;
                FpsUtils.setUpdateInterval(mResInterval);
                LogUtil.i(TAG, "restart play");
            }
        }
    }
}

