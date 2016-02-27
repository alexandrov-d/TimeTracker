package com.ada.timetracker.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import com.ada.timetracker.util.WorkingBitManager;

public class WorkingBItManagerTest {
	
	private static final String XML_FILE_ADD = "test_adding.xml";
	private static final String XML_FILE_DEL = "test_deleting.xml";

	@Test
	public void testAddNewWorkingBitToFile(){
		
		String title = "Сделать все как нужно : " +  new Random().nextInt(5000);
		int time = new Random().nextInt(100);
		int id = new Random().nextInt(50);
		id = 100;
		
		WorkingBit wb = new WorkingBit("2016-6-27:1", id, title, "Главный проект", time);
		
		File file = new File(XML_FILE_ADD);

		WorkingBitManager manager1 = new WorkingBitManager(file);

		manager1.addWorkingBitToFile(wb);
		
		WorkingBitManager manager2 = new WorkingBitManager(file);
		List<WorkingBit> list = manager2.getWorkingBitList();
		assertEquals("WorkingBit mismatch", title, list.get(list.size()-1).getTaskTitle());
	}
	
	
	@Test
	public void testGetWorkingBitListFromUnexistedFile(){
		
		File file = new File(XML_FILE_DEL);
		file.delete();
		assertFalse("File not deleted", file.exists());
		
		new WorkingBitManager(file);

		assertTrue(file.exists());
	}
}
