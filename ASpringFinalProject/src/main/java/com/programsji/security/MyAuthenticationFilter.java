package com.programsji.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class MyAuthenticationFilter extends
		UsernamePasswordAuthenticationFilter {

	UsernamePasswordAuthenticationToken authRequest = null;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		// if
		// ("customer".equals(request.getParameter("radioAuthenticationType")))
		// {
		// authRequest = new CustomerUsernamePasswordAuthenticationToken(
		// request.getParameter("j_username"),
		// request.getParameter("j_password"));
		// } else {
		// authRequest = new EmployeeUsernamePasswordAuthenticationToken(
		// request.getParameter("j_username"),
		// request.getParameter("j_password"));
		// }
		// setDetails(request, authRequest);
		// return getAuthenticationManager().authenticate(authRequest);
		return super.attemptAuthentication(request, response);
	}
}
