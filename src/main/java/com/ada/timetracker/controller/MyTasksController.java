package com.ada.timetracker.controller;

import java.awt.Desktop;
import java.net.URI;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.ada.timetracker.App;
import com.ada.timetracker.TimeCatcherClient;
import com.ada.timetracker.model.Task;
import com.ada.timetracker.model.TaskList;
import com.ada.timetracker.model.WorkingBit;
import com.ada.timetracker.model.WorkingBitManager;
import com.ada.timetracker.util.TimeHelper;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * MyTasks.fxml controller
 * @author Alexandrov Dmytro
 *
 */
public class MyTasksController {
	
	//private final static Logger LOGGER = Logger.getLogger("log");
	
	private static Boolean working = false;
	private static int timePassed;
    private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
    private static ScheduledFuture<?> taskTimer;
    private static ScheduledFuture<?> taskListTimer;
    

	private static TaskList taskList;
	private static long selectedTaskId;
	private static TimeCatcherClient client;
	
	private  Runnable reinitialize;
	private Runnable taskWorkingTimer;
	

	
	@FXML 
	private VBox taskListPane;
	
	@FXML
	private Label projectNameLabel;
	
	@FXML
	private Label taskTimeLabel;
	
	@FXML
	private Text taskText;
	
	@FXML
	private Hyperlink taskURL;
	
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
	
    	client = TimeCatcherClient.getInstance();
   
        taskList = new TaskList();

        createTaskListPane();
    
        if ( selectedTaskId != 0){
        	selectTaskById(selectedTaskId);
        }

