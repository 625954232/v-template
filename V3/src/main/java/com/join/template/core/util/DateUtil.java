package com.join.template.core.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能描述：日期处理工具类（基于Calendar） 主要功能：日期校验；获取系统当前日期（可自定义系统日期）；判断闰年；获取连个日期之间的天数，月数；
 * 判定日期的前后；将字符串转换为Date或Calendar等... 日期格式默认：yyyyMMdd
 *
 * @author
 * @date 2008-11-21
 * @see null @修改日志：1.0
 */
public class DateUtil extends Object {

    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public final static String DATE_PATTERN = "yyyy-MM-dd";

    public final static String TIME_PATTERN = "HH:mm:ss";

    /**
     * 当前操作系统日期 Calendar.
     */
    private static Calendar calendar = new GregorianCalendar(TimeZone.getDefault());

    /**
     * yyyy
     */
    public final static SimpleDateFormat DATE_Y = new SimpleDateFormat("yyyy");
    /**
     * yyyy-MM
     */
    public final static SimpleDateFormat DATE_Y_M = new SimpleDateFormat("yyyy-MM");
    /**
     * yyyy-MM-dd
     */
    public final static SimpleDateFormat DATE_Y_M_D = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * yyyyMM
     */
    public final static SimpleDateFormat DATE_YM = new SimpleDateFormat("yyyyMM");
    /**
     * yyyyMMdd
     */
    public final static SimpleDateFormat DATE_YMD = new SimpleDateFormat("yyyyMMdd");

    /**
     * yyyyMMdd
     */
    public final static SimpleDateFormat TIME_YMDH = new SimpleDateFormat("yyyyMMddHH");
    /**
     * yyyyMMddHH
     */
    public final static SimpleDateFormat TIME_YMDHM = new SimpleDateFormat("yyyyMMddHHmm");
    /**
     * yyyyMMddHHmmss
     */
    public final static SimpleDateFormat TIME_YMDHMS = new SimpleDateFormat("yyyyMMddHHmmss");
    /**
     * yyyyMMddHHmmssSSS
     */
    public final static SimpleDateFormat TIME_YMDHMSS = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    /**
     * yyyy-MM-dd HH
     */
    public final static SimpleDateFormat TIME_Y_M_D_H = new SimpleDateFormat("yyyy-MM-dd HH");
    /**
     * yyyy-MM-dd HH:mm
     */
    public final static SimpleDateFormat TIME_Y_M_D_H_M = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public final static SimpleDateFormat TIME_Y_M_D_H_M_S = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * yyyy-MM-dd HH:mm:ss:SSS
     */
    public final static SimpleDateFormat TIME_Y_M_D_H_M_S_S = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
    /**
     * HH
     */
    public final static SimpleDateFormat TIME_H = new SimpleDateFormat("HH");
    /**
     * HH:mm
     */
    public final static SimpleDateFormat TIME_H_M = new SimpleDateFormat("HH:mm");
    /**
     * HH:mm:ss
     */
    public final static SimpleDateFormat TIME_H_M_S = new SimpleDateFormat("HH:mm:ss");
    /**
     * HH:mm:ss:SSS
     */
    public final static SimpleDateFormat TIME_H_M_S_S = new SimpleDateFormat("HH:mm:ss:SSS");
    /**
     * yyyy-MM-dd hh24:mi:ss
     */
    public final static SimpleDateFormat TIME_Y_M_D_H24_M_S = new SimpleDateFormat("yyyy-MM-dd H24:mm:ss");
    /**
     * 年
     */
    private static int year = 0;
    /**
     * 月
     */
    private static int month = 0;
    /**
     * 日
     */
    private static int day = 0;
    /**
     * 时
     */
    private static int hour = 0;
    /**
     * 分
     */
    private static int minute = 0;
    /**
     * 秒
     */
    private static int second = 0;

    /**
     * 静态初始化（默认系统当前日期和时间）
     */
    static {
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
    }

    /**
     * 构造方法
     */
    public DateUtil() {
        // Do Nothing
    }

