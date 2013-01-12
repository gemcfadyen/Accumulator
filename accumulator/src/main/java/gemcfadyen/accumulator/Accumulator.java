package gemcfadyen.accumulator;

import static java.lang.Integer.valueOf;

public class Accumulator {

	private static final String ESCAPE_CHARACTER = "\\";
	private static final String CUSTOM_DELIMITER_INDICATOR = "//";
	private static final String COMMA_DELIMITER = ",";
	private static final String NEWLINE_DELIMITER = "\n";
	private String delimiter;

	public int add(String numbers) throws InvalidValueException {
		if (isBlank(numbers)) {
			return 0;
		}

		delimiter = determineDelimiterFrom(numbers);
		String standardisedInput = stripInputDownToNumbersSeperatedByDelimiter(numbers);

		if (isValid(standardisedInput)) {
			return sum(getNumbersFrom(standardisedInput));
		} else {
			throw new InvalidValueException("The input " + standardisedInput
					+ " is not valid");
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

	private boolean isValid(String input) {
		if (input.equals(delimiter) || input.contains(delimiter + delimiter)) {
			return false;
		} else {
			return true;
		}
	}

	private String[] getNumbersFrom(String input) {
		return input.split(ESCAPE_CHARACTER + delimiter);
	}

	private String replaceNewLineDelimitersWithCommasIn(String input) {
		return input.replace(NEWLINE_DELIMITER, COMMA_DELIMITER);
	}

	private int sum(String[] numbers) {
		int result = 0;
		for (String number : numbers) {
			result = result + valueOf(number);
		}
		return result;
	}

	private boolean isBlank(String input) {
		return input.length() == 0;
	}

}
