import java.util.concurrent.Semaphore;


/*
 * This is a bounded buffer modeled
 * after an example in chapter 6
 * 
 */

public class BoundedBuffer<E> {
	private static final int BUFFER_SIZE = 10;
	private E[] buffer;
	private int in, out;
	private Semaphore mutex;
	private Semaphore empty;
	private Semaphore full;
	
	@SuppressWarnings("unchecked")
	public BoundedBuffer(){
		in = 0;
		out = 0;
		mutex = new Semaphore(1);
		empty = new Semaphore(BUFFER_SIZE);
		full = new Semaphore(0);
		
		buffer = (E[]) new Object[BUFFER_SIZE];
	}
	
	public void insertWord (E item) throws InterruptedException{
		empty.acquire();
		mutex.acquire();
		
		//add an item to the buffer
		buffer[in] = item;
		in = (in + 1) % BUFFER_SIZE;
		
		mutex.release();
		full.release();
	}
	
	public E consumeWord() throws InterruptedException{
		E item;
		
		full.acquire();
		mutex.acquire();
		
		//remove an item from the buffer
		item = buffer[out];
		out = (out + 1) % BUFFER_SIZE;
		
		mutex.release();
		empty.release();
		
		return item;
	}
	
	
	

}
