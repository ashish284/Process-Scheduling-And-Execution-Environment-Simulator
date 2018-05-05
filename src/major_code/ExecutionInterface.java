package major_code;

import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ashley
 */
public interface ExecutionInterface {
    
    void setReadyQueue(LinkedList<Process> readyQueue);

    void setWaitQueue(LinkedList<Process> waitQueue);

    void cpuExecution();

    LinkedList<Process> getProcessList();

    LinkedList<ProcessOrder>[] getProcessOrder();
}
