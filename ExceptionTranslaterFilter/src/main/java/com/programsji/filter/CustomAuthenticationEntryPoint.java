package com.programsji.filter;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

public class CustomAuthenticationEntryPoint extends
		LoginUrlAuthenticationEntryPoint {
	public CustomAuthenticationEntryPoint(final String loginFormUrl) {
		super(loginFormUrl);
	}

	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {

		if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
			response.sendError(403, "Not an authenticated User");
		} else {
			request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,
					authException.getMessage());
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(getLoginFormUrl());
			dispatcher.forward(request, response);
		}
	}
}