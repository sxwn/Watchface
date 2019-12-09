package com.goertek.commonlib.utils;

import android.text.TextUtils;

import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

public class HEXUtils {

    private static final String TAG = "HEXUtils";
    private static final byte[] NULL_BYTE_ARRAY = new byte[0];
    private static final String HEXSTR = "0123456789ABCDEF";
    private static final String UTF_8 = "utf-8";

    public static String intToHex(int i) {
        if (i < 0) {
            String hexString = Integer.toHexString(i);
            return hexString.substring(hexString.length() - 4);
        } else if (Integer.toHexString(i).length() % 2 == 0) {
            return Integer.toHexString(i);
        } else {
            return "0" + Integer.toHexString(i);
        }
    }

    public static String int2Uint32Hex(int i) {
        String intToHex = intToHex((i >> 24) & 255);
        String str = intToHex + intToHex((i >> 16) & 255);
        String str2 = str + intToHex((i >> 8) & 255);
        return str2 + intToHex(i & 255);
    }

    public static String int2Uint16Hex(int i) {
        String intToHex = intToHex((i >> 8) & 255);
        return intToHex + intToHex(i & 255);
    }

    public static String stringToUnicode(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            String hexString = Integer.toHexString(charAt);
            if (charAt > 128) {
                sb.append(hexString);
            } else {
                sb.append("00");
                sb.append(hexString);
            }
        }
        return sb.toString();
    }

    public static String unicodeToString(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder("");
        int length = str.length() / 4;
        int i = 0;
        while (i < length) {
            i++;
            String subStr = str.substring((i - 1) * 4, i * 4);
            sb.append(new String(Character.toChars(
                    Integer.valueOf(subStr.substring(0, 2) + "00", 16) +
                            Integer.valueOf(subStr.substring(2), 16))));
        }
        return sb.toString();
    }

    public static byte[] hexToBytes(String str) {
        if (TextUtils.isEmpty(str)) {
            return NULL_BYTE_ARRAY;
        }
        String replaceAll = str.replaceAll(" ", "");
        int length = replaceAll.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            String subBefore = replaceAll.substring(i * 2, i * 2 + 1);
            String subAfter = replaceAll.substring(i * 2 + 1, i * 2 + 2);
            bArr[i] = (byte) Integer.parseInt(subBefore + subAfter, 16);
        }
        return bArr;
    }

    public static String byteToHex(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & UnsignedBytes.MAX_VALUE);
            if (hexString.length() == 1) {
                sb.append("0");
            }
            sb.append(hexString);
        }
        return sb.toString().toUpperCase(Locale.ENGLISH).trim();
    }

    public static String hexToString(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        char[] charArray = str.toCharArray();
        byte[] bArr = new byte[(str.length() / 2)];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) (((HEXSTR.indexOf(charArray[i * 2]) * 16)
                    + HEXSTR.indexOf(charArray[i * 2 + 1])) & 255);
        }
        String result = "";
        try {
            result = new String(bArr, UTF_8);
        } catch (UnsupportedEncodingException e) {
            LogUtil.e(TAG, "UnsupportedEncodingException error !!!");
        }
        return result;
    }

    public static String stringToHex(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        char[] charArray = HEXSTR.toCharArray();
        StringBuilder sb = new StringBuilder();
        byte[] bArr = null;
        try {
            bArr = str.getBytes(UTF_8);
        } catch (UnsupportedEncodingException e) {
            LogUtil.e(TAG, "UnsupportedEncodingException error !!!");
        }
        if (bArr == null || bArr.length <= 0) {
            return "";
        }
        for (byte b : bArr) {
            sb.append(charArray[(b & 240) >> 4]);
            sb.append(charArray[b & Ascii.SI]);
        }
        return sb.toString().trim();
    }

    public static byte[] intTo16UnitBytes(int i) {
        return new byte[]{(byte) ((65280 & i) >> 8), (byte) (i & 255)};
    }

    public static byte[] intTo32UnitBytes(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public static byte[] intTo64UnitBytes(long j) {
        byte[] bArr = new byte[8];
        for (int i = 0; i < bArr.length; i++) {
            bArr[(bArr.length - i) - 1] = (byte) ((int) (255 & j));
            j >>= 8;
        }
        return bArr;
    }

    public static long bytesToLong(byte[] bArr) {
        long result = 0;
        for (byte b : bArr) {
            result = (result << 8) | ((long) (b & UnsignedBytes.MAX_VALUE));
        }
        return result;
    }

    public static int bytesToInteger(byte[] bArrs) {
        int result = 0;
        for (byte bArr : bArrs) {
            result = (result << 8) | (bArr & UnsignedBytes.MAX_VALUE);
        }
        return result;
    }
}
