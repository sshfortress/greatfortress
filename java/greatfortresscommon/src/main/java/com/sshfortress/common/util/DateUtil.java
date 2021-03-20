package com.sshfortress.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	private static final SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
	private static final SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");
	private static final SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 
	 * @param orgDate
	 *            原始日期（yyyy-MM-dd）
	 * @param diffYear
	 *            加上的几年，减去几年用负数
	 * @param diffMonth
	 *            加上的几个月，减去几个月用负数
	 * @param diffDay
	 *            加上的几天，减去几天用负数
	 * @return
	 * @throws ParseException
	 */
	public static String addYear(String orgDate, int diffYear, int diffMonth, int diffDay) {
		String reStr = "";
		try {
			Date dt = sdfDay.parse(orgDate);
			Calendar rightNow = Calendar.getInstance();
			rightNow.setTime(dt);
			rightNow.add(Calendar.YEAR, diffYear);
			rightNow.add(Calendar.MONTH, diffMonth);
			rightNow.add(Calendar.DAY_OF_YEAR, diffDay);
			Date dt1 = rightNow.getTime();
			reStr = sdfDay.format(dt1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return reStr;
	}

	/**
	 * 计算两个日期相差几个月
	 * 
	 * @param beginDate
	 *            开始日期（yyyy-MM-dd）
	 * @param endDate
	 *            结束日期（yyyy-MM-dd）
	 * @return
	 */
	public static int getDiffMonth(String beginDate, String endDate) {
		int diff = 0;
		try {
			Date bdt = sdfDay.parse(beginDate);
			Date edt = sdfDay.parse(endDate);

			Calendar c = Calendar.getInstance();

			c.setTime(bdt);
			int beginYear = c.get(Calendar.YEAR);
			int beginMonth = c.get(Calendar.MONTH);
			int beginDay = c.get(Calendar.DATE);

			c.setTime(edt);
			int endYear = c.get(Calendar.YEAR);
			int endMonth = c.get(Calendar.MONTH);
			int endDay = c.get(Calendar.DATE);

			if (beginYear == endYear) {
				diff = endMonth - beginMonth;// 两个日期相差几个月，即月份差
			} else {
				diff = 12 * (endYear - beginYear) + endMonth - beginMonth;// 两个日期相差几个月，即月份差
			}
			if(beginMonth == endMonth && endDay < beginDay){
				diff -= 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return diff;
	}

	public static String getYear() {
		return sdfYear.format(new Date());
	}

	public static String getDay() {
		return sdfDay.format(new Date());
	}

	public static String getDays() {
		return sdfDays.format(new Date());
	}

	public static String getTime() {
		return sdfTime.format(new Date());
	}

	public static boolean compareDate(String s, String e) {
		if ((fomatDate(s) == null) || (fomatDate(e) == null)) {
			return false;
		}
		return fomatDate(s).getTime() >= fomatDate(e).getTime();
	}

	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public static int getDiffYear(String startTime, String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			long aa = 0L;
			return (int) ((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / 86400000L / 365L);
		} catch (Exception e) {
		}
		return 0;
	}

	public static long getDaySub(String beginDateStr, String endDateStr) {
		long day = 0L;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = null;
		Date endDate = null;
		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		day = (endDate.getTime() - beginDate.getTime()) / 86400000L;

		return day;
	}

	public static String getAfterDayDate(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance();
		canlendar.add(5, daysInt);
		Date date = canlendar.getTime();

		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdfd.format(date);

		return dateStr;
	}

	public static String getAfterDayWeek(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance();
		canlendar.add(5, daysInt);
		Date date = canlendar.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("E");
		String dateStr = sdf.format(date);

		return dateStr;
	}

	public static String dateChangeString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String nowTime = dateFormat.format(date);
		return nowTime;
	}
	
	/**
	 * <p class="detail">
	 * 功能：全格式 yyyy-mm-dd HH24:mm:ss
	 * </p>
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = dateFormat.format(date);
		return nowTime;
	}

	public static void main(String[] args) {
		/*
		 * System.out.println(getDays());
		 * System.out.println(getAfterDayWeek("3"));
		 * System.out.println(getDiffYear("2000-3-17", "2014-3-14"));
		 */
		String regTime = "2011-12-1";
		String nextTime = "";
		System.out.println(DateUtil.getDay());
		int diff = DateUtil.getDiffMonth(regTime, DateUtil.getDay());
		System.out.println(diff);
		if (diff < 5*12) {
			nextTime = DateUtil.addYear(regTime, diff / 12 + 1, 0, 0);
		} else {
			nextTime = DateUtil.addYear(regTime, 0, diff + 6 - diff % 6, 0);
		}
		System.out.println(nextTime);
	}
	
	/**
	 * <p class="detail">
	 * 功能：将字符串时间解析为Date类型时间
	 * </p>
	 * @param datestr
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String datestr,String format) throws ParseException{
		return new SimpleDateFormat(format).parse(datestr);
	}
	/**
	 * <p class="detail">
	 * 功能：日期转换成时间戳
	 * </p>
	 * @param s
	 * @return
	 * @throws ParseException
	 */
    public static String dateToStamp(String s) throws ParseException{
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
    
    /**
	 * <p class="detail">
	 * 功能：日期格式化为字符串
	 * </p>
	 * @param date 时间
	 * @param format 格式
	 * @return
	 */
	public static String dateFormat(Date date,String format){
		return new SimpleDateFormat(format).format(date);
	}
	
	/**
	 * <p class="detail">
	 * 功能：判断两个时间大小
	 * </p>
	 * @param Time1
	 * @param Time2
	 * @param dataformat
	 * @return
	 * @throws ParseException
	 */
	public static boolean BSize(String Time1,String Time2 ,String dataformat) throws ParseException{
		Date end=new SimpleDateFormat(dataformat).parse(Time1);
		Date start=new SimpleDateFormat(dataformat).parse(Time2);
		Calendar cal=Calendar.getInstance();
		cal.setTime(end);
		Calendar cal2=Calendar.getInstance();
		cal2.setTime(start);
		if(cal.getTimeInMillis()>cal2.getTimeInMillis()){
			return true;
		}else{
			return false;
		}

	}
	
	/**
	 * <p class="detail">
	 * 功能：获得传入的时间凌晨时间(即00:00:00)
	 * </p>
	 * @param date
	 * @return
	 */
	public static String getMorningToString(Date date){
		return dateFormat(date,"yyyy-MM-dd HH:mm:ss").substring(0,10)+" 00:00:00";
	}
	/**
	 * <p class="detail">
	 * 功能：获得传入的时间午夜时间(即23:59:59)
	 * </p>
	 * @param date
	 * @return
	 */
	public static String getNightToString(Date date){
		return dateFormat(date,"yyyy-MM-dd HH:mm:ss").substring(0,10)+" 23:59:59";
	}
	
	/**
	 * <p class="detail">
	 * 功能：时间加减某个天数后的时间
	 * </p>
	 * @param date 要加减的时间
	 * @param day 要加减的天数，减天数传负数
	 * @return
	 */
	public static Date addSubtractDate(Date date,int day){
		Calendar resultCalendar = Calendar.getInstance();
		resultCalendar.setTime(date);
		resultCalendar.add(Calendar.DAY_OF_YEAR,day);
		return resultCalendar.getTime();
	}

	/**
	 * <p class="detail">
	 * 功能：时间加减某个月数后的时间
	 * </p>
	 * @param date 要加减的时间
	 * @param month 要加减的月数，减月数传负数
	 * @return
	 */
	public static Date addSubtractMonth(Date date,int month){
		Calendar resultCalendar = Calendar.getInstance();
		resultCalendar.setTime(date);
		resultCalendar.add(Calendar.MONTH,month);
		return resultCalendar.getTime();
	}

	/**
	 * <p class="detail">
	 * 功能：根据日期获取得到星期
	 * </p>
	 * @param date
	 * @return
	 */
	public static String getWeek(Date date){  
		String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};  
		Calendar cal = Calendar.getInstance();  
		cal.setTime(date);  
		int week_index = cal.get(Calendar.DAY_OF_WEEK) ;  
		if(week_index<0){  
			week_index = 0;  
		}   
		//return weeks[week_index];
		return String.valueOf(week_index) ; 
	}

	/**
	 * <p class="detail">
	 * 功能：根据日期获取得到星期几 中文显示
	 * </p>
	 * @param date
	 * @return
	 */
	public static String getWeekByChinese(Date date){  
		String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};  
		Calendar cal = Calendar.getInstance();  
		cal.setTime(date);  
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;  
		if(week_index<0){  
			week_index = 0;  
		}   
		return weeks[week_index];
	}
}
