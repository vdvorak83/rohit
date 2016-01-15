package com.rmicp.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.xml.MarshallingView;

import com.rmicp.bo.Person;
import com.rmicp.interceptor.MyOwnInterceptor;
import com.rmicp.viewresolver.JsonViewResolver;
import com.rmicp.viewresolver.MarshallingXmlViewResolver;

@Configuration
@PropertySource("classpath:myproperties.properties")
@ComponentScan("com.rmicp")
@EnableWebMvc
public class ServletConfig extends WebMvcConfigurerAdapter {
	// ////////////////////////VIEW RESOLVERS//////////////////

	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver viewresolver = new InternalResourceViewResolver();
		viewresolver.setPrefix("/WEB-INF/views/");
		viewresolver.setSuffix(".jsp");
		return viewresolver;
	}

	@Bean
	public ViewResolver getJsonViewResolver() {
		return new JsonViewResolver();
	}

	@Bean
	public MarshallingXmlViewResolver getXmlviewResolver() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(new Class[] { Person.class });
		return new MarshallingXmlViewResolver(marshaller);
	}

	@Bean
	public ViewResolver getContentNegotiatingViewResolver(
			ContentNegotiationManager manager) {
		List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
		ContentNegotiatingViewResolver viewresolver = new ContentNegotiatingViewResolver();
		viewresolver.setContentNegotiationManager(manager);
		resolvers.add(getInternalResourceViewResolver());
		resolvers.add(getJsonViewResolver());
		resolvers.add(getXmlviewResolver());
		viewresolver.setViewResolvers(resolvers);
		return viewresolver;
	}

	// ////////////////////////Content Negotiation//////////////////
	@Override
	public void configureContentNegotiation(
			ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(true);
		configurer.ignoreAcceptHeader(true);
		configurer.useJaf(false);
		configurer.defaultContentType(MediaType.TEXT_HTML);
		configurer.mediaType("htm", MediaType.TEXT_HTML);
		configurer.mediaType("json", MediaType.APPLICATION_JSON);
		configurer.mediaType("xml", MediaType.APPLICATION_XML);
	}

	// ////////////////////////Interceptors//////////////////

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		MyOwnInterceptor myOwnInterceptor = new MyOwnInterceptor();

		registry.addInterceptor(myOwnInterceptor).addPathPatterns("/*.htm");
		registry.addInterceptor(getLocaleChangeInterceptor());
	}

	@Bean
	public LocaleChangeInterceptor getLocaleChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}

	// ////////////////////////SimpleUrlMappingHandling//////////////////

	@Bean
	public SimpleUrlHandlerMapping getSimpleUrlHandlerMapping() {
		SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
		mapping.setOrder(1);
		mapping.setAlwaysUseFullPath(true);
		Map urlPathMap = new HashMap();
		urlPathMap.put("/xx.htm", "welcomeController");
		mapping.setUrlMap(urlPathMap);
		mapping.setOrder(1);
		return mapping;
	}

	// ////////////////////////Resource Handler &
	// i18N/////////////////////////////////////
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations(
				"/resources/");
	}

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:resourcebundleproperties");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean(name = "localeResolver")
	public LocaleResolver getLocaleResolver() {
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);
		return cookieLocaleResolver;
	}

	// ////////////////////////Want to use @Value///////////////////////

	@Bean
	public PropertySourcesPlaceholderConfigurer propertySourcePlaceHolderConfigurer() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		configurer
				.setLocation(new ClassPathResource("myproperties.properties"));
		return configurer;
	}

	// ///////////////////SET DEFAULT HANDLER//////////////////////////////
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		// TODO Auto-generated method stub
		configurer.enable();
	}
}
