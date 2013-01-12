package gemcfadyen.accumulator;

import static java.lang.Integer.valueOf;

public class Accumulator {

	public int add(String numbers) {
		if (numbers.length() == 0) {
			return 0;
		} else {

			String[] inputToSum = numbers.split(",");
			int result = 0;
			for (String input : inputToSum) {
				result = result + valueOf(input);
			}
			return result;
		}

	}

}
