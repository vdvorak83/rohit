package com.programsji.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class CustomAuthentication implements Authentication {

	private Authentication original;
	private Collection<? extends GrantedAuthority> extraRoles;

	public CustomAuthentication(Authentication original,
			Collection<? extends GrantedAuthority> extraRoles) {
		this.original = original;
		this.extraRoles = extraRoles;
	}

	@Override
	public String getName() {
		return original.getName();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<? extends GrantedAuthority> originalRoles = original
				.getAuthorities();

		ArrayList<GrantedAuthority> temp = new ArrayList<GrantedAuthority>(
				originalRoles.size() + extraRoles.size());
		temp.addAll(originalRoles);
		temp.addAll(extraRoles);
		return Collections.unmodifiableList(temp);

	}

	@Override
	public Object getCredentials() {
		return original.getCredentials();
	}

	@Override
	public Object getDetails() {
		return original.getDetails();
	}

	@Override
	public Object getPrincipal() {
		return original.getPrincipal();
	}

	@Override
	public boolean isAuthenticated() {
		return original.isAuthenticated();
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated)
			throws IllegalArgumentException {
		original.setAuthenticated(isAuthenticated);

	}

}
