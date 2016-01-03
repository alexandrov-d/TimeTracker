package com.ada.timetracker.util;

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
}
