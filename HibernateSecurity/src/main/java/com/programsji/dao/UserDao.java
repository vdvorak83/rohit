package com.programsji.dao;

import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.stereotype.Repository;

import com.programsji.bo.User;

@Repository
public class UserDao {
	@Resource
	SessionFactory sessionFactory;

	public User getUser(String userName) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			User user = (User) session.createCriteria(User.class)
					.add(Restrictions.eq("userName", userName)).uniqueResult();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}