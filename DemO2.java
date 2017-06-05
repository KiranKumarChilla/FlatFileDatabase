import java.io.*;
import java.util.*;
import java.math.*;
public class DemO2{
public static void main(String[] args){  
        System.out.println("Enter the query:");
        Scanner scanner = new Scanner(System.in);
        String query;
        query = scanner.nextLine();
        int fileCount=0,counter=1;
        int prime=0,count=0,acknowledge=0;
         List<String> attributeList=new ArrayList<String>();
         if(query.startsWith("INSERT")){
        	 insertQuery(query);
        	 acknowledge=1;}
         if(query.startsWith("SELECT")){
        	 selectQuery(query);
        	 acknowledge=1;}
         if(query.startsWith("UPDATE")){
        	 updateQuery(query);
        	 acknowledge=1;}
         if(query.startsWith("DELETE")){
        	 deleteQuery(query);
        	 acknowledge=1;
        	 }	
         if(acknowledge==0){
         String[] delimters = query.split("\\(+|\\;+|\\)+|\\,+|\\:+|\\-+");
         for(String object:delimters){
        	 if(!object.equals(null)){
        		 attributeList.add(object.trim());
        		}
        	 }
         attributeList.removeAll(Arrays.asList("", null));
         File f=new File("./"+attributeList.get(0));
         if(!f.exists())
         {
        	 f.mkdir();
        	 try{
        		 BufferedWriter bufferReader = new BufferedWriter(new OutputStreamWriter(new DataOutputStream(new 

FileOutputStream("./"+attributeList.get(0)+"/"+"OUTPUT.txt",true))));
        		 int attributeValue=1;
        		 	for(attributeValue=1;attributeValue<attributeList.size();attributeValue=attributeValue+2){
        		 		if(attributeList.get(attributeValue+1).toLowerCase().contains("key") && ((attributeList.get

(attributeValue+2).matches("(?attributeValue:string|integer|date)"))))
        		 		{
        		 			if(attributeList.get(attributeValue+2).equals("integer")){
        		 				bufferReader.write(attributeList.get(attributeValue)+"(1) ");
        		 				count++;
        		 				}
        		 			if(attributeList.get(attributeValue+2).equals("string")){
        		 				bufferReader.write(attributeList.get(attributeValue)+"(0) ");
        		 				count++;
        		 				}

        		 			if(attributeList.get(attributeValue+2).equals("date")){
        		 				bufferReader.write(attributeList.get(attributeValue)+"(2) ");
        		 				count++;
        		 				}

        		 			attributeValue++;prime=counter;counter++;fileCount=1;
        		 			}

        		 		else if(attributeList.get(attributeValue+1).matches("(?attributeValue:string|integer|date)")){

        		 			if(attributeList.get(attributeValue+1).equals("string")){
        		 				bufferReader.write(attributeList.get(attributeValue)+"(0) ");
        		 				count++;}

        		 			if(attributeList.get(attributeValue+1).equals("integer")){
        		 				bufferReader.write(attributeList.get(attributeValue)+"(1) ");
        		 				count++;}

        		 			if(attributeList.get(attributeValue+1).equals("date")){
        		 				bufferReader.write(attributeList.get(attributeValue)+"(2) ");
        		 				count++;}

        		 			counter++;fileCount=1;
        		 			}

        		 		else{
        		 			System.out.println("Error in query");
        		 			attributeValue=attributeList.size();
        		 			fileCount=0;
        		 			}
        		 		}
        		 	
         			bufferReader.close(); 
         			}
        	 catch(Exception exception)
        	 {
				exception.printStackTrace();
				}
        	 if(fileCount==0){

        		 File file= new File("./"+attributeList.get(0));
        		 file.delete();
        		 }
        	 }

         	else{
         		System.out.println("The Table exists!");
         		}



         if(fileCount==1){

         try{

         BufferedWriter bufferReader = new BufferedWriter(new OutputStreamWriter(new DataOutputStream(new FileOutputStream

("./OUTPUT.txt",true))));
         bufferReader.write(attributeList.get(0)+"("+count+","+prime+")");
         bufferReader.write(System.getProperty("line.separator"));

         bufferReader.close(); } 
         catch(Exception exception){exception.printStackTrace();}}}

		}

public static void insertQuery(String query){
		
         List<String> queryValues=new ArrayList<String>();
         String[] delimiters = query.split("\\(+|\\,+|\\)+|\\-+");
         String[] value=delimiters[0].split("\\s");
         queryValues.add(value[value.length-1]);

         if(!(value[1]).toLowerCase().equals("into")|| !(value[3].toLowerCase().equals("values"))){

         System.out.println("Check the query");
         }

         for(int l=1;l<delimiters.length;l++){

                if(!delimiters[l].equals(null)){
                	queryValues.add(delimiters[l].trim());
                	}
                }
         queryValues.removeAll(Arrays.asList("", null));
         File file = new File("./"+value[2]+"/OUTPUT.txt");
         if(file.exists()){
        	 try{
        		 BufferedReader fileReader=new BufferedReader(new InputStreamReader(new FileInputStream("./"+value

[2]+"/OUTPUT.txt")));
        		 BufferedReader bufferReader=new BufferedReader(new InputStreamReader(new FileInputStream("./OUTPUT.txt")));
        		 String string;int lenght=0,prime=0;
        		 while((string=bufferReader.readLine())!=null){if(string.startsWith(value[2])){
        			 String[] val=string.split("\\(+|\\)+|\\,");
        			 prime=Integer.parseInt(val[val.length-1]);
        }}

        while((string = fileReader.readLine()) != null){
        	String[] OUTPUT=string.split("\\s++|\\,+|\\(+|\\)");
        	lenght=OUTPUT.length-1;
        	}

        if(queryValues.size() == lenght/2){
        File fp=new File("./"+value[2]+"/"+queryValues.get(prime-1)+".txt");
        if(fp.exists()){
        	System.out.println("Tuple with primary key "+queryValues.get(prime-1)+" already exists");}
        else{
        	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new DataOutputStream(new FileOutputStream("./"+value

[2]+"/"+queryValues.get(prime-1)+".txt",true))));
        for(int p=0;p<queryValues.size();p++){bw.write(queryValues.get(p)+"|");}
        bw.close();}
        }
        else{System.out.println("Missing attributes in OUTPUT");
        }
        fileReader.close();}
        catch(Exception exception){exception.printStackTrace();}
        }
}

