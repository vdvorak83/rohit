package com.rmicp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.authentication.AuthenticationManagerFactoryBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rmicp.security.CustomAuthenticationProvider;
import com.rmicp.security.CustomPasswordEncoder;
import com.rmicp.security.MyAuthenticationFailureHandler;
import com.rmicp.security.MyAuthenticationSuccessHandler;
import com.rmicp.security.UserService;

//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig_Real extends WebSecurityConfigurerAdapter {

	// @Autowired
	// private UserDetailsServiceImpl userDetailsServiceImpl;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**,/img/**,/js/**");
	}

	// /@Autowired
	CustomAuthenticationProvider authenticationProvider;

	// /@Autowired
	UserService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.userDetailsService(userDetailsService).authorizeRequests()
		// .antMatchers("/**").hasRole("USER").and().httpBasic().and()
		// .formLogin();
		// http.authenticationProvider(authenticationProvider).authorizeRequests()
		// .antMatchers("/**").hasRole("USER").and().formLogin().and()
		// .httpBasic();

		http.authorizeRequests().antMatchers("/**").hasRole("USER").and()
				.formLogin().defaultSuccessUrl("/home", true)
				.successHandler(new MyAuthenticationSuccessHandler())
				.failureHandler(new MyAuthenticationFailureHandler()).and()
				.httpBasic();

		// http.authorizeRequests().anyRequest().authenticated().and().formLogin()
		// .and().httpBasic();
		// .loginPage("/login")
		// .defaultSuccessUrl("/success-login", true)
		// .failureUrl("/error-login")
		// .loginProcessingUrl("/process-login")
		// .usernameParameter("security_username")
		// .passwordParameter("security_password").permitAll().and()
		// .logout().logoutSuccessUrl("/login").logoutUrl("/logout")
		// .permitAll().and().rememberMe()
		// .key("04E87501B3F04DB297ADB74FA8BD48CA").and().csrf().disable();
	}

	// /@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	// /@Autowired
	PasswordEncoder customePasswordEncoder;

	// /@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.authenticationProvider(authenticationProvider)
				.userDetailsService(userDetailsService)
				.passwordEncoder(customePasswordEncoder);

		// auth.inMemoryAuthentication().withUser("rohit").password("malhotra")
		// .roles("USER");
		super.configure(auth);
	}

}
