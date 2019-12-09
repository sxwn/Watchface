package com.goertek.commonlib.attribute;

/**
 * 绘制属性实体
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/09
 */
/**
 * 属性传递实体类
 *
 * @author wwx706176
 * @version 1.0.0
 * @since 2019-05-10
 */
public class AttributeBean {

    private float mPositionX;

    private float mPositionY;

    private float mRotateAngle;

    private float mAngleStart;

    private float mAngleSeep;

    public AttributeBean(float positionX,float positionY,float rotateAngle) {
        mPositionX = positionX;
        mPositionY = positionY;
        mRotateAngle = rotateAngle;
    }

    public AttributeBean(float angleStart,float angleSweep) {
        mAngleStart = angleStart;
        mAngleSeep = angleSweep;
    }

    public float getPositionX() {
        return mPositionX;
    }

    public float getPositionY() {
        return mPositionY;
    }

    public float getRotateAngle() {
        return mRotateAngle;
    }

    public float getAngleStart() {
        return mAngleStart;
    }

    public float getAngleSeep() {
        return mAngleSeep;
    }
}