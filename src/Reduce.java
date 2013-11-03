import java.util.ArrayList;
import java.util.HashMap;

public class Reduce extends Thread {
	private BoundedBuffer buffer;
	private int num;
	private HashMap<String, ArrayList<Tuple>> invertedIndex;

 
	public Reduce(int num) {
		buffer = new BoundedBuffer<String>();
		this.num = num;
		invertedIndex = new HashMap<String, ArrayList<Tuple>>();  
	}
 
	public void run() {
		try {
			buffer.consumeWord();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void invertedIndex(String fileName, int lineNum, String word){
		if(invertedIndex.containsValue(word)){
			//if the word is already entered, add a new tuple to that word's arraylist
			invertedIndex.get(word).add(new Tuple(fileName, lineNum));

		}
		else{
			// create new ArrayList
			// insert tuple into arrayList
			// insert arrayList into invertedIndex
			ArrayList<Tuple> listOfWords = new ArrayList<Tuple>();
			Tuple record = new Tuple(fileName, lineNum);
			listOfWords.add(record);
			invertedIndex.put(word, listOfWords);

		}
	}
	
	public BoundedBuffer getBuffer(){
		return buffer;
	}
}