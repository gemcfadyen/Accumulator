package gemcfadyen.accumulator;


import static junit.framework.Assert.assertNull;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class InputValidatorTest {
	
	@Test
	public void shouldReturnTrueForValidInput() throws InvalidValueException{
		Validator validator = new InputValidator("3\n1\n3", "\n|,");
		boolean isValid = validator.validate();
		assertThat(isValid, is(true));
		assertNull(validator.getValidationErrorMessage());
	}
	
	@Test
	public void shouldReturnFalseIfInputIsOnlyANewLine() throws InvalidValueException{
		Validator validator = new InputValidator("\n", "\n|,");
		boolean isValid = validator.validate();
		assertThat(isValid, is(false));
		assertThat(validator.getValidationErrorMessage(), is("Invalid entries not allowed [\n] "));
	}
	
	@Test
	public void shouldReturnFalseIfInputIsOnlyAComma() throws InvalidValueException{
		Validator validator = new InputValidator(",", "\n|,");
		boolean isValid = validator.validate();
		assertThat(isValid, is(false));
		assertThat(validator.getValidationErrorMessage(), is("Invalid entries not allowed [,] "));
	}
	
	@Test
	public void shouldReturnFalseForInvalidInput() throws InvalidValueException{
		Validator validator = new InputValidator("3\n-3", "\n|,");
		boolean isValid = validator.validate();
		assertThat(isValid, is(false));
		assertThat(validator.getValidationErrorMessage(), is("negatives not allowed [-3] "));
	}
	
	@Test
	public void shouldReturnAllNegativeNumbersUsedInInputString() throws InvalidValueException{
		Validator validator = new InputValidator("3\n-3,-7,-8", "\n|,");
		validator.validate();
		assertThat(validator.getValidationErrorMessage(), is("negatives not allowed [-3] [-7] [-8] "));
	}
	
	@Test
	public void shouldReturnFalseForInvalidCombinationOfBasicDelimiters() throws InvalidValueException{
		Validator validator = new InputValidator("3\n3,\n", "\n|,");
		boolean isValid = validator.validate();
		assertThat(isValid, is(false));
		assertThat(validator.getValidationErrorMessage(), is("Invalid entries not allowed [3\n3,\n] "));
	}
	
	@Test(expected=InvalidValueException.class)
	public void shouldThrowExceptionWhenTwoCustomDelimitersAreUsedInInputStringWithNoDigitInbetween() throws InvalidValueException{
		Validator validator = new InputValidator("3&&$", "\\&\\&|\\$,");
		validator.validate();
	}
	
	@Test(expected=InvalidValueException.class)
	public void shouldThrowExceptionIfNonNumericCharactersUsedInInputString() throws InvalidValueException{
		Validator validator = new InputValidator("3,a,", "\n|,");
		validator.validate();
	}
	
}
