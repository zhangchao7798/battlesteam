package com.medical.exam.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author Xu
 */
public class DateUtils
{

	private static Logger logger = LoggerFactory.getLogger(DateUtils.class);
	
	public final static String[] weekDays = {"日", "一", "二", "三", "四", "五", "六"};

	/** 毫秒 */
	public final static long MS = 1;
	/** 每秒钟的毫秒数 */
	public final static long SECOND_MS = MS * 1000;
	/** 每分钟的毫秒数 */
	public final static long MINUTE_MS = SECOND_MS * 60;
	/** 每小时的毫秒数 */
	public final static long HOUR_MS = MINUTE_MS * 60;
	/** 每天的毫秒数 */
	public final static long DAY_MS = HOUR_MS * 24;

	/** 标准日期（不含时间）格式化器 */
	private final static SimpleDateFormat NORM_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	/** 标准日期时间格式化器 */
	private final static SimpleDateFormat NORM_DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/** HTTP日期时间格式化器 */
	private final static SimpleDateFormat HTTP_DATETIME_FORMAT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);

	/** 标准日期时间格式化到小时 */
	public final static String NORM_DATE_HOUR_FORMAT = "yyyy-MM-dd HH:mm";

	/** 格式化到day日期 */
	public final static String DAY_FORMAT = "dd";

	public static String getPreviousDay(Date date)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);
		return formatDateTime(c.getTime());
	}

	public static String getPreviousDayAt0(Date date)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, -1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return formatDateTime(c.getTime());
	}

	public static String getNextDay(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DATE);
		calendar.set(Calendar.DATE, day + 1);
		return formatDateTime(calendar.getTime());
	}

	public static String getNextStringDayAt0(Date date)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return formatDateTime(c.getTime());
	}

	public static Date getNextDateDayAt0(Date date)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	public static Date getCurDateDayAt0(Date date)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	public static String getCurStringDayAt0(Date date)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return formatDateTime(c.getTime());
	}

	/**
	 * 当前时间，格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @return 当前时间的标准形式字符串
	 */
	public static String now()
	{
		return formatDateTime(new Date());
	}

	/**
	 * 当前时间，格式 yyyy-MM-dd
	 * 
	 * @return 当前时间的标准形式字符串
	 */
	public static String nowDay()
	{
		return formatDate(new Date());
	}

	/**
	 * 当前日期，格式 yyyy-MM-dd
	 * 
	 * @return 当前日期的标准形式字符串
	 */
	public static String today()
	{
		return formatDate(new Date());
	}

	/**
	 * 时间，格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @return 当前日期的标准形式字符串
	 */
	public static Date getInvestRepaymentTime()
	{
		return parseDateTime(formatDate(new Date()) + " 16:00:00");
	}
	
	/**
	 * 时间，格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @return 当前日期的标准形式字符串
	 */
	public static Date getInvestRepaymentTime(Date date)
	{
		return parseDateTime(formatDate(date) + " 16:00:00");
	}

	/**
	 * 邀请时间，格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @return 当前日期的标准形式字符串
	 */
	public static Date getInviteRepaymentTime()
	{
		return parseDateTime(formatDate(new Date()) + " 17:00:00");
	}

	// ------------------------------------ Format start ----------------------------------------------
	/**
	 * 根据特定格式格式化日期
	 * 
	 * @param date 被格式化的日期
	 * @param format 格式
	 * @return 格式化后的字符串
	 */
	public static String format(Date date, String format)
	{
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * 格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date 被格式化的日期
	 * @return 格式化后的日期
	 */
	public static synchronized String formatDateTime(Date date)
	{
		return NORM_DATETIME_FORMAT.format(date);
	}

	/**
	 * 格式化为Http的标准日期格式
	 * 
	 * @param date 被格式化的日期
	 * @return HTTP标准形式日期字符串
	 */
	public static synchronized String formatHttpDate(Date date)
	{
		// return new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US).format(date);
		return HTTP_DATETIME_FORMAT.format(date);
	}

	/**
	 * 格式 yyyy-MM-dd
	 * 
	 * @param date 被格式化的日期
	 * @return 格式化后的字符串
	 */
	public static synchronized String formatDate(Date date)
	{
		return NORM_DATE_FORMAT.format(date);
	}
	// ------------------------------------ Format end ----------------------------------------------

	// ------------------------------------ Parse start ----------------------------------------------
	/**
	 * 将特定格式的日期转换为Date对象
	 * 
	 * @param dateString 特定格式的日期
	 * @param format 格式，例如yyyy-MM-dd
	 * @return 日期对象
	 */
	public static Date parse(String dateString, String format)
	{
		try
		{
			return (new SimpleDateFormat(format)).parse(dateString);
		}
		catch (ParseException e)
		{
			logger.error("Parse " + dateString + " with format " + format + " error!", e);
		}
		return null;
	}

	/**
	 * 格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateString 标准形式的时间字符串
	 * @return 日期对象
	 */
	public synchronized static Date parseDateTime(String dateString)
	{
		try
		{
			return NORM_DATETIME_FORMAT.parse(dateString);
		}
		catch (ParseException e)
		{
			logger.error("Parse " + dateString + " with format " + NORM_DATETIME_FORMAT.toPattern() + " error!", e);
		}
		return null;
	}

	/**
	 * 格式yyyy-MM-dd
	 * 
	 * @param dateString 标准形式的日期字符串
	 * @return 日期对象
	 */
	public synchronized static Date parseDate8YMD(String dateString)
	{
		try
		{
			return NORM_DATE_FORMAT.parse(dateString);
		}
		catch (ParseException e)
		{
			logger.error("Parse " + dateString + " with format " + NORM_DATE_FORMAT.toPattern() + " error!", e);
		}
		return null;
	}
	// ------------------------------------ Parse end ----------------------------------------------

	/**
	 * 时间，格式 yyyy-MM-dd HH:mm:ss
	 *
	 * @return 当前日期的标准形式字符串
	 */
	public static Date getDayBeginTime()
	{
		return parseDateTime(formatDate(new Date()) + " 00:00:00");
	}

	/**
	 * 时间，格式 yyyy-MM-dd HH:mm:ss
	 *
	 * @return 当前日期的标准形式字符串
	 */
	public static Date getDayBeginTime(Date date)
	{
		return parseDateTime(formatDate(date) + " 00:00:00");
	}

	/**
	 * 时间，格式 yyyy-MM-dd HH:mm:ss
	 *
	 * @return 当前日期的标准形式字符串
	 */
	public static Date getDayEndTime()
	{
		return parseDateTime(formatDate(new Date()) + " 23:59:59");
	}

	/**
	 * 时间，格式 yyyy-MM-dd HH:mm:ss
	 *
	 * @return 当前日期的标准形式字符串
	 */
	public static Date getDayEndTime(Date date)
	{
		return parseDateTime(formatDate(date) + " 23:59:59");
	}

	/**
	 * 获取指定日期偏移指定时间后的时间
	 * 
	 * @param date 基准日期
	 * @param calendarField 偏移的粒度大小（小时、天、月等）使用Calendar中的常数
	 * @param offsite 偏移量，正数为向后偏移，负数为向前偏移
	 * @return 偏移后的日期
	 */
	public static Date getOffsiteDate(Date date, int calendarField, int offsite)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(calendarField, offsite);
		return cal.getTime();
	}

	public static Date getOffsiteDateAt0(Date date, int calendarField, int offsite)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(calendarField, offsite);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	/**
	 * 判断两个日期相差的时长<br/>
	 * 返回 minuend - subtrahend 的差
	 * 
	 * @param subtrahend 减数日期
	 * @param minuend 被减数日期
	 * @param diffField 相差的选项：相差的天、小时
	 * @return 日期差
	 */
	public static long dateDiff(Date subtrahend, Date minuend, long diffField)
	{
		long diff = minuend.getTime() - subtrahend.getTime();
		return diff / diffField;
	}

	/**
	 * <pre>
	 * DateUtils.equals(null, null)   = true
	 * DateUtils.equals(null, new Date())  = false
	 * DateUtils.equals(new Date(), null)  = false
	 * DateUtils.equals(new Date(), new Date()) = true
	 * </pre>
	 */
	public static boolean equals(Date date1, Date data2)
	{
		if (null == date1)
		{
			if (null == data2)
			{
				return true;
			}
			return false;
		}
		return date1.equals(data2);
	}

	public static boolean notEquals(Date date1, Date data2)
	{
		return !equals(date1, data2);
	}

}
