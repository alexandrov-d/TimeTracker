package com.ada.timetracker.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.jws.soap.SOAPBinding;

public class TimeHelper {
	
	/**
	 * Converts time string to number of seconds
	 * @param string time in HH:mm:ss format
	 * @return number of seconds
	 */
	public static int stringToSeconds(String string){
		String [] time = string.split(":");
		int hours = Integer.parseInt( time[0] );
		int minutes = Integer.parseInt( time[1] );
		int seconds = Integer.parseInt( time[2] );
		
		return hours*3600 + minutes*60 + seconds;
	}
	
	
	public static String secondsToString(int seconds){
	
		int hours = seconds/3600;
		int minutes = (seconds - hours*3600) / 60;
		int sec = (seconds - hours*3600 -  minutes * 60);
		String h,m,s;
		h = hours < 10 ? "0" + hours : hours + "";
		m = minutes < 10 ? "0" + minutes : minutes + "";
		s = sec < 10 ? "0" + sec : sec + "";

		return h + ":" + m + ":" + s ;
	}
	
	public static String doubleHoursToTime(Double hours){
	
		int h = hours.intValue();
		long min = Math.round(hours%1*0.6*100);
		String ms = String.valueOf(min);
		if ( min < 10){
			ms = "0" + min;
		}else if(min == 60){
			h++;
			ms = "00";
		}
		return h + ":" + ms;
	}
	
	public static List<Date> getDaysRange(int numOfDays) {
		
	    List<Date> dates = new ArrayList<Date>();
	    Calendar calendar = new GregorianCalendar();

		Date now = new Date();
		calendar.setTime(now);
		
		while ( numOfDays > 0) {
			dates.add(calendar.getTime());
			calendar.add(Calendar.DATE, -1);
			numOfDays--;
		}
	
		return dates;
	}
}
