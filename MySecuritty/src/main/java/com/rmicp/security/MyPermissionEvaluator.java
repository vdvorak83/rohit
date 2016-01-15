package com.rmicp.security;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.rmicp.bo.Permission;

@Component
public class MyPermissionEvaluator implements PermissionEvaluator {

	@Resource(name = "permissionsMap")
	private Map permissionsMap;

	@Resource(name = "roleHierarchy")
	private RoleHierarchy roleHierarchy;

	@Override
	public boolean hasPermission(Authentication authentication,
			Object targetDomainObject, Object permission) {
		String role = getRole(authentication);
		return hasPermission(role, permission, targetDomainObject);
	}

	@Override
	public boolean hasPermission(Authentication authentication,
			Serializable targetId, String targetType, Object permission) {
		// TODO Auto-generated method stub
		return false;
	}

	private String getRole(Authentication authentication) {
		String highestRole = null;

		try {
			Collection<GrantedAuthority> auths = (Collection<GrantedAuthority>) roleHierarchy
					.getReachableGrantedAuthorities(authentication
							.getAuthorities());
			for (GrantedAuthority auth : auths) {
				highestRole = auth.getAuthority();
				// break;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return highestRole;
	}

	private Boolean hasPermission(String role, Object permission, Object domain) {
		if (permissionsMap.containsKey(role)) {
			Permission userPermission = (Permission) permissionsMap.get(role);
			if (userPermission.getObjects().containsKey(
					domain.getClass().getName())) {
				for (String action : userPermission.getObjects().get(
						domain.getClass().getName())) {
					if (action.equals(permission)) {
						return true;
					}
				}
			}
		}
		return false;
	}
}