package com.programsji.security;
//package com.programsji.security;
//
//import java.io.Serializable;
//import java.util.Collection;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.springframework.security.access.PermissionEvaluator;
//import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//
//public class CustomPermissionEvaluator implements PermissionEvaluator {
//
//	@Resource(name = "permissionsMap")
//	private Map permissionMap;
//	@Resource(name = "roleHierarchy")
//	private RoleHierarchy roleHierarchy;
//
//	@Override
//	public boolean hasPermission(Authentication authentication,
//			Object targetDomainObject, Object permission) {
//		if (authentication != null && permission instanceof String) {
//			String role = getRole(authentication);
//			return hasPermission(role, permission, targetDomainObject);
//		}
//		return false;
//	}
//
//	@Override
//	public boolean hasPermission(Authentication authentication,
//			Serializable targetId, String targetType, Object permission) {
//		return false;
//	}
//
//	private Boolean hasPermission(String role, Object permission, Object domain) {
//
//		if (permissionMap.containsKey(role)) {
//			Permission userPermission = (Permission) permissionMap.get(role);
//			for (String action : userPermission.getObjects().get(
//					domain.getClass().getName())) {
//				if (action.equals(permission)) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}
//
//	private String getRole(Authentication authentication) {
//		String highestRole = null;
//		try {
//			Collection<GrantedAuthority> auths = (Collection<GrantedAuthority>) roleHierarchy
//					.getReachableGrantedAuthorities(authentication
//							.getAuthorities());
//
//			for (GrantedAuthority auth : auths) {
//				highestRole = auth.getAuthority();
//				break;
//			}
//			System.out.println("Highest role hiearchy: "
//					+ roleHierarchy
//							.getReachableGrantedAuthorities(authentication
//									.getAuthorities()));
//		} catch (Exception ex) {
//
//		}
//		return highestRole;
//	}
//}
