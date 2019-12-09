package com.goertek.commonlib.utils;

public class FpsUtils {


    private static long sUpdateIntervalMs = 1000L;

    private static long UPDATE_TIME_DEFAULT = 30;

    private static long mFpsTimeUpdate;

    public static long getFpsTimeUpdate(){
        return mFpsTimeUpdate < UPDATE_TIME_DEFAULT ? UPDATE_TIME_DEFAULT : mFpsTimeUpdate;
    }

    public static void resetUpdateInterval() { sUpdateIntervalMs = 1000L; }

    public static void setUpdateInterval(long paramLong) {
        long l2 = sUpdateIntervalMs;
        long l1 = paramLong;
        if (l2 < paramLong)
            l1 = l2;
        sUpdateIntervalMs = l1;
    }
}