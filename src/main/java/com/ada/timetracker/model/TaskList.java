package com.ada.timetracker.model;


import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import com.ada.timetracker.App;
import com.ada.timetracker.client.TimeCatcherInterface;
import com.ada.timetracker.client.TimeCatcherService;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class TaskList {
	
	private static ObservableList<Task> myTaskList = FXCollections.observableArrayList();
	
	private Task selected;
	
	private Callback<Task, Void> cb;
//	private Task selected;
	
    public static ObservableList<Task> getMyTaskListData() {
        return myTaskList;
    }
    
    public TaskList(){
		
		TimeCatcherService service = new TimeCatcherService();
		  
 		TimeCatcherInterface server = service.getTimeCatcherEndpoint();
  
 		com.ada.timetracker.client.TaskList tasks = server.getTaskList("45c48cce2e2d7fbdea1afc51c7c6ad26" );
 
 		for (com.ada.timetracker.client.TaskList.Task task : tasks.getTask()) {
 			myTaskList.add(new Task( 
				task.getProjectName(), 
				task.getName()
			));	
 		}
	}
    

    public void addTo(Pane pane){
  
    	App app = App.getInstance();
    	
    	for (Task task : myTaskList) {
			AnchorPane taskCanvas = new AnchorPane();
		
			Label l1 = new Label(task.getProjectName());
			Hyperlink hp = new Hyperlink(task.getTaskName());
			
			hp.setOnAction(new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(ActionEvent e) {
	               
	                	String url = "http://www.google.com";
                	//  app.getHostServices().showDocument(hp.getText());
                	   // app.getHostServices().showDocument("www.google.com");
                	 // if (Desktop.isDesktopSupported()) {
                		//  System.out.println(Desktop.isDesktopSupported());
                	  
                	 
                
						try {
								
							Runnable r = () -> {
								try {
									Desktop.getDesktop().browse(new URI(url));
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							};
						
							System.out.println("runn");
							Thread th = new Thread(r);
							th.start();
						
							
						} catch (Exception   e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                		 
						System.out.println("Done");
                	    e.consume();
	                }
	            });

		//	Label l2 = new Label("<a href=''>" + + "</a>");
			hp.setLayoutY(50);
			taskCanvas.getChildren().addAll(l1, hp);

			taskCanvas.setMinHeight(100);
			taskCanvas.getStyleClass().add("task");

			pane.getChildren().add(taskCanvas);
			
			EventHandler<MouseEvent> eventHandler = (event) -> {
		        selected = task;
		        cb.call(task);
		        event.consume();
	    	};
			
		
			taskCanvas.addEventHandler( MouseEvent.MOUSE_CLICKED, eventHandler);
			
		}
    	
     	
					
        			
			
                /*(observable, oldValue, newValue) -> showPersonDetails(newValue));*/
    }
    
    public void callBack(Callback<Task, Void> value){
    	cb = value; 
    }
    
    
}
