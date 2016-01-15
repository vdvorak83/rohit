package com.programsji.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SecurityConfig.class, ServletConfig.class })
@ComponentScan(value = { "com.programsji" })
public class RootConfig {

}