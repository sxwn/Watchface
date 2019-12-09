package com.goertek.commonlib.custom.common;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.goertek.commonlib.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义页面页签
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/20
 */
public class PageIndicator {
    private static final int DOT_MARGIN = 3;
    private static final int DOT_SIZE = 10;
    private static final String TAG = "PageIndicator";
    private Context mContext;
    private List<ImageView> mDotList;
    private LinearLayout mLayout;

    public PageIndicator(Context context, LinearLayout linearLayout) {
        mContext = context;
        mLayout = linearLayout;
        mDotList = new ArrayList(0);
    }

    private void removeDotCount(int dotIndex, int dotCount) {
        if (dotIndex <= dotCount) {
            if (mDotList.size() >= dotIndex) {
                int dotSize = dotCount;
                if (dotCount > mDotList.size()) {
                    dotSize = mDotList.size();
                }
                ArrayList<ImageView> imageViews = new ArrayList(0);

                while (dotIndex < dotSize) {
                    ImageView imageView = mDotList.get(dotIndex);
                    imageViews.add(imageView);
                    mLayout.removeView(imageView);
                    dotIndex++;
                }
                mDotList.removeAll(imageViews);
            }
        }
    }

    public void setDotCount(int dotCount) {
        if (dotCount > 0) {
            for (int dot = 0; dot < dotCount; ++dot) {
                ImageView imageView = new ImageView(mContext);
                if (dot == 0) {
                    imageView.setBackgroundResource(R.drawable.dot_select);
                } else {
                    imageView.setBackgroundResource(R.drawable.dot_unselect);
                }

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(-2, -2));
                layoutParams.leftMargin = 3;
                layoutParams.rightMargin = 3;
                layoutParams.height = 10;
                layoutParams.width = 10;
                mLayout.addView(imageView, layoutParams);
                mDotList.add(imageView);
            }
        }
    }

    public void setSelectedPosition(int selectedPosition) {
        if (selectedPosition >= 0) {
            if (selectedPosition < mDotList.size()) {
                for (int dotSelect = 0; dotSelect < mDotList.size(); ++dotSelect) {
                    if (selectedPosition == dotSelect) {
                        mDotList.get(dotSelect).setBackgroundResource(R.drawable.dot_select);
                    } else {
                        mDotList.get(dotSelect).setBackgroundResource(R.drawable.dot_unselect);
                    }
                }
            }
        }
    }

    public void updateDotCount(int dotIndex) {
        int dotListSize = mDotList.size();
        if (dotListSize > dotIndex) {
            removeDotCount(dotIndex, dotListSize);
        } else {
            setDotCount(dotIndex - dotListSize);
        }
        setSelectedPosition(0);
    }
}