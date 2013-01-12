package gemcfadyen.accumulator;

import static java.lang.Integer.valueOf;

public class Accumulator {

	private static final String COMMA_DELIMITER = ",";
	private static final String NEWLINE_DELIMITER = "\n";

	public int add(String numbers) throws InvalidValueException {
		if (isBlank(numbers)) {
			return 0;
		} else {
			String inputSeperatedByCommas = putCommaInPlaceOfAllNewLineDelimetersIn(numbers);
			if (isValid(inputSeperatedByCommas)) {
				return sum(getNumbersFrom(inputSeperatedByCommas));
			} else {
				throw new InvalidValueException("The input "
						+ inputSeperatedByCommas + " is not valid");
			}
		}
	}

	private boolean isValid(String input) {
		if(input.equals(COMMA_DELIMITER) || input.contains(COMMA_DELIMITER + COMMA_DELIMITER)){
			return false;
		}
		else{
			return true;
		}
	}

	private String[] getNumbersFrom(String input) {
		return input.split(COMMA_DELIMITER);
	}

	private String putCommaInPlaceOfAllNewLineDelimetersIn(String input) {
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
