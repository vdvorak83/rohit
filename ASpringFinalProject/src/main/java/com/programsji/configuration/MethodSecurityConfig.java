package com.programsji.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

//	@Override
//	protected MethodSecurityExpressionHandler createExpressionHandler() {
//		return getownExpressionHandler();
//	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		// TODO Auto-generated method stub
		super.configure(auth);
	}

	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		// auth.authenticationProvider(customAuthenticationProvider);

		auth.inMemoryAuthentication().withUser("rohit").password("malhotra")
				.roles("USER");
		super.configure(auth);
	}

//	@Bean
//	public DefaultMethodSecurityExpressionHandler getownExpressionHandler() {
//		DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
//		expressionHandler
//				.setPermissionEvaluator(getCustomPermissionEvaluator());
//		expressionHandler.setRoleHierarchy(getRoleHierarchyImpl());
//		return expressionHandler;
//	}

	public RoleHierarchyImpl getRoleHierarchyImpl() {
		RoleHierarchyImpl roleHierarchyImpl = new RoleHierarchyImpl();
		roleHierarchyImpl
				.setHierarchy("ROLE_USER>ROLE_CUSTOMER,ROLE_CUSTOMER>ROLE_EMPLOYEE");
		return roleHierarchyImpl;
	}

//	@Bean
//	public CustomPermissionEvaluator getCustomPermissionEvaluator() {
//		CustomPermissionEvaluator customPermissionEvaluator = new CustomPermissionEvaluator();
//		return customPermissionEvaluator;
//	}

}
