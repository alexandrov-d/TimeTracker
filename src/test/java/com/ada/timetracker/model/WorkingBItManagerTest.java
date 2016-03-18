package com.ada.timetracker.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.junit.Ignore;
import org.junit.Test;

public class WorkingBItManagerTest {
	
	private static final String XML_FILE_ADD = "test_adding.xml";
	private static final String XML_FILE_DEL = "test_deleting.xml";
	
	/*@Before	public void createTestXmlFileWithData(){
		File file = new File("my-time-test.xml");
		file.delete();
		WorkingBitManager.setFileName(file);
		WorkingBitManager manager = WorkingBitManager.getInstance();
		
		String hour = WorkingBitManager.getCurrentHour();
		WorkingBit wb;
		for( int i = 0; i < 15; i++ ){
			wb = new WorkingBit(hour, 100, "Простая  задача", "Простой проэкт", 1);
			manager.addWorkingBitToFile(wb, true);
		}
	}*/
	@Test 
	public void TestgetWorkingBitSummary(){
		
		File file = new File("my-time-test.xml");
		file.delete();
		WorkingBitManager.setFileName(file);
		WorkingBitManager manager = WorkingBitManager.getInstance();
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		String today = dateFormat.format(date);
	    
	    Calendar calendar = new GregorianCalendar();
	    int todayDayNumber = calendar.get(Calendar.DAY_OF_WEEK);
	    
	    //First day of this week
	    calendar.add(Calendar.DAY_OF_WEEK, -calendar.get(Calendar.DAY_OF_WEEK)+2);
		String weekStart = dateFormat.format(calendar.getTime());
		
	    //Some day of this week if today not monday
	    String thisWeekDay = "";

	    if ( todayDayNumber > 3){
		    calendar.add(Calendar.DAY_OF_WEEK, 1);
		    thisWeekDay = dateFormat.format(calendar.getTime());
	    }

	    //Get first day of last week
	    calendar.add(Calendar.DAY_OF_WEEK, -7);
	    String lastWeekStart = dateFormat.format(calendar.getTime());
	
	    //Get first day of last week + 2
	    calendar.add(Calendar.DAY_OF_WEEK, 2);
	    String lastWeekDay = dateFormat.format(calendar.getTime());

	    
	    //Populate last week ours
	    WorkingBit wb = new WorkingBit(lastWeekStart + ":10", 100, "TestgetWorkingBitSummary", "WorkingBItManagerTest", 50);
	    manager.addWorkingBitToFile(wb, true);
	    wb = new WorkingBit(lastWeekStart + ":13", 100, "TestgetWorkingBitSummary", "WorkingBItManagerTest", 25);
	    manager.addWorkingBitToFile(wb, true);
	    wb = new WorkingBit(lastWeekDay + ":16", 100, "TestgetWorkingBitSummary", "WorkingBItManagerTest", 30);
	    manager.addWorkingBitToFile(wb, true);
	    wb = new WorkingBit(lastWeekDay + ":18", 100, "TestgetWorkingBitSummary", "WorkingBItManagerTest", 45);
	    manager.addWorkingBitToFile(wb, true);
	    
	    //Populate this week hours
	    wb = new WorkingBit(weekStart + ":9", 100, "TestgetWorkingBitSummary", "WorkingBItManagerTest", 50);
	    manager.addWorkingBitToFile(wb, true);
	    wb = new WorkingBit(weekStart + ":14", 100, "TestgetWorkingBitSummary", "WorkingBItManagerTest", 35);
	    manager.addWorkingBitToFile(wb, true);
	    
	    if (todayDayNumber > 3){
	       	wb = new WorkingBit( thisWeekDay + ":10", 100, "TestgetWorkingBitSummary", "WorkingBItManagerTest", 30);
	 	    manager.addWorkingBitToFile(wb, true);
	 	    wb = new WorkingBit( thisWeekDay + ":18", 100, "TestgetWorkingBitSummary", "WorkingBItManagerTest", 45);
	 	    manager.addWorkingBitToFile(wb, true);
	 	  
	    }
	    if (todayDayNumber > 2){
    	    wb = new WorkingBit( today + ":10", 100, "TestgetWorkingBitSummary", "WorkingBItManagerTest", 20);
	 	    manager.addWorkingBitToFile(wb, true);
	 	    wb = new WorkingBit( today + ":18", 100, "TestgetWorkingBitSummary", "WorkingBItManagerTest", 15);
	 	    manager.addWorkingBitToFile(wb, true);
	    }
	 
	    assertEquals( (50 + 25 + 30 + 45)/60.00, manager.getStatistic().get("lastWeek"), 0.01);
	    
	    if (todayDayNumber > 3 ){
	    	assertEquals( ( 50 + 35 + 30 + 45 + 20 + 15 )/60.00, manager.getStatistic().get("thisWeek"), 0.01);
	    	assertEquals( ( 20 + 15 )/60.00, manager.getStatistic().get("today"), 0.01);
	    }else if ( todayDayNumber == 3 ){
	    	assertEquals( ( 50 + 35 )/60.00, manager.getStatistic().get("thisWeek"), 0.01);
	    	assertEquals( ( 20 + 15 )/60.00, manager.getStatistic().get("today"), 0.01);
	    }else {
	    	assertEquals( ( 50 + 35 )/60.00, manager.getStatistic().get("thisWeek"), 0.01);
	    	assertEquals( ( 50 + 35 )/60.00, manager.getStatistic().get("today"), 0.01);
	    }
		
	}
	
