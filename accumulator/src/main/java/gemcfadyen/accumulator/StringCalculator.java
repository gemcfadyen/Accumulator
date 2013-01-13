package gemcfadyen.accumulator;

import static gemcfadyen.accumulator.Delimiter.CUSTOM_DELIMITER_INDICATOR;
import static gemcfadyen.accumulator.Delimiter.ESCAPE_CHARACTER;
import static gemcfadyen.accumulator.Delimiter.PIPE;
import static java.lang.Integer.valueOf;

import java.util.ArrayList;
import java.util.Iterator;

public class StringCalculator implements Accumulator {
	private static final int UPPER_THRESHOLD = 1000;
	private String delimiterExpression;
	private StringBuffer invalidInputErrorMessage;
	private Delimiter delimiter;

	public int add(String numbers) throws InvalidValueException {
		if (isBlank(numbers)) {
			return 0;
		}

		delimiterExpression = determineDelimiterExpressionFrom(numbers);
		String standardisedInput = delimiter.stripDelimiterFromStartOf(numbers);

		if (isValid(standardisedInput)) {
			return sum(getNumbersFrom(standardisedInput));
		} else {
			throw new InvalidValueException(invalidInputErrorMessage.toString());
		}
	}

	private String determineDelimiterExpressionFrom(String input) {
		if (input.startsWith(CUSTOM_DELIMITER_INDICATOR)) {
			delimiter = new CustomDelimiter();
		} else {
			delimiter = new BasicDelimiter();
		}

		return delimiter.getDelimiterUsedIn(input);
	}

	private void addInvalidInputErrorFor(String input) {
		if (invalidInputErrorMessage == null) {
			invalidInputErrorMessage = new StringBuffer("Invalid entries not allowed");
		}
		invalidInputErrorMessage.append("[" + input + "] ");
	}

	private boolean isValid(String input) throws InvalidValueException {
		if (isJustDelimitersIn(input) || !isNumberBetweenEachDelimiterIn(input) || isNegative(input)) {
			return false;
		}
		return true;
	}

	private boolean isJustDelimitersIn(String input) {
		for (String delimiter : delimiterExpression.split(ESCAPE_CHARACTER + PIPE)) {
			if (input.equals(delimiter)) {
				addInvalidInputErrorFor(input);
				return true;
			}
		}
		return false;
	}

	private boolean isNumberBetweenEachDelimiterIn(String input) {
		ArrayList<String> allInvalidCombinations = getInvalidCombinationsOf(delimiterExpression);
		for (Iterator<String> allCombinationsToTraverse = allInvalidCombinations.iterator(); allCombinationsToTraverse.hasNext();) {
			String invalidCombonation = allCombinationsToTraverse.next();

			if (input.contains(invalidCombonation)) {
				addInvalidInputErrorFor(input);
				return false;
			}
		}

		return true;
	}

	private String[] getNumbersFrom(String input) {
		return input.split(delimiterExpression);
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

	private boolean isNegative(String input) throws InvalidValueException {
		boolean isNegative = false;

		String[] checkForNegatives = input.split(delimiterExpression);
		for (String digit : checkForNegatives) {
			try {
				if (valueOf(digit) < 0) {
					isNegative = true;
					addNegativeNumberErrorFor(digit);
				}
			} catch (NumberFormatException e) {
				throw new InvalidValueException(digit + " is not a valid number");
			}
		}
		return isNegative;
	}

	private boolean isBlank(String input) {
		return input.length() == 0;
	}

	private ArrayList<String> getInvalidCombinationsOf(String delimiters) {
		String[] delimiterSplit = delimiterExpression.split(ESCAPE_CHARACTER + PIPE);

		ArrayList<String> invalidCombinations = new ArrayList<String>();
		for (int headDelimiterIndex = 0; headDelimiterIndex < delimiterSplit.length; headDelimiterIndex++) {
			String combination = delimiterSplit[headDelimiterIndex];
			invalidCombinations.add(combination+combination);
			boolean isOnlyLastDelimiter = true;
			for (int indexAfterHeadDelimiter = headDelimiterIndex + 1; indexAfterHeadDelimiter < delimiterSplit.length; indexAfterHeadDelimiter++) {
				combination = combination + delimiterSplit[indexAfterHeadDelimiter];
				isOnlyLastDelimiter = false;

			}
			
			if (!isOnlyLastDelimiter) {
				invalidCombinations.add(combination);
				invalidCombinations.add(reverse(combination));
			}

		}

		return invalidCombinations;
	}

	private String reverse(String input) {
		return new StringBuffer(input).reverse().toString();
	}

}
