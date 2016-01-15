package com.programsji.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Profile(value = "external")
@EnableTransactionManagement
public class DatabaseConfig_ForExternalProfile {
	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://192.168.1.244:3306/myexternaldatabase");
		dataSource.setUsername("nooneknows");
		dataSource.setPassword("secret");
		return dataSource;
	}
}
