package gemcfadyen.accumulator;

public class BasicDelimiter implements Delimiter {
	protected static final String COMMA_DELIMITER = ",";
	
	public String getDelimiterUsedIn(String input){
		return COMMA_DELIMITER +PIPE + NEWLINE_DELIMITER;
	}

	public String stripDelimiterFromStartOf(String input) {
		return replaceNewLineDelimitersWithCommasIn(input);
	}

	private String replaceNewLineDelimitersWithCommasIn(String input) {
		return input;
	}

}