public static void selectQuery(String query){

        List<String> queryList=new ArrayList<String>();
        List<String> List=new ArrayList<String>();
        String[] delimiters=query.split("(?<=(?i)where) ");
        if(delimiters.length>2){
        	System.out.println("Error in the query");}
        else{
        	String[] value=delimiters[0].split("\\s+|\\,");
        	if(delimiters.length==1){
        		for(int i=1;i<value.length-2;i++){
        			queryList.add(value[i].trim());}
        try{

        BufferedReader fileReader=new BufferedReader(new InputStreamReader(new FileInputStream("./OUTPUT.txt")));
        String sr;int fileCount=0;
        while((sr = fileReader.readLine())!=null){
        	if(sr.toLowerCase().startsWith(value[value.length-1].toLowerCase())){
        		fileCount=1;
        		break;}}
        if(fileCount==1){
        		BufferedReader bufferReader=new BufferedReader(new InputStreamReader(new FileInputStream("./"+value[value.length-

1]+"/OUTPUT.txt")));
        		String rs;String[] st=null;
        		while((rs=bufferReader.readLine())!=null){
        			st=rs.split("\\s+|\\(+|\\) ");}
        			File filei=new File("./"+value[value.length-1]);
        			File[] files = filei.listFiles(); int counter=0;
        			for (File file:files) {
        				if (!(file.getName().equals("OUTPUT.txt")) && file.isFile() && file.exists()) {
        					try{
        						BufferedReader readFile=new BufferedReader(new InputStreamReader(new 

FileInputStream(file)));
        						String readF;String[] delimr=null;
        						int count=0;counter=0;
        						while((readF=readFile.readLine())!=null){
        							delimr=readF.split("\\|");}
        						for(int i=0;i<queryList.size();i=i+1){
        							for(int j=0;j<delimr.length;j++){
        								if(queryList.get(i).equals(st[2*j])){
        									System.out.print(delimr[j]+" ");count++;counter++;}}
        						}if(count>0){System.out.println();}
        readFile.close();}
        catch(IOException excpetion){excpetion.printStackTrace();}}}
        if(counter!=queryList.size()){System.out.println("Incomplete data:Error in matching attributes");}}
        else{System.out.println("Table "+value[value.length-1]+" does not exists");}
        fileReader.close();}catch(IOException excpetion){excpetion.printStackTrace();}
        }
        if(delimiters.length==2){
        	String[] n1=delimiters[1].split("\\,+|\\=+|(?i)OR");
        for(int i=0;i<n1.length;i++){List.add(n1[i].trim());}
        for(int i=1;i<value.length-3;i++){queryList.add(value[i].trim());}
        try{
        BufferedReader fileReader=new BufferedReader(new InputStreamReader(new FileInputStream("./OUTPUT.txt")));
        String sr;int fileCount=0;
        while((sr = fileReader.readLine())!=null){if(sr.toLowerCase().startsWith(value[value.length-2].toLowerCase()))

{fileCount=1;break;}}
        if(fileCount==1){
        	BufferedReader bufferReader=new BufferedReader(new InputStreamReader(new FileInputStream("./"+value[value.length-

2]+"/OUTPUT.txt")));
        String rs;String[] st=null;
        while((rs=bufferReader.readLine())!=null){
        st=rs.split("\\s+|\\(+|\\) ");}
        File filei=new File("./"+value[value.length-2]);
        File[] files = filei.listFiles(); int counter=0;
        for (File file:files) {
        	if (!(file.getName().equals("OUTPUT.txt")) && file.isFile() && file.exists()) {
        		try{
        			BufferedReader readFile=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        			String readF;String[] delimr=null;int count=0;counter=0;
        			while((readF=readFile.readLine())!=null){delimr=readF.split("\\|");}
        					for(int i=0;i<queryList.size();i=i+1){
        	for(int j=0;j<delimr.length;j++){
        if(queryList.get(i).equals(st[2*j])){
        for(int u=0;u<List.size();u=u+2){
        for(int v=0;v<st.length;v=v+2){
        if(List.get(u).equals(st[v])){
        if(List.get(u+1).equals(delimr[v/2])){
        System.out.print(delimr[j]+" ");
        count++;
        counter++;
        }}}}}} 
        }
        if(count>0){System.out.println();}
        readFile.close();}
        catch(IOException excpetion){excpetion.printStackTrace();}}}
        if(counter!=queryList.size()){System.out.println("No other matching attributes");}}
        else{System.out.println("Table "+value[value.length-1]+" does not exists");}
        fileReader.close();}
        catch(Exception exception){
        	exception.printStackTrace();}}
            }
}

