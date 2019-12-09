package com.goertek.commonlib.provider.data.model;

public class IntRangeValue {
    private int mMax;

    private int mMin;

    private int mValue;

    public IntRangeValue(int value, int max, int min) {
        mValue = value;
        mMax = max;
        mMin = min;
    }

    public int getmMax() { return mMax; }

    public int getmMin() { return mMin; }

    public int getmValue() { return mValue; }
}
