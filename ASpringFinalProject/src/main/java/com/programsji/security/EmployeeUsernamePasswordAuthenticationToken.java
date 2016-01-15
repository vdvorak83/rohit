package com.programsji.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class EmployeeUsernamePasswordAuthenticationToken extends
		UsernamePasswordAuthenticationToken {

	public EmployeeUsernamePasswordAuthenticationToken(Object principal,
			Object credentials) {
		super(principal, credentials);
	}

	private static final long serialVersionUID = 1L;

}
