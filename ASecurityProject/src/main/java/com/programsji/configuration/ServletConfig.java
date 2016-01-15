package com.programsji.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.XmlViewResolver;
import org.springframework.web.servlet.view.xml.MarshallingView;

import com.programsji.bo.Person;
import com.programsji.controller.HomeController;
import com.programsji.controller.MyController;


@Configuration
@EnableWebMvc
// @EnableWebMVC, this is the same as <mvc:annotation-driven/>
// @Import({ DataBaseConfig.class })
@ComponentScan("com.programsji")
// IT IS MENDATORY HERE!!! ROOTCONFIG's CONFIGURATIONS DOES NOT WORK.

public class ServletConfig extends WebMvcConfigurerAdapter {

	// @Resource
	// private Environment env;

	@Autowired
	ServletContext servletContext;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry regsitry) {
		regsitry.addResourceHandler("/resources/**").addResourceLocations(
				"/resources/");
	}

	// 7. Set default servlet handler, this is the same as
	// <mvc:default-servlet-handler/>
	// PROBLEM IS , IF I ACTIVATE THIS, SIMPLEURLHANDLERMAPPING DOES NOT WORK
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// registry.addViewController("/").setViewName("login");
	}

	
	public void configureContentNegotiation(
			ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(true).useJaf(false)
				.ignoreAcceptHeader(true)
				.mediaType("html", MediaType.TEXT_HTML)
				.mediaType("xml", MediaType.APPLICATION_XML)
				.mediaType("json", MediaType.APPLICATION_JSON)
				.defaultContentType(MediaType.TEXT_HTML);
	}

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
		source.setBasename("classpath:propertiesresourcebundle");
		source.setDefaultEncoding("UTF-8");
		return source;
	}

	// /Only needed if we are using @Value and ${...} when referencing
	// properties

	
	@Bean
	public ViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/pages/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	
	// ///////////////SIMPLE URL HANDLER
	// MAPPING//////////////////////////////////

	

	// /////////////////////////////////////////////SIMPLE URL HANDLER MAPPING
	// [END]//////////////////////////////////////

	// @Bean(name = "excelViewResolver")
	// public ViewResolver getXmlViewResolver() {
	// XmlViewResolver resolver = new XmlViewResolver();
	//
	//
	// //resolver.setLocation(new
	// ClassPathResource("com/programsji/configuration/PersonExcelView.class"));
	// //IT NEEDS XML FILE, THAT I DONT WANT TO CREATE FOR THIS PROJECT
	// resolver.setOrder(1);
	// return resolver;
	// }

	// @Bean(name = "home")
	// public PersonExcelView getPersonExcelView() {
	// return new PersonExcelView();
	//
	// }

	@Bean(name = "localeResolver")
	// It is Mendatory To Assign Name Here
	public LocaleResolver getLocaleResolver() {
		CookieLocaleResolver clr = new CookieLocaleResolver();
		clr.setDefaultLocale(Locale.ITALY);
		return clr;
	}

	// /////////////////////////Interceptors/////////////////////////

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor i = new LocaleChangeInterceptor();
		i.setParamName("lang");
		return i;
	}

	// /////////////////////////Interceptors[End]////////////////////

}
