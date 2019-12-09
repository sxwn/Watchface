package com.goertek.commonlib.view.unit.basic;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.goertek.commonlib.attribute.AttributeBean;
import com.goertek.commonlib.provider.data.manager.DataAdapter;
import com.goertek.commonlib.provider.manager.AssetPackage;
import com.goertek.commonlib.provider.model.Layer;
import com.goertek.commonlib.utils.UIDrawUnitFactory;
import com.goertek.commonlib.view.unit.base.IDrawUnit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class SelectedLayer implements IDrawUnit {
    private List<IDrawUnit> mDrawUnits;
    private String mValueType;

    public SelectedLayer(AssetPackage assetPackage, Layer layer) {
        this.mValueType = layer.getValueType();
        this.mDrawUnits = new ArrayList(0);

        List<Layer> layers = layer.getLayers();

        for (Layer selectedLayer : layers) {
            IDrawUnit drawUnit = UIDrawUnitFactory.createDrawUnit(assetPackage, selectedLayer);
            mDrawUnits.add(drawUnit);
        }
    }

    private IDrawUnit getSelectDrawUnit() {
        int indexValue = DataAdapter.getInstance().getLayerIndexValue(this.mValueType);
        return this.mDrawUnits.get(indexValue);
    }

    public void destroy() {
        if (mDrawUnits != null && mDrawUnits.size() > 0) {
            for (IDrawUnit unit : mDrawUnits) {
                unit.destroy();
            }
        }
    }

    @Override
    public void preDraw(Canvas canvas, boolean isAmbientMode) {

    }

    public void draw(Canvas canvas, boolean isAmbientMode) {
        this.getSelectDrawUnit().draw(canvas, isAmbientMode);
    }

    public float[] getDrawUnitEndPosition() {
        return new float[0];
    }

    public void isAmbientModeChanged(boolean ambientMode) {
        this.getSelectDrawUnit().isAmbientModeChanged(ambientMode);
    }

    public void onClick(int x, int y) {
        this.getSelectDrawUnit().onClick(x, y);
    }

    public void onVisibilityChanged(boolean visible) {
        this.getSelectDrawUnit().onVisibilityChanged(visible);
    }

    public void setWidgetRect(RectF rect) {
        Iterator var2 = this.mDrawUnits.iterator();

        while (var2.hasNext()) {
            ((IDrawUnit) var2.next()).setWidgetRect(rect);
        }

    }

    @Override
    public void setRelativeAttribute(AttributeBean attributeBean) {
    }
}