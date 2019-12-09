package com.goertek.watchface.dataprovider.network;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;

import java.util.Set;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/30
 */
public class NetWorkStatusController {
    private static final String TAG = "NetWorkStatusController";

    private static final int BLUETOOTH_CLOSE_FLAG = 0;

    private static final int BLUETOOTH_OPEN_AND_CONNECT_FLAG = 2;

    private static final int BLUETOOTH_OPEN_FLAG = 1;

    private static NetWorkStatusController instance;

    private Context mContext;

    public NetWorkStatusController() {
    }

    public static NetWorkStatusController getInstance() {
        synchronized (NetWorkStatusController.class) {
            if (instance == null) {
                instance = new NetWorkStatusController();
            }
        }
        return instance;
    }

    public void destoryContext() {
        this.mContext = null;
    }

    public int getBluethStatus() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null && defaultAdapter.isEnabled()) {
            Set bondedDevices = defaultAdapter.getBondedDevices();
            return bondedDevices != null && bondedDevices.size() > 0 ? 2 : 1;
        } else {
            return 0;
        }
    }

    public void setContext(Context context) {
        this.mContext = context;
    }
}

