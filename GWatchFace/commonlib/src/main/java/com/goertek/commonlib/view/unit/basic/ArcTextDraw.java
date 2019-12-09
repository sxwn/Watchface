package com.goertek.commonlib.view.unit.basic;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextUtils;

import com.goertek.commonlib.provider.data.manager.DataAdapter;
import com.goertek.commonlib.provider.manager.AssetPackage;
import com.goertek.commonlib.provider.model.Layer;
import com.goertek.commonlib.utils.LogUtil;
import com.goertek.commonlib.utils.WatchFaceUtil;

/**
 * 弧形文本绘制类
 *
 * @author ww
 * @version 1.0.0
 * @since 2019/07/08
 */
public class ArcTextDraw extends TextDraw {
    private static final String TAG = "ArcTextDraw";

    private Paint mPaint;

    private RectF mRect;

    private Typeface mTextFont;

    private float mTextSize;

    private boolean mTextIsBold;

    private int mTextActiveColor;

    private int mTextAmbientColor;

    private String mDefaultText;

    private String mText;

    private String mWordCapitalType;

    private String mWordIsAbbr;

    private String mWordSupportCN;

    private String mValueType;

    private float mStartAngle;

    private float mSweepAngle;

    /**
     * 初始化弧形文本绘制资源
     *
     * @param assetPackage 资源包
     * @param layer 图层
     */
    public ArcTextDraw(AssetPackage assetPackage, Layer layer) {
        super(assetPackage, layer);
        mDefaultText = WatchFaceUtil.getStringValue(layer.getTextContent());
        mTextActiveColor = WatchFaceUtil.getColor(layer.getTextActiveColor());
        mTextAmbientColor = WatchFaceUtil.getColor(layer.getTextAmbientColor());
        mTextFont = assetPackage.getTypeface(layer.getTextFont());
        mTextSize = WatchFaceUtil.getFloatValue(layer.getTextSize());
        mTextIsBold = WatchFaceUtil.getBoolValue(layer.getTextIsBold());
        mWordSupportCN = WatchFaceUtil.getStringValue(layer.getWordSupportCN());
        mWordIsAbbr = WatchFaceUtil.getStringValue(layer.getWordIsAbbr());
        mWordCapitalType = WatchFaceUtil.getStringValue(layer.getWordCapitalType());
        mValueType = WatchFaceUtil.getStringValue(layer.getValueType());
        mRect = WatchFaceUtil.getRectF(layer.getRect());
        mStartAngle = WatchFaceUtil.getFloatValue(layer.getStartAngle());
    }

    private void updatePaint(boolean isAmbientMode) {
        int textColor;
        if (mPaint == null){
            mPaint = new Paint();
        }
        mPaint.setTypeface(mTextFont);
        mPaint.setTextSize(mTextSize);
        mPaint.setFakeBoldText(mTextIsBold);
        mPaint.setAntiAlias(true);
        Paint paint = mPaint;
        if (isAmbientMode) {
            textColor = mTextAmbientColor;
        } else {
            textColor = mTextActiveColor;
        }
        paint.setColor(textColor);
    }

    private void updateText() {
        if (!TextUtils.isEmpty(mDefaultText)) {
            mText = mDefaultText;
            return;
        }
        if (TextUtils.isEmpty(mWordIsAbbr) || TextUtils.isEmpty(mWordCapitalType)) {
            mText = DataAdapter.getInstance().getStringValue(mValueType);
            return;
        }
        String wordSupportCN = mWordSupportCN;
        String wordIsAbbr = mWordIsAbbr;
        String wordCapitalType = mWordCapitalType;
        mText = DataAdapter.getInstance().getStringValue(mValueType, new String[]{wordSupportCN, wordIsAbbr, wordCapitalType});
    }

    @Override
    public void preDraw(Canvas canvas, boolean isAmbientMode) {
        updatePaint(isAmbientMode);
        updateText();
    }

    @Override
    protected void onDraw(Canvas canvas, boolean isAmbientMode) {
        if (mText == null) {
            LogUtil.e("TextDraw", "mText is null !!");
            return;
        }
        Path path = new Path();
        path.addArc(mRect, mStartAngle, mSweepAngle);
        canvas.drawTextOnPath(mText, path, 0, 0, mPaint);
    }
}
