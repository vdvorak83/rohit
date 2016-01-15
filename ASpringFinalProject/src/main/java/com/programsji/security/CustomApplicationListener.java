package com.programsji.security;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

//IT WAS NOT WORKING
@Component
public class CustomApplicationListener implements
		ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

	@Override
	public void onApplicationEvent(
			AuthenticationFailureBadCredentialsEvent event) {
		Object userName = event.getAuthentication().getPrincipal();
		Object password = event.getAuthentication().getCredentials();

		System.out.println("User Name and Passwords : " + userName + "   ,  "
				+ password);

	}

}
