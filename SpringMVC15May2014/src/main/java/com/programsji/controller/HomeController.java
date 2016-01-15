package com.programsji.controller;

import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.bouncycastle.asn1.x509.sigi.PersonalData;
import org.jboss.netty.handler.codec.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.programsji.bo.Person;
import com.programsji.customannotation.Country;
import com.sun.mail.handlers.message_rfc822;

@Controller
public class HomeController {
	@Autowired
	private RequestMappingHandlerMapping handlerMapping;
	@Autowired
	private ReloadableResourceBundleMessageSource messageSource;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(@Country String personCountry, Model m) {
		Person p = new Person();
		m.addAttribute("person", p);
		return "person";
	}

	@RequestMapping(value = "/addperson", method = RequestMethod.GET)
	public String addPerson(Model m) {
		Person p = new Person();
		m.addAttribute("person", p);
		return "person";
	}

	@RequestMapping(value = "/addperson", method = RequestMethod.POST)
	public String addPerson(@RequestBody(required = false) @Valid Person p,
			BindingResult br)
	// , consumes = "application/json"
	// public String addPerson(@ModelAttribute("person") @Valid Person p,
	// ,BindingResult br, SessionStatus status) {
	{
		if (br.hasErrors()) {
			return "person";
		}
		return "person";
	}

	@RequestMapping(value = "/features", method = RequestMethod.GET, params = { "type=1" })
	public String features(
			@CookieValue(required = false, value = "JSESSIONID") String cookie,
			WebRequest webRequest, HttpServletRequest request) {
		// To Get All Mapped Urls
		Set<RequestMappingInfo> set = handlerMapping.getHandlerMethods()
				.keySet();
		for (RequestMappingInfo requestMappingInfo : set) {
			System.out.println(requestMappingInfo.getPatternsCondition());
		}

		// To Get All Controller Classes
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(
				false);
		scanner.addIncludeFilter(new AnnotationTypeFilter(Controller.class));
		scanner.addIncludeFilter(new AssignableTypeFilter(Controller.class));
		for (BeanDefinition beanDefintion : scanner
				.findCandidateComponents("com.programsji")) {
			System.out.println(beanDefintion.getBeanClassName());
		}

		// Get Value from message source
		System.out.println(messageSource.getMessage("person.fname",
				new Object[] { "rohit" }, Locale.ENGLISH));

		// webRequest[To get URL, parameters and other details about request

		System.out.println(webRequest.getDescription(false));
		System.out.println(webRequest.getAttribute("myrequest", 0));

		// To Get Current Locale
		System.out.println(LocaleContextHolder.getLocale().toString());

		// ToGet Flash Map
		FlashMap flashMap = RequestContextUtils.getOutputFlashMap(request);
		flashMap.put("rm", "igb");

		return "home";
	}
}
