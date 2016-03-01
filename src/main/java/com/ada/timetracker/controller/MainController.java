package com.ada.timetracker.controller;

import java.io.IOException;

import com.ada.timetracker.App;
import com.ada.timetracker.Config;
import com.ada.timetracker.model.Task;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Main.fxml file controller
 * 
 * @author Alexandrov Dmytro
 */
public class MainController {

	MyTasksController myTaskController;

	@FXML
	private Tab taskTab;
	
	@FXML
	private Tab statisticTab;

	//@FXML
	//private ToggleGroup toggleGroup;

	/**
	 * The constructor. The constructor is called before the initialize()
	 * method.
	 */
	public MainController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
	
		try {
			populateTaskTab();
			populateStatisticTab();
	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void populateTaskTab() throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(App.class.getResource("view/fxml/MyTasks.fxml"));
		AnchorPane taskList = (AnchorPane) loader.load();
		myTaskController = loader.getController();

		taskTab.setContent(taskList);
	}

	@FXML
	private void handleOptions() {

		boolean okClicked = App.getInstance().getOptionsDialog();
		if (okClicked) {
			myTaskController.reinitialize();
		}
	}

	/**
	 * Get selected menuitem Id, cut `sort-` prefix, set new order to Task class
	 * and reinitialize.
	 * 
	 * @param event
	 */
	@FXML
	private void handleSorting(Event event) {
		RadioMenuItem menuItem = (RadioMenuItem) event.getSource();
		Task.setOrder(menuItem.getId().replaceAll("sort-", ""));
		myTaskController.reinitialize();
	}

	@FXML
	private void handleExit(Event event) {
		System.exit(0);
	}

	private void populateStatisticTab() {
	
	    try {
	        // Load the fxml file and create a new stage for the popup.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(App.class.getResource("view/fxml/WorkingChart.fxml"));
	        AnchorPane chart = (AnchorPane) loader.load();
	        statisticTab.setContent(chart);
	        
	      //  Scene scene = new Scene(chart);
	     //   dialogStage.setScene(scene);

	        // Set the persons into the controller.
	    //    WorkingChartController controller = loader.getController();
	       // controller.setPersonData(personData);

	     //   dialogStage.show();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
	}

}