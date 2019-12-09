package com.goertek.commonlib.view.unit.basic;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.goertek.commonlib.attribute.AttributeBean;
import com.goertek.commonlib.provider.manager.AssetPackage;
import com.goertek.commonlib.provider.model.Layer;
import com.goertek.commonlib.utils.LogUtil;
import com.goertek.commonlib.view.unit.base.BaseResDraw;

import java.util.List;

/**
 * 单图绘制类，支持位置变换效果
 *
 * @author ww
 * @version 1.0.0
 * @since 2019/07/10
 */
public class SingleResDraw extends BaseResDraw {
    private static final String TAG = "SingleResDraw";

    private static final int FACTOR = 2;

    /**
     * 初始化单图绘制资源
     *
     * @param assetPackage 资源包
     * @param layer 图层
     */
    public SingleResDraw(AssetPackage assetPackage, Layer layer) {
        super(assetPackage, layer);
    }

    @Override
    public void preDraw(Canvas canvas, boolean isAmbientMode) {
    }

    @Override
    protected void onDraw(Canvas canvas, boolean isAmbientMode) {
        List<Bitmap> bitmaps = isAmbientMode ? getAmbientBitmaps() : getActiveBitmaps();
        if ((bitmaps == null) || (bitmaps.size() <= 0)) {
            LogUtil.e(TAG, "bitmaps == null");
            return;
        }
        Bitmap bitmap = bitmaps.get(0);
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, getPosition()[0], getPosition()[1], getPaint());
        }
    }

    @Override
    public void setRelativeAttribute(AttributeBean bean) {
        setPosition(new float[]{bean.getPositionX(), bean.getPositionY()});
        setRotateAnimation(bean.getRotateAngle());
        Bitmap showBitmap = getShowBitmap();
        float bitmapCenterX = (showBitmap == null) ? 0 : (showBitmap.getWidth()) / FACTOR;
        float bitmapCenterY = (showBitmap == null) ? 0 : (showBitmap.getHeight()) / FACTOR;
        float centerX = getPosition()[0] + bitmapCenterX;
        float centerY = getPosition()[1] + bitmapCenterY;
        setRotateCenterPoint(centerX, centerY);
    }

    private Bitmap getShowBitmap() {
        List<Bitmap> bitmaps = getActiveBitmaps();
        if ((bitmaps == null) || (bitmaps.size() <= 0)) {
            return null;
        }
        return bitmaps.get(0);
    }

    @Override
    protected void onDestroy() {
    }

    @Override
    public void isAmbientModeChanged(boolean ambientMode) {
    }

    @Override
    public float[] getDrawUnitEndPosition() {
        if (getShowBitmap() != null) {
            return new float[]{getShowBitmap().getWidth(), 0};
        }
        return new float[]{0,0};
    }
}