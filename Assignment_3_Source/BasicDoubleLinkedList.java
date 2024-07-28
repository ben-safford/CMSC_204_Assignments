import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;


/**
 * Linked list implementation
 * @param <T> Type of item in the list
 */
public class BasicDoubleLinkedList<T> implements Iterable<T> {

	protected Node head;
	protected Node tail;
	
	protected int size;
	
	/**
	 * Returns DoubleLinkedListIterator set to this list's head node
	 */
	@Override
	public ListIterator<T> iterator() {
		return new DoubleLinkedListIterator(this);
	}
	
	/**
	 * Adds item to front of list
	 * @param item
	 */
	public void addToFront(T item) {
		Node newNode = new Node(item);
		if(head != null) {
			newNode.setNext(head);
			head.setPrev(newNode);
		} else if (tail == null)
			tail = newNode;
		head = newNode;
		size++;
	}
	
	/**
	 * Adds item to end of list
	 * @param item
	 */
	public void addToEnd(T item) {
		Node newNode = new Node(item);
		if(tail != null) {
			newNode.setPrev(tail);
			tail.setNext(newNode);
		} else if (head == null)
			head = newNode;
		tail = newNode;
		size++;
	}

	/**
	 * Returns number of items in list
	 * @return number of items in list
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Iterates over each element of list and performs the action
	 */
	@Override
	public void forEach(Consumer<? super T> action) {
		Node next = head;
		while(next != null) {
			action.accept(next.getValue());
			next = next.getNext();
		}
	}
	
	/**
	 * 
	 * @return an arraylist containing all of this list's elements
	 */
	public ArrayList<T> toArrayList() {
		ArrayList<T> outList = new ArrayList<T>();
		this.forEach((item) -> outList.add(item));
		return outList;
	}
	
	/**
	 * Removes first element of list
	 * @return the element
	 */
	public T retrieveFirstElement() {
		Node element = head;
		if(element != null) {
			head = element.getNext();
			head.setPrev(null);
		}
		else
			throw new NoSuchElementException();
		return element.getValue();
	}
	
	/**
	 * Removes the last element of the list
	 * @return the element
	 */
	public T retrieveLastElement() {
		Node element = tail;
		if(element != null) {
			tail = element.getPrev();
			tail.setNext(null);
		}
		else
			throw new NoSuchElementException();
		return element.getValue();
	}
	
	/**
	 * 
	 * @return the first element of the list
	 */
	public T getFirst() {
		if(head == null)
			throw new NoSuchElementException();
		return head.getValue();
	}
	
	/**
	 * 
	 * @return the last element of the list
	 */
	public T getLast() {
		if(tail == null)
			throw new NoSuchElementException();
		return tail.getValue();
	}
	
	/**
	 * Removes the first instance of the element found in this list
	 * Uses the provided comparator to determine equality
	 * @param newElement the element to remove
	 * @param comp 
	 */
	public void remove(T newElement, Comparator<T> comp) {
		Node tgt = head;
		while(tgt != null) {
			if(comp.compare(tgt.getValue(), newElement) == 0) { //values are equal
				Node prev = tgt.getPrev();
				Node next = tgt.getNext();
				if(prev != null)
					prev.setNext(next);
				else
					head = next;
				if(next != null)
					next.setPrev(prev);
				else
					tail = prev;
				size--;
				return;
			}
			tgt = tgt.getNext();
		}
		
	}
	
	/**
	 * Data structure for individual node of list
	 */
	class Node {
		private T value;
		private Node prev;
		private Node next;
		
		public Node(T value){
			this.value = value;
		}

		public T getValue() {
			return value;
		}

		public void setValue(T value) {
			this.value = value;
		}

		public Node getPrev() {
			return prev;
		}

		public void setPrev(Node prev) {
			this.prev = prev;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
	}

	/**
	 * Iterator used to read list efficiently
	 */
	class DoubleLinkedListIterator implements ListIterator<T> {

		private Node cursorNode;
		private boolean hasReadForward;
		
		public Node getCursor() {
			return cursorNode;
		}
		
		public DoubleLinkedListIterator(BasicDoubleLinkedList<T> list) {
			hasReadForward = false;
			cursorNode = list.head;
		}
		
		@Override
		public boolean hasNext() {
			if(!hasReadForward)
				return cursorNode != null;
			return cursorNode.getNext() != null;
		}

		@Override
		public T next() {
			if(!hasNext())
				throw new NoSuchElementException();
			if(hasReadForward)
				cursorNode = cursorNode.getNext();
			else
				hasReadForward = true;
			return cursorNode.getValue();
		}

		@Override
		public boolean hasPrevious() {
			if(hasReadForward)
				return cursorNode != null;
			return cursorNode.getPrev() != null;
		}

		@Override
		public T previous() {
			if(!hasPrevious())
				throw new NoSuchElementException();
			if(!hasReadForward)
				cursorNode = cursorNode.getPrev();
			else
				hasReadForward = false;
			return cursorNode.getValue();
		}

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(T e) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void add(T e) {
			throw new UnsupportedOperationException();
		}

	}

}
