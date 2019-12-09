package com.goertek.commonlib.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import androidx.annotation.ColorInt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * 项目工具类
 *
 * @author ww
 * @version 1.0.0
 * @since 2019/07/08
 */
public class WatchFaceUtil {
    private static final String TAG = "WatchFaceUtil";

    private static final String INTERCEPT_STR_PREFIX = "@R.string.";

    private static final String RES_TYPE = "string";

    private static final String SPLIT_STRING = ",";

    private WatchFaceUtil() {
    }

    /**
     * 禁用应用
     *
     * @param context 上下文
     * @param packageName 包名
     * @param enabled 是否禁用
     * @return int
     */
    public static int isPackageEnabled(Context context, String packageName, boolean enabled) {
        if (context == null) {
            return 0;
        }
        PackageManager pm = context.getPackageManager();
        pm.setApplicationEnabledSetting(packageName,
                enabled ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED : PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                0);

        return 1;
    }

    /**
     * 禁用组件
     *
     * @param context 上下文
     * @param componentName 组件名
     * @param enabled 是否禁用
     * @return int
     */
    public static int isComponentEnabled(Context context, String componentName, boolean enabled) {
        if (context == null) {
            return 0;
        }
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(new ComponentName(context, componentName),
                enabled ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED : PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                0);
        return 1;
    }

