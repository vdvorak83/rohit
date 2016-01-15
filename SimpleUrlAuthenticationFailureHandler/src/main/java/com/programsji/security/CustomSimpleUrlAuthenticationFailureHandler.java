package com.programsji.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.programsji.controller.DemoController;

public class CustomSimpleUrlAuthenticationFailureHandler extends
		SimpleUrlAuthenticationFailureHandler {

	private static final Logger logger = LoggerFactory
			.getLogger(DemoController.class);

	private String url;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private String fromusernamekey = UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;

	public CustomSimpleUrlAuthenticationFailureHandler(String url) {
		this.url = url;
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response,
			AuthenticationException authenticationException)
			throws IOException, ServletException {
		saveException(request, authenticationException);
		String username = request.getParameter(fromusernamekey);
		String redirectUrl = url + "?username=" + username;
		logger.info("Login Was Not Successful");
		redirectStrategy.sendRedirect(request, response, redirectUrl);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	public String getFromusernamekey() {
		return fromusernamekey;
	}

	public void setFromusernamekey(String fromusernamekey) {
		this.fromusernamekey = fromusernamekey;
	}
}