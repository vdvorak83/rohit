package com.programsji.service;

import java.util.List;

import com.programsji.model.Person;

public interface PersonService {
	public void create(Person person);

	public void update(Person person);

	public Person getPerson(Integer id);

	public List<Person> listPersons();

	public void delete(Integer id);
}
