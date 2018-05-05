package major_code;

import java.util.Collections;
import java.util.LinkedList;
import java.util.*;

class CustomExecution {

    private LinkedList<Process> completedProcessList = new LinkedList<Process>();
    private LinkedList<Process> readyQueue;
    private LinkedList<Process> waitQueue;
    private LinkedList<ProcessOrder> processOrder[] = new LinkedList[Processor.getNumberOfCores()];
    private ProcessOrder po;
    private long globalClock;
    private boolean gc;
    private Process cpu[];
    private int cores;
    private long[] pStart = new long[Processor.getNumberOfCores()];

    public CustomExecution(Process cpu[],int cores) {
        this.cores = cores;
        this.cpu = cpu;
    }

    public LinkedList<ProcessOrder>[] getProcessOrder() {
        return processOrder;
    }

    public void setGlobalClock(long globalClock) {
        this.globalClock = globalClock;
    }

    public LinkedList<Process> getProcessList() {
        return completedProcessList;
    }

    void setReadyQueue(LinkedList<Process> readyQueue) {
        this.readyQueue = readyQueue;
    }

    void setWaitQueue(LinkedList<Process> waitQueue) {
        this.waitQueue = waitQueue;
    }

    void cpuExecution() {
        long currentTime;
        Process nxtProcess = null, lastProcess = null;
        long remainingBurst;
        for (int i = 0; i < cores; i++) {
            if (i == 0 && gc == false) {
                gc = true;
                globalClock = System.currentTimeMillis();

            }
            lastProcess = null;
            remainingBurst = 1000000;
            if (cpu[i] != null) {
                if (cpu[i].finish == true) {
                    if (pStart[i] != cpu[i].timeOfTimesliceOver) {
                        po = new ProcessOrder(cpu[i].pid, pStart[i], cpu[i].timeOfTimesliceOver);
                        processOrder[i].add(po);
                    }

                    cpu[i] = null;
                } else if (cpu[i].timesliceOver == true) {
                    cpu[i].timesliceOver = false;
                    lastProcess = cpu[i];
                    if (pStart[i] != cpu[i].timeOfTimesliceOver) {
                        po = new ProcessOrder(cpu[i].pid, pStart[i], cpu[i].timeOfTimesliceOver);
                        processOrder[i].add(po);
                    }
                    cpu[i] = null;
                }
            }
            synchronized (readyQueue) {
                if (cpu[i] == null && readyQueue != null) {
                    //Process.sortQueue(readyQueue);
                    currentTime = System.currentTimeMillis() - globalClock;

                    nxtProcess = null;

                    for (Process process : readyQueue) {
                        if (process.arrivalTime <= currentTime) {
                            if (process.remainingBurst < remainingBurst) {
                                remainingBurst = process.remainingBurst;
                                nxtProcess = process;
                            }
                        }
                    }

                    if (nxtProcess != null) {
                        if (processOrder[i] == null) {
                            processOrder[i] = new LinkedList<ProcessOrder>();
                        }

                        if (lastProcess != null && lastProcess != nxtProcess) {
                            lastProcess.contextSwitch++;
                        }
                        cpu[i] = nxtProcess;
                        //System.out.println(i + " " + cpu[i].remainingBurst + " " + cpu[i].pid + " " + currentTime);
                        if (nxtProcess.startTime < 0) {
                            nxtProcess.startTime = currentTime;
                        }
                        pStart[i] = currentTime;
                        CustomCPUExecution t = new CustomCPUExecution();
                        t.setWaitQueue(waitQueue);
                        t.setReadyQueue(readyQueue);
                        t.setCompletedProcessList(completedProcessList);
                        t.setGlobalClock(globalClock);
                        t.setProcess(nxtProcess);
                        readyQueue.remove(nxtProcess);
                        t.start();
                    }
                }
            }
        }
    }
}
