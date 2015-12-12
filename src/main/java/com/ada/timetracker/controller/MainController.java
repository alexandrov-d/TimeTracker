package com.ada.timetracker.controller;

import com.ada.timetracker.App;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

public class MainController {

	@FXML
	private Tab tab1;


    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public MainController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {


      
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp( App mainApp) {
     //   this.mainApp = mainApp;

        // Add observable list data to the table
      //  personTable.setItems(mainApp.getPersonData());
    }
    
    public void setView( AnchorPane pane){
    	 tab1.setContent(pane);
    }
    
}