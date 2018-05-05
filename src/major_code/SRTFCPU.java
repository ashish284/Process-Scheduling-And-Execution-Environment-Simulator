package major_code;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SRTFCPU extends Thread {

    private Process process;
    private long globalClock;
    private LinkedList<Process> waitQueue;
    private LinkedList<Process> readyQueue;
    private LinkedList<Process> completedProcessList;

    public void setGlobalClock(long globalClock) {
        this.globalClock = globalClock;
    }

    public void setCompletedProcessList(LinkedList<Process> completedProcessList) {
        this.completedProcessList = completedProcessList;
    }

    public void setReadyQueue(LinkedList<Process> readyQueue) {
        this.readyQueue = readyQueue;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public void setWaitQueue(LinkedList<Process> waitQueue) {
        this.waitQueue = waitQueue;
    }

    @Override
    public void run() {
        long sleep = 1;
        if (process.bursts[process.currentBurst] >= sleep) {
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
            //System.out.println(process.pid+" "+process.bursts[process.currentBurst]+" "+process.currentBurst+" "+process.burstCount);
            process.bursts[process.currentBurst] -= sleep;
            process.remainingBurst -= sleep;
            process.timeOfTimesliceOver = System.currentTimeMillis() - globalClock;
            if (process.currentBurst == process.burstCount - 1 && process.bursts[process.currentBurst] == 0) {
                process.finish = true;
                process.endTime = process.timeOfTimesliceOver;
                completedProcessList.addLast(process);
            } else {
                synchronized(readyQueue){
                    Process.addToQueue(readyQueue, process);
                }    
            }
            process.timesliceOver = true;

        } else {
            process.timeOfTimesliceOver = System.currentTimeMillis() - globalClock;
            process.timesliceOver = true;
            process.contextSwitch++;
            process.currentBurst++;
            //System.out.println("for io"+process.pid+" "+process.bursts[process.currentBurst]+" "+process.currentBurst+" "+process.burstCount);
            Process.addToQueue(waitQueue, process);
            SRTFIOExecution ioExecution = new SRTFIOExecution();
            ioExecution.setReadyQueue(readyQueue);
            ioExecution.setWaitQueue(waitQueue);
            process.ioQueueWait[(process.currentBurst - 1) / 2] = System.currentTimeMillis();
            ioExecution.setProcess(process);
            ioExecution.start();

//send for io
        }
        // set cpu to null
    }
}
