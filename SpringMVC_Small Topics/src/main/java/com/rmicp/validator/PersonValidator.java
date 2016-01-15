package com.rmicp.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.rmicp.bo.Person;

@Component
public class PersonValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// Person.class.
		return true;
	}

	@Override
	public void validate(Object target, Errors errors) {

	}

}
