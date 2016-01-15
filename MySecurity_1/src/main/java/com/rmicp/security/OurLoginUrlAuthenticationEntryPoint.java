package com.rmicp.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

public class OurLoginUrlAuthenticationEntryPoint extends
		LoginUrlAuthenticationEntryPoint {

	RedirectStrategy redirectStratey = new DefaultRedirectStrategy();

	public OurLoginUrlAuthenticationEntryPoint(String loginurl) {
		super(loginurl);
	}

	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		//redirectStratey.sendRedirect(request, response, getLoginFormUrl());
		request.getRequestDispatcher(getLoginFormUrl()).forward(request, response);
	}
}
