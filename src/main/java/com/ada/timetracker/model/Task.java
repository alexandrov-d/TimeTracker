package com.ada.timetracker.model;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.XMLGregorianCalendar;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Task.
 *
 */
public class Task {

	/*
	 @XmlSchemaType(name = "unsignedInt")
     protected long id;
     @XmlElement(name = "project_id")
     @XmlSchemaType(name = "unsignedInt")
     protected long projectId;
     @XmlElement(name = "project_name", required = true)
     protected String projectName;
     @XmlElement(name = "priority_id")
     @XmlSchemaType(name = "unsignedInt")
     protected long priorityId;
     @XmlElement(required = true)
     protected String priority;
     @XmlElement(required = true)
     protected String name;
     @XmlElement(name = "due_date", required = true)
     @XmlSchemaType(name = "date")
     protected XMLGregorianCalendar dueDate;
     @XmlElement(name = "actual_time", required = true)
     protected String actualTime;
     */
     
    private final StringProperty projectName;
   /* private final StringProperty lastName;
    private final StringProperty street;
    private final IntegerProperty postalCode;
    private final StringProperty city;
    private final ObjectProperty<LocalDate> birthday;*/

    /**
     * Default constructor.
     */
    public Task() {
        this(null, null);
    }

    /**
     * Constructor with some initial data.
     * 
     * @param firstName
     * @param lastName
     */
    public Task(String projectName, String lastName) {
    	
        this.projectName = new SimpleStringProperty(projectName);
       /* this.lastName = new SimpleStringProperty(lastName);

        // Some initial dummy data, just for convenient testing.
        this.street = new SimpleStringProperty("some street");
        this.postalCode = new SimpleIntegerProperty(1234);
        this.city = new SimpleStringProperty("some city");
        this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));*/
    }

	public StringProperty getProjectName() {
		System.out.println(projectName);
		return projectName;
	}
	


   /* public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getStreet() {
        return street.get();
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public StringProperty streetProperty() {
        return street;
    }

    public int getPostalCode() {
        return postalCode.get();
    }

    public void setPostalCode(int postalCode) {
        this.postalCode.set(postalCode);
    }

    public IntegerProperty postalCodeProperty() {
        return postalCode;
    }

    public String getCity() {
        return city.get();
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public StringProperty cityProperty() {
        return city;
    }

    public LocalDate getBirthday() {
        return birthday.get();
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }*/
}