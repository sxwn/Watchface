package com.goertek.commonlib.custom.fragment;

import com.goertek.commonlib.custom.BaseCustomFragment;
import com.goertek.commonlib.view.unit.UnitConstants;

/**
 * 世间Fragment
 *
 * @author ww
 * @version 1.0.0
 * @since 2019/07/19
 */
public class TimeFragment extends BaseCustomFragment {
    private static final String TAG = "TimeFragment";

    public TimeFragment() {
    }

    @Override
    public String getLabel() {
        return UnitConstants.LABEL_TIME;
    }

    @Override
    protected int getSortNum() {
        return UnitConstants.LABEL_TIME_SORT_NUM;
    }
}
