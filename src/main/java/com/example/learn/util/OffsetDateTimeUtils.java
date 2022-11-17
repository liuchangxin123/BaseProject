package com.example.learn.util;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Locale;

import static java.time.ZoneId.systemDefault;

/**
 * @ClassName OffsetDateTimeUtils
 * @Description 时间转换工具类
 * @Date 2022/11/17 11:08
 * @Author pluto
 */
public class OffsetDateTimeUtils {

    private static final String SEMICOLON = ":";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 字符串转 OffsetDateTime
     *
     * @param text    字符串
     * @param pattern 描述日期和时间格式的模式
     * @return OffsetDateTime
     */
    public static OffsetDateTime fromString(String text, String pattern) {
        return fromDate(parse(text, pattern));
    }

    /**
     * 字符串 转 OffsetDateTime 只支持两种格式：yyyy-MM-dd HH:mm:ss / yyyy-MM-dd
     */
    public static OffsetDateTime fromString(String text) {
        if (text.contains(SEMICOLON)) {
            return fromDate(parse(text, DATE_TIME_FORMAT));
        } else {
            return fromDate(parse(text, DATE_FORMAT));
        }
    }

    private static Date parse(String value, String pattern) {
        if (StringUtils.isBlank(value) || StringUtils.isBlank(pattern)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        try {
            return sdf.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static OffsetDateTime fromDate(Date date) {
        return date == null ? null : fromLong(date.getTime());
    }

    public static OffsetDateTime fromLong(Long mills) {
        return new Timestamp(mills).toInstant().atZone(systemDefault()).toOffsetDateTime();
    }

    public static OffsetDateTime getMonthOfFirstDay(OffsetDateTime dateTime) {
        return LocalDateTime.of(dateTime.getYear(), dateTime.getMonthValue(), 1, 0, 0, 0, 0)
                .atZone(systemDefault())
                .toOffsetDateTime();
    }

    /**
     * 本月最后一天结束时间
     *
     * @param dateTime
     * @return
     */
    public static OffsetDateTime getMonthOfEndDay(OffsetDateTime dateTime) {
        LocalDateTime localDateTime = dateTime.toLocalDateTime();
        OffsetDateTime offsetDateTime = localDateTime.with(TemporalAdjusters.lastDayOfMonth()).atZone(systemDefault()).toOffsetDateTime();
        return withTimeAtEndOfDay(offsetDateTime);
    }

    /**
     * 本月最后一天开始时间
     * @param dateTime
     * @return
     */
    public static OffsetDateTime getMonthOfEndDayStart(OffsetDateTime dateTime) {
        LocalDateTime localDateTime = dateTime.toLocalDateTime();
        OffsetDateTime offsetDateTime = localDateTime.with(TemporalAdjusters.lastDayOfMonth()).atZone(systemDefault()).toOffsetDateTime();
        return withTimeAtStartOfDay(offsetDateTime);
    }

    /**
     * 前一月第一天
     *
     * @param dateTime
     * @return
     */
    public static OffsetDateTime getLastMonthOfFirstDay(OffsetDateTime dateTime) {
        LocalDateTime localDateTime = dateTime.toLocalDateTime();
        OffsetDateTime offsetDateTime = localDateTime.with(TemporalAdjusters.firstDayOfMonth()).minusDays(1).with(TemporalAdjusters.firstDayOfMonth()).atZone(systemDefault()).toOffsetDateTime();
        return withTimeAtStartOfDay(offsetDateTime);

    }

    /**
     * 前一个月最后一天结束时间
     *
     * @param dateTime
     * @return
     */
    public static OffsetDateTime getLastMonthOfEndDay(OffsetDateTime dateTime) {
        LocalDateTime localDateTime = dateTime.toLocalDateTime();
        OffsetDateTime offsetDateTime = localDateTime.with(TemporalAdjusters.firstDayOfMonth()).minusDays(1).atZone(systemDefault()).toOffsetDateTime();
        return withTimeAtEndOfDay(offsetDateTime);
    }

    /**
     * N月前的一天
     *
     * @param dateTime
     * @return
     */
    public static OffsetDateTime getLastNMonthOfDay(OffsetDateTime dateTime,int n) {
        LocalDateTime localDateTime = dateTime.toLocalDateTime();
        return localDateTime.minusMonths(n).atZone(systemDefault()).toOffsetDateTime();

    }
    /**
     * N天前的时间
     *
     * @param dateTime
     * @return
     */
    public static OffsetDateTime getLastNDays(OffsetDateTime dateTime,int n) {
        LocalDateTime localDateTime = dateTime.toLocalDateTime();
        return localDateTime.minusDays(n).atZone(systemDefault()).toOffsetDateTime();

    }
    /**
     * 本年第一天
     *
     * @param dateTime
     * @return
     */
    public static OffsetDateTime getYearOfFirstDay(OffsetDateTime dateTime) {
        LocalDateTime localDateTime = dateTime.toLocalDateTime();
        OffsetDateTime offsetDateTime = localDateTime.with(TemporalAdjusters.firstDayOfYear()).atZone(systemDefault()).toOffsetDateTime();
        return withTimeAtStartOfDay(offsetDateTime);
    }

    /**
     * 本年最后一天
     *
     * @param dateTime
     * @return
     */
    public static OffsetDateTime getYearOfEndDay(OffsetDateTime dateTime) {
        LocalDateTime localDateTime = dateTime.toLocalDateTime();
        OffsetDateTime offsetDateTime = localDateTime.with(TemporalAdjusters.lastDayOfYear()).atZone(systemDefault()).toOffsetDateTime();
        return withTimeAtEndOfDay(offsetDateTime);
    }


    /**
     * 前一年第一天
     *
     * @param dateTime
     * @return
     */
    public static OffsetDateTime getLastYearOfFirstDay(OffsetDateTime dateTime) {
        LocalDateTime localDateTime = dateTime.toLocalDateTime();
        OffsetDateTime offsetDateTime = localDateTime.with(TemporalAdjusters.firstDayOfYear()).minusDays(1).with(TemporalAdjusters.firstDayOfYear()).atZone(systemDefault()).toOffsetDateTime();
        return withTimeAtStartOfDay(offsetDateTime);
    }

    /**
     * 前一年最后一天
     *
     * @param dateTime
     * @return
     */
    public static OffsetDateTime getLastYearOfEndDay(OffsetDateTime dateTime) {
        LocalDateTime localDateTime = dateTime.toLocalDateTime();
        OffsetDateTime offsetDateTime = localDateTime.with(TemporalAdjusters.firstDayOfYear()).minusDays(1).atZone(systemDefault()).toOffsetDateTime();
        return withTimeAtEndOfDay(offsetDateTime);
    }

    /**
     * 本周第一天开始时间
     */
    public static OffsetDateTime getWeekOfFirstDay(OffsetDateTime dateTime) {
        int datOfWeek = dateTime.getDayOfWeek().getValue();
        return withTimeAtStartOfDay(dateTime.minusDays(datOfWeek - 1));
    }

    /**
     * 本周最后一天结束时间
     *
     * @param dateTime
     * @return
     */
    public static OffsetDateTime getWeekOfEndDay(OffsetDateTime dateTime) {
        int datOfWeek = dateTime.getDayOfWeek().getValue();
        return withTimeAtEndOfDay(dateTime.minusDays(datOfWeek - 7));
    }
    /**
     * 上周第一天开始时间
     *
     * @param dateTime
     * @return
     */
    public static OffsetDateTime getLastWeekOfFirstDay(OffsetDateTime dateTime) {
        int datOfWeek = dateTime.getDayOfWeek().getValue();
        return withTimeAtLastStartOfWeek(dateTime.minusDays(datOfWeek - 1));
    }

    /**
     * 上周最后一天结束时间
     *
     * @param dateTime
     * @return
     */
    public static OffsetDateTime getLastWeekOfEndDay(OffsetDateTime dateTime) {
        int datOfWeek = dateTime.getDayOfWeek().getValue();
        return withTimeAtLastEndOfWeek(dateTime.minusDays(datOfWeek - 7));
    }

    /**
     * 获取对应日期的当天开始时间
     */
    public static OffsetDateTime withTimeAtStartOfDay(OffsetDateTime dateTime) {
        return dateTime.withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    /**
     * 前一天开始时间
     *
     * @param dateTime
     * @return
     */
    public static OffsetDateTime withTimeAtLastStartOfDay(OffsetDateTime dateTime) {
        return withTimeAtStartOfDay(dateTime.minusDays(1));
    }

    /**
     * 前一天结束时间
     *
     * @param dateTime
     * @return
     */
    public static OffsetDateTime withTimeAtLastEndOfDay(OffsetDateTime dateTime) {
        return withTimeAtEndOfDay(dateTime.minusDays(1));
    }

    /**
     * 前一周开始时间
     *
     * @param dateTime
     * @return
     */
    public static OffsetDateTime withTimeAtLastStartOfWeek(OffsetDateTime dateTime) {
        return withTimeAtStartOfDay(dateTime.minusWeeks(1));
    }

    /**
     * 前一周结束时间
     *
     * @param dateTime
     * @return
     */
    public static OffsetDateTime withTimeAtLastEndOfWeek(OffsetDateTime dateTime) {
        return withTimeAtEndOfDay(dateTime.minusWeeks(1));
    }

    /**
     * 获取对应日期的当天结束时间
     */
    public static OffsetDateTime withTimeAtEndOfDay(OffsetDateTime dateTime) {
        return dateTime.withHour(23).withMinute(59).withSecond(59).withNano(0);
    }
    /**
     * 格式化时间
     */
    public static String format(OffsetDateTime dateTime, String pattern) {
        if (dateTime == null || StringUtils.isEmpty(pattern)) {
            return null;
        }

        if (ZoneOffset.UTC.equals(dateTime.getOffset())) {
            return dateTime.toInstant()
                    .atOffset(ZoneOffset.ofTotalSeconds(OffsetDateTime.now().getOffset().getTotalSeconds()))
                    .format(DateTimeFormatter.ofPattern(pattern));
        } else {
            return dateTime.format(DateTimeFormatter.ofPattern(pattern));
        }
    }
}
