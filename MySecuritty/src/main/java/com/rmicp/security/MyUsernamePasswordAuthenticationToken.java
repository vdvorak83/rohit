package com.rmicp.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class MyUsernamePasswordAuthenticationToken extends
		UsernamePasswordAuthenticationToken {

	public MyUsernamePasswordAuthenticationToken(Object principal,
			Object credentials) {
		super(principal, credentials);

	}

}
