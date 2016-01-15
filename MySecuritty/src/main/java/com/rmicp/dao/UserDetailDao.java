package com.rmicp.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.rmicp.bo.Role;
import com.rmicp.bo.User;

@Repository
public class UserDetailDao {

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		return getUserDetails();
	}

	public UserDetails getUserDetails() {
		User user = new User();
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEmailId("rm.software.traine@gmail.com");
		user.setEnabled(true);
		user.setUsername("Rohit");
		user.setPassword("1234567");
		List<Role> roles = new ArrayList<Role>();
		Role role1 = new Role();
		role1.setName("ROLE_USER");

		// //Role role2 = new Role();
		// role2.setName("ROLE_EMPLOYEE");
		// //role2.setName("ROLE_ADMIN");

		Role role3 = new Role();
		role3.setName("ROLE_CUSTOMER");

		roles.add(role1);
		// ///roles.add(role2);
		roles.add(role3);
		user.setRoles(roles);
		return user;
	}
}
