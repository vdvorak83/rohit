package com.programsji.configuration;

import java.sql.SQLException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Profile(value = "external")
@EnableTransactionManagement
public class DatabaseConfig_ExternalProfile {
	private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
	private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
	private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
	private static final String PROPERTY_NAME_DATABASE_DIALECT = "db.dialect";
	private static final String PROPERTY_NAME_DATABASE_SHOWSQL = "db.showsql";
	private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "db.packagestoscan";

	@Resource
	Environment env;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env
				.getProperty(PROPERTY_NAME_DATABASE_DRIVER));
		dataSource
				.setPassword(env.getProperty(PROPERTY_NAME_DATABASE_PASSWORD));
		dataSource.setUrl(env.getProperty(PROPERTY_NAME_DATABASE_URL));
		dataSource
				.setUsername(env.getProperty(PROPERTY_NAME_DATABASE_USERNAME));
		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean localSessionFactoryBean() {
		LocalSessionFactoryBean localsessionfactorybean = new LocalSessionFactoryBean();
		localsessionfactorybean.setDataSource(dataSource());
		localsessionfactorybean.setPackagesToScan("com.programsji.bo");
		localsessionfactorybean.setHibernateProperties(hibProperties());
		return localsessionfactorybean;
	}

	@Bean
	public HibernateTransactionManager hibernateTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(
				localSessionFactoryBean().getObject());
		return transactionManager;
	}

	private Properties hibProperties() {
		Properties properties = new Properties();
		properties.put(PROPERTY_NAME_DATABASE_DIALECT,
				env.getProperty(PROPERTY_NAME_DATABASE_DIALECT));
		properties.put(PROPERTY_NAME_DATABASE_SHOWSQL,
				env.getProperty(PROPERTY_NAME_DATABASE_SHOWSQL));

		properties.put("hibernate.hbm2ddl.auto", "create-drop");
		return properties;
	}

}
