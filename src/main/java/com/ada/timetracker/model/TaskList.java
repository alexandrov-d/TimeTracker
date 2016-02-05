package com.ada.timetracker.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.ada.timetracker.TimeCatcherClient;

/**
 * Task list model class
 * @author Alexandrov Dmytro
 *
 */
public class TaskList { 

//	private static ObservableList<Task> myTaskList = FXCollections.observableArrayList();
	private static Map<Long, Task> myTaskList; 

	private static TimeCatcherClient client;
	
   /* public  ObservableList<Task> getMyTaskListData() {
        return myTaskList;
    }*/
    public   Map<Long, Task> getMyTaskListData() {
    	return myTaskList;
    }
    
    static  <K,V extends Comparable<? super V>> Map<K, V> entriesSortedByValues(Map<K,V> map) {

		List<Entry<K,V>> sortedEntries = new ArrayList<Entry<K,V>>(map.entrySet());
		
		Collections.sort(sortedEntries, 
		    new Comparator<Entry<K,V>>() {
		        @Override
		        public int compare(Entry<K,V> e1, Entry<K,V> e2) {       
		            return e2.getValue().compareTo(e1.getValue());
		        }
		    }
		);
		Map<K, V> result = new LinkedHashMap<>();
		for ( Entry<K,V> entry : sortedEntries){
			result.put(entry.getKey(), entry.getValue());
		}
		
		return result;
	}

    
    public TaskList(){
    
    	client = TimeCatcherClient.getInstance();
    	
		if ( myTaskList != null ){
			myTaskList.clear(); 
		}else{
			myTaskList = new HashMap<>();
		}
		
    	ua.com.zetweb.tracktime.ws.types.TaskList remoteTasks = client.getTaskList();
    	
 		for ( ua.com.zetweb.tracktime.ws.types.TaskList.Task task : remoteTasks.getTask() ) {
 			//XMLGregorianCalendar  t = task.getDueDate().toString();
 			myTaskList.put(task.getId(), new Task( 
				task.getId(),
				task.getName(),
				task.getProjectId(),
				task.getProjectName(), 
				task.getPriorityId(),
				task.getPriority(),
				task.getActualTime(),
				"sss" //task.getDueDate().toString()
			));
 		}
 		myTaskList = entriesSortedByValues(myTaskList);
	}
    


   /* public static boolean startWorkingOnTask( long taskId){
   
    	return client.startTaskExec(taskId);
    }
    
    public static boolean stopWorkingOnTask(long taskId ){
		return client.stopTaskExec(taskId);
    }*/
    
    public static Task getById( long id ){
    	return myTaskList.get(id);
    }
    
    public static boolean updateTaskTime( long id ){
		return client.updateTaskExecTime(id);
    }
    

}
