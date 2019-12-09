package com.goertek.commonlib.custom.widget;

import android.content.Context;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.RecyclerView;

import com.goertek.commonlib.R;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/24
 */
public class CurvingLayoutCallback extends WatchFaceLinearLayoutManager.LayoutCallback {
    private static final String TAG = "CurvingLayoutCallback";

    private static final float EPSILON = 0.001F;

    private final float[] mAnchorOffsetXY = new float[2];

    private float mCurveBottom;

    private final Path mCurvePath = new Path();

    private int mCurvePathHeight;

    private float mCurveTop;

    private boolean mIsScreenRound;

    private int mLayoutHeight;

    private int mLayoutWidth;

    private float mLineGradient;

    private RecyclerView mParentView;

    private float mPathLength;

    private final PathMeasure mPathMeasure = new PathMeasure();

    private final float[] mPathPoints = new float[2];

    private final float[] mPathTangent = new float[2];

    private int mXCurveOffset;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public CurvingLayoutCallback(Context context) {
        mIsScreenRound = context.getResources().getConfiguration().isScreenRound();
        mXCurveOffset = context.getResources().getDimensionPixelSize(R.dimen.ws_wrv_curve_default_x_offset);
    }

    private void maybeSetUpCircularInitialLayout(int layoutWidth, int layoutHeight) {
        if (mCurvePathHeight != layoutHeight) {
            mCurvePathHeight = layoutHeight;
            float height = (float) layoutHeight;
            mCurveBottom = -0.048F * height;
            mCurveTop = 1.048F * height;
            mLineGradient = 10.416667F;
            mCurvePath.reset();
            Path curvePath = mCurvePath;
            float width = (float) layoutWidth;
            curvePath.moveTo(0.5F * width, this.mCurveBottom);
            curvePath = mCurvePath;
            float widthf = width * 0.34F;
            curvePath.lineTo(widthf, 0.075F * height);
            curvePath = mCurvePath;
            float widthPath = 0.22F * width;
            width = 0.13F * width;
            curvePath.cubicTo(widthPath, height * 0.17F, width, height * 0.32F, width, (float) (layoutHeight / 2));
            mCurvePath.cubicTo(width, height * 0.68F, widthPath, height * 0.83F, widthf, height * 0.925F);
            mCurvePath.lineTo((float) (layoutWidth / 2), mCurveTop);
            mPathMeasure.setPath(mCurvePath, false);
            mPathLength = mPathMeasure.getLength();
        }

    }

    public void onLayoutFinished(View view, RecyclerView recyclerView) {
        RecyclerView parentView = mParentView;
        if (parentView != recyclerView || parentView != null && (parentView.getWidth() != recyclerView.getWidth() || mParentView.getHeight() != recyclerView.getHeight())) {
            mParentView = recyclerView;
            mLayoutWidth = mParentView.getWidth();
            mLayoutHeight = mParentView.getHeight();
        }

        if (!mIsScreenRound) {
            view.setTranslationY(0.0F);
        } else {
            maybeSetUpCircularInitialLayout(mLayoutWidth, mLayoutHeight);
            float[] anchorOffsetXY = mAnchorOffsetXY;
            anchorOffsetXY[0] = (float) mXCurveOffset;
            anchorOffsetXY[1] = (float) view.getHeight() / 2.0F;
            float height = -((float) view.getHeight()) / 2.0F;
            float layoutHeight = (float) mLayoutHeight + (float) view.getHeight() / 2.0F;
            float topOffset = (float) view.getTop() + mAnchorOffsetXY[1];
            float heightOffset = (Math.abs(height) + topOffset) / (layoutHeight - height);
            mPathMeasure.getPosTan(heightOffset * mPathLength, mPathPoints, mPathTangent);
            boolean isPath;
            if (Math.abs(mPathPoints[1] - mCurveBottom) < 0.001F && height < mPathPoints[1]) {
                isPath = true;
            } else {
                isPath = false;
            }

            boolean heightPoints;
            if (Math.abs(this.mPathPoints[1] - mCurveTop) < 0.001F && layoutHeight > mPathPoints[1]) {
                heightPoints = true;
            } else {
                heightPoints = false;
            }

            if (isPath || heightPoints) {
                anchorOffsetXY = mPathPoints;
                anchorOffsetXY[1] = topOffset;
                anchorOffsetXY[0] = Math.abs(topOffset) * mLineGradient;
            }

            view.offsetLeftAndRight((int) (mPathPoints[0] - mAnchorOffsetXY[0]) - view.getLeft());
            view.setTranslationY(mPathPoints[1] - topOffset);
        }
    }
}
