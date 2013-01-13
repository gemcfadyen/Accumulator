package gemcfadyen.accumulator;

import static java.lang.Character.isLetter;
import static java.lang.Character.valueOf;

public class CustomDelimiter implements Delimiter {
	
	public String getDelimiterUsedIn(String input) {
		 return escapeThe(delimitersDeclaredAtStartOf(input));
	}
	
	public String stripDelimiterFromStartOf(String input) {
		return input.substring(indexOfFirstNewLineDelmiterIn(input) + NEWLINE_DELIMITER.length());
	}
	
	private String delimitersDeclaredAtStartOf(String input) {
		return input.substring(indexOfEndOfCustomDelimiterIndicatorIn(input), indexOfFirstNewLineDelmiterIn(input));
	}
	
	private int indexOfEndOfCustomDelimiterIndicatorIn(String input) {
		return input.indexOf(CUSTOM_DELIMITER_INDICATOR) + CUSTOM_DELIMITER_INDICATOR.length();
	}
	
	private int indexOfFirstNewLineDelmiterIn(String input) {
		return input.indexOf(NEWLINE_DELIMITER);
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
}
