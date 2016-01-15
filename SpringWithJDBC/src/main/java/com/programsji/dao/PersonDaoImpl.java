package com.programsji.dao;

import com.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.programsji.model.Person;
import com.programsji.rowmapper.PersonRowMapper;

@Repository
public class PersonDaoImpl implements PersonDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Person person) {
        String sql = "Insert into person(id,firstname,lastname,age) values(?,?,?,?)";
        jdbcTemplate.update(
                sql,
                new Object[]{person.getId(), person.getFirstname(),
                    person.getLastname(), person.getAge()});
    }

    @Override
    public void update(Person person) {
        String sql = "update person set firstname=?,lastname=?,age=? where id=?";
        jdbcTemplate.update(sql,
                new Object[]{person.getFirstname(), person.getLastname(),
                    person.getAge(), person.getId()});

    }

    @Override
    public Person getPerson(Integer id) {
        String sql = "Select * from person where id=" + id + "";
        List<Person> personlist = new ArrayList<Person>();
        personlist = jdbcTemplate.query(sql, new PersonRowMapper());
        return personlist.get(0);
    }

    @Override
    public List<Person> listPersons() {
        List<Person> personlist = new ArrayList<Person>();
        String sql = "Select * from Person";
        return jdbcTemplate.query(sql, new PersonRowMapper());
    }

    @Override
    public void delete(Integer id) {
        String sql = "Delete from person where id=" + id;
        jdbcTemplate.update(sql);
    }
}
