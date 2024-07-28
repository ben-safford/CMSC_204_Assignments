import java.util.Comparator;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {

	private Comparator<T> comparator;
	
	public SortedDoubleLinkedList(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	public void addToFront(T item) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void addToEnd(T item) {
		throw new UnsupportedOperationException();
	}
	
	public void add(T item) {
		if(size == 0) {
			super.addToFront(item);
			return;
		}
		//if smaller than front, add to front
		if(comparator.compare(item, head.getValue()) <= 0) {
			super.addToFront(item);
		}
		//if larger than back, add to back
		else if (comparator.compare(item, tail.getValue()) >= 0) {
			super.addToEnd(item);
		} 
		else { //binary search!
			DoubleLinkedListIterator iterator = (DoubleLinkedListIterator)iterator();
			int jumpSize = size / 2;
			boolean traverseForward = true;
			
			T testValue = getFirst();
			int compareResult = 0;
			
			while(jumpSize > 0) {
				for(int i = 0; i < jumpSize; i++) {
					if(traverseForward)
						testValue = iterator.next();
					else
						testValue = iterator.previous();
				}
				
				compareResult = comparator.compare(item, testValue);
				
				if(compareResult == 0) { //equal: early out, place new node behind its twin
					Node insertPoint = iterator.getCursor();
					addNodeBehind(item, insertPoint);
					return;
				} else
					traverseForward = compareResult > 0;
				jumpSize /= 2;
			}
			
			Node insertPoint = iterator.getCursor();
			if(compareResult > 0) { //insert behind
				addNodeBehind(item, insertPoint);
			} else { //insert in front
				addNodeInFront(item, insertPoint);
			}
		}
	}

	private void addNodeBehind(T item, Node insertPoint) {
		Node nextPoint = insertPoint.getNext();
		Node toInsert = new Node(item);
		toInsert.setPrev(insertPoint);
		insertPoint.setNext(toInsert);
		if(nextPoint != null) {
			nextPoint.setPrev(insertPoint.getNext());
			toInsert.setNext(nextPoint);
		} else
			tail = toInsert;
	}
	
	private void addNodeInFront(T item, Node insertPoint) {
		Node prevPoint = insertPoint.getPrev();
		Node toInsert = new Node(item);
		toInsert.setNext(insertPoint);
		insertPoint.setPrev(toInsert);
		if(prevPoint != null) {
			prevPoint.setNext(toInsert);
			toInsert.setPrev(prevPoint);
		} else
			head = toInsert;
	}
	
}
