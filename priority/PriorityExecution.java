package major_code;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import java.util.logging.Logger;

class CustomExecution {

    LinkedList<Process> processList = new LinkedList<Process>();
    LinkedList<Process> readyQueue;
    LinkedList<Process> waitQueue;
    long endTimeCpu;

    //New!!
    long gc;
    boolean firstProcess;
    long startTimeProcess[] = new long[10]; //For setting the processess' end times 
    boolean firstTimeSliceOver[] = new boolean[10]; // For setting the processess' start times
    Process lastProcess[] = new Process[10]; // To find which process has executed before in that cpu
    Process nextProcess;
    int maxPriority;
    Process cpu[];
    int cores;
    LinkedList<ProcessOrder> processOrder[] = new LinkedList[Processor.getNumberOfCores()];
    ProcessOrder po;
    long pStart[] = new long[Processor.getNumberOfCores()];
    boolean globalClock;

    LinkedList<ProcessOrder>[] getProcessOrder() {
        return processOrder;
    }

    public CustomExecution(Process cpu[], int cores) {
        this.cpu = cpu;
        this.cores = cores;
    }

    LinkedList<Process> getProcessList() {
        return processList;
    }

    void setReadyQueue(LinkedList<Process> readyQueue) {
        this.readyQueue = readyQueue;
    }

    void setWaitQueue(LinkedList<Process> waitQueue) {
        this.waitQueue = waitQueue;
    }

    void cpuExecution() {
        int i;
        if (globalClock == false) {
            gc = System.currentTimeMillis();
            globalClock = true;
        }
        for (i = 0; i < cores; i++) {
            if (cpu[i] != null && cpu[i].finish == true) {
//                cpu[i].endTime = cpu[i].timeOfTimesliceOver;

                if ((cpu[i].timeOfTimesliceOver) != pStart[i]) {
//                    System.out.println("Process " + cpu[i].pid + " start at " + pStart[i] + " and end at " + (cpu[i].timeOfTimesliceOver) + "at cpu " + i);
                    po = new ProcessOrder(cpu[i].pid, pStart[i], (cpu[i].timeOfTimesliceOver ));
                    processOrder[i].add(po);
                }

//                processList.add(cpu[i]);
                cpu[i] = null;

            }
            if (cpu[i] != null && cpu[i].timesliceOver == true) {
                cpu[i].timesliceOver = false;
//                                System.out.println("****Process "+cpu[i].pid+" timeslice over at "+(cpu[i].timeSliceOverTime-gc));
                if ((cpu[i].timeOfTimesliceOver) != pStart[i]) {
//                    System.out.println("Process " + cpu[i].pid + " start at " + pStart[i] + " and end at " + (cpu[i].timeOfTimesliceOver ));
                    po = new ProcessOrder(cpu[i].pid, pStart[i], (cpu[i].timeOfTimesliceOver ));
                    processOrder[i].add(po);

                }
                cpu[i] = null;
            }

            if (!readyQueue.isEmpty() && cpu[i] == null) {
                if (firstProcess == false) {
                    firstProcess = true;
                }

                endTimeCpu = System.currentTimeMillis() - gc;
                pStart[i] = endTimeCpu;
                // Finding the largest priority process
                maxPriority = 11;
                nextProcess = null;
                synchronized (readyQueue) {
                    Iterator<Process> iterator = readyQueue.iterator();
                    while (iterator.hasNext()) {
                        Process p = iterator.next();
                        if (p.arrivalTime <= endTimeCpu) {
                            if (maxPriority > p.priority) {
                                maxPriority = p.priority;
                                nextProcess = p;
                            }
                        }
                    }
                }
//                                System.out.println("%%%end time of cpu "+i+" is "+endTimeCpu);
                cpu[i] = nextProcess;
                if (processOrder[i] == null) {
                    processOrder[i] = new LinkedList<ProcessOrder>();
                }

                if (cpu[i] != null) {
                    // Context switch calculation
                    if (lastProcess[i] != null) {
                        if (lastProcess[i].pid != nextProcess.pid && lastProcess[i].finish == false) {
                            lastProcess[i].contextSwitch++;
                        }
                    }

                    lastProcess[i] = nextProcess;
                    CustomCPUExecution t = new CustomCPUExecution();
                    t.setGlobalClock(gc);
                    t.setProcess(cpu[i]);
                    t.setCompletedProcessList(processList);
                    t.setWaitQueue(waitQueue);
                    t.setReadyQueue(readyQueue);
                    Process.removeFromQueue(readyQueue, cpu[i]);

                    // Setting the start time of the process
                    if (firstTimeSliceOver[cpu[i].pid - 1] == false) {
                        firstTimeSliceOver[cpu[i].pid - 1] = true;
                        startTimeProcess[cpu[i].pid - 1] = System.currentTimeMillis();
                        if (cpu[i].arrivalTime < endTimeCpu) {
                            cpu[i].startTime = endTimeCpu;
                        } else {
                            cpu[i].startTime = cpu[i].arrivalTime;
                        }
                    }

//                                        System.out.println("Process "+cpu[i].pid+" went on cpu "+i+" at time "+pStart[i]);
                    t.start();
                }
            }
        }
    }
}
