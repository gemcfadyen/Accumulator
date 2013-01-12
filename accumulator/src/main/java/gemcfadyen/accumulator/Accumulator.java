package gemcfadyen.accumulator;

import static java.lang.Character.isLetter;
import static java.lang.Character.valueOf;
import static java.lang.Integer.valueOf;

public class Accumulator {

	private static final char PIPE = '|';
	private static final int UPPER_THRESHOLD = 1000;
	private static final String ESCAPE_CHARACTER = "\\";
	private static final String CUSTOM_DELIMITER_INDICATOR = "//";
	private static final String COMMA_DELIMITER = ",";
	private static final String NEWLINE_DELIMITER = "\n";
	private String delimiterExpression;
	private StringBuffer invalidInputErrorMessage;

	public int add(String numbers) throws InvalidValueException {
		if (isBlank(numbers)) {
			return 0;
		}

		delimiterExpression = determineDelimiterExpressionFrom(numbers);

		String standardisedInput = stripInputDownToNumbersSeperatedByDelimiters(numbers);

		if (isValid(standardisedInput)) {
			return sum(getNumbersFrom(standardisedInput));
		} else {
			throw new InvalidValueException(invalidInputErrorMessage.toString());
		}

	}

	private String stripInputDownToNumbersSeperatedByDelimiters(String input) {
		if (input.startsWith(CUSTOM_DELIMITER_INDICATOR)) {
			return input.substring(indexOfFirstNewLineDelmiterIn(input)
					+ NEWLINE_DELIMITER.length());
		} else {
			return replaceNewLineDelimitersWithCommasIn(input);
		}
	}

	private String determineDelimiterExpressionFrom(String input) {
		if (input.startsWith(CUSTOM_DELIMITER_INDICATOR)) {
			return escapeThe(delimitersDeclaredAtStartOf(input));
		} else {
			return COMMA_DELIMITER;
		}
	}

	private String escapeThe(String delimiters) {
		char[] charactersInDelimiter = delimiters.toCharArray();
		StringBuffer escapedDelimiter = new StringBuffer();
		boolean isOnlyPipes = delimiterContainsOnlyPipes(delimiters);

		for (char character : charactersInDelimiter) {
			if (isOnlyPipes) {
				escapedDelimiter.append(ESCAPE_CHARACTER);
			} else if (!isLetter(character) && character != valueOf(PIPE)) {
				escapedDelimiter.append(ESCAPE_CHARACTER);
			}
			escapedDelimiter.append(character);
		}
		return escapedDelimiter.toString();
	}

	private boolean delimiterContainsOnlyPipes(String delimiters) {
		boolean isAllPipes = true;
		for (char delimiter : delimiters.toCharArray()) {
			if (delimiter != PIPE) {
				isAllPipes = false;
			}
		}
		return isAllPipes;

	}

	private String delimitersDeclaredAtStartOf(String input) {
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
			invalidInputErrorMessage = new StringBuffer("Invalid entries not allowed");
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
		if (input.equals(delimiterExpression) || input.contains(delimiterExpression + delimiterExpression)) {
			addInvalidInputErrorFor(input);
			return false;
		}
		return true;
	}

	private String[] getNumbersFrom(String input) {
		return input.split(delimiterExpression);
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

		String[] checkForNegatives = input.split(delimiterExpression);
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
