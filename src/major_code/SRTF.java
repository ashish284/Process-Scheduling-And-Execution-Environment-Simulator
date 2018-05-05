package major_code;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ashish
 */
public class SRTF {

    /**
     * @param args the command line arguments
     */
    public void execSRTF(InputModule inputModule, OutputModule out) {
        LinkedList<Process> waitQueue = new LinkedList<Process>();
        LinkedList<Process> readyQueue = new LinkedList<Process>();
        LinkedList<Process> completedProcessList = new LinkedList<Process>();

        //  InputModule inputModule = new InputModule();
        readyQueue = inputModule.createReadyQueue();
        int size = readyQueue.size();
      //  float cpuClock = Processor.getClockSpeed();
        // for (Process process : readyQueue) {
        //      process.IOCalculate(cpuClock);
        //  }
        int cores = Processor.getNumberOfCores();
        Process cpu[] = new Process[cores];
        for (int i = 0; i < cores; i++) {
            cpu[i] = null;
        }
        SRTFExecution execution = new SRTFExecution(cpu, cores);
        execution.setReadyQueue(readyQueue);
        execution.setWaitQueue(waitQueue);

        //execution.setGlobalClock(System.currentTimeMillis());
        while (completedProcessList.size() != size) {
            execution.cpuExecution();
            completedProcessList = execution.getProcessList();

        }

        new OutputGanttChart(execution.getProcessOrder(), completedProcessList.getLast().endTime, 3, size, out, completedProcessList);

       

     //   OutputModule out = new OutputModule();
        //   LinkedList<ProcessOrder>[] processOrder = execution.getProcessOrder();
        //          OutputGanttChart o = new OutputGanttChart(processOrder,processList.getLast().endTime,3);
//        out.displayTable(completedProcessList, 3);
        /*LinkedList<ProcessOrder> po[] = execution.getProcessOrder();
         for(int i=0;i<cores;i++){
         for(ProcessOrder p:po[i]){
         System.out.println(i+"  "+p.pid +"  "+p.startTime+" "+p.endTime);
         }
         }*/

    }

}
