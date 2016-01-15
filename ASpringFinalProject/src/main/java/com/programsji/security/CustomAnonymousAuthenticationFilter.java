package com.programsji.security;
//package com.programsji.security;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
//
//public class CustomAnonymousAuthenticationFilter extends
//		AnonymousAuthenticationFilter {
//	private String key;
//	private Object principal;
//	private List<GrantedAuthority> authorities;
//
//	public CustomAnonymousAuthenticationFilter() {
//
//	}
//
//	public CustomAnonymousAuthenticationFilter(String key, Object principal,
//			List<GrantedAuthority> authorities) {
//		this.key = key;
//		this.principal = principal;
//		this.authorities = authorities;
//	}
//
//	@Override
//	protected Authentication createAuthentication(HttpServletRequest request) {
//
//		CustomAnonymousAuthenticationToken auth = new CustomAnonymousAuthenticationToken(
//				key, principal, authorities);
//		auth.setDetails(authenticationDetailsSource.buildDetails(request));
//
//		return auth;
//	}
//
//}
