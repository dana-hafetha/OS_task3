import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class functions {
	
	//Reading from the Request and the Allocation file
	public static void fileRead(ArrayList <Data> Processes , String path) throws Exception {
		
		String process = "";
		ArrayList<Integer> resources = new ArrayList<>();
		Data pro; 
		int index = 0;
		int from  = 0 ; 
		int to = 0;
     
		//create the buffered reader (Work like scanner but faster)
		BufferedReader reader = new BufferedReader(new FileReader(path));
		
		//read the first line 
		process = reader.readLine(); 
		
		//start from the second
		while ((process = reader.readLine())!= null) {
			  index = 0;
			String [] fields = process.split(",");
			while (index < (fields.length - 1)) {
				if (fields[index+1].equals("") == false) {
				resources.add(Integer.parseInt(fields[index+1]));
				index++;
				to++;
			}
				else 
					index++;
			}
			pro = new Data(fields[0]);
			Processes.add(pro);
			pro.res(resources,from,to);
		    from = to;
			}	
	}
	
	//for the second file 
	public static void read(ArrayList <Integer> resources , String path ) throws Exception{
		
		String res = "";
		int count = 0 ;
	     
		//create the buffered reader (Work like scanner but faster)
		BufferedReader reader = new BufferedReader(new FileReader(path));
		
		//read the first line 
		res = reader.readLine(); 
		
		//start from the second
	     res = reader.readLine();
	     
	    String[] fields = res.split(",");
	     
    	while (count < fields.length) {
    	   if (fields[count].equals("") == false) {
	     	resources.add(Integer.parseInt(fields[count])); /*start from Resource A */
         	count++;
    	   }
    	   else 
    		   count++;
	   }
  }

	//printing the Request and the Allocation file
	public static void filePrint(ArrayList<Data> process) {
		int index = 0;
		for (Data proces : process) {
            proces.print(index);
            index++;
        }
	}
	
	//for the second file 
	public static void print(ArrayList<Integer> process) {
		for (int num : process) {
            System.out.println(num);
        }
	}
	
	// a function to read from all the files
	public static void Read(ArrayList <Data> file1 , ArrayList <Integer> file2 , ArrayList <Data> file3, String path1, String path2 , 
			String path3) throws Exception{
		
		//first file (Allocation)
				fileRead(file1,path1);
				
				//read the second file (Available) 
				read(file2,path2);
				
				//Third file (Request)
				fileRead(file3,path3);
		
	}

	// a function to print all the files
	public static void Print(ArrayList <Data> file1 , ArrayList <Integer> file2 , ArrayList <Data> file3){
		//first file (Allocation)
		        filePrint(file1);
					
			//second file (Available) 
			    print(file2);
					
			//Third file (Request)
			    filePrint(file3);
	}
	
	// check if they have the same number of processes
	//if not throw exception
	public static void checkSize (ArrayList <Data> file1 , ArrayList <Data> file2) throws Exception{
	    
        
    	//the error in the processes
    	if (file1.size() < file2.size()) {
            throw new Exception("the number of processes in the Allocation file are lesser than the Request file ");
    	}
    	else if (file1.size() > file2.size()) {
            throw new Exception("the number of processes in the Request file are lesser than the Allocation file ");
   		}
    	
    }
		
	// check if 2 processes have the same name in the file 
	//if So throw exception
	public static void checkName (ArrayList <Data> file) throws Exception{
			Data currentProcess = null;
	    	for (int a = 0 ; a < file.size() ; a++ ) {
	    		currentProcess = file.get(a);
	    	for (int c = a+1 ; c < file.size() ; c++) { 
	    	    if (file.get(c).compareTo(currentProcess) == 1) {
	    	    	throw new Exception("The process have a same name as an other process");
	    	    }
	    	  }
	    	}	
	    }
			
    // check for all the processes in the same file is having the same number of resources
	//if not throw exception
    public static void checkResources (ArrayList <Data> file) throws Exception { 
			Data currentProcess = null;
			for (int a = 0 ; a < file.size() ; a++ ) {
	    		currentProcess = file.get(a);
	    	for (int c = a+1 ; c < file.size() ; c++) { 
	    	    if (file.get(c).compareToRes(currentProcess) == 0) {
	    	    	throw new Exception("The process have a different number of processes than the others");
	    	    }
	    	    
	    	  }
	    	}
	    }
          
    // compare the files resources with the available  and check if the are not the same 
    // throw exception
    public static void checkAvailable (ArrayList <Data> file , ArrayList <Integer> file2) throws Exception{
  		 
  			Data currentProcess = null;
  			for (int a = 0 ; a < file.size() ; a++ ) {
  	    		currentProcess = file.get(a); 
  	    	    if (currentProcess.getResources().size() != file2.size()) {
  	    	    	throw new Exception("The Resources are different in number");
  	    	    }
  	    	  }
  	    }
                
    // a function to insert in the array
    public static void insert (int [] insertIn , ArrayList <Integer> insertFrom) {
        	  
        	  for (int i = 0 ; i < insertFrom.size() ; i++) {
          		insertIn[i] = insertFrom.get(i);
          	}
          }
              
   // check the request and the work of the process 
   // if the request is lesser or equal than the work return true
   //else return false
    public static boolean checkCondition (int [] request , int [] work , int size) {
    	
        	  boolean condition = false;  
  		     for (int i = 0 ; i < size ; i++) {
  		      	if (request[i] <= work[i]) {
  			        // there is no deadlock the flag becomes true
  		      	condition = false;
  				     
  			    }
  			    else {
  				     // there is a deadlock
  			    	condition = true;
  				     break;
  			    }
  		}     
  	return condition;
  }
	
    // a function to check if the dimensions are consistent 
    // if found any thing wrong throw the specified exception
    public static void consistent (ArrayList <Data> file1 , ArrayList <Integer> file2 , ArrayList <Data> file3) throws Exception{
	    
        
    	//the error in the processes
		// if they don't have the same number of processes
    	checkSize(file1,file3);
    	
    	// if one of the processes have the same name as the others 
    	//check for the two files (Allocation and Request)
    	// first of the Allocation file
    	checkName(file1);
    	
    	//second of the Request file
    	checkName(file3);
    	
    	// check the errors in the resources ...
    	
    	// in the same file all the resources have to be the same size for every process 
    	// first of the Allocation file
         checkResources(file1);
         
         
    	//second of the Request file
         checkResources(file3);
    	
    	// compare the files resources with the available  
    	// first of the Allocation file
    	checkAvailable(file1, file2);

    	//second of the Request file
    	checkAvailable(file3, file2);

    }
    
 // a function to detect whether there is a deadlock or not
 	//if so there is a deadlock return true 
 	//if there isn't return false
     public static boolean checkDeadLock (ArrayList <Data> file1 , ArrayList <Integer> file2 , ArrayList <Data> file3) {
     	
     	int M = file2.size();
     	int sequanceIndex = 0;
     	
     	int [] Work = new int[M]; // the Available 
     	
         insert(Work, file2);
     	
        int [] request = new int [M];
     	
     	// printing the work array 
//     	for (int i = 0 ; i < M ; i++) {
//     		System.out.println(Work[i]);
//     	}

     	// go on the allocation process 
     	// initiate the finish values 
     	for (Data pro : file1) {
     		for (int index = 0 ; index < file1.get(0).getResources().size() ; index++) {
     			pro.setFinish(false);
     		}
     	}
     	
    Data process;
    int size = file1.size();
    for(int x = 0 ; x < size ; x++) { //loop the processes in the allocation
 	  process = file1.get(x);
     	if (process.isFinish() == false) {
     		//System.out.println(process.toString());
     		// check the request for the process
     		// first find it in the request file and get it 
     		for (Data p : file3){
     			if (p.getProcessName().equals(process.getProcessName())) {
     				// get the request and save it in the request array
     				insert(request, p.getResources());
     			}
     		// print the request
     		//System.out.println(request.length);
     	}
     		boolean condition = checkCondition(request, Work, M);
     		if (condition == false) {
     		// there is no deadlock 
     				process.setFinish(true);
     				// add request to the work
     				for (int v = 0 ; v < M ; v++) {
     				  Work[v] += process.getResources().get(v); // have to sum with the allocation not the request
     				}
     				sequanceIndex++;
     				process.setSequanceIndex(sequanceIndex);
     				//start from the beginning
     				x = -1;
     		}
     		else {
     	          // there is a deadlock 
     				process.setFinish(false);
     	    }
     	}
     }
    
    boolean result = false ;
   for (Data pro : file1) {
	   if (pro.isFinish() == false ) {
		   result = true; // there is a deadlock
		   break;
	   }
	   else 
		   result = false;// there isn't a deadlock
   }

  return result;
     	
     }

     
     // a function to print the deadLock sequence if it exists
     public static void printDSeq(ArrayList <Data> file) {
    	ArrayList<String> result = new ArrayList<>();
    	for(Data process : file){
    		if (process.isFinish() == false) {
    			result.add(process.getProcessName());
    		}
    	}
    	System.out.println(result.toString());
     }

  // a function to print the safe sequence if there is no deadLock
     public static void printSSeq(ArrayList <Data> file) {
    	 
    	 String [] result = new String[file.size()];
     	for(Data process : file){
     		if (process.getSequanceIndex() != -1) {
     			result[process.getSequanceIndex()-1] = process.getProcessName();
     		}
     	}
     	
     	System.out.println(Arrays.toString(result));
      }

}
