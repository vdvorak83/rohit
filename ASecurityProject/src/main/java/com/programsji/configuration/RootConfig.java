package com.programsji.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.programsji.controller.HomeController;

@Configuration
@ComponentScan(value = { "com.programsji" })
@Import({ Security_Config.class })
public class RootConfig {

}
