package com.ada.timetracker;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Properties;
import java.util.logging.Logger;

public class Config extends Observable{

	private static Config instance = new Config();
	private Properties properties;
	private static final String CONFIG_FILE = "config.xml";
	
	private final static Logger LOGGER = Logger.getLogger("log");
	//private ArrayList<Observer> observers;
	
	private Config(){
		//observers = new ArrayList<>();
	}
	
	public static Config getInstance(){
		return instance;
	}
	
	/**
	 * Load properties from file
	 */
	public boolean load(){
	
		InputStream input = null;
		try {
			properties = new Properties();
			input = new FileInputStream(CONFIG_FILE);
			properties.loadFromXML(input);
			return true;
		} catch (IOException e) {
			LOGGER.info("No config file");
			return false;
		}
	}
	
	/**
	 * Get api key
	 * @return String apikey
	 */
	public String getApiKey(){
		String apiKey = properties.getProperty("apiKey");
		if (apiKey == null){
			System.out.println("No apiKey");
			return "";
		}
		return apiKey;
	}
	

	public Properties getProperties(){
		return properties;
	}
	/**
	 * Save properties to xml file and notify observers
	 */
	public void saveProperties(){
	//	properties = new Properties();
		OutputStream output = null;
		try {
			output = new FileOutputStream(CONFIG_FILE);
			properties.storeToXML(output, null);
			setChanged();
			notifyObservers();
			LOGGER.info("Config file updated");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void notifyObservers() {
		for ( Observer o : observers){
			o.update();
		}
	}*/
		

}
