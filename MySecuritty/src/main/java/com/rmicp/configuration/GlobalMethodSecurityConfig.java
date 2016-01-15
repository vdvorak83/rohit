package com.rmicp.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

import com.rmicp.bo.Permission;
import com.rmicp.security.MyPermissionEvaluator;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class GlobalMethodSecurityConfig extends
		GlobalMethodSecurityConfiguration {

	@Autowired
	MyPermissionEvaluator myPermissionEvaluator;

	@Override
	protected MethodSecurityExpressionHandler createExpressionHandler() {
		return getDefaultMethodSecurityExpressionHandler();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		super.configure(auth);
	}

	@Bean(name = "expressionHandler")
	public DefaultMethodSecurityExpressionHandler getDefaultMethodSecurityExpressionHandler() {
		DefaultMethodSecurityExpressionHandler obj = new DefaultMethodSecurityExpressionHandler();
		obj.setPermissionEvaluator(myPermissionEvaluator);
		obj.setRoleHierarchy(getRoleHierarchy());
		return obj;
	}

	@Bean(name = "roleHierarchy")
	public RoleHierarchyImpl getRoleHierarchy() {
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		roleHierarchy
				.setHierarchy("ROLE_USER > ROLE_VISITOR ROLE_USER > ROLE_CUSTOMER ROLE_ADMIN > ROLE_USER");
		return roleHierarchy;
	}

	@Bean(name = "permissionsMap")
	public Map getPermissionMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ROLE_ADMIN", getAdminPermission());
		map.put("ROLE_USER", getUserPermission());
		map.put("ROLE_VISITOR", getVisitorPermission());
		map.put("ROLE_CUSTOMER", getVisitorPermission());
		return map;
	}

	@Bean(name = "admin")
	public Permission getAdminPermission() {
		Permission admin = new Permission();
		Map<String, List<String>> objlist = new HashMap<String, List<String>>();
		List<String> valuesforadmin = new ArrayList<String>();
		valuesforadmin.add("READ");
		valuesforadmin.add("WRITE");
		objlist.put("com.rmicp.bo.AdminPost", valuesforadmin);

		List<String> valuesforpersonal = new ArrayList<String>();
		valuesforpersonal.add("READ");
		objlist.put("com.rmicp.bo.PersonalPost", valuesforpersonal);

		List<String> valuesforpublic = new ArrayList<String>();
		valuesforpublic.add("READ");
		valuesforpublic.add("WRITE");
		objlist.put("com.rmicp.bo.PublicPost", valuesforpublic);
		admin.setObjects(objlist);
		return admin;
	}

	@Bean(name = "user")
	public Permission getUserPermission() {
		Permission user = new Permission();
		Map<String, List<String>> objlist = new HashMap<String, List<String>>();

		List<String> valuesforpersonal = new ArrayList<String>();
		valuesforpersonal.add("READ");
		valuesforpersonal.add("WRITE");
		objlist.put("com.rmicp.bo.PersonalPost", valuesforpersonal);

		List<String> valuesforpublic = new ArrayList<String>();
		valuesforpublic.add("READ");
		valuesforpublic.add("WRITE");
		objlist.put("com.rmicp.bo.PublicPost", valuesforpublic);
		user.setObjects(objlist);
		return user;
	}

	@Bean(name = "visitor")
	public Permission getVisitorPermission() {
		Permission visitor = new Permission();
		Map<String, List<String>> objlist = new HashMap<String, List<String>>();
		List<String> valuesforpublic = new ArrayList<String>();
		valuesforpublic.add("READ");
		valuesforpublic.add("WRITE");
		objlist.put("com.rmicp.bo.PublicPost", valuesforpublic);
		visitor.setObjects(objlist);
		return visitor;
	}
}
