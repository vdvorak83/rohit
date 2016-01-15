package com.rmicp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import com.rmicp.security.OurAccessDeniedHandler;
import com.rmicp.security.OurLoginFailureHandler;
import com.rmicp.security.OurLoginUrlAuthenticationEntryPoint;
import com.rmicp.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userdetailservice;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**");
		// super.configure(web);
	}

	@Bean
	public OurLoginUrlAuthenticationEntryPoint getOurLoginUrlAuthenticationEntryPoint() {
		OurLoginUrlAuthenticationEntryPoint obj = new OurLoginUrlAuthenticationEntryPoint(
				"/withoutcredentials");
		return obj;
	}

	@Bean
	public OurAccessDeniedHandler getOurAccessDeniedHandler() {
		OurAccessDeniedHandler obj = new OurAccessDeniedHandler();
		return obj;
	}

	@Bean
	public OurLoginFailureHandler getOurLoginFailureHandler() {
		OurLoginFailureHandler ourLoginFailureHandler = new OurLoginFailureHandler();
		ourLoginFailureHandler.setDefaultFailureUrl("/login?error");
		return ourLoginFailureHandler;
	}

	@Bean
	public TokenBasedRememberMeServices getTokenBasedRemembermeServices() {
		TokenBasedRememberMeServices tokenBasedRemembermeServices = new TokenBasedRememberMeServices(
				"remember-me", userdetailservice);
		return tokenBasedRemembermeServices;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.exceptionHandling()
				.accessDeniedHandler(getOurAccessDeniedHandler())
				.authenticationEntryPoint(
						getOurLoginUrlAuthenticationEntryPoint()).and()
				.authorizeRequests().antMatchers("/customer")
				.hasAnyRole("CUSTOMER").antMatchers("/employee")
				.hasAnyRole("EMPLOYEE").antMatchers("/").permitAll().and()
				.formLogin().loginPage("/login")
				.loginProcessingUrl("/j_spring_security_check")
				.usernameParameter("j_username")
				.passwordParameter("j_password")
				.failureHandler(getOurLoginFailureHandler()).and().logout()
				.and().rememberMe()
				.rememberMeServices(getTokenBasedRemembermeServices())
				.tokenValiditySeconds(3000).and().httpBasic().and()
				.userDetailsService(userdetailservice).csrf().disable();
	}

	@Autowired
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
		dap.setUserDetailsService(userdetailservice);
		auth.authenticationProvider(dap);
		super.configure(auth);
	}

}