    /**
     * 判断当前系统时间是否是休息日(周六、周日)
     *
     * @return
     */
    public static boolean isDayOff() {
        LocalDateTime now = LocalDateTime.now();
        DayOfWeek dayOfWeek = now.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    /**
     * 判断传入时间是否是休息日(周六、周日)
     *
     * @param localDateTime
     * @return
     */
    public static boolean isDayOff(LocalDateTime localDateTime) {
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    /**
     * LocalDateTime 转 Date
     *
     * @param time
     * @return
     */
    public static Date localDateTime2Date(LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date 转 LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * 功能描述：自定义系统时间。（谨慎使用）
     *
     * @param strdate 自定义日期字符串，格式：yyyymmdd
     * @author wangshanfang
     * @date 2008-11-21 @修改日志：1.0
     */
    public static void setSysDate(String strdate) {
        if (isDateStr(strdate)) {
            calendar = parseCalendar(strdate);

            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH) + 1;
            day = calendar.get(Calendar.DAY_OF_MONTH);
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            minute = calendar.get(Calendar.MINUTE);
            second = calendar.get(Calendar.SECOND);
        }
    }

    /**
     * 功能描述： 初始化系统日期(当前系统日期)调用setSysDate()后会用到次方法重新初始化系统日期时间 为当前日期时间
     *
     * @author wangshanfang
     * @date 2008-11-21 @修改日志：1.0
     */
    public static void initSys() {
        calendar = new GregorianCalendar(TimeZone.getDefault());

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
    }

    /**
     * 功能描述：获取系统当前日期---年
     *
     * @return int 年
     * @author wangshanfang
     * @date 2008-11-21 @修改日志：1.0
     */
    public static int getYear() {
        return year;
    }

    /**
     * 功能描述：获取系统当前日期---年
     *
     * @return String 年
     * @author wangshanfang
     * @date 2008-11-21 @修改日志：1.0
     */
    public static String getStrYear() {
        return String.valueOf(year);
    }

    /**
     * 功能描述：获取系统当前日期---月
     *
     * @return int 月
     * @author wangshanfang
     * @date 2008-11-21 @修改日志：1.0
     */
    public static int getMonth() {
        return month;
    }

    /**
     * 获取年份
     *
     * @param format
     * @param strDate
     * @return
     */
    public static int getYear(SimpleDateFormat format, String strDate) {
        Date startDate;
        int intYear = 0;
        try {
            startDate = format.parse(strDate);
            Calendar starCal = Calendar.getInstance();
            starCal.setTime(startDate);
            intYear = starCal.get(Calendar.YEAR);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return intYear;
    }

    /**
     * 获取月份
     *
     * @param format
     * @param strDate
     * @return
     */
    public static int getMonth(SimpleDateFormat format, String strDate) {
        Date startDate;
        int intMonth = 0;
        try {
            startDate = format.parse(strDate);
            Calendar starCal = Calendar.getInstance();
            starCal.setTime(startDate);
            intMonth = starCal.get(Calendar.MONTH) + 1;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return intMonth;
    }

    /**
     * 方法描述：将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate 日期 YYYYMMDD
     * @return Date
     * @author XieZhenGuo
     * @date 2013-1-5 上午11:36:36
     */
    public static String shotToDate(String strDate) {
        String strtodate = strDate.substring(0, 4) + "-" + strDate.substring(4, 6) + "-" + strDate.substring(6);
        return strtodate;
    }

    /**
     * 方法描述： 将短时间格式字符串转换为时间 HH:mm:ss
     *
     * @param strTime 时间 HHmmss
     * @return String
     * @author XieZhenGuo
     * @date 2013-1-5 上午11:44:01
     */
    public static String shotToTime(String strTime) {
        String strtodate = strTime.substring(0, 2) + ":" + strTime.substring(2, 4) + ":" + strTime.substring(4);
        return strtodate;
    }

    /**
     * 功能描述：获取系统当前日期---月
     *
     * @return String 月
     * @author wangshanfang
     * @date 2008-11-21 @修改日志：1.0
     */
    public static String getStrMonth() {
        return month >= 10 ? String.valueOf(month) : "0" + String.valueOf(month);
    }

    /**
     * 功能描述：获取系统当前日期---日
     *
     * @return int 日
     * @author wangshanfang
     * @date 2008-11-21 @修改日志：1.0
     */
    public static int getDay() {
        return day;
    }

    /**
     * 功能描述：获取系统当前日期---日
     *
     * @return String 日
     * @author wangshanfang
     * @date 2008-11-21 @修改日志：1.0
     */
    public static String getStrDay() {
        return day < 10 ? "0" + String.valueOf(day) : String.valueOf(day);
    }

    /**
     * 功能描述：获取系统时间--小时
     *
     * @return int
     * @author wangshanfang
     * @date 2008-11-24 @修改日志：1.0
     */
    public static int getHour() {
        return hour;
    }

    /**
     * 功能描述：获取系统时间--分钟
     *
     * @return int
     * @author wangshanfang
     * @date 2008-11-24 @修改日志：
     */
    public static int getMinute() {
        return minute;
    }

    /**
     * 功能描述：获取系统时间--秒
     *
     * @return int
     * @author wangshanfang
     * @date 2008-11-24 @修改日志：
     */
    public static int getSecond() {
        return second;
    }

    /**
     * 功能描述：获取系统当前日期---年月日 （格式：yyyymmdd）
     *
     * @return String 年月日
     * @author wangshanfang
     * @date 2008-11-21 @修改日志：1.0
     */
    public static String getDate(SimpleDateFormat format) {
        Calendar calendar1 = new GregorianCalendar(TimeZone.getDefault());
        Date date = calendar1.getTime();
        return format.format(date);
    }

    public static String getDate(SimpleDateFormat format, Date date) {
        return format.format(date);
    }

    public static Date localToDate(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }


    /**
     * 功能描述：判断给定日期（格式yyyymmdd）是否在系统日期之前，是（或等于）：true，否：false
     *
     * @param strdate 给定日期
     * @return boolean
     * @author wangshanfang
     * @date 2008-11-21 @修改日志：1.0
     */
    public static boolean isBefore(String strdate) {
        Calendar cal = parseCalendar(strdate);
        return cal.before(calendar);
    }

    /**
     * 功能描述：判断给定的两个日期的前后。strdate1在strdate2之前（或同一天），返回true，反之，false
     *
     * @param strdate1 日期1
     * @param strdate2 日期2
     * @return boolean
     * @author wangshanfang
     * @date 2008-11-25 @修改日志：1.0
     */
    public static boolean isBefore(String strdate1, String strdate2) {
        Calendar cal1 = parseCalendar(strdate1);
        Calendar cal2 = parseCalendar(strdate2);
        return cal1.before(cal2);
    }

    /**
     * 功能描述：计算在当前系统日期增加或减少 n 天后的日期
     *
     * @param days 增加或减少的天数，正数增加，反之减少
     * @author wangshanfang
     * @date 2008-11-24 @修改日志：
     */
    public static String addByDay(SimpleDateFormat format, int days) {
        Calendar calendar1 = new GregorianCalendar(TimeZone.getDefault());
        calendar1.add(Calendar.DATE, days);
        Date date = calendar1.getTime();
        return format.format(date);
    }

    /**
     * 功能描述：计算在给定的日期加上或减去 n 天后的日期
     *
     * @param datestr 给定的日期
     * @param days    正数增加，反之减少
     * @return String
     * @author wangshanfang
     * @date 2008-11-24 @修改日志：
     */
    public static String addByDay(SimpleDateFormat format, String datestr, int days) {
        Calendar cal = parseCalendar(datestr);
        cal.add(Calendar.DATE, days);
        Date date = cal.getTime();
        return format.format(date);
    }

    /**
     * 功能描述：计算在给定的秒数加上或减去 n 秒后的秒数
     *
     * @param format
     * @param datestr
     * @param seconds
     * @return
     */
    public static String addBySecond(SimpleDateFormat format, String datestr, int seconds) {
        Calendar cal = parseCalendar(datestr);
        cal.add(Calendar.SECOND, seconds);
        Date date = cal.getTime();
        return format.format(date);
    }

    /**
     * 功能描述：在给定日期上加月加天 后的日期
     *
     * @param datestr 合同起始日
     * @param months  增加月
     * @param days    增加天
     * @return
     * @author dhcc lizhiyu
     * @date Nov 25, 2009 @修改日志：
     */
    public static String addByMonth(SimpleDateFormat format, String datestr, int months, int days) {
        Calendar cal = parseCalendar(datestr);
        cal.add(Calendar.MONTH, months);
        cal.add(Calendar.DATE, days);
        Date date = cal.getTime();
        return format.format(date);
    }

    /**
     * 功能描述：获得给定日期与系统当前日期之间的天数
     *
     * @param strdate 给定的日期字符串
     * @return long 天数
     * @author wangshanfang
     * @date 2008-11-21 @修改日志：1.0
     */
    public static long getDays(SimpleDateFormat format, String strdate) {
        Calendar cal = parseCalendar(strdate);
        Calendar cal1 = parseCalendar(getDate(format));
        long millis = Math.abs(cal.getTimeInMillis() - cal1.getTimeInMillis());
        return millis / (24L * 60L * 60L * 1000L);
    }

    /**
     * 功能描述：获得给定的两个日期之间相差的天数（日期不分前后）
     *
     * @param fromdate 日期字符串 格式：yyyymmdd
     * @param todate   日期字符串 格式：yyyymmdd
     * @return long
     * @author wangshanfang
     * @date 2008-11-21 @修改日志：1.0
     */
    public static long getDaysBetween(String fromdate, String todate) {
        Calendar from = parseCalendar(fromdate);
        Calendar to = parseCalendar(todate);
        long millis = Math.abs(from.getTimeInMillis() - to.getTimeInMillis());
        return millis / (24L * 60L * 60L * 1000L);
    }

    /**
     * 功能描述：获得给定日期与系统当前日期之间的月数，不记天数
     *
     * @param strdate 给定的日期字符串
     * @return long 月数
     * @author wangshanfang
     * @date 2008-11-21
     * @修改日志：待定
     */
    private static long getMonths(String strdate) {
        long months = getMonth() - Integer.parseInt(strdate.substring(4, 6));
        long years = getYear() - Integer.parseInt(strdate.substring(0, 4));
        if (!isBefore(strdate)) {
            months = -months;
            years = -years;
        }
        if (months >= 0) {
            return years * 12 + months;
        } else {
            return (years - 1) * 12 + months + 12;
        }
    }

    /**
     * 功能描述：获得给定日期与系统当前日期之间的月数和天数
     *
     * @param strdate 给定的日期字符串
     * @return long[] 下标0月数，1天数
     * @author wangshanfang
     * @date 2008-11-21
     * @修改日志：待定
     */
    public static long[] getMonthsAndDays(SimpleDateFormat format, String strdate) {
        long m = getMonths(strdate);
        int d = getDay() - Integer.parseInt(strdate.substring(6, 8));
        String date = "";
        if (!isBefore(strdate)) {
            d = -d;
            date = strdate;
        } else {
            date = getDate(format);
        }
        while (d < 0) {
            m--;
            d += getDaysOfMonth(date);
        }
        long[] md = {m, d};
        return md;
    }

    /**
     * 功能描述：获得给定两个日期之间的月数和天数
     *
     * @param strdate1
     * @param strdate2
     * @return long[] 下标0月数，1天数
     * @author wangshanfang
     * @date 2008-11-25 @修改日志：
     */
    public static long[] getMonthsAndDays(SimpleDateFormat format, String strdate1, String strdate2) {
        long[] md = new long[2];
        try {
            int monthnum = getMonths(format, strdate1, strdate2);
            String tempEndDate = addByMonth(format, strdate1, monthnum, 0);
            Long days = getDays(format, tempEndDate, strdate2);
            md[0] = monthnum;
            md[1] = days;
            tempEndDate = null;
            days = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md;
    }

    /**
     * 功能描述：判断字符串是否可以转换为日期型 是：true，否：false
     *
     * @param strdate 预转换字符串
     * @return boolean
     * @author wangshanfang
     * @date 2008-11-21 @修改日志：1.0
     */
    public static boolean isDateStr(String strdate) {
        if (strdate.length() != 8) {
            return false;
        }

        String reg = "^(\\d{4})((0([1-9]{1}))|(1[012]))((0[1-9]{1})|([1-2]([0-9]{1}))|(3[0|1]))$";

        if (Pattern.matches(reg, strdate)) {
            reg = null;
            return getDaysOfMonth(strdate) >= Integer.parseInt(strdate.substring(6, 8));
        } else {
            return false;
        }
    }

    /**
     * 功能描述：判断是否是闰年（年限1000--9999）是：true，否：false
     *
     * @param strdate 预判断年 格式yyyymmdd 或 yyyy
     * @return boolean
     * @author wangshanfang
     * @date 2008-11-21 @修改日志：1.0
     */
    public static boolean isLeapYear(String strdate) {
        int y = Integer.parseInt(strdate.substring(0, 4));
        if (y <= 999) {
            return false;
        }
        if (y % 400 == 0 || (y % 4 == 0 && y % 100 != 0)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 功能描述：获取某一月份的天数
     *
     * @param strdate 日期 格式：yyyymmdd 或 yyyymm
     * @return int
     * @author wangshanfang
     * @date 2008-11-21 @修改日志：1.0
     */
    public static int getDaysOfMonth(String strdate) {
        int m = Integer.parseInt(strdate.substring(4, 6));
        switch (m) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (isLeapYear(strdate)) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return 0;
        }
    }

    /**
     * 功能描述：把字符串转换为Calendar
     *
     * @param strdate 预转换的字符串
     * @return Calendar
     * @author wangshanfang
     * @date 2008-11-21 @修改日志：1.0
     */
    public static Calendar parseCalendar(String strdate) {
        if (isDateStr(strdate)) {
            int year = Integer.parseInt(strdate.substring(0, 4));
            int month = Integer.parseInt(strdate.substring(4, 6)) - 1;
            int day = Integer.parseInt(strdate.substring(6, 8));
            return new GregorianCalendar(year, month, day);
        } else {
            return null;
        }
    }

    /**
     * 功能描述：将字符串转换为Date型日期 日期格式yyyymmdd
     *
     * @param strdate 预转换的字符串
     * @return Date
     * @author wangshanfang
     * @date 2008-11-21 @修改日志：1.0
     */
    public static Date parseDate(SimpleDateFormat format, String strdate) {
        Date d = null;
        try {
            d = format.parse(strdate);
        } catch (Exception pe) {
            pe.printStackTrace();
        }
        return d;
    }

    /**
     * 功能描述：将字符串转换为 LocalDateTime 型日期 字符串格式为:yyyy-mm-dd
     *
     * @param strdate 预转换的字符串
     * @return Date
     * @author wangshanfang
     * @date 2008-11-21 @修改日志：1.0
     */
    public static LocalDateTime parseDate(String strdate) {
        Date date = null;
        LocalDateTime localDateTime = null;
        try {
            date = DATE_Y_M_D.parse(strdate);
            localDateTime = date2LocalDateTime(date);
        } catch (Exception pe) {
            pe.printStackTrace();
        }
        return localDateTime;
    }

    /**
     * 根据指定类型计算两个日期相差的时间<br>
     * eg. dateDiff(birth, today, Calendar.MONTH) 孩子的月龄
     *
     * @param sDate    开始时间
     * @param eDate    结束时间
     * @param diffType 日期类型
     * @return 根据 diffType计算的 eDate - sDate 时差
     */
    public static Long dateDiff(Date sDate, Date eDate, int diffType) {
        Calendar calst = Calendar.getInstance();
        Calendar caled = Calendar.getInstance();

        calst.setTime(sDate);
        caled.setTime(eDate);

        long diffMill = caled.getTime().getTime() - calst.getTime().getTime();
        long rtn = 0;
        switch (diffType) {
            case Calendar.MILLISECOND:
                rtn = diffMill;
                break;
            case Calendar.SECOND:
                rtn = diffMill / 1000;
                break;
            case Calendar.MINUTE:
                rtn = diffMill / 1000 / 60;
                break;
            case Calendar.HOUR:
                rtn = diffMill / 1000 / 3600;
                break;
            case Calendar.DATE:
                rtn = diffMill / 1000 / 60 / 60 / 24;
                break;
            case Calendar.MONTH:
                rtn = diffMill / 1000 / 60 / 60 / 24 / 12;
                break;
            case Calendar.YEAR:
                rtn = diffMill / 1000 / 60 / 60 / 24 / 365;
                break;
        }
        return rtn;
    }

    /**
     * 功能描述：查询下几个月的今天
     *
     * @param date 输入的期限
     * @param val  要查询第几个月后的今天的日期
     * @return 下几个月日期
     * @author dhcc gouqifeng
     * @date Nov 2, 2009 @修改日志：
     */
    public static String getRepayDay(String date, int val) {

        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(4, 6));
        int day = Integer.parseInt(date.substring(6, 8));

        String riqi = "";
        int reapyMonth = 0;
        if ((month + val) % 12 == 0) { // 如 月加上输入的月取模==0 则把初始化的月 赋值=12
            reapyMonth = 12;
        } else { // 否则把除以12 的余数赋值个月参数上
            reapyMonth = (month + val) % 12;
        }

        year += (month + val - 1) / 12; // 原先的月数+输入的要查询的月数 -1/12 如果和 是13 年加1
        if (reapyMonth <= 9) {
            riqi = year + "0" + reapyMonth + date.substring(6, 8);
        } else {
            riqi = year + "" + reapyMonth + date.substring(6, 8);
        }

        if (getDaysOfMonth(riqi) < day) { // 查询日期的日 < 当期日期的日 该日期 如 20090130 的
            // 下个月的期限是多少 ,日应该 是28, 因为不是闰年
            riqi = riqi.substring(0, 6) + DateUtil.getDaysOfMonth(riqi);// 这样
            // 就截取
            // 对应的日期加上
            // 该月应该有的日期是多少就对了,2,4,6,8
            // 等
            // 小月的日期就对了
        }

        return riqi;
    }

    /**
     * @名称 isBetween
     * @描述 判断是否在开始日期和结束日期之间
     * @时间 Mar 15, 2011 11:47:58 AM
     * @参数 begin 开始日期 end 结束日期 betweenValue 中间值 DateFormat 日期格式 boundaryValue
     * 是否包括边界值
     */
    public static boolean isBetween(SimpleDateFormat dateFormat, String begin, String end, String betweenValue,
                                    boolean boundaryValue) {
        boolean flag = false;
        try {
            Date beginDate = dateFormat.parse(begin);
            Date endDate = dateFormat.parse(end);
            Date betweenDate = dateFormat.parse(betweenValue);
            if (betweenDate.after(beginDate) && betweenDate.before(endDate)) {
                flag = true;
            }
            if (boundaryValue) {
                if (betweenDate.compareTo(beginDate) == 0) {
                    flag = true;
                }
                if (betweenDate.compareTo(endDate) == 0) {
                    flag = true;
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * @param tempdate 需要操作的日期变量
     * @param days     需要添加几天，正数表示添加几天，负数表示减少几天
     * @param format   日期格式
     * @名称 addDay
     * @描述 给一个日期添加或减少几天
     * @时间 Mar 16, 2011 9:29:34 AM
     */
    public static String addDay(SimpleDateFormat dateFormat, String tempdate, int days, String format) {
        int year = Integer.parseInt(tempdate.substring(0, 4));
        int month = 0;
        int day = 0;
        String s_month = tempdate.substring(4, 6);
        String s_day = tempdate.substring(6, 8);
        if ("0".equals(s_month.substring(0, 1))) {
            month = Integer.parseInt(tempdate.substring(5, 6));
        } else {
            month = Integer.parseInt(tempdate.substring(4, 6));
        }
        if ("0".equals(s_day.substring(0, 1))) {
            day = Integer.parseInt(tempdate.substring(7, 8));
        } else {
            day = Integer.parseInt(tempdate.substring(6, 8));
        }

        GregorianCalendar firstFlight = new GregorianCalendar(year, month - 1, day);

        Date date = firstFlight.getTime();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return dateFormat.format(cal.getTime());
    }

    /**
     * @param begin  开始日期
     * @param end    结束日期
     * @param format 日期格式
     * @名称 getDays
     * @功能 如果结束日期小余开始日期返回-1 相等返回0否则返回两个日期之间的天数
     * @作者 乾之轩
     * @时间 Mar 18, 2011 8:15:42 PM
     */
    public static long getDays(SimpleDateFormat format, String begin, String end) {
        long datanumber = 0;
        long l_end;
        long l_begin;
        try {
            l_end = format.parse(end).getTime();
            l_begin = format.parse(begin).getTime();
            long temp = l_end - l_begin;
            datanumber = temp / (1000L * 60L * 60L * 24L);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (datanumber < 0) {
            datanumber = -1;
        }
        return datanumber;
    }

    /**
     * @param begin  开始日期
     * @param end    结束日期
     * @param format 日期格式
     * @return boolean
     * @名称 isAfter
     * @功能 判断end是不是在begin之后.是返回true不是返回false
     * @作者 乾之轩
     * @时间 Mar 21, 2011 11:03:13 AM
     */
    public static boolean isAfter(String begin, String end, String format) {
        boolean flag = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            Date beginDate = dateFormat.parse(begin);
            Date endDate = dateFormat.parse(end);
            flag = endDate.after(beginDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 方法描述： begin > end 返回 -1，begin = end 返回 0，begin < end 返回 1.
     *
     * @param begin
     * @param end
     * @param format
     * @return int
     * @author luanhaowei
     * @date 2012-6-5 下午07:27:04
     */
    public static int compareTo(SimpleDateFormat format, String begin, String end) {
        int flag = 0;
        try {
            Date beginDate = format.parse(begin);
            Date endDate = format.parse(end);
            flag = endDate.compareTo(beginDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * @param year 年
     * @return boolean
     * @名称 getDaysOfYear
     * @功能 获得一年的天数
     * @作者 乾之轩
     * @时间 Mar 21, 2011 11:03:13 AM
     */
    public static String getDaysOfYear(String year) {
        if (isLeapYear(year)) {
            return "366";
        } else {
            return "365";
        }

    }

    /**
     * 验证日期格式是否符合xxxx-xx-xx这种格式（只验证格式，不验证日期是否正确）
     *
     * @param date 日期字符串
     * @return 符合：true 不符合：false
     * @author yxdong
     */
    public static boolean testDate(String date) {
        boolean isDate = false;
        Pattern pattern = Pattern.compile("^\\d{4}(\\-)\\d{2}(\\-)\\d{2}$");
        Matcher matcher = pattern.matcher(date);
        isDate = matcher.matches();
        return isDate;
    }

    /**
     * @名称 isFullMonth
     * @描述 判断2个日期之间的间隔是不是整月 如2011-01-02和2011-03-02是整月 2011-12-31和2011-4-30 是整月
     * @作者 乾之轩
     * @时间 Dec 1, 2011 3:39:09 PM
     */
    public static boolean isFullMonth(SimpleDateFormat format, String begin, String end) {
        return getMonthsAndDays(format, begin, end)[1] > 0 ? false : true;
    }

    /**
     * @名称 isLastDayOfMonth
     * @描述 判断两个日期是否都是月末
     * @参数 @param begin
     * @参数 @param end
     * @参数 @param format
     * @参数 @return
     * @返回值 boolean
     * @作者 luanhaowei
     * @时间 2012-4-13 上午11:24:48
     */
    public static boolean isLastDayOfMonth(SimpleDateFormat format, String begin, String end) {
        boolean result = false;
        if (isLastDayOfMonth(format, begin) && isLastDayOfMonth(format, end)) {
            result = true;
        }
        return result;
    }

    /**
     * @名称 getMonth
     * @描述 判断2个日期相差的月数
     * @作者 乾之轩
     * @时间 Dec 1, 2011 4:57:49 PM
     */
    public static int getMonth(SimpleDateFormat format, String begin1, String end1) throws ParseException {
        Date s = format.parse(begin1);
        Date e = format.parse(end1);
        if (s.after(e)) {
            Date t = s;
            s = e;
            e = t;
        }
        Calendar start = Calendar.getInstance();
        start.setTime(s);
        Calendar end = Calendar.getInstance();
        end.setTime(e);
        Calendar temp = Calendar.getInstance();
        temp.setTime(e);
        temp.add(Calendar.DATE, 1);

        int y = end.get(Calendar.YEAR) - start.get(Calendar.YEAR);
        int m = end.get(Calendar.MONTH) - start.get(Calendar.MONTH);

        if ((start.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) == 1)) {// 前后都不破月
            return y * 12 + m + 1;
        } else if ((start.get(Calendar.DATE) != 1) && (temp.get(Calendar.DATE) == 1)) {// 前破月后不破月
            return y * 12 + m;
        } else if ((start.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) != 1)) {// 前不破月后破月
            return y * 12 + m;
        } else {// 前破月后破月
            return (y * 12 + m - 1) < 0 ? 0 : (y * 12 + m - 1);
        }
    }

    /**
     * @名称 getDay
     * @描述 判断2个日期相差的天数数
     * @作者 乾之轩
     * @时间 Dec 1, 2011 4:58:29 PM
     */
    public static int getDay(SimpleDateFormat format, String begin1, String end1) throws ParseException {
        Date s = format.parse(begin1);
        Date e = format.parse(end1);

        if (s.after(e)) {
            Date t = s;
            s = e;
            e = t;
        }
        Calendar start = Calendar.getInstance();
        start.setTime(s);
        Calendar end = Calendar.getInstance();
        end.setTime(e);
        Calendar temp = Calendar.getInstance();
        temp.setTime(e);
        temp.add(Calendar.DATE, 1);

        if ((start.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) == 1)) {// 前后都不破月
            return 0;
        } else if ((start.get(Calendar.DATE) != 1) && (temp.get(Calendar.DATE) == 1)) {// 前破月后不破月
            return getDayP(start);
        } else if ((start.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) != 1)) {// 前不破月后破月
            return end.get(Calendar.DATE);
        } else {// 前破月后破月
            if (start.get(Calendar.MONTH) == end.get(Calendar.MONTH)
                    && start.get(Calendar.YEAR) == end.get(Calendar.YEAR)) {
                return end.get(Calendar.DATE) - start.get(Calendar.DATE) + 1;
            } else {
                return getDayP(start) + end.get(Calendar.DATE);
            }
        }
    }

    public static int getDayP(Calendar s) {
        int d;
        if (s.get(Calendar.MONTH) == 1 && s.get(Calendar.YEAR) % 4 == 0 && s.get(Calendar.YEAR) % 100 != 0) {// 闰年2月
            d = 29;
        } else {
            Map<Integer, Integer> m = new HashMap<Integer, Integer>();
            m.clear();
            m.put(1, 31);
            m.put(3, 31);
            m.put(5, 31);
            m.put(7, 31);
            m.put(8, 31);
            m.put(10, 31);
            m.put(12, 31);
            m.put(4, 30);
            m.put(6, 30);
            m.put(9, 30);
            m.put(11, 30);
            m.put(2, 28);
            d = m.get(s.get(Calendar.MONTH) + 1);
        }
        return d - s.get(Calendar.DATE);
    }

    @SuppressWarnings("static-access")
    static String GetSysDate(SimpleDateFormat format, String StrDate, int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(format.parse((StrDate), new ParsePosition(0)));

        if (day != 0) {
            cal.add(cal.DATE, day);
        }
        if (month != 0) {
            cal.add(cal.MONTH, month);
        }
        if (year != 0) {
            cal.add(cal.YEAR, year);

        }
        return format.format(cal.getTime());
    }

    /**
     * 根据开始时间和结束时间获取相差月份
     *
     * @param format
     * @param begin
     * @param end
     * @return
     */
    public static int getMonths(SimpleDateFormat format, String begin, String end) {
        int iMonth = 0;
        int flag = 0;
        try {
            Date date1 = format.parse(begin);
            Date date2 = format.parse(end);
            Calendar objCalendarDate1 = Calendar.getInstance();
            objCalendarDate1.setTime(date1);

            Calendar objCalendarDate2 = Calendar.getInstance();
            objCalendarDate2.setTime(date2);

            if (objCalendarDate2.equals(objCalendarDate1))
                return 0;
            if (objCalendarDate1.after(objCalendarDate2)) {
                Calendar temp = objCalendarDate1;
                objCalendarDate1 = objCalendarDate2;
                objCalendarDate2 = temp;
            }
            if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) < objCalendarDate1.get(Calendar.DAY_OF_MONTH))
                flag = 1;
            if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) == 30 && objCalendarDate1.get(Calendar.DAY_OF_MONTH) == 31
                    && objCalendarDate2.get(Calendar.YEAR) >= objCalendarDate1.get(Calendar.YEAR))
                flag = 0;// 草川禾 20110120 添加 处理特殊情况 此处是唯一一种
            // 即结束日期是30天，开始日期日31时计划日期[)情况
            if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) == 28 && objCalendarDate1.get(Calendar.DAY_OF_MONTH) == 31
                    && objCalendarDate2.get(Calendar.YEAR) >= objCalendarDate1.get(Calendar.YEAR))
                flag = 0;// 草川禾 20110120 添加 处理特殊情况 此处是唯一一种
            // 即结束日期是30天，开始日期日31时计划日期[)情况
            if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) == 28 && objCalendarDate1.get(Calendar.DAY_OF_MONTH) == 29
                    && objCalendarDate2.get(Calendar.YEAR) >= objCalendarDate1.get(Calendar.YEAR))
                flag = 0;// 处理getMonthsAndDays("20120229", "20130228")这种情况
            if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) == 29 && objCalendarDate1.get(Calendar.DAY_OF_MONTH) == 31
                    && objCalendarDate2.get(Calendar.YEAR) >= objCalendarDate1.get(Calendar.YEAR))
                flag = 0;// 草川禾 20110120 添加 处理特殊情况 此处是唯一一种
            // 即结束日期是30天，开始日期日31时计划日期[)情况
            if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1.get(Calendar.YEAR))
                iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1.get(Calendar.YEAR)) * 12
                        + objCalendarDate2.get(Calendar.MONTH) - flag) - objCalendarDate1.get(Calendar.MONTH);
            else
                iMonth = objCalendarDate2.get(Calendar.MONTH) - objCalendarDate1.get(Calendar.MONTH) - flag;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return iMonth;
    }

    /**
     * @名称 isLastDayOfMonth
     * @描述 判断一个日期是否该月的月末
     * @参数 @param beginDate
     * @参数 @return
     * @返回值 boolean
     * @作者 luanhaowei
     * @时间 2012-4-13 上午11:13:45
     */
    public static boolean isLastDayOfMonth(SimpleDateFormat format, String beginDate) {
        boolean result = false;
        try {
            Date date = format.parse(beginDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
            if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
                result = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 方法描述： 获取下个月份
     *
     * @param monStr
     * @return String
     * @author rjq
     * @date Jan 18, 2014 3:27:05 PM
     */
    public static String getNextMon(String monStr) {
        String result = "";
        int year = Integer.parseInt(monStr.substring(0, 4));
        int mon = Integer.parseInt(monStr.substring(4));

        mon++;
        if (mon == 13) {
            mon = 1;
            year++;
        }

        if (mon > 9) {
            result = year + "" + mon;
        } else {
            result = year + "0" + mon;
        }

        return result;

    }

    /**
     * 方法描述： 获取上个月份
     *
     * @param monStr
     * @return String
     * @author rjq
     * @date Jan 18, 2014 3:27:05 PM
     */
    public static String getPreMon(String monStr) {
        String result = "";
        int year = Integer.parseInt(monStr.substring(0, 4));
        int mon = Integer.parseInt(monStr.substring(4, 6));

        mon--;
        if (mon == 0) {
            mon = 12;
            year--;
        }

        if (mon < 10) {
            result = year + "0" + mon;
        } else {
            result = year + "" + mon;
        }
        return result;

    }

    /**
     * 方法描述： 根据类型和日期返回开始和截止日期
     *
     * @param type    查询类型1.日；2.旬；3.月；4.季;5.半年；6.年
     * @param dateStr 传入的查询日期 20150317 void
     * @author hly
     * @date 2014-10-15 上午10:59:19
     */
    public static String[] getDateArray(SimpleDateFormat format, String type, String dateStr) {
        if (dateStr.length() == 6) {
            dateStr += "01";
        }
        String[] dateArray = new String[2];
        String yearStr = dateStr.substring(0, 4);
        String monthStr = dateStr.substring(4, 6);
        String dayStr = dateStr.substring(6, 8);
        if (type.equals("1")) {// 日
            dateArray[0] = dateStr;
            dateArray[1] = dateStr;
        }
        if (type.equals("2")) {// 计算旬
            int d = Integer.parseInt(dayStr);
            if (d >= 1 && d <= 10) {
                dateArray[0] = yearStr + monthStr + "01";
                dateArray[1] = yearStr + monthStr + "10";
            } else if (d >= 11 && d <= 20) {
                dateArray[0] = yearStr + monthStr + "11";
                dateArray[1] = yearStr + monthStr + "20";
            } else {

                dateArray[0] = yearStr + monthStr + "21";
                int y = Integer.parseInt(yearStr);
                if (Integer.parseInt(monthStr) == 12) {// 如果本月加1等于12月则需推到下一年
                    y = y + 1;
                    monthStr = "01";
                } else {
                    int monVal = Integer.parseInt(monthStr);
                    monVal++;
                    if (monVal > 9) {
                        monthStr = String.valueOf(monVal);
                    } else {
                        monthStr = "0" + monVal;
                    }
                }

                String s = String.valueOf(y) + monthStr + "01";

                String ss = addByDay(format, s, -1);// 下月第一天减去一天为本月一号

                dateArray[1] = ss;
            }
        }
        if (type.equals("3")) {// 月
            int y = Integer.parseInt(yearStr);

            dateArray[0] = yearStr + monthStr + "01";
            if (Integer.parseInt(monthStr) == 12) {// 如果本月加1等于12月则需推到下一年
                y = y + 1;
                monthStr = "01";
            } else {
                monthStr = String.valueOf(Integer.parseInt(monthStr) + 1);

                if (Integer.parseInt(monthStr) < 10) {
                    monthStr = "0" + monthStr;
                }
            }

            String s = String.valueOf(y) + monthStr + "01";
            String ss = addByDay(format, s, -1);// 下月第一天减去一天为本月一号

            dateArray[1] = ss;
        }
        if (type.equals("4")) {// 季
            int m = Integer.parseInt(monthStr);
            if (m >= 1 && m <= 3) {
                dateArray[0] = yearStr + "01" + "01";
                dateArray[1] = yearStr + "03" + "31";
            } else if (m >= 4 && m <= 6) {
                dateArray[0] = yearStr + "04" + "01";
                dateArray[1] = yearStr + "06" + "30";
            } else if (m >= 7 && m <= 9) {
                dateArray[0] = yearStr + "07" + "01";
                dateArray[1] = yearStr + "09" + "30";
            } else if (m >= 10 && m <= 12) {
                dateArray[0] = yearStr + "10" + "01";
                dateArray[1] = yearStr + "12" + "31";
            }
        }
        if (type.equals("5")) {// 半年
            int m = Integer.parseInt(monthStr);
            if (m >= 1 && m <= 6) {
                dateArray[0] = yearStr + "01" + "01";
                dateArray[1] = yearStr + "06" + "30";
            } else if (m >= 7 && m <= 12) {
                dateArray[0] = yearStr + "07" + "01";
                dateArray[1] = yearStr + "12" + "31";
            }
        }
        if (type.equals("6")) {// 年
            dateArray[0] = yearStr + "01" + "01";
            dateArray[1] = yearStr + "12" + "31";
        }
        return dateArray;
    }

    /**
     * 转换日期格式
     *
     * @param datestr       日期
     * @param pattern       需要装换的格式
     * @param targetpattern 目标格式
     * @return
     */
    public static String convertFormat(String datestr, SimpleDateFormat pattern, SimpleDateFormat targetpattern) {
        String t_date = "";
        Date t_ddate = null;
        try {
            t_ddate = pattern.parse(datestr);
            t_date = targetpattern.format(t_ddate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return t_date;
    }


    public static char formatDigit(char sign) {
        if (sign == '0')
            sign = '0';
        if (sign == '1')
            sign = '一';
        if (sign == '2')
            sign = '二';
        if (sign == '3')
            sign = '三';
        if (sign == '4')
            sign = '四';
        if (sign == '5')
            sign = '五';
        if (sign == '6')
            sign = '六';
        if (sign == '7')
            sign = '七';
        if (sign == '8')
            sign = '八';
        if (sign == '9')
            sign = '九';
        return sign;
    }


}