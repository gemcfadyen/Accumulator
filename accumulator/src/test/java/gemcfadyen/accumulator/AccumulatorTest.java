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
public class AccumulatorTest {
	private Accumulator accumulator;
	private String input;
	private int sum;

	public AccumulatorTest(String input, int sum) {
		this.input = input;
		this.sum = sum;
	}

	@Parameters
	public static List<Object[]> testDataInitialisation() {
		return Arrays.asList(new Object[][] {
				{ "", 0 }, {"0", 0}, { "1", 1 }, { "3", 3 }, { "10", 10 }, 
				{ "1,2", 3 }, { "3,4", 7 }, { "10,10", 20 }, 
				{ "1,2,3", 6 }, { "7,8,9,10", 34 },	{ "1,2,3,4,5,6,7", 28 },
				{ "1\n2,3", 6 }, { "3\n1\n3", 7 }, { "1,2,3,4\n5", 15 }, {"6\n2,2,1\n6\n1", 18},
				{"//;\n1;2", 3}, {"//:\n3:4:1", 8}, {"//|\n1|1|1",3}, {"//*\n9*1*6", 16}, {"//^\n9^1", 10}, {"//&\n0&0&0",0},
				{"2,1001", 2}, {"//%\n1%1001", 1}, {"//@\n3@4@3000", 7}, {"999\n2\n2000",1001 },
				{"//***\n1***2***3", 6}, {"//;;;\n1;;;1;;;2", 4}, {"//||\n1||1||1", 3}, {"//abcd\n3abcd5abcd2", 10},
				{"//*|%\n1*2%3", 6}, {"//@|a\n4@1a3", 8}
			});
	}

	@Test
	public void shouldReturnTotalOfNumbersPassedIntoAccumulator() throws InvalidValueException {
		accumulator = new Accumulator();
		int result = accumulator.add(input);
		assertThat(result, is(equalTo(sum)));
	}
	
}