    /**
     * 获取像素值
     *
     * @param context 上下文
     * @return 像素
     */
    public static int getDisplayMetrics(Context context) {
        if (context == null) {
            return 0;
        }
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 获取数字
     *
     * @param value 数字
     * @param pos 指数
     * @return 数字
     */
    public static int getDigit(String value, int pos) {
        int length = value.length();
        if ((0 > pos) || (length <= pos)) {
            return 0;
        }
        int max = ((Double) Math.pow(CommonConstantsUtil.NUMBER_TEN, length - pos)).intValue();
        int min = ((Double) Math.pow(CommonConstantsUtil.NUMBER_TEN, length - pos - 1)).intValue();
        long valueLong = 0L;
        try {
            valueLong = Long.valueOf(value);
        } catch (NumberFormatException e) {
            LogUtil.e(TAG, "method getDigit() catch NumberFormatException");
            valueLong = 0;
        }
        return (int) (valueLong % max / min);
    }

    /**
     * 获取数字
     *
     * @param value 数字
     * @param pos 指数
     * @return 数字
     */
    public static int getDigit(int value, int pos) {
        int length = String.valueOf(value).length();
        if ((0 > pos) || (length <= pos)) {
            return 0;
        }
        int max = ((Double) Math.pow(CommonConstantsUtil.NUMBER_TEN, length - pos)).intValue();
        int min = ((Double) Math.pow(CommonConstantsUtil.NUMBER_TEN, length - pos - 1)).intValue();

        return (value % max) / min;
    }

    /**
     * 获取角度
     *
     * @param max 最大值
     * @param value 当前值
     * @return 角度
     */
    public static float getAngel(long max, long value) {
        if ((0 >= max) || (0 >= value)) {
            return 0F;
        }
        float unit = CommonConstantsUtil.NUMBER_THREE_HUNDRED_AND_SIX / max;
        return (value % max) * unit;
    }

    /**
     * 获取角度
     *
     * @param startAngel 起始角度
     * @param endAngel 终止角度
     * @param max 最大值
     * @param min 最小值
     * @param value 当前值
     * @return 角度
     */
    public static float getAngel(float startAngel, float endAngel, int max, int min, int value) {
        if ((min > max) || (min > value) || (startAngel == endAngel)) {
            return 0F;
        }
        int range = max - min;
        if (range == 0) {
            return endAngel;
        }
        int relative = value - min;
        float rangeAngel = (startAngel < endAngel) ? (endAngel - startAngel) : (startAngel - endAngel);
        float relativeAngel = relative * (rangeAngel / range);
        float angel = (startAngel < endAngel) ? (startAngel + relativeAngel) : (startAngel - relativeAngel);
        return angel;
    }

    /**
     * 获取进度
     *
     * @param startPoint 起始点
     * @param endPoint 终止点
     * @param max 最大值
     * @param min 最小值
     * @param value 当前值
     * @return 坐标点
     */
    public static float[] getValuePoint(float[] startPoint, float[] endPoint, int max, int min, int value) {
        if ((min >= max) || (min > value)) {
            return startPoint;
        }
        if ((startPoint == null) || (endPoint == null)) {
            return startPoint;
        }
        int range = max - min;
        int relative = value - min;
        float rangeX = endPoint[0] - startPoint[0];
        float rangeY = endPoint[1] - startPoint[1];
        float relativeX = (relative % range) * (rangeX / range);
        float relativeY = (relative % range) * (rangeY / range);
        return new float[] {startPoint[0] + relativeX, startPoint[1] + relativeY};
    }

    /**
     * 获取偏移量
     *
     * @param startPoint 起始点
     * @param endPoint 终止点
     * @param max 最大值
     * @param min 最小值
     * @param value 当前值
     * @return 偏移量
     */
    public static float[] getValueRelative(float[] startPoint, float[] endPoint, int max, int min, int value) {
        if ((min >= max) || (min > value)) {
            return new float[0];
        }
        if ((startPoint == null) || (endPoint == null)) {
            return new float[0];
        }
        int range = max - min;
        int relative = value - min;
        float rangeX = endPoint[0] - startPoint[0];
        float rangeY = endPoint[1] - startPoint[1];
        float relativeX = relative * (rangeX / range);
        float relativeY = relative * (rangeY / range);
        return new float[] {relativeX, relativeY};
    }

    /**
     * 获取boolean
     *
     * @param info 资源信息
     * @return boolean
     */
    public static boolean getBoolValue(String info) {
        if (TextUtils.isEmpty(info)) {
            LogUtil.i(TAG, "getBoolValue(), info: null");
            return false;
        }
        return info.trim().toLowerCase(Locale.ENGLISH).equals("true");
    }

    /**
     * 获取boolean
     *
     * @param info 资源信息
     * @param def 默认值
     * @return boolean
     */
    public static boolean getBoolValue(String info, boolean def) {
        if (TextUtils.isEmpty(info)) {
            LogUtil.i(TAG, "getBoolValue(), info: null");
            return def;
        }
        return info.trim().toLowerCase(Locale.ENGLISH).equals("true");
    }

    /**
     * 获取String
     *
     * @param info 资源信息
     * @return string
     */
    public static String getStringValue(String info) {
        String value = "";
        if (TextUtils.isEmpty(info)) {
            LogUtil.i(TAG, "getStringValue()  info: null");
            return value;
        }
        if (info.startsWith(INTERCEPT_STR_PREFIX)) {
            try {
                String resIdStr =
                        info.substring(info.lastIndexOf(INTERCEPT_STR_PREFIX) + INTERCEPT_STR_PREFIX.length());
                int resId = ContextUtil.getContext().getResources().getIdentifier(resIdStr, RES_TYPE,
                        ContextUtil.getContext().getPackageName());
                value = ContextUtil.getContext().getString(resId);
            } catch (Resources.NotFoundException exception) {
                LogUtil.e(TAG, "getStringValue catch Resources.NotFoundException");
            }
        } else {
            value = info.trim();
        }
        return value;
    }

    /**
     * 获取strings
     *
     * @param info 资源信息
     * @return strings
     */
    public static List<String> getStringValues(String info) {
        if (TextUtils.isEmpty(info)) {
            LogUtil.i(TAG, "getStringValues(), info: null");
            return Collections.emptyList();
        }
        List<String> tempList = Arrays.asList(info.split(SPLIT_STRING));
        List<String> values = new ArrayList<>(tempList.size());
        for (String value : tempList) {
            String tempValue = value.trim();
            values.add(tempValue);
        }
        return values;
    }

    public static List<String> getStringValues(String info,String splitStr) {
        if (TextUtils.isEmpty(info)) {
            LogUtil.i(TAG, "getStringValues(), info: null");
            return Collections.emptyList();
        }
        List<String> tempList = Arrays.asList(info.split(splitStr));
        List<String> values = new ArrayList<>(tempList.size());
        for (String value : tempList) {
            String tempValue = value.trim();
            values.add(tempValue);
        }
        return values;
    }

    /**
     * 获取int值
     *
     * @param info 资源信息
     * @return int
     */
    public static int getIntValue(String info) {
        if (TextUtils.isEmpty(info)) {
            LogUtil.i(TAG, "getIntValue() label: info: null");
            return 0;
        }
        return Integer.valueOf(info.trim());
    }

    /**
     * 获取long值
     *
     * @param info 资源信息
     * @return long
     */
    public static long getLongValue(String info) {
        if (TextUtils.isEmpty(info)) {
            LogUtil.i(TAG, "getLongValue() label: info: null");
            return 0;
        }
        return Long.valueOf(info.trim());
    }

    /**
     * 获取float
     *
     * @param info 资源信息
     * @return float
     */
    public static float getFloatValue(String info) {
        if (TextUtils.isEmpty(info)) {
            LogUtil.i(TAG, "getFloatValue(), info: null");
            return 0;
        }
        return Float.valueOf(info.trim());
    }

    /**
     * 获取float
     *
     * @param info 资源信息
     * @param def 默认值
     * @return float
     */
    public static float getFloatValue(String info, float def) {
        if (TextUtils.isEmpty(info)) {
            LogUtil.i(TAG, "getFloatValue(), info: null");
            return def;
        }
        return Float.valueOf(info.trim());
    }

    /**
     * 获取floats
     *
     * @param info 资源信息
     * @return floats
     */
    public static float[] getFloatValues(String info) {
        if (TextUtils.isEmpty(info)) {
            LogUtil.i(TAG, "getFloatValues() info: null");
            return new float[0];
        }
        List<String> values = Arrays.asList(info.split(SPLIT_STRING));
        float[] floatValues = new float[values.size()];
        int i = 0;
        for (String value : values) {
            floatValues[i] = Float.valueOf(value.trim());
            i++;
        }
        return floatValues;
    }

    /**
     * 获取Point
     *
     * @param info 资源信息
     * @return point
     */
    public static float[] getPoint(String info) {
        if (TextUtils.isEmpty(info)) {
            LogUtil.i(TAG, "getPoint() info: null");
            return new float[]{0,0};
        }
        List<String> values = Arrays.asList(info.trim().split(SPLIT_STRING));
        if (values.size() != CommonConstantsUtil.NUMBER_TWO) {
            LogUtil.i(TAG, "getPoint() info: " + info + ", info format error");
            return new float[]{0,0};
        }
        float x = Float.valueOf(values.get(0).trim());
        float y = Float.valueOf(values.get(1).trim());
        return new float[] {x, y};
    }

    /**
     * 获取rect
     *
     * @param info 资源信息
     * @return rect
     */
    public static Rect getRect(String info) {
        Rect rect = null;
        if (!TextUtils.isEmpty(info)) {
            List<String> values = Arrays.asList(info.trim().split(SPLIT_STRING));
            if (values.size() == CommonConstantsUtil.NUMBER_FOUR) {
                rect = new Rect(Integer.valueOf(values.get(0).trim()), Integer.valueOf(values.get(1).trim()),
                        Integer.valueOf(values.get(CommonConstantsUtil.NUMBER_TWO).trim()),
                        Integer.valueOf(values.get(CommonConstantsUtil.NUMBER_THREE).trim()));
            } else {
                LogUtil.i(TAG, "getRect() label, info format error");
            }
        } else {
            LogUtil.i(TAG, "getRect(), info: null");
        }
        return rect;
    }

    /**
     * 获取rect
     *
     * @param info 资源信息
     * @return rect
     */
    public static RectF getRectF(String info) {
        RectF rectF = null;
        if (!TextUtils.isEmpty(info)) {
            List<String> values = Arrays.asList(info.trim().split(SPLIT_STRING));
            if (values.size() == CommonConstantsUtil.NUMBER_FOUR) {
                rectF = new RectF(Float.valueOf(values.get(0).trim()), Float.valueOf(values.get(1).trim()),
                        Float.valueOf(values.get(CommonConstantsUtil.NUMBER_TWO).trim()),
                        Float.valueOf(values.get(CommonConstantsUtil.NUMBER_THREE).trim()));
            } else {
                LogUtil.i(TAG, "getRectF() label, info format error");
            }
        } else {
            LogUtil.i(TAG, "getRectF(), info: null");
        }
        return rectF;
    }

    /**
     * 获取color
     *
     * @param info 资源信息
     * @return color
     */
    public static @ColorInt int getColor(String info) {
        if (TextUtils.isEmpty(info)) {
            LogUtil.i(TAG, "getColor() label: info: null");
            return 0;
        }
        return Color.parseColor(info.trim());
    }

    /**
     * 获取color
     *
     * @param info 资源信息
     * @return color
     */
    public static @ColorInt int[] getColors(String info) {
        if (TextUtils.isEmpty(info)) {
            LogUtil.i(TAG, "getColor() label: info: null");
            return new int[0];
        }
        List<String> values = Arrays.asList(info.split(SPLIT_STRING));
        @ColorInt
        int[] colors = new int[values.size()];
        int i = 0;
        for (String value : values) {
            colors[i] = Color.parseColor(value.trim());
            i++;
        }
        return colors;
    }

    /**
     * 判断坐标是否在矩形之中
     *
     * @param x x坐标
     * @param y y坐标
     * @param rect 矩形
     * @return bool
     */
    public static boolean isInBound(int x, int y, Rect rect) {
        if (rect == null) {
            return false;
        }
        if ((x < rect.left) || (x > rect.right)) {
            return false;
        }
        if ((y < rect.top) || (y > rect.bottom)) {
            return false;
        }
        return true;
    }
}