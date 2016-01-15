package com.rmicp.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

	List<Privilige> priviliges = new ArrayList<Privilige>();
	String name;

	@Override
	public String getAuthority() {
		return name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role() {

	}

	public Role(String rolename) {
		this.name = rolename;
	}

	public List<Privilige> getPriviliges() {
		return priviliges;
	}

	public void setPriviliges(List<Privilige> priviliges) {
		this.priviliges = priviliges;
	}

}
