package gemcfadyen.accumulator.validator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import gemcfadyen.accumulator.InvalidValueException;

import org.junit.Test;

public class InputStringRulesTest {

	
	@Test
	public void shouldReturnTrueWhenNoValidationRulesAreBroken() throws InvalidValueException{
		ValidationRules validationRules = new InputStringRules("1,2,3",",|\n");	
		assertThat(validationRules.isValid(), is(true));
		assertThat(validationRules.getErrorDescription(), is(""));
	}
	
	@Test
	public void shouldReturnFalseWhenAnyValidationRuleBreaks() throws InvalidValueException{
		ValidationRules validationRules = new InputStringRules("1,2,-3",",|\n");	
		assertThat(validationRules.isValid(), is(false));
		String errorMessage = validationRules.getErrorDescription();
		assertTrue(errorMessage.startsWith("negatives not allowed [-3] "));
	}
	
}
