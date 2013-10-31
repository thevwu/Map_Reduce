import java.io.*;
import java.util.ArrayList;

public class Map extends Thread {
	private BoundedBuffer buffer;
	private BufferedReader reader;
	private String word;
	private String fileName;
	private int lineNumber;
	private int reduceThreadRecieverID;
	private ArrayList<Reduce> reduces;
	
 
	public Map(String fileName, ArrayList<Reduce> reduces) throws FileNotFoundException {
		this.reduces= reduces;
		this.fileName = fileName;
		this.lineNumber = 0;
		reader = new BufferedReader(new FileReader(fileName));
		
		
		
	}
 
	public void run() {
		try {
			while ((word = reader.readLine()) != null) {
				//hashing function to determine which reduce thread to send word to
				reduceThreadRecieverID = Character.getNumericValue(word.toLowerCase().charAt(0) % reduces.size()+1);
				reduces.get(reduceThreadRecieverID).invertedIndex(fileName, lineNumber, word);
				lineNumber++;

				
			}
 
		} catch (IOException e) {
			System.out.println("Error with Producer Thread.");
		}
 
	}
}