package com.goertek.commonlib.view.unit.basic;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.goertek.commonlib.provider.data.manager.DataAdapter;
import com.goertek.commonlib.provider.data.model.IntRangeValue;
import com.goertek.commonlib.provider.manager.AssetPackage;
import com.goertek.commonlib.provider.model.Layer;
import com.goertek.commonlib.utils.WatchFaceUtil;
import com.goertek.commonlib.view.unit.base.BaseDraw;

/**
 * 指针绘制类
 *
 * @author ww
 * @version 1.0.0
 * @since 2019/07/10
 */
public class HandDraw extends BaseDraw {
    private static final float FULL_CIRCLE_DEGREE = 360.0F;

    private static final float HALF = 0.5F;

    private static final float HALF_CIRCLE_DEGREE = 180.0F;

    private static final float SHADOW_OFFSET = 4.0F;

    private static final String TAG = "HandDraw";

    private Bitmap mActiveDotBitmap;

    private Bitmap mActiveLeftBitmap;

    private Bitmap mActiveRightBitmap;

    private Bitmap mActiveScaleBitmap;

    private Bitmap mAmbientDotBitmap;

    private Bitmap mAmbientLeftBitmap;

    private Bitmap mAmbientRightBitmap;

    private Bitmap mAmbientScaleBitmap;

    private float mDegree;

    private Bitmap mDotShadowBitmap;

    private boolean mIsShadowOnlyShowInActive;

    private Paint mPaint;

    private float mRotateEndAngel;

    private float[] mRotatePointFace;

    private float[] mRotatePointFaceRelative;

    private float[] mRotatePointHand;

    private float mRotateStartAngel;

    private Bitmap mShadowBitmap;

    private float mShadowOffSet;

    private String mValueType;

    public HandDraw(AssetPackage assetPackage, Layer layer) {
        super(assetPackage, layer);
        mActiveScaleBitmap = assetPackage.getBitmap(layer.getResActiveScale());
        mActiveLeftBitmap = assetPackage.getBitmap(layer.getResActiveLeft());
        mActiveRightBitmap = assetPackage.getBitmap(layer.getResActiveRight());
        mActiveDotBitmap = assetPackage.getBitmap(layer.getResActiveDot());
        mAmbientScaleBitmap = assetPackage.getBitmap(layer.getResAmbientScale());
        mAmbientLeftBitmap = assetPackage.getBitmap(layer.getResAmbientLeft());
        mAmbientRightBitmap = assetPackage.getBitmap(layer.getResAmbientRight());
        mAmbientDotBitmap = assetPackage.getBitmap(layer.getResAmbientDot());
        mShadowBitmap = assetPackage.getBitmap(layer.getResShadow());
        mDotShadowBitmap = assetPackage.getBitmap(layer.getResDotShadow());
        mIsShadowOnlyShowInActive = WatchFaceUtil.getBoolValue(layer.getResShadowIsOnlyShowActive());
        mRotateStartAngel = WatchFaceUtil.getFloatValue(layer.getRotateStartAngel());
        mRotateEndAngel = WatchFaceUtil.getFloatValue(layer.getRotateEndAngel(), 360.0F);
        mRotatePointHand = WatchFaceUtil.getPoint(layer.getRotatePointHand());
        mRotatePointFace = WatchFaceUtil.getPoint(layer.getRotatePointFace());
        mRotatePointFaceRelative = WatchFaceUtil.getPoint(layer.getRotatePointFaceRelative());
        mValueType = WatchFaceUtil.getStringValue(layer.getValueType());
        mShadowOffSet = WatchFaceUtil.getFloatValue(layer.getShadowOffSet());
        mPaint = new Paint();
        mPaint.setFlags(2);
    }

    private float getShadowOffset() {
        float shadowOffSet = mShadowOffSet;
        float shadown = shadowOffSet;
        if (shadowOffSet == 0.0F) {
            shadown = 4.0F;
        }
        return Float.valueOf(String.valueOf(Math.sin(mDegree * 0.017453292519943295D))).floatValue() * shadown;
    }

