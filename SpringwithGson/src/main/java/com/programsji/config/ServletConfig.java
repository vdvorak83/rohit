package com.programsji.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan({ "com.programsji" })
@Import({ DatabaseConfig_ExternalProfile.class,
		DatabaseConfig_LocalProfile.class })
@PropertySource(value = { "classpath:property1.properties",
		"classpath:property2.properties" })
public class ServletConfig extends WebMvcConfigurerAdapter {

	@Autowired
	ServletContext servletContext;

	// //////////CODE FOR DETECTING DEVICE///////////////////
	@Override
	public void addArgumentResolvers(
			List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new ServletWebArgumentResolverAdapter(
				new DeviceWebArgumentResolver()));
		argumentResolvers
				.add(new SitePreferenceHandlerMethodArgumentResolver());
		argumentResolvers.add(new MyHandlerMethodArgumentResolver());
	}

	// ///////FOR READING PROPERTY FILES
	// (1)
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		propertySourcesPlaceholderConfigurer.setLocations(new Resource[] {
				new ClassPathResource("property1.properties"),
				new ClassPathResource("property2.properties") });
		return propertySourcesPlaceholderConfigurer;
	}

	// //VIEW RESOLVERS
	// (1)
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setRedirectHttp10Compatible(false);
		internalResourceViewResolver.setOrder(1);
		internalResourceViewResolver.setPrefix("/WEB-INF/pages/");
		internalResourceViewResolver.setSuffix(".jsp");
		return internalResourceViewResolver;
	}

	// (2)
	// @Bean
	// public UrlBasedViewResolver urlBasedViewResolver() {
	// UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
	// urlBasedViewResolver.setRedirectHttp10Compatible(false);
	// urlBasedViewResolver.setOrder(2);
	// urlBasedViewResolver.setPrefix("/WEB-INF/pages/");
	// urlBasedViewResolver.setSuffix(".jsp");
	// urlBasedViewResolver.setViewClass(JstlView.class);
	// return urlBasedViewResolver;
	// }

	// (3)
	@Bean(name = "marshallingXmlviewresolver")
	public ViewResolver marshallingXMLViewResolver() {
		Jaxb2Marshaller marshallar = new Jaxb2Marshaller();
		marshallar.setClassesToBeBound(new Class[] { Person.class });
		return new XMLViewResolver(marshallar);

	}

	// (4)
	@Bean(name = "jsonViewResolver")
	public ViewResolver jsonViewResolver() {
		return new JsonViewResolver();
	}

	// (5)
	@Bean
	public OwnSimpleMappingExceptionResolver ownSimpleMappingExceptionResolver() {
		OwnSimpleMappingExceptionResolver ownSimpleMappingExceptionResolver = new OwnSimpleMappingExceptionResolver();
		Properties properties = new Properties();
		properties.put("java.lang.Exception", "message");
		// properties.put("java.lang.Exception", value);
		ownSimpleMappingExceptionResolver.setExceptionMappings(properties);
		return ownSimpleMappingExceptionResolver;
	}

	// (6)ConfigureContentNegotiation
	@Override
	public void configureContentNegotiation(
			ContentNegotiationConfigurer configurer) {
		configurer.favorParameter(true).useJaf(false).ignoreAcceptHeader(true)
				.mediaType("html", MediaType.TEXT_HTML)
				.mediaType("xml", MediaType.APPLICATION_XML)
				.mediaType("json", MediaType.APPLICATION_JSON)
				.defaultContentType(MediaType.TEXT_HTML);
		super.configureContentNegotiation(configurer);
	}

	// (7)ContentNegtiationViewResolver
	@Bean
	public ViewResolver contentNegotiationViewResolver(
			ContentNegotiationManager contentNegotiationManager) {
		List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
		ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();
		viewResolver.setContentNegotiationManager(contentNegotiationManager);
		resolvers.add(tilesViewResolver());
		resolvers.add(internalResourceViewResolver());
		// resolvers.add(urlBasedViewResolver());
		resolvers.add(marshallingXMLViewResolver());
		resolvers.add(jsonViewResolver());
		viewResolver.setViewResolvers(resolvers);
		return viewResolver;
	}

	@Bean
	public TilesViewResolver tilesViewResolver() {
		TilesViewResolver tilesViewResolver = new TilesViewResolver();
		tilesViewResolver.setViewClass(TilesView.class);
		tilesViewResolver.setOrder(0);
		return tilesViewResolver;
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(new String[] { "/WEB-INF/tiles.xml" });
		tilesConfigurer.setCheckRefresh(true);
		return tilesConfigurer;
	}

	// ///HANDLER MAPPING
	@Bean
	public BeanNameUrlHandlerMapping beanNameUrlHandlerMapping() {
		BeanNameUrlHandlerMapping beanNameUrlHandlerMapping = new BeanNameUrlHandlerMapping();
		beanNameUrlHandlerMapping.setOrder(3);
		beanNameUrlHandlerMapping.setAlwaysUseFullPath(false);
		return beanNameUrlHandlerMapping;
	}

	//
	@Bean
	public ControllerClassNameHandlerMapping controllerClassnameHandlerMapping() {
		ControllerClassNameHandlerMapping controllerClassNameHandlerMapping = new ControllerClassNameHandlerMapping();
		return controllerClassNameHandlerMapping;
	}

	@Bean
	public SimpleUrlHandlerMapping simpleUrlHandlerMapping() {
		SimpleUrlHandlerMapping simpleUrlHandlerMapping = new SimpleUrlHandlerMapping();
		simpleUrlHandlerMapping.setAlwaysUseFullPath(false);
		simpleUrlHandlerMapping
				.setInterceptors(new Object[] { new SharedInterceptor() });
		Map urlMap = new HashMap();
		urlMap.put("/welcome.htm", "welcomeController");
		urlMap.put("/*/welcome.htm", "welcomeController");
		simpleUrlHandlerMapping.setUrlMap(urlMap);
		simpleUrlHandlerMapping.setOrder(1);
		return simpleUrlHandlerMapping;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SharedInterceptor());
		registry.addInterceptor(localeChangeInterceptor());
		registry.addInterceptor(new DeviceResolverHandlerInterceptor());
		registry.addInterceptor(new SitePreferenceHandlerInterceptor());

		SitePreferenceRepository sitePreferenceRepository = new CookieSitePreferenceRepository();
		SitePreferenceHandler sitePreferenceHandler = new StandardSitePreferenceHandler(
				sitePreferenceRepository);
		SiteUrlFactory normalsiteUrlFactory = new NormalSitePathUrlFactory("/");
		SiteUrlFactory mobileSiteUrlFactory = new MobileSitePathUrlFactory(
				"/m/", "/t/");
		SiteUrlFactory tabletSiteUrlFactory = new TabletSitePathUrlFactory(
				"/t/", "/m/");
		registry.addInterceptor(new SiteSwitcherHandlerInterceptor(
				normalsiteUrlFactory, mobileSiteUrlFactory,
				tabletSiteUrlFactory, sitePreferenceHandler));
		super.addInterceptors(registry);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
		super.addViewControllers(registry);
	}

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		// TODO Auto-generated method stub
		configurer.enable();
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
		// converters.add(new PersonMessageConverter(MediaType.ALL));
		// converters.add(new PersonMessageConverter());
	}

	@Bean
	public MappingJacksonHttpMessageConverter mappingJacksonHttpMessageConverter() {
		return new MappingJacksonHttpMessageConverter();
	}

	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		// TODO Auto-generated method stub

		super.configureAsyncSupport(configurer);
	}

	// /WebContentInterceptor

	@Bean
	public WebContentInterceptor getWebContentInterceptor() {
		WebContentInterceptor wci = new WebContentInterceptor();
		wci.setAlwaysUseFullPath(false);
		wci.setCacheSeconds(1000);
		wci.setUseCacheControlHeader(false);
		wci.setUseCacheControlNoStore(false);
		return wci;
	}

	// /////FOR READING RESOURCE BUNDLE//I18Support
	@Bean(name = "localeResolver")
	public LocaleResolver localeResolver() {
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setDefaultLocale(Locale.ITALY);
		return cookieLocaleResolver;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:i18n");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations(
				"/resources/");
	}

	// /////////////MULTIPART RESOLVER////////////////

	@Bean
	public CommonsMultipartResolver commonMultipartResolver() {
		CommonsMultipartResolver commonMultipartResolver = new CommonsMultipartResolver();
		commonMultipartResolver.setMaxUploadSize(10000);
		return commonMultipartResolver;
	}
}
