package com.programsji.view;

import java.util.Locale;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

public class CustomViewResolver implements ViewResolver, Ordered {

	private int order = 0;

	public CustomViewResolver() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public View resolveViewName(String viewName, Locale locale)
			throws Exception {
		if (viewName.startsWith("txt:")) {
			return new CustomView();
		}
		return null;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public int getOrder() {

		return 0;
	}

}
