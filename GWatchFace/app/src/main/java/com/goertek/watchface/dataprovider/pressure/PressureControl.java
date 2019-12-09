package com.goertek.watchface.dataprovider.pressure;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;

import com.goertek.commonlib.utils.LogUtil;

import java.util.LinkedList;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class PressureControl {
    private static final int ALTITUDE_LENGTH = 24;
    private static final String TAG = "PressureControl";

    private static final int HOUR_LENGTH = 3600000;

    private static PressureControl mControl;

    private LinkedList<Integer> mAltitudeList = null;

    private Context mContext;

    private BroadcastReceiver mTimeTickReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {

            LogUtil.d(PressureControl.TAG, "mTimeTickReceiver() onReceive()");
            if (context != null && intent != null) {
                if ("android.intent.action.TIME_TICK".equals(intent.getAction())) {
                    LogUtil.d(PressureControl.TAG, "mTimeTickReceiver() in ACTION");
                    PressureControl.this.onTimeTick();
                }

            } else {
                LogUtil.d(PressureControl.TAG, "mTimeTickReceiver() context or intent is null !");
            }
        }
    };

    private PressureControl() {
        this.mAltitudeList = new LinkedList();
    }

    private void addAltitude(int altitude) {
        this.mAltitudeList.pollFirst();
        this.mAltitudeList.addLast(altitude);
    }

    public static PressureControl getInstance() {
        synchronized (PressureControl.class) {
            if (mControl == null) {
                mControl = new PressureControl();
            }
            return mControl;
        }
    }

    private void initLinkedList() {
        LinkedList altitudeStrFromSp = PressureUtils.getAltitudeStrFromSp(this.mContext);
        int size = altitudeStrFromSp.size();
        if (size < 24) {
            for (int i = size; i < 24; ++i) {
                altitudeStrFromSp.addLast(1000);
            }
        }

        if (size == 0) {
            PressureUtils.saveAltitude(this.mContext, altitudeStrFromSp);
        }

        this.mAltitudeList.addAll(altitudeStrFromSp);
    }

    private void onTimeTick() {
        if (this.mContext != null) {
            if (System.currentTimeMillis() - PressureUtils.getTimeFromSp(this.mContext) >= 3600000L) {
                this.addAltitude(PressureSensorListenerUtils.getInstance().getAltitudeValue());
                PressureUtils.saveAltitude(this.mContext, this.mAltitudeList);
            }

        }
    }

    public int getAltitudeWithOffset(String s) {
        if (TextUtils.isEmpty(s)) {
            return 0;
        } else {
            int indexOf = s.lastIndexOf("_");
            if (indexOf < 0) {
                return 0;
            } else {
                s = s.substring(indexOf + 1);
                if (TextUtils.isEmpty(s)) {
                    return 0;
                } else {
                    label42:
                    {
                        boolean var10001;
                        try {
                            indexOf = Integer.parseInt(s);
                        } catch (NumberFormatException var4) {
                            var10001 = false;
                            break label42;
                        }

                        if (indexOf < 0) {
                            return 0;
                        }

                        if (indexOf > 23) {
                            return 0;
                        }

                        try {
                            indexOf = (Integer) this.mAltitudeList.get(24 - indexOf - 1);
                            return indexOf;
                        } catch (NumberFormatException var3) {
                            var10001 = false;
                        }
                    }

                    LogUtil.e(TAG, "getAltitudeWithOffset() Integer.parseInt error!");
                    return 0;
                }
            }
        }
    }

    public void registerTimeTickReceiver(Context context) {
        LogUtil.d(TAG, "registerTimeTickReceiver()");
        this.mContext = context;
        this.initLinkedList();
        IntentFilter filter = new IntentFilter("android.intent.action.TIME_TICK");
        context.registerReceiver(this.mTimeTickReceiver, filter);
    }

    public void unregisterTimeTickReceiver(Context context) {
        LogUtil.d(TAG, "unregisterTimeTickReceiver()");

        try {
            context.unregisterReceiver(this.mTimeTickReceiver);
        } catch (IllegalArgumentException var2) {
            LogUtil.e(TAG, "unregisterLocationReceiver() mTimeTickReceiver Error !");
        }
    }
}
