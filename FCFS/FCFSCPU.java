package major_code;
import java.util.LinkedList;
public class CustomCPUExecution extends Thread
{
	private Process process;
	private long startTime;
	private LinkedList<Process> waitQueue;
        
                
        public void setStartTime(long startTime)
        {
                this.startTime = startTime;
        }
	
	public void setProcess(Process process)
        {
		this.process = process;
	}
	
        public void setWaitQueue(LinkedList<Process> waitQueue)
        {
                this.waitQueue=waitQueue; 
        }
  
        @Override
	public void run()
        {
                while(process.currentBurst < process.burstCount)
                {
                        if(process.currentBurst%2==0)
                        {
                                try
                                {
                                        Thread.sleep(process.bursts[process.currentBurst]);
                                }
                                catch(InterruptedException e)
                                {
                                        System.out.println(e);
                                } 
                                if(process.burstCount-1 == process.currentBurst)
                                {  
                                        process.endTime = System.currentTimeMillis() - startTime + process.startTime;
                                      //  System.out.println("****Endtime of " +process.pid+" is "+process.endTime);
                                        process.finish = true;
                                }
                                process.currentBurst++;	
                        }
                        else
                        {
                                waitQueue.addLast(process);
                                process.ioQueueWait[(process.currentBurst-1)/2] = System.currentTimeMillis();
                                CustomIOExecution ioExecution = new CustomIOExecution();
                                ioExecution.setProcess(process);
                                ioExecution.setWaitQueue(waitQueue);
                                ioExecution.start();
                                try
                                {
                                        ioExecution.join();
                                } 
                                catch (Exception ex) 
                                {
                                        System.out.println(ex);
                                }
                                process.currentBurst++;
                        }
                }
	}
}
