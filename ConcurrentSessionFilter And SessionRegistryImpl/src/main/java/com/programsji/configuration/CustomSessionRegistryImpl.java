package com.programsji.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.stereotype.Component;

//@Component(value = "sessionRegistry")
public class CustomSessionRegistryImpl extends SessionRegistryImpl implements
		ApplicationListener<SessionDestroyedEvent> {

	@Override
	public SessionInformation getSessionInformation(String sessionId) {
		return super.getSessionInformation(sessionId);
	}

	@Override
	public void registerNewSession(String sessionId, Object principal) {
		super.registerNewSession(sessionId, principal);

	}
}
