package com.ada.timetracker;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.ada.timetracker.util.TimeHelper;
@Ignore
@RunWith(Parameterized.class)
public class TimeHelperTest {
	
	private String timeString;
	private int seconds;
	
	@Parameters
	public static List<Object[]> data(){
		return Arrays.asList(new Object[][]{
			{"00:00:00", 0},
			{"00:00:05", 5},
			{"01:30:08", 5408},
			{"01:00:05", 3605},
			{"11:20:37", 40837},
			
		});
	}
	
	public TimeHelperTest(String timeString, int seconds){
		this.timeString = timeString;
		this.seconds = seconds;
	}

	@Test
	public void ConvertsStringToNumOfSeconds() {
		assertEquals( seconds,TimeHelper.stringToSeconds(timeString));
	}
	
	@Test
	public void ConvertsNumOfSecondsToString() {
		assertEquals( timeString,TimeHelper.secondsToString(seconds));
	}

}
