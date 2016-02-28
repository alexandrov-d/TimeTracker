package com.ada.timetracker.model;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import com.ada.timetracker.model.WorkingBit;

public class WorkingBitTest {

	@Test
	public void test() {
		
	//	WorkingBit wb = new WorkingBit();

		Calendar calendar = new GregorianCalendar();
		Date trialTime = new Date();
		calendar.setTime(trialTime);
		Date d = calendar.getTime();
	
		
		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
		
		DateFormat sdf = new SimpleDateFormat("y-m-d:h");
	}
}
