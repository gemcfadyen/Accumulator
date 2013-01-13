package gemcfadyen.accumulator;

public interface Validator {
	String getValidationErrorMessage() ;
    boolean validate() throws InvalidValueException ;
}
