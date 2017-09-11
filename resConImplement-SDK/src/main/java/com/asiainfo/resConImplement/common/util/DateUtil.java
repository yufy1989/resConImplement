package com.asiainfo.resConImplement.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 类说明：日期相关操作
 * 
 * @author BAOMZ
 * @date 2016年5月21日 下午3:38:13
 */
public class DateUtil extends DateUtils{
	
	
	public static final String FORMAT_DATE_DAY="yyyy-MM-dd";
	public static final String FORMAT_DATE_SECOND="yyyy-MM-dd HH:mm:ss";

	private static DateFormat FORMAT = new SimpleDateFormat("yyMMddHHmmssSSS");

	private static DateFormat FORMAT1 = new SimpleDateFormat("yyyyMM");
	
	/**
	 * 功能描述：返回201604 这样的数字
	 * 
	 * @author BAOMZ
	 * @date 2016年5月21日 下午3:56:34
	 * @param @return
	 * @return long
	 */
	public static long getYearAndMonth() {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		return (year * 100) | (month + 1);
	}

	/**
	 * 功能描述：如果当前是201604返回 这样的数字 yearAppend是10 那么返回202604
	 * 
	 * @author BAOMZ
	 * @date 2016年5月21日 下午3:57:03
	 * @param @param
	 *            yearAppend
	 * @param @return
	 * @return long
	 */
	public static long getYearAndMonthAppend(int yearAppend) {
		return getYearAndMonth() + (yearAppend * 100);
	}

	/**
	 * 功能描述：如果当前是201604返回 这样的数字 yearAppend是10 monthAppend是2 那么返回202606 如果month
	 * 相加超过12,只返回12
	 * 
	 * @author BAOMZ
	 * @date 2016年5月21日 下午4:05:07
	 * @param @param
	 *            yearAppend
	 * @param @param
	 *            monthAppend
	 * @param @return
	 * @return long
	 */
	public static long getYearAndMonthAppend(int yearAppend, int monthAppend) {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		month = (month + monthAppend);
		if (month >= 11) {
			month = 11;
		}
		return ((year + yearAppend) * 100) + (month + 1);
	}

	/**
	 * 功能描述：如果当前是201604返回 这样的数字 monthAppend是2 那么返回202606 如果monthAppend 是 13 返回
	 * 201701
	 * 
	 * @author BAOMZ
	 * @date 2016年5月21日 下午4:15:27
	 * @param @param
	 *            month
	 * @param @return
	 * @return long
	 */
	public static long getYearAndMonthAppendMoth(int appendMonth) {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		month += appendMonth;
		int appendY = month / 12;
		int appendM = month % 12;
		return ((year + appendY) * 100) + (appendM + 1);
	}

	private static Long currentWwitNumber = 0l;

	/**
	 * 功能描述：返回15位流水号 yyMMddHHmmssSSS,该方法在多线程中保持唯一序列
	 * @author BAOMZ
	 * @date 2016年5月21日 下午5:01:12
	 * @param @return 
	 * @return long
	 */
	public static long switNumber() {

		long l = Long.parseLong(FORMAT.format(new Date()));
		if (currentWwitNumber == l) {
			synchronized (currentWwitNumber) {
				return switNumber();
			}
		}
		currentWwitNumber = l;
		return l;
	}
	
	/**
	 * 功能描述： 获取转换格式时间yyyymm
	 * @author lipj
	 * @date 2016年6月7日 下午5:50:09
	 * @param @return 
	 * @return long
	 */
	public static long switNumber1() {

		long l = Long.parseLong(FORMAT1.format(new Date()));
	
		return l;
	}
	
