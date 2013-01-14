package gemcfadyen.accumulator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class InvalidValueExceptionTest {
	private static final String ERROR_MSG = "Error Msg";

	@Test
	public void shouldReturnAnInvalidValueExceptionWithCustomMessage() {
		InvalidValueException exception = new InvalidValueException(ERROR_MSG);
		assertThat(exception.getMessage(), is(ERROR_MSG) );
	}
}
