package com.programsji.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.programsji.entrypoint.CustomBasicAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**", "/css/**", "/theme/**").and()
				.debug(true);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.exceptionHandling()
				.authenticationEntryPoint(customBasicAuthenticationEntryPoint())
				.and().authorizeRequests().anyRequest().hasRole("USER").and()
				.httpBasic().realmName("Please enter username and password");
	}

	@Bean
	CustomBasicAuthenticationEntryPoint customBasicAuthenticationEntryPoint() {
		CustomBasicAuthenticationEntryPoint customBasicAuthenticationEntryPoint = new CustomBasicAuthenticationEntryPoint();
		customBasicAuthenticationEntryPoint.setRealmName("Programsji");
		return customBasicAuthenticationEntryPoint;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("john").password("john")
				.roles("USER");
	}
}
