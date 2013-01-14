package gemcfadyen.accumulator;

import static gemcfadyen.accumulator.delimiter.Delimiter.ESCAPE_CHARACTER;
import static gemcfadyen.accumulator.delimiter.Delimiter.PIPE;
import static java.lang.Integer.valueOf;

import java.util.ArrayList;
import java.util.Iterator;

public class InputValidator implements Validator {
	private static final String IS_NOT_A_VALID_NUMBER = " is not a valid number";
	private static final String INVALID_ENTRIES_NOT_ALLOWED = "Invalid entries not allowed ";
	private static final String NEGATIVES_NOT_ALLOWED = "negatives not allowed ";
	private String input;
	private String delimiterExpression;
	private StringBuffer invalidInputErrorMessage;

	public InputValidator(String input, String delimiterExpression) {
		this.input = input;
		this.delimiterExpression = delimiterExpression;
	}

	public String getValidationErrorMessage() {
		if (invalidInputErrorMessage != null) {
			return invalidInputErrorMessage.toString();
		} else {
			return null;
		}
	}

	public boolean validate() throws InvalidValueException {
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
			String invalidCombination = allCombinationsToTraverse.next();

			if (input.contains(invalidCombination)) {
				addInvalidInputErrorFor(input);
				return false;
			}
		}

		return true;
	}

	private ArrayList<String> getInvalidCombinationsOf(String delimiters) {
		String[] delimiterSplit = delimiterExpression.split(ESCAPE_CHARACTER + PIPE);

		ArrayList<String> invalidCombinations = new ArrayList<String>();
		for (int headDelimiterIndex = 0; headDelimiterIndex < delimiterSplit.length; headDelimiterIndex++) {
			String combination = delimiterSplit[headDelimiterIndex];
			invalidCombinations.add(combination + combination);
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
				throw new InvalidValueException(digit + IS_NOT_A_VALID_NUMBER);
			}
		}
		return isNegative;
	}

	private void addNegativeNumberErrorFor(String input) {
		if (invalidInputErrorMessage == null) {
			invalidInputErrorMessage = new StringBuffer(NEGATIVES_NOT_ALLOWED);
		}
		invalidInputErrorMessage.append("[" + input + "] ");
	}

	private void addInvalidInputErrorFor(String input) {
		if (invalidInputErrorMessage == null) {
			invalidInputErrorMessage = new StringBuffer(INVALID_ENTRIES_NOT_ALLOWED);
		}
		invalidInputErrorMessage.append("[" + input + "] ");
	}

}
