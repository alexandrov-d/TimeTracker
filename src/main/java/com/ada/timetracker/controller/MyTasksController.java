package com.ada.timetracker.controller;

import com.ada.timetracker.App;
import com.ada.timetracker.model.Task;
import com.ada.timetracker.model.TaskList;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class MyTasksController {
	
	@FXML 
	private VBox taskPane;
	
	@FXML
	private Label projectNameLabel;
	
	/*@FXML
    private TableView<com.ada.timetracker.model.Task> taskTable;*/
	
    @FXML
    private TableColumn<com.ada.timetracker.model.Task, String> firstNameColumn;
    
    // Reference to the main applicatio
    private App mainApp;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	
        TaskList taskList = new TaskList();
        taskList.addTo(taskPane);

		taskList.callBack((t) -> {
			showPersonDetails(t);
			return null;
		});
        
        /*
        taskList.callBack(new Callback<Task, Void>() {
            	public Void call(Task t) {
            		showPersonDetails(t);
            		return null;
            	}
         	}
		);*/
    }
    
    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
        
        // Add observable list data to the table
       // taskTable.setItems(mainApp.getPersonData());
    }
    
    private String showPersonDetails(Task task) {
    	
        if (task != null) {
        	projectNameLabel.setText(task.getProjectName());
           
        } else {
        	projectNameLabel.setText("");
        }
        System.out.println(task);
        return "rrrrrr";
    }
	

}
