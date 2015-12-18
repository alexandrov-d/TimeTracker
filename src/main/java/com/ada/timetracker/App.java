package com.ada.timetracker;

import java.io.IOException;

import javax.swing.DefaultBoundedRangeModel;

import com.ada.timetracker.controller.MainController;
import com.ada.timetracker.controller.MyTasksController;
import com.ada.timetracker.model.TaskList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */

public class App extends Application
{
	private static App instance = null;
	
	private Stage stage;
    private BorderPane rootLayout;
    private MainController controller;
    private MyTasksController mcontroller;
    
    
   /* public static void main( String[] args ){
        launch(args);
    }*/


	@Override
	public void start(Stage primaryStage) throws Exception {
		
		this.stage = primaryStage;
		
 instance= this;
        this.stage.setTitle("TimeTracker");
      //  System.out.println(System.getProperty("os.name"));
        
        
        //TODO  Get Config
     
    
   //     AppController controller = new AppController(); 
 
 		stage.setMinWidth(600);
 		stage.setMinHeight(430);
      //  setUserAgentStylesheet(STYLESHEET_CASPIAN);
        
        //Start rendering...
        initLayout();
        //showPersonOverview();
		
	}
	
	 /**
     * Initializes the root layout.
     */
    public void initLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("view/fxml/Main.fxml"));
            rootLayout = (BorderPane) loader.load();
           // controller = loader.getController();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static App getInstance(){
    	return instance;
    }
}
