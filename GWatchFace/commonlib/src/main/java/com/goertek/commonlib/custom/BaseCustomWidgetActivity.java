package com.goertek.commonlib.custom;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.goertek.commonlib.R;
import com.goertek.commonlib.custom.adapter.ViewpagerAdapter;
import com.goertek.commonlib.custom.common.PageIndicator;
import com.goertek.commonlib.custom.fragment.BackgroundFragment;
import com.goertek.commonlib.custom.fragment.DateFragment;
import com.goertek.commonlib.custom.fragment.DialFragment;
import com.goertek.commonlib.custom.fragment.StylesFragment;
import com.goertek.commonlib.custom.fragment.TimeFragment;
import com.goertek.commonlib.custom.fragment.WidgetFragment;
import com.goertek.commonlib.eventbus.ModuleBus;
import com.goertek.commonlib.eventbus.Sub;
import com.goertek.commonlib.eventbus.event.FragmentScrollEvent;
import com.goertek.commonlib.eventbus.event.WatchFaceEditCompleteEvent;
import com.goertek.commonlib.eventbus.event.WatchFaceModuleChangeEvent;
import com.goertek.commonlib.provider.manager.AssetPackage;
import com.goertek.commonlib.provider.manager.AssetPackageHolder;
import com.goertek.commonlib.provider.manager.ElementProvider;
import com.goertek.commonlib.provider.model.Element;
import com.goertek.commonlib.provider.model.Style;
import com.goertek.commonlib.provider.model.Styles;
import com.goertek.commonlib.utils.CommonConstantsUtil;
import com.goertek.commonlib.utils.LogUtil;
import com.goertek.commonlib.utils.WatchFaceUtil;
import com.goertek.commonlib.view.unit.UnitConstants;
import com.google.common.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

