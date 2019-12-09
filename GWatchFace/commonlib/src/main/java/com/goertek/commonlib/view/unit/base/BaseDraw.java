package com.goertek.commonlib.view.unit.base;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.text.TextUtils;

import com.goertek.commonlib.attribute.AttributeBean;
import com.goertek.commonlib.provider.data.manager.DataAdapter;
import com.goertek.commonlib.provider.data.model.IntRangeValue;
import com.goertek.commonlib.provider.manager.AssetPackage;
import com.goertek.commonlib.provider.model.Layer;
import com.goertek.commonlib.utils.FpsUtils;
import com.goertek.commonlib.utils.WatchFaceUtil;
import com.goertek.commonlib.view.unit.UnitConstants;
import com.goertek.commonlib.view.unit.animation.RotateAnimation;
import com.goertek.commonlib.view.unit.animation.ScaleAnimation;

import java.util.List;

/**
 * 抽象绘制类
 *
 * 主要描述绘制的动态属性
 * 包括 scale(缩放) 、 rotate(旋转)
 *
 * @author ww
 * @version 1.0.0
 * @since 2019/07/04
 */
public abstract class BaseDraw implements IDrawUnit {
    private String mRotateType;

    private float mRotateFixedDegree;

    private float[] mRotateCenterPoint;

    private float[] mRotateCenterPointRelative;

    private float[] mRotateDegree;

    private float[] mRotateTime;

    private List<String> mRotateMotionType;

    private String mTranslateType;

    private float[] mResPosition;

    private float[] mResPositionRelative;

    private float[] mTranslateEndPosition;

    private float[] mTranslateEndPositionRelative;

    private String mValueType;

    private String mScaleType;

    private float[] mScaleAmount;

    private float[] mScaleTime;

    private List<String> mScaleMotionType;

    private float[] mScaleCenterPoint;

    private float[] mScaleCenterPointRelative;

    private RotateAnimation mRotateAnimation;

    private ScaleAnimation mScaleAnimation;

    private float mArcLinearMargin;

    /**
     * 初始化资源
     *
     * @param assetPackage 资源包
     * @param layer 图层
     */
    protected BaseDraw(AssetPackage assetPackage, Layer layer) {
        mRotateType = WatchFaceUtil.getStringValue(layer.getRotateType());
        mRotateFixedDegree = WatchFaceUtil.getFloatValue(layer.getRotateFixedDegree());
        mRotateCenterPoint = WatchFaceUtil.getFloatValues(layer.getRotateCenterPoint());
        mRotateCenterPointRelative = WatchFaceUtil.getFloatValues(layer.getRotateCenterPointRelative());
        mRotateDegree = WatchFaceUtil.getFloatValues(layer.getRotateDegree());
        mRotateTime = WatchFaceUtil.getFloatValues(layer.getRotateTime());
        mRotateMotionType = WatchFaceUtil.getStringValues(layer.getRotateMotionType());
        if (TextUtils.equals(mRotateType, "time_variant")) {
            this.mRotateAnimation = new RotateAnimation(mRotateDegree, mRotateTime, mRotateMotionType);
            FpsUtils.setUpdateInterval(67L);
        }

        if (TextUtils.equals(this.mRotateType, "gyro_variant")) {
            FpsUtils.setUpdateInterval(67L);
        }

        mScaleType = WatchFaceUtil.getStringValue(layer.getScaleType());
        mScaleAmount = WatchFaceUtil.getFloatValues(layer.getScaleAmount());
        mScaleTime = WatchFaceUtil.getFloatValues(layer.getScaleTime());
        mScaleMotionType = WatchFaceUtil.getStringValues(layer.getScaleMotionType());
        mScaleCenterPoint = WatchFaceUtil.getFloatValues(layer.getScaleCenterPoint());
        mScaleCenterPointRelative = WatchFaceUtil.getFloatValues(layer.getScaleCenterPointRelative());
        if (TextUtils.equals(this.mScaleType, "time_variant")) {
            this.mScaleAnimation = new ScaleAnimation(this.mScaleAmount, this.mScaleTime, this.mScaleMotionType);
            FpsUtils.setUpdateInterval(67L);
        }

        mTranslateType = WatchFaceUtil.getStringValue(layer.getTranslateType());
        mResPosition = WatchFaceUtil.getFloatValues(layer.getResPosition());
        mResPositionRelative = WatchFaceUtil.getFloatValues(layer.getResPositionRelative());
        mTranslateEndPosition = WatchFaceUtil.getFloatValues(layer.getTranslateEndPosition());
        mTranslateEndPositionRelative = WatchFaceUtil.getFloatValues(layer.getTranslateEndPositionRelative());
        mValueType = WatchFaceUtil.getStringValue(layer.getValueType());
    }