	/**
	 * 功能描述：添加小时
	 * @author pankx
	 * @date 2016年8月5日 上午10:24:33
	 * @param @return 
	 * @return String
	 */
	public static String addHour(Date date,int amount){
	
		SimpleDateFormat slf = new SimpleDateFormat(FORMAT_DATE_SECOND);
		if(date == null ){
			return null;
		}
		//添加小时为0，则返回当前日期
		if(amount ==0){
			return slf.format(date);
		}
		
		return slf.format(addHours(date, amount));
	}
	
	
	/**
	 * 功能描述：将字符串类型的日期转换为日期类型
	 * @author pankx
	 * @date 2016年5月21日 下午2:28:55
	 * @param @param date
	 * @param @param pattern
	 * @param @return 
	 * @return Date
	 */
	public static Date parse(String date,String pattern){
		
		if(StringUtils.isBlank(pattern)){
			pattern=FORMAT_DATE_SECOND;
		}
		SimpleDateFormat sml = new SimpleDateFormat(pattern);
		if(StringUtils.isNotBlank(date)){
			
			try {
				return sml.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	/**
	 * 功能描述：将日期转换为字符串
	 * @author pankx
	 * @date 2016年5月21日 上午11:18:32
	 * @param @param date 
	 * @return void
	 */
	public static String DateToStr(Date date,String pattern){

		if(date==null){
			return null;
		}
		
		if(StringUtils.isBlank(pattern)){
			pattern = FORMAT_DATE_SECOND;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
		
	}
	
	/**
	 * 功能描述：将日期转换为字符串，默认是时分秒
	 * @author pankx
	 * @date 2016年5月21日 上午11:18:32
	 * @param @param date 
	 * @return void
	 */
	public static String DateToStr(Date date){
		return DateToStr(date,"");
	}
	
	/**
	 * 功能描述：某个日期加上某些天返回字符串类型的日期
	 * @author pankx
	 * @date 2016年5月21日 上午11:25:49
	 * @param @param date
	 * @param @param mount
	 * @param @return 
	 * @return String
	 */
	public static String dateAddDay(String date,int amount){
		
		if(StringUtils.isBlank(date)){
			return null;
		}
		
		Date _date = addDays(parse(date,FORMAT_DATE_SECOND), amount);
		return DateToStr(_date);

	}
	
	/**
	 * 功能描述： n个月后的第一天的起始时间,负数是前几个月月初的起始时间,0是当前月的开始日期
	 * @author pankx
	 * @date 2016年5月21日 上午11:52:17
	 * @param @param date
	 * @param @param amount
	 * @param @return 
	 * @return String
	 */
	public static String getMonthFirstDate(Date date,int amount){
		
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_DAY);
		if(date==null){
			date = new Date();
		}
		
		try{
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, amount);
			cal.set(Calendar.DAY_OF_MONTH,1);
			return sdf.format(cal.getTime());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	/*
	 * 功能描述：下个月的月初开始日期
	 * @author pankx
	 * @date 2016年5月21日 下午2:18:26
	 * @param @return 
	 * @return String
	 */
	public static String afterMonthFirstDate(){
		return getMonthFirstDate(new Date(),1);
	}
	
	/**
	 * 功能描述：上个月的月初起始日期
	 * @author pankx
	 * @date 2016年5月21日 下午2:19:37
	 * @param @return 
	 * @return String
	 */
	public static String beforeMonthFirstDate(){
		return getMonthFirstDate(new Date(),-1);
	}
	
	/**
	 * 功能描述：返回位随机数
	 * @author cuishuo
	 * @date 2016年6月20日 下午5:51:25
	 * @param @return 
	 * @return Integer
	 */
	public static Integer getIntegerNum(){
		StringBuilder str=new StringBuilder();//定义变长字符串
		Random random=new Random();
		//随机生成数字，并添加到字符串
		for(int i=0;i<8;i++){
			str.append(random.nextInt(10));
		}
		//将字符串转换为数字并输出
		int num=Integer.parseInt(str.toString());
		return Integer.valueOf(num);
	}
	
	
	public static void main(String[] args){
	/*	System.out.println(getNextCycleId(201619));
		 try {
			System.out.println(DateToStr(addSecond(new Date(),5000)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 */
		System.out.println(utc2Local("2007-10-23T17:15:44.000Z", "yyyy-MM-dd'T'HH:mm:ss.SSS Z", "yyyy-mm-dd HH:mm:ss"));
		
		//System.out.println(utc2Local(new Date(),0,0));
	//	System.out.println(local2Utc(dateAddHourAddSeconds(new Date(),0,5), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss"));
	}
	
	/**
	 * 功能描述：根据当前帐期返回下一个帐期 帐期按年月表示
	 * @author Baomz
	 * @date 2016年6月16日 下午7:49:44
	 * @param @param cycleId 201601
	 * @param @return 
	 * @return Integer
	 */
	public static Integer getNextCycleId(Integer cycleId){
		Date date = null;
		try {
			date = FORMAT1.parse(cycleId + "");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, 1);
		String rs = FORMAT1.format(c.getTime());
		return Integer.parseInt(rs);
	}
	
	/**
	 * 功能描述：根据年月返回Date
	 * @author Baomz
	 * @date 2016年6月21日 上午10:49:15
	 * @param @param yearAndMonth
	 * @param @return 
	 * @return Date
	 */
	public static Date getDate(Integer yearAndMonth){
		Date date = null;
		try{
			date = FORMAT1.parse(yearAndMonth + "");
		}catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 功能描述：在某个日期上加多少秒
	 * @author pankx
	 * @date 2016年7月27日 下午4:17:25
	 * @param @param date
	 * @param @param seconds
	 * @param @return 
	 * @return Date
	 * @throws ParseException 
	 */
	public static Date addSecond(Date date,long seconds) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_SECOND);
		long times = date.getTime()+seconds;
		return sdf.parse(sdf.format(times));
	}
	
	/**
	 * 功能描述：JAVA中将UTC时间转换为本地时间的方法
	 * @author pankx
	 * @date 2016年8月2日 下午3:03:27
	 * @param @param utcTime
	 * @param @param utcTimePatten
	 * @param @param localTimePatten
	 * @param @return 
	 * @return String
	 */
	public static String utc2Local(String utcTime, String utcTimePatten,
			   String localTimePatten) {
		 
		  SimpleDateFormat utcFormater = new SimpleDateFormat(utcTimePatten);
		  utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));
		  utcTime = utcTime.replace("Z", " UTC");   
		  System.out.println("utcTime:"+utcTime);
		  Date gpsUTCDate = null;
		  try {
			  gpsUTCDate = utcFormater.parse(utcTime);
		  } catch (ParseException e) {
			  e.printStackTrace();
		  }
		 
		  SimpleDateFormat localFormater = new SimpleDateFormat(localTimePatten);
		  localFormater.setTimeZone(TimeZone.getDefault());
		  String localTime = localFormater.format(gpsUTCDate.getTime());
		 
		  return localTime;
	 }
	
	/**
	 * 功能描述： 增加几小时，和多少秒
	 * @author pankx
	 * @date 2016年8月5日 上午10:32:33
	 * @param @return 
	 * @return String
	 */
	public static String dateAddHourAddSeconds(Date date,int amountHour,int amountSec){
		SimpleDateFormat smpl = new SimpleDateFormat(FORMAT_DATE_SECOND);
		if(amountHour==0 && amountSec==0){
			return smpl.format(date);
		}
		
		if(amountHour==0 && amountSec!=0){
			return smpl.format(addSeconds(date, amountSec));
		}else if(amountHour!=0 && amountSec==0){
			return smpl.format(addHours(date, amountHour));
		}else{
			return smpl.format(addSeconds(addHours(date, amountHour), amountSec));
		}
		
	}
	/**
	 * 功能描述：local 时间转换为UTC时间格式
	 * @author pankx
	 * @date 2016年8月2日 下午4:36:47
	 * @param @param localTime
	 * @param @param localPattern
	 * @param @param utcTimePattern
	 * @param @return 
	 * @return String
	 */
	public static String local2Utc(String localTime,String localPatten,String utcTimePatten){
		SimpleDateFormat localformater = new SimpleDateFormat(localPatten);
		Date _locatTime;
		 String _utcTime =null;
		try {
			_locatTime = localformater.parse(localTime);
			  SimpleDateFormat utcFormater = new SimpleDateFormat(utcTimePatten);
			  utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));
			 _utcTime = utcFormater.format(_locatTime.getTime())+"Z";
			  System.out.println("utcTime:"+_utcTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return _utcTime;
	}
}
