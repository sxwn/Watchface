package com.goertek.commonlib.provider.data.manager;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.goertek.commonlib.provider.data.model.FloatRangeValue;
import com.goertek.commonlib.provider.data.model.IntRangeValue;
import com.goertek.commonlib.utils.LogUtil;

import java.lang.annotation.Target;
import java.util.HashMap;

import static com.goertek.commonlib.service.BaseCanvasWatchFaceService.TAG;

/**
 * 数据提供者适配器
 *
 * @author ww
 * @version 1.0.0
 * @since 2019/07/10
 */
public class DataAdapter {
    private static DataAdapter instance;

    private HashMap<String, IDataProvider> mDataProviders;

    /**
     * 数据提供者私有构造函数
     */
    private DataAdapter() {
        mDataProviders = new HashMap<>(0);
    }

    /**
     * 数据提供者适配器单例获取
     * @return DataAdapter单例对象
     */
    public static DataAdapter getInstance() {
        if (instance == null) {
            synchronized (DataAdapter.class) {
                if (instance == null) {
                    instance = new DataAdapter();
                }
            }
        }
        return instance;
    }

    /**
     * 注册数据提供者
     * @param valueType 数据类型
     * @param dataProvider 数据提供者对象
     */
    public void registerDataProvider(String valueType, IDataProvider dataProvider) {
        mDataProviders.put(valueType, dataProvider);
    }

    /**
     * 去注册数据提供者
     * @param valueType 数据类型
     * @param dataProvider 数据提供者对象
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void unregisterDataProvider(String valueType, IDataProvider dataProvider) {
        mDataProviders.remove(valueType, dataProvider);
    }

    /**
     * 获取字符串值
     * @param valueType 数据类型
     * @return 字符串值
     */
    public String getStringValue(String valueType) {
        LogUtil.e(TAG,"valueType =======" + valueType);
        IDataProvider provider = mDataProviders.get(valueType);
        if (provider != null) {
            return provider.getStringValue(valueType);
        }
        return "";
    }

    /**
     * 获取字符串值
     * @param valueType 数据类型
     * @param format 字符串格式要求
     * @return 字符串值
     */
    public String getStringValue(String valueType, String... format) {
        IDataProvider provider = mDataProviders.get(valueType);
        if (provider != null) {
            return provider.getStringValue(valueType, format);
        }
        return "";
    }

    /**
     * 获取整数值
     * @param valueType 数据类型
     * @return 整数值
     */
    public int getIntValue(String valueType) {
        IDataProvider provider = mDataProviders.get(valueType);
        if (provider != null) {
            return provider.getIntValue(valueType);
        }
        return 0;
    }

    /**
     * 获取浮点数值
     * @param valueType 数据类型
     * @return 浮点数值
     */
    public float getFloatValue(String valueType) {
        IDataProvider provider = mDataProviders.get(valueType);
        if (provider != null) {
            return provider.getFloatValue(valueType);
        }
        return 0f;
    }

    /**
     * 获取整数RangeValue对象
     * @param valueType 数据类型
     * @return 整数RangeValue对象
     */
    public IntRangeValue getIntRangeValue(String valueType) {
        IDataProvider provider = mDataProviders.get(valueType);
        if (provider != null) {
            return provider.getIntRangeValue(valueType);
        }
        return new IntRangeValue(0, 0, 0);
    }

    /**
     * 获取浮点数RangeValue对象
     * @param valueType 数据类型
     * @return 浮点数RangeValue对象
     */
    public FloatRangeValue getFloatRangeValue(String valueType) {
        IDataProvider provider = mDataProviders.get(valueType);
        if (provider != null) {
            return provider.getFloatRangeValue(valueType);
        }
        return new FloatRangeValue(0f, 0f, 0f);
    }

    /**
     * 获取数据类型对应的索引值
     * @param valueType 数据类型
     * @return 索引值
     */
    public int getIndexValue(String valueType) {
        IDataProvider provider = mDataProviders.get(valueType);
        if (provider != null) {
            return provider.getIndexValue(valueType);
        }
        return 0;
    }

    /**
     * 获取数据类型对应的layer索引值
     * @param valueType 数据类型
     * @return layer索引值
     */
    public int getLayerIndexValue(String valueType) {
        IDataProvider provider = mDataProviders.get(valueType);
        if (provider != null) {
            return provider.getLayerIndexValue(valueType);
        }
        return 0;
    }

    /**
     * 点击事件
     * @param dataType 数据类型
     */
    public void doClick(String dataType) {
        IDataProvider provider = mDataProviders.get(dataType);
        if (provider != null) {
            provider.doClick(dataType);
        }
    }
}
