package com.programsji.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

//@Component
public class CustomLoginUrlAuthenticationEntryPoint extends
		LoginUrlAuthenticationEntryPoint {

	public CustomLoginUrlAuthenticationEntryPoint() {
		// TODO Auto-generated constructor stub
	}

	public CustomLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
		super(loginFormUrl);
		
	}

	public void commence(
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response,
			org.springframework.security.core.AuthenticationException authException)
			throws java.io.IOException, javax.servlet.ServletException {

		super.commence(request, response, authException);
		// response.sendError(HttpServletResponse.SC_FOUND, "Unauthorized");
		//response.sendRedirect("../login");

	}
}
