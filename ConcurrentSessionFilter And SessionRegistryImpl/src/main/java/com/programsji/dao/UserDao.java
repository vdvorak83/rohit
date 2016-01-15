package com.programsji.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.programsji.bo.Role;
import com.programsji.bo.User;

@Repository
public class UserDao {
	@Resource(name = "sessionRegistry")
	// @Autowired
	SessionRegistryImpl sessionRegistry;

	public UserDetails getUserDetailByUserName(String username) {
		User user = new User("John Nathan", "john", "test", true, true, true,
				true);
		List<Role> roles = new ArrayList<Role>();
		Role role = new Role();
		role.setName("ROLE_USER");
		Role role1 = new Role();
		role1.setName("ROLE_ADMIN");
		roles.add(role);
		roles.add(role1);
		user.setAuthorities(roles);
		return user;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	public SessionRegistryImpl getSessionRegistry() {
		return sessionRegistry;
	}

	public void setSessionRegistry(SessionRegistryImpl sessionRegistry) {
		this.sessionRegistry = sessionRegistry;
	}
}