package com.goertek.watchface.dataprovider.moon;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 功能说明
 *
 * @author: ww
 * @version: 1.0.0
 * @since: 2019/07/08
 */
public class LunarCalendar {
    private static final String TAG = "LunarCalendar";

    private static final int BYTE_TO_SHIFT = 20;

    private static final int DATE_TYPE_INDEX_ONE = 1;

    private static final int DATE_TYPE_INDEX_THREE = 3;

    private static final int DATE_TYPE_INDEX_TWO = 2;

    private static final int DATE_TYPE_INDEX_ZERO = 0;

    private static final int DATE_TYPE_NUMBER = 4;

    private static final int DAYS_OF_MONTH = 31;

    private static final int LEAP_DAYS_BASE_RADIX = 1048576;

    private static final int LEAP_MONTH_BASE_RADIX = 15728640;

    private static final int LEAP_YEAR_BASE_RADIX = 1048448;

    private static final int LOOP_PARAMETER = 524288;

    private static final int LOOP_RANGE = 7;

    private static final int LUNAR_BIG_MONTH_DAYS = 30;

    private static final int[] LUNAR_INFO = new int[]{8697535, 306771, 677704, 5580477, 861776, 890180, 4631225, 354893, 634178, 2404022, 306762, 6966718, 675154, 861510, 6116026, 742478, 879171, 2714935, 613195, 7642049, 300884, 674632, 5973436, 435536, 447557, 4905656, 177741, 612162, 2398135, 300874, 6703934, 870993, 959814, 5690554, 372046, 177732, 3749688, 601675, 8165055, 824659, 870984, 7185723, 742735, 354885, 4894137, 154957, 601410, 2921910, 693578, 8080061, 445009, 742726, 5593787, 318030, 678723, 3484600, 338764, 9082175, 955730, 436808, 7001404, 701775, 308805, 4871993, 677709, 337474, 4100917, 890185, 7711422, 354897, 617798, 5549755, 306511, 675139, 5056183, 861515, 9261759, 742482, 748103, 6909244, 613200, 301893, 4869049, 674637, 11216322, 435540, 447561, 7002685, 702033, 612166, 5543867, 300879, 412484, 3581239, 959818, 8827583, 371795, 702023, 5846716, 601680, 824901, 5065400, 870988, 894273, 2468534, 354889, 8039869, 154962, 601415, 6067642, 693582, 739907, 4937015, 709962, 9788095, 309843, 678728, 6630332, 338768, 693061, 4672185, 436812, 709953, 2415286, 308810, 6969149, 675409, 861766, 6198074, 873293, 371267, 3585335, 617803, 11841215, 306515, 675144, 7153084, 861519, 873028, 6138424, 744012, 355649, 2403766, 301898, 8014782, 674641, 697670, 5984954, 447054, 711234, 3496759, 603979, 8689601, 300883, 412488, 6726972, 959823, 436804, 4896312, 699980, 601666, 3970869, 824905, 8211133, 870993, 894277, 5614266, 354894, 683331, 4533943, 339275, 9082303, 693587, 739911, 7034171, 709967, 350789, 4873528, 678732, 338754, 3838902, 430921, 7809469, 436817, 709958, 5561018, 308814, 677699, 4532024, 861770, 9343806, 873042, 895559, 6731067, 355663, 306757, 4869817, 675148, 857409, 2986677};

    private static final int LUNAR_SMALL_MONTH_DAYS = 29;

    private static final int LUNAR_YEAR_DAYS_BASE = 348;

    private static final int LUNAR_YEAR_DAYS_BASE_LEAP = 377;

    private static final int MAX_LUNAR_MONTH = 13;

    private static final int MAX_YEAR = 2099;

    private static final int MIN_YEAR = 1900;

    private static final float OFFSET_BASE = 8.64E7F;

    private LunarCalendar() {
    }

    private static int daysInLunarMonth(int day, int nowDay) {
        return (LUNAR_INFO[day - 1900] & 1048576 >> nowDay) == 0 ? 29 : 30;
    }

    private static int daysInLunarYear(int year) {
        int days;
        if (leapMonth(year) != 0) {
            days = 377;
        } else {
            days = 348;
        }

        int var3 = LUNAR_INFO[year - 1900];

        int dayInYear;
        for (year = 524288; year > 7; days = dayInYear) {
            dayInYear = days;
            if ((var3 & 1048448 & year) != 0) {
                dayInYear = days + 1;
            }

            year >>= 1;
        }

        return days;
    }

    private static int leapMonth(int months) {
        return (LUNAR_INFO[months - 1900] & 15728640) >> 20;
    }

    public static final int[] solarToLunar(int var0, int var1, int var2) {
        short var3 = 1900;
        byte var6 = 0;
        Date var8 = (new GregorianCalendar(1900, 0, 31)).getTime();
        var1 = Float.valueOf((float) ((new GregorianCalendar(var0, var1, var2)).getTime().getTime() - var8.getTime()) / 8.64E7F).intValue();
        int var4 = 0;

        for (var0 = var3; var0 <= 2099 && var1 > 0; ++var0) {
            var4 = daysInLunarYear(var0);
            var1 -= var4;
        }

        int var9 = var0;
        var2 = var1;
        if (var1 < 0) {
            var2 = var1 + var4;
            var9 = var0 - 1;
        }

        int var7 = leapMonth(var9);
        var0 = var2;
        var2 = 1;

        for (var4 = 0; var2 <= 13 && var0 > 0; ++var2) {
            var4 = daysInLunarMonth(var9, var2);
            var0 -= var4;
        }

        byte var5 = var6;
        var1 = var2;
        if (var7 != 0) {
            var5 = var6;
            var1 = var2;
            if (var2 > var7) {
                --var2;
                var5 = var6;
                var1 = var2;
                if (var2 == var7) {
                    var5 = 1;
                    var1 = var2;
                }
            }
        }

        int var10 = var0;
        var2 = var1;
        if (var0 < 0) {
            var10 = var0 + var4;
            var2 = var1 - 1;
        }

        return new int[]{var9, var2, var10 + 1, var5};
    }
}