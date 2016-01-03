package com.ada.timetracker.controller;

import java.io.IOException;

import com.ada.timetracker.App;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

/**
 * Main.fxml file controller
 * @author Alexandrov Dmytro
 */
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
    	
    	//Load Tab View
    	try {
    		//taskList loaded from MyTasks.fxml Controller
             FXMLLoader loader = new FXMLLoader();
             loader.setLocation(App.class.getResource("view/fxml/MyTasks.fxml"));
             AnchorPane taskList = (AnchorPane) loader.load();

             
             tab1.setContent(taskList);
             
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
  //  public void setMainApp( App mainApp) {
     //   this.mainApp = mainApp;

        // Add observable list data to the table
      //  personTable.setItems(mainApp.getPersonData());
    //}
    
   /* public void setView( AnchorPane pane){
    	 tab1.setContent(pane);
    }*/
    
}