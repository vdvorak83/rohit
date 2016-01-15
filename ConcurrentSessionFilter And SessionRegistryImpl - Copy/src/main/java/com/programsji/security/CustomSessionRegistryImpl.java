package com.programsji.security;

import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.stereotype.Component;

@Component(value="sessionRegistry")
public class CustomSessionRegistryImpl extends SessionRegistryImpl {

}
