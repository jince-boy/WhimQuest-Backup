package com.whim.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Jince
 * @since: 2023.12.15 下午 08:59
 * @description: 时间工具类
 */
public class DateUtils {
    public static final String FORMAT_01 = "yyyy-MM-dd";
    public static final String FORMAT_02 = "HH:mm:ss";
    public static final String FORMAT_03 = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_04 = "yyyyMMddHHmmss";
    public static final String FORMAT_05 = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String FORMAT_06 = "yyyyMMdd";
    public static final String FORMAT_07 = "HHmmss";
    public static final String FORMAT_08 = "yyyy/MM/dd";
    public static final String FORMAT_09 = "yyyyMMdd HH:mm:ss";
    public static final String FORMAT_10 = "yyyyMM";
    public static final String FORMAT_11 = "yyyy";


    public static Date now() {
        return Date.from(Instant.now());
    }

    /**
     * str to date
     */
    public static Date parse(String date, String pattern) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            sdf.setLenient(false);
            return sdf.parse(date);
        } catch (Exception e) {
            throw new IllegalArgumentException("failed to parse " + date
                    + " by pattern: " + pattern, e);
        }
    }

    /**
     * date to str
     */
    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            sdf.setLenient(false);
            return sdf.format(date);
        }
        return null;
    }

    /**
     * 转换时间格式
     */
    public static String transFormat(String timeStr, String sourceFormat, String targetFormat) {
        try {
            SimpleDateFormat sourceSdf = new SimpleDateFormat(sourceFormat);
            SimpleDateFormat targetSdf = new SimpleDateFormat(targetFormat);
            Date date = sourceSdf.parse(timeStr);
            return targetSdf.format(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取时间之前或之后天数的时间
     *
     * @param dateString--yyyyMMddHHmmss；为空获取当前时间
     * @param day--正数后延天数；负数前延天数
     */
    public static String getBeforeDay(String dateString, int day) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_04);
        Calendar g = Calendar.getInstance();
        if (StringUtils.isNotBlank(dateString)) {
            Date date = sdf.parse(dateString);
            g.setTime(date);
        } else {
            g.setTimeInMillis(System.currentTimeMillis());
        }
        g.add(Calendar.DAY_OF_YEAR, day);// before 1 天
        return sdf.format(g.getTime());
    }

    public static Timestamp getCurTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static String formatCurrentDate(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setLenient(false);
        return sdf.format(currentDate());
    }

    public static String formatCurrentDateTime(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setLenient(false);
        return sdf.format(now());
    }

    /**
     * 获取当前日期
     */
    public static Date currentDate() {
        return date(now());
    }

    public static Date date(Date date) {
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取当前年月日
     */
    public static String getCurDT() {
        return formatCurrentDate(FORMAT_06);
    }

    /**
     * 获取当前年月
     */
    public static String getCurMonth() {
        return formatCurrentDate(FORMAT_10);
    }

    /**
     * 获取当前年
     */
    public static String getCurYear() {
        return formatCurrentDate(FORMAT_11);
    }

    /**
     * 获取当前时分秒
     */
    public static String getCurTM() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_07);
        return formatter.format(currentTime);
    }

    /**
     * 校验是否为有效的日期格式
     */
    public static boolean isValidDate(String s, String format) {
        if (StringUtils.isBlank(s)) {
            return false;
        }
        if (s.length() != format.length()) {
            return false;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            dateFormat.setLenient(false);
            dateFormat.parse(s);
            return true;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }
    }

    /**
     * 获取前 dateLength 天的日期: yyyyMMdd
     */
    public static String getDate(int dateLength) {
        Date date = new Date(); // 取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, - dateLength); // 把日期往后增加一天.正数往后推,负数往前移动
        date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_06);
        return formatter.format(date);
    }

    /**
     * 获取前 dateLength 天的日期: format
     *
     * @param format     日期格式
     * @param dateLength 获取当前日期之前传负值，获取当前日期之后传正值
     * @return 指定日期格式
     */
    public static String getDateFormat(String format, int dateLength) {
        Date date = new Date(); // 取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, dateLength);
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    /**
     * 获取前 dateLength 天的日期: yyyyMMdd
     */
    public static String getDate(String dateStr, int dateLength) {
        Date date = parse(FORMAT_06, dateStr); // 取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, - dateLength); // 把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_06);
        return formatter.format(date);
    }

    /**
     * date2比date1多的天数
     */
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) {  //同一年
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {   //闰年
                    timeDistance += 366;
                } else {   //不是闰年
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else {  //不同年
            return day2 - day1;
        }
    }
}
