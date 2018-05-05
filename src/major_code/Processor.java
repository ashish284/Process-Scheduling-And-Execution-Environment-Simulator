package major_code;
import java.io.FileReader; 
import java.io.BufferedReader;
public class Processor
{
    private static int core;
    private static float clock;
        static void setNumberOfCores(int cores)
        {
            core=cores;
        }
	static int getNumberOfCores()
	{
		 String fileName;
        String workingDirectory = System.getProperty("user.dir");
        if (System.getProperty("os.name").equals("Linux")) {
            fileName = workingDirectory + "/src/major_code/";
        } else {
            fileName = workingDirectory + "\\src\\major_code\\";
        }
            if(core==0)
            {
		int coreDefault=0;
		try
		{
        		FileReader fr=new FileReader(fileName + "ProcessorContent.cvs");
                	BufferedReader br = new BufferedReader(fr);
        		String line;
                        int k=0;
			while((line=br.readLine())!=null)
			{
                                if(k==0)
                                {
                                        k++;
                                        continue;
                                }
				String[] arr = line.split(",");
				coreDefault=Integer.parseInt(arr[0]);	
			}
			br.close();
			fr.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return coreDefault;
            }
            else
                return core;
	}
	
        static void setClockSpeed(float clockspeed)
        {
            clock=clockspeed;
        }
        
	static float getClockSpeed()
	{
		String fileName;
        String workingDirectory = System.getProperty("user.dir");
        if (System.getProperty("os.name").equals("Linux")) {
            fileName = workingDirectory + "/src/major_code/";
        } else {
            fileName = workingDirectory + "\\src\\major_code\\";
        }
            if(clock==0.0f)
            {
                float speed=0;
		try
		{
                        FileReader fr=new FileReader(fileName+"ProcessorContent.cvs");
                	BufferedReader br = new BufferedReader(fr);
        		String line;
                        int k=0;
			while((line=br.readLine())!=null)
			{
                                if(k==0)
                                {
                                        k++;
                                        continue;
                                }
				String[] arr = line.split(",");			
                		speed=Float.parseFloat(arr[1]);		
                        }
			fr.close();
			br.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return speed;
            }
            else
                return clock;
	}
}
