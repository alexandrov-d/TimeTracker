package com.ada.timetracker;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import com.ada.timetracker.client.TaskList;
import com.ada.timetracker.client.TimeCatcherInterface;
import com.ada.timetracker.client.TimeCatcherService;
import com.ada.timetracker.controller.MainController;
import com.ada.timetracker.controller.MyTasks2Controller;
import com.ada.timetracker.controller.MyTasksController;
import com.ada.timetracker.controller.TaskController;
import com.ada.timetracker.model.Task;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application
{
    
	private Stage stage;
    private BorderPane rootLayout;
    private  MainController controller;
    private MyTasks2Controller mcontroller;
    
    private ObservableList<Task> taskData = FXCollections.observableArrayList();
    
    public static void main( String[] args )
    {
        launch(args);

		
    }
    
    public ObservableList<Task> getTaskData() {
        return taskData;
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.stage = primaryStage;
	//	ResourceBundle bg;
        this.stage.setTitle("TimeTracker");
        System.out.println(System.getProperty("os.name"));
        
 		TimeCatcherService service = new TimeCatcherService();
 		 
 		TimeCatcherInterface server = service.getTimeCatcherEndpoint();
  
 		TaskList tl = server.getTaskList("45c48cce2e2d7fbdea1afc51c7c6ad26" );
 
 		for (com.ada.timetracker.client.TaskList.Task task : tl.getTask()) {
 			taskData.add(new Task( task.getProjectName(), "ffff"));
 			System.out.println(task.getProjectName());
 		}
        
        
        
        
        
        
        //setUserAgentStylesheet(STYLESHEET_CASPIAN);
        initRootLayout();
        showPersonOverview();
     
		
	}
	
	 /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("view/Main.fxml"));
            rootLayout = (BorderPane) loader.load();
            controller = loader.getController();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("view/MyTasks2.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
           // rootLayout.
            // Set person overview into the center of root layout.
          //  System.out.println(tab1);
         
          //  addTasks(personOverview);
            
            
            controller.setView(personOverview);
            
            
            mcontroller = loader.getController();
            mcontroller.setMainApp(this);
          //  Tab tab1 = controller.getTab();
          //  tab1.setText("Ffffffffffff");
        
         //   controller.setMainApp(this);
           // rootLayout.setCenter(personOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    
    public ObservableList<Task> getPersonData() {
        return taskData;
    }
    
    private void addTasks(AnchorPane pane){
    	
    }
    
}
