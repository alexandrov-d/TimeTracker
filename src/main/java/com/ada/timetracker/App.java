package com.ada.timetracker;

import java.io.IOException;
import java.util.Properties;

import com.ada.timetracker.controller.OptionsDialogController;
import com.ada.timetracker.util.AppInstanceManager;
import com.ada.timetracker.util.Log;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Main application class
 * @author Alexandrov Dmytro
 *
 */
public class App extends Application
{
	//private final static Logger LOGGER = Logger.getLogger("log");
	private static App instance = null;
	
	private Stage primaryStage;
    private BorderPane rootLayout;
    int count = 1;
    
   public static void main( String[] args ){
        launch(args);
    }


	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Log.set();
	
		//Terminate application if another instance already started
		if (!AppInstanceManager.registerInstance()) {
			// instance already running.
			System.out.println("Another instance of this application is already running.  Exiting.");
			System.exit(0);
		}
		//If another instance of app already exists this  listener puts main window to front 
		AppInstanceManager.setAppInstanceListener(
			()-> Platform.runLater( 
				()-> primaryStage.toFront()
			)
		);
		
		this.primaryStage = primaryStage;
		instance = this;
        this.primaryStage.setTitle("TimeTracker");
         
        Font.loadFont(App.class.getResource("/font/digital-7.ttf").toExternalForm(), 10);
        Font.loadFont(App.class.getResource("/font/digital-7-mono.ttf").toExternalForm(), 10);

        // Set the application icon.
        this.primaryStage.getIcons().add(new Image("/images/button-red.png"));

 		primaryStage.setMinWidth(600);
 		primaryStage.setMinHeight(430);

      //  setUserAgentStylesheet(STYLESHEET_CASPIAN);

 	    //Load configuration
 		Config config = Config.getInstance();
		if ( !config.load()  && showOptionsDialog(config.getProperties())){	
			config.saveProperties();
		}
		
        //Start rendering...
        initLayout();
        

        /*Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(primaryStage);
        alert.setTitle("No Selection");
        alert.setHeaderText("No Person Selected");
        alert.setContentText("Please select a person in the table.");

        alert.showAndWait();*/
        
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Application Closed by Button(X)");
                        System.exit(0);
                    }
                });
            }
        });
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
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Load options dialog
     * @param props properties 
     * @return boolean true if Ok button is clicked
     */
    public boolean showOptionsDialog(Properties props) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("view/fxml/OptionsDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Настройки");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            OptionsDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
       
            controller.setOptions(props);
            
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
         
         
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Get App instance
     * @return App
     */
    public static App getInstance(){
    	return instance;
    }
}
