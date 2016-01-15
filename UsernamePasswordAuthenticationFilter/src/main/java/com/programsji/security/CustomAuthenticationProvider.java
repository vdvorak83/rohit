package com.programsji.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

import com.programsji.bo.Role;
import com.programsji.bo.User;
import com.programsji.service.UserService;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserService service;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		User user = (User) service.loadUserByUsername(username);
		if (user == null) {
			throw new BadCredentialsException("User Name Not Valid");
		}

		Object obj = authentication.getDetails();

		if (!user.getPassword().equals(password)) {
			throw new BadCredentialsException("Password Not Valid");
		}
		List<Role> roleList = new ArrayList<Role>();

		if (authentication instanceof UsernamePasswordAuthenticationTokenForRawdata) {
			Role role = new Role("ROLE_RAWDATA");
			roleList.add(role);
			return new UsernamePasswordAuthenticationToken(user, password,
					roleList);
		} else if (authentication instanceof UsernamePasswordAuthenticationTokenForReports) {
			Role role = new Role("ROLE_REPORT");
			roleList.add(role);
			return new UsernamePasswordAuthenticationToken(user, password,
					roleList);
		}

		return new UsernamePasswordAuthenticationToken(user, password,
				user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class
				.isAssignableFrom(authentication));
	}
}
