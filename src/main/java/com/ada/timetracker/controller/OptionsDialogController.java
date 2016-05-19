package com.ada.timetracker.controller;



import java.util.prefs.Preferences;

import com.ada.timetracker.Config;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OptionsDialogController {
	
	private boolean isOkClicked = false;
	private Preferences preferences;
	
	@FXML
	private TextField apiField;
	
	private Stage dialogStage;
	
	@FXML
    private void initialize() {
    }
	
	public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
	
	@FXML
	private void handleOk() {
		preferences.put(Config.API_KEY, apiField.getText());
		isOkClicked = true;
		Config.getInstance().setPreferences();
		dialogStage.close();
	}
	
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    
    public boolean isOkClicked(){
    	return isOkClicked;
    }
    
    public void setOptions(Preferences prefs){
    	this.preferences = prefs;
    	apiField.setText(preferences.get(Config.API_KEY, ""));
    }
}
