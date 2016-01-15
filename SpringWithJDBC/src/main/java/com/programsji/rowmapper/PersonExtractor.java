package com.programsji.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.programsji.model.Person;

public class PersonExtractor implements ResultSetExtractor<Person> {

	@Override
	public Person extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		Person person = new Person();
		person.setId(rs.getInt(1));
		person.setFirstname(rs.getString(2));
		person.setLastname(rs.getString(3));
		person.setAge(rs.getInt(4));
		return person;
	}

}
