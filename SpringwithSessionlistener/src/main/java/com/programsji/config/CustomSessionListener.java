package com.programsji.config;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.programsji.controller.DemoController;

public class CustomSessionListener implements HttpSessionListener {
	private static final Logger logger = LoggerFactory
			.getLogger(CustomSessionListener.class);

	public static int activeSessions = 0;

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		logger.info("NEW SESSION CREATED");
		activeSessions = activeSessions + 1;
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		logger.info("SESSION DESTROYED");
		activeSessions = activeSessions - 1;
	}
}
