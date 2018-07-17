package cn.xiaoyu.learning.java8.date.api;

import java.time.*;

/**
 * Date Time API 增强对日期和时间的处理
 */
public class DateApiTest {
    public static void main(String[] args) {
        /**
         * 本地化日期时间 API
         */
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("当前时间：" + currentTime);

        LocalDate localDate = currentTime.toLocalDate();
        System.out.println("当前日期：" + localDate);

        Month month = currentTime.getMonth();
        int day = currentTime.getDayOfMonth();
        int second = currentTime.getSecond();
        System.out.println("月: " + month + ", 日: " + day + ", 秒: " + second);

        LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
        System.out.println("date2: " + date2);

        // 12 december 2014
        LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
        System.out.println("date3: " + date3);

        // 22 小时 15 分钟
        LocalTime date4 = LocalTime.of(22, 15);
        System.out.println("date4: " + date4);

        // 解析字符串
        LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date5: " + date5);

        /**
         * 使用时区的日期时间API
         */
        // 获取当前时间日期
        ZonedDateTime date1 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
        System.out.println("date1: " + date1);

        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId: " + id);

        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("当期时区: " + currentZone);
    }
}
