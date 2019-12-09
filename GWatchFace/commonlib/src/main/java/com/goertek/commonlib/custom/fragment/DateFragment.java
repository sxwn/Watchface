package com.goertek.commonlib.custom.fragment;

import com.goertek.commonlib.custom.BaseCustomFragment;
import com.goertek.commonlib.view.unit.UnitConstants;

/**
 * 日期Fragment
 *
 * @author ww
 * @version 1.0.0
 * @since 2019/07/19
 */
public class DateFragment extends BaseCustomFragment {

    public DateFragment() {
    }

    @Override
    public String getLabel() {
        return UnitConstants.LABEL_DATE;
    }

    @Override
    protected int getSortNum() {
        return UnitConstants.LABEL_DATE_SORT_NUM;
    }
}
