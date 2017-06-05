import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Demo {

public static void inputFile(List<String> attributeList,List<String> outputList) {
	 int tableCount=1;
	 int prime=0;	 
	try{ 
		    	System.out.println("Enter the file name:");
		    	String fileName;	
		    	Scanner scaner = new Scanner(System.in);
		    	fileName= scaner.nextLine();
		         BufferedReader reader = new BufferedReader(new FileReader(fileName));
		         String strLine;
			 
		         while ((strLine = reader.readLine()) != null)
		         {
		        	 if(!((strLine.startsWith("Write"))||(strLine.startsWith("Read"))||(strLine.startsWith("Output"))||(strLine.startsWith("Delete"))))
		        	 {	 
		        		 String[] delims = strLine.split("\\(+|\\:+|\\;+|\\)+|\\,+|\\-+");
		        		 for(String attributes:delims)
		        		 {
		        			 if(!attributes.equals(null))
		        			 {
		        				 attributeList.add(attributes.trim());
		        			 }
		        		 }
		        	 }
		        	 if((strLine.startsWith("Write"))||(strLine.startsWith("Read"))||(strLine.startsWith("Output"))||(strLine.startsWith("Delete")))
		        	 {
		        		 String[] delims = strLine.split("\\(+|\\:+|\\;+|\\)+|\\-+");
		        		 for(String attributes:delims)
		        		 {
		        			 if(!attributes.equals(null))
		        			 {
		        				 attributeList.add(attributes.trim());
		        			 }
					 	}
		        	 }  
		         }
		         attributeList.removeAll(Arrays.asList("", null));
		      reader.close();
		      scaner.close();
		 	}
			catch(IOException e)
		    {
				System.err.println("File not found ");
		    }
			int attributeValue=0; 
			String schema=attributeList.get(attributeValue);
			attributeValue++;
			try
			{
				PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter("./output.txt",true)));
			    w.write("SCHEMA:  "+schema);
			    w.write(System.getProperty("line.separator"));
			    w.close();
			 }
			catch(IOException e)
			{
				e.printStackTrace();
			}
			while(!(attributeList.get(attributeValue).equals("Write"))||(attributeList.get(attributeValue).equals("Read"))||(attributeList.get(attributeValue).equals("Delete"))||(attributeList.get(attributeValue).equals("Output")))
			{
				if(attributeList.get(attributeValue).equals("STRING")||attributeList.get(attributeValue).equals("INTEGER"))
				{
					if(attributeList.get(attributeValue-1).contains("Key"))
					{
						outputList.add(attributeList.get(attributeValue-2));
						prime=tableCount;
						tableCount++;
						
					}
					else
					{
						outputList.add(attributeList.get(attributeValue-1));
						tableCount++;
									
					}
				}
				attributeValue++;
			}
			outputFile(attributeList,outputList,attributeValue,tableCount, prime);
	}

private static void outputFile(List<String> attributeList, List<String> outputList,int attributeValue,int tableCount ,int prime) {
	
	int count=0;
	int countl=0;
    for(attributeValue=attributeValue;attributeValue<attributeList.size();attributeValue++)
    { 
    	if(attributeList.get(attributeValue).equals("Write"))
    	{ 
    		try
    		{
    			int j=0;
    			if(count>0)
    			{
    				for(int consoleOutput=tableCount-1;consoleOutput<outputList.size();consoleOutput=consoleOutput+tableCount-1)
    				{
    					if(attributeList.get(attributeValue+1).equals(outputList.get(consoleOutput)))
    					{
    						System.out.println("data with primary key element "+outputList.get(consoleOutput)+" cannot be inserted");
    						count++;
    						countl++;
    						System.out.println();
    					}
    					else
    					{
    						countl=0;
    					}		
    				}
    			}				
    			if(countl==0)
    			{
    				for(j=attributeValue+1;j<attributeValue+tableCount;j++)
    				{
    					outputList.add(attributeList.get(j));
    				
    				}		
    				count++;
    			}
    		}	
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    	}
    	if(attributeList.get(attributeValue).equals("Read"))
    	{
    		int readValue=0;
    		int counter=0;
    		while(readValue<tableCount-1)
    		{
    			if(attributeList.get(attributeValue+1).equals(outputList.get(readValue)))
    			{
    				int m=readValue+tableCount-1;
    				while(m<outputList.size())
    				{
    					if(((outputList.get(m)).equals(attributeList.get(attributeValue+2)))||((outputList.get(m)).contains(attributeList.get(attributeValue+2)+" "))||((outputList.get(m)).contains(" "+attributeList.get(attributeValue+2))))
    					{	
    						for(int consoleOutput=m-readValue;consoleOutput<=m+tableCount-readValue-2;consoleOutput++)
    						{
    							String format = "%-33s";
    							System.out.printf(format,outputList.get(consoleOutput)+" ");
    						}
    						System.out.print("\n");
    						System.out.print("\n");
    						m=m+tableCount-1;
    						counter++;
    					}
    					else
    					{
    						m=m+tableCount-1;
    					}
    				}
    				if(counter==0)
    				{
    					System.out.println(attributeList.get(attributeValue+2)+" does not exist"+" in "+attributeList.get(attributeValue+1));
    					System.out.print("\n");
    				}
				    readValue=tableCount;
    			}
    			else
    			{
    				readValue++;
    			}
    		}
    		if(readValue==tableCount-1)
    		{
    			System.out.println("column "+attributeList.get(attributeValue+1)+" does not exist");
    			System.out.print("\n");
    		}
    	}
    	if(attributeList.get(attributeValue).equals("Delete"))
    	{
    		int deleteValue=prime-1;
   
    		while(deleteValue<outputList.size())
    		{
    			if(attributeList.get(attributeValue+1).equals(outputList.get(deleteValue)))
    			{
    				for(int m=deleteValue-prime+1;m<deleteValue+tableCount-prime;m++)
    				{
    					outputList.remove(deleteValue-prime+1);
    				}
    				deleteValue=outputList.size()+1;
    			}
    			else
    			{
    				deleteValue=deleteValue+tableCount-1;
    			}
    		}
    		if(deleteValue==outputList.size())
    		{
    			System.out.println(attributeList.get(attributeValue+1)+" does not exist in database ");
    			System.out.print("\n");
    		}
    	
    	}
    	
   	if(attributeList.get(attributeValue).equals("Output"))
    	{
    		
    		for(int s=0;s<outputList.size();s=s+tableCount-1)
    		{try {
				PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("./output.txt",true)));
				for(int consoleOutput=s;consoleOutput<s+tableCount-1;consoleOutput++)
    			{
    				String format = "%-33s";
    				System.out.printf(format,outputList.get(consoleOutput)+" ");
    			writer.write(outputList.get(consoleOutput)+" ---------- ");
    			}
				writer.write(System.getProperty("line.separator"));
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
    			
    			System.out.print("\n");
    		}
    		System.out.println();
    	}
	} 
  }
public static void main(String[] args) {
	
	 List<String> outputList=new ArrayList<String>(); 
	 List<String> attributeList=new ArrayList<String>(); // all attributes   
	 inputFile(attributeList,outputList);
}	
}


