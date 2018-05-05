package major_code;
import java.util.LinkedList;

public class Priority
{
	public  void execPriority(InputModule inputModule, OutputModule out)
        {
                LinkedList<Process> waitQueue = new LinkedList<Process>();
		LinkedList<Process> readyQueue = new LinkedList<Process>();
                LinkedList<Process> processList;
                int size;
             //   InputModule inputModule = new InputModule();
                
		readyQueue = inputModule.createReadyQueue();
                size = readyQueue.size();
              //  float cpuClock = Processor.getClockSpeed();
            /*    for(Process process : readyQueue)
                {   
                        process.IOCalculate(cpuClock);
                }*/
                
                int cores = Processor.getNumberOfCores();
                Process cpu[] = new Process[cores];
                for(int i=0;i<cores;i++){
                    cpu[i] = null;
                }
		//1 for all processes
		//2 Calculate process.ioCal()
		//3 make ready and wait queue
		//4 while(process in ready queue)
		//5 call cpu execution method of interface Execution
                PriorityExecution execution = new PriorityExecution(cpu,cores);
                execution.setReadyQueue(readyQueue);
                execution.setWaitQueue(waitQueue);
                processList = execution.getProcessList();
                while(processList.size() != size)
                {
                        
                        execution.cpuExecution();
                        processList = execution.getProcessList();
                }
             
              
                
                LinkedList<ProcessOrder>[] processOrder = execution.getProcessOrder();
                OutputGanttChart o = new OutputGanttChart(processOrder,processList.getLast().endTime,4,size,out,processList);
                
             //   OutputModule out=new OutputModule();
                
//                out.displayTable(processList, 4);
	}       
}
        
