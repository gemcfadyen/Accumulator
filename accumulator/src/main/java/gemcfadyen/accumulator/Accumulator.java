package gemcfadyen.accumulator;

import static java.lang.Integer.valueOf;

public class Accumulator {

	private static final int UPPER_THRESHOLD = 1000;
	private static final String ESCAPE_CHARACTER = "\\";
	private static final String CUSTOM_DELIMITER_INDICATOR = "//";
	private static final String COMMA_DELIMITER = ",";
	private static final String NEWLINE_DELIMITER = "\n";
	private String delimiter;
	private StringBuffer invalidInputErrorMessage;

	public int add(String numbers) throws InvalidValueException {
		if (isBlank(numbers)) {
			return 0;
		}

		delimiter = determineDelimiterFrom(numbers);
		String standardisedInput = stripInputDownToNumbersSeperatedByDelimiter(numbers);

		if (isValid(standardisedInput)) {
			return sum(getNumbersFrom(standardisedInput));
		} else {
			throw new InvalidValueException(invalidInputErrorMessage.toString());
		}

	}

	private String stripInputDownToNumbersSeperatedByDelimiter(String numbers) {
		if (numbers.startsWith(CUSTOM_DELIMITER_INDICATOR)) {
			return numbers.substring(indexOfFirstNewLineDelmiterIn(numbers)
					+ NEWLINE_DELIMITER.length());
		} else {
			return replaceNewLineDelimitersWithCommasIn(numbers);
		}
	}

	private String determineDelimiterFrom(String input) {
		if (input.startsWith(CUSTOM_DELIMITER_INDICATOR)) {
			return getDelimiterDeclaredAtStartOf(input);
		} else {
			return COMMA_DELIMITER;
		}
	}

	private String getDelimiterDeclaredAtStartOf(String input) {
		return input.substring(indexOfEndOfCustomDelimiterIndicatorIn(input),
				indexOfFirstNewLineDelmiterIn(input));
	}

	private int indexOfFirstNewLineDelmiterIn(String input) {
		return input.indexOf(NEWLINE_DELIMITER);
	}

	private int indexOfEndOfCustomDelimiterIndicatorIn(String input) {
		return input.indexOf(CUSTOM_DELIMITER_INDICATOR)
				+ CUSTOM_DELIMITER_INDICATOR.length();
	}

	private void addInvalidInputErrorFor(String input) {
		if (invalidInputErrorMessage == null) {
			invalidInputErrorMessage = new StringBuffer(
					"Invalid entries not allowed");
		}
		invalidInputErrorMessage.append("[" + input + "] ");
	}

	private boolean isValid(String input) {
		boolean isValid = true;

		if (!isNumberBetweenEachDelimiterIn(input) || isNegative(input)) {
			isValid = false;
		}

		return isValid;
	}

	private boolean isNumberBetweenEachDelimiterIn(String input) {
		if (input.equals(delimiter) || input.contains(delimiter + delimiter)) {
			addInvalidInputErrorFor(input);
			return false;
		}
		return true;
	}

	private String[] getNumbersFrom(String input) {
		return input.split(ESCAPE_CHARACTER + delimiter);
	}

	private String replaceNewLineDelimitersWithCommasIn(String input) {
		return input.replace(NEWLINE_DELIMITER, COMMA_DELIMITER);
	}

	private void addNegativeNumberErrorFor(String input) {
		if (invalidInputErrorMessage == null) {
			invalidInputErrorMessage = new StringBuffer(
					"negatives not allowed ");
		}
		invalidInputErrorMessage.append("[" + input + "] ");
	}

	private int sum(String[] numbers) throws InvalidValueException {
		int result = 0;

		for (String number : numbers) {
			int digit = valueOf(number);
			if (digit < UPPER_THRESHOLD) {
				result = result + valueOf(digit);
			}
		}

		return result;
	}

	private boolean isNegative(String input) {
		boolean isNegative = false;
		String[] checkForNegatives = input.split(ESCAPE_CHARACTER + delimiter);
		for (String digit : checkForNegatives) {
			if (valueOf(digit) < 0) {
				isNegative = true;
				addNegativeNumberErrorFor(digit);
			}
		}
		return isNegative;

	}

	private boolean isBlank(String input) {
		return input.length() == 0;
	}

}
