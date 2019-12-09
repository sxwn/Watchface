package com.goertek.commonlib.custom;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.goertek.commonlib.R;
import com.goertek.commonlib.custom.adapter.WidgetOptionListAdapter;
import com.goertek.commonlib.custom.widget.WatchFaceLinearLayoutManager;
import com.goertek.commonlib.custom.widget.WatchFaceScrollingLayoutCallback;
import com.goertek.commonlib.custom.widget.WidgetOptionBean;
import com.goertek.commonlib.eventbus.ModuleBus;
import com.goertek.commonlib.eventbus.event.WatchFaceModuleChangeEvent;
import com.goertek.commonlib.provider.data.util.DataConstantUtils;
import com.goertek.commonlib.provider.manager.AssetPackage;
import com.goertek.commonlib.provider.manager.AssetPackageHolder;
import com.goertek.commonlib.provider.model.Container;
import com.goertek.commonlib.provider.model.Element;
import com.goertek.commonlib.provider.model.Option;
import com.goertek.commonlib.utils.CommonConstantsUtil;
import com.goertek.commonlib.utils.CustomizeUtil;
import com.goertek.commonlib.utils.WatchFaceUtil;
import com.goertek.commonlib.view.unit.UnitConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能选择列表
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/23
 */
public class BaseWidgetOptionListActivity extends Activity implements WidgetOptionListAdapter.OnItemClickListener {
    private static final String TAG = "BaseWidgetOptionListActivity";

    private AssetPackage mAssetPackage;

    private String containerIndex;

    private ArrayList<Option> options;

    private ArrayList<WidgetOptionBean> widgetOptionBeans;

