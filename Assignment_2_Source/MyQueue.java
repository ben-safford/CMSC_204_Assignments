import java.util.ArrayList;


/**
 * Straightforward implementation of QueueInterface.
 * @param <T>
 */
public class MyQueue<T> implements QueueInterface<T> {
	private ArrayList<T> arr;
	private int maxSize;
	
	public MyQueue(int queueSize) {
		arr = new ArrayList<T>();
		maxSize = queueSize;
	}

	@Override
	public boolean isEmpty() {
		return arr.size() <= 0;
	}

	@Override
	public boolean isFull() {
		return arr.size() >= maxSize;
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(isEmpty())
			throw new QueueUnderflowException();
		T item = arr.get(0);
		arr.remove(0);
		return item;
	}

	@Override
	public int size() {
		return arr.size();
	}

	@Override
	public boolean enqueue(T e) throws QueueOverflowException {
		if(isFull())
			throw new QueueOverflowException();
		arr.add(e);
		return true;
	}

	public String toString() {
		return toString("");
	}
	
	@Override
	public String toString(String delimiter) {
		String outString = "";
		for(int i = 0; i < arr.size(); i++) {
			outString += arr.get(i);
			if(i < arr.size() - 1)
				outString += delimiter;
		}
		return outString;
	}

	@Override
	public void fill(ArrayList<T> list) throws QueueOverflowException {
		for(int i = 0; i < list.size(); i++) {
			enqueue(list.get(i));
		}
	}

}
