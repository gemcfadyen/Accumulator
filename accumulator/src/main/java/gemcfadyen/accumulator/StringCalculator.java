package gemcfadyen.accumulator;

import static gemcfadyen.accumulator.Delimiter.CUSTOM_DELIMITER_INDICATOR;
import static java.lang.Integer.valueOf;

public class StringCalculator implements Accumulator {
	private static final int UPPER_THRESHOLD = 1000;
	private String delimiterExpression;
	private Delimiter delimiter;

	public int add(String numbers) throws InvalidValueException {
		if (isBlank(numbers)) {
			return 0;
		}

		delimiterExpression = determineDelimiterExpressionFrom(numbers);
		String standardisedInput = delimiter.stripDelimiterFromStartOf(numbers);
		
		Validator validator = getValidatorFor(standardisedInput);
		
		if(validator.validate()){
			return sum(getNumbersFrom(standardisedInput));
		} else {
			throw new InvalidValueException(validator.getValidationErrorMessage());
		}
	}
	
	private Validator getValidatorFor(String input){
		return new InputValidator(input, delimiterExpression);
	}

	private String determineDelimiterExpressionFrom(String input) {
		if (input.startsWith(CUSTOM_DELIMITER_INDICATOR)) {
			delimiter = new CustomDelimiter();
		} else {
			delimiter = new BasicDelimiter();
		}

		return delimiter.getDelimiterUsedIn(input);
	}

	private String[] getNumbersFrom(String input) {
		return input.split(delimiterExpression);
	}

	private int sum(String[] numbers) throws InvalidValueException {
		int result = 0;

		for (String number : numbers) {
			try {
				int digit = valueOf(number);
				if (digit < UPPER_THRESHOLD) {
					result = result + valueOf(digit);
				}
			} catch (NumberFormatException e) {

				throw new InvalidValueException("Invalid input");
			}
		}
		return result;
	}

	private boolean isBlank(String input) {
		return input.length() == 0;
	}

}
