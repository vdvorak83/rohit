package com.rmicp.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.support.ControllerClassNameHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.rmicp.interceptor.SharedInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan({ "com.rmicp.bo", "com.rmicp.exception", "com.rmicp.dao",
		"com.rmicp.controller", "com.rmicp.service" })
@Import({ DatabaseConfig_ExternalProfile.class,
		DatabaseConfig_LocalProfile.class })
@PropertySource(value = { "classpath:property1.properties",
		"classpath:property2.properties" })
public class ServletConfig extends WebMvcConfigurerAdapter {

	// //VIEW RESOLVERS

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setRedirectHttp10Compatible(false);
		internalResourceViewResolver.setOrder(1);
		internalResourceViewResolver.setPrefix("/WEB-INF/pages/");
		internalResourceViewResolver.setSuffix(".jsp");
		return internalResourceViewResolver;
	}

	@Bean
	public UrlBasedViewResolver urlBasedViewResolver() {
		UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
		urlBasedViewResolver.setRedirectHttp10Compatible(false);
		urlBasedViewResolver.setOrder(2);
		urlBasedViewResolver.setPrefix("/WEB-INF/pages/");
		urlBasedViewResolver.setSuffix(".jsp");
		return urlBasedViewResolver;
	}

	@Bean
	public BeanNameUrlHandlerMapping beanNameUrlHandlerMapping() {
		BeanNameUrlHandlerMapping beanNameUrlHandlerMapping = new BeanNameUrlHandlerMapping();
		beanNameUrlHandlerMapping.setOrder(3);
		beanNameUrlHandlerMapping.setAlwaysUseFullPath(false);
		return beanNameUrlHandlerMapping;
	}

	@Bean
	public ControllerClassNameHandlerMapping controllerClassnameHandlerMapping() {
		ControllerClassNameHandlerMapping controllerClassNameHandlerMapping = new ControllerClassNameHandlerMapping();
		return controllerClassNameHandlerMapping;
	}

	@Bean
	public SimpleUrlHandlerMapping simpleUrlHandlerMapping() {
		SimpleUrlHandlerMapping simpleUrlHandlerMapping = new SimpleUrlHandlerMapping();
		return simpleUrlHandlerMapping;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SharedInterceptor());
		super.addInterceptors(registry);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
		super.addViewControllers(registry);
	}

	@Override
	public void configureContentNegotiation(
			ContentNegotiationConfigurer configurer) {
		// TODO Auto-generated method stub
		super.configureContentNegotiation(configurer);
	}

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		// TODO Auto-generated method stub
		super.configureDefaultServletHandling(configurer);
	}

	// //FORMATTERS
	@Override
	public void addFormatters(FormatterRegistry registry) {
		// TODO Auto-generated method stub
		super.addFormatters(registry);
	}

	// //CONVERTERS
	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		// TODO Auto-generated method stub
		super.configureMessageConverters(converters);
	}

	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		// TODO Auto-generated method stub
		super.configureAsyncSupport(configurer);
	}

	// ///////FOR READING PROPERTY FILES

	// /////FOR READING RESOURCE BUNDLE//I18Support

}
