package com.ada.timetracker.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "workingBits")
public class WorkingBitListWrapper {
	

	private List<WorkingBit> workingBits;


    public List<WorkingBit> getWorkingBitList() {
        return workingBits;
    }

    @XmlElement(name = "workingBit")

    public void setWorkingBitList(List<WorkingBit> workingBits) {
        this.workingBits = workingBits;
    }
	
}
