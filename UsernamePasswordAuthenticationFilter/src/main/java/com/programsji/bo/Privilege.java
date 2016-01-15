package com.programsji.bo;

import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

public class Privilege {
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		
		this.name = name;
	}
}
