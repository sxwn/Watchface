package com.goertek.commonlib.view.unit.base;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.goertek.commonlib.provider.manager.AssetPackage;
import com.goertek.commonlib.provider.model.Layer;
import com.goertek.commonlib.utils.LogUtil;
import com.goertek.commonlib.utils.WatchFaceUtil;

import java.util.List;

/**
 * 选图绘制类，支持位置变换效果
 *
 * @author ww
 * @version 1.0.0
 * @since 2019/07/05
 */
public class BaseResDraw extends BaseDraw {
    private static final String TAG = "BaseResDraw";

    private List<Bitmap> mActiveBitmaps;

    private List<Bitmap> mAmbientBitmaps;

    private float[] mPosition;

    private float[] mPositionRelative;

    private String mValueType;

    private Paint mPaint;

    /**
     * 初始化选图绘制资源
     *
     * @param assetPackage 资源包
     * @param layer        图层
     */
    public BaseResDraw(AssetPackage assetPackage, Layer layer) {
        super(assetPackage, layer);
        LogUtil.e(TAG,"layer=========" +layer);
        mActiveBitmaps = assetPackage.getBitmaps(layer.getResActive());
        mAmbientBitmaps = assetPackage.getBitmaps(layer.getResAmbient());
        mPosition = WatchFaceUtil.getPoint(layer.getResPosition());
        mPositionRelative = WatchFaceUtil.getPoint(layer.getResPositionRelative());
        mValueType = WatchFaceUtil.getStringValue(layer.getValueType());
        mPaint = new Paint();
        mPaint.setFlags(Paint.FILTER_BITMAP_FLAG);
    }

    @Override
    public void preDraw(Canvas canvas, boolean isAmbientMode) {

    }

    @Override
    public void setWidgetRect(RectF rect) {
        super.setWidgetRect(rect);
        if (rect == null) {
            return;
        }
        if ((mPositionRelative != null) && (mPositionRelative.length != 0)) {
            mPosition = new float[]{rect.left + mPositionRelative[0], rect.top + mPositionRelative[1]};
        }
    }

    @Override
    public void isAmbientModeChanged(boolean ambientMode) {

    }

    @Override
    protected void onDraw(Canvas canvas, boolean isAmbientMode) {

    }

    @Override
    protected void onDestroy() {

    }

    public List<Bitmap> getActiveBitmaps() {
        LogUtil.e(TAG, "mActiveBitmaps========" + mActiveBitmaps.size());
        return mActiveBitmaps;
    }

    public List<Bitmap> getAmbientBitmaps() {
        return mAmbientBitmaps;
    }

    protected float[] getPosition() {
        return mPosition;
    }

    protected void setPosition(float[] position) {
        mPosition = position;
    }

    protected String getValueType() {
        return mValueType;
    }

    protected Paint getPaint() {
        return mPaint;
    }
}
