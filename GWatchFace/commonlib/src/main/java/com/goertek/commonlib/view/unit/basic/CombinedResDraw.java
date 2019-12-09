package com.goertek.commonlib.view.unit.basic;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.text.TextUtils;

import com.goertek.commonlib.provider.data.manager.DataAdapter;
import com.goertek.commonlib.provider.manager.AssetPackage;
import com.goertek.commonlib.provider.model.Layer;
import com.goertek.commonlib.utils.LogUtil;
import com.goertek.commonlib.utils.WatchFaceUtil;
import com.goertek.commonlib.view.unit.UnitConstants;
import com.goertek.commonlib.view.unit.base.BaseResDraw;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合图绘制类，支持位置变换效果
 *
 * @author ww
 * @version 1.0.0
 * @since 2019/07/10
 */
public class CombinedResDraw extends BaseResDraw {
    private static final String TAG = "CombinedResDraw";

    private static final float HALF = 0.5f;

    private String mResAlign;

    private List<Bitmap> mBitmapList;

    private float mStartX;

    private float mStartY;

    /**
     * 初始化组合图绘制资源
     *
     * @param assetPackage 资源包
     * @param layer        图层
     */
    public CombinedResDraw(AssetPackage assetPackage, Layer layer) {
        super(assetPackage, layer);
        LogUtil.e(TAG,"layer====" + layer);
        mResAlign = WatchFaceUtil.getStringValue(layer.getResAlign());
        mBitmapList = new ArrayList(0);
    }

    @Override
    public void preDraw(Canvas canvas, boolean isAmbientMode) {
        LogUtil.e(TAG,"preDraw is Running----");
        mBitmapList.clear();
        String valueString = DataAdapter.getInstance().getStringValue(getValueType());
        LogUtil.e(TAG, "valueType ========" + valueString);
        List<Bitmap> bitmaps = isAmbientMode ? getAmbientBitmaps() : getActiveBitmaps();
        LogUtil.e(TAG, "activeMapsSize ====" + bitmaps.size());
        if (bitmaps == null || bitmaps.size() == 0) {
            LogUtil.e(TAG,"preDraw bitmaps is null");
            return;
        }
        int totalWidth = 0;
        int bitmapSize = bitmaps.size();
        for (int i = 0; i < valueString.length(); i++) {
            int digit = WatchFaceUtil.getDigit(valueString, i);
            LogUtil.e(TAG, "digit ====" + digit);
            if ((digit >= bitmapSize) && (bitmapSize > 0)) {
                digit %= bitmapSize;
            }
            // 要求mAmbientBitmaps和mActiveBitmaps 必须是0~9十个数字按顺序排列的图片资源数组
            mBitmapList.add(bitmaps.get(digit));
            totalWidth += bitmaps.get(digit).getWidth();
        }

        LogUtil.e(TAG, "mBitmapList =====" + mBitmapList);
        if (TextUtils.equals(mResAlign, UnitConstants.VALUE_ALIGN_RIGHT)) {
            mStartX = getPosition()[0] - totalWidth;
        } else if (TextUtils.equals(mResAlign, UnitConstants.VALUE_ALIGN_CENTER)) {
            mStartX = getPosition()[0] - totalWidth * HALF;
        } else {
            mStartX = getPosition()[0];
        }
        mStartY = getPosition()[1];
    }

    @Override
    protected void onDraw(Canvas canvas, boolean isAmbientMode) {
        if (mBitmapList.size() == 0) {
            LogUtil.e(TAG, "onDraw() combined bitmap list empty");
            return;
        }
        float startX = mStartX;
        float startY = mStartY;
        LogUtil.e(TAG, "startX ===" + startX + "startY " + startY);
        for (Bitmap bitmap : mBitmapList) {
            if (bitmap == null) {
                continue;
            }
            canvas.drawBitmap(bitmap, startX, startY, getPaint());
            startX += bitmap.getWidth();
        }
    }

    @Override
    protected void onDestroy() {
        mBitmapList.clear();
    }

    @Override
    public void isAmbientModeChanged(boolean ambientMode) {
    }
}

