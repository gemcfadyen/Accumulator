package gemcfadyen.accumulator;

import static java.lang.Integer.valueOf;

public class Accumulator {

	private static final String DELIMITER = ",";
	private int result = 0;

	public int add(String input) {
		if (noNumbersPresentIn(input)) {
			return result;
		} else {
			String[] numbers = input.split(DELIMITER);
			result = sum(numbers);
			return result;
		}

	}

	private int sum(String[] numbers) {
		for (String number : numbers) {
			result = result + valueOf(number);
		}
		return result;
	}

	private boolean noNumbersPresentIn(String input) {
		return input.length() == 0;
	}

}
