package com.ada.timetracker.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.junit.Before;
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
		WorkingBitManager.setFileName(file);
		
		WorkingBitManager manager = WorkingBitManager.getInstance();

		manager.addWorkingBitToFile(wb, false);
		
		List<WorkingBit> list = manager.getWorkingBitList();
		
		assertEquals("WorkingBit mismatch", title, list.get(list.size()-1).getTaskTitle());
	}
	

	//@Test 
	public void TestAddTimeToLastWorkingBitWithDifferentId(){

		File file = new File(XML_FILE_ADD);
		
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
		//assertFalse(true);
		assertEquals(totalForADay, minutesPerDay.get("02/09").doubleValue());
	}

}