    @Override
    public void setWidgetRect(RectF rect) {
        if (rect == null) {
            return;
        }
        if ((mRotateCenterPointRelative != null) && (mRotateCenterPointRelative.length != 0)) {
            mRotateCenterPoint =
                    new float[] {rect.left + mRotateCenterPointRelative[0], rect.top + mRotateCenterPointRelative[1]};
        }
        if ((mScaleCenterPointRelative != null) && (mScaleCenterPointRelative.length != 0)) {
            mScaleCenterPoint =
                    new float[] {rect.left + mScaleCenterPointRelative[0], rect.top + mScaleCenterPointRelative[1]};
        }
        if ((mTranslateEndPositionRelative != null) && (mTranslateEndPositionRelative.length != 0)) {
            mTranslateEndPosition =
                    new float[] {rect.left + mTranslateEndPositionRelative[0], rect.top + mTranslateEndPositionRelative[1]};
        }
        if ((mResPositionRelative != null) && (mResPositionRelative.length != 0)) {
            mResPosition = new float[] {rect.left + mResPositionRelative[0], rect.top + mResPositionRelative[1]};
        }
    }

    private void doRotate(Canvas canvas) {
        if (!TextUtils.isEmpty(mRotateType)) {
            switch (mRotateType) {
                case UnitConstants.VALUE_ROTATE_TYPE_FIXED:
                    canvas.rotate(mRotateFixedDegree, mRotateCenterPoint[0], mRotateCenterPoint[1]);
                    break;
                case UnitConstants.VALUE_ROTATE_TYPE_TIME_VARIANT:
                    canvas.rotate(mRotateAnimation.getDegree(), mRotateCenterPoint[0], mRotateCenterPoint[1]);
                    break;
                default:
                    break;
            }
        }
    }

    public void setRotateCenterPoint(float startX,float startY) {
        mRotateCenterPoint[0] = startX;
        mRotateCenterPoint[1] = startY;
    }

    private void doScale(Canvas canvas) {
        if (!TextUtils.isEmpty(mScaleType)) {
            switch (mScaleType) {
                case UnitConstants.VALUE_SCALE_TYPE_TIME_VARIANT:
                    float amount = mScaleAnimation.getAmount();
                    canvas.scale(amount, amount, mScaleCenterPoint[0], mScaleCenterPoint[1]);
                    break;
                default:
                    break;
            }
        }
    }

    private void doTranslate(Canvas canvas) {
        if (!TextUtils.isEmpty(mTranslateType)) {
            switch (mTranslateType) {
                case UnitConstants.VALUE_TRANSLATE_TYPE_FIXED:
                    IntRangeValue intRangeValue = DataAdapter.getInstance().getIntRangeValue(mValueType);
                    float[] relative = WatchFaceUtil.getValueRelative(mResPosition, mTranslateEndPosition,
                            intRangeValue.getmMax(), intRangeValue.getmMin(), intRangeValue.getmValue());
                    canvas.translate(relative[0], relative[1]);
                    break;
                default:
                    break;
            }
        }
    }

    private void doAnimation(Canvas canvas) {
        // first scale, then rotate, then translate
        doScale(canvas);
        doRotate(canvas);
        doTranslate(canvas);
    }

    @Override
    public void draw(Canvas canvas, boolean isAmbientMode) {
        preDraw(canvas, isAmbientMode);
        canvas.save();
        doAnimation(canvas);
        onDraw(canvas, isAmbientMode);
        canvas.restore();
    }

    public float getArcLinearMargin() {
        return mArcLinearMargin;
    }

    @Override
    public float[] getDrawUnitEndPosition() {
        return new float[0];
    }

    /**
     * 设置旋转
     *
     * @param degree 旋转角度
     * @return 旋转后的bitmap
     */
    public void setRotateAnimation(float degree) {
        mRotateFixedDegree = degree;
    }

    @Override
    public void destroy() {
        onDestroy();
    }

    @Override
    public void onVisibilityChanged(boolean visible) {
    }

    @Override
    public void setRelativeAttribute(AttributeBean bean) {
    }

    /**
     * 获取控件对齐方式
     *
     * @return String 返回对齐方式
     */
    public String getDrawUnitAlign() {
        return UnitConstants.VALUE_ALIGN_LEFT;
    }

    /**
     * 绘制
     *
     * @param canvas 画笔
     * @param isAmbientMode 是否处于半亮模式
     */
    protected abstract void onDraw(Canvas canvas, boolean isAmbientMode);

    /**
     * 资源销毁
     */
    protected abstract void onDestroy();

    @Override
    public void onClick(int x, int y) {
    }
}
