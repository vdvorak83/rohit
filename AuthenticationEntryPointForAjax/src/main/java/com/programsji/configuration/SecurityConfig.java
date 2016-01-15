package com.programsji.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.programsji.entrypoint.CustomAuthenticationEntryPoint;

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
	public CustomAuthenticationEntryPoint customAuthenticationEntryPoint() {
		return new CustomAuthenticationEntryPoint("/login");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.exceptionHandling()
				.authenticationEntryPoint(customAuthenticationEntryPoint())
				.and().authorizeRequests().antMatchers("/visitorpage")
				.permitAll().anyRequest().hasRole("USER").and().formLogin()
				.loginPage("/login").failureUrl("/login?error")
				.loginProcessingUrl("/j_spring_security_check")
				.passwordParameter("j_password")
				.usernameParameter("j_username")
				.defaultSuccessUrl("/visitorpage", true).permitAll().and()
				.httpBasic().and().logout().logoutSuccessUrl("/login?logout")
				.and().csrf().disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("john").password("john")
				.roles("USER");
	}

}
