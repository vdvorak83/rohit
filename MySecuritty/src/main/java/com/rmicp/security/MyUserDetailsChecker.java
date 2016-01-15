package com.rmicp.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;

public class MyUserDetailsChecker implements UserDetailsChecker {

	@Override
	public void check(UserDetails toCheck) {
		System.out.println(toCheck);
	}
}
