package com.rmicp.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.wimpi.telnetd.io.terminal.ansi;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

public class CustomOncePerRequestFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (null != request.getParameter("accessKey")) {
			if ((request.getParameter("accessKey")).equals("rm")) {
				Authentication auth = SecurityContextHolder.getContext()
						.getAuthentication();
				List<GrantedAuthority> extraRoles = new ArrayList<GrantedAuthority>();

				extraRoles.add(new GrantedAuthorityImpl("Role X"));
				extraRoles.add(new GrantedAuthorityImpl("Role Y"));
				CustomAuthentication wrapper = new CustomAuthentication(auth,
						extraRoles);
				SecurityContextHolder.getContext().setAuthentication(wrapper);
			}
		}

		filterChain.doFilter(request, response);

	}
}
