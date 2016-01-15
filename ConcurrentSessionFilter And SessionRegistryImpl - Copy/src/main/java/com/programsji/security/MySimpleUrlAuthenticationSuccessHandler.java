package com.programsji.security;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class MySimpleUrlAuthenticationSuccessHandler extends
		SimpleUrlAuthenticationSuccessHandler {
	@Resource(name = "sessionRegistry")
	// @Autowired
	SessionRegistryImpl sessionRegistry;

	@Override
	protected void handle(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		super.handle(request, response, authentication);
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		response.sendRedirect("adminpage");
		// super.onAuthenticationSuccess(request, response, authentication);
	}
}
