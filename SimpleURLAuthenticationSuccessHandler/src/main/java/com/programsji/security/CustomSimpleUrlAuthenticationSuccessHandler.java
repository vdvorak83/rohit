package com.programsji.security;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CustomSimpleUrlAuthenticationSuccessHandler extends
		SimpleUrlAuthenticationSuccessHandler {

	private static final Logger logger = LoggerFactory
			.getLogger(CustomSimpleUrlAuthenticationSuccessHandler.class);

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		Set<String> roles = AuthorityUtils.authorityListToSet(authentication
				.getAuthorities());
		if (roles.contains("ROLE_ADMIN")) {
			getRedirectStrategy().sendRedirect(request, response,
					"/demoadminpage");
		} else if (roles.contains("ROLE_USER")) {
			getRedirectStrategy().sendRedirect(request, response,
					"/demouserpage");
		} else {
			super.onAuthenticationSuccess(request, response, authentication);
			return;
		}
	}

}