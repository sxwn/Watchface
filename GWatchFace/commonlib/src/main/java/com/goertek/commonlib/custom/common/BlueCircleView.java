package com.goertek.commonlib.custom.common;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.goertek.commonlib.utils.LogUtil;

/**
 * 蓝色背景圈
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/20
 */
public class BlueCircleView extends View {
    private static final String TAG = "BlueCircleView";

    private static final int STROKE_WIDTH = 6;

    private RectF mBound;

    private Paint mPaint = new Paint();

    public BlueCircleView(Context context, Rect rect) {
        super(context);
        if (rect == null)
            LogUtil.e("BlueCircleView", "BlueCircleView() error");
        this.mBound = new RectF(rect.left, rect.top, rect.right, rect.bottom);
    }

    public BlueCircleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(6.0F);
        canvas.drawOval(mBound, mPaint);

    }

}
