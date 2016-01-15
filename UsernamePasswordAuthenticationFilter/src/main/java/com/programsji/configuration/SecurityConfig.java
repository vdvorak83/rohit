package com.programsji.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.programsji.security.CustomAuthenticationProvider;
import com.programsji.security.CustomSuccessHandler;
import com.programsji.security.CustomUsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**", "/css/**", "/theme/**").and()
				.debug(true);
	}

	@Bean
	public CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter()
			throws Exception {
		CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter = new CustomUsernamePasswordAuthenticationFilter();
		customUsernamePasswordAuthenticationFilter
				.setAuthenticationManager(authenticationManagerBean());
		customUsernamePasswordAuthenticationFilter
				.setAuthenticationSuccessHandler(customSuccessHandler());
		return customUsernamePasswordAuthenticationFilter;
	}

	@Bean
	public CustomSuccessHandler customSuccessHandler() {
		CustomSuccessHandler customSuccessHandler = new CustomSuccessHandler();
		
		return customSuccessHandler;
	}

	@Bean
	public CustomAuthenticationProvider customAuthenticationProvider() {
		CustomAuthenticationProvider customAuthenticationProvider = new CustomAuthenticationProvider();
		return customAuthenticationProvider;
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		List<AuthenticationProvider> authenticationProviderList = new ArrayList<AuthenticationProvider>();
		authenticationProviderList.add(customAuthenticationProvider());
		AuthenticationManager authenticationManager = new ProviderManager(
				authenticationProviderList);
		return authenticationManager;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/reportspage").hasRole("REPORT")
				.antMatchers("/rawdatapage").hasRole("RAWDATA").anyRequest()
				.hasRole("USER").and().formLogin().loginPage("/login")
				.failureUrl("/login?error")
				.loginProcessingUrl("/j_spring_security_check")
				.passwordParameter("j_password")
				.usernameParameter("j_username").defaultSuccessUrl("/")
				.permitAll().and().httpBasic().and().logout()
				.logoutSuccessUrl("/login?logout").and().csrf().disable()
				.addFilter(customUsernamePasswordAuthenticationFilter());
	}

}
