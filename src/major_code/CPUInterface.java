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
public interface CPUInterface {
            public void setProcess(Process process);
            public void setWaitQueue(LinkedList<Process> waitQueue);
            public void setReadyQueue(LinkedList<Process> readyQueue);
}
