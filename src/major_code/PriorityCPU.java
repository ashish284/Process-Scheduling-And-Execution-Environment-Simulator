package major_code;

import java.util.LinkedList;

public class PriorityCPU extends Thread implements CPUInterface {

    private Process process;
    private long startTime;
    private LinkedList<Process> waitQueue;
    private LinkedList<Process> readyQueue;
    private long globalClock;
    LinkedList<Process> completedProcessList;

    public void setGlobalClock(long globalClock) {
        this.globalClock = globalClock;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public void setWaitQueue(LinkedList<Process> waitQueue) {
        this.waitQueue = waitQueue;
    }
    //NEW!!

    public void setReadyQueue(LinkedList<Process> readyQueue) {
        this.readyQueue = readyQueue;
    }

    public void setCompletedProcessList(LinkedList<Process> completedProcessList) {
        this.completedProcessList = completedProcessList;
    }

    @Override
    public void run() {
        if (process.currentBurst % 2 == 0 && process.bursts[process.currentBurst] != 0) {
            try {
                process.bursts[process.currentBurst]--;
                Thread.sleep(1);
                process.timeOfTimesliceOver = System.currentTimeMillis() - globalClock;
//System.out.println("Time of timeslice over of "+process.pid+" is "+process.timeOfTimesliceOver);
            } catch (InterruptedException e) {
                System.out.println("Error:: The CPU thread on which process " + process.pid + " was being executed was interrupted.");
            }
            if (process.burstCount - 1 == process.currentBurst && process.bursts[process.currentBurst] == 0) {
                process.finish = true;
                process.endTime = process.timeOfTimesliceOver;
                completedProcessList.addLast(process);
            } else {
                synchronized (readyQueue) {
                    Process.addToQueue(readyQueue, process);
                }
            }
            if (process.bursts[process.currentBurst] == 0) {
                process.currentBurst++;

            }
//            process.timeOfTimesliceOver = System.currentTimeMillis();

        } else {

            Process.addToQueue(waitQueue, process);
            process.ioQueueWait[(process.currentBurst - 1) / 2] = System.currentTimeMillis();
            PriorityIOExecution ioExecution = new PriorityIOExecution();
            ioExecution.setProcess(process);
            ioExecution.setWaitQueue(waitQueue);
            ioExecution.setReadyQueue(readyQueue);
            ioExecution.start();
            process.timeOfTimesliceOver = System.currentTimeMillis() - globalClock;
        }

        process.timesliceOver = true;

    }
}
