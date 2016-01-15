package com.programsji.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class UsernamePasswordAuthenticationTokenForReports extends
		UsernamePasswordAuthenticationToken {
	public UsernamePasswordAuthenticationTokenForReports(Object principal,
			Object credentials) {
		super(principal, credentials);
	}
}
