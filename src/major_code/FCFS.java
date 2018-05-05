package major_code;

import java.util.LinkedList;

public class FCFS {

    public void execFCFS(InputModule inputModule, OutputModule out) {
        LinkedList<Process> waitQueue = new LinkedList<Process>();
        LinkedList<Process> readyQueue = new LinkedList<Process>();
        LinkedList<Process> processList = new LinkedList<Process>();
        int size;
        //InputModule inputModule = new InputModule();
        readyQueue = inputModule.createReadyQueue();
        size = readyQueue.size();
/*        for (Process process : readyQueue) {

            System.out.println(process.pid);
        }*/

        /*  float cpuClock = Processor.getClockSpeed();
         for(Process process : readyQueue)
         {
         process.IOCalculate(cpuClock);
         for(int i=0; i<process.burstCount ; i++)
         System.out.print(process.bursts[i]+" ");
         System.out.println();
         } */
        int cores = Processor.getNumberOfCores();
//        System.out.println("core =      " + cores);

        Process cpu[] = new Process[cores];
        for (int i = 0; i < cores; i++) {
            cpu[i] = null;
        }
		//1 for all processes
        //2 Calculate process.ioCal()
        //3 make ready and wait queue
        //4 while(process in ready queue)
        //5 call cpu execution method of interface Execution
        FCFSExecution execution = new FCFSExecution(cpu, cores);
        execution.setReadyQueue(readyQueue);
        execution.setWaitQueue(waitQueue);
        processList = execution.getProcessList();
        
        
        while (processList.size() != size) {
            execution.cpuExecution();
            processList = execution.getProcessList();
        }
               LinkedList<ProcessOrder>[] processOrder = execution.getProcessOrder();
        //     long duration[]=execution.getDuration();
               //System.out.println("size = "+processOrder.size());
        //     OutputModule out=new OutputModule();
       // OutputGanttChartPanel panel = new OutputGanttChartPanel(execution.getProcessOrder());
//                for(int i = 0 ;i < cores; i++){
//             for(ProcessOrder po1 : processOrder[i]){
//                 System.out.println(po1.pid+" start  "+po1.startTime+" end "+po1.endTime);
//             }
                     
//        }
        OutputGanttChart o = new OutputGanttChart(processOrder,processList.getLast().endTime,1,size,out,processList);
//        out.displayTable(processList, 1);
      //  Output.getProcessOrder(execution.getProcessOrder());
       // Output.main();
        //    for(int i=0;i<processList.size();i++)
        //           System.out.println(processOrder[i]+" "+duration[i]);
    }
}
