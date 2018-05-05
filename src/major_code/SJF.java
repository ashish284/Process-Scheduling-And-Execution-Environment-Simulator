/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package major_code;
import java.util.LinkedList;
/**
 *
 * @author Admin
 */
public class SJF {
    public void execSJF(InputModule inputModule, OutputModule out)
    {
            LinkedList<Process> readyQueue= new LinkedList<Process>();
            LinkedList<Process> waitQueue= new LinkedList<Process>();
            LinkedList<Process> processList= new LinkedList<Process>();
            int size;
          
	    readyQueue = inputModule.createReadyQueue();
            size = readyQueue.size();
            //System.out.println("size = "+size);
          /*  float cpuClock = Processor.getClockSpeed();
            for(Process process : readyQueue){
                    
                    process.IOCalculate(cpuClock);
                   
                }*/
                
                int cores = Processor.getNumberOfCores();
//	        System.out.println("core =      "+cores);
                Process cpu[] = new Process[cores];
                for(int i=0;i<cores;i++){
                    cpu[i] = null;
                }
		//  for all processes
		//2  Calculate process.ioCal()
		//3 make ready and wait queue
		//4 while(process in ready queue)
		//5 call cpu execution method of interface Execution
                SJFExecution execution = new SJFExecution(cpu,cores);
                execution.setReadyQueue(readyQueue);
                execution.setWaitQueue(waitQueue);
                processList = execution.getProcessList();
                
                while(processList.size() != size)
                {
                        execution.cpuExecution(cpu, cores);
                        processList = execution.getProcessList();
                }
              //  int processOrder[]=execution.getProcessOrder();
             //   long duration[]=execution.getDuration();
              //  OutputModule out=new OutputModule();
             //   OutputGanttChartPanel panel=new OutputGanttChartPanel(execution.getProcessOrder());
                LinkedList<ProcessOrder>[] processOrder = execution.getProcessOrder();
                 OutputGanttChart o = new OutputGanttChart(processOrder,processList.getLast().endTime,2,size,out,processList);
//                out.displayTable(processList, 2);
             //   for(int i=0;i<processList.size();i++)
               //         System.out.println(processOrder[i]+" "+duration[i]);
        
        
    
    
    }
    
}
