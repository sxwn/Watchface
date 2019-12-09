package com.goertek.commonlib.custom.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

import com.goertek.commonlib.R;

/**
 * 随机背景背景
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/20
 */
public class RandomBackgroundView extends View {
    private static final int DIVISOR = 2;

    private static final int TEXT_SIZE = 60;

    private Bitmap mBgBitmap;

    private RectF mBound;

    private Paint mPaint = new Paint();

    private String mRandomString;

    public RandomBackgroundView(Context context, Rect rect, int resId) {
        super(context);
        mRandomString = context.getString(resId);
        mBound = new RectF(rect.left, rect.top, rect.right, rect.bottom);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(60.0F);
        mPaint.setColor(-1);
        mBgBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.random_bg);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(this.mBgBitmap, null, mBound, null);
        float positionX = (this.mBound.right - mBound.left - mPaint.measureText(mRandomString)) / 2.0F;
        float positionY = (this.mBound.bottom - mBound.top + 60.0F) / 2.0F;
        canvas.drawText(mRandomString, positionX, positionY, mPaint);
    }
}
