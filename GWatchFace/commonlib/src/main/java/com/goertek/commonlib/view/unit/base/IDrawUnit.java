package com.goertek.commonlib.view.unit.base;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.goertek.commonlib.attribute.AttributeBean;
/**
 * 绘制单元接口，提供绘制方法和资源销毁方法
 *
 * @author ww
 * @version 1.0.0
 * @since 2019/07/09
 */
public interface IDrawUnit {
    /**
     * 绘制前准备
     *
     * @param canvas 画笔
     * @param isAmbientMode 是否处于半亮模式
     */
    void preDraw(Canvas canvas, boolean isAmbientMode);

    /**
     * 绘制
     * @param canvas 画笔
     * @param isAmbientMode 是否是半亮模式
     */
    void draw(Canvas canvas, boolean isAmbientMode);

    /**
     * 根据控件区域重新计算图层绘制的绝对坐标
     * @param rect 控件区域
     */
    void setWidgetRect(RectF rect);

    /**
     * 根据父图层计算的相关数据传递给内部图层
     * @param bean 属性封装类
     */
    void setRelativeAttribute(AttributeBean bean);

    /**
     * 亮暗模式是否改变
     * @param ambientMode 是否半亮模式改变
     */
    void isAmbientModeChanged(boolean ambientMode);

    /**
     * 获取绘制单元的宽度
     * @return float 返回绘制单元宽度
     */
    float[] getDrawUnitEndPosition();

    /**
     * 显示模式是否改变
     * @param visible 是否显示
     */
    void onVisibilityChanged(boolean visible);

    /**
     * 资源销毁
     */
    void destroy();

    /**
     * 点击事件
     * @param x x轴坐标
     * @param y y轴坐标
     */
    void onClick(int x, int y);
}
