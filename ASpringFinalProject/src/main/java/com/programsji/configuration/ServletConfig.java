package com.programsji.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.hibernate.cfg.Environment;
import org.springframework.beans.BeanWrapperImpl;
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
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.WebContentInterceptor;
import org.springframework.web.servlet.mvc.support.ControllerClassNameHandlerMapping;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.XmlViewResolver;
import org.springframework.web.servlet.view.xml.MarshallingView;

import com.programsji.bo.Person;
import com.programsji.controller.BeanNameUrlHandlerMappingController;
import com.programsji.controller.MycnameController;
import com.programsji.controller.HomeController;
import com.programsji.controller.MyController;
import com.programsji.interceptor.ACommonInterceptor;
import com.programsji.viewresolvers.JsonViewResolver;
import com.programsji.viewresolvers.MarshallingXmlViewResolver;

@Configuration
@EnableWebMvc
// @EnableWebMVC, this is the same as <mvc:annotation-driven/>
@ComponentScan("com.programsji")
@Import({ DataBaseConfig.class, DataBaseConfig1.class, SecurityConfig.class })
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

	// @Bean
	// public UrlBasedViewResolver getUrlBasedViewResolver() {
	// UrlBasedViewResolver urlbasedViewResolver = new UrlBasedViewResolver();
	// return urlbasedViewResolver;
	// }

	@Bean
	public ViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/pages/");
		resolver.setSuffix(".jsp");
		resolver.setRedirectHttp10Compatible(false);
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

	@Bean
	public WebContentInterceptor getWebContentInterceptor() {
		WebContentInterceptor wci = new WebContentInterceptor();
		wci.setPathMatcher(new AntPathMatcher());
		wci.setCacheSeconds(1000);
		wci.setUseCacheControlNoStore(true);
		wci.setUseExpiresHeader(true);

		return wci;

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

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ACommonInterceptor());// .addPathPatterns("/");
		registry.addInterceptor(localeChangeInterceptor());
		registry.addInterceptor(getThemeChangeInterceptor());
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor i = new LocaleChangeInterceptor();
		i.setParamName("lang");
		return i;
	}

	// /////////////////////////Interceptors[End]////////////////////

	// ////////////////////THEME FUNCTIONALITY//////////////////////////

	@Bean
	public ResourceBundleThemeSource getResourceBundleThemeSource() {
		ResourceBundleThemeSource resourceBundleThemeSource = new ResourceBundleThemeSource();
		resourceBundleThemeSource.setBasenamePrefix("theme-");
		return resourceBundleThemeSource;
	}

	@Bean
	public ThemeChangeInterceptor getThemeChangeInterceptor() {
		ThemeChangeInterceptor themeChangeInterceptor = new ThemeChangeInterceptor();
		themeChangeInterceptor.setParamName("theme");
		return themeChangeInterceptor;

	}

	@Bean
	public CookieThemeResolver getCookieThemeResolver() {
		CookieThemeResolver themeResolver = new CookieThemeResolver();
		themeResolver.setDefaultThemeName("default");
		return themeResolver;
	}

	// //////////////////////THEME FUNCTIONALITY [ENDS]/////////////////
	// NO NEED TO DEFINE FOLLOWING BEAN, IT IS ALREADY THERE//
	// @Bean
	// public BeanNameUrlHandlerMapping getBeanNameUrlHandlerMapping() {
	// BeanNameUrlHandlerMapping mapping = new BeanNameUrlHandlerMapping();
	// mapping.setAlwaysUseFullPath(false);
	// // Object[] interceptors = new Object[] { new ACommonInterceptor() };
	// // mapping.setInterceptors(interceptors);
	// return mapping;
	// }

	@Bean(name = "/mybean")
	public BeanNameUrlHandlerMappingController bean_name() {
		return new BeanNameUrlHandlerMappingController();
	}

	@Bean
	public MycnameController mybean_name() {
		return new MycnameController();
	}

	@Bean
	public ControllerClassNameHandlerMapping getControllerClassNameHandlerMapping() {
		ControllerClassNameHandlerMapping x = new ControllerClassNameHandlerMapping();
		x.setAlwaysUseFullPath(false);
		x.setCaseSensitive(false);
		return new ControllerClassNameHandlerMapping();
	}

}
