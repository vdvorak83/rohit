package com.programsji.security;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Component;

@Component
public class CustomPostAuthenticationChecks implements UserDetailsChecker {

	@Override
	public void check(UserDetails toCheck) {
		User user = (User) toCheck;
		if (user.isNeedsEmailAuthentication()) {
			user.getAuthorities().clear();
			ArrayList<GrantedAuthority> temp = new ArrayList<GrantedAuthority>();
			temp.add(new SimpleGrantedAuthority("ROLE_NEEDS_EMAIL_AUTH"));
			user.setRole(temp);
		} else {
			user.setNeedsEmailAuthentication(false);
			ArrayList<GrantedAuthority> temp = new ArrayList<GrantedAuthority>();
			temp.add(new SimpleGrantedAuthority("ROLE_USER"));
			user.setRole(temp);
		}
	}
}