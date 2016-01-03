package com.ada.timetracker;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Main application class
 * @author Alexandrov Dmytro
 *
 */
public class App extends Application
{
	private final static Logger LOGGER = Logger.getLogger(App.class.getName());
	private static App instance = null;
	
	private Stage stage;
    private BorderPane rootLayout;
    int count = 1;
    
   /* public static void main( String[] args ){
        launch(args);
    }*/


	@Override
	public void start(Stage primaryStage) throws Exception {
		
		this.stage = primaryStage;
		
		instance= this;
        this.stage.setTitle("TimeTracker");
        
        //TODO  Get Config
     
        Font.loadFont(App.class.getResource("/font/digital-7.ttf").toExternalForm(), 10);
        Font.loadFont(App.class.getResource("/font/digital-7-mono.ttf").toExternalForm(), 10);

        // Set the application icon.
        this.stage.getIcons().add(new Image("/images/button-red.png"));

        
        
 		stage.setMinWidth(600);
 		stage.setMinHeight(430);
      //  setUserAgentStylesheet(STYLESHEET_CASPIAN);
        
        //Start rendering...
        initLayout();
		
	}
	
	//private void 
	
	 /**
     * Initializes the root layout.
     */
    public void initLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("view/fxml/Main.fxml"));
            rootLayout = (BorderPane) loader.load();
            
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
