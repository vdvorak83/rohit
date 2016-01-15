package com.programsji.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.programsji.bo.Person;

@Repository
public class PersonDao {

	@Resource
	SessionFactory sessionFactory;

	public PersonDao() {

	}

	public void addPerson(Person person) {
		sessionFactory.getCurrentSession().save(person);
	}

	public List<Person> getPersonList(String fname, String lname, int age) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Person p");
		return query.list();
	}
}
