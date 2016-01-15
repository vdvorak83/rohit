package com.programsji.service;

import java.util.Date;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.programsji.bo.Person;
import com.programsji.security.User_hib;

//@Secured(value = "ROLE_USER")
@Component
public class PersonService {

	public Person getDefaultPerson() {
		return new Person("Rohit", 10, 1000.43f, new Date());
	}
	
}
