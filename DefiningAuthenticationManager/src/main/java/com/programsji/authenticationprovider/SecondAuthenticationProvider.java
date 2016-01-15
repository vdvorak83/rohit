package com.programsji.authenticationprovider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.programsji.bo.Role;
import com.programsji.bo.User;
import com.programsji.service.UserService;

public class SecondAuthenticationProvider implements AuthenticationProvider {
	String fixeduserName = "meuser";
	String fixedpassword = "mypassword";

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		if (!username.equals(fixeduserName)) {
			throw new BadCredentialsException("User Name Not Valid");
		}

		Object obj = authentication.getDetails();

		if (!password.equals(fixedpassword)) {
			throw new BadCredentialsException("Password Not Valid");
		}
		User user = new User(username, username, password, true, true, true,
				true);

		List<Role> authorityList = new ArrayList<Role>();
		authorityList.add(new Role("ROLE_USER"));
		user.setAuthorities(authorityList);

		return new UsernamePasswordAuthenticationToken(user, password,
				authorityList);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class
				.isAssignableFrom(authentication));
	}
}