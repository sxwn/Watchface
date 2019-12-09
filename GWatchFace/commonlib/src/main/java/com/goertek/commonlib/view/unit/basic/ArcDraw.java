package com.goertek.commonlib.view.unit.basic;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.text.TextUtils;

import com.goertek.commonlib.provider.data.manager.DataAdapter;
import com.goertek.commonlib.provider.data.model.IntRangeValue;
import com.goertek.commonlib.provider.manager.AssetPackage;
import com.goertek.commonlib.provider.model.Layer;
import com.goertek.commonlib.utils.WatchFaceUtil;
import com.goertek.commonlib.view.unit.base.BaseDraw;

/**
 * 弧形绘制类
 *
 * @author ww
 * @version 1.0.0
 * @since 2019/07/08
 */
public class ArcDraw extends BaseDraw {
    private static final String TAG = "ArcDraw";

    private static final String PAINT_CAP_ROUND = "round";

    private static final float FULL_CIRCLE_ANGLE = 360.0f;

    private int[] mPrimaryColor;

    private int[] mSecondaryColor;

    private int mLineWidth;

    private String mLineCap;

    private float mStartAngle;

    private float mEndAngle;

    private RectF mRect;

    private RectF mRectRelative;

    private String mValueType;

    private Paint mPrimaryPaint;

    private Paint mSecondaryPaint;

    private float mArcStartAngle;

    private float mArcSweepAngle;

    private float mShadowStartAngle;

    private float mShadowSweepAngle;

    /**
     * 初始化弧形绘制资源
     *
     * @param assetPackage 资源包
     * @param layer 图层
     */
    public ArcDraw(AssetPackage assetPackage, Layer layer) {
        super(assetPackage, layer);
        mPrimaryColor = WatchFaceUtil.getColors(layer.getPrimaryColor());
        mSecondaryColor = WatchFaceUtil.getColors(layer.getSecondaryColor());
        mLineWidth = WatchFaceUtil.getIntValue(layer.getLineWidth());
        mLineCap = WatchFaceUtil.getStringValue(layer.getLineCap());
        mStartAngle = WatchFaceUtil.getFloatValue(layer.getStartAngle());
        mEndAngle = WatchFaceUtil.getFloatValue(layer.getEndAngle());
        mRect = WatchFaceUtil.getRectF(layer.getRect());
        mRectRelative = WatchFaceUtil.getRectF(layer.getRectRelative());
        mValueType = WatchFaceUtil.getStringValue(layer.getValueType());
    }

    @Override
    public void setWidgetRect(RectF rect) {
        super.setWidgetRect(rect);
        if (rect == null) {
            return;
        }
        if (mRectRelative == null) {
            return;
        }
        mRect = new RectF(rect.left + mRectRelative.left, rect.top + mRectRelative.top, rect.left + mRectRelative.right,
                rect.top + mRectRelative.bottom);
    }

    @Override
    public void isAmbientModeChanged(boolean ambientMode) {
    }

    private void updatePaint() {
        if ((mPrimaryColor != null) && (mPrimaryColor.length > 0)) {
            mPrimaryPaint = new Paint();
            mPrimaryPaint.setAntiAlias(true);
            mPrimaryPaint.setStyle(Paint.Style.STROKE);
            mPrimaryPaint.setStrokeWidth(mLineWidth);
            mPrimaryPaint.setStrokeCap((TextUtils.equals(mLineCap, PAINT_CAP_ROUND)) ? (Paint.Cap.ROUND)
                                               : (Paint.Cap.BUTT));
            // 设置渐变需要两种以上的颜色
            if (mPrimaryColor.length > 1) {
                SweepGradient sweepGradient = new SweepGradient(mRect.centerX(), mRect.centerY(), mPrimaryColor,
                        new float[]{0, mArcSweepAngle / FULL_CIRCLE_ANGLE});
                Matrix matrix = new Matrix();
                matrix.setRotate(mArcStartAngle, mRect.centerX(), mRect.centerY());
                sweepGradient.setLocalMatrix(matrix);
                mPrimaryPaint.setShader(sweepGradient);
            } else {
                mPrimaryPaint.setColor(mPrimaryColor[0]);
            }
        }

        if ((mSecondaryColor != null) && (mSecondaryColor.length > 0)) {
            mSecondaryPaint = new Paint();
            mSecondaryPaint.setAntiAlias(true);
            mSecondaryPaint.setStrokeWidth(mLineWidth);
            mSecondaryPaint.setStrokeCap((TextUtils.equals(mLineCap, PAINT_CAP_ROUND)) ? (Paint.Cap.ROUND)
                                                 : (Paint.Cap.BUTT));
            // 设置渐变需要两种以上的颜色
            if (mSecondaryColor.length > 1) {
                SweepGradient sweepGradient = new SweepGradient(mRect.centerX(), mRect.centerY(), mSecondaryColor,
                        new float[]{0, mShadowSweepAngle / FULL_CIRCLE_ANGLE});
                Matrix matrix = new Matrix();
                matrix.setRotate(mShadowStartAngle, mRect.centerX(), mRect.centerY());
                sweepGradient.setLocalMatrix(matrix);
                mSecondaryPaint.setShader(sweepGradient);
            } else {
                mSecondaryPaint.setColor(mSecondaryColor[0]);
            }
        }
    }

    @Override
    public void preDraw(Canvas canvas, boolean isAmbientMode) {
        mShadowStartAngle = (mStartAngle < mEndAngle) ? mStartAngle : mEndAngle;
        mShadowSweepAngle = (mStartAngle < mEndAngle) ? (mEndAngle - mStartAngle) : (mStartAngle - mEndAngle);
        IntRangeValue rangeValue = DataAdapter.getInstance().getIntRangeValue(mValueType);
        if (rangeValue == null) {
            mArcStartAngle = (mStartAngle < mEndAngle) ? mStartAngle : mEndAngle;
            mArcSweepAngle = 0;
        } else {
            float angle = WatchFaceUtil.getAngel(mStartAngle, mEndAngle, rangeValue.getmMax(), rangeValue.getmMin(), rangeValue
                                                                                                                           .getmValue());
            mArcStartAngle = (mStartAngle < mEndAngle) ? mStartAngle : angle;
            mArcSweepAngle = (mStartAngle < mEndAngle) ? (angle - mStartAngle) : (mStartAngle - angle);
        }
        updatePaint();
    }

    @Override
    protected void onDraw(Canvas canvas, boolean isAmbientMode) {
        if (!isAmbientMode) {
            if (mSecondaryPaint != null) {
                canvas.drawArc(mRect, mShadowStartAngle, mShadowSweepAngle, false, mSecondaryPaint);
            }
            if (mPrimaryPaint != null) {
                canvas.drawArc(mRect, mArcStartAngle, mArcSweepAngle, false, mPrimaryPaint);
            }
        }
    }

    @Override
    protected void onDestroy() {
    }
}
