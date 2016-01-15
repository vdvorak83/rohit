package com.programsji.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

public class CustomWebAuthenticationDetailsSource extends
		WebAuthenticationDetailsSource {

	@Override
	public CustomWebAuthenticationDetail buildDetails(HttpServletRequest context) {
		return new CustomWebAuthenticationDetail(context);
	}

}
