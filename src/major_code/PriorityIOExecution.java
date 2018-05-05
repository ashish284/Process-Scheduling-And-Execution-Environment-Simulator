package major_code;
import java.util.LinkedList;
public class PriorityIOExecution extends Thread implements IOExecutionInterface
{
	Process process;
        LinkedList<Process> waitQueue;
	LinkedList<Process> readyQueue;
        public void setProcess(Process process)
        {
		this.process = process;
	}
        
        public void setWaitQueue(LinkedList<Process> waitQueue)
        {
                this.waitQueue=waitQueue;
        }
        
        public void setReadyQueue(LinkedList<Process> readyQueue)
        {
                this.readyQueue=readyQueue;
        }
	
        synchronized public void run()
        {
		process.ioQueueWait[(process.currentBurst-1)/2] = System.currentTimeMillis() - process.ioQueueWait[(process.currentBurst-1)/2];
                try
                {
                        Thread.sleep(process.bursts[process.currentBurst]);
                        Process.removeFromQueue(waitQueue,process);
                        synchronized(readyQueue){
                        Process.addToQueue(readyQueue, process);
                        process.currentBurst++;
                        }
                }
                catch(Exception e)
                {
			System.out.println(e);
		} 
	}
}
