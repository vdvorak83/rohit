package com.programsji.authenticationprovider;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import com.programsji.bo.User;
import com.programsji.service.UserService;

public class CustomAbstractUserDetailsAuthenticationProvider extends
		AbstractUserDetailsAuthenticationProvider {

	UserService service;

	public CustomAbstractUserDetailsAuthenticationProvider() {

	}

	public CustomAbstractUserDetailsAuthenticationProvider(UserService service) {
		this.service = service;
	}

	@Override
	protected UserDetails retrieveUser(String username,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		String password = (String) authentication.getCredentials();
		User user = (User) getService().loadUserByUsername(username);
		if (user == null) {
			throw new BadCredentialsException("User Name Not Valid");
		}
		return user;
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
	}

	public UserService getService() {
		return service;
	}

	public void setService(UserService service) {
		this.service = service;
	}
}
