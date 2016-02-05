package com.ada.timetracker.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {
	
	private final static Logger LOGGER = Logger.getLogger("log");
	
	public static void set(){
		FileHandler fh;  
	    try {  
	        fh = new FileHandler("time-tracker.log", true);  
	        fh.setLevel(Level.SEVERE);
	        LOGGER.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);  

	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	}

}