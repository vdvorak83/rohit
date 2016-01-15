package com.programsji.bo;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class User implements UserDetails {

	String name;
	String username;
	String password;

	boolean accountNonExpired;
	boolean accountNonLocked;
	boolean credentialsNonExpired;
	boolean enabled;
	private List<Role> authorities;

	public User() {

	}

	public List<Role> getRole() {
		return authorities;
	}
	

	public void setRole(List<GrantedAuthority> authorities) {
		for (GrantedAuthority grantedAuthority : authorities) {
			this.authorities.add((Role) grantedAuthority);
		}
	}

	public User(String name, String username, String password,
			boolean accountNonExpired, boolean accountNonLocked,
			boolean credentialsNonExpired, boolean enabled) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setAuthorities(List<Role> authorities) {
		this.authorities = authorities;
	}
}