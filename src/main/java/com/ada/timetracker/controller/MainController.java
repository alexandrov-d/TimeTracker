package com.ada.timetracker.controller;

import java.io.IOException;

import com.ada.timetracker.App;
import com.ada.timetracker.Config;
import com.ada.timetracker.model.Task;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

/**
 * Main.fxml file controller
 * @author Alexandrov Dmytro
 */
public class MainController {

	MyTasksController myTaskController;
	
	@FXML
	private Tab tab1;
	
	@FXML 
	private ToggleGroup toggleGroup;

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
             myTaskController = loader.getController();
             
             tab1.setContent(taskList);
             
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    private void handleOptions(){
    	Config config = Config.getInstance();
    	boolean okClicked = App.getInstance().showOptionsDialog(config.getProperties());
    	if (okClicked){
    		config.saveProperties();
    		myTaskController.reinitialize();
    	}
    }
    /**
     * Get selected menuitem Id, cut `sort-` prefix, set new order to Task class and reinitialize.
     * @param event
     */
    @FXML 
    private void handleSorting(Event event){
    	RadioMenuItem menuItem = (RadioMenuItem) event.getSource();
    	Task.setOrder(menuItem.getId().replaceAll("sort-", ""));
    	myTaskController.reinitialize();
    }
    
}