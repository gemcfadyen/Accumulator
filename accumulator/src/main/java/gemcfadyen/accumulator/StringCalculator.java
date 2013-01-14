package gemcfadyen.accumulator;

import static java.lang.Integer.valueOf;
import gemcfadyen.accumulator.delimiter.Delimiter;
import gemcfadyen.accumulator.delimiter.DelimiterFactory;
import gemcfadyen.accumulator.validator.InputStringRules;
import gemcfadyen.accumulator.validator.ValidationRules;

public class StringCalculator implements Accumulator {
	private static final int UPPER_THRESHOLD = 1000;
	private String delimiterExpression;
	private Delimiter delimiter;
	private ValidationRules validationRules;
	 
	public int add(String numbers) throws InvalidValueException {
		if (isBlank(numbers)) {
			return 0;
		}
		
		delimiterExpression = determineDelimiterExpressionFrom(numbers);
		String standardisedInput = delimiter.stripDelimiterFromStartOf(numbers);

		validationRules = getValidationRulesFor(standardisedInput);
		if (validationRules.isValid()){
			return sum(getNumbersFrom(standardisedInput));
		} else {
			throw new InvalidValueException(validationRules.getErrorDescription());
		}
	}

	private ValidationRules getValidationRulesFor(String input) {
		return new InputStringRules(input, delimiterExpression);
	}

	private String determineDelimiterExpressionFrom(String input) {
		DelimiterFactory delimiterFactory = new DelimiterFactory();
		delimiter = delimiterFactory.createDelimiterFrom(input);
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
					result = result + digit;
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