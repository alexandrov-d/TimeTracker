package com.ada.timetracker;

import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

import ua.com.zetweb.tracktime.ws.TimeCatcherInterface;
import ua.com.zetweb.tracktime.ws.TimeCatcherService;
import ua.com.zetweb.tracktime.ws.types.TaskId;
import ua.com.zetweb.tracktime.ws.types.TaskList;

/**
 * Adapter for remote TimecCatcherService.
 * It is prepearing calls for auto-generated from wsdls TimeCatcherService.
 * @author Alexandrov Dmytro
 *
 */
public class TimeCatcherClient implements java.util.Observer{
	
	private final TimeCatcherService service;
	private final TimeCatcherInterface server;
	private static TimeCatcherClient instance = new TimeCatcherClient();
	private String apiKey;
	private TaskId clientTask = new TaskId();

	private final static Logger LOGGER = Logger.getLogger("log");
	
	private TimeCatcherClient(){
		this.service = new TimeCatcherService();
		this.server = service.getTimeCatcherEndpoint();
		Config config = Config.getInstance();
		this.apiKey = config.getApiKey();
		config.addObserver(this);
		this.clientTask.setApiKey(apiKey);	
		
	}
	
	public static TimeCatcherClient getInstance(){
		return instance;
	}
	
	public TaskList getTaskList(){
		return server.getTaskList(apiKey);
	}
	
	public boolean startTaskExec( long taskId ){
		clientTask.setTaskId(taskId);
		int i = server.startTaskExec(clientTask);
		if ( i != 0){
			LOGGER.log(Level.INFO, "A method returned:" + i, i);
			return false;
		}else{
			return true;
		}
	}
	
	public boolean stopTaskExec( long taskId ){
		clientTask.setTaskId(taskId);
		int i = server.stopTaskExec(clientTask);
		if ( i != 0){
			LOGGER.log(Level.INFO, "A method returned:" + i, i);
			return false;
		}else{
			return true;
		}
	}
	
	public boolean finishTask( long taskId ){
		clientTask.setTaskId(taskId);
		int i =  server.finishTask(clientTask);
		if ( i != 0){
			LOGGER.log(Level.INFO, "Finish Task method of WS returned:" + i, i);
		}
		LOGGER.log(Level.INFO, "Finish Task method Meassge:" + i, i);
		return i != -1 ? true : false;
	}
	
	public boolean updateTaskExecTime( long taskId ){
		clientTask.setTaskId(taskId);
		int i = server.updateTaskExecTime(clientTask);
		if ( i != 0){
			LOGGER.log(Level.INFO, "Update Task method of WS returned:" + i, i);
			return false;
		}else{
			LOGGER.log(Level.INFO, "Updating Task...");
			return true;
		}
	}

	/*@Override
	public void update() {
		this.apiKey = Config.getInstance().getApiKey();
	}*/

	@Override
	public void update(Observable observable, Object arg) {
		if ( observable instanceof Config){
			Config config = (Config)observable;
			this.apiKey = config.getApiKey();
		}
		
	}

}
