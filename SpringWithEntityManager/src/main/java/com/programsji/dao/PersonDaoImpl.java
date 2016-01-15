package com.programsji.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.programsji.model.Person;

@Repository
public class PersonDaoImpl implements PersonDao {

	@PersistenceContext(unitName = "entityManager")
	private EntityManager entityManager;

	@Override
	public void create(Person person) {
		this.entityManager.persist(person);
	}

	@Override
	public void update(Person person) {
		this.entityManager.merge(person);
	}

	@Override
	public Person getPerson(Integer id) {
		return this.entityManager.find(Person.class, id);
	}

	@Override
	public List<Person> listPersons() {
		CriteriaQuery cq = this.entityManager.getCriteriaBuilder()
				.createQuery();
		cq.select(cq.from(Person.class));
		return this.entityManager.createQuery(cq).getResultList();
	}

	@Override
	public void delete(Integer id) {
		this.entityManager.remove((getPerson(id)));
	}
}