	//@Test
	public void testAddWorkingBitsToFile(){
		File file = new File("my-time-test.xml");
		file.delete();
		WorkingBitManager.setFileName(file);
		WorkingBitManager manager = WorkingBitManager.getInstance();
		
		String hour = WorkingBitManager.getCurrentHour();
		WorkingBit wb;
		for( int i = 0; i < 15; i++ ){
			wb = new WorkingBit(hour, 100, "Простая  задача", "Простой проэкт", 1);
			manager.addWorkingBitToFile(wb, true);
		}
		
		for( int i = 0; i < 20; i++ ){
			wb = new WorkingBit(hour, 101, "Сложная  задача", "Простой проэкт", 1);
			if ( i == 9){
				int h = Integer.parseInt( hour.substring( hour.indexOf(':') + 1, hour.length()) ) + 1;
				hour = hour.substring( 0, hour.indexOf(':') ) + ':' + h;
			}
			manager.addWorkingBitToFile(wb, true);
		}
		wb = new WorkingBit(hour, 101, "Сложная  задача", "Простой проэкт", 1);
		manager.addWorkingBitToFile(wb, false);
		
		for( int i = 0; i < 20; i++ ){
			wb = new WorkingBit(hour, 100, "Простая  задача", "Простой проэкт", 1);
			manager.addWorkingBitToFile(wb, true);
		}
		List<WorkingBit> list = manager.getWorkingBitList();
	
		assertEquals("WorkingBit mismatch", 15, list.get(list.size()-5).getTime());
		assertEquals("WorkingBit mismatch", 10, list.get(list.size()-4).getTime());
		assertEquals("WorkingBit mismatch", 0, list.get(list.size()-3).getTime());
		assertEquals("WorkingBit mismatch", 1, list.get(list.size()-2).getTime());
		assertEquals("WorkingBit mismatch", 20, list.get(list.size()-1).getTime());
	}
	

	@Test
	public void testAddNewWorkingBitToFile(){
		
		String title = "Add NEW working bit: " +  new Random().nextInt(5000);
		int time = new Random().nextInt(100);
		int id = new Random().nextInt(50);
		id = 10;
		
		WorkingBit wb = new WorkingBit("2016-6-28:1", id, title, "Project add NEW", time);
		
		File file = new File(XML_FILE_ADD);
		file.delete();
		WorkingBitManager.setFileName(file);
		
		WorkingBitManager manager = WorkingBitManager.getInstance();

		manager.addWorkingBitToFile(wb, false);
		
		List<WorkingBit> list = manager.getWorkingBitList();
		
		assertEquals("WorkingBit mismatch", title, list.get(list.size()-1).getTaskTitle());
	}
	

