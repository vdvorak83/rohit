package com.programsji.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class MyAuthenticationFailureHandler extends
		SimpleUrlAuthenticationFailureHandler
// implements AuthenticationFailureHandler
{
	String urlPrefix;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private String fromusernamekey = UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY;

	public MyAuthenticationFailureHandler() {
		// TODO Auto-generated constructor stub
	}

	public MyAuthenticationFailureHandler(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response,
			AuthenticationException authenticationException)
			throws IOException, ServletException {
		saveException(request, authenticationException);
		String username = request.getParameter(fromusernamekey);
		String redirectUrl = urlPrefix + "?username" + username;
		System.out.println("Login Was Not Successful");
		// response.sendRedirect("login");
		redirectStrategy.sendRedirect(request, response, redirectUrl);
	}

	@Override
	protected RedirectStrategy getRedirectStrategy() {
		// TODO Auto-generated method stub
		return super.getRedirectStrategy();
	}

	public String getFromusernamekey() {
		return fromusernamekey;
	}

	public String getUrlPrefix() {
		return urlPrefix;
	}

	@Override
	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		// TODO Auto-generated method stub
		super.setRedirectStrategy(redirectStrategy);
	}

	public void setFromusernamekey(String fromusernamekey) {
		this.fromusernamekey = fromusernamekey;
	}

	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}

}
