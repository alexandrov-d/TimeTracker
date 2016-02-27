package com.ada.timetracker.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
	
	private List<WorkingBit> workingBitList;
	
	public WorkingBitManager(File file){
		
		this.file = file;
		
		if ( file.exists() ){
			loadWorkingBitListFromFile();
		}else{
			workingBitList = new ArrayList<>();
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

	        WorkingBitListWrapper wrapper = new WorkingBitListWrapper();
	        wrapper.setWorkingBitList(workingBitList);

	        // Marshalling and saving XML to the file.
	        m.marshal(wrapper, file);

	    } catch (Exception e) { 
	    	e.printStackTrace();
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Could not save data");
	        alert.setContentText("Could not save data to file:\n" + file.getPath());
	        alert.showAndWait();
	    }
	}
	
	public List<WorkingBit> getWorkingBitList(){
		return workingBitList;
	}
	
	
	/**
	 * Loads working bits data from the specified file. 
	 */
	private void loadWorkingBitListFromFile() {
	    try {
	        JAXBContext context = JAXBContext.newInstance(WorkingBitListWrapper.class);
	        Unmarshaller um = context.createUnmarshaller();
	  
	        // Reading XML from the file and unmarshalling.
	        WorkingBitListWrapper wrapper = (WorkingBitListWrapper) um.unmarshal(file);
	        
	        workingBitList = wrapper.getWorkingBitList(); 
	        if (workingBitList == null){
	        	workingBitList = new ArrayList<>();
	        }


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
	 */
	public void addWorkingBitToFile(WorkingBit workingBit) {
		
	    try {
	        JAXBContext context = JAXBContext.newInstance(WorkingBitListWrapper.class);
	        
	        Marshaller m = context.createMarshaller();
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	        // Wrapping our person data.
	        WorkingBitListWrapper wrapper = new WorkingBitListWrapper();
	        
	        workingBitList.add(workingBit);
	        wrapper.setWorkingBitList(workingBitList);
	    
	        m.marshal(wrapper, file);

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


	
	
}
