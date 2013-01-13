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
public class BasicDelimiterTest {
	private Delimiter delimiter;
	private String input;
	private String delimiterExpression;

	public BasicDelimiterTest(String input, String delimiterExpression) {
		this.input = input;
		this.delimiterExpression = delimiterExpression;
	}

	@Parameters
	public static List<Object[]> testDataInitialisation() {
		return Arrays.asList(new Object[][] {
				{ "", "," }, {"0", ","}, { "1","," }, { "3", "," }, { "10", "," }, 
				{ "1,2", "," }, { "3,4", "," }, { "10,10", "," }, 
				{ "1,2,3", "," }, { "7,8,9,10", "," },	{ "1,2,3,4,5,6,7", "," },
				{ "1\n2,3", "," }, { "3\n1\n3", "," }, { "1,2,3,4\n5", "," }, {"6\n2,2,1\n6\n1", ","},
				{"999\n2\n2000","," },
			
			});
	}

	@Test
	public void shouldReturnTotalOfNumbersPassedIntoAccumulator() throws InvalidValueException {
		delimiter = new BasicDelimiter();
		String calculatedDelimiter = delimiter.getDelimiterUsedIn(input);
		assertThat(calculatedDelimiter, is(equalTo(delimiterExpression)));
	}
	
}
