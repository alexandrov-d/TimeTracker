package com.ada.timetracker.model;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.AnchorPane;

/**
 * Model class for a Task.
 *
 */
public class Task implements Comparable<Task> {
	
    private final LongProperty id;
    private final StringProperty title;
    private final LongProperty projectId;
    private final StringProperty projectTitle;
    private final LongProperty priorityId;
    private final StringProperty priorityTitle;
    private final StringProperty time;
    private final StringProperty dueDate;
    private  AnchorPane taskPane = null;

    private static String order = "priority";

	/**
     * Default constructor.
     */
  /*  public Task() {
        this(null, null);
    }
*/
    public Task(long id, String title,  long projectId, String projectTitle, long priorityId, String priorityTitle, String  time, String dueDate) {
    	
    	this.id = new SimpleLongProperty( id ); 
        this.title = new SimpleStringProperty( title );
        this.projectId = new SimpleLongProperty( projectId );
        this.projectTitle = new SimpleStringProperty( projectTitle );
        this.priorityId = new SimpleLongProperty( priorityId );
        this.priorityTitle = new SimpleStringProperty( priorityTitle );
        if ( time.isEmpty() ){
        	this.time =  new SimpleStringProperty( "00:00:00" );
        }else{
        	this.time = new SimpleStringProperty( time );
        }  
        this.dueDate = new SimpleStringProperty( dueDate );
    }

	public long getId() {
		return id.get();
	}
	public void setId(String id) {
		this.title.set(id);
	}
	public LongProperty idProperty() {
		return id;
	}
	
	public String getTitle() {
		return title.get();
	}
	public void setTitle(String title) {
		this.title.set(title);
	}
	public StringProperty titleProperty() {
		return title;
	}
    
	public long getProjectId() {
		return projectId.get();
	}
	public void setProjectId(int projectId) {
		this.projectId.set(projectId);
	}
	public LongProperty projectIdProperty() {
		return projectId;
	}
	
	public String getProjectTitle() {
		return projectTitle.get();
	}
	public void setProjectTitle(String projectTitle) {
		this.projectTitle.set(projectTitle);
	}
	public StringProperty projectTitleProperty() {
		return projectTitle;
	}

	public long getPriorityId() {
		return priorityId.get();
	}
	public void setPriorityId( int priorityId){
		this.priorityId.set(priorityId);
	}
	public LongProperty priorityIdProperty() {
		return priorityId;
	}
	
	public String getPriorityTitle() {
		return priorityTitle.get();
	}
	public void setPriorityTitle( String priorityTitle ){
		this.priorityTitle.set( priorityTitle );
	}
	public StringProperty priorityTitleProperty() {
		return priorityTitle;
	}
	
	public String getTime() {
		return time.get();
	}
	public void setTime( String time ){
		this.time.set( time );
	}
	public StringProperty timeProperty() {
		return time;
	}
	
	public String getDueDate() {
		return time.get();
	}
	public void setDueDate( String dueDate){
		this.dueDate.set( dueDate );
	}
	public StringProperty dueDateProperty() {
		return dueDate;
	}
	
    public AnchorPane getTaskPane() {
		return taskPane;
	}
    public void setTaskPane(AnchorPane pane) {
    	this.taskPane = pane;
    }

    
	public String toString(){
		
		return String.format("Проэкт: %s\t Задача: %s\t Время: %s\t, Приоритет: %s\t\n"
				, getPriorityTitle()
				, getTitle()
				, getTime()
				, getPriorityTitle()
		);
	}
	
	public static void setOrder(String o){
		order = o;
	}

	@Override
	public int compareTo(Task t) {
	    int result = 0;
		switch (order) {
			case "project":
				result =  t.getProjectId() >= this.getProjectId() ? -1 : 1;
				break;
			case "title":
				result =  t.getId() >= this.getId() ? -1 : 1;
				break;
			case "priority" :
			default:
				result =  t.getPriorityId() >= this.getPriorityId() ? -1 : 1;
				break;
		}
		
		return result;
	
		
	}
}