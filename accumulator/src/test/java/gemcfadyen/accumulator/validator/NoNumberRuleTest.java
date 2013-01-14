package gemcfadyen.accumulator.validator;

import static gemcfadyen.accumulator.validator.Validator.INVALID_ENTRIES_NOT_ALLOWED;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import gemcfadyen.accumulator.InvalidValueException;

import org.junit.Test;

public class NoNumberRuleTest {

	@Test
	public void shouldReturnFalseIfInputIsOnlyANewLine() throws InvalidValueException {
		Validator validator = new NoNumberRule("\n", "\n|,");
		boolean isValid = validator.isValid();
		assertThat(isValid, is(false));
		assertThat(validator.getErrorMessage(), is(INVALID_ENTRIES_NOT_ALLOWED + "[\n] "));
	}

	@Test
	public void shouldReturnFalseIfInputIsOnlyAComma() throws InvalidValueException {
		Validator validator = new NoNumberRule(",", "\n|,");
		boolean isValid = validator.isValid();
		assertThat(isValid, is(false));
		assertThat(validator.getErrorMessage(), is(INVALID_ENTRIES_NOT_ALLOWED + "[,] "));
	}

}
