package major_code;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Process implements Comparable<Process>
{
	final int MAX_BURST = 100;
	int pid;
	long arrivalTime;
	long startTime;
	int ioPercent;
	long endTime;
	long waitingTime;
	int currentBurst;
        int newCpuBurst;
        long remainingBurst;
        int ioTotalDuration;
	int burstCount;
	long cpuBurst;
	long ioQueueWait[] = new long[MAX_BURST/2];
	long bursts[] = new long[MAX_BURST];
	int priority;
        boolean finish;
        boolean timesliceOver;
        long timeOfTimesliceOver;
	int currentQueue;
	int contextSwitch;
	int multithreading;
        long currentIOTime;
        long endTimeCpu;
        
        int getPid()
	{
		return pid;
	}
        
        static synchronized void addToQueue(LinkedList<Process> queue, Process p)
        {
                queue.add(p);
        }
        
        static synchronized void addToQueue(LinkedList<Process> queue, Process p,int index)
        {
                queue.add(index,p);
        }
        
        static synchronized void removeFromQueue(LinkedList<Process> queue, Process p)
        {
                queue.remove(p);
        }
        
	long getIoBurst()
	{
		long ioBurst=0;
		for(int i=1;i<MAX_BURST;i+=2)
		{
			ioBurst+=bursts[i];
		}
		return ioBurst;
	}
        
        long getArrivalTime()
	{
		return arrivalTime;
	}
	
        long getStartTime()
	{
		return startTime;
	}
	
        long getWaitingTime()
	{
		return waitingTime;
	}
	
        long getNewCPUBurst()
	{
		return newCpuBurst;
	}
	
        long getEndTime()
	{
		return endTime;
	}
	
        int getioPercent()
	{
		return ioPercent;
	}
	
        int getIoTotalDuration()
	{
		return ioTotalDuration;
	}
	
        int getContextSwitches()
	{
		return contextSwitch;
	}
	
        long getTotalIoWait()
	{
		long total=0;
		int i;
		for(i=0;i<MAX_BURST/2;i++)
		{
			total+=ioQueueWait[i];
		}
		return total;
	}

        int getRandomNumberInRange(int min, int max) 
	{
                Random r = new Random();
                return r.nextInt((max - min) + 1) + min;
	}
		
        public int compareTo(Process st)
        {  
                if(arrivalTime==st.arrivalTime)  
                        return 0;  
                else if(arrivalTime>st.arrivalTime)  
                        return 1;  
                else  
                        return -1;  
        } 
        
	void IOCalculate(float clockSpeed)
	{   
                FileReader fr = null;
				String fileName;
				String workingDirectory = System.getProperty("user.dir");
				if (System.getProperty("os.name").equals("Linux")) {
					fileName = workingDirectory + "/src/major_code/";
				} else {
					fileName = workingDirectory + "\\src\\major_code\\";
				}	
                try 
                {
                        fr = new FileReader(fileName+"IODetails.cvs");
                } 
                catch (FileNotFoundException ex) 
                {
                        System.out.println(ex);
                }
                BufferedReader br = new BufferedReader(fr);
                String strLine;
                int a[] = new int[3]; // To read % IO, max IO and min IO from the file
                int i=0,k,j,sum=0;
                newCpuBurst=(int)(cpuBurst/clockSpeed);
//                System.out.println("%%% "+newCpuBurst);
                remainingBurst=newCpuBurst;
                ioTotalDuration=(ioPercent*newCpuBurst)/(100-ioPercent);
                
                int ioDuration[] = new int[15]; // For individual IO duration
                int numberOfOccurrencesOfIo;
                int ioInstant[] = new int[15];
                int ioIntervals[] = new int[15];
                k=ioPercent/10+1;
                try 
                {
                        while((strLine=br.readLine())!=null && i!=k+1)
                        {
                                if(i==0)
                                {
                                        i++;
                                        continue;
                                }
                                if(i==k)
                                {
                                        String[] arr = strLine.split(",");
                                        for(j=0;j<3;j++)
                                        {
                                                a[j]=Integer.parseInt(arr[j]);
                                        }
                                }
                                i++;
                        }
                } 
                catch (IOException ex) 
                {
                        System.out.println(ex);
                }
                numberOfOccurrencesOfIo = getRandomNumberInRange(a[1],a[2]);
                
//                System.out.println("io duration * " + ioTotalDuration);
//                System.out.println("numberofoccurences * " + numberOfOccurrencesOfIo);
                if(ioTotalDuration < numberOfOccurrencesOfIo)
                {
                    numberOfOccurrencesOfIo = ioTotalDuration;
                }
                
                if(newCpuBurst < numberOfOccurrencesOfIo)
                {
                    numberOfOccurrencesOfIo = newCpuBurst;
                }
                
                if(numberOfOccurrencesOfIo == 0)
                {
                    
                    bursts[0]=newCpuBurst;
                    burstCount=1;
//                    System.out.println("$$$$$"+bursts[0]);
                    return;
                }
                
                int flag;
                for(j=0 ; j<numberOfOccurrencesOfIo ; j++)
                {
                 //   ioIntervals[j]=j*newCpuBurst/numberOfOccurrencesOfIo;
                    ioInstant[j]=getRandomNumberInRange(1,newCpuBurst-1);
                    while(true)
                        {
                                flag=0;
                                for(int l=1; l<j; l++)
                                {
                                        if(ioInstant[j]==ioInstant[l])
                                        {
                                                ioInstant[j]=getRandomNumberInRange(1,newCpuBurst-1);
                                                flag=1;
                                        }
                                }
                                if(flag==0)
                                { 
                                    
                                        break;
                                }
                        }
                }
                try 
                {
                        br.close();
                } 
                catch (IOException ex) 
                {
                        System.out.println(ex);
                }
        //        for(j=0; j<numberOfOccurrencesOfIo-1; j++)
        //                ioInstant[j]=getRandomNumberInRange(ioIntervals[j]+1,ioIntervals[j+1]);
        //        ioInstant[j]=getRandomNumberInRange(ioIntervals[j]+1,newCpuBurst-1);
                ioDuration[0]=0;
               
                for(j=1; j<numberOfOccurrencesOfIo; j++)
                {
                        ioDuration[j]=getRandomNumberInRange(1,ioTotalDuration-1);
                        while(true)
                        {
                                flag=0;
                                for(int l=1; l<j; l++)
                                {
                                        if(ioDuration[j]==ioDuration[l])
                                        {
                                                ioDuration[j]=getRandomNumberInRange(1,ioTotalDuration-1);
                                                flag=1;
                                        }
                                }
                                if(flag==0)
                                { 
                                        break;
                                }
                        }
                }
                int temp;
                for (j = 0; j < numberOfOccurrencesOfIo; j++) 
                {
                        for (int l = j + 1; l < numberOfOccurrencesOfIo; l++) 
                        {
                                if (ioInstant[j] > ioInstant[l]) 
                                {
                                        temp = ioInstant[j];
                                        ioInstant[j] = ioInstant[l];
                                        ioInstant[l] = temp;
                                }
                        }
                }
                for (j = 0; j < numberOfOccurrencesOfIo; j++) 
                {
                        for (int l = j + 1; l < numberOfOccurrencesOfIo; l++) 
                        {
                                if (ioDuration[j] > ioDuration[l]) 
                                {
                                        temp = ioDuration[j];
                                        ioDuration[j] = ioDuration[l];
                                        ioDuration[l] = temp;
                                }
                        }
                } 
                for(j=0; j<numberOfOccurrencesOfIo; j++)
//                    System.out.println("ioinstant = "+ioInstant[j]);
                for(j=0; j<numberOfOccurrencesOfIo-1; j++)
                {
                        ioDuration[j]=ioDuration[j+1]-ioDuration[j];
                }
                ioDuration[j]=ioTotalDuration - ioDuration[j];
                i=0;
                k=0;
                for(j=0; j<numberOfOccurrencesOfIo; j++)
                        sum += ioDuration[j];
                bursts[0]=ioInstant[i++];
                for(j=1; j<2*numberOfOccurrencesOfIo; j++)
                {
                        if(j%2!=0)
        		{
                                bursts[j]=ioDuration[k++];				
                        }
                	else
                        {
                                bursts[j]=ioInstant[i]-ioInstant[i-1];
                                i++;
        		}
                }
                bursts[j]= newCpuBurst - ioInstant[i-1];
                burstCount = 2*numberOfOccurrencesOfIo + 1;
                
	}
}
        
        

