package com.programsji.configuration;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.session.ConcurrentSessionFilter;

import com.programsji.security.CustomSessionRegistryImpl;
import com.programsji.security.MySimpleUrlAuthenticationSuccessHandler;
import com.programsji.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userSevice;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**", "/css/**", "/theme/**").and()
				.debug(true);
	}

	@Resource(name = "sessionRegistry")
	CustomSessionRegistryImpl sessionRegistryImpl;

	// @Bean(name = "sessionRegistry")
	public CustomSessionRegistryImpl sessionRegistryImpl() {
		// CustomSessionRegistryImpl sessionRegistryImpl = new
		// CustomSessionRegistryImpl();
		// return sessionRegistryImpl;
		return sessionRegistryImpl;
	}

	@Bean
	public ConcurrentSessionFilter concurrentSessionFilter() {
		ConcurrentSessionFilter concurrentSessionFilter = new ConcurrentSessionFilter(
				sessionRegistryImpl(), "/login?sessionexpired");
		return concurrentSessionFilter;
	}

	@Bean
	public ConcurrentSessionControlAuthenticationStrategy concurrentSessionControlAuthenticationStrategy() {
		ConcurrentSessionControlAuthenticationStrategy concurrentSessionControlAuthenticationStrategy = new ConcurrentSessionControlAuthenticationStrategy(
				sessionRegistryImpl());
		return concurrentSessionControlAuthenticationStrategy;
	}

	@Bean
	public UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() {
		UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter = new UsernamePasswordAuthenticationFilter();
		usernamePasswordAuthenticationFilter
				.setAuthenticationManager(authenticationManager());
		usernamePasswordAuthenticationFilter
				.setSessionAuthenticationStrategy(concurrentSessionControlAuthenticationStrategy());

		usernamePasswordAuthenticationFilter

				.setAuthenticationSuccessHandler(simpleUrlAuthenticationSuccessHandler());
		return usernamePasswordAuthenticationFilter;
	}

	@Bean
	public AuthenticationManager authenticationManager() {
		List<AuthenticationProvider> list = new ArrayList<AuthenticationProvider>();
		list.add(daoAuthenticationProvider());
		AuthenticationManager authenticationManager = new ProviderManager(list);
		return authenticationManager;
	}

	@Bean
	public MySimpleUrlAuthenticationSuccessHandler simpleUrlAuthenticationSuccessHandler() {
		MySimpleUrlAuthenticationSuccessHandler a = new MySimpleUrlAuthenticationSuccessHandler();
		return a;
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userSevice);
		return daoAuthenticationProvider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/userpage").hasRole("USER")
				.antMatchers("/adminpage").hasRole("ADMIN").and().formLogin()
				.loginPage("/login").failureUrl("/login?error")
				.loginProcessingUrl("/j_spring_security_check")
				.passwordParameter("j_password")
				.usernameParameter("j_username").permitAll()
				.successHandler(simpleUrlAuthenticationSuccessHandler()).and()
				.csrf().disable().addFilter(concurrentSessionFilter())
				.addFilter(usernamePasswordAuthenticationFilter())
				.sessionManagement().maximumSessions(1)
				.sessionRegistry(sessionRegistryImpl());
	}

}
