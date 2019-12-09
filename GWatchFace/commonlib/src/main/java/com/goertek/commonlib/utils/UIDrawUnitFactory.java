package com.goertek.commonlib.utils;

import com.goertek.commonlib.provider.manager.AssetPackage;
import com.goertek.commonlib.provider.model.Layer;
import com.goertek.commonlib.view.unit.UnitConstants;
import com.goertek.commonlib.view.unit.base.IDrawUnit;
import com.goertek.commonlib.view.unit.basic.ArcDraw;
import com.goertek.commonlib.view.unit.basic.ArcTextDraw;
import com.goertek.commonlib.view.unit.basic.CombinedResDraw;
import com.goertek.commonlib.view.unit.basic.HandDraw;
import com.goertek.commonlib.view.unit.basic.LineDraw;
import com.goertek.commonlib.view.unit.basic.OrderedResDraw;
import com.goertek.commonlib.view.unit.basic.RandomResDraw;
import com.goertek.commonlib.view.unit.basic.SelectedLayer;
import com.goertek.commonlib.view.unit.basic.SelectedResDraw;
import com.goertek.commonlib.view.unit.basic.SingleResDraw;
import com.goertek.commonlib.view.unit.basic.TextDraw;

/**
 * 根据传递的draw_type获取对应绘制类型
 */
public class UIDrawUnitFactory {
    private static final String TAG = "UIDrawUnitFactory";

    private UIDrawUnitFactory() {
    }

    /**
     * 生产方法
     *
     * @param assetPackage 资源包对象
     * @param layer        xml中的最小绘制单元
     * @return 生产出的layer对象
     */
    public static IDrawUnit createDrawUnit(AssetPackage assetPackage, Layer layer) {
        IDrawUnit dawUnit;
        LogUtil.e(TAG,"layer.getDrawType==========="+layer.getDrawType());
        switch (layer.getDrawType()) {
            case UnitConstants.VALUE_DRAW_TYPE_SINGLE_RES:
                dawUnit = new SingleResDraw(assetPackage, layer);
                break;
            case UnitConstants.VALUE_DRAW_TYPE_ORDERED_RES:
                dawUnit = new OrderedResDraw(assetPackage, layer);
                break;
            case UnitConstants.VALUE_DRAW_TYPE_HAND_RES:
                dawUnit = new HandDraw(assetPackage, layer);
                break;
            case UnitConstants.VALUE_DRAW_TYPE_COMBINED_RES:
                dawUnit = new CombinedResDraw(assetPackage, layer);
                break;
            case UnitConstants.VALUE_DRAW_TYPE_TEXT:
                LogUtil.e(TAG,"drawType============" + layer);
                dawUnit = new TextDraw(assetPackage, layer);
                break;
            case UnitConstants.VALUE_DRAW_TYPE_ARC_TEXT:
                dawUnit = new ArcTextDraw(assetPackage, layer);
                break;
            case UnitConstants.VALUE_DRAW_TYPE_LINE:
                dawUnit = new LineDraw(assetPackage, layer);
                break;
            case UnitConstants.VALUE_DRAW_TYPE_ARC:
                dawUnit = new ArcDraw(assetPackage, layer);
                break;
            case UnitConstants.VALUE_DRAW_TYPE_SELECTED_RES:
                dawUnit = new SelectedResDraw(assetPackage, layer);
                break;
            case UnitConstants.VALUE_DRAW_TYPE_RANDOM_RES:
                dawUnit = new RandomResDraw(assetPackage, layer);
                break;
            case UnitConstants.VALUE_DRAW_TYPE_SELECTED_LAYER:
                dawUnit = new SelectedLayer(assetPackage, layer);
                break;
            default:
                dawUnit = null;
                break;
        }
        return dawUnit;
    }
}
