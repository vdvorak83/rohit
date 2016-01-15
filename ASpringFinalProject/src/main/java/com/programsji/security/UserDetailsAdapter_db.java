package com.programsji.security;
//package com.programsji.security;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.GrantedAuthorityImpl;
//import org.springframework.stereotype.Service;
//
//@Service("userDetailsAdapter_db")
//public class UserDetailsAdapter_db extends
//		org.springframework.security.core.userdetails.User {
//
//	private final Long id;
//
//	public UserDetailsAdapter_db(User_db userEntity) {
//		super(userEntity.getUsername(), userEntity.getPassword(), userEntity
//				.isEnabled(), true, true, true, toAutorities(userEntity
//				.getRole()));
//		this.id = userEntity.id;
//
//	}
//
//	private static Collection<GrantedAuthority> toAutorities(
//			List<String> authorities) {
//		Collection<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
//		for (String authority : authorities) {
//			authorityList.add(new GrantedAuthorityImpl(authority));
//		}
//		return authorityList;
//	}
//
//	public Long getId() {
//		return id;
//	}
//}
