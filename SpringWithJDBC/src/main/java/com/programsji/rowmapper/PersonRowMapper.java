package com.programsji.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.programsji.model.Person;

public class PersonRowMapper implements RowMapper<Person> {

	@Override
	public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
		PersonExtractor personExtractor = new PersonExtractor();
		return personExtractor.extractData(rs);
	}

}
