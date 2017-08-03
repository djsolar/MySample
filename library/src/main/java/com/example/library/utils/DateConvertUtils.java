package com.example.library.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zhouyiran on 2017/7/18.
 */

public class DateConvertUtils {
    public static final String DATE_FORMAT_PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_PATTERN_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT_PATTERN_YYYY_MM_DD = "yyyy-MM-dd";


    /**
     * 将时间字符串转化成时间戳
     *
     * @param data    待转换的日期
     * @param pattern 日期格式
     * @return
     */
    public static long dateToTimeStamp(String data, String pattern) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.CHINA);
        Date date = null;
        try {
            date = simpleDateFormat.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert date != null;
        return date.getTime();
    }

    /**
     * 将时间戳转换为字符串
     *
     * @param time 待转换的时间戳
     * @param pattern 时间格式
     * @return
     */
    public static String timeStampToString(long time, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.CHINA);
        Date date = new Date(time);
        return simpleDateFormat.format(date);
    }


    /**
     * 日期转星期
     *
     * @param dateString 日期
     * @param pattern 传进的字符串的格式
     * @return
     */
    public static String convertDateToWeek(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_PATTERN_YYYY_MM_DD, Locale.CHINA);
        Date date = null;
        try {
            date = sdf.parse(dateString);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (isNow(date)) {
            return "今天";
        }

        String[] weeks = new String[] {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weeks[dayOfWeek];
    }

    private static boolean isNow(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_PATTERN_YYYY_MM_DD, Locale.CHINA);
        Date now = new Date();
        String nowDate = simpleDateFormat.format(now);
        String dayDate = simpleDateFormat.format(date);
        return nowDate.equals(dayDate);
    }
}
