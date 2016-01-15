package com.programsji.security;

import java.io.IOException;
import java.util.Collection;

import javax.jms.IllegalStateException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationSuccessHandler implements
		AuthenticationSuccessHandler {
	RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
	}

	private void handle(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException {
		saveCookie("language", "en", response);
		String targetUrl = determineTargetUrl(authentication);
		if (response.isCommitted()) {
			System.out
					.println("Response is Already Commited, So Can't Redirect to "
							+ targetUrl);
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	private String determineTargetUrl(Authentication authentication) {
		Collection<? extends GrantedAuthority> authorities = authentication
				.getAuthorities();
		for (GrantedAuthority authority : authorities) {
			if (authority.getAuthority().equals("ROLE_USER")) {
				return "/home";
			} else {
				return "/adminhome";
			}
		}
		return null;
	}

	private void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	public void saveCookie(String cookieName, String cookieValue,
			HttpServletResponse response) {
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(2592000);
		response.addCookie(cookie);
	}

}
