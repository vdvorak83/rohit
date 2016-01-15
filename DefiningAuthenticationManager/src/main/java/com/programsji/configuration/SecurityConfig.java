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

import com.programsji.authenticationprovider.FirstAuthenticationProvider;
import com.programsji.authenticationprovider.SecondAuthenticationProvider;

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
		http.authorizeRequests().anyRequest().hasRole("USER").and().formLogin()
				.defaultSuccessUrl("/demo").and().csrf().disable();

	}

	@Bean
	public FirstAuthenticationProvider firstAuthenticationProvider() {
		FirstAuthenticationProvider authenticationProvider = new FirstAuthenticationProvider();
		return authenticationProvider;
	}

	@Bean
	public SecondAuthenticationProvider secondAuthenticationProvider() {
		SecondAuthenticationProvider authenticationProvider = new SecondAuthenticationProvider();
		return authenticationProvider;
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		List<AuthenticationProvider> authenticationProviderList = new ArrayList<AuthenticationProvider>();
		authenticationProviderList.add(firstAuthenticationProvider());
		authenticationProviderList.add(secondAuthenticationProvider());
		AuthenticationManager authenticationManager = new ProviderManager(
				authenticationProviderList);
		return authenticationManager;
	}
}
