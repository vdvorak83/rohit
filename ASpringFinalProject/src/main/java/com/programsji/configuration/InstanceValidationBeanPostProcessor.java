package com.programsji.configuration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class InstanceValidationBeanPostProcessor implements BeanPostProcessor,
		Ordered {

	int order;

	public InstanceValidationBeanPostProcessor() {
		System.err.println("Created InstanceValidationBeanPostProcessor()");
	}

	@Override
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String arg1)
			throws BeansException {
		System.err.println("Created postProcessAfterInitialization()");
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String arg1)
			throws BeansException {
		System.err.println("Created postProcessBeforeInitialization()");
		return bean;
	}

}
