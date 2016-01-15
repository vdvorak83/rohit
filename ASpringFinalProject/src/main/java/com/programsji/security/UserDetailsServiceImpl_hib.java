package com.programsji.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl_hib implements UserDetailsService {

	@Autowired
	UserDao_hib userDaoHib;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User_hib userHib = userDaoHib.getUser(username);
		List<Role> authorities = new ArrayList<Role>();
		for (Role_hib role : userHib.getUserRoles()) {
			authorities.add(new Role(role.getRoleName()));
		}
		User user = new User(userHib.getUserName(), userHib.getUserName(),
				userHib.getPassword(), true, true, true, true);
		user.setAuthorities(authorities);
		return user;
	}
}