import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		
		System.out.println("Reading from file.....");
		
		//create the strings to save the path in them 
		String path1 = "C:/Users/hzdan/OneDrive/Desktop/Proj/Allocation.csv";/*first file (Allocation)*/
		String path2 = "C:/Users/hzdan/OneDrive/Desktop/Proj/Available.csv";/*Second file (Available)*/
		String path3 = "C:/Users/hzdan/OneDrive/Desktop/Proj/Request.csv";/*Third file (Request)*/
		
		
		//create an Array List for all the files 
		ArrayList <Data> file1 = new ArrayList<Data>(); 
		ArrayList <Integer> file2 = new ArrayList<>();
		ArrayList <Data> file3 = new ArrayList<Data>();
		
		// read from files.....
	    functions.Read(file1,file2,file3,path1,path2,path3);	
		// print the array list of the files  ....
		//functions.Print(file1,file2,file3);

		//checking for consistent
		functions.consistent(file1,file2,file3);
		
		boolean deadLock = functions.checkDeadLock(file1,file2,file3);
		
		if (deadLock == true) {
			System.out.println("the system is deadlocked");
			// print the deadlock sequence
			System.out.println("the set of the processes that are deadlocked");
			functions.printDSeq(file1);
		}
		else  {
			System.out.println("the system is not deadlocked");
			// print the safe sequence
			System.out.println("the series of process executions");
			functions.printSSeq(file1);
		}
		
		System.out.println("---------------------------------------------------------------");
	}
    
}
