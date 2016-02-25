package com.ada.timetracker;

import java.util.Observable;
import java.util.prefs.Preferences;

public class Config extends Observable{

	private static Config instance = new Config();
	private Preferences prefs;
	//private static final String CONFIG_FILE = "config.xml";
	
	//private final static Logger LOGGER = Logger.getLogger("log");
	public final static String API_KEY = "apiKey";
	
	//sprivate final String keys[] = {API_KEY};
	
	private Config(){
	}
	
	public static Config getInstance(){
		return instance;
	}
	
	/**
	 * Load preferences 
	 */
/*	public boolean load(){
		
		//InputStream input = null;
		//try {
			//properties = new Properties();
		prefs = Preferences.userNodeForPackage(App.class);
		if (prefs.get("apiKey", null) == null ){
			return false;
		}
		return true;
			//input = new FileInputStream(CONFIG_FILE);
			//properties.loadFromXML(input);
		//	return true;
//		} catch (IOException e) {
//			LOGGER.info("No config file");
//			return false;
//		}
	}*/
	
	/**
	 * Get api key
	 * @return String apikey
	 */
	public String getApiKey(){
		/*String apiKey = properties.getProperty("apiKey");
		if (apiKey == null){
			System.out.println("No apiKey");
			return "";
		}*/
		Preferences prefs = Preferences.userNodeForPackage(App.class);
		String apiKey = prefs.get(API_KEY, "");
		return apiKey;
	}
	
	/**
	 * Set properties and notify observers
	 * @param preferences
	 */
	public void setPreferences(){
		//Preferences prefs = Preferences.userNodeForPackage(App.class);
//		
//		for ( String key : keys){
//			prefs.put( key, preferences.get(key));
//		}
		setChanged();
		notifyObservers();
		
	}
	

	public Preferences getPreferences(){
		prefs = Preferences.userNodeForPackage(App.class);
		return prefs;
	}
	/**
	 * Save properties to xml file and notify observers
	 */
	/*public void saveProperties(){
	//	properties = new Properties();
	//	OutputStream output = null;
	//	try {
	    Preferences prefs = Preferences.userNodeForPackage(App.class);
		//	output = new FileOutputStream(CONFIG_FILE);
		//	properties.storeToXML(output, null);
	     //  prefs.put("apiKey", file.getPath());
			setChanged();
			notifyObservers();
			LOGGER.info("Config file updated");
	//	} catch (IOException e) {
	//		e.printStackTrace();
	//	}
	}*/
}
