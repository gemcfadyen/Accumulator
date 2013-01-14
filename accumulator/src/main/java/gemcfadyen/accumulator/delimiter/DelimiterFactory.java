package gemcfadyen.accumulator.delimiter;

import static gemcfadyen.accumulator.delimiter.Delimiter.CUSTOM_DELIMITER_INDICATOR;

public class DelimiterFactory {
	public Delimiter createDelimiterFrom(String input) {
		if (input.startsWith(CUSTOM_DELIMITER_INDICATOR)) {
			return new CustomDelimiter();
		} else {
			return new BasicDelimiter();
		}
	}

}
