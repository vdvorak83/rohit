package com.programsji.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.programsji.dao.PersonDao;
import com.programsji.model.Person;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonDao persondao;

    @Override
    public void create(Person person) {
        persondao.create(person);
    }

    @Override
    public void update(Person person) {
        persondao.update(person);

    }

    @Override
    public Person getPerson(Integer id) {
        return persondao.getPerson(id);
    }

    @Override
    public List<Person> listPersons() {
        return persondao.listPersons();
    }

    @Override
    public void delete(Integer id) {
        persondao.delete(id);

    }
}
