package com.alimama.api.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 *
 * @author  PengWX
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 格式化日期
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        return formatDate(date, pattern, null);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
     * "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的小时
     *
     * @param date
     * @return
     */
    public static long pastHour(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取过去的分钟
     *
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 获取过去的分钟
     *
     * @param date
     * @return
     */
    public static long pastSecond(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / 1000;
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param before
     * @param after
     * @return
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }


    /**
     * 判断时间是否在时间段内
     *
     * @param date      当前时间 yyyy-MM-dd HH:mm:ss
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    public static boolean isInTime(Date date, Date beginDate, Date endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(date);

        String strDateBegin = sdf.format(beginDate);
        String strDateEnd = sdf.format(endDate);
        strDateBegin = strDateBegin.substring(11, 19);
        strDateEnd = strDateEnd.substring(11, 19);

        // 截取当前时间时分秒
        int strDateH = Integer.parseInt(strDate.substring(11, 13));
        int strDateM = Integer.parseInt(strDate.substring(14, 16));
        int strDateS = Integer.parseInt(strDate.substring(17, 19));
        // 截取开始时间时分秒
        int strDateBeginH = Integer.parseInt(strDateBegin.substring(0, 2));
        int strDateBeginM = Integer.parseInt(strDateBegin.substring(3, 5));
        int strDateBeginS = Integer.parseInt(strDateBegin.substring(6, 8));
        // 截取结束时间时分秒
        int strDateEndH = Integer.parseInt(strDateEnd.substring(0, 2));
        int strDateEndM = Integer.parseInt(strDateEnd.substring(3, 5));
        int strDateEndS = Integer.parseInt(strDateEnd.substring(6, 8));
        if ((strDateH >= strDateBeginH && strDateH <= strDateEndH)) {
            // 当前时间小时数在开始时间和结束时间小时数之间
            if (strDateH > strDateBeginH && strDateH < strDateEndH) {
                return true;
                // 当前时间小时数等于开始时间小时数，分钟数在开始和结束之间
            } else if (strDateH == strDateBeginH && strDateM >= strDateBeginM
                    && strDateM <= strDateEndM) {
                return true;
                // 当前时间小时数等于开始时间小时数，分钟数等于开始时间分钟数，秒数在开始和结束之间
            } else if (strDateH == strDateBeginH && strDateM == strDateBeginM
                    && strDateS >= strDateBeginS && strDateS <= strDateEndS) {
                return true;
            }
            // 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数小等于结束时间分钟数
            else if (strDateH >= strDateBeginH && strDateH == strDateEndH
                    && strDateM <= strDateEndM) {
                return true;
                // 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数等于结束时间分钟数，秒数小等于结束时间秒数
            } else if (strDateH >= strDateBeginH && strDateH == strDateEndH
                    && strDateM == strDateEndM && strDateS <= strDateEndS) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 返回开始搜索时间
     * @param date
     * @return if date == null && now == 2017-06-24 10:33:18, then return 2017-05-24 00:00:00
     */
    public static Date getBeginDate(Date date) {
        if (date == null) {
            date = DateUtils.addMonths(new Date(), -1);
        }
        return truncate(date, Calendar.DATE);
    }

    /**
     * 返回结束搜索时间
     * @param date
     * @return if date == null && now == 2017-06-24 10:33:18, then return 2017-06-24 23:59:59
     */
    public static Date getEndDate(Date date) {
        if (date == null) {
            date = new Date();
        }
        date = ceiling(date, Calendar.DATE);
        return new Date(date.getTime() - 1);
    }

    /**
     * 转换日期字符串 比如yyyymmdd 转为 yyyy-mm-dd,目前不支持带时分秒的
     * @param source
     * @param i 第一个要插入symbol的位置
     * @param symbol 要插入的字符 比如'-'
     * @return
     */
    public static String getDIYDateString(String source,int i,String symbol){
        StringBuilder builder = new StringBuilder(source);
        builder.insert(i,symbol);
        builder.insert(i+3,symbol);
        return builder.toString();
    }

    /**
     * 日期格式验证
     * @param str
     * @return
     */
    public static boolean isValidDate(String str) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy-MM-dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * 判断是否为节假日
     * @return
     */
    public static Boolean ifHolidayDay(String url1,Date date) {
        boolean flag = true;
        String  isHoliday = "0";
        BufferedReader in = null;
        StringBuffer sb = new StringBuffer();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        // Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String after14 = sdf.format(calendar.getTime());
        try {
            URL url = new URL(url1 + after14);
            in = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
            String str = null;
            while ((str = in.readLine()) != null) {
                sb.append(str);
            }
            JSONObject json = JSONObject.parseObject(sb.toString());
            String value = json.get("data").toString();
            if (Objects.equals(isHoliday,value)) {
                flag = false;
            }
        } catch (Exception ex) {
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
            }
        }
        return flag;
    }
    /**
     * @param strDate 日期形式的字符串
     * @return 返回yyyy-MM-dd格式的日期
     * @throws ParseException
     */
    public static Date strToDate(String strDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(strDate);
    }

    /**
     * @param strDate   日期形式的字符串
     * @param strFormat 日期格式化的字符串
     * @return 返回要转换的日期格式的日期
     * @throws ParseException
     */
    public static Date strToDate(String strDate, String strFormat)
            throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(strFormat);
        return format.parse(strDate);
    }

    /**
     * @param objDate 日期格式的对象
     * @return 返回yyyy-MM-dd格式的日期
     * @throws Exception
     */
    public static Date objToDate(Object objDate) throws Exception {
        String dateString = objDate.toString();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(dateString);
    }

    /**
     * @param objDate 日期格式的对象
     * @return 返回yyyy-MM-dd HH：mm:ss 格式的日期
     * @throws Exception
     */
    public static Date objToFullDate(Object objDate) throws Exception {
        String dateString = objDate.toString();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.parse(dateString);
    }

    /**
     * @param iDate 日期
     * @return 返回yyyy-MM-dd格式的日期字符串
     */
    public static String format(Date iDate) {
        if (iDate == null) return null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(iDate);
    }

    /**
     * @param iDate     日期
     * @param strFormat 日期格式化的字符串
     * @return 返回格式化的日期字符串
     */
    public static String format(Date iDate, String strFormat) {
        if (iDate == null) return null;
        SimpleDateFormat format = new SimpleDateFormat(strFormat);
        return format.format(iDate);
    }

    /**
     * @param idate     日期
     * @param strFormat 日期格式化的字符串
     * @return 返回格式化的日期
     * @throws ParseException
     */
    public static Date formatDateToDate(Date idate, String strFormat)
            throws ParseException {
        String ddate = null;
        ddate = format(idate);
        return strToDate(ddate, strFormat);
    }

    /**
     * @param idate 日期
     * @return 返回yyyy-MM-dd格式的日期
     * @throws ParseException
     */
    public static Date formatToDefaultDate(Date idate) throws ParseException {
        String ddate = null;
        ddate = format(idate);
        return strToDate(ddate, "yyyy-MM-dd");
    }

    /**
     * 将CST的时间字符串转换成需要的日期格式字符串<br>
     *
     * @param cststr The source to be dealed with.
     *               (exp:Fri Jan 02 00:00:00 CST 2009)
     * @param fmt    The format string
     * @return string or <code>null</code> if the cststr is unpasrseable or is
     * null return null,else return the string.
     * @author HitSnail
     */
    public static String getDateFmtStrFromCST(String cststr, String fmt) {
        if ((null == cststr) || (null == fmt)) {
            return null;
        }
        String str = null;
        SimpleDateFormat sdfy = new SimpleDateFormat(fmt.trim());
        SimpleDateFormat sdf = new SimpleDateFormat(
                "EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.US);
        try {
            str = sdfy.format(sdf.parse(cststr.trim()));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return str;
    }

    /**
     * 获取当日凌晨时间
     */
    public static Date getToday() {
        Calendar cld = Calendar.getInstance();
        cld.set(Calendar.HOUR_OF_DAY, 0);
        cld.set(Calendar.MINUTE, 0);
        cld.set(Calendar.SECOND, 0);
        cld.set(Calendar.MILLISECOND, 0);
        return cld.getTime();
    }

    /**
     * 获取当日结束时刻23:59:59.999
     */
    public static Date getEndOfDay() {
        Calendar cld = Calendar.getInstance();
        cld.set(Calendar.HOUR_OF_DAY, 23);
        cld.set(Calendar.MINUTE, 59);
        cld.set(Calendar.SECOND, 59);
        cld.set(Calendar.MILLISECOND, 999);
        return cld.getTime();
    }

    /**
     * 获取距离现在指定天数的某天的开始时刻00:00:00.000
     *
     * @param interval 间隔
     * @return Date java.util.Date
     */
    public static Date getStartOfDay(int interval) {
        Calendar cld = Calendar.getInstance();
        cld.setTimeInMillis(cld.getTimeInMillis() + interval * 24 * 60 * 60
                * 1000l);
        cld.set(Calendar.HOUR_OF_DAY, 0);
        cld.set(Calendar.MINUTE, 0);
        cld.set(Calendar.SECOND, 0);
        cld.set(Calendar.MILLISECOND, 0);
        return cld.getTime();
    }

    /**
     * 获取某天的开始时刻00:00:00.000
     *
     * @param date 需要获取天内的时间
     * @return Date java.util.Date
     */
    public static Date getStartOfDay(Date date) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        cld.set(Calendar.HOUR_OF_DAY, 0);
        cld.set(Calendar.MINUTE, 0);
        cld.set(Calendar.SECOND, 0);
        cld.set(Calendar.MILLISECOND, 0);
        return cld.getTime();
    }

    /**
     * 获取距离现在指定天数的某天的结束时刻23:59:59.999
     *
     * @param interval 间隔
     * @return Date java.util.Date
     */
    public static Date getEndOfDay(int interval) {
        Calendar cld = Calendar.getInstance();
        cld.setTimeInMillis(cld.getTimeInMillis() + interval * 24 * 60 * 60
                * 1000l);
        cld.set(Calendar.HOUR_OF_DAY, 23);
        cld.set(Calendar.MINUTE, 59);
        cld.set(Calendar.SECOND, 59);
        cld.set(Calendar.MILLISECOND, 999);
        return cld.getTime();
    }

    /**
     * 获取某天的最后时刻23:59:59.999
     *
     * @param date 需要获取天内的时间
     * @return Date java.util.Date
     */
    public static Date getEndOfDay(Date date) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        cld.set(Calendar.HOUR_OF_DAY, 23);
        cld.set(Calendar.MINUTE, 59);
        cld.set(Calendar.SECOND, 59);
        cld.set(Calendar.MILLISECOND, 999);
        return cld.getTime();
    }

    /**
     * 获取距离现在指定星期数的某个星期的开始时刻00:00:00.000
     *
     * @param interval 间隔
     * @return Date java.util.Date
     */
    public static Date getStartOfWeek(int interval) {
        Calendar cld = Calendar.getInstance();
        cld.setTimeInMillis(cld.getTimeInMillis() + interval * 7 * 24 * 60 * 60
                * 1000l);
        cld.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return getStartOfDay(cld.getTime());
    }

    /**
     * 获取指定星期的开始时刻00:00:00.000
     *
     * @param week 一年中的第几周
     * @param year 年份
     * @return Date java.util.Date
     */
    public static Date getStartOfWeek(int year, int week) {
        Calendar cld = Calendar.getInstance();
        cld.set(Calendar.YEAR, year);
        cld.set(Calendar.WEEK_OF_YEAR, week);
        cld.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return getStartOfDay(cld.getTime());
    }

    /**
     * 获取距离指定日期指定星期数的某个星期的开始时刻00:00:00.000
     *
     * @param interval 间隔
     * @param date     日期
     * @return Date java.util.Date
     */
    public static Date getStartOfWeek(Date date, int interval) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        cld.setTimeInMillis(cld.getTimeInMillis() + interval * 7 * 24 * 60 * 60
                * 1000l);
        cld.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return getStartOfDay(cld.getTime());
    }

    /**
     * 获取指定时间所在周的第一天的00:00:00.000
     *
     * @param date 需要获取周的某天
     * @return Date java.util.Date
     */
    public static Date getStartOfWeek(Date date) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        cld.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return getStartOfDay(cld.getTime());
    }

    /**
     * 获取距离现在指定周数的某周的最后时刻23:59:59.999
     *
     * @param interval 间隔
     * @return Date java.util.Date
     */
    public static Date getEndOfWeek(int interval) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(getStartOfWeek(interval + 1));
        return new Date(cld.getTimeInMillis() - 1);
    }

    /**
     * 获取指定周数的最后时刻23:59:59.999
     *
     * @param week 一年中的第几周
     * @param year 年份
     * @return Date java.util.Date
     */
    public static Date getEndOfWeek(int year, int week) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(getStartOfWeek(year, week + 1));
        return new Date(cld.getTimeInMillis() - 1);
    }

    /**
     * 获取距离指定日期指定周数的某周的最后时刻23:59:59.999
     *
     * @param interval 间隔
     * @param date     日期
     * @return Date java.util.Date
     */
    public static Date getEndOfWeek(Date date, int interval) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        cld.setTime(getStartOfWeek(date, interval + 1));
        return new Date(cld.getTimeInMillis() - 1);
    }

    /**
     * 获取指定时间所在周的最后一天的23:59:59.999
     *
     * @param date 需要获取周的某天
     * @return Date java.util.Date
     */
    public static Date getEndOfWeek(Date date) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        cld.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        return getEndOfDay(cld.getTime());
    }

    /**
     * 获取指定时间所在月的第一天的00:00:00.000
     *
     * @param date 需要获取月的某天
     * @return Date java.util.Date
     */
    public static Date getStartOfMonth(Date date) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        cld.set(Calendar.DAY_OF_MONTH, 1);
        return getStartOfDay(cld.getTime());
    }

    /**
     * 获取某年某月的开始时刻00:00:00.000
     *
     * @param year  年份
     * @param month 月份
     * @return Date java.util.Date
     */
    public static Date getStartOfMonth(int year, int month) {
        Calendar cld = Calendar.getInstance();
        cld.set(Calendar.YEAR, year);
        cld.set(Calendar.MONTH, month);
        cld.set(Calendar.DAY_OF_MONTH, 1);
        return getStartOfDay(cld.getTime());
    }

    /**
     * 获取距离现在指定月数的某月的开始时刻00:00:00.000
     *
     * @param interval 间隔
     * @return Date java.util.Date
     */
    public static Date getStartOfMonth(int interval) {
        Calendar cld = Calendar.getInstance();
        int currentMonth = cld.get(Calendar.MONTH); // 这里得到的值是0～11
        cld.add(Calendar.YEAR, ((interval + currentMonth) / 12));
        cld.set(Calendar.MONTH, ((interval + currentMonth) % 12));
        cld.set(Calendar.DAY_OF_MONTH, 1);
        return getStartOfDay(cld.getTime());
    }

    /**
     * 获取指定时间所在月的最后一天的23:59:59.999
     *
     * @param date 需要获取月的某天
     * @return Date java.util.Date
     */
    public static Date getEndOfMonth(Date date) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        int maxDay = cld.getActualMaximum(Calendar.DAY_OF_MONTH);
        cld.set(Calendar.DAY_OF_MONTH, maxDay);
        return getEndOfDay(cld.getTime());
    }

    /**
     * 获取距离现在指定月数的某月的结束时刻23:59:59.999
     *
     * @param interval 间隔
     * @return Date java.util.Date
     */
    public static Date getEndOfMonth(int interval) {
        return new Date(getStartOfMonth(interval + 1).getTime() - 1);
    }

    /**
     * 获取某年某月的结束时刻23:59:59.999
     *
     * @param year  年份
     * @param month 月份
     * @return Date java.util.Date
     */
    public static Date getEndOfMonth(int year, int month) {
        return new Date(getStartOfMonth(year, month + 1).getTime() - 1);
    }

    /**
     * 获取2个时间点之间的月份数
     *
     * @param startDate endDate
     * @return int
     */
    public static int monthsBetween(Date startDate, Date endDate) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(startDate);
        int startMonth = cld.get(Calendar.MONTH);
        int startYear = cld.get(Calendar.YEAR);
        cld.setTime(endDate);
        int endMonth = cld.get(Calendar.MONTH);
        int endYear = cld.get(Calendar.YEAR);
        return (endYear - startYear) * 12 + (endMonth - startMonth);
    }

    /**
     * 获取2个时间点之间的天数
     *
     * @param startDate sendDate
     * @return int
     */
    public static int daysBetween(Date startDate, Date endDate) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(startDate);
        c2.setTime(endDate);
        return daysBetween(c1, c2);
    }

    /**
     * 计算两个日期之间的天数
     *
     * @param early 日期
     * @param late  日期
     * @return
     */
    public static int daysBetween(Calendar early, Calendar late) {
        return (int) (toJulian(late) - toJulian(early));
    }

    /**
     * @param c 日期
     * @return
     */
    public static final float toJulian(Calendar c) {
        int Y = c.get(Calendar.YEAR);
        int M = c.get(Calendar.MONTH);
        int D = c.get(Calendar.DATE);
        int A = Y / 100;
        int B = A / 4;
        int C = 2 - A + B;
        float E = (int) (365.25f * (Y + 4716));
        float F = (int) (30.6001f * (M + 1));
        float JD = (C + D + E + F) - 1524.5f;
        return JD;
    }

    /**
     * 根据出生日期获得年龄
     *
     * @param birthday 生日
     * @return int 年龄
     */
    public static int getAge(Date birthday) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(birthday);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(new Date());
        int age = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        return age < 0 ? 0 : age;
    }

    public static int getGender(String cardNo) {
        if (cardNo == null || (cardNo.length() != 15 && cardNo.length() != 18)) {
            return -1;
        }
        if (cardNo.length() == 15) {
            return cardNo.charAt(cardNo.length() - 1) % 2;
        } else if (cardNo.length() == 18) {
            char x = cardNo.charAt(cardNo.length() - 2);
            return x % 2;
        } else {
            return -1;
        }
    }

    /**
     * 日期相加
     *
     * @param date
     * @param day
     * @return 返回相加后的日期
     */
    public static Date addDate(Date date, int day) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        cld.setTimeInMillis(cld.getTimeInMillis() + ((long) day) * 24 * 3600
                * 1000);
        return cld.getTime();
    }

    public static Date addHour(Date date, int hour) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        cld.setTimeInMillis(cld.getTimeInMillis() + ((long) hour) * 3600 * 1000);
        return cld.getTime();
    }

    public static Date addMinute(Date date, Long minute) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        cld.setTimeInMillis(cld.getTimeInMillis() - minute * 60 * 1000);
        return cld.getTime();
    }


    public static void main(String[] args) {
        System.out.println(addHour(getCurDate(), 1));
    }

    /**
     * 日期相减
     *
     * @param date
     * @param day
     * @return 返回相减后的日期
     */
    public static Date deleteDate(Date date, int day) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        cld.setTimeInMillis(cld.getTimeInMillis() - ((long) day) * 24 * 3600
                * 1000);
        return cld.getTime();
    }

    public static Long dateDiff(Date date1, Date date2) {
        date1 = dateTime2date(date1);
        date2 = dateTime2date(date2);
        long d1 = date1.getTime();
        long d2 = date2.getTime();
        long d = (d1 - d2) / 1000 / 60 / 60 / 24;
        return d;
    }

    /**
     * 截掉日期后面的时分秒，将时分秒设为0
     *
     * @param dt
     * @return
     */
    public static Date dateTime2date(Date dt) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String dt_string = sf.format(dt);
        try {
            return sf.parse(dt_string);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取当前日期时间
     *
     * @return 返回现在日期时间
     */
    public static Date getCurDate() {
        // SimpleDateFormat formatter = new SimpleDateFormat
        // ("yyyy年MM月dd日   HH:mm:ss     ");
        Date curDate = new Date(System.currentTimeMillis());
        // String str = formatter.format(curDate);
        return curDate;
    }

    /**
     * 获取当前日期的短日期形式
     *
     * @return
     */
    public static String GetNowShortDate() {
        Date dt = new Date();
        return format(dt);
    }

    /**
     * 获取指定时间所在月份，日期集合
     *
     * @param date
     * @return
     */
    public static List<String> getCurrentMonthDayList(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<String> dateList = new ArrayList<>();
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        int d = cld.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= d; i++) {
            cld.set(Calendar.DAY_OF_MONTH, i);
            cld.set(Calendar.HOUR_OF_DAY, 0);
            cld.set(Calendar.MINUTE, 0);
            cld.set(Calendar.SECOND, 0);
            cld.set(Calendar.MILLISECOND, 0);
            dateList.add(sdf.format(cld.getTime()));
        }
        return dateList;
    }

    /**
     * 获取两个时间之间相隔的分钟间隔
     *
     * @param orig
     * @param dest
     * @return
     */
    public static Long getDiffMinutes(Date orig, Date dest) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long currentTime = dest.getTime();
        long createTime = 0;
        try {
            createTime = df.parse(df.format(orig)).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long diff = (currentTime - createTime) / 1000 / 60;
        return diff;
    }

}
