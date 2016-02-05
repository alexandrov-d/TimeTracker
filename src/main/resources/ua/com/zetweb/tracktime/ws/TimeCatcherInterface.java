
package ua.com.zetweb.tracktime.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import ua.com.zetweb.tracktime.ws.types.ObjectFactory;
import ua.com.zetweb.tracktime.ws.types.TaskId;
import ua.com.zetweb.tracktime.ws.types.TaskList;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "TimeCatcherInterface", targetNamespace = "http://tracktime.zetweb.com.ua/ws/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface TimeCatcherInterface {


    /**
     * 
     * @param input
     * @return
     *     returns boolean
     */
    @WebMethod(operationName = "CheckVersion", action = "http://tempuri.org/CheckVersion")
    @WebResult(name = "CheckVersionOutput", targetNamespace = "http://tracktime.zetweb.com.ua/ws/types/", partName = "output")
    public boolean checkVersion(
        @WebParam(name = "CheckVersionInput", targetNamespace = "http://tracktime.zetweb.com.ua/ws/types/", partName = "input")
        String input);

    /**
     * 
     * @param input
     * @return
     *     returns ua.com.zetweb.tracktime.ws.types.TaskList
     */
    @WebMethod(operationName = "GetTaskList", action = "http://tempuri.org/GetTaskList")
    @WebResult(name = "GetTaskListOutput", targetNamespace = "http://tracktime.zetweb.com.ua/ws/types/", partName = "output")
    public TaskList getTaskList(
        @WebParam(name = "GetTaskListInput", targetNamespace = "http://tracktime.zetweb.com.ua/ws/types/", partName = "input")
        String input);

    /**
     * 
     * @param input
     * @return
     *     returns int
     */
    @WebMethod(operationName = "StartTaskExec", action = "http://tempuri.org/StartTaskExec")
    @WebResult(name = "StartTaskExecOutput", targetNamespace = "http://tracktime.zetweb.com.ua/ws/types/", partName = "output")
    public int startTaskExec(
        @WebParam(name = "StartTaskExecInput", targetNamespace = "http://tracktime.zetweb.com.ua/ws/types/", partName = "input")
        TaskId input);

    /**
     * 
     * @param input
     * @return
     *     returns int
     */
    @WebMethod(operationName = "UpdateTaskExecTime", action = "http://tempuri.org/UpdateTaskExecTime")
    @WebResult(name = "UpdateTaskExecTimeOutput", targetNamespace = "http://tracktime.zetweb.com.ua/ws/types/", partName = "output")
    public int updateTaskExecTime(
        @WebParam(name = "UpdateTaskExecTimeInput", targetNamespace = "http://tracktime.zetweb.com.ua/ws/types/", partName = "input")
        TaskId input);

    /**
     * 
     * @param input
     * @return
     *     returns int
     */
    @WebMethod(operationName = "StopTaskExec", action = "http://tempuri.org/StopTaskExec")
    @WebResult(name = "StopTaskExecOutput", targetNamespace = "http://tracktime.zetweb.com.ua/ws/types/", partName = "output")
    public int stopTaskExec(
        @WebParam(name = "StopTaskExecInput", targetNamespace = "http://tracktime.zetweb.com.ua/ws/types/", partName = "input")
        TaskId input);

    /**
     * 
     * @param input
     * @return
     *     returns int
     */
    @WebMethod(operationName = "FinishTask", action = "http://tempuri.org/FinishTask")
    @WebResult(name = "FinishTaskOutput", targetNamespace = "http://tracktime.zetweb.com.ua/ws/types/", partName = "output")
    public int finishTask(
        @WebParam(name = "FinishTaskInput", targetNamespace = "http://tracktime.zetweb.com.ua/ws/types/", partName = "input")
        TaskId input);

}
