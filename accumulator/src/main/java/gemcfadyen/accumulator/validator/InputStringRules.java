package gemcfadyen.accumulator.validator;

import gemcfadyen.accumulator.InvalidValueException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InputStringRules implements ValidationRules {
	private List<Validator> validators;
	private boolean isAllValid;

	public InputStringRules(String input, String delimiterExpressions) {
		validators = new ArrayList<Validator>();
		validators.add(new NegativeNumberRule(input, delimiterExpressions));
		validators.add(new NoNumberRule(input, delimiterExpressions));
		validators.add(new NumberBetweenDelimiterRule(input, delimiterExpressions));
	}

	public String getErrorDescription() {
		StringBuffer errorsToReturn= new StringBuffer();
		if (!isAllValid) {
			for (Iterator<Validator> validationRules = validators.iterator(); validationRules.hasNext();) {
				Validator ruleToEvaluate = validationRules.next();
				errorsToReturn.append(ruleToEvaluate.getErrorMessage());
			}
		}
		return errorsToReturn.toString();

	}

	public boolean isValid() throws InvalidValueException {
		isAllValid = true;
		for (Iterator<Validator> validationRules = validators.iterator(); validationRules.hasNext();) {
			Validator ruleToEvaluate = validationRules.next();
			isAllValid = isAllValid && ruleToEvaluate.isValid();
		}
		return isAllValid;
	}
}
