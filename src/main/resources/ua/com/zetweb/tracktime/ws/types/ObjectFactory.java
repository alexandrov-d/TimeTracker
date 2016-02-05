
package ua.com.zetweb.tracktime.ws.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ua.com.zetweb.tracktime.ws.types package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FinishTaskOutput_QNAME = new QName("http://tracktime.zetweb.com.ua/ws/types/", "FinishTaskOutput");
    private final static QName _UpdateTaskExecTimeInput_QNAME = new QName("http://tracktime.zetweb.com.ua/ws/types/", "UpdateTaskExecTimeInput");
    private final static QName _CheckVersionInput_QNAME = new QName("http://tracktime.zetweb.com.ua/ws/types/", "CheckVersionInput");
    private final static QName _StopTaskExecInput_QNAME = new QName("http://tracktime.zetweb.com.ua/ws/types/", "StopTaskExecInput");
    private final static QName _GetTaskListOutput_QNAME = new QName("http://tracktime.zetweb.com.ua/ws/types/", "GetTaskListOutput");
    private final static QName _StopTaskExecOutput_QNAME = new QName("http://tracktime.zetweb.com.ua/ws/types/", "StopTaskExecOutput");
    private final static QName _CheckVersionOutput_QNAME = new QName("http://tracktime.zetweb.com.ua/ws/types/", "CheckVersionOutput");
    private final static QName _StartTaskExecOutput_QNAME = new QName("http://tracktime.zetweb.com.ua/ws/types/", "StartTaskExecOutput");
    private final static QName _UpdateTaskExecTimeOutput_QNAME = new QName("http://tracktime.zetweb.com.ua/ws/types/", "UpdateTaskExecTimeOutput");
    private final static QName _GetTaskListInput_QNAME = new QName("http://tracktime.zetweb.com.ua/ws/types/", "GetTaskListInput");
    private final static QName _StartTaskExecInput_QNAME = new QName("http://tracktime.zetweb.com.ua/ws/types/", "StartTaskExecInput");
    private final static QName _FinishTaskInput_QNAME = new QName("http://tracktime.zetweb.com.ua/ws/types/", "FinishTaskInput");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ua.com.zetweb.tracktime.ws.types
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TaskList }
     * 
     */
    public TaskList createTaskList() {
        return new TaskList();
    }

    /**
     * Create an instance of {@link TaskId }
     * 
     */
    public TaskId createTaskId() {
        return new TaskId();
    }

    /**
     * Create an instance of {@link TaskList.Task }
     * 
     */
    public TaskList.Task createTaskListTask() {
        return new TaskList.Task();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tracktime.zetweb.com.ua/ws/types/", name = "FinishTaskOutput")
    public JAXBElement<Integer> createFinishTaskOutput(Integer value) {
        return new JAXBElement<Integer>(_FinishTaskOutput_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TaskId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tracktime.zetweb.com.ua/ws/types/", name = "UpdateTaskExecTimeInput")
    public JAXBElement<TaskId> createUpdateTaskExecTimeInput(TaskId value) {
        return new JAXBElement<TaskId>(_UpdateTaskExecTimeInput_QNAME, TaskId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tracktime.zetweb.com.ua/ws/types/", name = "CheckVersionInput")
    public JAXBElement<String> createCheckVersionInput(String value) {
        return new JAXBElement<String>(_CheckVersionInput_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TaskId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tracktime.zetweb.com.ua/ws/types/", name = "StopTaskExecInput")
    public JAXBElement<TaskId> createStopTaskExecInput(TaskId value) {
        return new JAXBElement<TaskId>(_StopTaskExecInput_QNAME, TaskId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TaskList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tracktime.zetweb.com.ua/ws/types/", name = "GetTaskListOutput")
    public JAXBElement<TaskList> createGetTaskListOutput(TaskList value) {
        return new JAXBElement<TaskList>(_GetTaskListOutput_QNAME, TaskList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tracktime.zetweb.com.ua/ws/types/", name = "StopTaskExecOutput")
    public JAXBElement<Integer> createStopTaskExecOutput(Integer value) {
        return new JAXBElement<Integer>(_StopTaskExecOutput_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tracktime.zetweb.com.ua/ws/types/", name = "CheckVersionOutput")
    public JAXBElement<Boolean> createCheckVersionOutput(Boolean value) {
        return new JAXBElement<Boolean>(_CheckVersionOutput_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tracktime.zetweb.com.ua/ws/types/", name = "StartTaskExecOutput")
    public JAXBElement<Integer> createStartTaskExecOutput(Integer value) {
        return new JAXBElement<Integer>(_StartTaskExecOutput_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tracktime.zetweb.com.ua/ws/types/", name = "UpdateTaskExecTimeOutput")
    public JAXBElement<Integer> createUpdateTaskExecTimeOutput(Integer value) {
        return new JAXBElement<Integer>(_UpdateTaskExecTimeOutput_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tracktime.zetweb.com.ua/ws/types/", name = "GetTaskListInput")
    public JAXBElement<String> createGetTaskListInput(String value) {
        return new JAXBElement<String>(_GetTaskListInput_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TaskId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tracktime.zetweb.com.ua/ws/types/", name = "StartTaskExecInput")
    public JAXBElement<TaskId> createStartTaskExecInput(TaskId value) {
        return new JAXBElement<TaskId>(_StartTaskExecInput_QNAME, TaskId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TaskId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tracktime.zetweb.com.ua/ws/types/", name = "FinishTaskInput")
    public JAXBElement<TaskId> createFinishTaskInput(TaskId value) {
        return new JAXBElement<TaskId>(_FinishTaskInput_QNAME, TaskId.class, null, value);
    }

}
