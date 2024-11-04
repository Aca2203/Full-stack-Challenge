package gui;

@SuppressWarnings("serial")
public class HeightException extends Exception {
	public HeightException() {
		super("The value of height is not valid!");
	}
}
