package com.goertek.commonlib.custom.fragment;

import com.goertek.commonlib.custom.BaseCustomFragment;
import com.goertek.commonlib.view.unit.UnitConstants;

/**
 * 样式Fragment
 *
 * @author ww
 * @version 1.0.0
 * @since 2019/07/19
 */
public class StylesFragment extends BaseCustomFragment {
    private static final String TAG = "StylesFragment";

    public StylesFragment() {
    }

    @Override
    public String getLabel() {
        return UnitConstants.LABEL_STYLES;
    }

    @Override
    protected int getSortNum() {
        return UnitConstants.LABEL_STYLES_SORT_NUM;
    }
}
