package gemcfadyen.accumulator;

import static java.lang.Integer.valueOf;

public class Accumulator {

	public int add(String numbers) {
		if(numbers.length() == 0){
			return 0;
		} else {
			return valueOf(numbers);
		}
			
	}

}
