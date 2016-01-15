package com.programsji.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.programsji.bo.Person;

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
