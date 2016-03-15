package com.ada.timetracker.model;

public class WorkingBit implements Comparable<WorkingBit>{
	
	private String hour;
	private long taskId;
	private String taskTitle;
	private String taskProject;
	private int time;
	
	public WorkingBit(){}
	

	public WorkingBit(String hour, long taskId, String taskTitle, String taskProject, int time) {
		this.hour = hour;
		this.taskId = taskId;
		this.taskTitle = taskTitle;
		this.taskProject = taskProject;
		this.time = time;
	}

	public String getHour() {
		return hour;
	}
	
	public void setHour(String hour) {
		this.hour = hour;
	}
	
	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public String getTaskTitle() {
		return taskTitle;
	}
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
	public String getTaskProject() {
		return taskProject;
	}
	public void setTaskProject(String taskProject) {
		this.taskProject = taskProject;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	
	public void addTime(int time){
		this.time += time;
	}


	@Override
	public int compareTo(WorkingBit wb) {
		
		boolean hourEq = this.hour.equals(wb.hour);
		boolean idEq = this.taskId == wb.taskId;
		
		if ( hourEq && idEq ){
			return 0;
		}else{
			return 1;
		}		
	}
	
	@Override
	public String toString(){
		
		return String.format("Hour: %s\t Task Id: %s\t Task Title: %s\t, Time: %s\t"
				, getHour()
				, getTaskId()
				, getTaskTitle()
				, getTime()
		);
	}
}
