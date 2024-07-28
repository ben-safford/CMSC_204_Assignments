
public class CourseDBElement {

	private String id;
	private int crn;
	private int credits;
	private String roomNum;
	private String instructorName;
	
	private CourseDBElement next;

	public CourseDBElement(String id, int crn, int credits, String roomNum, String instructorName) {
		setID(id);
		setCRN(crn);
		setCredits(credits);
		setRoomNum(roomNum);
		setInstructorName(instructorName);
	}

	public CourseDBElement() {
		// TODO Auto-generated constructor stub
	}

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public int getCRN() {
		return crn;
	}

	public void setCRN(int crn) {
		this.crn = crn;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}
	
	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	public CourseDBElement getNext() {
		return next;
	}

	public void setNext(CourseDBElement next) {
		this.next = next;
	}
	
	public String toString() {
		return "\nCourse:" + id + " CRN:" + crn + " Credits:" + credits + " Instructor:" + instructorName + "Room:" + roomNum;
	}
}