    @Override
    public void isAmbientModeChanged(boolean isAmbientMode) {
    }

    @Override
    protected void onDestroy() {
    }

    @Override
    protected void onDraw(Canvas canvas, boolean isAmbientMode) {
        Bitmap leftBitmap;
        Bitmap scalBitmap;
        if (isAmbientMode) {
            scalBitmap = mAmbientScaleBitmap;
        } else {
            scalBitmap = mActiveScaleBitmap;
        }
        if (scalBitmap != null) {
            canvas.drawBitmap(scalBitmap, mRotatePointFace[0] - scalBitmap.getWidth() * 0.5F, mRotatePointFace[1] - scalBitmap.getHeight() * 0.5F, mPaint);
        }
            canvas.save();
        float degree = mDegree;
        float[] rotatePointFace = mRotatePointFace;
        canvas.rotate(degree, rotatePointFace[0], rotatePointFace[1]);
        Bitmap shadowBitmap = mShadowBitmap;
        if (shadowBitmap != null) {
            if (!mIsShadowOnlyShowInActive) {
                canvas.drawBitmap(shadowBitmap, mRotatePointFace[0] - mRotatePointHand[0] + getShadowOffset(), mRotatePointFace[1] - mRotatePointHand[1], mPaint);
            } else if (!isAmbientMode) {
                canvas.drawBitmap(shadowBitmap, mRotatePointFace[0] - mRotatePointHand[0] - getShadowOffset(), mRotatePointFace[1] - mRotatePointHand[1], mPaint);
            }
        }
        if (isAmbientMode) {
            leftBitmap = mAmbientLeftBitmap;
        } else {
            leftBitmap = mActiveLeftBitmap;
        }
        if (isAmbientMode) {
            shadowBitmap = mAmbientRightBitmap;
        } else {
            shadowBitmap = mActiveRightBitmap;
        }
        if (mDegree < 180.0F) {
            if (shadowBitmap != null) {
                leftBitmap = shadowBitmap;
            }
        } else if (leftBitmap == null) {
            leftBitmap = shadowBitmap;
        }
        if (leftBitmap != null) {
            float[] rotatePoint = mRotatePointFace;
            degree = rotatePoint[0];
            float[] rotatePointHand = mRotatePointHand;
            canvas.drawBitmap(leftBitmap, degree - rotatePointHand[0], rotatePoint[1] - rotatePointHand[1],mPaint);
        }
        canvas.restore();
        shadowBitmap = mDotShadowBitmap;
        if (shadowBitmap != null) {
            canvas.drawBitmap(shadowBitmap, mRotatePointFace[0] - shadowBitmap.getWidth() * 0.5F, mRotatePointFace[1] - mDotShadowBitmap.getHeight() * 0.5F, mPaint);
        }
        if (isAmbientMode) {
            shadowBitmap = mAmbientDotBitmap;
        } else {
            shadowBitmap = mActiveDotBitmap;
        }
        if (shadowBitmap != null) {
            canvas.drawBitmap(shadowBitmap, mRotatePointFace[0] - shadowBitmap.getWidth() * 0.5F, mRotatePointFace[1] - shadowBitmap.getHeight() * 0.5F, mPaint);
        }
    }

    @Override
    public void preDraw(Canvas canvas, boolean isAmbientMode) {
        IntRangeValue intRangeValue = DataAdapter.getInstance().getIntRangeValue(mValueType);
        if (intRangeValue == null) {
            mDegree = 0.0F;
            return;
        }
        mDegree = WatchFaceUtil.getAngel(mRotateStartAngel, mRotateEndAngel, intRangeValue.getmMax(), intRangeValue.getmMin(), intRangeValue.getmValue());
    }

    @Override
    public void setWidgetRect(RectF rect) {
        super.setWidgetRect(rect);
        if (rect == null) {
            return;
        }
        if (mRotatePointFaceRelative == null) {
            return;
        }
        mRotatePointFace = new float[]{rect.left + mRotatePointFaceRelative[0], rect.top + mRotatePointFaceRelative[1]};
    }
}

