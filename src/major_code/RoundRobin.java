/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package major_code;
import java.util.LinkedList;
/**
 *
 * @author Anji
 */
public class RoundRobin
{
        public  void execRR(InputModule inputModule, OutputModule out)
        {
                LinkedList<Process> waitQueue = new LinkedList<Process>();
		LinkedList<Process> readyQueue = new LinkedList<Process>();
                LinkedList<Process> processList = new LinkedList<Process>();
                int size;
               // InputModule inputModule = new InputModule();
		readyQueue = inputModule.createReadyQueue();
                size = readyQueue.size();
             /*   float cpuClock = Processor.getClockSpeed();
                for(Process process : readyQueue)
                {
                        process.IOCalculate(cpuClock);
                        for(int i=0; i<process.burstCount ; i++)
                                System.out.print(process.bursts[i]+" ");
                        System.out.println();
                } 
                   */
                int cores = Processor.getNumberOfCores();
//                System.out.println("core =      "+cores);
                Process cpu[] = new Process[cores];
                for(int i=0;i<cores;i++){
                    cpu[i] = null;
                }
		//1 for all processes
		//2 Calculate process.ioCal()
		//3 make ready and wait queue
		//4 while(process in ready queue)
		//5 call cpu execution method of interface Execution
                RoundRobinExecution execution = new RoundRobinExecution(cpu,cores);
                execution.setReadyQueue(readyQueue);
                execution.setWaitQueue(waitQueue);
                processList = execution.getProcessList();
                
                while(processList.size() != size)
                {
                        execution.cpuExecution();
                        processList = execution.getProcessList();
                }
             //   int processOrder[]=execution.getProcessOrder();
             //   long duration[]=execution.getDuration();
             //   OutputModule out=new OutputModule();
              //  OutputGanttChartPanel panel=new OutputGanttChartPanel(execution.getProcessOrder());
             //   out.displayTable(processList, 5);
                LinkedList<ProcessOrder>[] processOrder = execution.getProcessOrder();
                OutputGanttChart o = new OutputGanttChart(processOrder,processList.getLast().endTime,5,size, out, processList);
//                out.displayTable(processList, 5);
	}       
}
