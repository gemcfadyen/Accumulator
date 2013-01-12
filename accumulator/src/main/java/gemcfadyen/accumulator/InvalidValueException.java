package gemcfadyen.accumulator;

public class InvalidValueException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public InvalidValueException(String errorMessage) {
		super(errorMessage);
	}
}
