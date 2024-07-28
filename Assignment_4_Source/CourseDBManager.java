import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseDBManager implements CourseDBManagerInterface {

	private CourseDBStructure structure;
	
	public CourseDBManager() {
		structure = new CourseDBStructure("Manager", 19);
	}
	
	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		structure.add(new CourseDBElement(id, crn, credits, roomNum, instructor));
	}

	@Override
	public CourseDBElement get(int crn) {
		try {
			return structure.get(crn);
		} catch (IOException exception){
			System.err.println("Failed to find item for CRN " + crn);
			return null;
		}
	}

	@Override
	public void readFile(File input) throws FileNotFoundException {
		Scanner fileReader = new Scanner(input);
		while(fileReader.hasNextLine()) {
			String line = fileReader.nextLine();
			String[] elements = line.split(" ");
			String id = elements[0];
			int crn = Integer.valueOf(elements[1]);
			int credits = Integer.valueOf(elements[2]);
			String instructor = elements[3];
			String roomNum = elements[4];
			add(id, crn, credits, instructor, roomNum);
		}
		fileReader.close();
	}

	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> outList = structure.showAll();
		return outList;
	}

}
