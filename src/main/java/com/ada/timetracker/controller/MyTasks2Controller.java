package com.ada.timetracker.controller;

import com.ada.timetracker.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MyTasks2Controller {
	
	@FXML 
	private VBox taskPane;
	
	/*@FXML
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
    	
    		
    	
      //   canvas.setPrefSize(200,200);
         for( int i = 0; i < 2; i++){
        	 AnchorPane canvas = new AnchorPane();
        	 
             	Label l1 = new Label(" a Label" + i);

        //     	canvas.setStyle("-fx-padding: 20px; -fx-background-color: cornsilk");
      //
                Button b1 = new Button("ffffffffff0");
                String s = b1.getStyle();
                System.out.println(s);
		       canvas.getChildren().addAll(l1, b1);
		    
		       canvas.setMinHeight(100);
		       canvas.getStyleClass().add("task");
		      
		       taskPane.getChildren().add(canvas);
       
           
         }
      

         
       
        // Initialize the person table with the two columns.
    //	projectNameColumn.setCellValueFactory(cellData -> cellData.getValue().getProjectName());
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
       // taskTable.setItems(mainApp.getPersonData());
    }
    
	public void setView(Pane pane) {
		
		taskPane.getChildrenUnmodifiable().add(pane);
		//Pane p = clone(pane);
		
		//taskPain.setContent(pane);
	}
}
