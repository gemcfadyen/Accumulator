package gemcfadyen.accumulator.validator;

import gemcfadyen.accumulator.InvalidValueException;

public abstract class Validator {
	protected static final String IS_NOT_A_VALID_NUMBER = " is not a valid number";
	protected static final String NEGATIVES_NOT_ALLOWED = "negatives not allowed ";
	protected static final String INVALID_ENTRIES_NOT_ALLOWED = "Invalid entries not allowed ";
	protected StringBuffer invalidInputErrorMessage;
	protected String input;
	protected String delimiterExpression;

	public Validator(String input, String delimiterExpression) {
		this.input = input;
		this.delimiterExpression = delimiterExpression;
	}

	public String getErrorMessage() {
		if (invalidInputErrorMessage != null) {
			return invalidInputErrorMessage.toString();
		}
		return null;
	}

	protected void addErrorDetailsFor(String errorMessage, String input) {
		if (invalidInputErrorMessage == null) {
			invalidInputErrorMessage = new StringBuffer(errorMessage);
		}
		invalidInputErrorMessage.append("[" + input + "] ");
	}

	public abstract boolean isValid() throws InvalidValueException;
}
