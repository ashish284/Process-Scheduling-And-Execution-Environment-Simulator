/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package major_code;

/**
 *
 * @author Admin
 */
public class ProcessOrder {
    int pid;
    long gap;
    long duration;
    long startTime;
    long endTime;
    
    public ProcessOrder()
    {
    }
    
    public ProcessOrder(int pid, long duration)
    {
    this.pid=pid;
    this.duration=duration;
    }
    
    public ProcessOrder(int pid, long startTime, long endTime)
    {
        this.pid=pid;
        //this.duration=duration;
        this.startTime=startTime;
        this.endTime=endTime;
    }


}
