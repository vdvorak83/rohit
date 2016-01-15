package com.rmicp.security;

import java.util.Collection;
import java.util.LinkedHashMap;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class MyFilterInvocationSecurityMetadataSource extends
		DefaultFilterInvocationSecurityMetadataSource {

	public MyFilterInvocationSecurityMetadataSource(
			LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap) {
		super(requestMap);

	}

}
