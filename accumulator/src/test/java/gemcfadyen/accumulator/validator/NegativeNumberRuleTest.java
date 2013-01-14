package gemcfadyen.accumulator.validator;

import static gemcfadyen.accumulator.validator.Validator.NEGATIVES_NOT_ALLOWED;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import gemcfadyen.accumulator.InvalidValueException;

import org.junit.Test;

public class NegativeNumberRuleTest {

	@Test
	public void shouldReturnFalseWhenNegativeValueInInput() throws InvalidValueException{
		Validator validator = new NegativeNumberRule("3\n-3", "\n|,");
		boolean isValid = validator.isValid();
		assertThat(isValid, is(false));
		assertThat(validator.getErrorMessage(), is(NEGATIVES_NOT_ALLOWED + "[-3] "));
	}
	
	@Test
	public void shouldReturnAllNegativeNumbersUsedInInputString() throws InvalidValueException{
		Validator validator = new NegativeNumberRule("3\n-3,-7,-8", "\n|,");
		assertThat(validator.isValid(), is(false));
		assertThat(validator.getErrorMessage(), is(NEGATIVES_NOT_ALLOWED + "[-3] [-7] [-8] "));
	}
	
	@Test(expected=InvalidValueException.class)
	public void shouldThrowExceptionIfNonDigitIsUsedInInputStringWithNegativeValues() throws InvalidValueException{
		Validator validator = new NegativeNumberRule("3\na,-7,-8", "\n|,");
		validator.isValid();
	}

}