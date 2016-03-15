package com.ada.timetracker.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "workingBitList")
public class WorkingBitListWrapper {
	

	private List<WorkingBit> workingBitList;


	public  WorkingBitListWrapper(){
		workingBitList = new ArrayList<>();
	}
    @XmlElement(name = "workingBit")
    public List<WorkingBit> getWorkingBitList() {
        return workingBitList;
    }



    public void setWorkingBitList(List<WorkingBit> workingBits) {
        this.workingBitList = workingBits;
    }
    
	public void add(WorkingBit workingBit) {
		
		if (workingBitList.size() > 0){
			
			WorkingBit lastBit = workingBitList.get(workingBitList.size() - 1);
			
			if ( lastBit.compareTo(workingBit) == 0){
				lastBit.addTime(workingBit.getTime());
			}else{
				workingBitList.add(workingBit);
			}
		}else{
			insert(workingBit);
		}
	}
	
	public void insert(WorkingBit workingBit) {
		workingBitList.add(workingBit);
	}
	
	
	public WorkingBit getLastWorkingBit(){
		
		if (workingBitList.size() > 0){
			return workingBitList.get(workingBitList.size()-1);
		}else{
			return null;
		}
		
	}
}
