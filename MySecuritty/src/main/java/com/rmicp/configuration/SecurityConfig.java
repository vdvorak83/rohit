package com.rmicp.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.header.HeaderWriterFilter;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.accept.ContentNegotiationStrategy;

import com.rmicp.bo.Permission;
import com.rmicp.security.MyAjaxAwareAuthenticationEntryPoint;
import com.rmicp.security.MyAuthenticationProvider;
import com.rmicp.security.MyFailureHandler;
import com.rmicp.security.MyFilterInvocationSecurityMetadataSource;
import com.rmicp.security.MyHttp403ForbiddenEntryPoint;
import com.rmicp.security.MyGenericFilter;
import com.rmicp.security.MyLogoutHandler;
import com.rmicp.security.MyPermissionEvaluator;
import com.rmicp.security.MySuccessHandler;
import com.rmicp.security.MyUserDetailsChecker;
import com.rmicp.security.MyUsernamePasswordAuthenticationFilter;
import com.rmicp.service.UserDetailService;

@Configuration
@EnableWebSecurity
@Import({ GlobalMethodSecurityConfig.class })
// @EnableGlobalMethodSecurity(jsr250Enabled = true, prePostEnabled = true,
// securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailService userDetailService;

	@Bean
	public SessionRegistry getSessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Bean
	public MyUsernamePasswordAuthenticationFilter getMyUsernamePasswordAuthenticationFilter()
			throws Exception {
		MyUsernamePasswordAuthenticationFilter obj = new MyUsernamePasswordAuthenticationFilter();
		obj.setAuthenticationManager(getAuthenticationManager());
		obj.setRememberMeServices(getTokenBasedRememberMeServices());
		obj.setAuthenticationFailureHandler(getMyFailureHandler());
		obj.setAuthenticationSuccessHandler(getMySuccessHandler());
		return obj;
	}

	@Bean
	public MyFailureHandler getMyFailureHandler() {
		return new MyFailureHandler();
	}

	@Bean
	public MySuccessHandler getMySuccessHandler() {
		return new MySuccessHandler();
	}

	@Bean
	public ConcurrentSessionFilter getConcurrentSessionFilter() {
		return new ConcurrentSessionFilter(getSessionRegistry());
	}

	@Bean
	public AuthenticationProvider getAuthenticationProvider() {
		MyAuthenticationProvider ap = new MyAuthenticationProvider();
		// DaoAuthenticationProvider ap = new DaoAuthenticationProvider();
		// ap.setUserDetailsService(userDetailService);
		ap.setPostAuthenticationChecks(getMyUserDetailChecker());
		ap.setPreAuthenticationChecks(getMyUserDetailChecker());
		return ap;
	}

	public AccessDeniedHandler getAccessDeniedHandler() {
		AccessDeniedHandlerImpl accessDeniedHandler = new AccessDeniedHandlerImpl();
		accessDeniedHandler.setErrorPage("/home1");
		return accessDeniedHandler;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**,/img/**,/js/**").and().debug(true);
		// super.configure(web);
	}

	@Bean
	public AuthenticationEntryPoint getLoginUrlAuthenticationEntryPoint() {
		AuthenticationEntryPoint authenticationentrypoint = new LoginUrlAuthenticationEntryPoint(
				"/home1");
		return authenticationentrypoint;
	}

	// @Bean
	// List filterChains() {
	// List simpleFilters = new ArrayList();
	// simpleFilters.add(encodingFilter());
	// return simpleFilters;
	// }

	// @Bean
	// public FilterChainProxy getFilterChainProxy() {
	// FilterChainProxy filterChainProxy = new FilterChainProxy(filterChains());
	// return filterChainProxy;
	// }

	// /////////////////////REMEMBER ME SERVICES////////////////////////////

	@Bean
	public RememberMeAuthenticationFilter getRememberMeAuthenticationFilter()
			throws Exception {
		RememberMeAuthenticationFilter obj = new RememberMeAuthenticationFilter(
				getAuthenticationManager(), getTokenBasedRememberMeServices());
		return obj;
	}

	@Bean
	public TokenBasedRememberMeServices getTokenBasedRememberMeServices() {
		TokenBasedRememberMeServices tokenBasedRememberMeServices = new TokenBasedRememberMeServices(
				"mykey", userDetailService);
		return tokenBasedRememberMeServices;
	}

	@Bean
	public RememberMeAuthenticationProvider getRemembermeAuthenticationProvider() {
		RememberMeAuthenticationProvider authenticationProvider = new RememberMeAuthenticationProvider(
				"mykey");
		return authenticationProvider;

	}

	@Bean
	public MyHttp403ForbiddenEntryPoint getHttp403ForbiddenEntryPoint() {
		MyHttp403ForbiddenEntryPoint obj = new MyHttp403ForbiddenEntryPoint();
		obj.setNewUrl("http403forbiddenentrypoint");
		return obj;
	}

	@Bean
	public MyAjaxAwareAuthenticationEntryPoint getMyAjaxAwareAuthenticationEntryPoint() {
		MyAjaxAwareAuthenticationEntryPoint obj = new MyAjaxAwareAuthenticationEntryPoint(
				"/login");
		// obj.setNewUrl("http403forbiddenentrypoint");
		return obj;
	}

	@Bean
	public MyUserDetailsChecker getMyUserDetailChecker() {
		MyUserDetailsChecker myUserDetailsChecker = new MyUserDetailsChecker();
		return myUserDetailsChecker;
	}

	@Bean
	public MyLogoutHandler getMyLogoutHandler() {
		MyLogoutHandler myLogoutHandler = new MyLogoutHandler();
		return myLogoutHandler;
	}

	// @Bean
	// public MyLogoutFilter getMyLogoutFilter() {
	// LogoutHandler[] logoutHandler = new LogoutHandler[1];
	// logoutHandler[0] = getMyLogoutHandler();
	// MyLogoutFilter m = new MyLogoutFilter("/login?logout=true",
	// logoutHandler);
	// return m;
	// }

	@Bean
	public MyGenericFilter getMyLogoutFilter() {
		MyGenericFilter m = new MyGenericFilter();
		return m;
	}

	// /////////////////////REMEMBER ME SERVICES [END]//////////////////////
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.exceptionHandling()
				.authenticationEntryPoint(getHttp403ForbiddenEntryPoint())
				.authenticationEntryPoint(getLoginUrlAuthenticationEntryPoint())
				.authenticationEntryPoint(
						getMyAjaxAwareAuthenticationEntryPoint())

				.accessDeniedHandler(getAccessDeniedHandler())
				.and()
				.authorizeRequests()
				.antMatchers("/home")
				.hasAnyRole("USER")
				// .antMatchers("/employee/home")
				// .hasAnyRole("ADMIN")
				.antMatchers("/home1")
				.permitAll()
				.antMatchers("/")
				.permitAll()
				.and()
				.formLogin()
				.loginPage("/login")
				.passwordParameter("j_password")
				.usernameParameter("j_username")
				.loginProcessingUrl("/j_spring_security_check")
				.and()
				// .rememberMe()
				// .rememberMeServices(getTokenBasedRememberMeServices())
				// .useSecureCookie(true)
				// .and()
				// .authenticationProvider(getAuthenticationProvider())
				.csrf()
				.disable()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
				.and()
				.addFilter(getMyUsernamePasswordAuthenticationFilter())
				// .addFilter(getRememberMeAuthenticationFilter())
				.addFilter(getFilterSecurityInterceptor())
				.addFilterAfter(getMyLogoutFilter(),
						RememberMeAuthenticationFilter.class).httpBasic()

				.and()
				.logout()
				// .addLogoutHandler(getMyLogoutHandler())
				.and()
				// .addFilterBefore(getConcurrentSessionFilter(),
				// SecurityContextPersistenceFilter.class)
				.sessionManagement().maximumSessions(1)
				.sessionRegistry(getSessionRegistry());
	}

	@Bean
	public AuthenticationManager getAuthenticationManager() throws Exception {
		List<AuthenticationProvider> authenticationProviders = new ArrayList<AuthenticationProvider>();
		authenticationProviders.add(getAuthenticationProvider());
		ProviderManager am = new ProviderManager(authenticationProviders);
		am.setAuthenticationEventPublisher(new DefaultAuthenticationEventPublisher());
		return am;
		// return super.authenticationManagerBean();
	}

	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.authenticationProvider(getAuthenticationProvider());
		// customAuthenticationProvider
		// .setPostAuthenticationChecks(customPostAuthenticationChecks);
		// auth.authenticationProvider(customAuthenticationProvider);
		super.configure(auth);
	}

	@Bean(name = "MyLastFilter")
	public FilterSecurityInterceptor getFilterSecurityInterceptor()
			throws Exception {
		FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
		filterSecurityInterceptor
				.setAuthenticationManager(getAuthenticationManager());
		filterSecurityInterceptor
				.setAccessDecisionManager(getAccessDecisionManager());
		filterSecurityInterceptor
				.setSecurityMetadataSource(getSecurityMetadataSource());
		return filterSecurityInterceptor;
	}

	@Bean
	public AccessDecisionManager getAccessDecisionManager() {
		List<AccessDecisionVoter> accessDecisionVoterlist = new ArrayList<AccessDecisionVoter>();
		RoleVoter roleVoter = new RoleVoter();
		AuthenticatedVoter authenticatedVoter = new AuthenticatedVoter();
		accessDecisionVoterlist.add(roleVoter);
		accessDecisionVoterlist.add(authenticatedVoter);
		AccessDecisionManager accessDecisionManager = new AffirmativeBased(
				accessDecisionVoterlist);
		return accessDecisionManager;
	}

	@Bean
	public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
		LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();
		ConfigAttribute ca = new org.springframework.security.access.SecurityConfig(
				"ROLE_USER");
		List<ConfigAttribute> list = new ArrayList<ConfigAttribute>();
		list.add(ca);
		requestMap.put(new AntPathRequestMatcher("/employee/home"), list);
		MyFilterInvocationSecurityMetadataSource securityMetadataSource = new MyFilterInvocationSecurityMetadataSource(
				requestMap);
		return securityMetadataSource;
	}
}
