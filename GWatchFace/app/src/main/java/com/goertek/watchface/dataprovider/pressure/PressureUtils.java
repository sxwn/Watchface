package com.goertek.watchface.dataprovider.pressure;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.goertek.commonlib.utils.LogUtil;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class PressureUtils {
    private static final String TAG = "PressureUtils";

    private static final String ALTITUDE = "outdoorAltitude";

    private static final String ALTITUDE_TIME = "time";

    private static final String SP_NAME = "outdoor";

    private PressureUtils() {
    }

    private static String getAltitudeStr(List<Integer> integers) {
        if (integers == null) {
            return "";
        } else {
            int var1 = 0;
            StringBuilder var4 = new StringBuilder(0);
            int var3 = integers.size();
            Iterator var5 = integers.iterator();

            while (var5.hasNext()) {
                var4.append((Integer) var5.next());
                if (var1 != var3 - 1) {
                    var4.append(",");
                }

                int var2 = var1 + 1;
                var1 = var2;
                if (var2 >= var3) {
                    break;
                }
            }

            return var4.toString();
        }
    }

    public static LinkedList<Integer> getAltitudeStrFromSp(Context var0) {
        LinkedList var1 = new LinkedList();
        String var4 = var0.getSharedPreferences("outdoor", 0).getString("outdoorAltitude", "");
        if (!TextUtils.isEmpty(var4)) {
            Iterator var5 = Arrays.asList(var4.split(",")).iterator();

            while (var5.hasNext()) {
                String var2 = (String) var5.next();

                try {
                    var1.addLast(Integer.parseInt(var2));
                } catch (NumberFormatException var3) {
                    LogUtil.e("PressureUtils", "getAltitudeStrFromSp() Integer.parseInt error!");
                }
            }
        }

        return var1;
    }

    public static long getTimeFromSp(Context var0) {
        return var0.getSharedPreferences("outdoor", 0).getLong("time", 0L);
    }

    public static void saveAltitude(Context var0, List<Integer> var1) {
        SharedPreferences var4 = var0.getSharedPreferences("outdoor", 0);
        long var2 = System.currentTimeMillis();
        SharedPreferences.Editor var5 = var4.edit();
        var5.putString("outdoorAltitude", getAltitudeStr(var1));
        var5.putLong("time", var2);
        var5.apply();
    }
}