package com.rmicp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.rmicp.controller.HomeController;

@Configuration
@ComponentScan(value = { "com.rmicp" })
@Import({ Security_Config.class })
public class RootConfig {

}
