package gemcfadyen.accumulator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AccumulatorWithErrorneousInputTest {
	private Accumulator accumulator;

	@Before
	public void setupAccumulator() {
		accumulator = new StringCalculator();
	}

	@Test(expected = InvalidValueException.class)
	public void shouldThrowExceptionWhenNoNumbersBetweenTwoNewLineDelimeters()
			throws InvalidValueException {
		accumulator.add("1\n\n");
	}

	@Test(expected = InvalidValueException.class)
	public void shouldThrowExceptionWhenOnlyNewLineDelimiterIsPresentInInput()
			throws InvalidValueException {
		accumulator.add("\n");
	}

	@Test
	public void shouldThrowExceptionWhenSeveralNegativeValuesAreInput() throws InvalidValueException {
		String expectedErrorMessage = "negatives not allowed [-1] [-3] ";
		String actualErrorMessage = "";
		try {
			accumulator.add("//.\n-1.2.-3");
		} catch (InvalidValueException exception) {
			actualErrorMessage = exception.getMessage();
		}
		assertTrue(actualErrorMessage.startsWith(expectedErrorMessage));
	}

	@Test
	public void shouldThrowExceptionWhenSeveralNegativeValuesAreInputUsingMulipleDelimitersOfSingleleLength() throws InvalidValueException {
		String expectedErrorMessage = "negatives not allowed [-9] [-3] ";
		String actualErrorMessage = "";
		try {
			accumulator.add("//%|^\n1%-9^-3");
			
		} catch (InvalidValueException exception) {
			actualErrorMessage = exception.getMessage();
		}
		assertTrue(actualErrorMessage.startsWith(expectedErrorMessage));
	}

	@Test
	public void shouldThrowExceptionWhenSeveralNegativeValuesAreInputUsingMulipleDelimitersOfMultipleLength()
			throws InvalidValueException {
		String expectedErrorMessage = "negatives not allowed [-9] [-3] ";
		String actualErrorMessage = "";
		try {
			accumulator.add("//%%|^\n1%%-9^-3");
		} catch (InvalidValueException exception) {
			actualErrorMessage = exception.getMessage();
		}
		assertTrue(actualErrorMessage.startsWith(expectedErrorMessage));
	}

	@Test
	public void shouldThrowExceptionWhenThereAreNoNumbersBetweenCustomDelimiters()
			throws InvalidValueException {
		String expectedErrorMessage = " is not a valid number";
		String actualErrorMessage = "";
		try {
			accumulator.add("//%%|^\n1%%^3");
		} catch (InvalidValueException exception) {
			actualErrorMessage = exception.getMessage();
		}
		assertThat(actualErrorMessage, is(expectedErrorMessage));

	}

	@Test
	public void shouldReturnErrorWhenDelimiterOfCustomLengthHasNoDigitsInbeween() throws InvalidValueException {
		String expectedErrorMessage = "Invalid entries not allowed [3&&$]";
		String actualErrorMessage = "";
		try {
			accumulator.add("//&&|$\n3&&$");
		} catch (InvalidValueException exception) {
			actualErrorMessage = exception.getMessage();
		}
		assertTrue(actualErrorMessage.contains(expectedErrorMessage));
	}
}
