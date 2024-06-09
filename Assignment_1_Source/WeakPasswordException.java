/**
 * exception: password too short
 */
public class WeakPasswordException extends Exception {
	public WeakPasswordException() {
		super("The password is weak. Strong passwords are at least 10 characters long.");
	}
}
