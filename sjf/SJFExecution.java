/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package major_code;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Iterator;
import static major_code.FCFSExecution.index;

/**
 *
 * @author Admin
 */
public class CustomExecution {

    LinkedList<Process> processList = new LinkedList<Process>();
    LinkedList<Process> readyQueue = new LinkedList<Process>();
    LinkedList<Process> waitQueue = new LinkedList<Process>();
    long endTimeCpu[] = new long[Processor.getNumberOfCores()];
    long cpuStart[] = new long[Processor.getNumberOfCores()];
    boolean firstProcessCpu[] = new boolean[Processor.getNumberOfCores()];
    long nowTime;
    boolean firstProcess;
    //    int processOrder[] = new int[50];
    //    long duration[] = new long[50];
    static int index;
    Process cpu[];
    boolean globalClock;
    int cores;
    LinkedList<ProcessOrder> processOrder[] = new LinkedList[Processor.getNumberOfCores()];
    ProcessOrder po;
    static long ref;
    long t1[] = new long[Processor.getNumberOfCores()];
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

    void setWaitQueue(LinkedList<Process> waitQueue) {
        this.waitQueue = waitQueue;
    }

    LinkedList[] getProcessOrder() {
        return processOrder;
    }

    static void setRef(long reference) {
        ref = reference;
    }

    /*
     int[] getProcessOrder()
     {
     return processOrder;
     }

     long[] getDuration()
     {
     return duration;
     }
     */
    void cpuExecution() {
        int i;
		if (globalClock == false) {
            ref = System.currentTimeMillis();
            globalClock = true;
        }
        for (i = 0; i < cores; i++) {
            if (cpu[i] == null && !readyQueue.isEmpty()) {
                //traverse the queue and search for the process whoose arrival time is before current time and with shortest burst
                cpu[i] = (Process) getShortestJob(i);
                CustomCPUExecution t = new CustomCPUExecution();
                t.setProcess(cpu[i]);
                t.setWaitQueue(waitQueue);
                if (firstProcess == false) {
                    firstProcess = true;
                }
                if (firstProcessCpu[i] == false) {
                    firstProcessCpu[i] = true;
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

                t1[i] = System.currentTimeMillis() - ref;

                if (processOrder[i] == null) {
                    processOrder[i] = new LinkedList<ProcessOrder>();
                }

                readyQueue.remove(cpu[i]);
                t.setStartTime(System.currentTimeMillis());
                t.start();

            }
            if (cpu[i] != null && cpu[i].finish == true) {
                processList.add(cpu[i]);
                endTimeCpu[i] = cpu[i].getEndTime();
                t2 = System.currentTimeMillis();
                po = new ProcessOrder(cpu[i].pid, cpu[i].startTime, cpu[i].endTime);
                po.duration = cpu[i].endTime - cpu[i].startTime;
                processOrder[i].add(po);
                        //        processOrder[index]=cpu[i].pid;
                //        duration[index] = cpu[i].endTime - cpu[i].startTime;
                index++;
                cpu[i] = null;
            }

        }

    }

    Process getShortestJob(int i) {
        long arrival;
        Process shortestProcess = readyQueue.getFirst();

        //System.out.println("end time of this processor is :"+endTimeCpu[i]+"");
        if (firstProcessCpu[i] == false) {
            Iterator<Process> iterator = readyQueue.iterator();
            while (iterator.hasNext()) {
                Process p = iterator.next();
                arrival = p.getArrivalTime();
                if (arrival == shortestProcess.getArrivalTime()) {
                    if (p.getNewCPUBurst() < shortestProcess.getNewCPUBurst()) {
                            //System.out.println("here "+p.getPid()+" is having lower burst then "+shortestProcess.getPid()+"");

                        shortestProcess = p;
                    }

                }

            }

        } else {
            Iterator<Process> iterator = readyQueue.iterator();
            while (iterator.hasNext()) {
                Process p = iterator.next();
                arrival = p.getArrivalTime();
                if (arrival > endTimeCpu[i]) {
                    break;
                }
                if (p.getNewCPUBurst() < shortestProcess.getNewCPUBurst()) {
                    //System.out.println("here "+p.getPid()+" is having lower burst then "+shortestProcess.getPid()+"");
                    shortestProcess = p;

                }

            }
        }
        //System.out.println("process taken is pid : "+shortestProcess.getPid()+"");
        return shortestProcess;
    }

}
