package gemcfadyen.accumulator.validator;

import gemcfadyen.accumulator.InvalidValueException;

public interface ValidationRules {
		String getErrorDescription();
		boolean isValid() throws InvalidValueException;
}
