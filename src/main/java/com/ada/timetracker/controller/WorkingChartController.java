package com.ada.timetracker.controller;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.ada.timetracker.model.WorkingBitManager;
import com.ada.timetracker.util.TimeHelper;

import javafx.application.Platform;
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
	
	private final static String DATE_FORMAT = "MM/dd";
	private static final String TOOLTIP = "tooltip";
	
	private static ScheduledFuture<?> taskListTimer;
	
	  
	@FXML
    private BarChart<String, Number> barChart;

    @FXML
    private CategoryAxis xAxis;
    
    @FXML
    private NumberAxis yAxis;
    
	private  Runnable reinitialize;
    private ObservableList<String> dayNumbers = FXCollections.observableArrayList();
    private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private XYChart.Series<String, Number> series;
    private static final int CHART_DAY_RANGE = 20;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
     
    	List<Date> dates = TimeHelper.getDaysRange(CHART_DAY_RANGE);
    	DateFormat formater = new SimpleDateFormat(DATE_FORMAT);
    	
    	for ( int i = dates.size() -1; i >= 0; i--){
    		dayNumbers.add(formater.format(dates.get(i)));
		}
    
        // Assign the month names as categories for the horizontal axis.
        xAxis.setCategories(dayNumbers);
     
        barChart.setLegendVisible(false);
 
        yAxis.setLabel("Часы");
     
        setWorkingBitData();
        
        //Schedule reinitialize every  minute
        if (taskListTimer == null ){
        	reinitialize = ()-> {
				Platform.runLater( () -> updateChart());
			};
        	taskListTimer = scheduler.scheduleWithFixedDelay( reinitialize , 60, 60, TimeUnit.SECONDS);
        }
    }
    
  
    

    /**
     * Sets the persons to show the statistics for.
     * 
     * @param persons
     */
    private void setWorkingBitData() {
    	
    	HashMap<String, Double> map = WorkingBitManager.getInstance().getWorkingBitListByDays();

        series = new XYChart.Series<>();
 
        Double value;
        // Create a XYChart.Data object for each month. Add it to the series.
        for (int i = 0; i < dayNumbers.size(); i++) {
        	String key = dayNumbers.get(i);
        	value = map.get(key);
            series.getData().add(new XYChart.Data<>(key, (value == null ? 0 : value)));
        }
        barChart.getData().add(series);
        String[] colors = {"derive(#1d1d1d,50%)", "derive(#1d1d1d,80%)"};
        int colorIndex = 0;
       
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        SimpleDateFormat dateParser = new SimpleDateFormat("yyyy/" + DATE_FORMAT);
     
        
        for (final Series<String, Number> series2 : barChart.getData()) {
            for (final Data<String, Number> data : series2.getData()) {
                 Tooltip tooltip = new Tooltip();
                 tooltip.setText(TimeHelper.doubleHoursToTime(data.getYValue().doubleValue()));
                 Tooltip.install(data.getNode(), tooltip); 
                 data.getNode().getProperties().put(TOOLTIP, tooltip);
                 try {
					calendar.setTime( dateParser.parse( year + "/" + data.getXValue() )) ;
				
					if ( calendar.get(Calendar.DAY_OF_WEEK) == 2 ){
						colorIndex = 1 - colorIndex;
					}
		
					data.getNode().setStyle("-fx-background-color:" + colors[colorIndex]);
					  
				} catch (ParseException e) {
					e.printStackTrace();
				}
            }
        }
    }
    
    public void updateChart(){
    	
    	DateFormat formater = new SimpleDateFormat(DATE_FORMAT);
    	String today = formater.format(new Date());
    	
    	ObservableList<XYChart.Data<String, Number>> list = barChart.getData().get(0).getData();
    	XYChart.Data<String, Number> data = list.get( list.size()-1 );

    	if ( data.getXValue().equals(today)){
    		Double hours = WorkingBitManager.getInstance().getWorkingBitListByDays().get(today);
    		if ( hours == null){
    			hours = 0.00;
    		}
    		data.setYValue(hours);
    		Tooltip tooltip = (Tooltip) data.getNode().getProperties().get(TOOLTIP);
            tooltip.setText(TimeHelper.doubleHoursToTime(hours));
    	}else{
    		series.getData().clear();
    		barChart.getData().clear();
    		setWorkingBitData();
    	}
    }
}
