package com.programsji.viewresolvers;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

public class JsonViewResolver implements ViewResolver {

	@Override
	public View resolveViewName(String viewname, Locale locale)
			throws Exception {
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		view.setPrettyPrint(true);// to Set Good Layout
		return view;
	}

}
