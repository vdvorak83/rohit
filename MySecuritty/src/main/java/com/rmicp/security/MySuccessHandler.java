package com.rmicp.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class MySuccessHandler extends
		SavedRequestAwareAuthenticationSuccessHandler {
	@Override
	protected String determineTargetUrl(HttpServletRequest request,
			HttpServletResponse response) {
		return super.determineTargetUrl(request, response);

	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		getRedirectStrategy().sendRedirect(request, response, "/customer/home");
	}

}
