package com.rmicp.dao;

import java.util.ArrayList;
import java.util.List;

import org.hsqldb.lib.ArrayListIdentity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import com.rmicp.bo.*;

@Repository
public class UserDao {
	List<User> users = new ArrayList<User>();

	public UserDetails loadUserByUsername(String username) {
		createDefaultusers();
		for (User u : users) {
			if (u.getUsername().equals(username)) {
				return u;
			}
		}
		return null;
	}

	private void createDefaultusers() {
		List<Role> role1 = new ArrayList<Role>();
		List<Role> role2 = new ArrayList<Role>();
		List<Role> role3 = new ArrayList<Role>();
		role1.add(new Role("ROLE_EMPLOYEE"));

		role2.add(new Role("ROLE_CUSTOMER"));

		role3.add(new Role("ROLE_EMPLOYEE"));
		role3.add(new Role("ROLE_CUSTOMER"));

		User user1 = new User("employee", "123456", role1);
		User user2 = new User("customer", "123456", role3);
		User user3 = new User("both", "123456", role2);
		users.add(user1);
		users.add(user2);
		users.add(user3);

	}
}
