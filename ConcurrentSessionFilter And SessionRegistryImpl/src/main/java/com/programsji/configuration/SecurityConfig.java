package com.programsji.configuration;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventPublicationInterceptor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.programsji.service.UserService;

@Configuration
@EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter implements
		ApplicationListener<AuthenticationSuccessEvent> {

	@Autowired
	UserService userSevice;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**", "/css/**", "/theme/**").and()
				.debug(true);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.addFilterAfter(authenticationFilter(),
		// SecurityContextPersistenceFilter.class);
		// http.addFilterBefore(concurrentSessionFilter(),
		// SecurityContextPersistenceFilter.class);

		http.addFilter(authenticationFilter());
		http.addFilter(concurrentSessionFilter());
		http.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
				.sessionAuthenticationStrategy(
						concurrentSessionControlStrategy()).maximumSessions(3)
				.sessionRegistry(sessionRegistryImpl());
		http.authorizeRequests().antMatchers("/login").permitAll().anyRequest()
				.hasRole("USER").and().csrf().disable();
		// .formLogin()
		// .loginPage("/login").failureUrl("/login?error")
		// .loginProcessingUrl("/j_spring_security_check")
		// .passwordParameter("j_password")
		// .usernameParameter("j_username");

		// .and().authorizeRequests().antMatchers("/visitorpage")
		// .permitAll().anyRequest().hasRole("USER").and()
		// .formLogin()
		// .loginPage("/login").failureUrl("/login?error")
		// .loginProcessingUrl("/j_spring_security_check")
		// .passwordParameter("j_password")
		// .usernameParameter("j_username").defaultSuccessUrl("/demo")
		// .permitAll().and().httpBasic().and().logout()
		// .logoutSuccessUrl("/login?logout").and().csrf().disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("john").password("john")
				.roles("USER");
	}

	public static SessionRegistryImpl obj;

	@Bean(name = "sessionRegistry")
	public SessionRegistryImpl sessionRegistryImpl() {
		if (obj == null) {
			obj = new SessionRegistryImpl();
		}
		return obj;
	}

	@Bean(name = "sas")
	public ConcurrentSessionControlStrategy concurrentSessionControlStrategy() {
		ConcurrentSessionControlStrategy concurrentSessionControlStrategy = new ConcurrentSessionControlStrategy(
				sessionRegistryImpl());
		return concurrentSessionControlStrategy;
	}

	@Bean(name = "concurrencyFilter")
	public ConcurrentSessionFilter concurrentSessionFilter() {
		ConcurrentSessionFilter concurrentSessionFilter = new ConcurrentSessionFilter(
				sessionRegistryImpl());
		return concurrentSessionFilter;
	}

	@Bean(name = "authenticationProvider")
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userSevice);
		return authenticationProvider;
	}

	@Autowired
	AnnotationConfigWebApplicationContext aep;// = new

	// AnnotationConfigWebApplicationContext();

	@Autowired
	ObjectPostProcessor<Object> objectPostProcessor;

	@Bean(name = "authenticationManager")
	public AuthenticationManager authenticationManager() throws Exception {
		List<AuthenticationProvider> list = new ArrayList<AuthenticationProvider>();
		list.add(authenticationProvider());
		ProviderManager authenticationManager = new ProviderManager(list);
		authenticationManager.afterPropertiesSet();

		DefaultAuthenticationEventPublisher eventPublisher = objectPostProcessor
				.postProcess(new DefaultAuthenticationEventPublisher());
		authenticationManager.setAuthenticationEventPublisher(eventPublisher);
		return authenticationManager;
	}

	@Bean
	public AuthenticationSuccess success() {
		AuthenticationSuccess a = new AuthenticationSuccess();
		return a;
	}

	@Bean(name = "authenticationFilter")
	public MyUsernamePasswordAuthenticationFilter authenticationFilter()
			throws Exception {
		MyUsernamePasswordAuthenticationFilter filter = new MyUsernamePasswordAuthenticationFilter();
		filter.setSessionAuthenticationStrategy(concurrentSessionControlStrategy());
		filter.setAuthenticationManager(authenticationManager());
		filter.setAuthenticationSuccessHandler(success());
		return filter;
	}

	public SecurityConfig() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		// TODO Auto-generated method stub

	}

	// @Override
	// public void init(WebSecurity web) throws Exception {
	// // TODO Auto-generated method stub
	//
	// super.init(web);
	// }
	//
	// @Override
	// public void setApplicationContext(ApplicationContext context) {
	// // TODO Auto-generated method stub
	// super.setApplicationContext(context);
	// }

}
