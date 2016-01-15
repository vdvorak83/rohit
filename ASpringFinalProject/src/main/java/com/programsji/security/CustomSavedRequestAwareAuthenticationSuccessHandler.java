package com.programsji.security;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomSavedRequestAwareAuthenticationSuccessHandler extends
		SavedRequestAwareAuthenticationSuccessHandler {
	@Override
	protected String determineTargetUrl(HttpServletRequest request,
			HttpServletResponse response) {
		String targetUrl = "";
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) securityContext
				.getAuthentication().getAuthorities();

		GrantedAuthority a = (GrantedAuthority) authorities.toArray()[0];

		a.getAuthority();
		// //////////IMPORTANT/////////////////////////////
		// if (authorities.contains(new SimpleGrantedAuthority(
		// "ROLE_NEEDS_EMAIL_AUTH"))) {
		// }

		if (authorities.contains(new Role("ROLE_NEEDS_EMAIL_AUTH"))) {
			targetUrl = "/authenticate";
		} else if (authorities.contains(a)) {
			targetUrl = "/home";
			// targetUrl = super.determineTargetUrl(request, response);
		} else {
			targetUrl = super.determineTargetUrl(request, response);
		}
		return targetUrl;
	}

}
