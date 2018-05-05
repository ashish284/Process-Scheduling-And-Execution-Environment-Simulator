/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package major_code;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anji
 */
public class RoundRobinExecution {

    LinkedList<Process> processList = new LinkedList<Process>();
    LinkedList<Process> readyQueue = new LinkedList<Process>();
    LinkedList<Process> waitQueue = new LinkedList<Process>();
    long endTimeCpu[] = new long[Processor.getNumberOfCores()];
    boolean firstProcess;
    int clockReference;
    int numberOfTimeslice;
    int core;
    long gc[] = new long[10];
    int flag[] = new int[10];
    //   int processOrder[] = new int[1000];
    //   long duration[] = new long[1000];
    static int index;
    long startTimeOfTimeslice;
    long t2;
    Process cpu[];
    int cores;
    LinkedList<ProcessOrder> processOrder[] = new LinkedList[Processor.getNumberOfCores()];
    ProcessOrder po;
    long t1[] = new long[Processor.getNumberOfCores()];
    long globalClock;
    boolean globalClockBool;
    long IOTime;
    long endTime[]= new long[Processor.getNumberOfCores()];

    static long ref;
    public RoundRobinExecution(Process cpu[], int cores) {
        this.cpu = cpu;
        this.cores = cores;
    }

    LinkedList<Process> getProcessList() {
        return processList;
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

    void setReadyQueue(LinkedList<Process> readyQueue) {
        this.readyQueue = readyQueue;
    }

    void setWaitQueue(LinkedList<Process> waitQueue) {
        this.waitQueue = waitQueue;
    }

    LinkedList[] getProcessOrder() {
        return processOrder;
    }
    
     

    void cpuExecution() {
        int i;
        if (globalClockBool == false) {
            ref = System.currentTimeMillis();
            globalClockBool = true;
        }
        for (i = 0; i < cores; i++) {
            synchronized (readyQueue) {
               // po = new ProcessOrder();
                if (cpu[i] == null && !readyQueue.isEmpty()) {
                    cpu[i] = (Process) readyQueue.getFirst();
                    RoundRobinCPU t = new RoundRobinCPU();
                    t.setProcess(cpu[i]);
                    t.setWaitQueue(waitQueue);
                    t.setReadyQueue(readyQueue);
                    if (firstProcess == false) {
                        firstProcess = true;
                        clockReference = (int) cpu[i].arrivalTime;
                         globalClock=System.currentTimeMillis();
                    
                    }
                   
                    
                   
                 //   t1[i] = System.currentTimeMillis()-ref;
                    
                    if (flag[cpu[i].pid] == 1) {
                        t.setStartTime(gc[cpu[i].pid]);
                       // po.gap = t1[i]-endTime[i];
                    }
                    if (flag[cpu[i].pid] == 0) {
                        if (cpu[i].arrivalTime < endTimeCpu[i]) {
                            cpu[i].startTime = endTimeCpu[i];
                                    try{
                    Thread.sleep(endTimeCpu[i]-cpu[i].arrivalTime);
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                    }
                            
                        } else {
                            cpu[i].startTime = cpu[i].arrivalTime;
                            
                                    try{
                    Thread.sleep(cpu[i].startTime-endTimeCpu[i]);
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                    }
                        }
                        
                        
                        
                        
                        
                       // System.out.println("start &&&&&&&&&&&&&& "+cpu[i].startTime);
                       // System.out.println("t1[i] &&&&&&&&&&&&&& "+t1[i]);
                       // System.out.println("t2 &&&&&&&&&&&&&& "+t2);
                      //  System.out.println("endtime &&&&&&&&&&&&&& "+endTime[i]);
                      //  po.gap=t1[i]-endTimeCpu[i]+cpu[i].startTime;
                        gc[cpu[i].pid] = System.currentTimeMillis();
                        flag[cpu[i].pid] = 1;
                        t.setStartTime(gc[cpu[i].pid]);
                    }
                   /* else
                    {
                        po.gap=t1[i]-endTimeCpu[i];
                    }*/
                    
                    t1[i] = System.currentTimeMillis()-ref;
                    
                    
                    
                  //  po.gap=t1[i]-endTimeCpu[i];
                    

                    Process.removeFromQueue(readyQueue, cpu[i]);
                    numberOfTimeslice++;
                    t.setClockReference(clockReference);
                    t.setNumberOfTimeslice(numberOfTimeslice);
                    

                     //po.pid = cpu[i].pid;
                  
//                    System.out.println("start &&&&&&&&&&&&&& "+cpu[i].startTime);
//                        System.out.println("t1[i] &&&&&&&&&&&&&& "+t1[i]);
//                        System.out.println("t2 &&&&&&&&&&&&&& "+t2);
//                        System.out.println("endtime &&&&&&&&&&&&&& "+endTimeCpu[i]+"    "+i);
                 //   System.out.println("***  gap = "+po.gap);
                    if (processOrder[i] == null) {
                        processOrder[i] = new LinkedList<ProcessOrder>();
                    }
                   
                    // t1[i] = System.currentTimeMillis()-ref;
                    
                    t.setStartTimeOfTimeslice(System.currentTimeMillis());
                    t.start();
                }
            }
            if (cpu[i] != null && cpu[i].timesliceOver == true && cpu[i].finish == false) {
                t2 = System.currentTimeMillis()-ref;
                IOTime=cpu[i].currentIOTime;    
                po = new ProcessOrder(cpu[i].pid,t1[i],t2-IOTime);
                po.duration = t2 - t1[i]-IOTime;
//                System.out.println("******************************************* duration "+po.duration);
                processOrder[i].add(po);//till here
              //  processOrder[i].add(po);
                //  index++;
                cpu[i].timesliceOver = false;
                cpu[i].contextSwitch++;
                endTimeCpu[i] = cpu[i].endTimeCpu;
                endTime[i]=cpu[i].timeOfTimesliceOver;  
                cpu[i] = null;
            }
            if (cpu[i] != null && cpu[i].finish == true) {
                t2 = System.currentTimeMillis()-ref;
                             //   processOrder[index]=cpu[i].pid;
                //     duration[index]=t2-t1;
                //    po = new ProcessOrder(cpu[i].pid, t1[i], t2);//change
                //   po.pid = cpu[i].pid;
                //  po.gap = t1[i] - endTimeCpu[i];
                //  po.duration = t2 - t1[i];
                //  processOrder[i].add(po);
               IOTime=cpu[i].currentIOTime;   
               po = new ProcessOrder(cpu[i].pid,t1[i],t2-IOTime);
                po.duration = t2 - t1[i]-IOTime;
                processOrder[i].add(po);//till here
                processList.add(cpu[i]);
                endTimeCpu[i] = cpu[i].endTimeCpu;
                endTime[i]=cpu[i].timeOfTimesliceOver;
                cpu[i] = null;
            }
        }
    }
}
