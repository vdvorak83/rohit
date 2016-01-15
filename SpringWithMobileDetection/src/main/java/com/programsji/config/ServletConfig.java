package com.programsji.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.mobile.device.DeviceWebArgumentResolver;
import org.springframework.mobile.device.site.CookieSitePreferenceRepository;
import org.springframework.mobile.device.site.SitePreferenceHandler;
import org.springframework.mobile.device.site.SitePreferenceHandlerInterceptor;
import org.springframework.mobile.device.site.SitePreferenceHandlerMethodArgumentResolver;
import org.springframework.mobile.device.site.SitePreferenceRepository;
import org.springframework.mobile.device.site.StandardSitePreferenceHandler;
import org.springframework.mobile.device.switcher.MobileSitePathUrlFactory;
import org.springframework.mobile.device.switcher.NormalSitePathUrlFactory;
import org.springframework.mobile.device.switcher.SiteSwitcherHandlerInterceptor;
import org.springframework.mobile.device.switcher.SiteUrlFactory;
import org.springframework.mobile.device.switcher.TabletSitePathUrlFactory;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ServletWebArgumentResolverAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan({ "com.programsji" })
public class ServletConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addArgumentResolvers(
			List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new ServletWebArgumentResolverAdapter(
				new DeviceWebArgumentResolver()));
		argumentResolvers
				.add(new SitePreferenceHandlerMethodArgumentResolver());
	}

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setRedirectHttp10Compatible(false);
		internalResourceViewResolver.setOrder(1);
		internalResourceViewResolver.setPrefix("/WEB-INF/pages/");
		internalResourceViewResolver.setSuffix(".jsp");
		return internalResourceViewResolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

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

}
