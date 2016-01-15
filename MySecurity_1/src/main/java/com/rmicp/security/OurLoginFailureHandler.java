package com.rmicp.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class OurLoginFailureHandler extends
		SimpleUrlAuthenticationFailureHandler {
	public OurLoginFailureHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		// request.getSession(false).setAttribute(
		// WebAttributes.AUTHENTICATION_EXCEPTION,
		// "Sorry !! A Problem Occured While Authenticated");
		exception = new BadCredentialsException(
				"Sorry !! A Problem Occured While Authenticated");
		super.onAuthenticationFailure(request, response, exception);
	}

}
