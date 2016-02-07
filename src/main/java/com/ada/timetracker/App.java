package com.ada.timetracker;

import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

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
	// private final static Logger LOGGER = Logger.getLogger("log");
	private static App instance = null;

	private Stage primaryStage;
	private BorderPane rootLayout;
	
//	private java.awt.TrayIcon trayIcon;
	// int count = 1;

	// private static final String iconImageRun = "/";
	// private static final String iconImageRun = "/";

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Log.set();
	    Platform.setImplicitExit(false);
	    
	    
		// javax.swing.SwingUtilities.invokeLater(()->addAppToTray());

		// Terminate application if another instance already started
		if (!AppInstanceManager.registerInstance()) {
			// instance already running.
			System.out.println("Another instance of this application is already running.  Exiting.");
			System.exit(0);
		}
		// If another instance of app already exists this listener puts main
		// window to front
		AppInstanceManager.setAppInstanceListener(() -> Platform.runLater(() -> primaryStage.toFront()));

		this.primaryStage = primaryStage;
		instance = this;
		this.primaryStage.setTitle("TimeTracker");

		// Load fonts
		Font.loadFont(App.class.getResource("/font/digital-7.ttf").toExternalForm(), 10);
		Font.loadFont(App.class.getResource("/font/digital-7-mono.ttf").toExternalForm(), 10);

		// Set the application icon.
		// this.primaryStage.getIcons().add(new
		// Image("/images/button-red.png"));

		// primaryStage.setMinWidth(600);
		// primaryStage.setMinHeight(430);

		// setUserAgentStylesheet(STYLESHEET_CASPIAN);

		// Load configuration
		Config config = Config.getInstance();
		if (!config.load() && showOptionsDialog(config.getProperties())) {
			config.saveProperties();
		}

		// Start rendering...
		initLayout();

		/*
		 * Alert alert = new Alert(AlertType.WARNING);
		 * alert.initOwner(primaryStage); alert.setTitle("No Selection");
		 * alert.setHeaderText("No Person Selected"); alert.setContentText(
		 * "Please select a person in the table.");
		 * 
		 * alert.showAndWait();
		 */
	
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
						    javax.swing.SwingUtilities.invokeLater(()->addAppToTray());
						}
					}
				});
			}
		});
	}

	// private void

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
	 * 
	 * @param props
	 *            properties
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
	 * 
	 * @return App
	 */
	public static App getInstance() {
		return instance;
	}

	private void addAppToTray() {
		try {
			//java.awt.Toolkit.getDefaultToolkit();
			SystemTray systemTray = SystemTray.getSystemTray();
			String imagePath;

			if (MyTasksController.isWorking()) {
				imagePath = "/images/icon-yellow.png";
			} else {
				imagePath = "/images/icon.png";

			}
			URL url = getClass().getResource(imagePath);
			java.awt.Image image = ImageIO.read(url);

			TrayIcon trayIcon = new java.awt.TrayIcon(image, "TimeTracker");
			
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
	            
			//try icon menu
			PopupMenu popup = new PopupMenu();
			popup.add(openItem);
			popup.addSeparator();
            popup.add(exitItem);
            
			trayIcon.setPopupMenu(popup);
			trayIcon.setImageAutoSize(true);

			trayIcon.addActionListener( event->Platform.runLater( ()->showStage(trayIcon)) );
			openItem.addActionListener(event -> Platform.runLater(()->showStage(trayIcon)));

			systemTray.add(trayIcon);
			//systemTray.remove(trayIcon);
			//tray.
			// URL imageLoc = new URL(icon);
		} catch (java.awt.AWTException | IOException e) {
			System.out.println("Unable to init system tray");
			e.printStackTrace();
		}
	}

	private void showStage(TrayIcon trayIcon) {
		SystemTray.getSystemTray().remove(trayIcon);
		primaryStage.show();
		primaryStage.toFront();
	}

}
