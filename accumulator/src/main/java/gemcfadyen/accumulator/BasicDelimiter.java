package gemcfadyen.accumulator;

public class BasicDelimiter implements Delimiter {
	protected static final String COMMA_DELIMITER = ",";
	
	
	public String getDelimiterUsedIn(String input){
		return COMMA_DELIMITER;
	}

}
