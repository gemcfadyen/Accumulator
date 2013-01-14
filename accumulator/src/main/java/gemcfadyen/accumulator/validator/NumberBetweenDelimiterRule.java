package gemcfadyen.accumulator.validator;

import static gemcfadyen.accumulator.delimiter.Delimiter.ESCAPE_CHARACTER;
import static gemcfadyen.accumulator.delimiter.Delimiter.PIPE;
import gemcfadyen.accumulator.InvalidValueException;

import java.util.ArrayList;
import java.util.Iterator;

public class NumberBetweenDelimiterRule extends Validator {
	
	public NumberBetweenDelimiterRule(String input, String delimiterExpressions) {
		super(input,delimiterExpressions);
	}

	public boolean isValid() throws InvalidValueException {
		return isNumberBetweenEachDelimiter();
	}
	
	private boolean isNumberBetweenEachDelimiter() {
		ArrayList<String> allInvalidCombinations = getInvalidCombinationsOf(delimiterExpression);
		for (Iterator<String> allCombinationsToTraverse = allInvalidCombinations.iterator(); allCombinationsToTraverse.hasNext();) {
			String invalidCombination = allCombinationsToTraverse.next();
			invalidCombination = removeEscapeCharactersFrom(invalidCombination);
			if (input.contains(invalidCombination)) {
				addErrorDetailsFor(INVALID_ENTRIES_NOT_ALLOWED, input);
				return false;
			}
		}
		return true;
	}
	
	private String removeEscapeCharactersFrom(String input){
		if(!delimiterContainsOnlyPipes(delimiterExpression)){
			return input.replace(ESCAPE_CHARACTER, "");
		}
		else {
			return input;
		}
	}
	
	private boolean delimiterContainsOnlyPipes(String delimiters) {
		boolean isAllPipes = true;
		for (char delimiter : delimiters.toCharArray()) {
			if (delimiter != ESCAPE_CHARACTER.charAt(0) && delimiter != PIPE) {
				isAllPipes = false;
			}
		}
		return isAllPipes;
	}
	
	private ArrayList<String> getInvalidCombinationsOf(String delimiterExpression) {
		String[] delimiterSplit = delimiterExpression.split(ESCAPE_CHARACTER + PIPE);

		ArrayList<String> invalidCombinations = new ArrayList<String>();
		for (int headDelimiterIndex = 0; headDelimiterIndex < delimiterSplit.length; headDelimiterIndex++) {
			String combination = delimiterSplit[headDelimiterIndex];
			invalidCombinations.add(twoOfTheSameDelimitersWithNoDigit(combination));
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
	
	private String twoOfTheSameDelimitersWithNoDigit(String combination){
		return combination + combination;
	}
	
	private String reverse(String input) {
		return new StringBuffer(input).reverse().toString();
	}
}
