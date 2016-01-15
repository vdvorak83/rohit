package com.programsji.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class CustomerUsernamePasswordAuthenticationToken extends
		UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = 1L;

	public CustomerUsernamePasswordAuthenticationToken(Object principal,
			Object credentials) {
		super(principal, credentials);
	}

}
