package com.goertek.commonlib.view.unit.animation;

/**
 * 动效接口类
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/09
 */
public interface IMotion {
    float getForwardDistance(float paramFloat);

    float getReverseDistance(float paramFloat);

    float getTotalTime();
}
