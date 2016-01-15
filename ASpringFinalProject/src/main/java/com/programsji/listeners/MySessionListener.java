package com.programsji.listeners;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MySessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("Session Is Created");

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println(se.getSession().getAttributeNames());
		System.out.println("Session Is Destroyed");

	}

}
