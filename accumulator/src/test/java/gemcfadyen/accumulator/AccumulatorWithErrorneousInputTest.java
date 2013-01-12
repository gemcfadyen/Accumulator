package gemcfadyen.accumulator;

import org.junit.Before;
import org.junit.Test;

public class AccumulatorWithErrorneousInputTest {

	private Accumulator accumulator;

	@Before
	public void setupAccumulator() {
		accumulator = new Accumulator();
	}
	
	@Test(expected=InvalidValueException.class)
	public void shouldThrowExceptionWhenNoNumbersBetweenCommaAndNewLineDelimiter() throws InvalidValueException{
		accumulator.add("1,\n");
	}
	
	@Test(expected=InvalidValueException.class)
	public void shouldThrowExceptionWhenNoNumbersBetweenTwoCommas() throws InvalidValueException{
		accumulator.add("1,,");
	}
	
	@Test(expected=InvalidValueException.class)
	public void shouldThrowExceptionWhenNoNumbersBetweenTwoNewLineDelimeters() throws InvalidValueException{
		accumulator.add("1\n\n");
	}
	
	@Test(expected=InvalidValueException.class)
	public void shouldThrowExceptionWhenOnlyNewLineDelimiterIsPresentInInput() throws InvalidValueException{
		accumulator.add("\n");
	}
	
	@Test(expected=InvalidValueException.class)
	public void shouldThrowExceptionWhenOnlyCommaDelimiterIsPresentInInput() throws InvalidValueException{
		accumulator.add(",");
	}
	
	@Test(expected=InvalidValueException.class)
	public void shouldThrowExceptionWhenTherAreSeveralDelimitersWithNoNumbersInBetweenThem() throws InvalidValueException{
		accumulator.add("1,,,\n,\n\n\n\n\n,,\n\n,,,,3");
	}
}
