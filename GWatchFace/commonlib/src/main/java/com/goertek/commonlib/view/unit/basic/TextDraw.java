package com.goertek.commonlib.view.unit.basic;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.nfc.Tag;
import android.text.TextUtils;
import android.util.Log;

import com.goertek.commonlib.provider.data.manager.DataAdapter;
import com.goertek.commonlib.provider.manager.AssetPackage;
import com.goertek.commonlib.provider.model.Layer;
import com.goertek.commonlib.utils.LogUtil;
import com.goertek.commonlib.utils.WatchFaceUtil;
import com.goertek.commonlib.view.unit.base.BaseDraw;

/**
 * 文本绘制类，支持位置变换效果
 *
 * @author ww
 * @version 1.0.0
 * @since 2019/07/08
 */
public class TextDraw extends BaseDraw {
    private static final String TAG = "TextDraw";

    private Paint mPaint;

    private Typeface mTextFont;

    private float mTextSize;

    private boolean mTextIsBold;

    private boolean mIsSupportTextShadow;

    private float[] mTextShadowPosition;

    private float[] mTextPositionRelative;

    private float mTextShadowRadius;

    private int mTextAmbientColor;

    private int mTextActiveColor;

    private int mTextShadowColor;

    private String mText;

    private String mTextAlign;

    private float mStartX;

    private float mStartY;

    private float[] mTextPosition;

    private float mTextWidth;

    private String mDefaultText;

    private String mWordIsAbbr;

    private String mWordSupportCN;

    private String mWordCapitalType;

    private String mValueType;

    /**
     * 初始化文字绘制资源
     *
     * @param assetPackage 资源包
     * @param layer        图层
     */
    public TextDraw(AssetPackage assetPackage, Layer layer) {
        super(assetPackage, layer);
        LogUtil.e(TAG, "layer ========" + layer);
        mDefaultText = WatchFaceUtil.getStringValue(layer.getTextContent());
        LogUtil.e(TAG, "mDefaultText =====" + mDefaultText);
        mTextActiveColor = WatchFaceUtil.getColor(layer.getTextActiveColor());
        mTextAmbientColor = WatchFaceUtil.getColor(layer.getTextAmbientColor());
        mTextFont = assetPackage.getTypeface(layer.getTextFont());
        mTextSize = WatchFaceUtil.getFloatValue(layer.getTextSize());
        mTextAlign = WatchFaceUtil.getStringValue(layer.getTextAlign());
        mTextPosition = WatchFaceUtil.getPoint(layer.getTextPosition());
        mTextPositionRelative = WatchFaceUtil.getPoint(layer.getTextPositionRelative());
        mTextIsBold = WatchFaceUtil.getBoolValue(layer.getTextIsBold());
        mWordSupportCN = WatchFaceUtil.getStringValue(layer.getWordSupportCN());
        mWordIsAbbr = WatchFaceUtil.getStringValue(layer.getWordIsAbbr());
        mWordCapitalType = WatchFaceUtil.getStringValue(layer.getWordCapitalType());
        mValueType = WatchFaceUtil.getStringValue(layer.getValueType());
        mTextShadowPosition = WatchFaceUtil.getPoint(layer.getTextShadowPosition());
        mTextShadowColor = WatchFaceUtil.getColor(layer.getTextShadowColor());
        mTextShadowRadius = WatchFaceUtil.getFloatValue(layer.getTextShadowRadius());
        mIsSupportTextShadow = WatchFaceUtil.getBoolValue(layer.getIsSupportTextShadow());
    }

    @Override
    public void preDraw(Canvas canvas, boolean isAmbientMode) {
        updatePaint(isAmbientMode);
        updateText();
    }

    @Override
    protected void onDraw(Canvas canvas, boolean isAmbientMode) {
        updatePosition();
        String str = mText;
        if (str != null && !isAmbientMode) {
            canvas.drawText(str, mStartX, mStartY, mPaint);
            LogUtil.e(TAG, "onDraw==str==" + str + "mStartX ==" + mStartX + "mStartY ====" + mStartY + "mPaint ===" + mPaint);
            LogUtil.e(TAG, "mStartX=====" + mStartX + "mStartY===" + mStartY + "mPaint====" + mPaint);
        }
    }

    private void updatePaint(boolean isAmbientMode) {
        int textColor;
        if (mPaint == null) {
            mPaint = new Paint();
            mPaint.setTypeface(mTextFont);
            mPaint.setTextSize(mTextSize);
            mPaint.setFakeBoldText(mTextIsBold);
            mPaint.setAntiAlias(true);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("mIsSupportTextShadow==");
            stringBuilder.append(mIsSupportTextShadow);
            if (mIsSupportTextShadow) {
                float[] textShadowPosition = mTextShadowPosition;
                if (textShadowPosition != null)
                    mPaint.setShadowLayer(mTextShadowRadius, textShadowPosition[0], textShadowPosition[1], mTextShadowColor);
            }
            Paint paint = mPaint;
            if (isAmbientMode) {
                textColor = mTextAmbientColor;
            } else {
                textColor = mTextActiveColor;
            }
            paint.setColor(textColor);
        }
    }

    private void updatePosition() {
        LogUtil.e(TAG, "updatePosition==== mText====" + mText);
        if (TextUtils.isEmpty(mText)) {
            return;
        }
        float textWidth = mPaint.measureText(mText);

        LogUtil.e(TAG, "textWidth=====" + textWidth);
        LogUtil.e(TAG, "mTextPosition ====" + mTextPosition.length + " 0====" + mTextPosition[0] + "1111 =====" + mTextPosition[1]);
        if (TextUtils.equals(mTextAlign, "right")) {
            mStartX = mTextPosition[0] - textWidth;
        } else if (TextUtils.equals(mTextAlign, "center")) {
            mStartX = mTextPosition[0] - 0.5F * textWidth;
        } else {
            mStartX = mTextPosition[0];
        }
        mStartY = mTextPosition[1];
        mTextWidth = textWidth;
    }

    private void updateText() {
        Log.e(TAG, "mDefaultText=" + mDefaultText + ";mValueType==" + mValueType + ";mWordIsAbbr==" + mWordIsAbbr + ";mWordCapitalType===" + mWordCapitalType);
        if (!TextUtils.isEmpty(mDefaultText)) {
            mText = mDefaultText;
            return;
        }
        if (TextUtils.isEmpty(mWordIsAbbr) || TextUtils.isEmpty(mWordCapitalType)) {
            mText = DataAdapter.getInstance().getStringValue(mValueType);
            return;
        }
        String mWordSupportCN = this.mWordSupportCN;
        String mWordIsAbbr = this.mWordIsAbbr;
        String mWordCapitalType = this.mWordCapitalType;
        mText = DataAdapter.getInstance().getStringValue(mValueType, new String[]{mWordSupportCN, mWordIsAbbr, mWordCapitalType});
    }

    public String getDrawUnitAlign() {
        return mTextAlign;
    }


    public float[] getDrawUnitEndPosition() {
        return new float[]{mTextWidth, mStartX};
    }

    public void isAmbientModeChanged(boolean paramBoolean) {
    }

    protected void onDestroy() {
    }

    public void setTextActiveColor(int paramInt) {
        mTextActiveColor = paramInt;
    }

    @Override
    public void setWidgetRect(RectF rect) {
        super.setWidgetRect(rect);
        if (rect != null) {
            if (this.mTextPositionRelative != null) {
                this.mTextPosition = new float[]{rect.left + this.mTextPositionRelative[0], rect.top + this.mTextPositionRelative[1]};
            }
        }
    }
}
