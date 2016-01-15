package com.programsji.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class CustomWebAuthenticationDetail extends WebAuthenticationDetails {

	private static final long serialVersionUID = 1L;
	private final String referer;

	public CustomWebAuthenticationDetail(HttpServletRequest request) {
		super(request);
		this.referer = request.getHeader("Referer");
	}

	public String getReferer() {
		return referer;
	}

}
