package com.goertek.commonlib.custom.widget;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * 表盘自定义功能选项滑动回调
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/24
 */
public class WatchFaceScrollingLayoutCallback extends WatchFaceLinearLayoutManager.LayoutCallback {
    private static final String TAG = "WatchFaceScrollingLayout";

    private static final float BASE_ICON_PROGRESS = 0.5F;

    private static final float CENTER_FACTOR = 2.0F;

    private static final float EXPAND_LABEL_ALPHA = 1.0F;

    private static final float MAX_ICON_PROGRESS = 0.65F;

    private static final float SHRINK_LABEL_ALPHA = 0.5F;

    private final int mId;

    public WatchFaceScrollingLayoutCallback(int resId) {
        this.mId = resId;
    }

    public void onLayoutFinished(View view, RecyclerView recyclerView) {
        float height = (float) view.getHeight() / CENTER_FACTOR / (float) recyclerView.getMeasuredHeight();
        float minHeight = Math.min(Math.abs(BASE_ICON_PROGRESS - (view.getY() / (float) recyclerView.getMeasuredHeight() + height)), MAX_ICON_PROGRESS);
        view = view.findViewById(this.mId);
        if (view != null) {
            float scal = EXPAND_LABEL_ALPHA - minHeight;
            view.setScaleX(scal);
            view.setScaleY(scal);
        }

        if (view != null) {
            if (minHeight <= height) {
                view.setAlpha(EXPAND_LABEL_ALPHA);
                return;
            }
            view.setAlpha(SHRINK_LABEL_ALPHA);
        }
    }
}