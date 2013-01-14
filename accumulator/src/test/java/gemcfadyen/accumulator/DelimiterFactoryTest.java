package gemcfadyen.accumulator;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class DelimiterFactoryTest {
	private DelimiterFactory delimiterFactory = new DelimiterFactory();
	
	@Test
	public void shouldReturnABasicDelimiter(){
		Delimiter basicDelimiter = delimiterFactory.createDelimiterFrom("1,2,3");
		assertThat(basicDelimiter, is(instanceOf(BasicDelimiter.class)));
	}
	
	@Test
	public void shouldReturnACustomDelimiter(){
		Delimiter customDelimiter = delimiterFactory.createDelimiterFrom("//*\n1*2*3");
		assertThat(customDelimiter, is(instanceOf(CustomDelimiter.class)));
	}
}

