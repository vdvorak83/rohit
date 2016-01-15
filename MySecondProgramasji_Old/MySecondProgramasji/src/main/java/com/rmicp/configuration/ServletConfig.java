package com.rmicp.configuration;

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

import com.rmicp.bo.Person;
import com.rmicp.controller.HomeController;
import com.rmicp.controller.MyController;
import com.rmicp.interceptor.ACommonInterceptor;
import com.rmicp.viewresolvers.JsonViewResolver;
import com.rmicp.viewresolvers.MarshallingXmlViewResolver;

@Configuration
@EnableWebMvc
// @EnableWebMVC, this is the same as <mvc:annotation-driven/>
// @Import({ DataBaseConfig.class })
@ComponentScan("com.rmicp")
@Import({ Security_Config.class })
// IT IS MENDATORY HERE!!! ROOTCONFIG's CONFIGURATIONS DOES NOT WORK.
@PropertySource("classpath:properties.properties")
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
		registry.addViewController("/login").setViewName("login");
	}

	@Bean
	public ViewResolver contentNegotiatingViewResolver(
			ContentNegotiationManager manager) {
		List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);
		// resolvers.add(getXmlViewResolver());
		resolvers.add(getInternalResourceViewResolver());
		resolvers.add(getJsonViewResolver());
		resolvers.add(getMarshallingXmlViewResolver());
		resolver.setViewResolvers(resolvers);
		return resolver;
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
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		propertySourcesPlaceholderConfigurer.setLocation(new ClassPathResource(
				"properties.properties"));
		return propertySourcesPlaceholderConfigurer;
	}

	@Bean
	public ViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/pages/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Bean(name = "marshallingXmlViewResolver")
	public ViewResolver getMarshallingXmlViewResolver() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(new Class[] { Person.class });
		return new MarshallingXmlViewResolver(marshaller);
	}

	// public ViewResolver getExcelViewResolver() {
	//
	// }

	@Bean(name = "jsonViewResolver")
	public ViewResolver getJsonViewResolver() {
		return new JsonViewResolver();
	}

	// ///////////////SIMPLE URL HANDLER
	// MAPPING//////////////////////////////////

	@Bean
	public SimpleUrlHandlerMapping getSimpleUrlHandlerMapping() {
		SimpleUrlHandlerMapping simpleUrlHandlerMapping = new SimpleUrlHandlerMapping();
		Map urlMap = new HashMap();
		// urlMap.put("/r/*.htm", "myController");
		urlMap.put("/welcome.htm", "welcomeController");
		urlMap.put("/*/welcome.htm", "welcomeController");
		simpleUrlHandlerMapping.setUrlMap(urlMap);
		simpleUrlHandlerMapping.setAlwaysUseFullPath(false);
		simpleUrlHandlerMapping.setOrder(1);
		simpleUrlHandlerMapping
				.setInterceptors(new Object[] { new ACommonInterceptor() });
		return simpleUrlHandlerMapping;
	}

	// /////////////////////////////////////////////SIMPLE URL HANDLER MAPPING
	// [END]//////////////////////////////////////

	// @Bean(name = "excelViewResolver")
	// public ViewResolver getXmlViewResolver() {
	// XmlViewResolver resolver = new XmlViewResolver();
	//
	//
	// //resolver.setLocation(new
	// ClassPathResource("com/rmicp/configuration/PersonExcelView.class"));
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

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ACommonInterceptor()).addPathPatterns(
				"/*.htm");
		registry.addInterceptor(localeChangeInterceptor());
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor i = new LocaleChangeInterceptor();
		i.setParamName("lang");
		return i;
	}

	// /////////////////////////Interceptors[End]////////////////////

}
