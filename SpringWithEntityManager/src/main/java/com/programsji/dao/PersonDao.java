package com.programsji.dao;

import java.util.List;

import javax.sql.DataSource;

import com.programsji.model.Person;

public interface PersonDao {

	public void create(Person person);

	public void update(Person person);

	public Person getPerson(Integer id);

	public List<Person> listPersons();

	public void delete(Integer id);
}
