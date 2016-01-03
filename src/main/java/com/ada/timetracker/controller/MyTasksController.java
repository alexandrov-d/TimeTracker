package com.ada.timetracker.controller;

import java.util.Timer;
import java.util.TimerTask;

import com.ada.timetracker.model.Task;
import com.ada.timetracker.model.TaskList;
import com.ada.timetracker.util.TimeHelper;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * MyTasks.fxml controller
 * @author Alexandrov Dmytro
 *
 */
public class MyTasksController {
	
	private static Boolean working = false;
	private static int timePassed;
	private static Timer timer = new Timer();
	private static TimerTask timerTask;
	private static TaskList taskList;
	@FXML 
	private VBox taskPane;
	
	@FXML
	private Label projectNameLabel;
	
	@FXML
	private Label taskTimeLabel;
	
	@FXML
	private Text taskText;
	
	@FXML 
	private Button startStopTaskButton;

	@FXML 
	private Button finishTaskButton;
	


    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        taskList = new TaskList(taskPane);
        
        taskList.addToTaskPane();
        
		taskList.onTaskSelection((task) -> {
			controlsEnable();
			showPersonDetails(task);
			return null;
		});
		
		//taskList.selectTask2(taskPane);
    }
    
    /**
     * Called when the user clicks on a start/stop button.
     */
    @FXML
    private void handleStartStopTask() {
    	if ( !working ){
    		startTask();
    	}else{
    		stopTask();
    	}
    }

    private void startTask(){

    	if ( TaskList.startWorkingOnTask() ){
    		working = true;
    		startStopTaskButton.getStyleClass().add("stop");
    		taskPane.setDisable(true);
    		startTimer();
    	}
    }
    
    private void stopTask(){
    	if ( TaskList.stopWorkingOnTask() ){
    		working = false;
    		int s = startStopTaskButton.getStyleClass().indexOf("stop");
			if ( s != -1){
				startStopTaskButton.getStyleClass().remove(s);
			}
			taskPane.setDisable(false);
			stopTimer();
			//reload task list
			TaskList.reload();
			//initialize();
    	}
    }
    
    /**
     * 
     * Called when the user clicks on the start/stop button.
     */
    @FXML
    private void handleFinishTask() {
    	
    	//TODO It's not finished!!! 

    	TaskList.updateTaskTime();	
    }
    
   
    
    private String showPersonDetails(Task task) {
    	
        if (task != null) {
        	projectNameLabel.setText(task.getProjectTitle());
        	taskTimeLabel.setText(task.getTime());
        	taskText.setText(task.getTitle());
           
        } else {
        	projectNameLabel.setText("");
        }
     //   System.out.println(task);
        return "rrrrrr";
    }
    
    /**
     * Enables task controls
     */
    private void controlsEnable(){
    	startStopTaskButton.setDisable(false);
    	finishTaskButton.setDisable(false);
    }

	/**
	 * Is currently any task at work
	 * @return boolean
	 */
    public static boolean isWorking(){
    	return working;
    }
    
    private void startTimer(){

    	timePassed = TimeHelper.stringToSeconds(taskTimeLabel.getText());
    	timerTask =  new TimerTask() {
			@Override
			public void run() { 
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						taskTimeLabel.setText(TimeHelper.secondsToString(++timePassed));
						
					}
				});
			}
		};
        timer.schedule( timerTask , 1000, 1000);
    
    }
    
    

    private void stopTimer(){
    	timerTask.cancel();
    }

}
