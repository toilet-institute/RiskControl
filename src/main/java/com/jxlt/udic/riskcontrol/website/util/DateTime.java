package com.jxlt.udic.riskcontrol.website.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;

/**
 * 一些日期处理的辅助类
 * <p>
 * 
 * </p>
 * 
 * @author fla
 * @version 1.0
 */
public class DateTime {
	private DateTime() {
	}

	/**
	 * 从字符串中取得日期，比如字符串2004-9-9转化成日期型
	 * 
	 * @param sDate
	 * @return
	 * @throws ParseException
	 */
	public static Date getDate(String sDate) throws ParseException {
		sDate = sDate.replace('/', '-');
		Date ltime = DateFormat.getDateInstance().parse(sDate);
		return new Date(ltime.getTime());
	}

	public static Date getDateByFormat(String sDate, String format) throws ParseException {
		if (StringUtils.isBlank(sDate)) {
			return null;
		}
		SimpleDateFormat simformat = new SimpleDateFormat(format);
		Date date = simformat.parse(sDate);

		return date;
	}

	public static long getFormatDate(String sDate) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date.getTime() / 1000;
	}

	/**
	 * 将日期转换成字符串表示，如2004-09-09 17:09:09
	 * 
	 * @param d
	 * @return
	 */
	public static String getFormatDate(Date d) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
	}

	/**
	 * 将日期转换成字符串
	 * 
	 * @param d
	 * @return
	 */
	public static String getFormatDateString(Date d, String format) {
		return new SimpleDateFormat(format).format(d);
	}

	/**
	 * 将日期转换成字符串表示，如2004-09-09 17:09:09
	 * 
	 * @param d
	 * @return
	 */
	public static String getFormatDay(Date d) {
		return new SimpleDateFormat("yyyy/MM/dd").format(d);
	}

	/**
	 * @return 返回字符串型式表示的当前时间如: 2002-12-10 12:10:18
	 */
	public static String now() {
		GregorianCalendar calenda = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(calenda.getTime());
	}

	/**
	 * @return 带上时分秒的当前时间
	 */
	public static Date currentTime() {
		GregorianCalendar calenda = new GregorianCalendar();
		return calenda.getTime();
	}

	/**
	 * 获得当前日期的时间格式
	 * 
	 * @return
	 */
	public static String currentDate() {
		GregorianCalendar calenda = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(calenda.getTime());
	}

	/**
	 * 获取昨天的日期
	 * 
	 * @return
	 */
	public static String yesterDate() {
		DateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, -1);
		String etime = dformat.format(c.getTime());
		return etime;
	}
	

	/**
	 * 获取昨天的日期
	 * 
	 * @return
	 */
	public static String yesterDateNext() {
		DateFormat dformat = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, -1);
		String etime = dformat.format(c.getTime());
		return etime;
	}

	/**
	 * 获取今天的日期
	 * 
	 * @return
	 */
	public static String currentDateNext() {
		DateFormat dformat = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, 0);
		String etime = dformat.format(c.getTime());
		return etime;
	}

	public static String getDateByLong(long time) {

		Date date = new Date(time);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return sdf.format(date);
	}

	public static Long getDistanceDays(Date one, Date two) throws Exception {
		Long days = 0L;
		try {
			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff;
			if (time1 <= time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
			days = diff / (1000 * 60 * 60 * 24);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return days;
	}

	public static String compareDays(Date one, Date two) throws Exception {
		if (one == null || two == null) {
			return "";
		}
		try {
			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff;
			if (time1 <= time2) {
				return "2";
			} else {
				return "1";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void mains(String[] args) throws Exception {

		String currtime = DateTime.now();
		String ddd="2018-09-24 23:59:59";
		String eee="2018-09-27 23:59:59";
		if(ddd.compareTo(currtime)<=0 && eee.compareTo(currtime)>=0){
            System.out.println(DateTime.getFormatDate(DateTime.plusDate(new Date(),"-1")));
		}
//		log.info(DateTime.getFormatDate(DateTime.plusDate(DateTime.getDateByFormat("2017-12-20 00:00:00","yyyy-MM-dd HH:mm:ss"),"30")));
	}

	public static Date long2date(String l) throws Exception {
		if (StringUtils.isBlank(l)) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d = format.format(Long.valueOf(l));
		Date date = format.parse(d);

		return date;
	}

	// day天前后的 日期时间，
	public static Date plusDate(Date date, String day) throws Exception {
		if (date == null) {
			return null;
		}
		if (day == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, Integer.valueOf(day));
		return c.getTime();
	}

}