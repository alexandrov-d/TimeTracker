package com.ada.timetracker;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.junit.Test;

import com.ada.timetracker.util.TimeHelper;


public class TimeHelperTest {
	
	@Test
	public void getDaysTest() {
		
		int numOfDays = -10;
		List<Date>  days = TimeHelper.getDaysRange(numOfDays);
		assertEquals(0, days.size());
		
		numOfDays = 30;
		days = TimeHelper.getDaysRange(numOfDays);
		assertEquals(numOfDays, days.size());
		
		
		DateFormat formater = new SimpleDateFormat("M/d");
		for ( int i = days.size() -1; i > 0; i--){
			System.out.println(formater.format(days.get(i)));
		}
    	
	}
}
