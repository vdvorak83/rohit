package com.programsji.logoutsuccesshandler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.programsji.controller.DemoController;

public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

	private static final Logger logger = LoggerFactory
			.getLogger(DemoController.class);

	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String Username = authentication.getName();
		String refererUrl = request.getHeader("Referer");
		logger.info("User " + Username + " Logged out from " + refererUrl
				+ " page");
		super.handle(request, response, authentication);
	}
}
