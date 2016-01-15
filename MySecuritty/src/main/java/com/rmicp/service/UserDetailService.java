package com.rmicp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rmicp.dao.UserDetailDao;

@Service
public class UserDetailService implements UserDetailsService {

	@Autowired
	UserDetailDao userDetailDao;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserDetails userdetail = userDetailDao.loadUserByUsername(username);
		if (userdetail == null) {
			throw new UsernameNotFoundException("User Name Not Found");
		}
		return userdetail;
	}
}
