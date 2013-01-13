package gemcfadyen.accumulator;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CustomDelimiterTest {
	private Delimiter delimiter = new CustomDelimiter();
	private String input;
	private String delimiterExpression;

	public CustomDelimiterTest(String input, String delimiterExpression) {
		this.input = input;
		this.delimiterExpression = delimiterExpression;
	}

	@Parameters
	public static List<Object[]> testDataInitialisation() {
		return Arrays.asList(new Object[][] {
				{"//;\n1;2", "\\;"}, {"//:\n3:4:1", "\\:"}, {"//|\n1|1|1","\\|"}, {"//*\n9*1*6", "\\*"}, {"//^\n9^1", "\\^"}, {"//&\n0&0&0","\\&"},
				{"//%\n1%1001", "\\%"}, {"//@\n3@4@3000", "\\@"}, 
				{"//***\n1***2***3", "\\*\\*\\*"}, {"//;;;\n1;;;1;;;2", "\\;\\;\\;"}, {"//||\n1||1||1", "\\|\\|"}, {"//abcd\n3abcd5abcd2", "abcd"},
				{"//*|%\n1*2%3", "\\*|\\%"}, {"//@|a\n4@1a3", "\\@|a"}, {"//&|$|£\n4$4£1&","\\&|\\$|\\£"},
				{"//***|%%\n1***2%%3", "\\*\\*\\*|\\%\\%"}, {"//^|((|$$\n1$$2^3((1", "\\^|\\(\\(|\\$\\$"}
			});
	}

	@Test
	public void shouldReturnTotalOfNumbersPassedIntoAccumulator() throws InvalidValueException {
		delimiter = new CustomDelimiter();
		String calculatedDelimiter = delimiter.getDelimiterUsedIn(input);
		assertThat(calculatedDelimiter, is(equalTo(delimiterExpression)));
	}
}
