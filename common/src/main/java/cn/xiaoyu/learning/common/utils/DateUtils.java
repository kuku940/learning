package cn.xiaoyu.learning.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 01399268
 * @date 2020/9/27
 */
public class DateUtils {
    public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDD = "yyyy-MM-dd";

    public static Date toDate(String date, String pattern) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.parse(date);
    }

    public static Calendar toCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static Calendar toCalendar(String date, String pattern) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(toDate(date, pattern));
        return calendar;
    }
}
