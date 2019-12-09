/*
 * Copyright (c) 2016 - Goertek -All rights reserved
 */

package com.goertek.commonlib.eventbus;

/**
 *   EventBus 功能类
 *  @author: carey.yu
 *  @version: 1.0.0
 *  @since: 2019/7/24
 */
public class ModuleBus {
    private static GoerEventBus mBus;
    private static void checkInitialized() {
        if (mBus != null) {
            return;
        }
        throw new IllegalStateException("EventBus has not been initialized yet");
    }

    /**
     * 发布
     * @param paramObject 对象
     * @return 发布完成
     */
    public static boolean emit(Object paramObject) {
        checkInitialized();
        mBus.send(paramObject);
        return true;
    }

    /**
     * eventBus 初始化
     * @return 完成初始化
     */
    public static boolean initEventBus() {
        if (mBus == null) {
           mBus = GoerEventBus.getDefault();
        }
        return true;
    }

    /**
     *  注册
     * @param paramObject 参数
     * @return 注册结果
     */
    public static boolean register(Object paramObject) {
        checkInitialized();
        mBus.register(paramObject);
        return true;
    }

    /**
     * 注销
     * @param paramObject
     * @return
     */
    public static boolean unregister(Object paramObject) {
        checkInitialized();
        mBus.unregister(paramObject);
        return true;
    }

}
