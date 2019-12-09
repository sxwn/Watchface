package com.goertek.watchface.dataprovider.pressure;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;

import com.goertek.commonlib.utils.LogUtil;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class PressureSensorListenerUtils {
    private static final String TAG = "PressureDataProvider";

    public static final int DEFAULT_GOAL_ALTITUDE = 10000;

    public static final int DEFAULT_MIN_ALTITUDE = 1000;

    private static final int DELAY = 500;

    private static final int HIGH_ACC = 2;

    private static final int PRESSURE_BASE_NUMBER = 44330000;

    private static final double PRESSURE_INDEX_NUMBER = 5255.0D;

    private static final double STANDARD_PRESSURE = 1013.25D;

    private static PressureSensorListenerUtils instance;

    private int mAltitudeValue = 0;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.arg1 == 6) {
                int arg2 = message.arg2;
                if (arg2 > 0) {
                    PressureSensorListenerUtils.this.setPressureValue(arg2);
                    double var2 = Math.pow((double) arg2 / 1013.25D, 1.9029495718363463E-4D);
                    PressureSensorListenerUtils.this.setAltitudeValue(Double.valueOf((1.0D - var2) * 4.433E7D).intValue());
                }
            }
        }
    };
    private boolean mIsRegister = false;
    private int mPressureValue = 0;
    private SensorEventListener mSensorEventListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent != null && sensorEvent.sensor != null) {
                int type = sensorEvent.sensor.getType();
                int intValue = Float.valueOf(sensorEvent.values[0]).intValue();
                int accuracy = sensorEvent.accuracy;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onSensorChanged type:");
                stringBuilder.append(type);
                stringBuilder.append(",pressure:");
                stringBuilder.append(intValue);
                LogUtil.i("PressureDataProvider", stringBuilder.toString());
                if (intValue > 0) {
                    if (accuracy >= 2) {
                        Message message = PressureSensorListenerUtils.this.mHandler.obtainMessage();
                        message.arg1 = type;
                        message.arg2 = intValue;
                        PressureSensorListenerUtils.this.mHandler.sendMessageDelayed(message, 500L);
                        PressureSensorListenerUtils.this.unRegisterPressure();
                    }
                }
            } else {
                LogUtil.e("PressureDataProvider", "event error");
            }
        }
    };
    private SensorManager mSensorManager;

    private PressureSensorListenerUtils() {
    }

    public static PressureSensorListenerUtils getInstance() {
        synchronized (PressureSensorListenerUtils.class) {
            if (instance == null) {
                instance = new PressureSensorListenerUtils();
            }
            return instance;
        }
    }

    private void setAltitudeValue(int altitudeValue) {
        this.mAltitudeValue = altitudeValue;
    }

    private void setPressureValue(int pressureValue) {
        this.mPressureValue = pressureValue;
    }

    public int getAltitudeValue() {
        return this.mAltitudeValue;
    }

    public int getPressureValue() {
        return this.mPressureValue;
    }

    public void registerPressure(Context context) {
        if (!this.mIsRegister) {
            if (this.mSensorManager == null) {
                LogUtil.i("PressureDataProvider", "consumption mSensorManager init()");
                this.mSensorManager = (SensorManager) context.getSystemService("sensor");
            }

            LogUtil.i("PressureDataProvider", "consumption mSensorManager.registerListener");
            Sensor defaultSensor = this.mSensorManager.getDefaultSensor(6, false);
            this.mIsRegister = this.mSensorManager.registerListener(this.mSensorEventListener, defaultSensor, 3);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("consumption mSensorManager.registerListener success ");
            stringBuilder.append(this.mIsRegister);
            LogUtil.i("PressureDataProvider", stringBuilder.toString());
        }
    }

    public void unRegisterPressure() {
        if (this.mSensorManager != null && this.mIsRegister) {
            LogUtil.i("PressureDataProvider", "consumption mSensorManager.unregisterListener");
            SensorManager mSensorManager = this.mSensorManager;
            mSensorManager.unregisterListener(this.mSensorEventListener, mSensorManager.getDefaultSensor(6, false));
            this.mIsRegister = false;
        }
    }
}