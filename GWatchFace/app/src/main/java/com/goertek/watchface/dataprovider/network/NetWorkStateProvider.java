package com.goertek.watchface.dataprovider.network;

import com.goertek.commonlib.provider.data.manager.IDataProvider;
import com.goertek.commonlib.provider.data.model.FloatRangeValue;
import com.goertek.commonlib.provider.data.model.IntRangeValue;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class NetWorkStateProvider implements IDataProvider {
    private NetWorkStatusController mNetWorkStatusController = NetWorkStatusController.getInstance();

    public NetWorkStateProvider() {
    }

    public void doClick(String var1) {
    }

    public FloatRangeValue getFloatRangeValue(String var1) {
        return new FloatRangeValue(0.0F, 0.0F, 0.0F);
    }

    public float getFloatValue(String var1) {
        return 0.0F;
    }

    public int getIndexValue(String var1) {
        byte var2;
        if (var1.hashCode() == -917247741 && var1.equals("blueth_status")) {
            var2 = 0;
        } else {
            var2 = -1;
        }

        return var2 != 0 ? 0 : this.mNetWorkStatusController.getBluethStatus();
    }

    public IntRangeValue getIntRangeValue(String var1) {
        return new IntRangeValue(0, 0, 0);
    }

    public int getIntValue(String var1) {
        return 0;
    }

    public int getLayerIndexValue(String var1) {
        return 0;
    }

    public String getStringValue(String var1) {
        return "";
    }

    public String getStringValue(String var1, String... var2) {
        return "";
    }
}
