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
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.programsji.security.CustomSimpleUrlLogoutSuccessHandler;

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
	public CustomSimpleUrlLogoutSuccessHandler customSimpleUrlLogoutSuccessHandler() {
		CustomSimpleUrlLogoutSuccessHandler customSimpleUrlLogoutSuccessHandler = new CustomSimpleUrlLogoutSuccessHandler();
		return customSimpleUrlLogoutSuccessHandler;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login").permitAll().anyRequest()
				.hasAnyRole("USER", "ADMIN").and().formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/j_spring_security_check")
				.passwordParameter("j_password")
				.usernameParameter("j_username").defaultSuccessUrl("/demo")
				.failureUrl("/login?error").permitAll().and().logout()
				.logoutUrl("/logout")
				.logoutSuccessHandler(customSimpleUrlLogoutSuccessHandler())
				.and().csrf().disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("john").password("john")
				.roles("ADMIN");
	}

}
