package com.goertek.commonlib.custom.widget;

import android.view.View;

import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WatchFacePagerSnapHelper extends PagerSnapHelper {

    private static final int DISTANCE_NUM = 2;
    private static final int DIVIDE_FACTOR = 2;
    private OrientationHelper mHorizontalHelper;
    private OrientationHelper mVerticalHelper;

    public int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutManager, View view) {
        int[] iArr = new int[2];
        if (layoutManager.canScrollHorizontally()) {
            iArr[0] = distanceToCenter(layoutManager, view, getHorizontalHelper(layoutManager));
        } else {
            iArr[0] = 0;
        }
        if (layoutManager.canScrollVertically()) {
            iArr[1] = distanceToCenter(layoutManager, view, getVerticalHelper(layoutManager));
        } else {
            iArr[1] = 0;
        }
        return iArr;
    }

    private int distanceToCenter(RecyclerView.LayoutManager layoutManager, View view, OrientationHelper orientationHelper) {
        int center;
        int decoratedStart = orientationHelper.getDecoratedStart(view) + (orientationHelper.getDecoratedMeasurement(view) / 2);
        if (layoutManager.getClipToPadding()) {
            center = orientationHelper.getStartAfterPadding() + (orientationHelper.getTotalSpace() / 2);
        } else {
            center = orientationHelper.getEnd() / 2;
        }
        return decoratedStart - center;
    }

    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager.canScrollVertically()) {
            return findCenterView(layoutManager, getVerticalHelper(layoutManager));
        }
        if (layoutManager.canScrollHorizontally()) {
            return findCenterView(layoutManager, getHorizontalHelper(layoutManager));
        }
        return null;
    }

    private List<View> removeLastDistanceChildView(RecyclerView.LayoutManager layoutManager, OrientationHelper orientationHelper) {
        int center;
        int childCount = layoutManager.getChildCount();
        ArrayList<View> removeLastViews = new ArrayList<>(childCount);
        ArrayList<RecyclerViewBean> viewBeans = new ArrayList<>(childCount);
        if (layoutManager.getClipToPadding()) {
            center = orientationHelper.getStartAfterPadding() + (orientationHelper.getTotalSpace() / 2);
        } else {
            center = orientationHelper.getEnd() / 2;
        }
        for (int i = 0; i < childCount; i++) {
            View childView = layoutManager.getChildAt(i);
            int distance = Math.abs((orientationHelper.getDecoratedStart(childView) + (orientationHelper.getDecoratedMeasurement(childView) / 2)) - center);
            viewBeans.add(new RecyclerViewBean(childView, distance));
        }
        Collections.sort(viewBeans);
        int startIndex = 1;
        if (viewBeans.size() <= 1) {
            startIndex = 0;
        }
        for (int j = startIndex; j < viewBeans.size(); j++) {
            RecyclerViewBean bean = viewBeans.get(j);
            removeLastViews.add(bean.getView());
        }
        return removeLastViews;
    }

    private View findCenterView(RecyclerView.LayoutManager layoutManager, OrientationHelper orientationHelper) {
        int center;
        View view = null;
        if (layoutManager.getChildCount() == 0) {
            return null;
        }
        if (layoutManager.getClipToPadding()) {
            center = orientationHelper.getStartAfterPadding() + (orientationHelper.getTotalSpace() / 2);
        } else {
            center = orientationHelper.getEnd() / 2;
        }
        int absClosest = Integer.MAX_VALUE;
        List<View> removeLastDistanceChildView = removeLastDistanceChildView(layoutManager, orientationHelper);
        if (removeLastDistanceChildView != null) {
            for (View childView : removeLastDistanceChildView) {
                int abs = Math.abs((orientationHelper.getDecoratedStart(childView) +
                        (orientationHelper.getDecoratedMeasurement(childView) / 2)) - center);
                if (abs < absClosest) {
                    view = childView;
                    absClosest = abs;
                }
            }
        }
        return view;
    }

    private OrientationHelper getVerticalHelper(RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper = mVerticalHelper;
        if (orientationHelper == null || orientationHelper.getLayoutManager() != layoutManager) {
            mVerticalHelper = OrientationHelper.createVerticalHelper(layoutManager);
        }
        return mVerticalHelper;
    }

    private OrientationHelper getHorizontalHelper(RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper = mHorizontalHelper;
        if (orientationHelper == null || orientationHelper.getLayoutManager() != layoutManager) {
            mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return mHorizontalHelper;
    }

}
