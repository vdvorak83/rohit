package com.rmicp.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class MyUsernamePasswordAuthenticationFilter extends
		UsernamePasswordAuthenticationFilter {
	UsernamePasswordAuthenticationToken authRequest = null;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		String username = request
				.getParameter(SPRING_SECURITY_FORM_USERNAME_KEY);
		String password = request
				.getParameter(SPRING_SECURITY_FORM_PASSWORD_KEY);

		authRequest = new UsernamePasswordAuthenticationToken(username,
				password);

		setDetails(request, authRequest);

		return getAuthenticationManager().authenticate(authRequest);
		// return super.attemptAuthentication(request, response);
	}
}
