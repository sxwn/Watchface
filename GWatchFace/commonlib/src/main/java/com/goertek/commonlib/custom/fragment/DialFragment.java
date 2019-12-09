package com.goertek.commonlib.custom.fragment;

import com.goertek.commonlib.custom.BaseCustomFragment;
import com.goertek.commonlib.view.unit.UnitConstants;

/**
 * 数据提供者适配器
 *
 * @author ww
 * @version 1.0.0
 * @since 2019/07/10
 */
public class DialFragment extends BaseCustomFragment {
    private static final String TAG = "DialFragment";

    public DialFragment() {
    }

    @Override
    public String getLabel() {
        return UnitConstants.LABEL_DIAL;
    }

    @Override
    protected int getSortNum() {
        return UnitConstants.LABEL_DIAL_SORT_NUM;
    }
}
