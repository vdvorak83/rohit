package com.programsji.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.UrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.programsji.security.CustomApplicationListener;
import com.programsji.security.CustomAuthenticationProvider;
import com.programsji.security.CustomAuthenticationProviderForCustomer;
import com.programsji.security.CustomAuthenticationProviderForEmployee;
import com.programsji.security.CustomLoginUrlAuthenticationEntryPoint;
import com.programsji.security.CustomLogoutFilter;
import com.programsji.security.CustomLogoutSuccessHandler;
import com.programsji.security.CustomOncePerRequestFilter;
import com.programsji.security.CustomPostAuthenticationChecks;
import com.programsji.security.CustomRememberMeService;
import com.programsji.security.CustomSavedRequestAwareAuthenticationSuccessHandler;
import com.programsji.security.CustomSessionRegistryImpl;
import com.programsji.security.CustomWebAuthenticationDetailsSource;
import com.programsji.security.MyAuthenticationFailureHandler;
import com.programsji.security.MyAuthenticationFilter;
import com.programsji.security.MyAuthenticationSuccessHandler;
import com.programsji.security.UserDetailsServiceImpl_hib;
import com.programsji.security.UserService;

@EnableWebSecurity
// @Import(value = { MethodSecurityConfig.class, AclConfiguration.class })
@Import(value = { MethodSecurityConfig.class })
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**,/img/**,/js/**,/theme/**").and()
				.debug(true);
	}

	@Autowired
	CustomSavedRequestAwareAuthenticationSuccessHandler customSavedRequestAwareAuthenticationSuccessHandler;

	@Autowired
	MyAuthenticationSuccessHandler mySuccessHandler;

	@Autowired
	// UserDetailsServiceImpl_hib userService;
	UserService userService;

	@Autowired
	CustomApplicationListener customApplicationListener;

	@Autowired
	CustomSessionRegistryImpl customSessionRegistryImpl;

	@Bean
	CustomWebAuthenticationDetailsSource getCustomWebAuthenticationDetailsSource() {
		CustomWebAuthenticationDetailsSource customWebAuthenticationDetailsSource = new CustomWebAuthenticationDetailsSource();
		return customWebAuthenticationDetailsSource;
	}

	@Bean
	public CustomLoginUrlAuthenticationEntryPoint getCustomLoginUrlAuthenticationEntryPoint() {
		CustomLoginUrlAuthenticationEntryPoint customLoginUrlAuthenticationEntryPoint = new CustomLoginUrlAuthenticationEntryPoint(
				"/login");

		return customLoginUrlAuthenticationEntryPoint;
	}

	@Bean
	public CustomLogoutSuccessHandler getCustomLogoutSuccessHandler() {
		CustomLogoutSuccessHandler customLogoutSuccessHandler = new CustomLogoutSuccessHandler();
		return customLogoutSuccessHandler;
	}

	@Bean
	public CustomRememberMeService getCustomRememberMeService() {
		CustomRememberMeService customRememberMeService = new CustomRememberMeService();
		customRememberMeService.setKey("MYKEY");
		customRememberMeService.setUserDetailsService(userService);
		return customRememberMeService;
	}

	@Bean
	public CustomLogoutFilter getCustomLogoutFilter() {
		CustomLogoutFilter customLogoutSuccess = new CustomLogoutFilter(
				getCustomLogoutSuccessHandler(),
				new LogoutHandler[] { getCustomRememberMeService() });
		return customLogoutSuccess;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.exceptionHandling()
				.authenticationEntryPoint(
						getCustomLoginUrlAuthenticationEntryPoint())
				.and()
				.authorizeRequests()
				.antMatchers("/employee/**")
				// .hasAnyRole("USER", "ANONYMOUS")
				.hasAnyRole("USER")
				.and()
				.formLogin()
				.loginPage("/login")
				.failureUrl("/login?error")
				.usernameParameter("j_username")
				.passwordParameter("j_password")
				.loginProcessingUrl("/j_spring_security_check")
				.defaultSuccessUrl("/customer/home")
				.authenticationDetailsSource(
						getCustomWebAuthenticationDetailsSource())
				.and()
				.httpBasic()
				.authenticationDetailsSource(
						getCustomWebAuthenticationDetailsSource()).and()
				.logout().invalidateHttpSession(false)
				.deleteCookies("JSESSIONID").logoutSuccessUrl("/login?logout")
				.and().csrf().disable()
				.addFilter(getUsernamePasswordAuthenticationFilter())
				.addFilter(getCustomLogoutFilter()).sessionManagement()
				.invalidSessionUrl("/customer/home")
				// .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.maximumSessions(1).sessionRegistry(customSessionRegistryImpl);

		// http.authorizeRequests().antMatchers("/employee/**")
		// .hasRole("EMPLOYEE").antMatchers("/**").permitAll().and()
		// .formLogin().defaultSuccessUrl("/home", true)
		// .loginPage("/login.jsp")
		//
		// .successHandler(new MyAuthenticationSuccessHandler())
		// .failureHandler(new MyAuthenticationFailureHandler()).and()
		// // .addFilter(getUsernamePasswordAuthenticationFilter())
		// // .authenticationProvider(customAuthenticationProvider)
		// .csrf().disable();

		// http.authorizeRequests().antMatchers("/employee/**")
		// .hasRole("EMPLOYEE").antMatchers("/customer/**")
		// .hasRole("CUSTOMER").antMatchers("/**")
		// .permitAll()
		// .and()
		// .formLogin()
		// .successHandler(mySuccessHandler)
		// .loginPage("/login.jsp")
		// // .failureUrl("/login.jsp?login_error=1")
		//
		// // .defaultSuccessUrl("/employee/home", true)
		// .and().logout().invalidateHttpSession(true)
		// .logoutSuccessUrl("/login.jsp").and().httpBasic().and().csrf()
		// .disable();
		//

		// .addFilter(getAuthenticationProcessingFilterForEmployee())
		// .addFilter(getAuthenticationProcessingFilterForCustomer())

	}

	@Bean
	public UsernamePasswordAuthenticationFilter getUsernamePasswordAuthenticationFilter() {
		MyAuthenticationFilter obj = new MyAuthenticationFilter();
		obj.setAuthenticationDetailsSource(getCustomWebAuthenticationDetailsSource());
		obj.setAuthenticationManager(getAuthenticationManager());
		// customSavedRequestAwareAuthenticationSuccessHandler.setUseReferer(true);
		obj.setAuthenticationSuccessHandler(customSavedRequestAwareAuthenticationSuccessHandler);
		MyAuthenticationFailureHandler failureHandler = new MyAuthenticationFailureHandler(
				"/login");
		obj.setAuthenticationFailureHandler(failureHandler);
		return obj;
	}

	// @Bean
	// public UsernamePasswordAuthenticationFilter
	// getAuthenticationProcessingFilterForEmployee() {
	// UsernamePasswordAuthenticationFilter obj = new
	// UsernamePasswordAuthenticationFilter();
	// obj.setAuthenticationManager(getAuthenticationManagerForEmployee());
	// obj.setFilterProcessesUrl("/j_spring_security_check_for_employee");
	// return obj;
	// }
	//
	// @Bean
	// public UsernamePasswordAuthenticationFilter
	// getAuthenticationProcessingFilterForCustomer() {
	// UsernamePasswordAuthenticationFilter obj = new
	// UsernamePasswordAuthenticationFilter();
	// obj.setAuthenticationManager(getAuthenticationManagerForCustomer());
	// obj.setFilterProcessesUrl("/j_spring_security_check_for_customer");
	// return obj;
	// }

	// @Bean
	// public FilterChainProxy getFilterChainProxy() {
	// List<SecurityFilterChain> filterChains = new
	// ArrayList<SecurityFilterChain>();
	// filterChains.add(customOncePerRequestFilter);
	// FilterChainProxy fcp = new FilterChainProxy(filterChains);
	// return fcp;
	// }

	// @Autowired
	// CustomAuthenticationProviderForCustomer
	// customAuthenticationProviderForCustomer;

	@Autowired
	CustomAuthenticationProvider customAuthenticationProvider;

	//
	// @Autowired
	// CustomAuthenticationProviderForEmployee
	// customAuthenticationProviderForEmployee;
	//
	@Autowired
	CustomPostAuthenticationChecks customPostAuthenticationChecks;

	// public ProviderManager getAuthenticationManagerForCustomer() {
	//
	// List<AuthenticationProvider> l = new ArrayList<AuthenticationProvider>();
	// l.add(customAuthenticationProviderForCustomer);
	// ProviderManager pm = new ProviderManager(l);
	// return pm;
	// }
	//
	// public ProviderManager getAuthenticationManagerForEmployee() {
	// List<AuthenticationProvider> l = new ArrayList<AuthenticationProvider>();
	// l.add(customAuthenticationProviderForEmployee);
	// ProviderManager pm = new ProviderManager(l);
	// return pm;
	// }

	public ProviderManager getAuthenticationManager() {
		List<AuthenticationProvider> l = new ArrayList<AuthenticationProvider>();
		customAuthenticationProvider
				.setPostAuthenticationChecks(customPostAuthenticationChecks);
		l.add(customAuthenticationProvider);
		// l.add(getDaoAuthenticationProvider());
		// l.add(customAuthenticationProviderForCustomer);
		// l.add(customAuthenticationProviderForEmployee);
		ProviderManager pm = new ProviderManager(l);
		pm.setAuthenticationEventPublisher(new DefaultAuthenticationEventPublisher());

		return pm;
	}

	// @Autowired
	// protected void configureGlobal(AuthenticationManagerBuilder auth)
	// throws Exception {
	// // customAuthenticationProvider
	// // .setPostAuthenticationChecks(customPostAuthenticationChecks);
	// auth.authenticationProvider(customAuthenticationProvider);
	// super.configure(auth);
	// }
	//

	@Bean
	public DaoAuthenticationProvider getDaoAuthenticationProvider() {
		DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
		dap.setUserDetailsService(userService);
		dap.setPasswordEncoder(getBCryptPasswordEncoder());
		dap.setSaltSource(getReflectionSaltSource());
		return dap;
	}

	@Bean
	public PasswordEncoder getBCryptPasswordEncoder() {
		return (PasswordEncoder) new BCryptPasswordEncoder();
	}

	// @Bean(name = "md5passwordencoder")
	// public PasswordEncoder getmd5PasswordEncoder() {
	// return (PasswordEncoder) new Md5PasswordEncoder();
	// }

	@Bean(name = "saltSource")
	public SaltSource getReflectionSaltSource() {
		ReflectionSaltSource saltSource = new ReflectionSaltSource();
		saltSource.setUserPropertyToUse("salt");
		return saltSource;
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	// ///////////////////////////////VOTERS///////////////////////////////

	// @Bean
	// public AuthenticatedVoter getAuthenticatedVoter() {
	// AuthenticatedVoter av = new AuthenticatedVoter();
	// return av;
	// }
	//
	// @Bean
	// public RoleVoter getRoleVoter() {
	// RoleVoter rv = new RoleVoter();
	// rv.setRolePrefix("ROLE1_");
	// return rv;
	// }
	//
	// @Bean
	// public AccessDecisionManager getAffirmativeBased() {
	// List<AccessDecisionVoter> adv = new ArrayList<AccessDecisionVoter>();
	// adv.add(getAuthenticatedVoter());
	// adv.add(getRoleVoter());
	// AffirmativeBased ab = new AffirmativeBased(adv);
	// return ab;
	// }

}
