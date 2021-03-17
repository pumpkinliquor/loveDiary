package com.plushih.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by sangyong on 10/17/14.
 */
public class DateUtils {

  public static final String DEFAULT_DATE_FORMAT_YMDHMS_DASHED = "yyyy-MM-dd HH:mm:ss";
  public static final String DEFAULT_DATE_FORMAT_YMD_DASHED = "yyyy-MM-dd";
  public static final String DEFAULT_DATE_FORMAT_YMDHMS_SLASHED = "yyyy/MM/dd HH:mm:ss";
  public static final String DEFAULT_DATE_FORMAT_YMDHMS = "yyyyMMddHHmmss";
  public static final String DEFAULT_DATE_FORMAT_YMD = "yyyyMMdd";
  public static final String DEFAULT_DATE_FORMAT_Y = "yyyy";

  private static class TIME_MAXIMUM{
    public static final int SEC = 60;
    public static final int MIN = 60;
    public static final int HOUR = 24;
    public static final int DAY = 30;
    public static final int MONTH = 12;
  }

  public static Calendar getCalendar() {
    return Calendar.getInstance();
  }

  public static Calendar getCalendar(Integer dayCount) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_YEAR,dayCount);
    return calendar;
  }
  public static Calendar getCalendar(Integer dayCount, Date day) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(day);
    calendar.add(Calendar.YEAR,dayCount);
    return calendar;
  }
  public static String getCalendarFormat(Integer dayCount, String format) {
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    Calendar calendar = dayCount==0?getCalendar():getCalendar(dayCount);
    return sdf.format(calendar.getTime());
  }
  public static String getCalendarFormat(Integer dayCount,String day, String format) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    Date date            = sdf.parse(day);
    Calendar calendar = dayCount==0?getCalendar():getCalendar(dayCount, date);
    return sdf.format(calendar.getTime());
  }
  public static Calendar getCalendar(String day, String format) throws ParseException {
    Calendar cal = Calendar.getInstance();
    cal.setTime(getDate(day, format));
    return cal;
  }

  public static Date getDate(String dateString, String dateFormat) throws ParseException {
    Date date = null;
    if (!StringUtils.hasText(dateString) || !StringUtils.hasText(dateFormat)
        || (dateString.length() != dateFormat.length())) {
      throw new IllegalArgumentException("DateString or DateFormat not found. ");
    }
    SimpleDateFormat dateFormater = new SimpleDateFormat(dateFormat);
    dateFormater.setLenient(false);
    try {
      date = dateFormater.parse(dateString);
    } catch (ParseException pe) {
      throw new ParseException(pe.getMessage(), pe.getErrorOffset());
    }
    return date;
  }

  public static Date getDate() {

    return new Date();
  }

  public static String getDate2String(Date date) throws IllegalArgumentException {
    return getDate2String( date, DEFAULT_DATE_FORMAT_YMDHMS_DASHED );
  }

  public static String getDate2String(Date date, String format) throws IllegalArgumentException {
    if (date == null || !StringUtils.hasText(format))
      throw new IllegalArgumentException("Date or Format String not found");

    DateFormat df = new SimpleDateFormat(format);
    return df.format(date);
  }

  public static String getDate2String(Date date, String format, Locale locale) throws IllegalArgumentException {
    if (date == null || !StringUtils.hasText(format))
      throw new IllegalArgumentException("Date or Format String not found");

    DateFormat df = new SimpleDateFormat(format, locale);
    return df.format(date);
  }

  public static String getToday(String format) {
    if (!StringUtils.hasText(format))
      throw new IllegalArgumentException("Format String not found");
    return getDate2String(getDate(), format);
  }

  public static long getDateToMillsec(String date) {
    if (!StringUtils.hasText(date) || date.length() < 14)
      throw new IllegalArgumentException("Date String not found or string length is shorten then 14");

    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
    cal.set(Calendar.MONTH, Integer.parseInt(date.substring(4, 6)) - 1);
    cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date.substring(6, 8)));
    cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(date.substring(8, 10)));
    cal.set(Calendar.MINUTE, Integer.parseInt(date.substring(10, 12)));
    cal.set(Calendar.SECOND, Integer.parseInt(date.substring(12, 14)));

    return cal.getTimeInMillis();
  }

  public static long getDateToMillsec(Date date) {
    SimpleDateFormat mDateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT_YMDHMS);
    return getDateToMillsec(mDateFormat.format(date));
  }

  public static Date getMillsecToDate(long millsec) {
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(millsec);
    return cal.getTime();
  }

  public static boolean atBetween(Date date, Date beginDate, Date endDate) {
    if (beginDate == null || endDate == null) {
      throw new IllegalArgumentException("Date not found.");
    }

    Calendar mCalendar = getCalendar();
    mCalendar.setTime(endDate);
    mCalendar.add(Calendar.DATE, 1);
    Date actualEndDate = mCalendar.getTime();

    if (date.after(beginDate) && date.before(actualEndDate))
      return true;

    return false;
  }

  public static String getTimeStringFromNow(Date tempDate) {

    long curTime = System.currentTimeMillis();
    long regTime = tempDate.getTime();
    long diffTime = (curTime - regTime) / 1000;

    String msg = null;
    if (diffTime < TIME_MAXIMUM.SEC) {
      // sec
      msg = "방금 전";
    } else if ((diffTime /= TIME_MAXIMUM.SEC) < TIME_MAXIMUM.MIN) {
      // min
      msg = diffTime + "분 전";
    } else if ((diffTime /= TIME_MAXIMUM.MIN) < TIME_MAXIMUM.HOUR) {
      // hour
      msg = (diffTime) + "시간 전";
    } else if ((diffTime /= TIME_MAXIMUM.HOUR) < TIME_MAXIMUM.DAY) {
      // day
      msg = (diffTime) + "일 전";
    } else if ((diffTime /= TIME_MAXIMUM.DAY) < TIME_MAXIMUM.MONTH) {
      // day
      msg = (diffTime) + "달 전";
    } else {
      msg = (diffTime) + "년 전";
    }

    return msg;
  }

}
