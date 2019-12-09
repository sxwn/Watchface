package com.goertek.commonlib.view.unit.basic;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.text.TextUtils;

import com.goertek.commonlib.provider.data.manager.DataAdapter;
import com.goertek.commonlib.provider.data.model.IntRangeValue;
import com.goertek.commonlib.provider.manager.AssetPackage;
import com.goertek.commonlib.provider.model.Layer;
import com.goertek.commonlib.utils.WatchFaceUtil;
import com.goertek.commonlib.view.unit.base.BaseDraw;

public class LineDraw extends BaseDraw {
    private static final String PAINT_CAP_ROUND = "round";

    private static final String TAG = "LineDraw";

    private float[] mEndPoint;

    private float[] mEndPointRelative;

    private String mLineCap;

    private int mLineWidth;

    private int[] mPrimaryColor;

    private Paint mPrimaryPaint = null;

    private int[] mSecondaryColor;

    private Paint mSecondaryPaint = null;

    private float[] mStartPoint;

    private float[] mStartPointRelative;

    private float[] mValuePoint;

    private String mValueType;
    public LineDraw(AssetPackage assetPackage, Layer layer) {
        super(assetPackage, layer);
        mPrimaryColor = WatchFaceUtil.getColors(layer.getPrimaryColor());
        mSecondaryColor = WatchFaceUtil.getColors(layer.getSecondaryColor());
        mLineWidth = WatchFaceUtil.getIntValue(layer.getLineWidth());
        mLineCap = WatchFaceUtil.getStringValue(layer.getLineCap());
        mStartPoint = WatchFaceUtil.getPoint(layer.getStartPoint());
        mEndPoint = WatchFaceUtil.getPoint(layer.getEndPoint());
        mStartPointRelative = WatchFaceUtil.getPoint(layer.getStartPointRelative());
        mEndPointRelative = WatchFaceUtil.getPoint(layer.getEndPointRelative());
        mValueType = WatchFaceUtil.getStringValue(layer.getValueType());
    }

    @Override
    protected void onDestroy() {

    }

    @Override
    public void preDraw(Canvas canvas, boolean isAmbientMode) {
        IntRangeValue intRangeValue = DataAdapter.getInstance().getIntRangeValue(mValueType);
        if (intRangeValue == null) {
            mValuePoint = mStartPoint;
            return;
        }
        mValuePoint = WatchFaceUtil.getValuePoint(mStartPoint, mEndPoint, intRangeValue.getmMax(), intRangeValue.getmMin(), intRangeValue.getmValue());
        updatePaint();
    }

    /**
     * 画直线
     *
     * @param canvas 画布
     * @param isAmbientMode 是否处于半亮模式
     * 关键API：void drawLine (float startX, float startY, float stopX, float stopY, Paint paint)
     */
    @Override
    protected void onDraw(Canvas canvas, boolean isAmbientMode) {
        Paint paint = mSecondaryPaint;
        if (paint != null) {
            float[] startPoints = mStartPoint;
            float pointX = startPoints[0];
            float pointY = startPoints[1];
            startPoints = mEndPoint;
            canvas.drawLine(pointX, pointY, startPoints[0], startPoints[1], paint);
        }
        paint = mPrimaryPaint;
        if (paint != null) {
            float[] startPoints = mStartPoint;
            float startPointX = startPoints[0];
            float startPointY = startPoints[1];
            startPoints = mValuePoint;
            canvas.drawLine(startPointX, startPointY, startPoints[0], startPoints[1], paint);
        }
    }

    private void updatePaint(){
        int[] mPrimaryColors = mPrimaryColor;
        if (mPrimaryColors != null && mPrimaryColors.length > 0) {
            Paint.Cap cap;
            mPrimaryPaint = new Paint();
            mPrimaryPaint.setAntiAlias(true);
            mPrimaryPaint.setStyle(Paint.Style.STROKE);
            mPrimaryPaint.setStrokeWidth(mLineWidth);
            Paint paint = mPrimaryPaint;
            if (TextUtils.equals(mLineCap, "round")) {
                cap = Paint.Cap.ROUND;
            } else {
                cap = Paint.Cap.SQUARE;
            }
            paint.setStrokeCap(cap);
            float[] startPoints = mStartPoint;
            float startPointX = startPoints[0];
            float startPointY = startPoints[1];
            startPoints = mValuePoint;
            LinearGradient linearGradient = new LinearGradient(startPointX, startPointY, startPoints[0], startPoints[1], mPrimaryColor, null, Shader.TileMode.CLAMP);
            mPrimaryPaint.setShader(linearGradient);
        }
        mPrimaryColors = mSecondaryColor;
        if (mPrimaryColors != null && mPrimaryColors.length > 0) {
            Paint.Cap cap;
            mSecondaryPaint = new Paint();
            mSecondaryPaint.setAntiAlias(true);
            mSecondaryPaint.setStyle(Paint.Style.STROKE);
            mSecondaryPaint.setStrokeWidth(mLineWidth);
            Paint paint = mSecondaryPaint;
            if (TextUtils.equals(mLineCap, "round")) {
                cap = Paint.Cap.ROUND;
            } else {
                cap = Paint.Cap.SQUARE;
            }
            paint.setStrokeCap(cap);
            float[] startPoints = mStartPoint;
            float startPointX = startPoints[0];
            float startPointY = startPoints[1];
            startPoints = mEndPoint;
            LinearGradient linearGradient = new LinearGradient(startPointX, startPointY, startPoints[0], startPoints[1], mSecondaryColor, null, Shader.TileMode.CLAMP);
            mSecondaryPaint.setShader(linearGradient);
        }
    }

    public void setWidgetRect(RectF rect) {
        super.setWidgetRect(rect);
        if (rect == null)
            return;
        if (mStartPointRelative != null) {
            if (mEndPointRelative == null){
                return;
            }
            mStartPoint = new float[] { rect.left + mStartPointRelative[0], rect.top + mStartPointRelative[1] };
            mEndPoint = new float[] { rect.left + mEndPointRelative[0], rect.top + mEndPointRelative[1] };
            return;
        }
    }

    @Override
    public void isAmbientModeChanged(boolean ambientMode) {
    }
}
