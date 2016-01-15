package com.programsji.configuration;

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
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import com.programsji.security.CustomAuthenticationProvider;
import com.programsji.security.MyAuthenticationFailureHandler;
import com.programsji.security.MyAuthenticationSuccessHandler;
import com.programsji.security.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class Security_Config extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userDetailsServiceImpl;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**,/img/**,/js/**");
	}

	@Autowired
	CustomAuthenticationProvider authenticationProvider;

	@Autowired
	UserService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.userDetailsService(userDetailsService).authorizeRequests()
		// .antMatchers("/**").hasRole("USER").and().httpBasic().and()
		// .formLogin();
		// http.authenticationProvider(authenticationProvider).authorizeRequests()
		// .antMatchers("/**").hasRole("USER").and().formLogin().and()
		// .httpBasic();
		http.authorizeRequests().antMatchers("/resources/**").permitAll()
				.anyRequest().authenticated().and().formLogin().and().logout()
				.permitAll().and().csrf().disable();

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

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.authenticationProvider(authenticationProvider).userDetailsService(
				userDetailsService);

		// auth.inMemoryAuthentication().withUser("rohit").password("malhotra")
		// .roles("USER");
		super.configure(auth);
	}

}
