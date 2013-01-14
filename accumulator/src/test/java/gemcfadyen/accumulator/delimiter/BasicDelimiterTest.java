package gemcfadyen.accumulator.delimiter;

import static gemcfadyen.accumulator.delimiter.BasicDelimiter.COMMA_DELIMITER;
import static gemcfadyen.accumulator.delimiter.Delimiter.NEWLINE_DELIMITER;
import static gemcfadyen.accumulator.delimiter.Delimiter.PIPE;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import gemcfadyen.accumulator.InvalidValueException;
import gemcfadyen.accumulator.delimiter.BasicDelimiter;
import gemcfadyen.accumulator.delimiter.Delimiter;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class BasicDelimiterTest {
	private Delimiter delimiter;
	private String input;
	private String delimiterExpression;
	private String inputWithNewLineCharactersReplacedWithCommas;
	
	private static final String COMMA_OR_NEWLINE_DELIMITER = COMMA_DELIMITER + PIPE + NEWLINE_DELIMITER;

	public BasicDelimiterTest(String input, String delimiterExpression, String inputWithNewLineCharactersReplacedWithCommas) {
		this.input = input;
		this.delimiterExpression = delimiterExpression;
		this.inputWithNewLineCharactersReplacedWithCommas = inputWithNewLineCharactersReplacedWithCommas;
	}

	@Before
	public void setupBasicDelimiter() {
		delimiter = new BasicDelimiter();
	}

	@Parameters
	public static List<Object[]> testDataInitialisation() {
		return Arrays.asList(new Object[][] { 
				{ "", COMMA_OR_NEWLINE_DELIMITER, "" },
				{ "0", COMMA_OR_NEWLINE_DELIMITER, "0" }, 
				{ "1", COMMA_OR_NEWLINE_DELIMITER, "1" },
				{ "3", COMMA_OR_NEWLINE_DELIMITER, "3" }, 
				{ "10", COMMA_OR_NEWLINE_DELIMITER, "10" },
				{ "1,2", COMMA_OR_NEWLINE_DELIMITER, "1,2" },
				{ "3,4", COMMA_OR_NEWLINE_DELIMITER, "3,4" },
				{ "10,10", COMMA_OR_NEWLINE_DELIMITER, "10,10" },
				{ "1,2,3", COMMA_OR_NEWLINE_DELIMITER, "1,2,3" },
				{ "7,8,9,10", COMMA_OR_NEWLINE_DELIMITER, "7,8,9,10" },
				{ "1,2,3,4,5,6,7", COMMA_OR_NEWLINE_DELIMITER, "1,2,3,4,5,6,7" },
				{ "1\n2,3", COMMA_OR_NEWLINE_DELIMITER, "1\n2,3" },
				{ "3\n1\n3", COMMA_OR_NEWLINE_DELIMITER, "3\n1\n3" },
				{ "1,2,3,4\n5", COMMA_OR_NEWLINE_DELIMITER, "1,2,3,4\n5" },
				{ "6\n2,2,1\n6\n1", COMMA_OR_NEWLINE_DELIMITER, "6\n2,2,1\n6\n1" },
				{ "999\n2\n2000", COMMA_OR_NEWLINE_DELIMITER, "999\n2\n2000" },

		});
	}

	@Test
	public void shouldReturnTotalOfNumbersPassedIntoAccumulator() throws InvalidValueException {
		String calculatedDelimiter = delimiter.getDelimiterUsedIn(input);
		assertThat(calculatedDelimiter, is(equalTo(delimiterExpression)));
	}

	@Test
	public void shouldReturnInputStringContainingCommasInPlaceOfNewLineCharacters() {
		String standardisedString = delimiter.stripDelimiterFromStartOf(input);
		assertThat(standardisedString, is(equalTo(inputWithNewLineCharactersReplacedWithCommas)));
	}

}
