package com.goertek.commonlib.custom.fragment;

import androidx.fragment.app.Fragment;

import com.goertek.commonlib.custom.BaseCustomFragment;
import com.goertek.commonlib.view.unit.UnitConstants;

/**
 * 背景Fragment
 *
 * @author ww
 * @version 1.0.0
 * @since 2019/07/19
 */
public class BackgroundFragment extends BaseCustomFragment {
    private static final String TAG = "BackgroundFragment";

    public BackgroundFragment() {
    }

    @Override
    public String getLabel() {
        return UnitConstants.LABEL_BACKGROUND;
    }

    @Override
    protected int getSortNum() {
        return UnitConstants.LABEL_BACKGROUND_SORT_NUM;
    }
}
