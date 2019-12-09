package com.goertek.watchface.dataprovider.network;

import android.content.Context;

import com.goertek.commonlib.provider.data.manager.DataAdapter;
import com.goertek.commonlib.utils.LogUtil;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class NetWorkStateRegister {
    private static final String TAG = "NetWorkStateRegister";

    private static NetWorkStateProvider netWorkStateProvider = new NetWorkStateProvider();

    private NetWorkStateRegister() {
    }

    public static void registerNetWorkStateProvider(Context context) {
        LogUtil.i("NetWorkStateRegister", "consumption registerNetWorkStateProvider");
        NetWorkStatusController.getInstance().setContext(context);
        registerProvider("blueth_status");
    }

    private static void registerProvider(String valueType) {
        DataAdapter.getInstance().registerDataProvider(valueType, netWorkStateProvider);
    }

    public static void unregisterNetWorkStateProvider() {
        LogUtil.i("NetWorkStateRegister", "consumption unregisterNetWorkStateProvider");
        unregisterProvider("blueth_status");
    }

    private static void unregisterProvider(String valueType) {
        DataAdapter.getInstance().unregisterDataProvider(valueType, netWorkStateProvider);
    }
}
