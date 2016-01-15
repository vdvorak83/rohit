package com.programsji.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.stereotype.Service;

import com.programsji.dao.UserDao;

@Service
public class SessionRegistryService {

	@Autowired
	UserDao userdao;
	@Resource(name = "sessionRegistry")
	private SessionRegistryImpl sessionRegistry;

	public SessionRegistryImpl getSessionRegistry() {
		return userdao.getSessionRegistry();
	}

}
