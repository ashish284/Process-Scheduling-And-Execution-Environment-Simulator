package major_code;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

class CustomExecution {

    LinkedList<Process> processList = new LinkedList<Process>();
    LinkedList<Process> readyQueue = new LinkedList<Process>();
    LinkedList<Process> waitQueue = new LinkedList<Process>();
    long endTimeCpu[] = new long[Processor.getNumberOfCores()];
    boolean firstProcess;
    //    int processOrder[] = new int[50];
    //    long duration[] = new long[50];
    static int index;
    Process cpu[];
    int cores;
    long t1[] = new long[Processor.getNumberOfCores()];
    LinkedList<ProcessOrder> processOrder[] = new LinkedList[Processor.getNumberOfCores()];
    ProcessOrder po;
    static long ref;
    boolean globalClock;
    long t2;

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

    LinkedList[] getProcessOrder() {
        return processOrder;
    }

    void setWaitQueue(LinkedList<Process> waitQueue) {
        this.waitQueue = waitQueue;
    }

    static void setRef(long reference) {
        ref = reference;
    }

    void cpuExecution() {
        int i;
         if (globalClock == false) {
            ref = System.currentTimeMillis();
            globalClock = true;
        }
        for (i = 0; i < cores; i++) {
            if (cpu[i] == null && !readyQueue.isEmpty()) {
                cpu[i] = (Process) readyQueue.getFirst();

                CustomCPUExecution t = new CustomCPUExecution();
                t.setProcess(cpu[i]);
                t.setWaitQueue(waitQueue);
                if (firstProcess == false) {
                    firstProcess = true;
                }
                if (cpu[i].arrivalTime < endTimeCpu[i]) {
                    cpu[i].startTime = endTimeCpu[i];
                    try {
                        Thread.sleep(endTimeCpu[i] - cpu[i].arrivalTime);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                } else {
                    cpu[i].startTime = cpu[i].arrivalTime;
                    try {
                        Thread.sleep(cpu[i].startTime - endTimeCpu[i]);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                
                t1[i] = System.currentTimeMillis()-ref;
               /* po = new ProcessOrder();//change
                po.pid = cpu[i].pid;
                po.gap = cpu[i].startTime - endTimeCpu[i];
                po.duration = cpu[i].bursts[cpu[i].currentBurst];*/
                if (processOrder[i] == null) {
                    processOrder[i] = new LinkedList<ProcessOrder>();
                }
               // processOrder[i].add(po);//till here
                readyQueue.remove(cpu[i]);
                t.setStartTime(System.currentTimeMillis());
                t.start();
            }
            if (cpu[i] != null && cpu[i].finish == true) {
                
                endTimeCpu[i] = cpu[i].getEndTime();
                t2=System.currentTimeMillis();
                po = new ProcessOrder(cpu[i].pid,cpu[i].startTime,cpu[i].endTime);
                po.duration = cpu[i].endTime-cpu[i].startTime;
                processOrder[i].add(po);
                processList.add(cpu[i]);
                //System.out.println("****processorder**** "+po.pid);
                //System.out.println("****processorder**** "+po.startTime);
                //System.out.println("****processorder**** "+po.endTime);
                //System.out.println("****processorder**** "+cpu[i].endTime);
                          //      processOrder[index]=cpu[i].pid;
                //    duration[index] = cpu[i].endTime - cpu[i].startTime;
                index++;
                cpu[i] = null;
            }
        }
    }
}
