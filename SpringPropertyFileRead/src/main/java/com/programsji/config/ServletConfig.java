package com.programsji.config;

import javax.activation.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.core.io.Resource;

@Configuration
@EnableWebMvc
@ComponentScan({ "com.programsji" })
@PropertySource(value = { "classpath:property1.properties",
		"classpath:property2.properties" })
public class ServletConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private Environment env;

	private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
	private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
	private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";

	@Bean(name = "myproperties")
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		propertySourcesPlaceholderConfigurer.setLocations(new Resource[] {
				new ClassPathResource("property1.properties"),
				new ClassPathResource("property2.properties") });
		return propertySourcesPlaceholderConfigurer;
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

	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env
				.getProperty(PROPERTY_NAME_DATABASE_DRIVER));
		dataSource.setUrl(env.getProperty(PROPERTY_NAME_DATABASE_URL));
		dataSource
				.setUsername(env.getProperty(PROPERTY_NAME_DATABASE_USERNAME));
		dataSource
				.setPassword(env.getProperty(PROPERTY_NAME_DATABASE_PASSWORD));
		return dataSource;
	}
}
