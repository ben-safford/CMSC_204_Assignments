import java.io.IOException;
import java.util.ArrayList;

public class CourseDBStructure implements CourseDBStructureInterface {

	public CourseDBElement[] hashTable;
	private String structureID;
	
	public CourseDBStructure(int elementCount) {
		hashTable = new CourseDBElement[elementCount - 1];
	}

	public CourseDBStructure(String id, int elementCount) {
		structureID = id;
		hashTable = new CourseDBElement[elementCount];
	}

	@Override
	public void add(CourseDBElement element) {
		// TODO Auto-generated method stub
		int chosenBucket = extracted(element.getCRN());
		boolean placed = false;
		CourseDBElement testElement = hashTable[chosenBucket];
		CourseDBElement lastElement = null;
		while(!placed) {
			//
			if(testElement == null) {
				hashTable[chosenBucket] = element;
				placed = true;
			} else {
				if(testElement.getCRN() < element.getCRN()) {
					CourseDBElement nextElement = testElement.getNext();
					if(nextElement == null || nextElement.getCRN() > element.getCRN()){
						element.setNext(nextElement);
						testElement.setNext(element);
						placed = true;
					}
				}
				else if (testElement.getCRN() == element.getCRN()) { //overwrite this spot in our hashtable
					if(lastElement == null) {
						hashTable[chosenBucket] = element;
					} else {
						lastElement.setNext(element);
					}
					element.setNext(testElement.getNext());
					placed = true;
				} else if (lastElement == null) { //this CRN is less than all CRNs in our bucket
					hashTable[chosenBucket] = element;
					element.setNext(testElement);
					placed = true;
				}
				
				testElement = testElement.getNext();
				lastElement = testElement;
			}
		}
	}

	private int extracted(int crn) {
		String crnString = String.valueOf(crn);
		int hashcode = crnString.hashCode();
		System.out.println(hashcode);
		int chosenBucket = hashcode % hashTable.length;
		return chosenBucket;
	}

	@Override
	public CourseDBElement get(int crn) throws IOException {
		int chosenBucket = extracted(crn);
		CourseDBElement toTest = hashTable[chosenBucket];
		while(toTest != null) {
			if(toTest.getCRN() == crn)
				return toTest;
			else
				toTest = toTest.getNext();
		}
		throw new IOException("No element found with CRN " + crn);
	}

	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> outList = new ArrayList<String>();
		for(int i = 0; i < hashTable.length; i++) {
			CourseDBElement toTest = hashTable[i];
			while(toTest != null) {
				outList.add(toTest.toString());
				toTest = toTest.getNext();
			}
		}
		return outList;
	}

	@Override
	public int getTableSize() {
		return hashTable.length;
	}

}
