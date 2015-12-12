
package com.ada.timetracker.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for TaskList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TaskList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="task" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="project_id" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="project_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="priority_id" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                   &lt;element name="priority" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="due_date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *                   &lt;element name="actual_time" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="total" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TaskList", propOrder = {
    "task",
    "total"
})
public class TaskList {

    protected List<TaskList.Task> task;
    @XmlSchemaType(name = "unsignedInt")
    protected long total;

    /**
     * Gets the value of the task property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the task property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTask().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TaskList.Task }
     * 
     * 
     */
    public List<TaskList.Task> getTask() {
        if (task == null) {
            task = new ArrayList<TaskList.Task>();
        }
        return this.task;
    }

    /**
     * Gets the value of the total property.
     * 
     */
    public long getTotal() {
        return total;
    }

    /**
     * Sets the value of the total property.
     * 
     */
    public void setTotal(long value) {
        this.total = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="project_id" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="project_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="priority_id" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
     *         &lt;element name="priority" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="due_date" type="{http://www.w3.org/2001/XMLSchema}date"/>
     *         &lt;element name="actual_time" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "id",
        "projectId",
        "projectName",
        "priorityId",
        "priority",
        "name",
        "dueDate",
        "actualTime"
    })
    public static class Task {

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

        /**
         * Gets the value of the id property.
         * 
         */
        public long getId() {
            return id;
        }

        /**
         * Sets the value of the id property.
         * 
         */
        public void setId(long value) {
            this.id = value;
        }

        /**
         * Gets the value of the projectId property.
         * 
         */
        public long getProjectId() {
            return projectId;
        }

        /**
         * Sets the value of the projectId property.
         * 
         */
        public void setProjectId(long value) {
            this.projectId = value;
        }

        /**
         * Gets the value of the projectName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProjectName() {
            return projectName;
        }

        /**
         * Sets the value of the projectName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProjectName(String value) {
            this.projectName = value;
        }

        /**
         * Gets the value of the priorityId property.
         * 
         */
        public long getPriorityId() {
            return priorityId;
        }

        /**
         * Sets the value of the priorityId property.
         * 
         */
        public void setPriorityId(long value) {
            this.priorityId = value;
        }

        /**
         * Gets the value of the priority property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPriority() {
            return priority;
        }

        /**
         * Sets the value of the priority property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPriority(String value) {
            this.priority = value;
        }

        /**
         * Gets the value of the name property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the value of the name property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setName(String value) {
            this.name = value;
        }

        /**
         * Gets the value of the dueDate property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDueDate() {
            return dueDate;
        }

        /**
         * Sets the value of the dueDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDueDate(XMLGregorianCalendar value) {
            this.dueDate = value;
        }

        /**
         * Gets the value of the actualTime property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getActualTime() {
            return actualTime;
        }

        /**
         * Sets the value of the actualTime property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setActualTime(String value) {
            this.actualTime = value;
        }

    }

}