public abstract class BaseCustomWidgetActivity extends FragmentActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private static final String TAG = BaseCustomWidgetActivity.class.getSimpleName();
    private ViewPager mViewPager;
    private ViewpagerAdapter mVpAdapter;

    private List<BaseCustomFragment> mFragments = new ArrayList<>(0);

    private BackgroundFragment mBackgroundFragment;
    private DialFragment mDialFragment;
    private TimeFragment mTimeFragment;
    private WidgetFragment mWidgetFragment;
    private StylesFragment mStylesFragment;
    private DateFragment mDateFragment;
    private List<ArrayList<Boolean>> mAvailableValues;
    private AssetPackage mAssetPackage;
    private List<String> mAllElements;
    private PageIndicator mPageIndicator;
    private Button mButton;
    private FragmentScrollEventReceiver mFragmentScrollEvent;
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.i(TAG, "onReceive() intent is null!");
                return;
            }
            String action = intent.getAction();
            if ("android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(action)) {
                if (CommonConstantsUtil.ACTION_HOME_KEY.equals(intent.getStringExtra(CommonConstantsUtil.ACTION_HOME_KEY_REASON))) {
                    LogUtil.i(TAG, "onReceive() HOME_KEY!");
                    finish();
                }
            } else if ("android.intent.action.SCREEN_OFF".equals(action)) {
                LogUtil.i(TAG, "onReceive() SCREEN_OFF!");
                finish();
            } else {
                LogUtil.i(TAG, "onReceive() action:" + action);
            }
        }
    };

    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        registerReceiver(mBroadcastReceiver, intentFilter, "com.goertek.watchface.permission.CONTACT", null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_main);
        mFragmentScrollEvent = new FragmentScrollEventReceiver();
        ModuleBus.register(mFragmentScrollEvent);
        mViewPager = findViewById(R.id.vp_customize_page);
        mButton = findViewById(R.id.bt_submit);
        mPageIndicator = new PageIndicator(this, (LinearLayout) findViewById(R.id.dot_indicator));
        initFragments();
        mVpAdapter = new ViewpagerAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mVpAdapter);
        mViewPager.addOnPageChangeListener(this);
        mButton.setOnClickListener(this);
        initAllElementStings();
        initAvailableValues();
        initViewPagerIndicator();
        registerReceiver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBroadcastReceiver != null) {
            unregisterReceiver(mBroadcastReceiver);
        }
    }

    private void initViewPagerIndicator() {
        if (mFragments.size() > 1) {
            mPageIndicator.updateDotCount(mFragments.size());
        } else {
            mPageIndicator.setDotCount(0);
        }

    }

    protected abstract AssetPackage getAssetPackage();

    private void initFragments() {
        mAssetPackage = getAssetPackage();
        AssetPackageHolder.getInstance().setAssetPackage(mAssetPackage);
        ElementProvider elementProvider = mAssetPackage.getElementProvider();
        Styles styles = elementProvider.getStyles();
        if (styles != null && styles.getStyles() != null) {
            mStylesFragment = new StylesFragment();
            mFragments.add(mStylesFragment);
        }
        Element background = elementProvider.getElementWithLabel(UnitConstants.LABEL_BACKGROUND);
        if (background != null && isShowFragment(UnitConstants.LABEL_BACKGROUND, styles) && WatchFaceUtil.getBoolValue(background.getIsSupportOption())) {
            mBackgroundFragment = new BackgroundFragment();
            mFragments.add(mBackgroundFragment);
        }
        Element time = elementProvider.getElementWithLabel(UnitConstants.LABEL_TIME);
        if (time != null && isShowFragment(UnitConstants.LABEL_TIME, styles) && WatchFaceUtil.getBoolValue(time.getIsSupportOption())) {
            mTimeFragment = new TimeFragment();
            mFragments.add(mTimeFragment);
        }
        Element date = elementProvider.getElementWithLabel(UnitConstants.LABEL_DATE);
        if (date != null && isShowFragment(UnitConstants.LABEL_DATE, styles) && WatchFaceUtil.getBoolValue(date.getIsSupportOption())) {
            mDateFragment = new DateFragment();
            mFragments.add(mDateFragment);
        }
        Element dial = elementProvider.getElementWithLabel(UnitConstants.LABEL_DIAL);
        if (dial != null && isShowFragment(UnitConstants.LABEL_DIAL, styles) && WatchFaceUtil.getBoolValue(dial.getIsSupportOption())) {
            mDialFragment = new DialFragment();
            mFragments.add(mDialFragment);
        }
        Element widget = elementProvider.getElementWithLabel(UnitConstants.LABEL_WIDGET);
        if (widget != null && isShowFragment(UnitConstants.LABEL_WIDGET, styles) && WatchFaceUtil.getBoolValue(widget.getIsSupportOption())) {
            mWidgetFragment = new WidgetFragment();
            mFragments.add(mWidgetFragment);
        }

        LogUtil.e(TAG, " mFragments.size()" + mFragments.size());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_submit) {
            mAssetPackage.getElementProvider().saveProviders();
            ModuleBus.emit(new WatchFaceEditCompleteEvent(""));
            finish();
        }

    }

    public void updateFragment() {
        LogUtil.e(TAG, "updateFragment");
        if (mAssetPackage.getElementProvider().getStyles() != null && mViewPager.getCurrentItem() == 0) {
            removeNoUseFragment();
        }

        mViewPager.setAdapter(mVpAdapter);
        mVpAdapter.notifyDataSetChanged();
        if (mFragments.size() > 1) {
            mPageIndicator.updateDotCount(mFragments.size());
        } else {
            mPageIndicator.setDotCount(0);
        }
    }

    private void removeNoUseFragment() {
        int currentPosition = mFragments.get(0).getCurrentPosition();
        if (currentPosition <= mAvailableValues.size()) {
            int size = mAllElements.size();
            for (int i = 0; i < size; i++) {
                boolean isShowFragment = mAvailableValues.get(currentPosition).get(i);
                BaseCustomFragment findFragmentWithLabel = findFragmentWithLabel(mAllElements.get(i));
                if (!isShowFragment) {
                    if (findFragmentWithLabel != null) {
                        mFragments.remove(findFragmentWithLabel);
                    }
                } else if (findFragmentWithLabel == null) {
                    addFragmentToList(mAllElements.get(i));
                }
            }
        }

    }

    private void initAvailableValues() {
        Styles styles = mAssetPackage.getElementProvider().getStyles();
        if (styles != null) {
            List<Style> styleList = styles.getStyles();
            mAvailableValues = new ArrayList(styleList.size());
            for (Style style : styleList) {
                ArrayList<Boolean> list = new ArrayList(mAllElements.size());
                boolean isShow ;
                for(String stringElement : mAllElements ) {
                    String str = null;
                    switch(stringElement) {
                        case UnitConstants.LABEL_TIME:
                            str = style.getTimeAvailableOption();
                            break;
                        case UnitConstants.LABEL_DIAL:
                            str = style.getDialAvailableOption();
                            break;
                        case UnitConstants.LABEL_DATE:
                            str = style.getDateAvailableOption();
                            break;
                        case UnitConstants.LABEL_WIDGET:
                            str = style.getWidgetAvailableContainers();
                            break;
                        case UnitConstants.LABEL_BACKGROUND:
                            str = style.getBackgroundAvailableOption();
                            break;
                        default:
                            break;
                    }

                    if (TextUtils.equals(stringElement, UnitConstants.LABEL_WIDGET)) {
                        isShow = !TextUtils.isEmpty(str);
                    } else {
                        List<String> values = WatchFaceUtil.getStringValues(str);
                        isShow = !TextUtils.isEmpty(str) && values.size() > 1;
                    }
                    list.add(isShow);
                }

                mAvailableValues.add(list);
            }

        }
    }

    private void initAllElementStings() {
        mAllElements = new ArrayList(0);
        mAllElements.add(UnitConstants.LABEL_BACKGROUND);
        mAllElements.add(UnitConstants.LABEL_DIAL);
        mAllElements.add(UnitConstants.LABEL_DATE);
        mAllElements.add(UnitConstants.LABEL_TIME);
        mAllElements.add(UnitConstants.LABEL_WIDGET);
    }

    private BaseCustomFragment findFragmentWithLabel(String paramString) {
        for (BaseCustomFragment baseCustomizeFragment : mFragments) {
            if (TextUtils.equals(baseCustomizeFragment.getLabel(), paramString)) {
                return baseCustomizeFragment;
            }
        }
        return null;
    }


    private void addFragmentToList(String lable) {
        switch (lable) {
            case UnitConstants.LABEL_TIME:
                if (mTimeFragment == null) {
                    mTimeFragment = new TimeFragment();
                }
                mFragments.add(mTimeFragment);
                break;
            case UnitConstants.LABEL_DIAL:
                if (mDialFragment == null) {
                    mDialFragment = new DialFragment();
                }
                mFragments.add(mDialFragment);
                break;
            case UnitConstants.LABEL_DATE:
                if (mDateFragment == null) {
                    mDateFragment = new DateFragment();
                }
                mFragments.add(mDateFragment);
                break;
            case UnitConstants.LABEL_WIDGET:
                if (mWidgetFragment == null) {
                    mWidgetFragment = new WidgetFragment();
                }
                mFragments.add(mWidgetFragment);
                break;
            case UnitConstants.LABEL_BACKGROUND:
                if (mBackgroundFragment == null) {
                    mBackgroundFragment = new BackgroundFragment();
                }
                mFragments.add(mBackgroundFragment);
                break;
            default:
                break;
        }
        Collections.sort(mFragments);
    }

    @Override
    public void onPageSelected(int position) {
        mPageIndicator.setSelectedPosition(position);
    }

    private boolean isShowFragment(String lable, Styles styles) {

        boolean isShow = (styles != null);
        if (styles == null) {
            return true;
        }
        Style style = styles.getStyles().get(obtainCurrentStyle());


        switch (lable) {
            case UnitConstants.LABEL_BACKGROUND:
                lable = style.getBackgroundAvailableOption();
                break;
            case UnitConstants.LABEL_DATE:
                lable = style.getDateAvailableOption();
                break;
            case UnitConstants.LABEL_DIAL:
                lable = style.getDialAvailableOption();
                break;
            case UnitConstants.LABEL_TIME:
                lable = style.getTimeAvailableOption();
                break;
            default:
                lable = "";
                break;
        }

        if (!TextUtils.isEmpty(lable)) {
            isShow = false || lable.contains(",");
        }
        return isShow;
    }

    private int obtainCurrentStyle() {
        Styles styles = mAssetPackage.getElementProvider().getStyles();
        if (styles == null) {
            return -1;
        } else {
            List<Style> styleList = styles.getStyles();
            String selectedStyle = styles.getSelectedStyle();
            if (TextUtils.isEmpty(selectedStyle)) {
                return -1;
            } else {
                Style style = styles.getStyle(selectedStyle);
                return style == null ? -1 : styleList.indexOf(style);
            }
        }
    }

    private class FragmentScrollEventReceiver {

        private FragmentScrollEventReceiver() {
        }

        @Sub
        public void onFragmentScrollEvent(FragmentScrollEvent fragmentScrollEvent) {

            if (fragmentScrollEvent != null) {
                if (fragmentScrollEvent.getItemIndex() >= 0) {
                    updateFragment();
                }
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == 0) {

            for (BaseCustomFragment customFragment : mFragments) {
                customFragment.notifyDataChanged();
            }
        }

    }

    public void onPageScrolled(int var1, float var2, int var3) {
    }
}
