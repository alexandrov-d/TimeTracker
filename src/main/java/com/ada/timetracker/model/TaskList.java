package com.ada.timetracker.model;


import com.ada.timetracker.TimeCatcherClient;
import com.ada.timetracker.controller.MyTasksController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Callback;

/**
 * Task list model class
 * @author Alexandrov Dmytro
 *
 */
public class TaskList { 

	private static ObservableList<Task> myTaskList = FXCollections.observableArrayList();
	
	private static long selectedTaskId;
	private Pane taskPane;

	private Callback<Task, Void> onTaskSelection;
	private static TimeCatcherClient client;
	
    public static ObservableList<Task> getMyTaskListData() {
        return myTaskList;
    }
    

    
    public TaskList(Pane pane){
	
    	taskPane = pane;
    	client = TimeCatcherClient.getInstance();
    	myTaskList.clear();
 
    	ua.com.zetweb.tracktime.ws.types.TaskList remoteTasks = client.getTaskList();
 	
 		for ( ua.com.zetweb.tracktime.ws.types.TaskList.Task task : remoteTasks.getTask() ) {
 			//XMLGregorianCalendar  t = task.getDueDate().toString();
 			myTaskList.add(new Task( 
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

	}

    /**
     * Creates task pane for each task in My task list and adds them to taskPane,
     *  adds click events to each pane
     * @param pane A pane to add My task list
     */
    public void addToTaskPane(){
    	
    	taskPane.getChildren().clear();
    	for (Task task : myTaskList) {
    	
			AnchorPane taskCanvas = new AnchorPane();
			taskCanvas.setPadding(new Insets( 5 ));
			Label projectLabel = new Label(task.getProjectTitle());
			projectLabel.setPadding(new Insets( 5 ));
			Label timeLabel = new Label(task.getTime());
			timeLabel.setPadding(new Insets( 5 ));
			String title = task.getTitle();
			Text text = new Text();
			if ( title.length() >60){
				text.setText(title.substring(0, 60)+ "...");
			}else{
				text.setText(title);
			}

			TextFlow textFlow = new TextFlow();
	
			AnchorPane.setRightAnchor(textFlow, 0.0);
			AnchorPane.setLeftAnchor(textFlow, 0.0);
			
			/*Hyperlink taskLink = new Hyperlink("перейти");
			taskLink.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                	
                	String url = "http://www.google.com";	
					Runnable r = () -> {
						try {
							Desktop.getDesktop().browse(new URI(url));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					};
					Thread th = new Thread(r);
					th.start();
            	    e.consume();
                }
            });*/
			textFlow.getChildren().addAll(text);
		
			textFlow.setLayoutY(50);
			timeLabel.setLayoutY(22);
			taskCanvas.getChildren().addAll(projectLabel, timeLabel, textFlow);
			taskCanvas.setMinHeight(100);
			taskCanvas.getStyleClass().add("task");
			taskPane.getChildren().add(taskCanvas);
			
			taskCanvas.addEventHandler( MouseEvent.MOUSE_CLICKED, onTaskClickHandler(task));
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
    		if ( !MyTasksController.isWorking()){
    			//System.out.println(event.getTarget());
    			selectTask(task, (AnchorPane)event.getTarget());
		        event.consume();
    		}
    	};
    }
    
    private void selectTask(Task task, AnchorPane p ){
    	taskPane.getChildren().forEach((node)->{
			int s = node.getStyleClass().indexOf("selected");
			if ( s != -1){
				node.getStyleClass().remove(s);
			}
		});
	//	pane.getChildren().get(1);
    	taskPane.getChildren().get(0).getStyleClass().add("selected");
        selectedTaskId = task.getId();
        onTaskSelection.call(task);
    }
    
    public void selectTask2(Pane pane){
    	//selectTask(myTaskList.get(0));
    }
    
    /**
     * On task selection Callback function.
     * @param value <Task> argument provided to the call method.
     */
    public void onTaskSelection(Callback<Task, Void> value){
    	onTaskSelection = value; 
    }
    

    public static boolean startWorkingOnTask(){
    	if ( selectedTaskId != 0){
    		return client.startTaskExec(selectedTaskId);
    	}
    	return false;
    }
    
    public static boolean stopWorkingOnTask(){
    	if ( selectedTaskId != 0){
    		return client.stopTaskExec(selectedTaskId);
    	}
    	return false;
    }
    
    public static boolean updateTaskTime(){
    	if ( selectedTaskId != 0){
    		return client.updateTaskExecTime(selectedTaskId);
    	}
    	return false;
    }
    
    public static void reload(){
    	
    }
}
