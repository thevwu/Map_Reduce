import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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
	    	reduces.add(reduceThread);
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
	    // loop through reduce threads
	    // wait for the reduce thread to complete (wait until boundedBuffer contents == 0)
	    // add reduce thread's inverted index to the
	    // MapReduce invertedIndex
	    
	}
}