        //Schedule reinitialize every  minute
        if (taskListTimer == null ){
        	reinitialize = ()-> {
				Platform.runLater( () -> initialize());
			};
        	taskListTimer = scheduler.scheduleWithFixedDelay( reinitialize , 60, 60, TimeUnit.SECONDS);
        }
    }
    
    public void reinitialize(){
    	initialize();
    }
    
    private void selectTaskById(long taskId){	
    	selectTask(TaskList.getById(taskId));
    }
    
    /**
     * Creates task pane for each task in My task list and adds them to taskPane,
     *  adds click events to each pane
     */
    private void createTaskListPane(){

    	Map<Long, Task> tasks = taskList.getMyTaskListData();
    	Set<Long> keys = tasks.keySet();

    	taskListPane.getChildren().clear();

    	for (Long key : keys){

    		Task task = tasks.get(key);

			AnchorPane taskPane = new AnchorPane();
			
			Label projectLabel = new Label(task.getProjectTitle());
			projectLabel.setPadding(new Insets( 5, 5, 5, 40 ));
		
			Label timeLabel = new Label(task.getTime());
			timeLabel.setPadding(new Insets( 5, 5, 5, 40 ));
			String title = task.getTitle();
			
			Text text = new Text();
			if ( title.length() >60){
				text.setText(title.substring(0, 60)+ "...");
			}else{
				text.setText(title);
			}
			text.setFill(Color.CORNSILK);
			TextFlow textFlow = new TextFlow();
			
			AnchorPane.setRightAnchor(textFlow, 0.0);
			AnchorPane.setLeftAnchor(textFlow, 40.0);
			
			textFlow.getChildren().addAll(text);
		
			textFlow.setLayoutY(50);
			timeLabel.setLayoutY(22);
			AnchorPane.setRightAnchor(timeLabel, 20.0);
			timeLabel.getStyleClass().add("time-label");
			
			//Add priority pane 
			Pane p = new Pane();
			long priority =  task.getPriorityId();
			p.getStyleClass().add("priority-" + priority );
			p.getStyleClass().add("priority");
			p.setMinWidth(6);
			p.setPrefHeight(110);
			p.setLayoutX(1);
			AnchorPane.setBottomAnchor(p, 0.0);
	
			taskPane.getChildren().addAll(p, projectLabel, timeLabel, textFlow);
			taskPane.setMinHeight(110);
			taskPane.getStyleClass().add("task");
			
			if (priority == 5){
				taskPane.getStyleClass().add("task-priority-high");
			}
			task.setTaskPane(taskPane);
			taskPane.addEventHandler( MouseEvent.MOUSE_CLICKED, onTaskClickHandler(task));
			taskListPane.getChildren().add(taskPane);
			
		}
    }
  
    /**
     * On task list click event
     * @param task
     * @param pane
     * @return void
     */
    private EventHandler<MouseEvent> onTaskClickHandler(Task task){
    	return (event) -> {
			selectTask(task);
	        event.consume();
    	};
    }
    
    private void selectTask(Task task ){
    	taskListPane.getChildren().forEach((node)->{
			int s = node.getStyleClass().indexOf("selected");
			if ( s != -1){
				node.getStyleClass().remove(s);
			}
		});
    	
    	task.getTaskPane().getStyleClass().add("selected");
        selectedTaskId = task.getId();
        
    	controlsEnable();
		showTaskDetails(task);
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
    	App.getInstance().updateTray();
    }
    
    @FXML
    private void handleHyperLink(){   	
    	Task task = TaskList.getById(selectedTaskId);
		String url = "http://tracktime.zetweb.com.ua/index.php/tasksComments?tasks_id=" + task.getId() + "&projects_id=" + task.getProjectId();
		Runnable r = () -> {
			try {
				Desktop.getDesktop().browse(new URI(url));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		};
		Thread th = new Thread(r);
		th.start();      
    }

    private void startTask(){
    	
    	if ( selectedTaskId !=0 && client.startTaskExec(selectedTaskId) ){
    		working = true;
    		startStopTaskButton.getStyleClass().add("stop");
    		taskListPane.setDisable(true);
    		startTimer();
    	}
    }
	
    /**
     * Stop task, but before make shure that task stoped server.
     */
    private void stopTask(){
    	if ( selectedTaskId !=0 && client.stopTaskExec(selectedTaskId) ){
    		working = false;
    		int s = startStopTaskButton.getStyleClass().indexOf("stop");
			if ( s != -1){
				startStopTaskButton.getStyleClass().remove(s);
			}
			taskListPane.setDisable(false);
			stopTimer();
			initialize();
    	}
    }
    
    /**
     * 
     * Called when the user clicks on the finish task button.
     */
    @FXML
    private void handleFinishTask() {
    	
    	if (confirmationDialog()){
			if (selectedTaskId != 0 && client.finishTask(selectedTaskId)) {

				working = false;
				selectedTaskId = 0;
				int s = startStopTaskButton.getStyleClass().indexOf("stop");
				if (s != -1) {
					startStopTaskButton.getStyleClass().remove(s);
				}
				taskListPane.setDisable(false);

				stopTimer();
				initialize();
			}
    	}
    }
    /**
     * Confirmation dialog.
     * @return either clicked Ok or Cancel
     */
    private boolean confirmationDialog(){
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Подтвердить окончание");
    	alert.setHeaderText("Вы уверены, что хотите закончить \nвыполнение задачи?");
 
    	Optional<ButtonType> result = alert.showAndWait();
    	
    	return result.get() == ButtonType.OK ? true : false;
    }
    
   
    /**
     * Show task details on the right part of application
     * @param task
     */
    private void showTaskDetails(Task task) {
    	
        if (task != null) {
        	projectNameLabel.setText(task.getProjectTitle());
        	taskTimeLabel.setText(task.getTime());
        	taskText.setText(task.getTitle() + "\n");
        	taskURL.setVisible(true);
        } else {
        	projectNameLabel.setText("");
        }
    }
    
    /**
     * Enables task controls
     */
    private void controlsEnable(){
    	startStopTaskButton.setDisable(false);
    	finishTaskButton.setDisable(false);
    }

    /**
     * Start count time while working on task and update time on server periodically
     */
    private void startTimer(){
    	timePassed = TimeHelper.stringToSeconds(taskTimeLabel.getText());
    	int startTime = timePassed;

    	Task currentTask = TaskList.getById(selectedTaskId);
    	taskWorkingTimer =  () ->{
			Platform.runLater( () -> taskTimeLabel.setText(TimeHelper.secondsToString(++timePassed)) );

			int taskWorkingTime  = timePassed - startTime;
			//Update task time on server every 120 seconds
			if ( taskWorkingTime % 120  == 0 ){
				TaskList.updateTaskTime(selectedTaskId);
			}

			//Update every minute
			if ( taskWorkingTime % 60  == 0  && taskWorkingTime != 0){
				WorkingBit wb = new WorkingBit(WorkingBitManager.getCurrentHour(), selectedTaskId, currentTask.getTitle(), currentTask.getProjectTitle(), 1);
				WorkingBitManager manager = WorkingBitManager.getInstance();
				
				manager.addWorkingBitToFile(wb, taskWorkingTime == 10);
				
				Platform.runLater( () -> WorkingChartController.update());
				
				
			}
		};
		taskTimer = scheduler.scheduleWithFixedDelay( taskWorkingTimer , 1, 1, TimeUnit.SECONDS);
    }
    
    
    private void stopTimer(){
    	if (taskTimer != null){
    		taskTimer.cancel(true);
    	}
    }
    
    public static boolean isWorking(){
    	return working;
    }
} 
