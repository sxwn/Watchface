package com.goertek.commonlib.custom.widget;

import android.content.Context;
import android.os.Build;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 表盘功能选项列表LayoutManager
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/24
 */
public class WatchFaceLinearLayoutManager extends LinearLayoutManager {
    private static final String TAG = "WatchFaceLinearLayoutManager";

    private WatchFaceLinearLayoutManager.LayoutCallback mLayoutCallback;

    public WatchFaceLinearLayoutManager(Context context, WatchFaceLinearLayoutManager.LayoutCallback layoutCallback) {
        super(context, RecyclerView.VERTICAL, false);
        mLayoutCallback = layoutCallback;
    }

    private void updateLayout() {
        if (mLayoutCallback != null) {
            int childCount = this.getChildCount();
            for (int i = 0; i < childCount; ++i) {
                View view = getChildAt(i);
                mLayoutCallback.onLayoutFinished(view, (RecyclerView) view.getParent());
            }

        }
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        if (getChildCount() != 0) {
            updateLayout();
        }
    }

    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        dy = super.scrollVerticallyBy(dy, recycler, state);
        this.updateLayout();
        return dy;
    }

    public abstract static class LayoutCallback {
        public LayoutCallback() {
        }

        public abstract void onLayoutFinished(View view, RecyclerView recyclerView);
    }
}
