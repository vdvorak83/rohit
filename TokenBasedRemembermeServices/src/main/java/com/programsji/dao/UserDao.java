package com.programsji.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.programsji.bo.Role;
import com.programsji.bo.User;

@Repository
public class UserDao {
	public UserDetails getUserDetailByUserName(String username) {
		User user = new User("John Nathan", "john", "john", true, true, true,
				true);
		List<Role> roles = new ArrayList<Role>();
		Role role = new Role();
		role.setName("ROLE_USER");
		Role role1 = new Role();
		role1.setName("ROLE_EMPLOYEE");
		Role role2 = new Role();
		role2.setName("ROLE_CUSTOMER");
		roles.add(role);
		roles.add(role1);
		roles.add(role2);
		user.setAuthorities(roles);
		return user;
	}
}