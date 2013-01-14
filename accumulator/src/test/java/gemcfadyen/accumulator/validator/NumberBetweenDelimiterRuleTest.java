package gemcfadyen.accumulator.validator;

import static gemcfadyen.accumulator.validator.Validator.INVALID_ENTRIES_NOT_ALLOWED;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import gemcfadyen.accumulator.InvalidValueException;

import org.junit.Test;

public class NumberBetweenDelimiterRuleTest {
		@Test
		public void shouldReturnFalseWhenNoNumbersBetweenCommaAndNewLineDelimiter() throws InvalidValueException {
			Validator validator = new NumberBetweenDelimiterRule("1,\n", "\n|,");
			boolean isValid = validator.isValid();
			assertThat(isValid, is(false));
			assertThat(validator.getErrorMessage(), is(INVALID_ENTRIES_NOT_ALLOWED + "[1,\n] "));
		}
		
		@Test
		public void shouldReturnTrueForValidInput() throws InvalidValueException{
			Validator validator = new NumberBetweenDelimiterRule("3\n1\n3", "\n|,");
			boolean isValid = validator.isValid();
			assertThat(isValid, is(true));
			assertNull(validator.getErrorMessage());
		}
		
		@Test
		public void shouldReturnFalseForInvalidCombinationOfBasicDelimiters() throws InvalidValueException{
			Validator validator = new NumberBetweenDelimiterRule("3\n3,\n", "\n|,");
			boolean isValid = validator.isValid();
			assertThat(isValid, is(false));
			assertThat(validator.getErrorMessage(), is(INVALID_ENTRIES_NOT_ALLOWED + "[3\n3,\n] "));
		}
		
		@Test
		public void shouldReturnFalseWhenTwoCustomDelimitersAreUsedInInputStringWithNoDigitInbetween() throws InvalidValueException{
			Validator validator = new NumberBetweenDelimiterRule("3&&$", "\\&\\&|\\$");
			assertThat(validator.isValid(), is(false));
			assertThat(validator.getErrorMessage(), is(INVALID_ENTRIES_NOT_ALLOWED + "[3&&$] "));
		}
			
		@Test
		public void shouldThrowExceptionWhenTherAreSeveralDelimitersWithNoNumbersInBetweenThem() throws InvalidValueException {
			Validator validator = new NumberBetweenDelimiterRule("1,,,\n,\n\n\n\n\n,,\n\n,,,,3", ",|\n");
			assertThat(validator.isValid(), is(false));
			assertThat(validator.getErrorMessage(), is(INVALID_ENTRIES_NOT_ALLOWED + "[1,,,\n,\n\n\n\n\n,,\n\n,,,,3] "));
		}
}
