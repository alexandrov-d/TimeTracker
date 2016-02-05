package com.ada.timetracker.controller;



import java.util.Properties;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OptionsDialogController {
	
	private boolean isOkClicked = false;
	private Properties properties;
	
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
		properties.setProperty("apiKey", apiField.getText());
		isOkClicked = true;
		dialogStage.close();
	}
	
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    
    public boolean isOkClicked(){
    	return isOkClicked;
    }
    
    public void setOptions(Properties props){
    	this.properties = props;
    	apiField.setText(properties.getProperty("apiKey", ""));
    }
}
