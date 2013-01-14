package gemcfadyen.accumulator.validator;

import static gemcfadyen.accumulator.delimiter.Delimiter.ESCAPE_CHARACTER;
import static gemcfadyen.accumulator.delimiter.Delimiter.PIPE;
import gemcfadyen.accumulator.InvalidValueException;

public class NoNumberRule extends Validator{

	public NoNumberRule(String input, String delimiterExpressions) {
		super(input,delimiterExpressions);
	}
	
	@Override
	public boolean isValid() throws InvalidValueException {
		return isJustDelimiters();
	}
	
	private boolean isJustDelimiters() {
		boolean isNotAllDelimiters=true;
		for (String delimiter : delimiterExpression.split(ESCAPE_CHARACTER + PIPE)) {
			if (input.equals(delimiter)) {
				addErrorDetailsFor(INVALID_ENTRIES_NOT_ALLOWED, input);
				isNotAllDelimiters = false;
			}
		}
		return isNotAllDelimiters;
	}
	
}

