/**
 * exception: passwords do not match
 */
public class UnmatchedException extends Exception {
	
	public UnmatchedException() {
		super("The passwords do not match");
	}
}
