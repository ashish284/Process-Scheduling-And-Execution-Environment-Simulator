
package major_code;
import java.util.LinkedList;

public class CustomIOExecution extends Thread implements IOExecutionInterface
{
		private Process process;
        private LinkedList<Process> waitQueue;
		private LinkedList<Process> readyQueue;
    
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
			// Enter your code here                
		}
}

