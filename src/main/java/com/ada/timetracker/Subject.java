package com.ada.timetracker;

public interface Subject {
	void addObserver(Observer o);
	void notifyObservers();
}
