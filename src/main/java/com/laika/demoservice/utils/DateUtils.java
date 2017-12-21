package com.laika.demoservice.utils;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: 会跳舞的机器人
 * @date: 2017/1/13 9:57
 * @description: 日期工具类
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    private static Logger logger = Logger.getLogger(DateUtils.class);

    /**
     * 获取当前日期的前11个月的日期集合（包括当月）
     *
     * @return
     */
    public static List<Date> getBeforeMonth() {
        List<Date> result = new LinkedList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, -11);
        Date beforeDate = calendar.getTime();
        while (beforeDate.compareTo(new Date()) < 0) {
            result.add(beforeDate);
            beforeDate = addMonth(beforeDate, 1);
        }
        return result;
    }

    /**
     * 字符串转日期，支持各种格式的日期
     *
     * @param dateStr
     * @return
     */
    public static Date getDateByStr(String dateStr) {
        SimpleDateFormat formatter = null;
        if (dateStr == null) {
            return null;
        } else if (dateStr.length() == 10) {
            formatter = new SimpleDateFormat("yyyy-MM-dd");
        } else if (dateStr.length() == 16) {
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        } else if (dateStr.length() == 19) {
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else if (dateStr.length() > 19) {
            dateStr = dateStr.substring(0, 19);
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else {
            return null;
        }
        try {
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            logger.error(e);
            return null;
        }
    }

    /**
     * 获取指定日期的年份，如果传null，则默认为当前时间
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取当前时间的年份
     *
     * @return
     */
    public static int getYear() {

        return getYear(new Date());
    }

    /**
     * 获取指定日期的月份，如果传null，则默认为当前时间
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前时间月份
     *
     * @return
     */
    public static int getMonth() {

        return getMonth(new Date());
    }

    /**
     * 获取指定时间的日期
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获取当天的日期
     *
     * @return
     */
    public static int getDay() {

        return getDay(new Date());
    }

    /**
     * 获取当前时间是属于当月的第几周
     *
     * @return
     */
    public static int getWeekOfMonth() {

        return getWeekOfMonth(new Date());
    }


    /**
     * 获取指定时间是属于当月的第几周
     *
     * @param date
     * @return
     */
    public static int getWeekOfMonth(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_MONTH);
    }


    /**
     * 给指定日期加上指定个月
     *
     * @param date
     * @param month
     * @return
     */
    public static Date addMonth(Date date, int month) {
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 获取指定日期0点
     *
     * @param date
     * @return
     */
    public static Date getBeginDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();
    }

    /**
     * 获取指定日期0点
     *
     * @return
     */
    public static Date getBeginDay() {
        return getBeginDay(new Date());
    }

    /**
     * 获取指定日期23:59:59秒日期
     *
     * @param date
     * @return
     */
    public static Date getEndDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        return calendar.getTime();
    }

    /**
     * 获取指定日期23:59:59秒日期
     *
     * @return
     */
    public static Date getEndDay() {
        return getEndDay(new Date());
    }

}