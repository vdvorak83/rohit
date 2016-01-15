package com.rmicp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Component;

import com.rmicp.service.UserDetailService;

public class MyAuthenticationProvider extends
		AbstractUserDetailsAuthenticationProvider
// implements AuthenticationProvider

{
	private UserDetailsChecker preAuthenticationChecks = new MyUserDetailsChecker();
	private UserDetailsChecker postAuthenticationChecks = new MyUserDetailsChecker();

	@Autowired
	UserDetailService userDetailService;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String username = authentication.getName();
		UserDetails userDetail = userDetailService.loadUserByUsername(username);
		if (userDetail == null) {
			throw new BadCredentialsException("Username not found!!");
		}
		if (!userDetail.getPassword().equals(authentication.getCredentials())) {
			throw new BadCredentialsException("Username not found!!");
		}
		preAuthenticationChecks.check(userDetail);
		authentication = new UsernamePasswordAuthenticationToken(username,
				authentication.getCredentials(), userDetail.getAuthorities());
		postAuthenticationChecks.check(userDetail);
		return authentication;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		// TODO Auto-generated method stub

	}

	@Override
	protected UserDetails retrieveUser(String username,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}

}
