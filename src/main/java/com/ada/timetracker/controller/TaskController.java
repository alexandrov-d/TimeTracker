package com.ada.timetracker.controller;

import com.ada.timetracker.App;
import com.ada.timetracker.client.TaskList.Task;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class TaskController {
	
	@FXML
	private Label label1;
/*	@FXML
    private TableView<com.ada.timetracker.model.Task> taskTable;
	
    @FXML
    private TableColumn<com.ada.timetracker.model.Task, String> projectNameColumn;*/
    
    // Reference to the main applicatio
    private App mainApp;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
    	//projectNameColumn.setCellValueFactory(cellData -> cellData.getValue().getProjectName());
        //lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        
        // Clear person details.
     //   showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
       /* personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));*/
    }
    
    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
      //  taskTable.setItems(mainApp.getPersonData());
    }
    
  
}
