package com.programsji.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class UsernamePasswordAuthenticationTokenForRawdata extends
		UsernamePasswordAuthenticationToken {

	public UsernamePasswordAuthenticationTokenForRawdata(Object principal,
			Object credentials) {
		super(principal, credentials);
	}
}
