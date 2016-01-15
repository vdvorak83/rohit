package com.rmicp.viewresolvers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.xml.MarshallingView;


public class MarshallingXmlViewResolver implements ViewResolver {

	private Marshaller marshaller;

	@Autowired
	public MarshallingXmlViewResolver(Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	@Override
	public View resolveViewName(String viewname, Locale arg1) throws Exception {
		MarshallingView view = new MarshallingView();
		view.setMarshaller(marshaller);
		return view;
	}

}
