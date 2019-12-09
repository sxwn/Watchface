package com.goertek.commonlib.custom.widget;

import android.view.View;

public class RecyclerViewBean implements Comparable<RecyclerViewBean>{

    private int mDistance;
    private View mView;

    public RecyclerViewBean(View paramView, int paramInt)
    {
        this.mView = paramView;
        this.mDistance = paramInt;
    }

    public int compareTo(RecyclerViewBean paramRecyclerViewBean)
    {
        if (mDistance > paramRecyclerViewBean.getDistance())
            return 1;
        if (mDistance < paramRecyclerViewBean.getDistance())
            return -1;
        return 0;
    }

    public int getDistance()
    {
        return mDistance;
    }

    public View getView()
    {
        return mView;
    }
}
