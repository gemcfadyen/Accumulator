package gemcfadyen.accumulator.validator;

import static java.lang.Integer.valueOf;
import gemcfadyen.accumulator.InvalidValueException;

public class NegativeNumberRule extends Validator {
	public NegativeNumberRule(String input, String delimiterExpressions) {
		super(input, delimiterExpressions);
	}

	@Override
	public boolean isValid() throws InvalidValueException {
		return isNegative();
	}

	private boolean isNegative() throws InvalidValueException {
		boolean isAllPositive = true;
		String[] checkForNegatives = input.split(delimiterExpression);
		for (String digit : checkForNegatives) {
			try {
				if (valueOf(digit) < 0) {
					isAllPositive = false;
					addErrorDetailsFor(NEGATIVES_NOT_ALLOWED, digit);
				}
			} catch (NumberFormatException e) {
				throw new InvalidValueException(digit + IS_NOT_A_VALID_NUMBER);
			}
		}
		return isAllPositive;
	}

}
