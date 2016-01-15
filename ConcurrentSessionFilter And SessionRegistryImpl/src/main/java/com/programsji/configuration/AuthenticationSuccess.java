package com.programsji.configuration;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class AuthenticationSuccess extends
		SimpleUrlAuthenticationSuccessHandler {
	@Resource(name = "sessionRegistry")
	// @Autowired
	SessionRegistryImpl sessionRegistry;
@Override
public void onAuthenticationSuccess(HttpServletRequest request,
		HttpServletResponse response, Authentication authentication)
		throws IOException, ServletException {
	// TODO Auto-generated method stub
	super.onAuthenticationSuccess(request, response, authentication);
}
}
