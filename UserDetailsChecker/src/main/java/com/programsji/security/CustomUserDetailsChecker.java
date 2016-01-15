package com.programsji.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

import com.programsji.bo.Role;
import com.programsji.bo.User;

public class CustomUserDetailsChecker implements UserDetailsChecker {

	@Override
	public void check(UserDetails toCheck) {
		User user = (User) toCheck;
		if (!user.isCredentialsNonExpired()) {
			user.getAuthorities().clear();
			Role authority = new Role("ROLE_TEMP");
			List<Role> authorities = new ArrayList<Role>();
			authorities.add(authority);
			user.setAuthorities(authorities);
		}
	}
}
