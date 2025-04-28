import java.util.ArrayList;

public class Data {
	
	// the fields for every process
	private String processName;
	private ArrayList <Integer> resources  = new ArrayList<> ();
	private boolean finish;
	private int sequanceIndex = -1;
	
	// the constructor
	public Data(String processName ) {
		super();
		this.processName = processName;
	}

	// the setters and getters for every one of the fields 
	public String getProcessName() {
		return processName;
	}


	public void setProcessName(String processName) {
		this.processName = processName;
	}

	
	public ArrayList<Integer> getResources() {
		return resources;
	}



	public void setResources(ArrayList<Integer> resources) {
		this.resources = resources;
	}
   
	public boolean isFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}
	

	public int getSequanceIndex() {
		return sequanceIndex;
	}

	public void setSequanceIndex(int sequanceIndex) {
		this.sequanceIndex = sequanceIndex;
	}

	public void res (ArrayList<Integer> resource ,int from , int to ) {
		while (from < to ) {
		  this.resources.add(resource.get(from));
		   from++;
		}	
	}

	// for the file 
	public void print(int index) {
		System.out.println( "Data [processName=" + processName + ", resources=" + resources + "]");
	}

	@Override
	public String toString() {
		return "Data [processName=" + processName + ", resources=" + resources + ", finish=" + finish + "]";
	}

	public int compareTo(Data o) {
		
        boolean answer = this.processName.equals(o.processName);
     
        if (answer) {
            return 1;
        } 
        else 
        	return 0;
	}
	
   public int compareToRes(Data o) {
		
        boolean answer;
        if (this.resources.size() == o.resources.size()) {
        	answer = true;	
        }
        else 
        	answer = false;
     
        if (answer) {
            return 1;
        } 
        else 
        	return 0;
	}
}
