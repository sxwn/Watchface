package com.goertek.commonlib.provider.data.model;

public class FloatRangeValue {
    private float mMax;

    private float mMin;

    private float mValue;

    public FloatRangeValue() {}

    public FloatRangeValue(float value, float max, float min) {
        this.mValue = value;
        mMax = max;
        mMin = min;
    }

    public float getmMax() { return mMax; }

    public float getmMin() { return mMin; }

    public float getmValue() { return mValue; }
}
