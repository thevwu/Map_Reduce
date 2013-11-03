import java.awt.List;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MapReduce {
	static ArrayList<Map> maps;
	static ArrayList<Reduce> reduces;
	
	
	public static void main(String[] args) throws FileNotFoundException {
		int m;
		int n;
		String fileName;
		
		
	
		Scanner in = new Scanner(System.in);

	    System.out.println("How many Map threads?  ");
	    m = in.nextInt();
	    System.out.println("How many Reduce threads?  ");
	    n = in.nextInt();
	    
	    in.close();

	    for(int i = 0; i < n; i++){
	    	Reduce reduceThread = new Reduce(n);
	    	reduceThread.start();
	    	reduces.add(reduceThread); //this is giving us a null pointer exception -- I think we need to synchronize the list
	    	// we add the threads too, but I'm having difficulty doing this.
	    	try {
				reduceThread.join();
				
			} catch (InterruptedException e) {
				System.out.println("Error ending Reduce thread " + n);
				e.printStackTrace();
			}
	    }
	    
	    
	    for(int i = 0; i < m; i++){
	    	fileName = "foo" + m + ".txt";
	    	Map mapThread = new Map(fileName, reduces);
	    	
	    	mapThread.start();
	    	try {
				mapThread.join();
			} catch (InterruptedException e) {
				System.out.println("Error ending Map thread " + m);
				e.printStackTrace();
			}
	    }
	    
	    // if maps are all done
	    // loop through reduce threads (we have an ArrayList of these)
	    // wait for the reduce thread to complete (wait until boundedBuffer contents == 0)
	    // add reduce thread's inverted index to the
	    // MapReduce invertedIndex
	    
	}
}