	//@Test 
	public void TestAddTimeToLastWorkingBitWithDifferentId(){

		File file = new File(XML_FILE_ADD);
		file.delete();
		String title = "Test Add working bit time Differnt Id: ";
		String project = "Project Add Time Diff ID" ;
		int time = 500;
		WorkingBit wb = new WorkingBit("2016-6-28:5", 100, title, project, time);
		WorkingBitManager.setFileName(file);
		WorkingBitManager manager = WorkingBitManager.getInstance();
		manager.addWorkingBitToFile(wb, false);
		
		int time2 = 10;
		WorkingBit wb2 = new WorkingBit("2016-6-28:5", 101, title, project, time2);
		manager.addWorkingBitToFile(wb2, true);

		List<WorkingBit> list = manager.getWorkingBitList();
		
		assertEquals("WorkingBit time sum incorrect", time2, list.get(list.size()-1).getTime());
	}
	
	//@Test 
	public void TestAddTimeToLastWorkingBit(){

		File file = new File(XML_FILE_ADD);
		
		String title = "Test Add working bit time: ";
		String project = "Project Add Time" ;
		int time = 100;
		WorkingBit wb = new WorkingBit("2016-6-27:1", 100, title, project, time);
		
		WorkingBitManager.setFileName(file);
		WorkingBitManager manager = WorkingBitManager.getInstance();
		manager.addWorkingBitToFile(wb, false);
		
		int time2 = 10;
		WorkingBit wb2 = new WorkingBit("2016-6-27:1", 100, title, project, time2);
		manager.addWorkingBitToFile(wb2, true);

		List<WorkingBit> list = manager.getWorkingBitList();
		
		assertEquals("WorkingBit time sum incorrect", time + time2, list.get(list.size()-1).getTime());
	}
	
	
	//@Test
	public void TestAddTimeToLastWorkingBitOnHourVerge(){
		File file = new File(XML_FILE_ADD);
		
		String title = "Test Add working bit time Hour verge: ";
		String project = "Project Add Time hour verge" ;
		int time = 100;
		WorkingBit wb = new WorkingBit("2016-5-27:1", 57, title, project, time);
		WorkingBitManager.setFileName(file);
		WorkingBitManager manager = WorkingBitManager.getInstance();
		manager.addWorkingBitToFile(wb, false);
		
		int time2 = 10;
		WorkingBit wb2 = new WorkingBit("2016-5-27:2", 57, title, project, time2);
		manager.addWorkingBitToFile(wb2, true);

		List<WorkingBit> list = manager.getWorkingBitList();
		
		assertEquals("WorkingBit time sum incorrect ", time2, list.get(list.size()-1).getTime());
	}
	
	
	//@Test
	public void TestGetWorkingBitListFromUnexistedFile(){
		
		File file = new File(XML_FILE_ADD );
		file.delete();
		assertFalse("File not deleted", file.exists());
		
		WorkingBitManager.setFileName(file);
		WorkingBitManager.getInstance();

		assertTrue(file.exists());
	}
	@Ignore
	//@Test
	public void TestGetWorkingBitListSortedByDay(){
		
		File file = new File("test/by_day_sort.xml");
		file.delete();
		WorkingBitManager.setFileName(file);
		WorkingBitManager manager = WorkingBitManager.getInstance();
		GregorianCalendar calendar = new GregorianCalendar(2016, 1, 1);

		SimpleDateFormat hourF = new SimpleDateFormat("yyyy-MM-dd");
		
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
		//assertFalse(true);
		assertEquals(totalForADay, minutesPerDay.get("02/09").doubleValue(), 0.001);
	}

}
