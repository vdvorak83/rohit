package com.programsji.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.XmlViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan({"com.programsji"})
public class ServletConfig extends WebMvcConfigurerAdapter {

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
    public XmlViewResolver xmlViewResolver() {
        XmlViewResolver xmlViewResolver = new XmlViewResolver();
        xmlViewResolver.setLocation(new ClassPathResource("excelviews.xml"));
        xmlViewResolver.setOrder(0);
        return xmlViewResolver;
    }
}
