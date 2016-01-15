package com.programsji.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl_db")
public class UserDetailsServiceImpl_db implements UserDetailsService {

	@Autowired
	private UserDao_db userDao_db;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserDetails userDetails = null;
		//User_db userEntity = userDao_db.getUserDetailByUserName(username);
		return null;
	}

}
