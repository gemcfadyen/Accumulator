package gemcfadyen.accumulator.delimiter;

public interface Delimiter {
	String CUSTOM_DELIMITER_INDICATOR = "//";
	String ESCAPE_CHARACTER = "\\";
	String NEWLINE_DELIMITER = "\n";
	char PIPE = '|';
	String getDelimiterUsedIn(String input);
	String stripDelimiterFromStartOf(String input);
}
