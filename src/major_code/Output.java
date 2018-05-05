package major_code;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Formatter;

class Output
{
	long arrival,start,end,burst,wait,ioWait,ioBurst;
	int ioPercent,contextSwitches,pid,ioTotalDuration;
	void displayTable(LinkedList<Process> processes)
	{
		System.out.println("Pid   |Arrival   |  Start   | Execution  | IO      | Exit    | Response  | Turn Around | Wait  | Context  | Io Wait");
		System.out.println("      |   Time   |  Time    |   Time     |  Time   |  Time   |   Time    | Time        |  Time | Switches | Time ");
		Iterator<Process> iterator=processes.iterator();
		while(iterator.hasNext())
		{
			Process p=iterator.next();
			arrival=p.getArrivalTime();
			start=p.getStartTime();
			end=p.getEndTime();
			burst=p.getNewCPUBurst();
			wait=p.getWaitingTime();
			ioPercent=p.getioPercent();
			contextSwitches=p.getContextSwitches();
			ioWait=p.getTotalIoWait();
                        ioBurst=p.getIoBurst();
			pid=p.getPid();
                        ioTotalDuration=p.getIoTotalDuration();
			//System.out.format("%7d%16d%11d%15d%18d%11d%17d",arrival,burst,end,(start-arrival),(end-arrival),wait,contextSwitches);
                        System.out.println("   "+pid+"  |  "+arrival+"     |    "+start+"    |     "+burst+"    |    "+ioTotalDuration+"    |    "+(end)+"   |    "+(start-arrival)+"     |    "+(end-arrival)+"    |     "+(end-arrival-burst-ioWait-ioBurst)+"    |     "+contextSwitches+"    |    "+ioWait+"");
			}
	}
	
}