    private ArrayList<String> types;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private WidgetOptionListAdapter widgetOptionListAdapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_option_list);
        Intent mIntent = getIntent();
        if ((mIntent == null)
                    || (TextUtils.isEmpty(mIntent.getStringExtra(CommonConstantsUtil.EXTRA_CONTAINER_INDEX)))) {
            return;
        }
        containerIndex = mIntent.getStringExtra(CommonConstantsUtil.EXTRA_CONTAINER_INDEX);
        mAssetPackage = AssetPackageHolder.getInstance().getAssetPackage();
        initData();
        WatchFaceLinearLayoutManager layoutManager = new WatchFaceLinearLayoutManager(this, new WatchFaceScrollingLayoutCallback(R.id.rl_widget_option_container));
        recyclerView = findViewById(R.id.rv_widget_option_selector);
        recyclerView.setLayoutManager(layoutManager);
        widgetOptionListAdapter = new WidgetOptionListAdapter(this, widgetOptionBeans, this);
        recyclerView.setAdapter(widgetOptionListAdapter);
        int size = widgetOptionBeans.size();
        // 此处需要使用下标索引的值
        for (int i = 0; i < size; i++) {
            if (widgetOptionBeans.get(i).isSelected()) {
                recyclerView.scrollToPosition(i);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initData() {
        getOptions();
        getTypes();
        if (widgetOptionBeans == null) {
            widgetOptionBeans = new ArrayList<>(0);
        }
        widgetOptionBeans.clear();
        WidgetOptionBean widgetOptionBeanEmpty = new WidgetOptionBean();
        widgetOptionBeanEmpty.setType(CommonConstantsUtil.RECYCLER_VIEW_TYPE_ITEM);
        widgetOptionBeanEmpty.setIcon(CustomizeUtil.getDataDrawableBySubType(this, ""));
        widgetOptionBeanEmpty.setContent(getResources().getString(R.string.module_none));
        widgetOptionBeanEmpty.setOptionIndex(String.valueOf(0));
        widgetOptionBeans.add(widgetOptionBeanEmpty);
        for (String type : types) {
            if (types.size() > 1) {
                WidgetOptionBean widgetOptionBeanTitle = new WidgetOptionBean();
                widgetOptionBeanTitle.setType(CommonConstantsUtil.RECYCLER_VIEW_TYPE_TITLE);
                widgetOptionBeanTitle.setContent(type);
                widgetOptionBeans.add(widgetOptionBeanTitle);
            }
            for (Option option : options) {
                String subType = option.getDataType().trim();
                if (TextUtils.equals(CustomizeUtil.getDataMainTypeBySubType(this, subType), type.trim())) {
                    WidgetOptionBean widgetOptionBeanItem = new WidgetOptionBean();
                    widgetOptionBeanItem.setType(CommonConstantsUtil.RECYCLER_VIEW_TYPE_ITEM);
                    widgetOptionBeanItem.setOptionIndex(option.getIndex());
                    widgetOptionBeanItem.setContent(CustomizeUtil.getDataNameBySubType(this, subType));
                    widgetOptionBeanItem.setIcon(CustomizeUtil.getDataDrawableBySubType(this, subType));
                    if (getClickContainer().getSelectedOption() != null
                                && TextUtils.equals(getClickContainer().getSelectedOption(), option.getIndex())) {
                        widgetOptionBeanItem.setSelected(true);
                        widgetOptionBeanItem.setIcon(CustomizeUtil.getChoiceDataDrawableBySubType(this, subType));
                    }
                    widgetOptionBeans.add(widgetOptionBeanItem);
                }
            }
        }
    }

    private void getOptions() {
        if (options == null) {
            options = new ArrayList<>(0);
        }
        options.clear();
        if (mAssetPackage == null) {
            return;
        }
        Element element = mAssetPackage.getElementProvider().getElementWithLabel(UnitConstants.LABEL_WIDGET);
        if (element == null) {
            return;
        }
        Container container = element.getContainer(containerIndex);
        if (container == null) {
            return;
        }
        String availableOptionsIndex = container.getAvailableOption();
        if (availableOptionsIndex != null) {
            for (String index : availableOptionsIndex.split(",")) {
                Option option = element.getOption(index);
                if (option == null) {
                    continue;
                }
                options.add(option);
            }
        }
    }

    private void getTypes() {
        if (types == null) {
            types = new ArrayList<>(0);
        }
        types.clear();
        for (Option option : options) {
            String subType = option.getDataType();
            String mainType = CustomizeUtil.getDataMainTypeBySubType(this, subType.trim());
            if (!types.contains(mainType)) {
                types.add(mainType);
            }
        }
    }

    private Container getClickContainer() {
        Container container =
                mAssetPackage.getElementProvider().getElementWithLabel(UnitConstants.LABEL_WIDGET).getContainer(
                        containerIndex);
        return container;
    }

    @Override
    public void onClick(int position) {
        // 更新xml配置
        boolean isHaveWidget = false;
        Container currContainer = getClickContainer();
        currContainer.setSelectedOption(widgetOptionBeans.get(position).getOptionIndex());
        String availableOption = currContainer.getAvailableOption();
        if (!TextUtils.isEmpty(availableOption)) {
            String optionIndex = currContainer.getSelectedOption();
            List<String> availableList = WatchFaceUtil.getStringValues(availableOption);
            isHaveWidget = TextUtils.isEmpty(optionIndex) ? Boolean.FALSE : availableList.contains(optionIndex);
        }
        ModuleBus.emit(new WatchFaceModuleChangeEvent(containerIndex, isHaveWidget, mAssetPackage.getPath()));

        // 当为世界时间时，跳转到世界时间设置界面。
        Element element = mAssetPackage.getElementProvider().getElementWithLabel(UnitConstants.LABEL_WIDGET);
        String indexOption = getClickContainer().getSelectedOption();
        Option option = element.getOption(indexOption);
        if ((option != null) && (TextUtils.equals(option.getDataType(),
                DataConstantUtils.DATA_TYPE_WORLD_TIME))) {
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(CommonConstantsUtil.ACTION_START_WORLD_TIME_SETTING);
            startActivity(intent);
        }
        finish();
    }
}
