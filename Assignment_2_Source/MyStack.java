import java.util.ArrayList;


/**
 * Slightly odd implementation of StackInterface - uses an array of objects instead of an arraylist
 * @param <T>
 */
public class MyStack<T> implements StackInterface<T> {

	private T[] arr;
	private int size;
	
	//this shouldn't be an issue as we're only using the Object[] inside the StackInterface
	//we could use an arraylist here but honestly arraylists do most of the stack stuff already
	//I feel like this is more fun to learn about
	@SuppressWarnings("unchecked")
	public MyStack(int stackSize) {
		arr = (T[])new Object[stackSize];
		size = 0;
	}
	
	@Override
	public boolean isEmpty() {
		return size <= 0;
	}

	@Override
	public boolean isFull() {
		return size >= arr.length;
	}

	@Override
	public T pop() throws StackUnderflowException {
		if(size <= 0)
			throw new StackUnderflowException();
		T item = arr[size - 1];
		arr[size - 1] = null;
		size--;
		return item;
	}

	@Override
	public T top() throws StackUnderflowException {
		if(size <= 0)
			throw new StackUnderflowException();
		return arr[size - 1];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean push(T e) throws StackOverflowException {
		if(size >= arr.length)
			throw new StackOverflowException();
		arr[size] = e;
		size++;
		return true;
	}

	public String toString() {
		return toString("");
	}
	
	@Override
	public String toString(String delimiter) {
		String outString = "";
		for(int i = 0; i < size; i++) {
			outString += arr[i];
			if(i < size - 1)
				outString += delimiter;
		}
		return outString;
	}

	@Override
	public void fill(ArrayList<T> list) throws StackOverflowException {
		for(int i = 0; i < list.size(); i++) {
			push(list.get(i));
		}
		
	}

}
