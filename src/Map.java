import java.io.*;
import java.util.ArrayList;

public class Map extends Thread {
	private BoundedBuffer buffer;
	private BufferedReader reader;
	private String word;
	ArrayList<Reduce> reduces;
 
	public Map(String fileName, ArrayList<Reduce> reduces) throws FileNotFoundException {
		this.reduces= reduces;
		reader = new BufferedReader(new FileReader(fileName));
		
		
		
	}
 
	public void run() {
		try {
			while ((word = reader.readLine()) != null) {
				//hash function here?

				
			}
 
		} catch (IOException e) {
			System.out.println("Error with Producer Thread.");
		}
 
	}
}