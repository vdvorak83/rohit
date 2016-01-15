package com.programsji.security;

import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao_hib {
	@Resource
	SessionFactory sessionFactory;

	public User_hib getUser(String userName) {
		Session session = null;
		try {
			session = sessionFactory.openSession();

			Map cm = sessionFactory.getAllClassMetadata();

			User_hib user = (User_hib) session.createCriteria(User_hib.class)
					.add(Restrictions.eq("userName", userName)).uniqueResult();

			// User_hib user = (User_hib) session.createQuery(
			// "Select u from User_hib u where u.userName='" + userName
			// + "'").uniqueResult();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void saveUser(User_hib user) {
		Session session = null;
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
	}
}
