package com.rmicp.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "com.rmicp.bo", "com.rmicp.exception", "com.rmicp.dao",
		"com.rmicp.controller", "com.rmicp.service" })
public class RootConfig {

}
