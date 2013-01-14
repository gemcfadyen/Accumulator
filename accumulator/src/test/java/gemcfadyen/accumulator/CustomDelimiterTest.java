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
	private String inputWithoutDelimiterDefinition;

	public CustomDelimiterTest(String input, String delimiterExpression,
			String inputWithoutDelimiterDefinition) {
		this.input = input;
		this.delimiterExpression = delimiterExpression;
		this.inputWithoutDelimiterDefinition = inputWithoutDelimiterDefinition;
	}

	@Parameters
	public static List<Object[]> testDataInitialisation() {
		return Arrays.asList(new Object[][] { 
				{ "//;\n1;2", "\\;", "1;2" },
				{ "//:\n3:4:1", "\\:", "3:4:1"}, 
				{ "//|\n1|1|1", "\\|", "1|1|1"},
				{ "//*\n9*1*6", "\\*", "9*1*6"},
				{ "//^\n9^1", "\\^", "9^1" },
				{ "//&\n0&0&0", "\\&", "0&0&0"}, 
				{ "//%\n1%1001", "\\%", "1%1001"},
				{ "//@\n3@4@3000", "\\@", "3@4@3000" },
				{ "//***\n1***2***3", "\\*\\*\\*", "1***2***3" },
				{ "//;;;\n1;;;1;;;2", "\\;\\;\\;", "1;;;1;;;2" },
				{ "//||\n1||1||1", "\\|\\|", "1||1||1" },
				{ "//abcd\n3abcd5abcd2", "abcd", "3abcd5abcd2" },
				{ "//*|%\n1*2%3", "\\*|\\%", "1*2%3" }, 
				{ "//@|a\n4@1a3", "\\@|a", "4@1a3"},
				{ "//&|$|£\n4$4£1&1", "\\&|\\$|\\£", "4$4£1&1" },
				{ "//***|%%\n1***2%%3", "\\*\\*\\*|\\%\\%", "1***2%%3" },
				{ "//^|((|$$\n1$$2^3((1", "\\^|\\(\\(|\\$\\$", "1$$2^3((1" } });
	}

	@Test
	public void shouldReturnCustomDelimiterExpression()
			throws InvalidValueException {
		delimiter = new CustomDelimiter();
		String calculatedDelimiter = delimiter.getDelimiterUsedIn(input);
		assertThat(calculatedDelimiter, is(equalTo(delimiterExpression)));
	}
	

	@Test
	public void shouldReturnInputStringContainingCommasInPlaceOfNewLineCharacters() {
		String stringWithoutDelimiter = delimiter.stripDelimiterFromStartOf(input);
		assertThat(stringWithoutDelimiter, is(equalTo(inputWithoutDelimiterDefinition)));
	}
}
