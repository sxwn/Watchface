package com.goertek.commonlib.custom.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.goertek.commonlib.custom.BaseCustomFragment;
import com.goertek.commonlib.eventbus.ModuleBus;
import com.goertek.commonlib.eventbus.Sub;
import com.goertek.commonlib.eventbus.event.WatchFaceModuleChangeEvent;
import com.goertek.commonlib.view.unit.UnitConstants;

/**
 * 自定义控件 Fragment
 *
 * @author ww
 * @version 1.0.0
 * @since 2019/07/19
 */
public class WidgetFragment extends BaseCustomFragment {
    private static final String TAG = "WidgetFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ModuleBus.register(this);
    }

    public WidgetFragment() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ModuleBus.unregister(this);
    }

    @Override
    public String getLabel() {
        return UnitConstants.LABEL_WIDGET;
    }

    @Override
    public int getSortNum() {
        return UnitConstants.LABEL_WIDGET_SORT_NUM;
    }

   @Sub
   public void onWidgetChangeEvent(WatchFaceModuleChangeEvent var1) {
       this.notifyDataChanged();
   }

}
