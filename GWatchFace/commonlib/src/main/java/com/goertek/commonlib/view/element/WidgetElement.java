package com.goertek.commonlib.view.element;

import android.graphics.Canvas;
import android.os.Build;
import android.text.TextUtils;

import androidx.annotation.RequiresApi;

import com.goertek.commonlib.provider.data.manager.DataAdapter;
import com.goertek.commonlib.provider.manager.AssetPackage;
import com.goertek.commonlib.provider.model.Container;
import com.goertek.commonlib.provider.model.Element;
import com.goertek.commonlib.provider.model.Layer;
import com.goertek.commonlib.provider.model.Option;
import com.goertek.commonlib.provider.model.Style;
import com.goertek.commonlib.provider.model.Styles;
import com.goertek.commonlib.utils.AttributeUtils;
import com.goertek.commonlib.utils.UIDrawUnitFactory;
import com.goertek.commonlib.utils.WatchFaceUtil;
import com.goertek.commonlib.view.unit.UnitConstants;
import com.goertek.commonlib.view.unit.base.IDrawUnit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class WidgetElement extends BaseElement {
    private List<String> mSelectedContainerIndexs;

    /**
     * 构造方法
     *
     * @param assetPackage 资源包对象
     */
    public WidgetElement(AssetPackage assetPackage) {
        super(UnitConstants.LABEL_WIDGET, assetPackage);
    }

    @Override
    public void addUnit() {
        Element element = getElement();
        if (element== null) {
            return;
        }

        Layer linearLayer = null;
        List<Layer> layerList = element.getLayers();
        if ((layerList != null) && (layerList.size()) > 0) {
            for (Layer layer : layerList) {
                String selectedContainer = layer.getArcLinearSelectedContainer();
                if (!TextUtils.isEmpty(selectedContainer)) {
                    linearLayer = layer;
                    mSelectedContainerIndexs = WatchFaceUtil.getStringValues(selectedContainer);
                    break;
                }
            }
        }

        if (mSelectedContainerIndexs == null) {
            mSelectedContainerIndexs = new ArrayList<>(0);
        }

        List<Container> containers = element.getContainers();
        if ((containers == null) || (containers.size() <= 0)) {
            return;
        }
        addContainerUnit(element, linearLayer, containers);
    }

    private void addContainerUnit(Element element, Layer linearLayer, List<Container> containers) {
        for (Container container : containers) {
            if (container == null) {
                continue;
            }

            String index = container.getIndex();

            if ((mSelectedContainerIndexs.contains(index)) && (linearLayer != null)) {
                IDrawUnit unit = UIDrawUnitFactory.createDrawUnit(getAssetPackage(), linearLayer);
                getDrawUnits().add(unit);
            } else {
                if (TextUtils.equals(container.getIsAvailable(), "false")) {
                    continue;
                }
                if (WatchFaceUtil.getBoolValue(container.getIsSupportOption())) {
                    Option option = element.getOption(container.getSelectedOption());
                    if (option == null) {
                        continue;
                    }
                    List<Layer> layers = option.getLayers();
                    for (Layer layer : layers) {
                        IDrawUnit unit = UIDrawUnitFactory.createDrawUnit(getAssetPackage(), layer);
                        // 需要根据控件位置和图层相对坐标重新计算图层的绝对坐标
                        unit.setWidgetRect(WatchFaceUtil.getRectF(container.getRect()));
                        getDrawUnits().add(unit);
                    }
                } else {
                    List<Layer> layers = container.getLayers();
                    if ((layers != null) && (layers.size() != 0)) {
                        for (Layer layer : layers) {
                            IDrawUnit unit = UIDrawUnitFactory.createDrawUnit(getAssetPackage(), layer);
                            // 需要根据控件位置和图层相对坐标重新计算图层的绝对坐标
                            unit.setWidgetRect(WatchFaceUtil.getRectF(container.getRect()));
                            getDrawUnits().add(unit);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onClick(int x, int y) {
        Element element = getElement();
        if (element == null) {
            return ;
        }

        List<String> indexList = obtainContainerIndexWithStyles();
        List<Container> containers = element.getContainers();
        if ((containers == null) || (containers.size() <= 0)) {
            return ;
        }

        boolean isContain;
        for (Container container : containers) {
            if (container == null) {
                continue;
            }

            String currIndex = container.getIndex();

            if ((Objects.isNull(indexList)) || (indexList.size() == 0)) {
                isContain = true;
            } else {
                isContain = indexList.contains(currIndex);
            }

            if (WatchFaceUtil.isInBound(x, y, WatchFaceUtil.getRect(container.getRect())) && isContain) {
                if (WatchFaceUtil.getBoolValue(container.getIsSupportOption())) {
                    Option option = element.getOption(container.getSelectedOption());
                    if (option != null) {
                        DataAdapter.getInstance().doClick(option.getDataType());
                        return ;
                    }
                    break;
                } else {
                    DataAdapter.getInstance().doClick(container.getDataType());
                }
            }
        }
    }

    private List<String> obtainContainerIndexWithStyles() {
        String currStyleIndex = null;
        List<String> results = null;
        Styles styles = getStyles();
        if (styles != null) {
            currStyleIndex = styles.getSelectedStyle();
        }
        if (!TextUtils.isEmpty(currStyleIndex)) {
            Style currStyle = styles.getStyle(currStyleIndex);
            if (currStyle != null) {
                String availableContainer = currStyle.getWidgetAvailableContainers();
                results = WatchFaceUtil.getStringValues(availableContainer);
            }
        }
        return results;
    }

    @Override
    public void onAmbientModeChanged(boolean inAmbientMode) {
        super.onAmbientModeChanged(inAmbientMode);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }
}
