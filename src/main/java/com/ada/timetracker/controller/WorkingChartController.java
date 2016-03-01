package com.ada.timetracker.controller;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.ada.timetracker.util.TimeHelper;
import com.ada.timetracker.util.WorkingBitManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Tooltip;

public class WorkingChartController {
	
	@FXML
    private BarChart<String, Number> barChart;

    @FXML
    private CategoryAxis xAxis;
    
    @FXML
    private NumberAxis yAxis;
    

    private ObservableList<String> dayNumbers = FXCollections.observableArrayList();

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
     
    	List<Date> dates = TimeHelper.getDaysRange(15);
    	DateFormat formater = new SimpleDateFormat("MM/dd");
    	
    	for ( int i = dates.size() -1; i >= 0; i--){
    		dayNumbers.add(formater.format(dates.get(i)));
		}
    
        // Assign the month names as categories for the horizontal axis.
        xAxis.setCategories(dayNumbers);
        yAxis.setLabel("Hours");
        
    
     
        
        
        setWorkingBitData();
     
        
    }
    
    

    /**
     * Sets the persons to show the statistics for.
     * 
     * @param persons
     */
    private void setWorkingBitData() {
    	
    	HashMap<String, Double> map = new WorkingBitManager(new File("test/by_day_sort.xml")).getWorkingBitListByDays();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        
        Double value;
        // Create a XYChart.Data object for each month. Add it to the series.
        for (int i = 0; i < dayNumbers.size(); i++) {
        	String key = dayNumbers.get(i);
        	value = map.get(key);
            series.getData().add(new XYChart.Data<>(key, (value == null ? 0 : value)));
        }
        barChart.getData().add(series);
        
        for (final Series<String, Number> series2 : barChart.getData()) {
            for (final Data<String, Number> data : series2.getData()) {
                 Tooltip tooltip = new Tooltip();
                 tooltip.setText(TimeHelper.doubleHoursToTime(data.getYValue().doubleValue()));
               //  tooltip.
                 Tooltip.install(data.getNode(), tooltip);                    
            }
       }
    }

}
