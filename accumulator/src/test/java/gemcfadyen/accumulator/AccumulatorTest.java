package gemcfadyen.accumulator;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class AccumulatorTest {
	
	@Test
	public void shouldReturn0WhenEmptyStringIsPassedToAccumulator(){
		Accumulator calculator = new Accumulator();
		int sum = calculator.add("");
		assertThat(sum, is(equalTo(0)));
	}

}
