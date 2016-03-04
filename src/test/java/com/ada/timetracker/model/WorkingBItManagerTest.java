package com.ada.timetracker.model;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.junit.Ignore;
import org.junit.Test;

import com.ada.timetracker.util.WorkingBitManager;

public class WorkingBItManagerTest {
	
	private static final String XML_FILE_ADD = "test_adding.xml";
	private static final String XML_FILE_DEL = "test_deleting.xml";
	
	@Ignore
	@Test
	public void testAddNewWorkingBitToFile(){

		String title = "Add NEW working bit: " +  new Random().nextInt(5000);
		int time = new Random().nextInt(100);
		int id = new Random().nextInt(50);
		id = 10;
		
		WorkingBit wb = new WorkingBit("2016-6-28:1", id, title, "Project add NEW", time);
		
		File file = new File(XML_FILE_ADD);

		WorkingBitManager manager1 = new WorkingBitManager(file);

		manager1.addWorkingBitToFile(wb, false);
		
		WorkingBitManager manager2 = new WorkingBitManager(file);
		List<WorkingBit> list = manager2.getWorkingBitList();
		
		assertEquals("WorkingBit mismatch", title, list.get(list.size()-1).getTaskTitle());
	}
	
	@Ignore
	@Test 
	public void TestAddTimeToLastWorkingBitWithDifferentId(){

		File file = new File(XML_FILE_ADD);
		
		String title = "Test Add working bit time Differnt Id: ";
		String project = "Project Add Time Diff ID" ;
		int time = 500;
		WorkingBit wb = new WorkingBit("2016-6-28:5", 100, title, project, time);
		WorkingBitManager manager1 = new WorkingBitManager(file);
		manager1.addWorkingBitToFile(wb, false);
		
		int time2 = 10;
		WorkingBit wb2 = new WorkingBit("2016-6-28:5", 101, title, project, time2);
		manager1.addWorkingBitToFile(wb2, true);

		WorkingBitManager manager2 = new WorkingBitManager(file);
		List<WorkingBit> list = manager2.getWorkingBitList();
		
		assertEquals("WorkingBit time sum incorrect", time2, list.get(list.size()-1).getTime());
	}
	
	@Ignore
	@Test 
	public void TestAddTimeToLastWorkingBit(){

		File file = new File(XML_FILE_ADD);
		
		String title = "Test Add working bit time: ";
		String project = "Project Add Time" ;
		int time = 100;
		WorkingBit wb = new WorkingBit("2016-6-27:1", 100, title, project, time);
		WorkingBitManager manager1 = new WorkingBitManager(file);
		manager1.addWorkingBitToFile(wb, false);
		
		int time2 = 10;
		WorkingBit wb2 = new WorkingBit("2016-6-27:1", 100, title, project, time2);
		manager1.addWorkingBitToFile(wb2, true);

		WorkingBitManager manager2 = new WorkingBitManager(file);
		List<WorkingBit> list = manager2.getWorkingBitList();
		
		assertEquals("WorkingBit time sum incorrect", time + time2, list.get(list.size()-1).getTime());
	}
	
	@Ignore
	@Test
	public void TestAddTimeToLastWorkingBitOnHourVerge(){
		File file = new File(XML_FILE_ADD);
		
		String title = "Test Add working bit time Hour verge: ";
		String project = "Project Add Time hour verge" ;
		int time = 100;
		WorkingBit wb = new WorkingBit("2016-5-27:1", 57, title, project, time);
		WorkingBitManager manager1 = new WorkingBitManager(file);
		manager1.addWorkingBitToFile(wb, false);
		
		int time2 = 10;
		WorkingBit wb2 = new WorkingBit("2016-5-27:2", 57, title, project, time2);
		manager1.addWorkingBitToFile(wb2, true);

		WorkingBitManager manager2 = new WorkingBitManager(file);
		List<WorkingBit> list = manager2.getWorkingBitList();
		
		assertEquals("WorkingBit time sum incorrect ", time2, list.get(list.size()-1).getTime());
	}
	
	@Ignore
	@Test
	public void TestGetWorkingBitListFromUnexistedFile(){
		
		File file = new File(XML_FILE_ADD );
		file.delete();
		assertFalse("File not deleted", file.exists());
		
		new WorkingBitManager(file);

		assertTrue(file.exists());
	}
	
	@Test
	public void TestGetWorkingBitListSortedByDay(){
		
		File file = new File("test/by_day_sort.xml");
		file.delete();
		WorkingBitManager manager= new WorkingBitManager(file);
		GregorianCalendar calendar = new GregorianCalendar(2016, 1, 1);

		SimpleDateFormat hourF= new SimpleDateFormat("yyyy-MM-dd");
		
		int i = 0 ;
	
		int totalForADay = 0;
		WorkingBit wb;
	
		while ( i < 30 ){
			
			calendar.add(Calendar.DATE, 1);
			int hour = new Random().nextInt(23);
			while ( hour <= 23){
				int times =  new Random().nextInt(3);
				
				while ( times <= 3 ){
					int time = new Random().nextInt(15);
					wb = new WorkingBit( hourF.format(calendar.getTime()) + ":" + hour , 100, "Task", "Project", time);	
					if ( calendar.get(Calendar.DAY_OF_YEAR) == 40){ //2016/02/09
						totalForADay += time;
					}
					manager.addWorkingBitToFile(wb, new Random().nextBoolean());
					times++;
					
				}
				hour++;
			}
			i++;
		}
		
		
		HashMap<String, Double> minutesPerDay = manager.getWorkingBitListByDays();
		assertFalse(true);
		assertEquals(totalForADay, minutesPerDay.get("02/09").doubleValue());
	}
	
	
}
