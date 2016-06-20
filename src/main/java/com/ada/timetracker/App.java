package com.ada.timetracker;

import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.ada.timetracker.controller.MyTasksController;
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
 * 
 * @author Alexandrov Dmytro
 *
 */
public class App extends Application {
	
	private static App instance = null;

	private Stage primaryStage;
	private TrayIcon trayIcon;
	private Stage dialogStage;
	OptionsDialogController dialogController;
	
	private BorderPane rootLayout;
	


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Log.set();
	    Platform.setImplicitExit(false);
	    
		isAppAlreadyRunning();
		
		// If another instance of app already exists this listener puts main window to front
		AppInstanceManager.setAppInstanceListener(() -> Platform.runLater(() -> primaryStage.toFront()));

		this.primaryStage = primaryStage;
		instance = this;
		this.primaryStage.setTitle("TimeTracker");

		// Load fonts
		Font.loadFont(App.class.getResource("/font/digital-7.ttf").toExternalForm(), 10);
		Font.loadFont(App.class.getResource("/font/digital-7-mono.ttf").toExternalForm(), 10);


		// Load configuration
		Config config = Config.getInstance();
	
		if ( config.getApiKey() == "" ) {
			getOptionsDialog();
		}

		initLayout();
	
		addAppToTray();
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						if (!SystemTray.isSupported()) {
							System.out.println("No system tray support, application exiting.");
							Platform.exit();
							System.exit(0);
						} else {
							primaryStage.hide();
						}
					}
				});
			}
		});
	}
	
	/**
	 * Checks if app already running if so exit  
	 */
	private void isAppAlreadyRunning() {
		if (!AppInstanceManager.registerInstance()) {
			// instance already running.
			System.out.println("Another instance of this application is already running.  Exiting.");
			System.exit(0);
		}
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

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return boolean true if Ok button is clicked
	 */
	public boolean getOptionsDialog() {
		
		if (dialogStage == null){
			return createOptionsDialog();
		}else{
			return showOptionsDialog();
		}

	}

	/**
	 * Shows options dialog
	 * @return true if ok button clicked
	 */
	private boolean showOptionsDialog() {
		dialogController.setOptions(Config.getInstance().getPreferences());
		dialogStage.showAndWait();

		return dialogController.isOkClicked();
	}

	/**
	 * Creates option dialog
	 * @return false if not created
	 */
	private boolean createOptionsDialog() {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("view/fxml/OptionsDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			dialogStage = new Stage();
			dialogStage.setTitle("Настройки");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			dialogController = loader.getController();
			dialogController.setDialogStage(dialogStage);

			return showOptionsDialog();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Get App instance
	 * @return Application
	 */
	public static App getInstance() {
		return instance;
	}
	
	public void updateTray(){
		addAppToTray();
	}
	
	/**
	 * Adds application icon to system tray
	 */
	private void addAppToTray() {
		try {
			SystemTray.getSystemTray().remove(trayIcon);
			SystemTray systemTray = SystemTray.getSystemTray();
			String imagePath;

			if (MyTasksController.isWorking()) {
				imagePath = "/images/icon-yellow.png";
			} else {
				imagePath = "/images/icon.png";

			}
			URL url = getClass().getResource(imagePath);
			java.awt.Image image = ImageIO.read(url);

			trayIcon = new java.awt.TrayIcon(image, "TimeTracker");
			
			//open from try menu item
			MenuItem openItem = new java.awt.MenuItem("Показать");
			java.awt.Font defaultFont = java.awt.Font.decode(null);
			java.awt.Font boldFont = defaultFont.deriveFont(java.awt.Font.BOLD);
			openItem.setFont(boldFont);
			
			//exit tray menu item
			MenuItem exitItem = new java.awt.MenuItem("Выйти");
	            exitItem.addActionListener(event -> {  
	                Platform.exit();
	                systemTray.remove(trayIcon);
	                System.exit(0);
	            });
	            
			//tray icon menu
			PopupMenu popup = new PopupMenu();

			popup.add(openItem);
			popup.addSeparator();
            popup.add(exitItem);
            
			trayIcon.setPopupMenu(popup);
			trayIcon.setImageAutoSize(true);

			trayIcon.addActionListener( event->Platform.runLater( ()->showStage(trayIcon)) );
			openItem.addActionListener(event -> Platform.runLater(()->showStage(trayIcon)));

			systemTray.add(trayIcon);
		
		} catch (java.awt.AWTException | IOException e) {
			System.out.println("Unable to init system tray");
			e.printStackTrace();
		}
	}

	/**
	 * Shows and focuses main application window 
	 * @param trayIcon system tray object
	 */
	private void showStage(TrayIcon trayIcon) {
		primaryStage.show();
		primaryStage.toFront();
	}
}
