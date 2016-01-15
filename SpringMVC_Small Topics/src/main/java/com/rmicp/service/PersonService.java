package com.rmicp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmicp.bo.Person;
import com.rmicp.dao.PersonDao;

@Service
@Transactional
public class PersonService {

	@Autowired
	PersonDao personDao;

	public void addPerson(Person person) {
		personDao.addPerson(person);
	}

	public List<Person> getPersonList(String fname, String lname, int age) {
		return personDao.getPersonList(fname, lname, age);
	}
}
