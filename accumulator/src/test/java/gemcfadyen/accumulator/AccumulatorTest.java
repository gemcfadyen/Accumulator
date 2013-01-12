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
		return Arrays.asList(new Object[][] { { "", 0 }, { "1", 1 },
				{ "3", 3 }, { "10", 10 } });
	}

	@Test
	public void shouldReturnTotalOfNumbersPassedIntoAccumulator() {
		accumulator = new Accumulator();
		int result = accumulator.add(input);
		assertThat(result, is(equalTo(sum)));
	}

}
