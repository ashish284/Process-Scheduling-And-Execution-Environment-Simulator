package major_code;

import java.util.LinkedList;

public class SRTFIOExecution extends Thread {

    private Process process;
    private LinkedList<Process> waitQueue;
    private  LinkedList<Process> readyQueue;

    public void setReadyQueue(LinkedList<Process> readyQueue) {
        this.readyQueue = readyQueue;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public void setWaitQueue(LinkedList<Process> waitQueue) {
        this.waitQueue = waitQueue;
    }

    synchronized public void run() {
        process.ioQueueWait[(process.currentBurst - 1) / 2] = System.currentTimeMillis() - process.ioQueueWait[(process.currentBurst - 1) / 2];
        try {
            Thread.sleep(process.bursts[process.currentBurst]);
            waitQueue.remove(process);
            process.currentBurst++;
            synchronized(readyQueue){
                Process.addToQueue(readyQueue,process);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
