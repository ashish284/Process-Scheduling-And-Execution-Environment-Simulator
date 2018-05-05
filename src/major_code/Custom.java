package major_code;

import java.util.LinkedList;

public class Custom {

    public void execCustom(InputModule inputModule, OutputModule out) {
        LinkedList<Process> waitQueue = new LinkedList<Process>();
        LinkedList<Process> readyQueue = new LinkedList<Process>();
        LinkedList<Process> processList;
        int size;
        readyQueue = inputModule.createReadyQueue();
        size = readyQueue.size();
        int cores = Processor.getNumberOfCores();
        Process cpu[] = new Process[cores];
        for (int i = 0; i < cores; i++) {
            cpu[i] = null;
        }
        CustomExecution execution = new CustomExecution(cpu, cores);
        execution.setReadyQueue(readyQueue);
        execution.setWaitQueue(waitQueue);
        processList = execution.getProcessList();
        while (processList.size() != size) {

            execution.cpuExecution();
            processList = execution.getProcessList();
        }
        LinkedList<ProcessOrder>[] processOrder = execution.getProcessOrder();
        OutputGanttChart o = new OutputGanttChart(processOrder, processList.getLast().endTime,6,size,out,processList);
//        out.displayTable(processList, 6);
    }
}
