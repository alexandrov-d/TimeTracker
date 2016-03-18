package com.ada.timetracker.model;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Saving and reading data from file
 * @author Alexandrov Dmytro
 */
public class WorkingBitManager {
	
	private static File file = new File("my-time.xml");;
	
	private static WorkingBitManager instance;
	
	private static final SimpleDateFormat bitHourFormat = new SimpleDateFormat("yyyy-MM-dd:H");
	
	private WorkingBitListWrapper bitsWrapper;
	
	private WorkingBitManager(){
		
		bitsWrapper = new WorkingBitListWrapper();
		
		
		if ( file.exists() ){
			loadWorkingBitListFromFile();
		}else{
			saveWorkingBitList();
		}	
	};
	
	public static WorkingBitManager getInstance(){
		if (instance == null){
			return new WorkingBitManager();
		}else{
			return instance;
		}
	}
	
	public static void setFileName(File f){
		file = f;
	}

	
	/**
	 * Saves the current Working Bit data to the specified file.
	 */
	private void saveWorkingBitList() {
	    try {
	        JAXBContext context = JAXBContext.newInstance(WorkingBitListWrapper.class);
	        Marshaller m = context.createMarshaller();
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);


	        m.marshal(bitsWrapper, file);

	    } catch (Exception e) { 
	    	e.printStackTrace();
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Could not save data");
	        alert.setContentText("Could not save data to file:\n" + file.getPath());
	        alert.showAndWait();
	    }
	}

	
	/**
	 * Loads working bits data from the specified file. 
	 */
	private void loadWorkingBitListFromFile() {
	    try {
	        JAXBContext context = JAXBContext.newInstance(WorkingBitListWrapper.class);
	        Unmarshaller um = context.createUnmarshaller();
	  
	        // Reading XML from the file and unmarshalling.
	        bitsWrapper = (WorkingBitListWrapper) um.unmarshal(file);
	        

	    } catch (Exception e) { // catches ANY exception
	    	e.printStackTrace();
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Could not load data");
	        alert.setContentText("Could not load data from file:\n" + file.getPath());

	        alert.showAndWait();
	    }
	}
	

	/**
	 * Add the current working bit data to the specified file.
	 * 
	 * @param workingBit
	 * @param working is we were working from the last adding
	 */
	public void addWorkingBitToFile(WorkingBit workingBit, boolean b) {
		
	    try {
	        JAXBContext context = JAXBContext.newInstance(WorkingBitListWrapper.class);
	        
	        Marshaller m = context.createMarshaller();
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        	bitsWrapper.add(workingBit);
        	
	        m.marshal(bitsWrapper, file);

	    } catch (Exception e) { 
	    	e.printStackTrace();
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Could not save data");
	        alert.setContentText("Could not save data to file:\n" + file.getPath());
	        alert.showAndWait();
	    }
	}


	public List<WorkingBit> getWorkingBitList() {
		return bitsWrapper.getWorkingBitList();
	}

	/**
	 * Get HashMap of day/minutes pairs from workingbitList
	 * @return Format: "02/09" => 100
	 */
	public HashMap<String, Double> getWorkingBitListByDays() {
		
		List<WorkingBit> bits = bitsWrapper.getWorkingBitList();
		
		SimpleDateFormat formater = new SimpleDateFormat("MM/dd");
	
		GregorianCalendar calendar = new GregorianCalendar();

		HashMap<String, Double> dayMap = new HashMap<>(); 
		Date d;
	
		for (WorkingBit bit : bits){
			try {
				d = bitHourFormat.parse(bit.getHour());
				calendar.setTime(d);
				String day =  formater.format(calendar.getTime());
				dayMap.merge(day, bit.getTime()/60.0, Double::sum);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return dayMap;
	}
	
	/**
	 * @return current hour in WorkingBit format '
	 */
	public static String getCurrentHour() {
		return bitHourFormat.format(new Date());
	}

	/**
	 * Get short statistic of working hours
	 * @return Hours {today => ..., thisWeek =>..., lastWeek => ...  }
	 */
	public HashMap<String, Double> getStatistic() {
		List<WorkingBit> bits = bitsWrapper.getWorkingBitList();
		
		Calendar calendar = new GregorianCalendar();
		//Today
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date  today = calendar.getTime();
	    
	    //First day of this week
	    calendar.add(Calendar.DAY_OF_WEEK, -calendar.get(Calendar.DAY_OF_WEEK)+2);
	    Date weekStart = calendar.getTime();
		

	    //Get first day of last week
	    calendar.add(Calendar.DAY_OF_WEEK, -7);
	    Date lastWeekStart = calendar.getTime();

		Date date;
		HashMap<String, Double> statistic = new HashMap<>();
	
		try {
			for (WorkingBit bit : bits){
				date = bitHourFormat.parse(bit.getHour());
				if ( date.after(today) ){
					statistic.merge("today", bit.getTime()/60.0, Double::sum);
				}
				if ( date.after(weekStart)){
					statistic.merge("thisWeek", bit.getTime()/60.0, Double::sum);
					
				}
				if ( date.after(lastWeekStart) && date.before(weekStart)){
					statistic.merge("lastWeek", bit.getTime()/60.0, Double::sum);
				}

			}
		} catch (ParseException e) {			
			e.printStackTrace();
		}
		return statistic;
	}
	
	


}
