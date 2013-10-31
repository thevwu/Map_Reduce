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
		buffer.consumeWord();
	}
	
	public void invertedIndex(String fileName, int lineNum, String word){
		if(invertedIndex.containsValue(word)){
			invertedIndex.get(word).insert(new Tuple(filename, lineNum));
		}
		else{
			// create new ArrayList
			// insert tuple into arrayList
			// insert arrayList into invertedIndex
		}
	}
	
	public BoundedBuffer getBuffer(){
		return buffer;
	}
}