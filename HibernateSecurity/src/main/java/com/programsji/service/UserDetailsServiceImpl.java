package com.programsji.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.programsji.bo.Role;
import com.programsji.bo.User;
import com.programsji.dao.UserDao;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		}
		User user = new User(userHib.getUserName(), userHib.getUserName(),
				userHib.getPassword(), true, true, true, true);
		user.setAuthorities(authorities);
		return user;
	}
}