package com.programsji.configuration;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.transaction.TransactionManager;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

@Configuration
@Profile(value = "internal")
public class DatabaseConfig_LocalProfile {

	private static String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
	private static String PROPERTY_NAME_DATABASE_USERNAME = "db.username";
	private static String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
	private static String PROPERTY_NAME_DATABASE_URL = "db.url";
	private static String PROPERTY_NAME_DATABASE_DIALECT = "db.dialect";
	private static String PROPERTY_NAME_DATABASE_SHOWSQL = "db.showsql";
	private static String PROPERTY_NAME_DATABASE_ENTITYMANAGER_PACKAGE_TO_SCAN = "db.packagetoscan";

	@Resource
	Environment env;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setDriverClassName(env
				.getProperty(PROPERTY_NAME_DATABASE_DRIVER));
		datasource
				.setPassword(env.getProperty(PROPERTY_NAME_DATABASE_PASSWORD));
		datasource.setUrl(env.getProperty(PROPERTY_NAME_DATABASE_URL));
		datasource
				.setUsername(env.getProperty(PROPERTY_NAME_DATABASE_USERNAME));
		return datasource;
	}

	@Bean
	public LocalSessionFactoryBean localSessionFactoryBean() {
		LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
		localSessionFactoryBean.setDataSource(dataSource());
		localSessionFactoryBean.setHibernateProperties(hibProperties());
		localSessionFactoryBean.setPackagesToScan("com.programsji.bo");
		return localSessionFactoryBean;
	}

	@Bean
	public HibernateTransactionManager hibernateTransactionManager() {
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager(
				localSessionFactoryBean().getObject());
		return hibernateTransactionManager;
	}

	private Properties hibProperties() {
		Properties properties = new Properties();
		properties.setProperty(PROPERTY_NAME_DATABASE_DIALECT,
				env.getProperty(PROPERTY_NAME_DATABASE_DIALECT));
		properties.setProperty(PROPERTY_NAME_DATABASE_SHOWSQL,
				env.getProperty(PROPERTY_NAME_DATABASE_SHOWSQL));
		return properties;
	}

}
