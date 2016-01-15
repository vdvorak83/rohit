package com.rmicp.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/* TO KNOW, WHETHER SESSION WAS EXPIRED oR URL WAS WRONG IN AJAX REQUESTS*/
public class MyAjaxAwareAuthenticationEntryPoint extends
		LoginUrlAuthenticationEntryPoint {

	public MyAjaxAwareAuthenticationEntryPoint(final String loginFormUrl) {
		super(loginFormUrl);
	}

	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
			// response.sendError(403, "Forbidden");
			response.getWriter().write("Sorry !! User js not Authorized");
		} else {
			super.commence(request, response, authException);
		}
	}
}
