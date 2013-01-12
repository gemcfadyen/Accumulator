package gemcfadyen.accumulator;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class AccumulatorTest {
	private Accumulator accumulator;
	
	@Before
	public void setup(){
		accumulator = new Accumulator();
	}
	
	@Test
	public void shouldReturn0WhenEmptyStringIsPassedToAccumulator(){
		int sum = accumulator.add("");
		assertThat(sum, is(equalTo(0)));
	}
	
	@Test
	public void shouldReturn1When1IsPassedToAccumulator(){
		int sum=accumulator.add("1");
		assertThat(sum, is(equalTo(1)));
	}
	
	@Test
	public void shouldReturn3When3IsPassedToAccumulator(){
		int sum=accumulator.add("3");
		assertThat(sum, is(equalTo(3)));
	}
	
	@Test
	public void shouldReturn10When10IsPassedIn(){
		int sum = accumulator.add("10");
		assertThat(sum, is(equalTo(10)));
	}

}
