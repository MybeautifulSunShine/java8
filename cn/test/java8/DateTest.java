package cn.test.java8;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;

/***************************************
 * @author:Alex Wang
 * @Date:2016/11/13 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class DateTest {
    public static void main(String[] args) throws ParseException, InterruptedException {
/*        Date date = new Date(116, 2, 18);
        System.out.println(date);*/

/*        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                for (int x = 0; x < 100; x++) {
                    Date parseDate = null;
                    try {
                        parseDate = sdf.parse("20160505");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    System.out.println(parseDate);
                }
            }).start();
        }*/

//        testLocalDate();
//        testLocalTime();
//        combineLocalDateAndTime();
//        testInstant();
//        testDuration();
//        testPeriod();
        testDateFormat();
        testDateParse();
    }

    private static void testLocalDate() {
        //这个类是线程安全的
        LocalDate localDate = LocalDate.of(2018, 04, 03);
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonth());
        System.out.println(localDate.getMonthValue());
        //这一年的那一天
        System.out.println(localDate.getDayOfYear());
        //这个月的那一天
        System.out.println(localDate.getDayOfMonth());
        //这一周的那一天
        System.out.println(localDate.getDayOfWeek());

        localDate.get(ChronoField.DAY_OF_MONTH);
    }

    private static void testLocalTime() {
        //现在的时间
        LocalTime time = LocalTime.now();
        System.out.println(time.getHour());
        System.out.println(time.getMinute());
        System.out.println(time.getSecond());
//        System.out.println(time.toNanoOfDay());
    }
    //整合localdate和localtime
    private static void combineLocalDateAndTime() {
        //更丰富,更安全
        /*LocalDate now = LocalDate.now();
        LocalTime now1 = LocalTime.now();
        LocalDateTime Localdatetime = LocalDateTime.of(now, now1);*/
        LocalDate localDate = LocalDate.now();
        LocalTime time = LocalTime.now();

        LocalDateTime localDateTime = LocalDateTime.of(localDate, time);
        System.out.println(localDateTime.toString());
        LocalDateTime now = LocalDateTime.now();
        //以前的APi
       /* Calendar instance = Calendar.getInstance();
        System.out.println(instance.getWeekYear());*/
        System.out.println(now);
    }

    private static void testInstant() throws InterruptedException {
        Instant start = Instant.now();
        Thread.sleep(1000L);
        Instant end = Instant.now();
        //代表的一段时间段
        Duration duration = Duration.between(start, end);
        System.out.println(duration.toMillis());
    }
    //只计算两个时间的差值
    private static void testDuration() {
        LocalTime time = LocalTime.now();
        //减去一个小时
        LocalTime beforeTime = time.minusHours(1);
        //计算两个之间的时间
        Duration duration = Duration.between(time, beforeTime);
        System.out.println(duration.toHours());
    }
    //相当于一个周期
    private static void testPeriod() {
        Period period = Period.between(LocalDate.of(2014, 1, 10), LocalDate.of(2018, 1, 10));
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
        System.out.println(period.getYears());
    }

    private static void testDateFormat() {
        LocalDate localDate = LocalDate.now();
        String format1 = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
//        String format2 = localDate.format(DateTimeFormatter.ISO_LOCAL_TIME);
        System.out.println(format1);
//        System.out.println(format2);

        DateTimeFormatter mySelfFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String format = localDate.format(mySelfFormatter);
        System.out.println(format);
        System.out.println(LocalDateTime.now());;
    }

    private static void testDateParse() {
        String date1 = "20161113";
        LocalDate localDate = LocalDate.parse(date1, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(localDate);
        
        DateTimeFormatter mySelfFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date2 = "2016-11-13";
        LocalDate localDate2 = LocalDate.parse(date2, mySelfFormatter);
        System.out.println(localDate2);
    }
}