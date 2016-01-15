package com.rmicp.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.UrlAuthorizationConfigurer;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.rmicp.security.CustomAuthenticationProvider;
import com.rmicp.security.CustomAuthenticationProviderForCustomer;
import com.rmicp.security.CustomAuthenticationProviderForEmployee;
import com.rmicp.security.CustomOncePerRequestFilter;
import com.rmicp.security.CustomPostAuthenticationChecks;
import com.rmicp.security.CustomSavedRequestAwareAuthenticationSuccessHandler;
import com.rmicp.security.MyAuthenticationFailureHandler;
import com.rmicp.security.MyAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true)
public class Security_Config extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**,/img/**,/js/**");

	}

	// @Autowired
	// CustomSavedRequestAwareAuthenticationSuccessHandler
	// customSavedRequestAwareAuthenticationSuccessHandler;

	// @Autowired
	// MyAuthenticationSuccessHandler mySuccessHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/employee/**")
				.access("hasRole('ROLE_USER')").and().formLogin()
				.loginPage("/login").failureUrl("/login?error").and().logout()
				.logoutSuccessUrl("/login?logout").and().csrf().disable();

		// http.authorizeRequests().antMatchers("/employee/**")
		// .hasRole("EMPLOYEE").antMatchers("/**").permitAll().and()
		// .formLogin().defaultSuccessUrl("/home", true)
		// .loginPage("/login.jsp")
		//
		// .successHandler(new MyAuthenticationSuccessHandler())
		// .failureHandler(new MyAuthenticationFailureHandler()).and()
		// // .addFilter(getUsernamePasswordAuthenticationFilter())
		// // .authenticationProvider(customAuthenticationProvider)
		// .csrf().disable();

		// http.authorizeRequests().antMatchers("/employee/**")
		// .hasRole("EMPLOYEE").antMatchers("/customer/**")
		// .hasRole("CUSTOMER").antMatchers("/**")
		// .permitAll()
		// .and()
		// .formLogin()
		// .successHandler(mySuccessHandler)
		// .loginPage("/login.jsp")
		// // .failureUrl("/login.jsp?login_error=1")
		//
		// // .defaultSuccessUrl("/employee/home", true)
		// .and().logout().invalidateHttpSession(true)
		// .logoutSuccessUrl("/login.jsp").and().httpBasic().and().csrf()
		// .disable();
		//

		// .addFilter(getAuthenticationProcessingFilterForEmployee())
		// .addFilter(getAuthenticationProcessingFilterForCustomer())
		// .addFilter(getUsernamePasswordAuthenticationFilter());

	}

	// @Bean
	// public UsernamePasswordAuthenticationFilter
	// getUsernamePasswordAuthenticationFilter() {
	// MyAuthenticationFilter obj = new MyAuthenticationFilter();
	// obj.setAuthenticationManager(getAuthenticationManager());
	// //
	// obj.setAuthenticationSuccessHandler(customSavedRequestAwareAuthenticationSuccessHandler);
	// return obj;
	// }

	// @Bean
	// public UsernamePasswordAuthenticationFilter
	// getAuthenticationProcessingFilterForEmployee() {
	// UsernamePasswordAuthenticationFilter obj = new
	// UsernamePasswordAuthenticationFilter();
	// obj.setAuthenticationManager(getAuthenticationManagerForEmployee());
	// obj.setFilterProcessesUrl("/j_spring_security_check_for_employee");
	// return obj;
	// }
	//
	// @Bean
	// public UsernamePasswordAuthenticationFilter
	// getAuthenticationProcessingFilterForCustomer() {
	// UsernamePasswordAuthenticationFilter obj = new
	// UsernamePasswordAuthenticationFilter();
	// obj.setAuthenticationManager(getAuthenticationManagerForCustomer());
	// obj.setFilterProcessesUrl("/j_spring_security_check_for_customer");
	// return obj;
	// }

	// @Bean
	// public FilterChainProxy getFilterChainProxy() {
	// List<SecurityFilterChain> filterChains = new
	// ArrayList<SecurityFilterChain>();
	// filterChains.add(customOncePerRequestFilter);
	// FilterChainProxy fcp = new FilterChainProxy(filterChains);
	// return fcp;
	// }

	// @Autowired
	// CustomAuthenticationProviderForCustomer
	// customAuthenticationProviderForCustomer;

//	@Autowired
//	CustomAuthenticationProvider customAuthenticationProvider;

	//
	// @Autowired
	// CustomAuthenticationProviderForEmployee
	// customAuthenticationProviderForEmployee;
	//
	// @Autowired
	// CustomPostAuthenticationChecks customPostAuthenticationChecks;

	// public ProviderManager getAuthenticationManagerForCustomer() {
	//
	// List<AuthenticationProvider> l = new ArrayList<AuthenticationProvider>();
	// l.add(customAuthenticationProviderForCustomer);
	// ProviderManager pm = new ProviderManager(l);
	// return pm;
	// }
	//
	// public ProviderManager getAuthenticationManagerForEmployee() {
	// List<AuthenticationProvider> l = new ArrayList<AuthenticationProvider>();
	// l.add(customAuthenticationProviderForEmployee);
	// ProviderManager pm = new ProviderManager(l);
	// return pm;
	// }

	// public ProviderManager getAuthenticationManager() {
	// List<AuthenticationProvider> l = new ArrayList<AuthenticationProvider>();
	// // customAuthenticationProvider
	// // .setPostAuthenticationChecks(customPostAuthenticationChecks);
	// l.add(customAuthenticationProvider);
	// // l.add(customAuthenticationProviderForCustomer);
	// // l.add(customAuthenticationProviderForEmployee);
	// ProviderManager pm = new ProviderManager(l);
	// return pm;
	// }

	// @Autowired
	// protected void configureGlobal(AuthenticationManagerBuilder auth)
	// throws Exception {
	// // customAuthenticationProvider
	// // .setPostAuthenticationChecks(customPostAuthenticationChecks);
	// auth.authenticationProvider(customAuthenticationProvider);
	// super.configure(auth);
	// }
	//
	//

	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		// auth.authenticationProvider(customAuthenticationProvider);

		auth.inMemoryAuthentication().withUser("rohit").password("malhotra")
				.roles("USER");
		super.configure(auth);
	}

	// @Bean
	// @Override
	// public AuthenticationManager authenticationManagerBean() throws Exception
	// {
	// return super.authenticationManagerBean();
	// }

	// ///////////////////////////////VOTERS///////////////////////////////

	// @Bean
	// public AuthenticatedVoter getAuthenticatedVoter() {
	// AuthenticatedVoter av = new AuthenticatedVoter();
	// return av;
	// }
	//
	// @Bean
	// public RoleVoter getRoleVoter() {
	// RoleVoter rv = new RoleVoter();
	// rv.setRolePrefix("ROLE1_");
	// return rv;
	// }
	//
	// @Bean
	// public AccessDecisionManager getAffirmativeBased() {
	// List<AccessDecisionVoter> adv = new ArrayList<AccessDecisionVoter>();
	// adv.add(getAuthenticatedVoter());
	// adv.add(getRoleVoter());
	// AffirmativeBased ab = new AffirmativeBased(adv);
	// return ab;
	// }
}
