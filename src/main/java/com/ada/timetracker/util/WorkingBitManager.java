package com.ada.timetracker.util;

import java.io.File;
import java.text.DateFormat;
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

import com.ada.timetracker.model.WorkingBit;
import com.ada.timetracker.model.WorkingBitListWrapper;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Saving and reading data from file
 * @author Alexandrov Dmytro
 */
public class WorkingBitManager {
	
	private File file;
	
	private WorkingBitListWrapper bitsWrapper;
	
	
	public WorkingBitManager(File file){
		
		this.file = file;
		
		bitsWrapper = new WorkingBitListWrapper();
		
		if ( file.exists() ){
			loadWorkingBitListFromFile();
		}else{
			saveWorkingBitList();
		}		
	}
	
	/**
	 * Saves the current Working Bit data to the specified file.
	 */
	private void saveWorkingBitList() {
	    try {
	        JAXBContext context = JAXBContext.newInstance(WorkingBitListWrapper.class);
	        Marshaller m = context.createMarshaller();
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	        //bitsWrapper = new WorkingBitListWrapper();
	       // wrapper.setWorkingBitList(workingBitList);

	        // Marshalling and saving XML to the file.
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
	
/*	public List<WorkingBit> getWorkingBitList(){
		return workingBitList;
	}*/
	
	
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
	public void addWorkingBitToFile(WorkingBit workingBit, Boolean working) {
		
	    try {
	        JAXBContext context = JAXBContext.newInstance(WorkingBitListWrapper.class);
	        
	        Marshaller m = context.createMarshaller();
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	        if ( working ){
	        	 bitsWrapper.add(workingBit);
	        }else{
	        	bitsWrapper.insert(workingBit);
	        }
	       
	        m.marshal(bitsWrapper, file);

	        // Save the file path to the registry.
	     //   setPersonFilePath(file);
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
		
		SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd:H");
		SimpleDateFormat formater = new SimpleDateFormat("MM/dd");
	
		GregorianCalendar calendar = new GregorianCalendar();

		HashMap<String, Double> dayMap = new HashMap<>(); 
		Date d;
	
		for (WorkingBit bit : bits){
			try {
				d = parser.parse(bit.getHour());
				calendar.setTime(d);
				String day =  formater.format(calendar.getTime());
				dayMap.merge(day, bit.getTime()/60.0, Double::sum);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return dayMap;
	}

}