public static void updateQuery(String query){

        List<String> queryList=new ArrayList<String>();
        List<String> list=new ArrayList<String>();
        int count=0;
        String[] delimiter=query.split("(?<=(?i)where) ");
        if(delimiter.length!=2){System.out.println("Query has errors");}
        else{
        String[] value=delimiter[1].split("\\,+|\\=+|(?i)OR");
        for(int l=0;l<value.length;l++){if(!value[l].equals(null)){list.add(value[l].trim());}}
        String[] m=delimiter[0].split("\\s+|\\,+|\\=");
        if(!m[0].equalsIgnoreCase("update") || !m[2].equalsIgnoreCase("set") || !m[m.length-1].equalsIgnoreCase("where"))

{System.out.println("Error in query");}
        else{
        for(int k=3;k<m.length-1;k++){queryList.add(m[k].trim());}
        String[] st=null;
        try{
        BufferedReader bufferReader=new BufferedReader(new InputStreamReader(new FileInputStream("./"+m[1]+"/OUTPUT.txt")));
        String string;
        while((string=bufferReader.readLine())!=null){
        	st=string.split("\\s+|\\(+|\\) +");}
        }
        catch(Exception excpetion){excpetion.printStackTrace();}
        File filei=new File("./"+m[1]);
        File[] files = filei.listFiles();
        for (File file:files) {
        if (!(file.getName().equals("OUTPUT.txt")) && file.isFile() && file.exists()) {
        int fileCount=0,accept=0;String readF;String[] delimiters=null;
        try{

        BufferedReader readFile=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        while((readF=readFile.readLine())!=null){delimiters=readF.split("\\|");}
        for(int x=0;x<list.size();x=x+2){ accept=0;
        for(int y=0;y<st.length;y=y+2){if(list.get(x).equals(st[y])){ accept++;
                                       if(list.get(x+1).equals(delimiters[y])){fileCount=1;break;}}}}
        readFile.close();   }catch(IOException excpetion){excpetion.printStackTrace();}
        if(accept==0){System.out.println("No Matching Attributes");}
        if(fileCount==1){
        for(int x=0;x<queryList.size();x=x+2){accept=0;
        for(int y=0;y<st.length;y=y+2){if(queryList.get(x).equals(st[y])){accept++;
        int acknowledge=0;
        String stringL=null;
        try{String stringLine;
        BufferedReader bw = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(file))));
        while((stringLine=bw.readLine())!=null){
        	stringL=stringLine.replace(delimiters[y/2],queryList.get(x+1));acknowledge=1;}
        bw.close();
           }catch(Exception excpetion){excpetion.printStackTrace();}
        if(accept==0){System.out.println("No Matching Attributes");}
        if(acknowledge==1){
        try{
        BufferedWriter bufferReader = new BufferedWriter(new OutputStreamWriter(new DataOutputStream(new FileOutputStream(file,false))));
        count++;
        bufferReader.write(stringL);bufferReader.close();}catch(Exception excpetion){excpetion.printStackTrace();}}
                              }}}
                }
        }}
        }}
        System.out.println("Rows updated: "+count);
}

