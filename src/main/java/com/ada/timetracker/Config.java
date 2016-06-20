package com.ada.timetracker;

import java.util.Observable;
import java.util.prefs.Preferences;

public class Config extends Observable{

	private static Config instance = new Config();
	private Preferences prefs;
	
	public final static String API_KEY = "apiKey";
	
	private Config(){
	}
	
	public static Config getInstance(){
		return instance;
	}
	
	/**
	 * Get api key
	 * @return String apikey
	 */
	public String getApiKey(){

		Preferences prefs = Preferences.userNodeForPackage(App.class);
		String apiKey = prefs.get(API_KEY, "");
		return apiKey;
	}
	
	/**
	 * Set properties and notify observers
	 * @param preferences
	 */
	public void setPreferences(){
		setChanged();
		notifyObservers();
	}
	

	public Preferences getPreferences(){
		prefs = Preferences.userNodeForPackage(App.class);
		return prefs;
	}
	
}