public static void deleteQuery(String s){

        List<String> queryList=new ArrayList<String>();
        String[] delimiter = s.split("\\=+|(?i)OR");
        String[] value=delimiter[0].split("\\s");
        queryList.add(value[value.length-1]);
        for(int l=1;l<delimiter.length;l++){if(!delimiter[l].equals(null)){queryList.add(delimiter[l].trim());}}
        if(!(value[1].toLowerCase().equals("from")) || !(value[3]).toLowerCase().equals("where")){
        System.out.println("Query has errors");}
        File file=new File("./OUTPUT.txt");
        if(file.exists()){
        try{
        	BufferedReader bufferReader=new BufferedReader(new InputStreamReader(new FileInputStream("./OUTPUT.txt")));
        	String string;int fileCount=0;
        	while((string = bufferReader.readLine())!=null){if(string.toLowerCase().startsWith(value[2].toLowerCase()))

{fileCount=1;break;}}
        if(fileCount==1){
        BufferedReader fileReader=new BufferedReader(new InputStreamReader(new FileInputStream("./"+value[2]+"/OUTPUT.txt")));	
        String rs;String[] st=null;int count=0,counter=0,i=0,j=0;
        while((rs=fileReader.readLine())!=null){
        st=rs.split("\\(+|\\) +|\\s+");}
        int delete=0;
        File filei=new File("./"+value[2]);
        File[] files = filei.listFiles();
        for (File fil:files) {
        if (fil.isFile() && fil.exists()) {
        try{
        BufferedReader readFile=new BufferedReader(new InputStreamReader(new FileInputStream(fil)));
        String readF;String[] delimr=null;
        while((readF=readFile.readLine())!=null){delimr=readF.split("\\|");}
        for(i=0;i<queryList.size();i=i+2){
        for(j=0;j<delimr.length;j++){
        if(queryList.get(i).equals(st[2*j])){counter++;
        if(queryList.get(i+1).equals(delimr[j])){delete=1;count++;break;}}}
        if(counter==0){break;}}
        readFile.close();   }
        catch(IOException excpetion){excpetion.printStackTrace();}
        if(delete==1){file.delete();}
                  }
             }
        if(counter==0){System.out.println("No attribute exists with: "+queryList.get(i));}
        System.out.println("Rows affected: "+count);}
        else{System.out.println("Table "+value[2]+" does not exist");}
        bufferReader.close();}catch(IOException excpetion){excpetion.printStackTrace();}}
        else{System.out.println("Table "+value[2]+"does not exist");}
}
}

